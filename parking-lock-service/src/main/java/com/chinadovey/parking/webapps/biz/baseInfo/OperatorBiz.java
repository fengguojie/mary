package com.chinadovey.parking.webapps.biz.baseInfo;

import java.util.List;

import com.chinadovey.parking.webapps.pojo.Operator;

public interface OperatorBiz {
	
	/**
	 * 根据id查找操作人
	 * @author 匿名用户
	 * @param id 操作人的id
	 */
	public Operator find(String id);
	
	/**
	 * 保存操作人
	 * @author 匿名用户
	 * @param operator 操作人对象
	 */
	public void save(Operator operator);
	
	/**
	 * 更新操作人
	 * @author 匿名用户
	 * @param operator 操作人对象
	 */
	public void update(Operator operator);
	
	/**
	 * 删除操作人
	 * @author 匿名用户
	 * @param id 操作人id
	 */
	public void delete(String id);
	
	/**
	 * 判断是否存在该操作人
	 * @param operator
	 * @param operatorId
	 * @return
	 */
	boolean isExit(String operator, String operatorId, Integer parkId);
	
	/**
	 * 查询所有操作人
	 * @author 匿名用户
	 * @param parkId
	 * @return
	 */
	List<Operator> getAllByParkId(Integer parkId);
	
	/**
	 * 根据停车场id拷贝数据
	 * @param parkId
	 * @return
	 */
	int backupMongodbToMysql(Integer parkId);

	boolean isExitId(String id);
}