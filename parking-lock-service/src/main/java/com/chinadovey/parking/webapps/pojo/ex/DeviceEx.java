package com.chinadovey.parking.webapps.pojo.ex;

import com.chinadovey.parking.webapps.pojo.CarLock;
import com.chinadovey.parking.webapps.pojo.Device;

/** 
* @author 作者  xqy
* @version 创建时间：2016年8月22日 下午3:35:54 
* 类说明 
*/
public class DeviceEx {
	
	private String slaveId;
	private Float voltage;
	private String mac;
	private String type;
	private String deviceId;
	private String password;
	private int id;
	private int switchStatus;
	private String bindNo;
	private String companyNo;
	
	public static DeviceEx change(Device device,CarLock carLock) {
		DeviceEx ex = new DeviceEx();
		ex.setId(device.getId());
		ex.setMac(device.getMac());
		ex.setPassword(device.getPassword());
		ex.setDeviceId(device.getDeviceId());
		ex.setType(device.getType());
		ex.setSlaveId(device.getSlaveId());
		if(carLock!=null){
			ex.setBindNo(carLock.getBindNo());
			ex.setVoltage(carLock.getVoltage());
			ex.setSwitchStatus(carLock.getSwitchStatus());
		}
		return ex;
	}


	public String getSlaveId() {
		return slaveId;
	}


	public void setSlaveId(String slaveId) {
		this.slaveId = slaveId;
	}


	public Float getVoltage() {
		return voltage;
	}


	public void setVoltage(Float voltage) {
		this.voltage = voltage;
	}


	public String getMac() {
		return mac;
	}


	public void setMac(String mac) {
		this.mac = mac;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getDeviceId() {
		return deviceId;
	}


	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}


	public int getSwitchStatus() {
		return switchStatus;
	}


	public void setSwitchStatus(int switchStatus) {
		this.switchStatus = switchStatus;
	}


	public String getBindNo() {
		return bindNo;
	}


	public void setBindNo(String bindNo) {
		this.bindNo = bindNo;
	}


	public String getCompanyNo() {
		return companyNo;
	}


	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	
}
