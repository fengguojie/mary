package com.chinadovey.parking.webapps.biz;

import java.util.Map;


public interface CarlockCheckLogBiz{

	Map<String, Object> getList(int page, int rows, String search, String sort, String order, String start, String end);

}
