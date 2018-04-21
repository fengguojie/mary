package com.chinadovey.parking.webapps.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinadovey.parking.core.supports.mybatis.pagination.PageContext;
import com.chinadovey.parking.core.supports.mybatis.pagination.Pagination;
import com.chinadovey.parking.webapps.biz.baseInfo.CarUserSpaceBiz;
import com.chinadovey.parking.webapps.mappers.gen.CarSpaceMapper;
import com.chinadovey.parking.webapps.mappers.gen.ParkMapper;
import com.chinadovey.parking.webapps.pojo.CarSpace;
import com.chinadovey.parking.webapps.pojo.CarUserRelCarSpace;
import com.chinadovey.parking.webapps.pojo.Park;

@Service
public class CarUserSpaceBizImpl implements CarUserSpaceBiz {


	@Autowired
	private CarSpaceMapper carSpaceMapper;

	@Autowired
	private ParkMapper parkMapper;

	@Override
	public List<CarUserRelCarSpace> getCarUserSpaceByCarUserId(Integer carUserId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public CarUserRelCarSpace find(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(CarUserRelCarSpace rel) {
		// TODO Auto-generated method stub
		
	}


}
