package com.chinadovey.parking.webapps.biz.test;

import java.util.Map;

import com.chinadovey.parking.webapps.pojo.UserJellard;

public interface UserJellardBiz {
    void add(UserJellard userJellard);
	
	void findById(String id);
	
	void update(UserJellard userJellard);
	
	void delete(Integer id);
	
	Map<String, Object> getList(int page, int rows, String search, String sort, String order);

	int findByPassword(String username, String password);


}
