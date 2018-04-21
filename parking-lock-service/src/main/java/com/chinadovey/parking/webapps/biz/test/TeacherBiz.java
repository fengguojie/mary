package com.chinadovey.parking.webapps.biz.test;

import java.util.Map;

import com.chinadovey.parking.webapps.pojo.Teacher;

public interface TeacherBiz {
	
	void add(Teacher teacher);
	
	void findByTeacherName(String teacherName);
	
	void update(Teacher teacher);
	
	void delete(Integer teacherId);
	
	Map<String, Object> getList(int page, int rows, String search, String sort, String order);

	boolean checkName(String teacherName);

	boolean checkNameById(int teacherId, String teacherName);

	Teacher find(int id);



}
