package com.chinadovey.parking.webapps.biz.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinadovey.parking.webapps.biz.CarLockDataBiz;
import com.chinadovey.parking.webapps.mappers.gen.CarLockDataMapper;
import com.chinadovey.parking.webapps.pojo.CarLock;
import com.chinadovey.parking.webapps.pojo.CarLockData;
import com.chinadovey.parking.webapps.pojo.CarLockDataExample;
import com.chinadovey.parking.webapps.utils.DateUtil;
@Service
public class CarLockDataBizImpl  implements CarLockDataBiz{

	@Autowired
	private CarLockDataMapper mapper;
	
	@Override
	public boolean find(String slaveId) {
		CarLockDataExample example=new CarLockDataExample();
		example.createCriteria().andSlaveIdEqualTo(slaveId);
		List<CarLockData> list=	mapper.selectByExample(example);
		if(list!=null && !list.isEmpty()){
			CarLockData data = list.get(0);
			int minutes = DateUtil.getBetweenMinutesByTime(new Date(), data.getCollectTime());
			if(minutes<60){
				return true;
			}else{
				return false;
			}
		}else{
		  return false;
		}
	}

	@Override
	public HashSet<String> findInTwohours() {
		CarLockDataExample e=new CarLockDataExample();
		Date TwohoursAgo=new Date(new Date().getTime()-2*60*60*1000);
		e.createCriteria().andCollectTimeGreaterThan(TwohoursAgo);
		List<CarLockData> list = mapper.selectByExample(e);
		
		HashSet<String> slaveIds=new HashSet<String>();
		
		if(list!=null && !list.isEmpty()){
			for(CarLockData c:list){
				slaveIds.add(c.getSlaveId());
			}
		}
		return slaveIds;
	}
	
	

}
