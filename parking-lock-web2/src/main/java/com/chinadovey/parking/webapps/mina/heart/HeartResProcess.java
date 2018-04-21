package com.chinadovey.parking.webapps.mina.heart;

import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.mina.core.session.IoSession;

import com.chinadovey.parking.webapps.mina.protocol.PacketsEntity;

public class HeartResProcess {
	
	private static Log logger = LogFactory.getLog(HeartResProcess.class);
	
	private String localAddress;
	
	public HeartResProcess(String localAddress) {
		this.localAddress = localAddress;
	}
	
	/**
	 * tag:0x0005H DAS回应心跳包
	 * @throws SQLException 
	 */
	public void p0x0005(IoSession session, PacketsEntity packets) throws SQLException {
		

	}
}
