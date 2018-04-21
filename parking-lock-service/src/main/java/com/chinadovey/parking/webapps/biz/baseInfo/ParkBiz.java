package com.chinadovey.parking.webapps.biz.baseInfo;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.chinadovey.parking.webapps.pojo.CarSpace;
import com.chinadovey.parking.webapps.pojo.Park;
import com.chinadovey.parking.webapps.pojo.base.ParkChargingRulesSubBase;
import com.chinadovey.parking.webapps.pojo.base.ParkFilterBase;
import com.chinadovey.parking.webapps.pojo.base.ParkSubBase;

public interface ParkBiz {

	/**
	 * 根据id查找停车场
	 * 
	 * @param id
	 * @return
	 */
	Park find(int id);
	/**
	 * 根据停车场名称查找
	 * @param name
	 * @return
	 */
    Park findbyparkname(String name);
	/**
	 * 根据id查找停车场
	 * 
	 * @author 王生栋
	 * @param id
	 *            停车场的id
	 */
	public Park findOfPrice(int id);

	/**
	 * 根据id查找停车场
	 * 
	 * @param id
	 */
	public ParkChargingRulesSubBase findOfChargingRules(Integer id);

	/**
	 * 根据id查找用于统计信息使用的停车场
	 * 
	 * @param id
	 *            停车场的id
	 */
	public Park findOfStatistics(int id);

	/**
	 * 保存停车场
	 * 
	 * @author 王生栋
	 * @param park
	 *            停车场对象
	 */
	public void save(Park park);

	public int saveBack(Park park);

	/**
	 * 更新停车场
	 * 
	 * @author 王生栋
	 * @param park
	 *            停车场对象
	 */
	public void update(Park park);

	/**
	 * 删除停车场
	 * 
	 * @author 王生栋
	 * @param id
	 *            停车场id
	 */
	public void delete(int id);

	/**
	 * 批量删除停车场
	 * 
	 * @author 王生栋
	 * @param ids
	 */
	public void batchDelete(int[] ids);

	/**
	 * 判断编码是否存在
	 * 
	 * @param code
	 * @return
	 * @auther 王生栋
	 */
	boolean isCodeExit(String code);

	/**
	 * 判断除自身外编码是否存在
	 * 
	 * @param code
	 * @return
	 * @auther 王生栋
	 */
	boolean isCodeExitExcept(String code, String orCode);

	/**
	 * 根据用户id查找
	 * 
	 * @author 王生栋
	 * @param id
	 * @return
	 */
	List<Park> getAllByUserId(int userId);

	/**
	 * 根据用户id得到停车场id列表
	 * 
	 * @param id
	 * @return
	 */
	List<Integer> getParkIdsByUserId(int userId);

	/**
	 * 分页根据用户id得到停车场id列表
	 * 
	 * @param id
	 * @param page
	 * @param rows
	 * @return
	 */
	List<Integer> getParkIdsByUserId(Integer userId, Integer page, Integer rows);

	/**
	 * 得到停车场总数
	 * 
	 * @param id
	 * @return
	 */
	public int getCount(int userId);

	/**
	 * 获取所有id列表
	 * 
	 * @author 王生栋
	 * @return
	 */
	List<Integer> getAllIds();

	/**
	 * 获取所有
	 * 
	 * @author 王生栋
	 * @return
	 */
	List<Park> getAll();

	/**
	 * 根据id获取
	 * 
	 * @param parkId
	 * @return
	 */
	List<Park> getAll(List<Integer> parkIds);

	/**
	 * 查询中心经纬度周边的所有停车场 按照圆形查询
	 * 
	 * @param latitudeDouble
	 * @param longtudeDouble
	 * @param meterInt
	 * @return
	 */
	List<Park> getAllByCoordinates(Double latitudeDouble, Double longitudeDouble, Integer meterInt);

	List<Park> getAllByCoordinates(Double latitudeDouble, Double longitudeDouble, Integer meterInt, Integer page,
			Integer rows);

	/**
	 * 根据西北角坐标和东南角坐标和停车场列表查询停车场
	 * 
	 * @param leftLatitudeDouble
	 * @param leftLongitudeDouble
	 * @param rightLatitudeDouble
	 * @param rightLongitudeDouble
	 * @return
	 */
	List<Park> getAllByCoordinates(Double leftLatitudeDouble, Double leftLongitudeDouble, Double rightLatitudeDouble,
			Double rightLongitudeDouble);

	/**
	 * 根据西北角坐标和东南角坐标和停车场列表查询停车场
	 * 
	 * @param leftLatitudeDouble
	 * @param leftLongitudeDouble
	 * @param rightLatitudeDouble
	 * @param rightLongitudeDouble
	 * @param parkIds
	 * @return
	 */
	List<Park> getAllByCoordinates(Double leftLatitudeDouble, Double leftLongitudeDouble, Double rightLatitudeDouble,
			Double rightLongitudeDouble, List<Integer> parkIds, Integer type);

	/**
	 * 根据距离和中心经纬度坐标查询周边停车场
	 * 
	 * @param latitude
	 * @param longitude
	 * @param meter
	 * @return
	 */
	List<Park> getAllByDistance(Double latitude, Double longitude, Integer meter, Integer flag);
    
	/**
	 * 根据停车场列表具体和中心经纬度坐标查询周边停车场
	 * 
	 * @param latitude
	 * @param longitude
	 * @param meter
	 * @param flag
	 * @param parkIds
	 * @return
	 */
	List<Park> getAllByDistance(Double latitude, Double longitude, Integer meter, Integer flag, List<Integer> parkIds);

	/**
	 * 根据停车场列表具体和中心经纬度坐标查询周边停车场
	 * 
	 * @param latitude
	 * @param longitude
	 * @param meter
	 * @param flag
	 * @param parkIds
	 * @return
	 */
	List<Park> getAllByDistance(Double latitude, Double longitude, Integer meter, Integer flag, List<Integer> parkIds,
			Integer type);

	/**
	 * 根据id判断编号是否存在，若不存在,则根据编号判断
	 * 
	 * @param code
	 * @param id
	 * @return
	 */
	boolean isCodeExitById(String code, int id);

	/**
	 * 根据当前位置查询最近的停车场
	 * 
	 * @return
	 */
	Park getNeighborhoodPark(Double latitudeDouble, Double longitudeDouble);

	/**
	 * 分页查询
	 * 
	 * @author 王生栋
	 * @param page
	 *            当前页
	 * @param rows
	 *            每页显示记录数
	 * @return
	 */
	Map<String, Object> getList(int page, int rows);

	/**
	 * 分页查询
	 * 
	 * @author 王生栋
	 * @param page
	 *            当前页
	 * @param rows
	 *            每页显示记录数
	 * @param ids
	 *            停车场id
	 * @return
	 */
	Map<String, Object> getList(int page, int rows, List<Integer> ids);

	/**
	 * 分页查询
	 * 
	 * @param page
	 *            当前页
	 * @param rows
	 *            每页显示记录数
	 * @param search
	 *            模糊查询使用的关键字
	 * @return
	 */
	Map<String, Object> getList(int page, int rows, String search);

	/**
	 * 分页查询
	 * 
	 * @param page
	 *            当前页
	 * @param rows
	 *            每页显示记录数
	 * @param search
	 *            模糊查询使用的关键字
	 * @param sort
	 *            排序的字段
	 * @param order
	 *            排序的顺序
	 * @return
	 */
	Map<String, Object> getList(int page, int rows, String sort, String order, String search);

	/**
	 * 分页查询
	 * 
	 * @param page
	 *            当前页
	 * @param rows
	 *            每页显示记录数
	 * @param search
	 *            模糊查询使用的关键字
	 * @param sort
	 *            排序的字段
	 * @param order
	 *            排序的顺序
	 * @param parkId
	 *            停车场id
	 * @return
	 */
	Map<String, Object> getList(int page, int rows, String sort, String order, String search, List<Integer> parkId);

	/**
	 * 根据停车场id获得空车位
	 * 
	 * @param i
	 * @return
	 */
	public List<CarSpace> getCarSpace(int id);

	/**
	 * 根据停车场id获得空车锁
	 * 
	 * @param i
	 * @return
	 */
//	public List<CarLock> getCarLock(int id);

	/**
	 * 查询出是否有收费信息的停车场
	 * 
	 * @param isPrice
	 * @return
	 */
	public List<Park> getAll(boolean isPrice);

	/**
	 * 查询出是否有收费信息的停车场
	 * 
	 * @param parkIds
	 * @param isPrice
	 * @return
	 */
	public List<Park> getAll(List<Integer> parkIds, boolean isPrice);

	/**
	 * 查询出有车位的停车场
	 * 
	 * @param parkFilterBase
	 * @return
	 */
	public Map<String, Object> getList(ParkFilterBase parkFilterBase);

	/**
	 * 根据距离获取排序停车场列表
	 * 
	 * @param page
	 * @param rows
	 * @param start
	 * @param end
	 * @param leftLatitudeDouble
	 *            左边经度
	 * @param leftLongitudeDouble
	 *            左边纬度
	 * @param rightLatitudeDouble
	 *            右边经度
	 * @param rightLongitudeDouble
	 *            右边纬度
	 * @return map 包含rows total pages
	 * 
	 */
	Map<String, Object> getListByDistanceSort(int page, int rows, Date start, Date end, double latitude,
			double longitude, double latitudeDifference, double longItudeDifference);

	/**
	 * 根据价格获取排序之后停车场列表
	 * 
	 * @param page
	 * @param rows
	 * @param start
	 * @param end
	 * @param latitude
	 * @param longitude
	 * @param latitudeDifference
	 * @param longItudeDifference
	 * @return
	 */
	Map<String, Object> getListByPriceSort(int page, int rows, Date start, Date end, double latitude, double longitude,
			double latitudeDifference, double longItudeDifference);

	/**
	 * 
	 * @param page
	 * @param rows
	 * @param leftLatitudeDouble
	 *            左边经度
	 * @param leftLongitudeDouble
	 *            左边纬度
	 * @param rightLatitudeDouble
	 *            右边经度
	 * @param rightLongitudeDouble
	 *            右边纬度
	 * @return map 包含rows total pages
	 */
	Map<String, Object> getListByCoordinates(int page, int rows, Double leftLatitudeDouble, Double leftLongitudeDouble,
			Double rightLatitudeDouble, Double rightLongitudeDouble);

	/**
	 * 设置首小时价格
	 * 
	 * @param park
	 */
	void setPrice(Park park);

	void backupMongodbToMysql();

	/**
	 * 得到在线停车场列表
	 * 
	 * @param page
	 * @param rows
	 * @return
	 */
	Map<String, Object> getOnlineList(Integer page, Integer rows);
	/**
	 * 获取在线离线个数
	 * @return
	 */
    int CountOnlineList(List<Integer> parkIds);
	/**
	 * 得到在线停车场列表
	 * 
	 * @param page
	 * @param rows
	 * @return
	 */
	Map<String, Object> getOnlineList(Integer page, Integer rows, List<Integer> parkIds);

	/**
	 * 得到在线停车场列表id
	 * 
	 * @return
	 */
	List<Integer> getOnlineList();

	ParkSubBase findSub(int id);
	Park findSumTotal(List<Integer> parkIds);

	/**
	 * 得到在线停车场列表id
	 * 
	 * @param parkIds
	 * @return
	 */
	List<Integer> getOnlineList(List<Integer> parkIds);

	Map<String, Object> getAllByDistanceSort(int page, int rows, double latitude, double longitude);

	/**
	 * 根据类型获取停车场
	 * 
	 * @author 王生栋
	 * @return
	 */
	List<Park> getByType(int type);

	/**
	 * 根据类型等级获取停车场
	 * 
	 * @author 王生栋
	 * @return
	 */
	List<Park> getByLevel(int type);

	List<Park> getOutLineList();

	List<Park> getOutLineList(List<Integer> parkIds);

	/**
	 * 得到在线停车场数
	 * @param parkIds
	 * @return
	 */
	Integer countOnLine(List<Integer> parkIds);
	/**
	 * 根据价格排序
	 * @param parkIds
	 * @return
	 */
	Map<String, Object> getAllByChargePriceSort(int page, int rows);
	
	Park getParkByName(String name);
	
	public List<Park> getSpaceOnPark(Double leftLatitudeDouble, Double leftLongitudeDouble,
			Double rightLatitudeDouble, Double rightLongitudeDouble);

	Map<String, Object> getOfflineList(Integer page, Integer rows, List<Integer> parkIds);
	Map<String, Object> getlineList(Integer page, Integer rows, List<Integer> parkIds);
	
	public void updateConfigure(Park park);
	public void saveConfigure(Park park);
	void saveParking(Park park);
	void saveByName(Park park);
	Park findById(int id);
	List<Park> getNoParks(List<Integer> hasparkIds);
}
