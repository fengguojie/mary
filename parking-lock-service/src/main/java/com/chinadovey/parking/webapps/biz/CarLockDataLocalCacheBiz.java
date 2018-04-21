package com.chinadovey.parking.webapps.biz;

import java.util.List;

import com.chinadovey.parking.webapps.pojo.CarLockDataLocalCache;

 /**
 * 本地缓存数据接口（扬州项目做的特殊处理）
 * @author wangshengdong
 *
 */
public interface CarLockDataLocalCacheBiz{
	
	/**
	 * 获取数据库中前100条数据
	 * @return
	 */
	List<CarLockDataLocalCache> getList();
	
	/**
	 * 根据ID删除
	 * @param ids
	 */
	void deleteByIds(List<Long> ids);
	
}
