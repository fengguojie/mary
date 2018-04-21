package com.chinadovey.parking.webapps.biz;

import java.util.List;
import java.util.Map;

import com.chinadovey.parking.webapps.pojo.Device;

public interface DeviceBiz{
	
	Map<String, Object> getList(int page, int rows, String search, String sort, String order, List<String> slaveIds);
    
	Device getBySlaveid(String slaveId);
	
	public Device getByDeviceId(String deviceId,String deviceType);
	
	Map<String, Object> getList(int page, int rows, String search, String sort, String order, List<String> slaveIds,List<String> companyNo);
}
