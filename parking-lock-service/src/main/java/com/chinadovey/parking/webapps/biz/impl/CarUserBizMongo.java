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
import com.chinadovey.parking.webapps.biz.baseInfo.CarUserBiz;
import com.chinadovey.parking.webapps.mappers.gen.CarSpaceMapper;
import com.chinadovey.parking.webapps.mappers.gen.CarUserMapper;
import com.chinadovey.parking.webapps.mappers.gen.CarUserRelCarNoMapper;
import com.chinadovey.parking.webapps.mappers.gen.CarUserRelCarSpaceMapper;
import com.chinadovey.parking.webapps.pojo.CarUser;
import com.chinadovey.parking.webapps.pojo.CarUserExample;
import com.chinadovey.parking.webapps.pojo.CarUserRelCarNo;
import com.chinadovey.parking.webapps.pojo.CarUserRelCarNoExample;
import com.chinadovey.parking.webapps.pojo.CarUserRelCarSpace;
import com.chinadovey.parking.webapps.pojo.CarUserRelCarSpaceExample;
import com.chinadovey.parking.webapps.pojo.base.CarUserCountBase;
import com.chinadovey.parking.webapps.utils.StringUtil;

@Service
public class CarUserBizMongo implements CarUserBiz {
	@Autowired
	private CarUserMapper mapper;

	@Autowired
	private CarUserRelCarSpaceMapper userRelSpaceMapper;

	@Autowired
	private CarUserRelCarNoMapper carUserRelCarNoMapper;


	@Autowired
	private CarSpaceMapper carSpaceMapper;

	@Override
	public CarUser find(int id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public CarUser save(CarUser carUser) {
		if(carUser.getRegisterTime()==null){
			carUser.setRegisterTime(new Date());
		}
		mapper.insertSelective(carUser);
		//mongoOps.save(carUser);
		return carUser;
	}

	@Override
	public void update(CarUser carUser) {
		mapper.updateByPrimaryKeySelective(carUser);
		//mongoOps.save(carUser);
	}
	@Override
	public void updateNo(CarUserRelCarNo carUserRelCarNo) {
		carUserRelCarNoMapper.updateByPrimaryKeySelective(carUserRelCarNo);
	}
	@Override
	public void delete(int id) {
		deleteCarUserById(id);
	}

	/**
	 * 根据删除与用户有关的信息
	 * 
	 * @param id
	 */
	public void deleteCarUserById(int id) {
		/*
		CarUser carUser = mongoOps.findOne(new Query(Criteria.where("_id").is(id)), CarUser.class);
		String mobile = carUser.getMobile();
		mongoOps.remove(new Query(Criteria.where("mobile").is(mobile)), Share.class);
		mongoOps.remove(new Query(Criteria.where("_id").is(id)), CarUser.class);
		*/

		CarUserRelCarSpaceExample carUserRelCarSpaceExample = new CarUserRelCarSpaceExample();
		carUserRelCarSpaceExample.createCriteria().andCarUserIdEqualTo(id);
		userRelSpaceMapper.deleteByExample(carUserRelCarSpaceExample);

		/*List<CarUserRelCarSpaceKey> carUserRelCarSpaceKeys = userRelSpaceMapper
				.selectByExample(carUserRelCarSpaceExample);
		CarSpace carSpace = new CarSpace();
		carSpace.setType(1);

		for (CarUserRelCarSpaceKey carUserRelCarSpaceKey : carUserRelCarSpaceKeys) {
			carSpace.setId(carUserRelCarSpaceKey.getCarSpaceId());
			carSpaceMapper.updateByPrimaryKeySelective(carSpace);
		}*/
        mapper.deleteByPrimaryKey(id);
	 }

	@Override
	public Map<String, Object> getList(int page, int rows) {
		return getList(page, rows, null, null);
	}

	@Override
	public Map<String, Object> getList(int page, int rows, List<Integer> parkId) {
		return getList(page, rows, null, parkId);
	}

	@Override
	public Map<String, Object> getList(int page, int rows, String search) {
		return getList(page, rows, search, null);
	}

	@Override
	public Map<String, Object> getList(int page, int rows, String search, List<Integer> parkId) {
		return getList(page, rows, null, null, search, parkId);
	}

	@Override
	public Map<String, Object> getList(int page, int rows, String sort, String order, String search,
			List<Integer> parkId) {
		CarUserExample example = new CarUserExample();
		// 进行分页
		if (page <= 0 || rows <= 0) {
			page = 0;
			rows = 10;
		}

		// 进行排序
		if (sort != null && !sort.isEmpty()) {
			example.setOrderByClause(StringUtil.changeOrderStr(sort) + " " + order);
		}

		CarUserExample.Criteria criteria1 = example.createCriteria();
		CarUserExample.Criteria criteria2 = example.createCriteria();
		CarUserExample.Criteria criteria3 = example.createCriteria();
		CarUserExample.Criteria criteria4 = example.createCriteria();

		// 使用ParkId进行查询
		if (parkId != null && parkId.size() > 0) {
			criteria1.andParkIdIn(parkId);
			criteria2.andParkIdIn(parkId);
			criteria3.andParkIdIn(parkId);
			criteria4.andParkIdIn(parkId);
			// 进行模糊查询
			if (search != null && !search.isEmpty()) {
				criteria1.andNameLike("%" + search + "%");
				criteria2.andMobileLike("%" + search + "%");
				criteria3.andWechatLike("%" + search + "%");
				criteria4.andPlateNumberLike("%" + search + "%");
			}
		} else {
			// 进行模糊查询
			if (search != null && !search.isEmpty()) {
				criteria1.andNameLike("%" + search + "%");
				criteria2.andMobileLike("%" + search + "%");
				criteria3.andWechatLike("%" + search + "%");
				criteria4.andPlateNumberLike("%" + search + "%");
			}
		}

		example.or(criteria2);
		example.or(criteria3);
		example.or(criteria4);

		Map<String, Object> map = new HashMap<String, Object>();
		Pagination<?> pagin = PageContext.initialize(page, rows);
		List<CarUser> list = mapper.selectByExample(example, pagin.getRowBounds());
		map.put("rows", list);
		map.put("total", pagin.getTotalRows());
		return map;
	}

	@Override
	public Map<String, Object> getList(int page, int rows, String sort, String order, String search) {
		return getList(page, rows, sort, order, search, null);
	}

	@Override
	public void batchDelete(int[] ids) {
		List<Integer> idList = new ArrayList<Integer>();
		for (int i = 0; i < ids.length; i++) {
			idList.add(ids[i]);
		}
		CarUserExample example = new CarUserExample();
		example.createCriteria().andIdIn(idList);
		mapper.deleteByExample(example);
	}

	@Override
	public void save(CarUser carUser, Integer[] carSpaceIds) {
		save(carUser);
		CarUserExample example = new CarUserExample();
		example.createCriteria().andNameEqualTo(carUser.getName());
		List<CarUser> user=mapper.selectByExample(example);
		if (user != null && !user.isEmpty()) {
		for (int spaceId : carSpaceIds) {
			CarUserRelCarSpace key = new CarUserRelCarSpace();
			
			key.setCarUserId(user.get(0).getId());
			key.setCarSpaceId(spaceId);
//			userRelSpaceMapper.insert(key);
		}	
		}
	}

	@Override
	public void update(CarUser carUser, Integer[] carSpaceIds) {
		update(carUser);
		CarUserRelCarSpaceExample keyExample = new CarUserRelCarSpaceExample();
		keyExample.createCriteria().andCarUserIdEqualTo(carUser.getId());
		userRelSpaceMapper.deleteByExample(keyExample);
		if (carSpaceIds == null) {
			return;
		}
	
		for (int spaceId : carSpaceIds) {
			CarUserRelCarSpace key = new CarUserRelCarSpace();
			key.setCarUserId(carUser.getId());
			key.setCarSpaceId(spaceId);
//			userRelSpaceMapper.insert(key);
		}

	}

	@Override
	public CarUser getByWechat(String wechat) {
		CarUserExample example = new CarUserExample();
		example.createCriteria().andWechatEqualTo(wechat);
		List<CarUser> users = mapper.selectByExample(example);
		if (users != null && !users.isEmpty()) {
			return users.get(0);
		}
		return null;
	}

	@Override
	public CarUser getByOpenid(String openid) {
		CarUserExample example = new CarUserExample();
		example.createCriteria().andOpenidEqualTo(openid);
		List<CarUser> users = mapper.selectByExample(example);
		if (users != null && !users.isEmpty()) {
			return users.get(0);
		}
		return null;
	}

	@Override
	public CarUser getByMobile(String mobile) {
		CarUserExample example = new CarUserExample();
		example.createCriteria().andMobileEqualTo(mobile);
		List<CarUser> users = mapper.selectByExample(example);
		if (users != null && !users.isEmpty()) {
			return users.get(0);
		}
		return null;
	}
//	@Override
//	public CarUserResponse getByMobileRes(String mobile) {
//		CarUserExample example = new CarUserExample();
//		example.createCriteria().andMobileEqualTo(mobile);
//		List<CarUser> users = mapper.selectByExample(example);
//		if (users != null && !users.isEmpty()) {
//			
//			CarUserAccount carUserAccount = carUserAccount
//			return CarUserResponse.change(users.get(0));
//		}
//		return null;
//	}
	@Override
	public CarUser getByPlateNumber(String plateNumber) {
		CarUserExample example = new CarUserExample();
		example.createCriteria().andPlateNumberEqualTo(plateNumber);
		List<CarUser> users = mapper.selectByExample(example);
		if (users != null && !users.isEmpty()) {
			return users.get(0);
		}
		return null;
	}

	@Override
	public List<CarUser> getListByPlateNumber(String plateNumber) {
		CarUserExample example = new CarUserExample();
		example.createCriteria().andPlateNumberEqualTo(plateNumber);
		List<CarUser> users = mapper.selectByExample(example);
		return users;
	}

	@Override
	public boolean isMobileExit(String mobile) {
		CarUserExample example = new CarUserExample();
		example.createCriteria().andMobileEqualTo(mobile);
		return mapper.countByExample(example) > 0;
	}

	@Override
	public boolean isMobileExitById(String mobile, int id) {
		CarUser carUser = mapper.selectByPrimaryKey(id);
		if (carUser.getMobile() == null || carUser.getMobile().isEmpty() || carUser.getMobile().equals(mobile)) {
			return false;
		}
		return isMobileExit(mobile);
	}

	@Override
	public boolean isWechatExit(String wechat) {
		CarUserExample example = new CarUserExample();
		example.createCriteria().andWechatEqualTo(wechat);
		return mapper.countByExample(example) > 0;
	}

	@Override
	public boolean isWechatExitById(String wechat, int id) {
		CarUser carUser = mapper.selectByPrimaryKey(id);
		if (carUser.getWechat() == null || carUser.getWechat().isEmpty() || carUser.getWechat().equals(wechat)) {
			return false;
		}
		return isWechatExit(wechat);
	}

	@Override
	public boolean isPlateNumberExit(String plateNumeber) {
		CarUserExample example = new CarUserExample();
		example.createCriteria().andPlateNumberEqualTo(plateNumeber);
		return mapper.countByExample(example) > 0;
	}

	@Override
	public boolean isPlateNumberExitById(String plateNumeber, int id) {
		CarUser carUser = mapper.selectByPrimaryKey(id);
		if (carUser.getPlateNumber() == null || carUser.getPlateNumber().isEmpty()
				|| carUser.getPlateNumber().equals(plateNumeber)) {
			return false;
		}
		return isPlateNumberExit(plateNumeber);
	}

	@Override
	public Integer addCarNo(Integer carUserId, String carNo) {
		if (carUserId == null || carNo == null) {
			return 0;
		}
		if (isCarNoExit(carUserId, carNo)) {
			return 0;
		}
		CarUserRelCarNo carUserRelCarNo = new CarUserRelCarNo();
		carUserRelCarNo.setCarNo(carNo);
		carUserRelCarNo.setCarUserId(carUserId);
		carUserRelCarNoMapper.insertSelective(carUserRelCarNo);
		return carUserRelCarNo.getId();
	}
	
	@Override
	public Integer saveCarNo(CarUserRelCarNo carUserRelCarNo) {
		if(carUserRelCarNo!=null){
			Integer carUserId = carUserRelCarNo.getCarUserId();
			String carNo = carUserRelCarNo.getCarNo();
			if (carUserId == null || carNo == null) {
				return 0;
			}
			if (isCarNoExit(carUserId, carNo)) {
				return 0;
			}
//			CarUserRelCarNo _carUserRelCarNo = new CarUserRelCarNo();
//			_carUserRelCarNo.setCarNo(carNo);
//			_carUserRelCarNo.setCarUserId(carUserId);
			carUserRelCarNoMapper.insertSelective(carUserRelCarNo);
			return 1;
		}
		return 0;
	}

	@Override
	public boolean isCarNoExit(Integer carUserId, String carNo) {
		CarUserRelCarNoExample carUserRelCarNoExample = new CarUserRelCarNoExample();
		carUserRelCarNoExample.createCriteria().andCarNoEqualTo(carNo).andCarUserIdEqualTo(carUserId);
		int count = carUserRelCarNoMapper.countByExample(carUserRelCarNoExample);
		return count >= 1 ? true : false;
	}

	@Override
	public void deleteCarNo(Integer carUserId, String carNo) {
		CarUserRelCarNoExample carUserRelCarNoExample = new CarUserRelCarNoExample();
		carUserRelCarNoExample.createCriteria().andCarNoEqualTo(carNo).andCarUserIdEqualTo(carUserId);
		carUserRelCarNoMapper.deleteByExample(carUserRelCarNoExample);
	}
	@Override
	public CarUserRelCarNo getBindNo(Integer carUserId,String carNo) {
		CarUserRelCarNoExample carUserRelCarNoExample = new CarUserRelCarNoExample();
		carUserRelCarNoExample.createCriteria().andCarUserIdEqualTo(carUserId).andCarNoEqualTo(carNo);
		List<CarUserRelCarNo> carUserRelCarNos = carUserRelCarNoMapper.selectByExample(carUserRelCarNoExample);
		if(carUserRelCarNos!=null&&carUserRelCarNos.size()>0){
			return carUserRelCarNos.get(0);
		}
		return null;
	}
	@Override
	public List<String> getCarNosByCarUserId(Integer carUserId) {
		CarUserRelCarNoExample carUserRelCarNoExample = new CarUserRelCarNoExample();
		carUserRelCarNoExample.createCriteria().andCarUserIdEqualTo(carUserId);
		List<CarUserRelCarNo> carUserRelCarNos = carUserRelCarNoMapper.selectByExample(carUserRelCarNoExample);
		List<String> carNos = new ArrayList<String>();
		for (CarUserRelCarNo carUserRelCarNo : carUserRelCarNos) {
			carNos.add(carUserRelCarNo.getCarNo());
		}
		return carNos;
	}

	@Override
	public List<CarUserRelCarNo> getCarUserNosByCarUserId(Integer carUserId) {
		CarUserRelCarNoExample carUserRelCarNoExample = new CarUserRelCarNoExample();
		carUserRelCarNoExample.createCriteria().andCarUserIdEqualTo(carUserId);
		List<CarUserRelCarNo> carUserRelCarNos = carUserRelCarNoMapper.selectByExample(carUserRelCarNoExample);
		return carUserRelCarNos;
	}

	@Override
	public void addHistoryCarNo(Integer carUserId, String carNo) {
		if (carUserId == null || carNo == null) {
			return;
		}
		if (isHistoryCarNoExit(carUserId, carNo)) {
			return;
		}
		/*CarUserHistoryCarNo carUserHistoryCarNo = new CarUserHistoryCarNo();
		carUserHistoryCarNo.setCarNo(carNo);
		carUserHistoryCarNo.setCarUserId(carUserId);
		carUserHistoryCarNoMapper.insertSelective(carUserHistoryCarNo);*/
	}

	@Override
	public boolean isHistoryCarNoExit(Integer carUserId, String carNo) {
		return false;
		/*CarUserHistoryCarNoExample carUserHistoryCarNoExample = new CarUserHistoryCarNoExample();
		carUserHistoryCarNoExample.createCriteria().andCarNoEqualTo(carNo).andCarUserIdEqualTo(carUserId);
		int count = carUserHistoryCarNoMapper.countByExample(carUserHistoryCarNoExample);
		return count >= 1 ? true : false;*/
	}

	@Override
	public void deleteHistoryCarNo(Integer carUserId, String carNo) {
		/*CarUserHistoryCarNoExample carUserHistoryCarNoExample = new CarUserHistoryCarNoExample();
		carUserHistoryCarNoExample.createCriteria().andCarNoEqualTo(carNo).andCarUserIdEqualTo(carUserId);
		carUserHistoryCarNoMapper.deleteByExample(carUserHistoryCarNoExample);*/
	}

	@Override
	public List<String> getHistoryCarNosByCarUserId(Integer carUserId) {
		return null;
		/*CarUserHistoryCarNoExample carUserHistoryCarNoExample = new CarUserHistoryCarNoExample();
		carUserHistoryCarNoExample.createCriteria().andCarUserIdEqualTo(carUserId);
		List<CarUserHistoryCarNo> carUserHistoryCarNos = carUserHistoryCarNoMapper
				.selectByExample(carUserHistoryCarNoExample);
		List<String> carNos = new ArrayList<String>();
		for (CarUserHistoryCarNo carUserHistoryCarNo : carUserHistoryCarNos) {
			carNos.add(carUserHistoryCarNo.getCarNo());
		}
		return carNos;*/
	}

	@Override
	public void saveUser(String openid, Integer wechatId) {
		/*CarUserRelWechatKey carUserRelWechatKey = new CarUserRelWechatKey();
		carUserRelWechatKey.setOpenid(openid);
		carUserRelWechatKey.setWechatId(wechatId);
		carUserRelWechatMapper.deleteByPrimaryKey(carUserRelWechatKey);
		carUserRelWechatMapper.insertSelective(carUserRelWechatKey);*/
	}

	@Override
	public Map<String, Object> getNoList(int page, int rows,int carUserId) {
		CarUserRelCarNoExample example = new CarUserRelCarNoExample();
		// 进行分页
		if (page <= 0 || rows <= 0) {
			page = 0;
			rows = 10;
		}
		// 进行排序
		example.setOrderByClause("status ");
		

		Map<String, Object> map = new HashMap<String, Object>();
		Pagination<?> pagin = PageContext.initialize(page, rows);
		example.createCriteria().andCarUserIdEqualTo(carUserId);
		List<CarUserRelCarNo> list = carUserRelCarNoMapper.selectByExample(example, pagin.getRowBounds());
		map.put("rows", list);
		map.put("total", pagin.getTotalRows());
		return map;
	}
	
	@Override
	public List<CarUserCountBase> getCountByDay(Date startDate, Date endDate) {
		CarUserExample carUserExample = new CarUserExample();
		carUserExample.createCriteria().andRegisterTimeBetween(startDate, endDate);
		carUserExample.setOrderByClause("register_time");
		List<CarUserCountBase> carUserCountBases = mapper.selectGroupByExample(carUserExample);
		return carUserCountBases;
	}
	@Override
	public Integer getCountByParkId(List<Integer> parkIds) {
		CarUserExample carUserExample = new CarUserExample();
		carUserExample.createCriteria().andParkIdIn(parkIds);
		return mapper.countByExample(carUserExample);
	}
	@Override
	public List<CarUserCountBase> getCountByDay(Date startDate, Date endDate,Integer source) {
		CarUserExample carUserExample = new CarUserExample();
		if (source == 0) {
			carUserExample.createCriteria().andRegisterTimeBetween(startDate, endDate);
		}else{
			carUserExample.createCriteria().andRegisterTimeBetween(startDate, endDate).andTypeEqualTo(source);
		}
		carUserExample.setOrderByClause("register_time");
		List<CarUserCountBase> carUserCountBases = mapper.selectGroupByExample(carUserExample);
		return carUserCountBases;
	}
	@Override
	public int getUserbyMonth(Date startDate, Date endDate,List<Integer> type) {
		CarUserExample carUserExample = new CarUserExample();
		CarUserCountBase base =new CarUserCountBase();
		if (startDate == null) {
			carUserExample.createCriteria().andTypeIn(type);
		}else{
			carUserExample.createCriteria().andRegisterTimeBetween(startDate, endDate).andTypeIn(type);
		}
		carUserExample.setOrderByClause("register_time");
	int count = mapper.countByExample(carUserExample);
		
		return count;
	}
	@Override
	public int getCarUserbyMonth(Date startDate, Date endDate,List<Integer> type) {
		CarUserExample carUserExample = new CarUserExample();
		CarUserCountBase base =new CarUserCountBase();
		CarUserExample.Criteria criteria2 = carUserExample.createCriteria();
		CarUserExample.Criteria criteria3 = carUserExample.createCriteria();
		if (startDate == null) {
			criteria2.andTypeIsNull();
			criteria3.andTypeNotIn(type);
		}else{
			criteria2.andRegisterTimeBetween(startDate, endDate).andTypeIsNull();
			criteria3.andTypeNotIn(type);
		}
		carUserExample.or(criteria3);
		int count = mapper.countByExample(carUserExample);
		
		return count;
	}
	@Override
	public CarUser getByPlateNumber(String plateNumber,Integer parkId) {
		CarUserExample example = new CarUserExample();
		example.createCriteria().andPlateNumberEqualTo(plateNumber).andParkIdEqualTo(parkId);
		List<CarUser> users = mapper.selectByExample(example);
		if (users != null && !users.isEmpty()) {
			return users.get(0);
		}
		return null;
	}
	
}