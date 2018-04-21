package com.chinadovey.parking.webapps.biz.baseInfo;

import java.util.List;

import com.chinadovey.parking.webapps.pojo.CarUserRelCarNo;

public interface CarUserNoBiz {

	/**
	 * 增加一个车牌
	 * 
	 * @param carUserId
	 * @param carNo
	 */
	public void addCarNo(Integer carUserId, String carNo);

	/**
	 * 删除车牌
	 * 
	 * @param carUserId
	 * @param carNo
	 */
	public void deleteCarNo(Integer carUserId, String carNo);

	/**
	 * 判断车牌号是否存在
	 * 
	 * @param carUserId
	 * @param carNo
	 * @return
	 */
	public boolean isCarNoExit(Integer carUserId, String carNo);

	/**
	 * 根据用户id查找车牌列表
	 * 
	 * @param carUserId
	 * @return
	 */
	public List<String> getCarNosByCarUserId(Integer carUserId);
	
	/**
	 * 根据用户id查找车牌信息列表
	 * 
	 * @param carUserId
	 * @return
	 */
	public List<CarUserRelCarNo> getCarUserNosByCarUserId(Integer carUserId);
	
	/**
	 * 增加一个车牌
	 * 
	 * @param carUserId
	 * @param carNo
	 */
	public void addHistoryCarNo(Integer carUserId, String carNo);

	/**
	 * 删除车牌
	 * 
	 * @param carUserId
	 * @param carNo
	 */
	public void deleteHistoryCarNo(Integer carUserId, String carNo);

	/**
	 * 判断车牌号是否存在
	 * 
	 * @param carUserId
	 * @param carNo
	 * @return
	 */
	public boolean isHistoryCarNoExit(Integer carUserId, String carNo);

	/**
	 * 根据用户id查找历史车牌列表
	 * 
	 * @param carUserId
	 * @return
	 */
	public List<String> getHistoryCarNosByCarUserId(Integer carUserId);
	
	/**
	 * 根据id查找我的爱车信息
	 * @param id
	 * @return
	 */
	public CarUserRelCarNo find(Integer id);
	
	/**
	 * 更新我的爱车信息
	 * @param carUserRelCarNo
	 * @return
	 */
	int update(CarUserRelCarNo carUserRelCarNo);
	
}
