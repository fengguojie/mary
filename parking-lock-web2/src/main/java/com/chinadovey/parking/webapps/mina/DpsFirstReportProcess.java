package com.chinadovey.parking.webapps.mina;

import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.mina.core.session.IoSession;

import com.chinadovey.parking.webapps.biz.DasConnectBiz;
import com.chinadovey.parking.webapps.biz.GatewayLogBiz;
import com.chinadovey.parking.webapps.mina.protocol.DASReportPackets;
import com.chinadovey.parking.webapps.mina.protocol.PacketsEntity;
import com.chinadovey.parking.webapps.pojo.DasConnect;
import com.chinadovey.parking.webapps.pojo.GatewayLog;
import com.chinadovey.parking.webapps.utils.ByteUtil;

public class DpsFirstReportProcess {
	
	private static Log logger = LogFactory.getLog(DPSProcess.class);
	
	private String localAddress;
	private DasConnectBiz dasConnectBiz;
	private CloudNodeIoHandler ioHandler;
	private GatewayLogBiz gatewayLogBiz;
	
	public DpsFirstReportProcess(String localAddress , DasConnectBiz dasConnectBiz , CloudNodeIoHandler ioHandler,GatewayLogBiz gatewayLogBiz) {
		this.localAddress = localAddress;
		this.dasConnectBiz = dasConnectBiz;
		this.ioHandler = ioHandler;
		this.gatewayLogBiz = gatewayLogBiz;
	}
	
	/**
	 * tag:0x0004H DAS上线数据包
	 * @throws SQLException 
	 */
	public void p0x0004(IoSession session, PacketsEntity packets) throws SQLException {
		DASReportPackets dasPackets = (DASReportPackets) packets.getValuePackets();
		try {
			String dasId =  ByteUtil.asHex(dasPackets.getDasId());
			GatewayLog gatewayLog = new GatewayLog();
			gatewayLog.setDasId(dasId);
			gatewayLog.setStatus(2);
			gatewayLog.setTime(new Date());
			gatewayLogBiz.save(gatewayLog);
			
			/*DASRealTime dasRelaTime = new DASRealTime();
			dasRelaTime.setId(ByteUtil.asHex(dasPackets.getDasId()));
			dasRelaTime.setDasIdBytes(dasPackets.getDasId());
			dasRelaTime.setSessionId(session.getId());
			dasRelaTime.setSlaveAddress(localAddress);
			dasRelaTime.setDasVersion(ByteUtil.asHex(dasPackets.getValue()));
			
			
			Query query = Query.query(
					Criteria.where("id").is(dasRelaTime.getId()));
			Update update = Update
					.update("slaveAddress", dasRelaTime.getSlaveAddress())
					.set("sessionId", dasRelaTime.getSessionId())
					.set("dasVersion", dasRelaTime.getDasVersion())
					.set("dasIdBytes", dasRelaTime.getDasIdBytes());
            mongoOps.upsert(query, update, DASRealTime.class);*/
            
            DasConnect dasConnect = new DasConnect();
			dasConnect.setDasId(ByteUtil.asHex(dasPackets.getDasId()));
			dasConnect.setSessionId(session.getId());
			dasConnect.setServerAddress(localAddress);
			dasConnect.setDasVersion(ByteUtil.asHex(dasPackets.getValue()));
			
			boolean flag = dasConnectBiz.getByDasId(dasConnect.getDasId());
			if(flag){
				//更新
				dasConnectBiz.updateByDasId(dasConnect);
			}else{
				//插入
				dasConnectBiz.insert(dasConnect);
			}
            
            
		} catch (Exception e) {
			logger.error(e.getMessage() + "\n DasPackets:\n" + dasPackets, e);
			e.printStackTrace();
		}
	}
}
