package com.chinadovey.parking.webapps.biz.system;

import java.util.List;

import com.chinadovey.parking.webapps.pojo.UserRelRole;

public interface UserRelRoleBiz {
	//通过用户id查询对应的roleId
	public List<UserRelRole> findByUserId(Integer userId);

}
