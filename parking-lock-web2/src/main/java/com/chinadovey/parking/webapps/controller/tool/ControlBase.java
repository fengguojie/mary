package com.chinadovey.parking.webapps.controller.tool;

/**
 * 车锁管控业务层（包括网关、车锁的配置以及控制）
 * 请不要对这里代码进行增删改操作，谢谢！
 * @author feng
 *
 */
public interface ControlBase{
	;
	/**
	 * 车位锁控制1 表示开，2 表示关
	 * @param slaveId  车锁编号
	 * @param acton  指令
	 * @return
	 */
	public Integer operate(String slaveId,int action);
	/**
	 * 车位锁配置3表示自动，4表示手动
	 * @param slaveId  车锁编号
	 * @param acton  指令
	 * @return
	 */
	public Integer autoOrHand(String slaveId,int action);
	
	/**
	 * 车锁-网关关系的配置
	 * @param dasId    网关编号
	 * @param slaveId  车锁编号
	 * @param serial   串口编号
	 * @return
	 */
	public Integer carlockConfig(String dasId,String slaveId,String serial);
	/**
	 * 车锁-网关关系的配置的删除
	 * @param dasId    网关编号
	 * @param slaveId  车锁编号
	 * @return
	 */
	public Integer carlockDelConfig(String dasId,String slaveId);
	
	
	

	
}
