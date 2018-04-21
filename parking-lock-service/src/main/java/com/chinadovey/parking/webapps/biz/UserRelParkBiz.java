package com.chinadovey.parking.webapps.biz;

import java.util.List;

import com.chinadovey.parking.webapps.pojo.UserRelPark;

public interface UserRelParkBiz {

	List<UserRelPark> getByUserId(int id);

}
