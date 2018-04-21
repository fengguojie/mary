package com.chinadovey.parking.webapps.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinadovey.parking.webapps.biz.system.UserRelRoleBiz;
import com.chinadovey.parking.webapps.mappers.gen.UserRelRoleMapper;
import com.chinadovey.parking.webapps.pojo.UserRelRole;
import com.chinadovey.parking.webapps.pojo.UserRelRoleExample;

@Service
public class UserRelRoleBizImpl implements UserRelRoleBiz{
	
	@Autowired
	private UserRelRoleMapper userRelRoleMapper;

	@Override
	public List<UserRelRole> findByUserId(Integer userId) {
		UserRelRoleExample example = new UserRelRoleExample();
		example.createCriteria().andUserIdEqualTo(userId);
		return userRelRoleMapper.selectByExample(example);
	}

}
