package com.chinadovey.parking.webapps.biz.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinadovey.parking.core.supports.mybatis.pagination.PageContext;
import com.chinadovey.parking.core.supports.mybatis.pagination.Pagination;
import com.chinadovey.parking.webapps.biz.baseInfo.ParkBiz;
import com.chinadovey.parking.webapps.mappers.gen.CarSpaceMapper;
import com.chinadovey.parking.webapps.mappers.gen.CompanyMapper;
import com.chinadovey.parking.webapps.mappers.gen.ParkMapper;
import com.chinadovey.parking.webapps.mappers.gen.UserRelParkMapper;
import com.chinadovey.parking.webapps.pojo.CarSpace;
import com.chinadovey.parking.webapps.pojo.CarSpaceExample;
import com.chinadovey.parking.webapps.pojo.Company;
import com.chinadovey.parking.webapps.pojo.CompanyExample;
import com.chinadovey.parking.webapps.pojo.Park;
import com.chinadovey.parking.webapps.pojo.ParkExample;
import com.chinadovey.parking.webapps.pojo.UserRelPark;
import com.chinadovey.parking.webapps.pojo.UserRelParkExample;
import com.chinadovey.parking.webapps.pojo.base.ParkChargingRulesSubBase;
import com.chinadovey.parking.webapps.pojo.base.ParkFilterBase;
import com.chinadovey.parking.webapps.pojo.base.ParkSubBase;
import com.chinadovey.parking.webapps.utils.StringUtil;
@Service
public class ParkBizImpl implements ParkBiz{
	
	@Autowired
	private ParkMapper parkMapper;
	@Autowired
	private UserRelParkMapper userRelParkMapper;
	@Autowired
	private CompanyMapper companyMapper;
	@Autowired
	private CarSpaceMapper carSpaceMapper;

	@Override
	public Park find(int id) {
		return parkMapper.selectByPrimaryKey(id);
	}

	@Override
	public Park findbyparkname(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Park findOfPrice(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ParkChargingRulesSubBase findOfChargingRules(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Park findOfStatistics(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 保存新增 li
	 */
	@Override
	public void save(Park park) {
		parkMapper.insert(park);
	}

	@Override
	public int saveBack(Park park) {
		
		return 0;
	}
	/**
	 * 修改车场信息
	 */
	@Override
	public void update(Park park) {
		parkMapper.updateByPrimaryKey(park);
	}
	/**
	 * 删除车场
	 */
	@Override
	public void delete(int id) {
		parkMapper.deleteByPrimaryKey(id);
		//再删除user-->park 中间表
		UserRelParkExample e = new UserRelParkExample();
		e.createCriteria().andParkIdEqualTo(id);
		userRelParkMapper.deleteByExample(e);
	}

	@Override
	public void batchDelete(int[] ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isCodeExit(String code) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCodeExitExcept(String code, String orCode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Park> getAllByUserId(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> getParkIdsByUserId(int userId) {
		UserRelParkExample userRelParkExample = new UserRelParkExample();
		userRelParkExample.createCriteria().andUserIdEqualTo(userId);
		List<UserRelPark> userRelParks = userRelParkMapper.selectByExample(userRelParkExample);
		List<Integer> parkIds = new ArrayList<Integer>();
		for (UserRelPark userRelPark : userRelParks) {
			parkIds.add(userRelPark.getParkId());
		}
		return parkIds;
	}

	@Override
	public List<Integer> getParkIdsByUserId(Integer userId, Integer page,
			Integer rows) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCount(int userId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Integer> getAllIds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Park> getAll() {
		return parkMapper.selectByExample(null);
	}

	@Override
	public List<Park> getAll(List<Integer> parkIds) {
		ParkExample example = new ParkExample();
		example.createCriteria().andIdIn(parkIds);
		return parkMapper.selectByExample(example);
	}

	@Override
	public List<Park> getAllByCoordinates(Double latitudeDouble,
			Double longitudeDouble, Integer meterInt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Park> getAllByCoordinates(Double latitudeDouble,
			Double longitudeDouble, Integer meterInt, Integer page, Integer rows) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Park> getAllByCoordinates(Double leftLatitudeDouble,
			Double leftLongitudeDouble, Double rightLatitudeDouble,
			Double rightLongitudeDouble) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Park> getAllByCoordinates(Double leftLatitudeDouble,
			Double leftLongitudeDouble, Double rightLatitudeDouble,
			Double rightLongitudeDouble, List<Integer> parkIds, Integer type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Park> getAllByDistance(Double latitude, Double longitude,
			Integer meter, Integer flag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Park> getAllByDistance(Double latitude, Double longitude,
			Integer meter, Integer flag, List<Integer> parkIds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Park> getAllByDistance(Double latitude, Double longitude,
			Integer meter, Integer flag, List<Integer> parkIds, Integer type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isCodeExitById(String code, int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Park getNeighborhoodPark(Double latitudeDouble,
			Double longitudeDouble) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getList(int page, int rows) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getList(int page, int rows, List<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getList(int page, int rows, String search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getList(int page, int rows, String sort, String order, String search) {
		ParkExample parkExample = new ParkExample();
		
		//进行分页
		if(page <= 0 || rows <= 0){
			page = 0;
			rows = 10;
		}
		//进行排序
		if (sort != null && !sort.isEmpty()) {
			parkExample.setOrderByClause(StringUtil.changeOrderStr(sort) + " " + order);
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		Pagination<?> pagin = PageContext.initialize(page, rows);
		List<Park> list = parkMapper.selectByExample(parkExample, pagin.getRowBounds());
		map.put("rows", list);
		map.put("total", pagin.getTotalRows());
		return map;
		
	}

	@Override
	public Map<String, Object> getList(int page, int rows, String sort, String order, String search, List<Integer> parkIds) {
		ParkExample parkExample = new ParkExample();
		//进行分页
		if (page <= 0 || rows <= 0) {
			page = 0;
			rows = 10;
		}

		//进行排序
		if (sort != null && !sort.isEmpty()) {
			parkExample.setOrderByClause(sort + " " + order);
		}

		ParkExample.Criteria criteria = parkExample.createCriteria();
		if(search!=null && !search.isEmpty()){
			criteria.andNameLike("%"+search+"%");
		}
		//使用ParkId进行查询
		if (parkIds != null && parkIds.size() > 0) {
			criteria.andIdIn(parkIds);
		} 

		Map<String, Object> map = new HashMap<String, Object>();
		Pagination<?> pagin = PageContext.initialize(page, rows);
		List<Park> list = parkMapper.selectByExample(parkExample, pagin.getRowBounds());
		
		//增加机构名称、和车场对应的车位数量
		for(Park p:list){
			CompanyExample e = new CompanyExample();
			e.createCriteria().andIdEqualTo(p.getCompanyId());
			List<Company> coms = companyMapper.selectByExample(e);
			p.setCompanyName(coms.get(0).getName());
			
			CarSpaceExample ce = new CarSpaceExample();
			ce.createCriteria().andParkIdEqualTo(p.getId());
			int spaceNum = carSpaceMapper.countByExample(ce);
			p.setTotal(spaceNum);
		}
		
		map.put("rows", list);
		map.put("total", pagin.getTotalRows());
		return map;
	}

	@Override
	public List<CarSpace> getCarSpace(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Park> getAll(boolean isPrice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Park> getAll(List<Integer> parkIds, boolean isPrice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getList(ParkFilterBase parkFilterBase) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getListByDistanceSort(int page, int rows,
			Date start, Date end, double latitude, double longitude,
			double latitudeDifference, double longItudeDifference) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getListByPriceSort(int page, int rows,
			Date start, Date end, double latitude, double longitude,
			double latitudeDifference, double longItudeDifference) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getListByCoordinates(int page, int rows,
			Double leftLatitudeDouble, Double leftLongitudeDouble,
			Double rightLatitudeDouble, Double rightLongitudeDouble) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPrice(Park park) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void backupMongodbToMysql() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<String, Object> getOnlineList(Integer page, Integer rows) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int CountOnlineList(List<Integer> parkIds) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Map<String, Object> getOnlineList(Integer page, Integer rows,
			List<Integer> parkIds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> getOnlineList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ParkSubBase findSub(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Park findSumTotal(List<Integer> parkIds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> getOnlineList(List<Integer> parkIds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getAllByDistanceSort(int page, int rows,
			double latitude, double longitude) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Park> getByType(int type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Park> getByLevel(int type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Park> getOutLineList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Park> getOutLineList(List<Integer> parkIds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer countOnLine(List<Integer> parkIds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getAllByChargePriceSort(int page, int rows) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Park getParkByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Park> getSpaceOnPark(Double leftLatitudeDouble,
			Double leftLongitudeDouble, Double rightLatitudeDouble,
			Double rightLongitudeDouble) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getOfflineList(Integer page, Integer rows,
			List<Integer> parkIds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getlineList(Integer page, Integer rows,
			List<Integer> parkIds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateConfigure(Park park) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveConfigure(Park park) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveParking(Park park) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveByName(Park park) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * 根据id获得车场实例
	 */
	@Override
	public Park findById(int id) {
		Park park = parkMapper.selectByPrimaryKey(id);
		return park;
	}
	/**
	 * 得到未关联用户的车场
	 */
	@Override
	public List<Park> getNoParks(List<Integer> hasparkIds) {
		ParkExample e = new ParkExample();
		if(!hasparkIds.isEmpty()){
			e.createCriteria().andIdNotIn(hasparkIds);
		}
		List<Park> list = parkMapper.selectByExample(e);
		return list;
	}

}
