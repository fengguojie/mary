package com.chinadovey.parking.webapps.biz.logManage;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.chinadovey.parking.webapps.pojo.CarUser;
import com.chinadovey.parking.webapps.pojo.OperateLog;

public interface OperateLogBiz {

	/**
	 * 根据id查找车位
	 * 
	 * @author 王生栋
	 * @param id
	 *            车位的id
	 */
	public OperateLog find(int id);

	/**
	 * 保存车位
	 * 
	 * @author 王生栋
	 * @param OperateLog
	 *            车位对象
	 */
	public void save(OperateLog OperateLog);

	/**
	 * 更新车位
	 * 
	 * @author 王生栋
	 * @param OperateLog
	 *            车位对象
	 */
	public void update(OperateLog OperateLog);

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
	 * 根据条件查询数据
	 * 
	 * @param page 当前页
	 * @param rows 每页显示记录数
	 * @param search 查询的字符串
	 * @param sort 排序的字段
	 * @param order 排序顺序
	 * @param start 开始时间
	 * @param end 结束时间
	 * @return
	 */
	
	public Map<String, Object> getList(int page, int rows, String search,
			String sort, String order, String start, String end,Integer type);

	/**
	 * 根据车位锁id判断上次操作的车主，如果是管理员则返回null
	 * @param carLockId
	 * @return
	 */
	CarUser getCarUserByCarLockId(Integer carLockId);
	
	List<OperateLog> findBySpaceIdAndTime(Date startTime,Date endTime,int carSpaceId);
	
}