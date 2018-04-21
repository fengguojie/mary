package com.chinadovey.parking.webapps.biz.test;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.chinadovey.parking.webapps.pojo.CarLock;

public interface CarLockBiz{
	/**
	 * 根据slaveId查询车锁信息
	 * 
	 * @param slaveId
	 * @return
	 */
	CarLock getBySlaveid(String slaveId);

	boolean isSlaveIdExit(String slaveId);

	boolean isSlaveIdExitById(String slaveId, int id);

	Map<String, Object> getList(int page, int rows, String search, String sort, String order,Integer isauto,String companyNo);
    
	List<CarLock> getAll();
       
	CarLock update(String slaveId);

	Integer getAll(List<String> dasIds);
	
	Integer countByStatus(Integer status);
	
	Integer countByCarStatus(Integer status);

	List<CarLock> getAll(Integer status);
	
	List<CarLock> getAllByIds(List<Integer> ids);
	
	List<CarLock> getAllBySlaveIds(List<String> ids);

	Integer countBySwitchStatus(int status);
	/**
	 * 更新数据
	 * wy
	 * 
	 * @param carLock
	 */
	public void update(CarLock carLock);
	/**
	 * 查询该车场对应的所有未绑定的车锁
	 * 
	 * @param parkId
	 * @param isBind
	 * @return
	 */
	public List<CarLock> findByParkId(int parkId, int isBind);
	/**
	 * 查找所有未绑定的车锁
	 * 
	 * @param isBind
	 * @return
	 */
	public List<CarLock> findAllNOBind(int isBind);
	/**
	 * 列表分页
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
	 * 根据parkIds查询车场对应的所有车位锁
	 * wy
	 * 
	 * @param parkIds
	 * @return
	 */
	public List<CarLock> findByParkIds(List<Integer> parkIds);
	/**
	 * 查询车场所属的网关信息
	 * wy
	 * 
	 * @param parkId
	 * @return
	 */
	public List<CarLock> findByParkId(Integer parkId);
	/**
	 * save
	 * wy
	 * 
	 * @param carLock
	 */
	public void save(CarLock carLock);
	/**
	 * 删除
	 * wy
	 * 
	 * @param id
	 */
	public void delete(Integer id);
	/**
	 * 查询一条记录
	 * wy
	 * 
	 * @param id
	 * @return
	 */
	public CarLock find(Integer id);

	List<String> getOfflineLock(HashSet<String> slaveIds);
	
}
