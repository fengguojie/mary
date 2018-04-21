package com.chinadovey.parking.webapps.mina;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.proxy.utils.ByteUtilities;

import com.chinadovey.parking.webapps.biz.DasConnectBiz;
import com.chinadovey.parking.webapps.biz.GatewayLogBiz;
import com.chinadovey.parking.webapps.mina.heart.HeartResProcess;
import com.chinadovey.parking.webapps.mina.pojo.RTURealTime;
import com.chinadovey.parking.webapps.mina.protocol.CMDPackets;
import com.chinadovey.parking.webapps.mina.protocol.DASPackets;
import com.chinadovey.parking.webapps.mina.protocol.ERRPackets;
import com.chinadovey.parking.webapps.mina.protocol.PacketsEntity;
import com.chinadovey.parking.webapps.mina.protocol.RESPackets;
import com.chinadovey.parking.webapps.mina.protocol.RTUPackets;
import com.chinadovey.parking.webapps.pojo.DasConnect;
import com.chinadovey.parking.webapps.utils.ByteUtil;

public class DPSProcess {

	private static Log logger = LogFactory.getLog(DPSProcess.class);

	private String localAddress;
	private DasConnectBiz dasConnectBiz;
	private RTUProcessFactory rtuProcessFactory;
	private GatewayLogBiz gatewayLogBiz;

	private CloudNodeIoHandler ioHandler;

	public void messageProcess(IoSession session, PacketsEntity packets) throws SQLException, IOException {

		if (packets.getTag().length < 2) {
			logger.warn("TranPackets 的 TAG 为"
					+ ByteUtilities.asHex(packets.getTag()));
			return;
		}

		switch (packets.getTag()[0]) {
		case 0x00:
			/*
			 * tag:0x00XXH DAS => DPS
			 */
			switch (packets.getTag()[1]) {
			case 0x01:
				// 数据上报
				//p0x0001(session, packets);
				DpsReportProcess rrp = new DpsReportProcess(localAddress, dasConnectBiz, ioHandler,rtuProcessFactory);
				rrp.p0x0001(session,packets);
				return;
			//case 0x02:
				// 异常上报
				//p0x0003(session, packets);
			//	return;
			case 0x04:
				//网关首次数据上报
				//p0x0004(session, packets);
				DpsFirstReportProcess rfrp = new DpsFirstReportProcess(localAddress, dasConnectBiz, ioHandler,gatewayLogBiz);
				rfrp.p0x0004(session,packets);
				return;
			case 0x03:
				// 控制回应
				p0x0003(session, packets);
				return;
			case 0x05:
				HeartResProcess hrp = new HeartResProcess(localAddress);
				hrp.p0x0005(session,packets);
				return;
			}
		//case 0x01:
			/*
			 * tag:0x01XXH DPS => DAS 如果需要在DPS直接转发的消息，在这里处理
			 */
		//	return;
		case 0x02:
			/*
			 * tag:0x02XXH APP => DPS 控制命令
			 */
			switch (packets.getTag()[1]) {
			case 0x01://车位锁开关控制
				p0x02XX(session, packets);
				break;
			case 0x03://配置车位锁
				ConfDpsProcess conf = new ConfDpsProcess(localAddress, dasConnectBiz, ioHandler);
				conf.p0x0203(session,packets);
				break;
			case 0x04://删除车位锁配置
				ConfDpsProcess delete = new ConfDpsProcess(localAddress, dasConnectBiz, ioHandler);
				delete.p0x0204(session,packets);
				break;
			case 0x05://网关配置
				ConfDasPortProcess cdpp = new ConfDasPortProcess(localAddress, dasConnectBiz, ioHandler);
				cdpp.p0x0205(session,packets);
				break;
				default:
					break;
			}
			break;
		//case 0x03:
			/*
			 * tag:0x03XXH DPS => APP 基本用不上
			 */
		//	return;
		default:
			logger.error("未知的Packets TAG" + ByteUtil.asHex(packets.getTag()));
			break;
		}
	}

	/**
	 * tag:0x02XXH DAS的下行命令包
	 */
	private void p0x02XX(IoSession session, PacketsEntity packets) {
		if (logger.isDebugEnabled()) {
			logger.debug("TAG:0x02XXH,DAS的下行命令包");
		}
		// 解析CMD数据包
		CMDPackets cmdParkets = (CMDPackets) packets.getValuePackets();
		if (logger.isDebugEnabled()) {
			logger.debug("成功解析CMD数据包：\n" + cmdParkets);
		}

		// 寻找DAS长连接
		/*Query query = Query.query(
				Criteria.where("id").is(ByteUtil.asHex(cmdParkets.getDasId()))
				.and("slaveAddress").is(localAddress));
		DASRealTime dasRealTime = dasConnectBiz.findOne(query, DASRealTime.class);*/
		DasConnect dasConnect = dasConnectBiz.getByDasIdAndServerAddress(ByteUtil.asHex(cmdParkets.getDasId()),localAddress);
		if (logger.isDebugEnabled()) {
			logger.debug("成功获取到 dasRealTime：" + dasConnect);
		}
		
		if (dasConnect != null) {
			
			int cacheId = ioHandler.cacheSession(session);
			byte[] b = new byte[4];
			ByteUtil.writeInt4(cacheId, b, 0);
			if (logger.isDebugEnabled()) {
				logger.debug("成功缓存发送者会话 AppId="+cacheId+" "+ByteUtil.asHex(b));
			}
			
			cmdParkets.setAppId(b);
			//cmdParkets.setDasId(dasRealTime.getDasIdBytes());  下 li
			cmdParkets.setDasId(ByteUtil.writeInt4(Integer.parseInt(dasConnect.getDasId(),16), 0));
			
			// 发送命令
			IoSession targetSession = ioHandler.getSession(dasConnect.getSessionId());
			if (logger.isDebugEnabled()) {
				logger.debug("成功获取到目标IoSession：targetSession = " + targetSession);
			}
			
			if (targetSession != null) {
				packets.getTag()[0] = 0x01;
				packets.finishFilling();
					targetSession.write(packets);
					if (logger.isInfoEnabled()) {
						logger.info("发送下行包：\n" + packets + "\n==命令包==\n" + cmdParkets);
					}
			}
			return;
		}
		
		// TODO 长连接没有找到，将来需要尝试转发到其他Node节点，现在先返回连接失败信息
		if (logger.isInfoEnabled()) {
			logger.info("没有找到RTU[" + ByteUtil.asHex(cmdParkets.getDasId()) + "]的连接信息，估计连接已断开!");
		}
		// TODO 发送错误包
		PacketsEntity entity = new PacketsEntity();
		entity.setTag(new byte[] { 0x03, 0x02 });
		entity.setIndex(new byte[] { 0x00, 0x00 });

		ERRPackets err = new ERRPackets();
		err.setTag(new byte[] { 0x7F, 0x7F });
		err.setDasId(cmdParkets.getDasId());
		err.setTime(new byte[] { 0x00, 0x00, 0x00, 0x00 });
		err.setCode(new byte[] { 0x7F, 0x7F });

		entity.setValuePackets(err);
		entity.finishFilling();

		session.write(entity);
		session.close(false);
	}

	/**
	 * tag:0x0003H DAS的下行命令反馈包
	 */
	private void p0x0003(IoSession session, PacketsEntity packets) {
		if (logger.isDebugEnabled()) {
			logger.debug("TAG:0x0003H,DAS的下行命令反馈包");
		}

		if(packets.getValuePackets() instanceof RESPackets){
			RESPackets resPackets = (RESPackets) packets.getValuePackets();
			int appId = ByteUtil.makeIntFromByte(resPackets.getAppId());
			IoSession targetSession = ioHandler.getCachedSession(appId,false);
			if (logger.isDebugEnabled()) {//AppId=131655 00000247
				logger.debug("尝试获取命令发送者会话 AppId="+appId+" "+ByteUtil.asHex(resPackets.getAppId()));
			}
			if (targetSession != null) {
				logger.debug("向命令发送者转发命令反馈");
				packets.getTag()[0] = 0x03;
				targetSession.write(packets);
				logger.debug("等待客户端关闭会话...");
//				targetSession.getCloseFuture().awaitUninterruptibly(5000);
//				logger.info("断开命令发送者会话 SESSION=" + targetSession.getId());
			} else {
				logger.debug("命令发送者会话未找到，估计已断开");
			}
		}
		
	}
	
	
	
	/**
	 * tag:0x0003H DAS的下行命令反馈包
	 */
	private void p0x0005(IoSession session, PacketsEntity packets) {
		if (logger.isDebugEnabled()) {
			logger.debug("TAG:0x0003H,DAS的下行命令反馈包");
		}
		if(packets.getValuePackets() instanceof RESPackets){
			RESPackets resPackets = (RESPackets) packets.getValuePackets();
			int appId = ByteUtil.makeIntFromByte(resPackets.getAppId());
			IoSession targetSession = ioHandler.getCachedSession(appId,false);
			if (logger.isDebugEnabled()) {//AppId=131655 00000247
				logger.debug("尝试获取命令发送者会话 AppId="+appId+" "+ByteUtil.asHex(resPackets.getAppId()));
			}
			if (targetSession != null) {
				logger.debug("向命令发送者转发命令反馈");
				packets.getTag()[0] = 0x03;
				targetSession.write(packets);
				logger.debug("等待客户端关闭会话...");
//				targetSession.getCloseFuture().awaitUninterruptibly(5000);
//				logger.info("断开命令发送者会话 SESSION=" + targetSession.getId());
			} else {
				logger.debug("命令发送者会话未找到，估计已断开");
			}
		}
		
	}

	public void setLocalAddress(String localAddress) {
		this.localAddress = localAddress;
	}

	public void setDasConnectBiz(DasConnectBiz dasConnectBiz) {
		this.dasConnectBiz = dasConnectBiz;
	}

	public void setRtuProcessFactory(RTUProcessFactory rtuProcessFactory) {
		this.rtuProcessFactory = rtuProcessFactory;
	}

	public void setIoHandler(CloudNodeIoHandler ioHandler) {
		this.ioHandler = ioHandler;
	}

	public GatewayLogBiz getGatewayLogBiz() {
		return gatewayLogBiz;
	}

	public void setGatewayLogBiz(GatewayLogBiz gatewayLogBiz) {
		this.gatewayLogBiz = gatewayLogBiz;
	}
	
	
}
