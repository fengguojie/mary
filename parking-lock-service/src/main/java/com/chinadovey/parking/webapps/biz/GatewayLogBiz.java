package com.chinadovey.parking.webapps.biz;

import java.util.Map;

import com.chinadovey.parking.webapps.pojo.GatewayLog;

public interface GatewayLogBiz{

	Map<String, Object> getList(int page, int rows, String search, String sort, String order, String start,
			String end);
	
	public void save(GatewayLog gatewayLog);

}
