package com.chinadovey.parking.webapps.biz.test;

import java.util.Map;

import com.chinadovey.parking.webapps.pojo.Student;

public interface StudentBiz {
	
	void add(Student student);
	
	void findBySno(String sno);
	
	void update(Student student);
	
	void delete(Integer id);
	
	Map<String, Object> getList(int page, int rows, String search, String sort, String order);

}
