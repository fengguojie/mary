package com.chinadovey.parking.webapps.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinadovey.parking.webapps.biz.UserRelParkBiz;
import com.chinadovey.parking.webapps.mappers.gen.UserRelParkMapper;
import com.chinadovey.parking.webapps.pojo.UserRelPark;
import com.chinadovey.parking.webapps.pojo.UserRelParkExample;
@Service
public class UserRelParkBizImpl implements UserRelParkBiz{
	@Autowired
	private UserRelParkMapper userRelParkMapper;
	
	@Override
	public List<UserRelPark> getByUserId(int id) {
		UserRelParkExample e = new UserRelParkExample();
		e.createCriteria().andUserIdEqualTo(id);
		List<UserRelPark> list = userRelParkMapper.selectByExample(e);
		return list;
	}

}
