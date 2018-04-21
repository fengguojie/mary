package com.chinadovey.parking.webapps.biz.assist;

import java.util.Date;

/**
 * mongo文件biz
 * @author Administrator
 *
 */
public interface MongoFileBiz {

	/**
	 * 获得进场图片
	 * @param carNo 车牌号
	 * @param parkId 停车场id
	 * @param inTime 进场时间
	 * @param flag 0 in 1 out
	 * @return
	 */
	String getInOutPicture(String carNo, Integer parkId, Date inTime,int flag);
	
	/**
	 * 获取图片
	 * @param parkId
	 * @param time
	 * @param flag 1
	 * @return
	 */
	String getPicture(Integer parkId,Date time,int flag);
	
	/**
	 * 根据类型清除mongodb图片
	 * @author 王生栋
	 * @param type
	 */
	void removePicture(int type);
}
