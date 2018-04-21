package com.chinadovey.parking.webapps.biz.statistics;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.chinadovey.parking.webapps.pojo.CarInOutRecord;

public interface CarInOutRecordBiz {

	Map<String, Object> getList(int page, int rows, String sort, String order, String search, String start, String end,
			Integer cardType);

	Map<String, Object> getList(int page, int rows, String sort, String order, List<Integer> parkIds, String search,
			String start, String end, Integer cardType);

	CarInOutRecord find(String id);

	Map<String, Object> getList(int page, int rows, Integer parkId, String search, String start, String end);

	Map<String, Object> getList(Integer page, Integer rows, Integer parkId, String search, String start, String end,
			Integer timeType);

	Map<String, Object> getList(int page, int rows, String sort, String order, List<Integer> parkIds, String search,
			String start, String end, Integer timeType, Integer cardType);

	/**
	 * 根据最新时间查询1000条记录
	 * 
	 * @param newestCarInOutRecords
	 * @param parkId
	 * @return
	 */
	List<CarInOutRecord> getCarInOutRecordByInTime(CarInOutRecord newestCarInOutRecord, Integer parkId);

	/**
	 * 根据开始时间取得最新记录
	 * 
	 * @param parkId
	 * @return
	 */
	public CarInOutRecord getNewestCarInOutRecordByInTime(Integer parkId);

	/**
	 * 根据结束时间取得最新记录
	 * 
	 * @param parkId
	 * @return
	 */
	public CarInOutRecord getNewestCarInOutRecordByOutTime(Integer parkId);

	/**
	 * 插入车辆进出记录
	 * 
	 * @param carInOutRecords
	 */
	void insertCarInOutRecords(List<CarInOutRecord> carInOutRecords);

	/**
	 * 根据最新入场、出场时间获得出入记录
	 * 
	 * @param parkId
	 * @return
	 */
	List<CarInOutRecord> getCarInOutRecordsByOutTime(Date newestCarOutRecord, Integer parkId);

	/**
	 * 检查记录并更新出入记录
	 * 
	 * @param carInOutRecords
	 * @param isExistRecord
	 */
	public void updateCarInOutRecords(List<CarInOutRecord> carInOutRecords);

	/**
	 * 判断是否存在该记录
	 * 
	 * @param carInOutRecord
	 * @return
	 */
	public boolean isExist(CarInOutRecord carInOutRecord);

	/**
	 * 得到未出场记录
	 * 
	 * @param parkId
	 * @return
	 */
	List<CarInOutRecord> getNoOutCarInOutRecord(Integer parkId);

	/**
	 * 更新未出场记录列表
	 * 
	 * @param carInOutRecords
	 */
	public void updateNotOutCarInOutRecords(List<CarInOutRecord> carInOutRecords);

	/**
	 * 查找mongodb中该记录是否存在
	 * 
	 * @param id
	 * @return
	 */
	public CarInOutRecord findByMongodb(String id);

	/**
	 * 根据验证时间来获得出场记录
	 * 
	 * @param newestCheckDate
	 * @param mongoNewestCheckDate
	 * @param parkId
	 * @return
	 */
	List<CarInOutRecord> getCarInOutRecordsByOutTime(Date newestCheckDate, Date mongoNewestCheckDate, Integer parkId);

	/**
	 * 验证每条出场记录并返回最新时间
	 * 
	 * @param carInOutRecords
	 * @return
	 */
	Date checkCarInOutRecords(List<CarInOutRecord> carInOutRecords, Date newestDate);

	CarInOutRecord findByCarNoAndOutTimeNull(String carNO);
	
	CarInOutRecord findByParam(Integer parkId, Date inTime, String carNo);

	List<CarInOutRecord> getList(int page, Integer rows, List<Integer> parkIds, Date startTime, Date endTime);

	/**
	 * 得到卡类型
	 * @param cardType
	 * @return
	 */
	String getCardTypeStr(Integer cardType);

	/**
	 * 统计出场记录
	 * @param startDate
	 * @param endDate
	 * @param parkIds
	 * @return
	 */
	Integer countOutRecord(Date startDate, Date endDate, List<Integer> parkIds);

	/**
	 * 统计停车时长
	 * @param startDate
	 * @param endDate
	 * @param parkIds
	 * @return
	 */
	Integer countParkingSpan(Date startDate, Date endDate, List<Integer> parkIds);
	
	Integer countOutRecord(Date startDate, Date endDate, Integer parkId);
	
	Integer countParkingSpan(Date startDate, Date endDate, Integer parkId);
	
	
    boolean isExist(String carNo , Date inTime , int parkId);
	
	CarInOutRecord find(String carNo , Date inTime , int parkId);
	
	void save(CarInOutRecord carInOut);
	
	void update(CarInOutRecord carInOut);
	
	void saveAndUpdateStatistics(CarInOutRecord carInOut);
	
	void updateAndUpdateStatistics(List<CarInOutRecord> carInOutRecords);
	
	List<CarInOutRecord> getListByInTime( Date startTime, Date endTime);
	
	public List<CarInOutRecord> getListByOutTime(Date startTime, Date endTime);
	
	public Integer countInRecord(Date startDate, Date endDate, Integer parkId);
	
	public CarInOutRecord getLastOne(Integer parkId);
	
	public List<CarInOutRecord> getListByInTimeAndParkId(Date startTime, Date endTime,int parkId);
	CarInOutRecord find(CarInOutRecord carInOutRecord);
	CarInOutRecord delect(CarInOutRecord carInOutCharge);
}
