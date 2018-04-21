package com.chinadovey.parking.webapps.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinadovey.parking.webapps.biz.CarLockDataLocalCacheBiz;
import com.chinadovey.parking.webapps.mappers.gen.CarLockDataLocalCacheMapper;
import com.chinadovey.parking.webapps.pojo.CarLockDataLocalCache;
import com.chinadovey.parking.webapps.pojo.CarLockDataLocalCacheExample;
@Service
public class CarLockDataLocalCacheBizImpl  implements CarLockDataLocalCacheBiz{

	@Autowired
	private CarLockDataLocalCacheMapper mapper;
	

	@Override
	public List<CarLockDataLocalCache> getList() {
		CarLockDataLocalCacheExample e = new CarLockDataLocalCacheExample();
		e.setOrderByClause("collect_time asc limit 100");
		return mapper.selectByExample(e);
	}

	@Override
	public void deleteByIds(List<Long> ids) {
		for(Long id : ids){
			mapper.deleteByPrimaryKey(id);
		}
	}
	
}
