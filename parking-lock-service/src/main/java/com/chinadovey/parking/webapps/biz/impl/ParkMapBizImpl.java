package com.chinadovey.parking.webapps.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinadovey.parking.webapps.biz.baseInfo.ParkMapBiz;
import com.chinadovey.parking.webapps.mappers.gen.ParkMapMapper;
import com.chinadovey.parking.webapps.pojo.ParkMap;
import com.chinadovey.parking.webapps.pojo.ParkMapExample;

/** 
* @author 作者  xqy
* @version 创建时间：2016年9月19日 下午2:56:51 
* 类说明 
*/
@Service
public class ParkMapBizImpl implements ParkMapBiz{
	@Autowired
	private ParkMapMapper mapper;

	@Override
	public void save(ParkMap parkMap) {
		// TODO Auto-generated method stub
		 mapper.insertSelective(parkMap);
	}

	@Override
	public ParkMap find(int id) {
		// TODO Auto-generated method stub
		return  mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<ParkMap> getAllByparkIdAndPic(Integer parkId,String pic) {
		ParkMapExample example = new ParkMapExample();
		example.createCriteria().andParkIdEqualTo(parkId).andPicEqualTo(pic);
		List<ParkMap> list = mapper.selectByExample(example);
		if (list != null && !list.isEmpty()) {
			return list;
		}
		return null;
	}
	@Override
	public ParkMap getByName(String name) {
		ParkMapExample example = new ParkMapExample();
		example.createCriteria().andNameEqualTo(name);
		List<ParkMap> list = mapper.selectByExample(example);
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}


	
}
