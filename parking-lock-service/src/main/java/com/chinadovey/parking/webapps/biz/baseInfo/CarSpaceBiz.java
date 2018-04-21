package com.chinadovey.parking.webapps.biz.baseInfo;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.chinadovey.parking.webapps.pojo.CarSpace;

public interface CarSpaceBiz {
	
	/**
	 * 根据id查找车位
	 * @author 王生栋
	 * @param id 车位的id
	 */
	public CarSpace find(int id);
	
	/**
	 * 根据车位锁id查找车位
	 * @param carLockId
	 * @return
	 */
	public CarSpace findByCarLockId(int carLockId);
	
	/**
	 * 保存车位
	 * @author 王生栋
	 * @param CarSpace 车位对象
	 */
	public void save(CarSpace carSpace);
	
	
	public int saveSpace(CarSpace carSpace);
	/**
	 * 更新车位
	 * @author 王生栋
	 * @param CarSpace 车位对象
	 */
	public void update(CarSpace carSpace);
	
	/**
	 * 删除车位
	 * @author 王生栋
	 * @param id 车位id
	 */
	public void delete(int id);
	
	/**
	 * 批量删除车位
	 * @author 王生栋
	 * @param ids
	 */
	public void batchDelete(int[] ids);
	
	/**
	 * 判断编码是否存在
	 * @param code
	 * @return
	 * @auther 王生栋
	 */
	boolean isCodeExit(String spaceNo);
	
	/**
	 * 判断除自身外编码是否存在
	 * @param code
	 * @return
	 * @auther 王生栋
	 */
	boolean isCodeExitExcept(String code , String orCode);
	
	
	/**
	 * 查询所有车位
	 * @author 王生栋
	 * @param parkId
	 * @return
	 */
	List<CarSpace> getAll(List<Integer> parkId);
	/**
	 * 防止重复绑定 
	 * @param parkId
	 * @return
	 */
	List<CarSpace> getAllNoUsed(List<Integer> parkId);
	List<CarSpace> getAllNoUsed(Integer parkId);
	
	/**
	 * 查询所有车位
	 * @param parkId
	 * @return
	 */
	List<CarSpace> getAll(Integer parkId);
	
	/**
	 * 根据用户id查询
	 * @author 王生栋
	 * @return
	 */
	List<CarSpace> getByUserId(int userId);
	
	/**
	 * 根据微信号查找车位
	 * @author 王生栋
	 * @param wechat
	 * @return
	 */
	List<CarSpace> getByWechat(String wechat);
	
	/**
	 * 根据手机号查找车位
	 * @author 王生栋
	 * @param mobile
	 * @return
	 */
	List<CarSpace> getByMobile(String mobile);
	
	/**
	 * 根据手机号获取单个车位信息
	 * @author 王生栋
	 * @param mobile 手机号
	 * @return
	 */
	CarSpace getSingleByMobile(String mobile);
	
	/**
	 * 查询所有的数量
	 * @author 王生栋
	 * @param parkId
	 * @return
	 */
	Integer getAllCount();
	
	/**
	 * 查询所有的数量
	 * @param parkId
	 * @return
	 */
	Integer getAllCount(List<Integer> parkId);

	/**
	 * 根据集合中的parkId获取车位列表
	 * @param parkIds
	 * @return
	 */
	List<CarSpace> getAll(Set<Integer> parkIds);
	
	/**
	 * 根据id判断编号是否存在，若不存在,则根据编号判断
	 * @param code
	 * @param id
	 * @return
	 */
	boolean isCodeExitById(String spaceNo, int id);
	
	/**
	 * 分页查询
	 * @author 王生栋
	 * @param page 当前页
	 * @param rows 每页显示记录数
	 * @param CarSpaceId 车位id
	 * @return
	 */
	Map<String , Object> getList(int page , int rows , List<Integer> parkId);
	
	/**
	 * 分页查询
	 * @author 张晨刚
	 * @param page 当前页
	 * @param rows 每页显示记录数
	 * @return
	 */
	Map<String , Object> getList(int page , int rows);
	
	/**
	 * 分页查询
	 * @param page 当前页
	 * @param rows 每页显示记录数
	 * @param search 模糊查询使用的关键字
	 * @param parkId 停车场id
	 * @return
	 */
	Map<String, Object> getList(int page, int rows, String search,
			List<Integer> parkId);
	/**
	 * 分页查询
	 * @param page 当前页
	 * @param rows 每页显示记录数
	 * @param search 模糊查询使用的关键字
	 * @return
	 */
	Map<String, Object> getList(int page, int rows, String search);

	/**
	 * 分页查询
	 * @param page 当前页
	 * @param rows 每页显示记录数
	 * @param search 模糊查询使用的关键字
	 * @param sort 排序的字段
	 * @param order 排序的顺序
	 * @param parkId 停车场id
	 * @return
	 */
	Map<String, Object> getList(int page, int rows, String search, String sort,
			String order, List<Integer> parkId);
	/**
	 * 分页查询
	 * 
	 * @param page
	 * @param rows
	 * @param search
	 * @param sort
	 * @param order
	 * @param parkId
	 * @param name 输入的车位号
	 * @return
	 */
	Map<String, Object> getList(int page, int rows, String search, String sort,
			String order, List<Integer> parkId, String name);

	/**
	 * 分页查询
	 * @param page 当前页
	 * @param rows 每页显示记录数
	 * @param search 模糊查询使用的关键字
	 * @param sort 排序的字段
	 * @param order 排序的顺序
	 * @return
	 */
	public Map<String, Object> getList(int page, int rows, String search,
			String sort, String order);

	/**
	 * 根据当前时间设置车位是否为空
	 */
	public void updateEmpty(Date startDateTime, Date endDateTime);
	
	/**
	 * 
	 * @author 王生栋
	 * @param lockId
	 * @return
	 */
	public CarSpace getByCarLockId(String lockId);

	/**
	 * 根据用户id得到车位数
	 * @param userId
	 * @return
	 */
	public int count(Integer userId);
	
	public List<CarSpace> getAll();
	CarSpace findBySacode(String code,String slaveId);
	public  CarSpace getLockId(String code,Integer parkId);
	
	List<CarSpace> getAllByCode(List<String> codes);
	
	CarSpace findBySlaveId(String id);
	
	List<CarSpace> getByStatus(int status);
	
	CarSpace findByCode(String code);
	void update(CarSpace space,String code,String slaveId);
	List<CarSpace> findSlaveIdsByUserId(int id);
	/**
	 * 
	 * 根据parkId等到slaveId
	*/
	public List<CarSpace> findSlaveIdsByParkId(Integer parkId);
	public CarSpace totalCountOfSpace(String slaveId);
	
	public List<CarSpace> findByParkId(Integer parkId);

	public Integer countByStatus(int status);
	/**
	 * 分页查询
	 * wy
	 * 
	 * @param parkIds
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @param search
	 * @return
	 */
	public Map<String, Object> getList(List<Integer> parkIds, int page, int rows, String sort, String order, String search);
	/**
	 * 查询尚未绑定车锁的车位信息
	 * wy
	 * 
	 * @param parkIds
	 * @param slaveId
	 * @return
	 */
	public List<CarSpace> findNOBindByParkIds(List<Integer> parkIds, String slaveId);
	/**
	 * 查询车场所有的车位信息
	 * 
	 * @param parkIds
	 * @return
	 */
	public List<CarSpace> findAll(List<Integer> parkIds);

	public Map<String, Object> getDetailData(int page, int rows, String search,
			String sort, String order);
	/**
	 * 根据车位名称查询车位信息
	 * wy
	 * 
	 * @param spaceName
	 * @return
	 */
	public CarSpace findBySpaceName(String spaceName);
}