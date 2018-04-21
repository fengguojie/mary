package com.chinadovey.parking.webapps.biz.baseInfo;

import java.util.List;
import java.util.Map;

import com.chinadovey.parking.webapps.pojo.Gateway;


public interface GatewayBiz{
	/**
	 * 获取分页数据
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
	 * 查询该网关编号是否存在
	 * wy
	 * 
	 * @param gatewayNo
	 * @return
	 */
	public int getCount(String gatewayNo);
	/**
	 * 保存添加网关的信息
	 * 
	 */
	public void save(Gateway gateway);
	/**
	 * 根据id查询对应的一条记录
	 * wy
	 * 
	 * @param id
	 * @return
	 */
	public Gateway find(Integer id);
	/**
	 * 更新数据
	 * wy
	 * 
	 */
	public void update(Gateway gateway);
	/**
	 * 删除一条记录
	 * wy
	 * 
	 * @param gateway
	 */
	public void delete(Integer id);
	/**
	 * 查询所有
	 * wy
	 * 
	 * @return
	 */
	public List<Gateway> getAll();
	
	Gateway getByDasId(String dasId);

	Map<String, Object> getList(int page, int rows, String search, String sort, String order,String companyNo);

	boolean isDasIdExitById(String dasId, int id);

	boolean isDasIdExit(String dasId);

	List<Gateway> getGateway(String companyNo);

	List<Gateway> getAll(List<String> companys);
	
	Integer countByStatus(Integer status);
	/**
	 * 查询车场所属网关信息
	 * wy
	 * 
	 * @param parkIds
	 * @return
	 */
	public List<Gateway> findByParkIds(List<Integer> parkIds);

}
