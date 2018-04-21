package com.chinadovey.parking.webapps.biz.baseInfo;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.chinadovey.parking.webapps.pojo.CarUser;
import com.chinadovey.parking.webapps.pojo.CarUserRelCarNo;
import com.chinadovey.parking.webapps.pojo.base.CarUserCountBase;

public interface CarUserBiz {

	/**
	 * 根据id查找车位
	 * 
	 * @author 王生栋
	 * @param id
	 *            车位的id
	 */
	public CarUser find(int id);
 
	
	/**
	 * 保存车位
	 * 
	 * @author 王生栋
	 * @param CarUser
	 *            车位对象
	 */
	public CarUser save(CarUser carUser);

	/**
	 * 保存车位
	 * 
	 * @author 王生栋
	 * @param CarUser
	 *            车位对象
	 */
	public void save(CarUser carUser, Integer[] carSpaceIds);

	/**
	 * 更新车位
	 * 
	 * @author 王生栋
	 * @param CarUser
	 *            车位对象
	 * @return 车主
	 */
	public void update(CarUser carUser);

	/**
	 * 更新车位
	 * 
	 * @author 王生栋
	 * @param CarUser
	 *            车位对象
	 */
	public void update(CarUser carUser, Integer[] carSpaceIds);

	/**
	 * 删除车位
	 * 
	 * @author 王生栋
	 * @param id
	 *            车位id
	 */
	public void delete(int id);

	/**
	 * 批量删除车位
	 * 
	 * @author 王生栋
	 * @param ids
	 */
	public void batchDelete(int[] ids);

	/**
	 * 根据微信查找用户
	 * 
	 * @author 王生栋
	 * @return
	 */
	public CarUser getByWechat(String wechat);

	/**
	 * 根据openid查找用户信息
	 * 
	 * @param openid
	 * @return
	 */
	public CarUser getByOpenid(String openid);

	/**
	 * 根据手机查找用户
	 * 
	 * @author 王生栋
	 * @return
	 */
	public CarUser getByMobile(String mobile);

	/**
	 * 根据车牌号查找用户
	 * 
	 * @param plateNumber
	 * @return
	 */
	public CarUser getByPlateNumber(String plateNumber);

	/**
	 * 根据车牌号查找用户列表
	 * @param plateNumber
	 * @return
	 */
	public List<CarUser> getListByPlateNumber(String plateNumber);
	
	/**
	 * 判断手机号是否存在
	 * 
	 * @author 王生栋
	 * @param mobile
	 * @return
	 */
	boolean isMobileExit(String mobile);

	/**
	 * 根据id判断手机号是否存在，若不存在,则根据手机号判断
	 * 
	 * @param mobile
	 * @param id
	 * @return
	 */
	boolean isMobileExitById(String mobile, int id);

	/**
	 * 判断微信号是否存在
	 * 
	 * @param wechat
	 * @return
	 */
	boolean isWechatExit(String wechat);

	/**
	 * 根据id判断微信号是否存在，若不存在,则根据微信号判断
	 * 
	 * @param wechat
	 * @param id
	 * @return
	 */
	boolean isWechatExitById(String wechat, int id);

	/**
	 * 判断车牌号码是否存在
	 * 
	 * @param plateNumber
	 * @return
	 */
	boolean isPlateNumberExit(String plateNumber);

	/**
	 * 根据id判断车牌号是否存在，若不存在,则根据车牌号判断
	 * 
	 * @param plateNumber
	 * @param id
	 * @return
	 */
	boolean isPlateNumberExitById(String plateNumeber, int id);

	/**
	 * 分页查询
	 * 
	 * @author 王生栋
	 * @param page
	 *            当前页
	 * @param rows
	 *            每页显示记录数
	 * @param CarUserId
	 *            车位id
	 * @return
	 */
	Map<String, Object> getList(int page, int rows, List<Integer> parkId);

	/**
	 * 分页查询
	 * 
	 * @author 张晨刚
	 * @param page
	 *            当前页
	 * @param rows
	 *            每页显示记录数
	 * @return
	 */
	Map<String, Object> getList(int page, int rows);

	/**
	 * 分页查询
	 * 
	 * @param page
	 *            当前页
	 * @param rows
	 *            每页显示记录数
	 * @param search
	 *            模糊查询使用的关键字
	 * @param parkId
	 *            停车场id
	 * @return
	 */
	Map<String, Object> getList(int page, int rows, String search, List<Integer> parkId);

	/**
	 * 分页查询
	 * 
	 * @param page
	 *            当前页
	 * @param rows
	 *            每页显示记录数
	 * @param search
	 *            模糊查询使用的关键字
	 * @return
	 */
	Map<String, Object> getList(int page, int rows, String search);

	/**
	 * 分页查询
	 * 
	 * @param page
	 *            当前页
	 * @param rows
	 *            每页显示记录数
	 * @param search
	 *            模糊查询使用的关键字
	 * @param sort
	 *            排序的字段
	 * @param order
	 *            排序的顺序
	 * @param parkId
	 *            停车场id
	 * @return
	 */
	Map<String, Object> getList(int page, int rows, String sort, String order, String search, List<Integer> parkId);

	/**
	 * 分页查询
	 * 
	 * @param page
	 *            当前页
	 * @param rows
	 *            每页显示记录数
	 * @param search
	 *            模糊查询使用的关键字
	 * @param sort
	 *            排序的字段
	 * @param order
	 *            排序的顺序
	 * @return
	 */
	public Map<String, Object> getList(int page, int rows, String sort, String order, String search);

	/**
	 * 增加一个车牌
	 * 
	 * @param carUserId
	 * @param carNo
	 */
	public Integer addCarNo(Integer carUserId, String carNo);

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
	 * 根据用户id查找车牌列表
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
	 * 根据用户id查找车牌列表
	 * 
	 * @param carUserId
	 * @return
	 */
	public List<String> getHistoryCarNosByCarUserId(Integer carUserId);
	
	/**
	 * 保存用户
	 * @param openid
	 * @param wechatId 
	 */
	void saveUser(String openid,Integer wechatId);
	
//	ExCarUser getByMobileRes(String mobile);
	
	Integer saveCarNo(CarUserRelCarNo carUserRelCarNo);
	
	Map<String, Object> getNoList(int page, int rows,int carUserId);
	
	void updateNo(CarUserRelCarNo carUserRelCarNo);
	
	CarUserRelCarNo getBindNo(Integer carUserId,String carNo);

	public List<CarUserCountBase> getCountByDay(Date startDate, Date endDate);
	
	public List<CarUserCountBase> getCountByDay(Date startDate, Date endDate,Integer source);
	
	public CarUser getByPlateNumber(String plateNumber,Integer parkId);
	
	public Integer getCountByParkId(List<Integer> parkIds);


	int getUserbyMonth(Date startDate, Date endDate, List<Integer> type);


	int getCarUserbyMonth(Date startDate, Date endDate, List<Integer> type);

}