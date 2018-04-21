package com.chinadovey.parking.webapps.biz.baseInfo;

import java.util.List;

import com.chinadovey.parking.webapps.pojo.ParkMap;

public interface ParkMapBiz {
	
	public void save(ParkMap parkMap);
	
	public ParkMap find(int id);

	List<ParkMap> getAllByparkIdAndPic(Integer parkId,String pic);
	
	ParkMap getByName(String name);
}