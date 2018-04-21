package com.chinadovey.parking.webapps.biz.test;

import java.util.Map;

import com.chinadovey.parking.webapps.pojo.Person;

public interface PersonBiz {
	
	void add(Person person);
	
	void findByName(String name);
	
	void update(Person person);
	
	void delete(Integer id);
	
	Map<String, Object> getList(int page, int rows, String search, String sort, String order);

}
