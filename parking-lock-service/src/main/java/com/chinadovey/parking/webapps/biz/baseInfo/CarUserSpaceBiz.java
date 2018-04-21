package com.chinadovey.parking.webapps.biz.baseInfo;

import java.util.List;

import com.chinadovey.parking.webapps.pojo.CarUser;
import com.chinadovey.parking.webapps.pojo.CarUserRelCarSpace;

/**
 * 车主车位biz
 * 
 * @author Administrator
 *
 */
public interface CarUserSpaceBiz {

	/**
	 * 根据用户id查找车位列表
	 * 
	 * @param carUserId
	 * @return
	 */
	public List<CarUserRelCarSpace> getCarUserSpaceByCarUserId(Integer carUserId);

	/**
	 * 根据用户id查找车位列表
	 * 
	 * @param carUserId
	 * @return
	 */
//	public List<CarUserSpace> getCarUserSpaceByCarUserId(Integer page, Integer rows, Integer carUserId);

	/**
	 * 根据id查找
	 * 
	 * @param id
	 * @return
	 */
	CarUserRelCarSpace find(Integer id);
	
	public void save(CarUserRelCarSpace rel);
}
