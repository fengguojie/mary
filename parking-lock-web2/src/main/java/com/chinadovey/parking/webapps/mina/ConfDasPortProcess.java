package com.chinadovey.parking.webapps.mina;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.mina.core.session.IoSession;

import com.chinadovey.parking.webapps.biz.DasConnectBiz;
import com.chinadovey.parking.webapps.mina.protocol.DASSerialPortPackets;
import com.chinadovey.parking.webapps.mina.protocol.ERRPackets;
import com.chinadovey.parking.webapps.mina.protocol.PacketsEntity;
import com.chinadovey.parking.webapps.pojo.DasConnect;
import com.chinadovey.parking.webapps.utils.ByteUtil;

public class ConfDasPortProcess {
	
	private static Log logger = LogFactory.getLog(DPSProcess.class);
	
	private String localAddress;
	private DasConnectBiz dasConnectBiz;
	private CloudNodeIoHandler ioHandler;
	
	public ConfDasPortProcess(String localAddress , DasConnectBiz dasConnectBiz, CloudNodeIoHandler ioHandler) {
		this.localAddress = localAddress;
		this.dasConnectBiz = dasConnectBiz;
		this.ioHandler = ioHandler;
	}
	
	public void p0x0205(IoSession session, PacketsEntity packets) {
		if (logger.isDebugEnabled()) {
			logger.debug("TAG:0x02XXH,DAS的下行命令包");
		}
		// 解析CMD数据包
		DASSerialPortPackets cmdParkets = (DASSerialPortPackets) packets.getValuePackets();
		if (logger.isDebugEnabled()) {
			logger.debug("成功解析CMD数据包：\n" + cmdParkets);
		}

		// 寻找DAS长连接
		/*Query query = Query.query(
				Criteria.where("id").is(ByteUtil.asHex(cmdParkets.getDasId()))
				.and("slaveAddress").is(localAddress));
		DASRealTime dasRealTime = mongoOps.findOne(query, DASRealTime.class);*/
		
		DasConnect dasConnect = dasConnectBiz.getByDasIdAndServerAddress(ByteUtil.asHex(cmdParkets.getDasId()), localAddress);
		
		if (logger.isDebugEnabled()) {
			logger.debug("成功获取到 DASRealTime：" + dasConnect);
		}
		
		if (dasConnect != null) {
			int cacheId = ioHandler.cacheSession(session);
			byte[] b = new byte[4];
			ByteUtil.writeInt4(cacheId, b, 0);
			if (logger.isDebugEnabled()) {
				logger.debug("成功缓存发送者会话 AppId="+cacheId+" "+ByteUtil.asHex(b));
			}
			
			cmdParkets.setAppId(b);
			cmdParkets.setDasId(ByteUtil.writeInt4(Integer.parseInt(dasConnect.getDasId(),16), 0));
			
			// 发送命令
			IoSession targetSession = ioHandler.getSession(dasConnect.getSessionId());
			if (logger.isDebugEnabled()) {
				logger.debug("成功获取到目标IoSession：targetSession = " + targetSession);
			}
			
			if (targetSession != null) {
				packets.setTag(new byte[]{0x01,0x01}); 
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
	
}
