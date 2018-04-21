package com.chinadovey.parking.webapps.pojo;

public class CarLockSerial {
	/**
	 * 网关编号
	 * 
	 */
	private String gatewayNo;
	/**
	 * 车锁编号
	 * 
	 */
	private String slaveId;
	/**
	 * 串口
	 * 
	 */
	private String serial;
	
	public String getGatewayNo() {
		return gatewayNo;
	}
	
	public String getSlaveId() {
		return slaveId;
	}
	
	public String getSerial() {
		return serial;
	}

	public void setGatewayNo(String gatewayNo) {
		this.gatewayNo = gatewayNo;
	}

	public void setSlaveId(String slaveId) {
		this.slaveId = slaveId;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}
	
	

}
