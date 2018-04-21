package com.chinadovey.parking.webapps.mina.heart;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;

import com.chinadovey.parking.webapps.biz.DasConnectBiz;
import com.chinadovey.parking.webapps.mina.protocol.PacketsEntity;
import com.chinadovey.parking.webapps.pojo.DasConnect;

public class KeepAliveMessageFactoryImpl implements  KeepAliveMessageFactory { 
	
	private static final Logger logger = Logger.getLogger(KeepAliveMessageFactoryImpl.class);
	
	private DasConnectBiz dasConnectBiz;
	
	public KeepAliveMessageFactoryImpl(DasConnectBiz dasConnectBiz){
		this.dasConnectBiz = dasConnectBiz;
	}
	
	private final static byte[] tag = {0x01,0x02};
	private final static byte[] index = {0x00,0x00};
	
	
	private final static String minVersion = "00070303";
	
	@Override  
	public boolean isRequest(IoSession session, Object message) {
		try {
			System.out.println("判断是否dps->das 心跳包");
			if (message instanceof PacketsEntity) {
				PacketsEntity entity = (PacketsEntity) message;
				if (entity != null && entity.getValuePackets() instanceof HeartPackets) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;  
	}  

	@Override  
	public boolean isResponse(IoSession session, Object message) { 
		try {
			System.out.println("判断是否das->dps 心跳包");
			if (message instanceof PacketsEntity) {
				PacketsEntity entity = (PacketsEntity) message;
				if (entity != null && entity.getValuePackets() instanceof HeartPackets) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;  
	}
	
	@Override  
	public Object getRequest(IoSession session) {
		try {
			logger.debug("得到dps-》das心跳包");
			Long sessionId = session.getId();
//			Query query = Query.query(Criteria.where("sessionId").is(sessionId));
//			DASRealTime dasRealTime = mongoOps.findOne(query, DASRealTime.class);
			
			DasConnect dasConnect = dasConnectBiz.findOne(sessionId);
			
			if(dasConnect != null){
				String dasVersion = dasConnect.getDasVersion();
				if (dasVersion != null && dasVersion.length() == 10) {
					String realVersion =  dasVersion.substring(2);
					if (realVersion.compareTo(minVersion) >= 0) {
						PacketsEntity entity = new PacketsEntity();
						entity.setTag(tag);
						entity.setIndex(index);
						HeartPackets heartPackets = new HeartPackets();
						entity.setValuePackets(heartPackets);
						entity.finishFilling();
						return entity;//版本在00070303之后 发送心跳包
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;  
	}  
	
	@Override  
	public Object getResponse(IoSession session, Object request) {
		logger.debug("得到das-》dps心跳包");
		return null; 
	} 
	

}
