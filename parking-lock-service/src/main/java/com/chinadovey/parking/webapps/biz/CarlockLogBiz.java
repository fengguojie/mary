package com.chinadovey.parking.webapps.biz;

import java.util.Map;

import com.chinadovey.parking.webapps.pojo.CarlockLog;


public interface CarlockLogBiz{

	void save(CarlockLog log);

	Map<String, Object> getList(int page, int rows, String sort, String order,
			String search, String start, String end);

}
