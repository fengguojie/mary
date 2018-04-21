package com.chinadovey.parking.webapps.biz.test;

import java.util.Map;

import com.chinadovey.parking.webapps.pojo.Person;
import com.chinadovey.parking.webapps.pojo.Pp_Goods;

public interface PpGoodsBiz {
	
	void add(Pp_Goods pp_Goods);
	
	void findByGname(String gname);
	
	void update(Pp_Goods pp_Goods);
	
	void delete(Integer id);
	
	Map<String, Object> getList(int page, int rows, String search, String sort, String order);

	boolean checkName(String gname);

	boolean checkNameById(int id, String gname);

	Pp_Goods find(int id);

}
