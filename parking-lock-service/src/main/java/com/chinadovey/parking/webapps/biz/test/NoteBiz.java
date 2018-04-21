package com.chinadovey.parking.webapps.biz.test;

import java.util.Map;

import com.chinadovey.parking.webapps.pojo.CarLock;
import com.chinadovey.parking.webapps.pojo.Note;

public interface NoteBiz {
	
	void add(Note note);
	
	void findById(Integer id);
	
	void update(Note note);
	
	void delete(Integer id);
	
	Map<String, Object> getList(int page, int rows, String search, String sort, String order);

	Note find(int id);

	boolean checkName(String title);

	boolean checkNameById(int id, String title);

}
