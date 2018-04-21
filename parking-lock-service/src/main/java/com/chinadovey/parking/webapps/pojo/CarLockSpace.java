package com.chinadovey.parking.webapps.pojo;

public class CarLockSpace {
	/**
	 * id
	 * 
	 */
	private int id ;
	/**
	 * 车场id
	 * 
	 */
	private int parkId;
	/**
	 * 网关编号
	 * 
	 */
	private String gatewayNo;
	/**
	 * 车锁编号
	 * 
	 */
	private String SlaveId;
	/**
	 * 车位名称
	 * 
	 */
	private String spaceName;
	/**
	 * 车位编号
	 * 
	 */
	private String spaceNo;
	/**
	 * 延时
	 * 
	 */
	private int delay;
	
	public int getId() {
		return id;
	}
	
	public int getParkId() {
		return parkId;
	}

	public String getGatewayNo() {
		return gatewayNo;
	}
	
	public String getSlaveId() {
		return SlaveId;
	}
	
	public String getSpaceName() {
		return spaceName;
	}
	
	public void setSpaceNo(String spaceNo) {
		this.spaceNo = spaceNo;
	}

	public int getDelay() {
		return delay;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void setParkId(int parkId) {
		this.parkId = parkId;
	}

	public void setGatewayNo(String gatewayNo) {
		this.gatewayNo = gatewayNo;
	}

	public void setSlaveId(String slaveId) {
		SlaveId = slaveId;
	}

	public void setSpaceName(String spaceName) {
		this.spaceName = spaceName;
	}
	
	public String getSpaceNo() {
		return spaceNo;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}
	

}
