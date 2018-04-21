package com.chinadovey.parking.webapps.biz.system;

import java.util.Map;

import com.chinadovey.parking.webapps.pojo.Code;

public interface CodeBiz {

	/**
	 * 插入code
	 * 
	 * @param code
	 * @return
	 */
	int insert(Code code);

	/**
	 * 分页查询列表
	 * 
	 * @param rows
	 * @param page
	 * @return
	 */
	Map<String, Object> getList(int rows, int page, String search, String sort, String order);

	/**
	 * 根据id查询code
	 * 
	 * @param id
	 * @return
	 */
	Code find(int id);

	/**
	 * 根据key得到value
	 * 
	 * @param key
	 * @return
	 */
	String getValueByKey(String key);

	/**
	 * 根据key得到code对象
	 * 
	 * @param key
	 * @return
	 */
	Code getCodeByKey(String key);

	/**
	 * 删除code
	 * 
	 * @param id
	 * @return
	 */
	int delete(int id);

	/**
	 * 根据code修改value
	 * 
	 * @param code
	 * @param value
	 * @return
	 */
	int updateValueByCode(String code, String value);

	/**
	 * 根据code,parkId修改value
	 * 
	 * @param code
	 * @param value
	 * @param parkId
	 * @return
	 */
	int updateValueByCode(String code, String value, Integer parkId);

	/**
	 * 根据key得到value
	 * 
	 * @param key
	 * @param parkId
	 * @return
	 */
	String getValueByKey(String key, Integer parkId);

	/**
	 * 根据key得到Code
	 * 
	 * @param key
	 * @param parkId
	 * @return
	 */
	Code getCodeByKey(String key, Integer parkId);
}
