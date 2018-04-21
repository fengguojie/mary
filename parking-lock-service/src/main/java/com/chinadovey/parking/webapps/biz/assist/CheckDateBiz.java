package com.chinadovey.parking.webapps.biz.assist;

import java.util.Date;

public interface CheckDateBiz {

	static final Integer IN_OUT = 1;

	static final Integer NOT_APP_CHARGE = 2;

	static final Integer APP_CHARGE = 3;

	/**
	 * 根据停车场id和类型  更新最新检查时间
	 * @param parkId
	 * @param newestCheckDate
	 * @param type
	 */
	void updateNewestCheckDate(Integer parkId, Date newestCheckDate, Integer type);

	/**
	 * 根据停车场id和类型查询最新检查时间
	 * @param parkId
	 * @param type
	 * @return
	 */
	Date getNewestCheckDate(Integer parkId, Integer type);

	/**
	 * 根据停车场id和类型查询mongodb最新检查时间
	 * @param parkId
	 * @param type
	 * @return
	 */
	Date getMongoNewestCheckDate(Integer parkId, Integer type);
}
