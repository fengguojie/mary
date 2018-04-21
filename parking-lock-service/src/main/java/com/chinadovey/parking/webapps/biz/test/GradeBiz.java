package com.chinadovey.parking.webapps.biz.test;

import java.util.Map;

import com.chinadovey.parking.webapps.pojo.Grade;

public interface GradeBiz {
	
	void add(Grade grade);
	
	void findByGradeType(String gradeType);
	
	void update(Grade grade);
	
	void delete(Integer id);
	
	Map<String, Object> getList(int page, int rows, String search, String sort, String order);

}
