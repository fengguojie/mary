package com.chinadovey.parking.webapps.biz.baseInfo;

import java.util.List;
import java.util.Map;

import com.chinadovey.parking.webapps.pojo.Company;
import com.chinadovey.parking.webapps.pojo.Park;

/**
 * 公司biz
 * @author Administrator
 *
 */
public interface CompanyBiz {

	/**
	 * 保存公司
	 * @param company
	 */
	void save(Company company);

	/**
	 * 保存公司
	 * @param name
	 * @param addrss
	 * @param remark
	 */
	void save(String name, String addrss, String remark);

	/**
	 * 删除公司
	 * @param id
	 */
	void delete(Integer id);

	/**
	 * 根据公司id
	 * @param id
	 * @return
	 */
	Company find(Integer id);


	/**
	 * 更新公司
	 * @param company
	 */
	void update(Company company);
	
	/**
	 * 根据公司id获取停车场id列表
	 * @param id
	 * @return
	 */
	List<Integer> getParkIds(Integer id);
	
	/**
	 * 根据公司id获取停车场列表
	 * @param id
	 * @return
	 */
	List<Park> getParks(Integer id);

	/**
	 * 根据公司id查询微信配置id
	 * @param companyId
	 * @return
	 */

	/**
	 * 根据公司id查询微信配置id
	 * @param companyId
	 * @return
	 */
	Integer findServiceProviderId(Integer companyId);
	
	Map<String, Object> getList(int page, int rows, String search, String sort, String order);

	/**
	 * 查找所有公司
	 * @return
	 */
	List<Company> getList();
	
	/**
	 * 查找所有公司
	 * @return
	 */
	List<Company> getAll();
	
	/**
	 * 停车场ids
	 * @param parkIds
	 * @return
	 */
	List<Integer> getCompanyIdsByParkIds(List<Integer> parkIds);

	/**
	 * 得到所有的公司
	 * @param companyIds
	 * @return
	 */
	List<Company> getAll(List<Integer> companyIds);

	/**
	 * 根据停车场id查找 
	 * @param parkId
	 * @return
	 */
	Company findByParkId(Integer parkId);
	
	/**
	 * 根据停车场id查找机构id
	 * @param parkId
	 * @return
	 */
	Integer findIdByParkId(Integer parkId);
	
	Company findByCompanyNo(String companyNo);
	/**
	 * 检查机构名称是否已存在
	 * @param name
	 * @return
	 */
	boolean checkName(String name);

	boolean checkNameById(int id,String name);

}
