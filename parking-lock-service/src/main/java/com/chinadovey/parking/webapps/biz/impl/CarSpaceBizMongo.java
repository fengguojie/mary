package com.chinadovey.parking.webapps.biz.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinadovey.parking.core.supports.mybatis.pagination.PageContext;
import com.chinadovey.parking.core.supports.mybatis.pagination.Pagination;
import com.chinadovey.parking.webapps.biz.CarLockBiz;
import com.chinadovey.parking.webapps.biz.baseInfo.CarSpaceBiz;
import com.chinadovey.parking.webapps.biz.baseInfo.CarUserBiz;
import com.chinadovey.parking.webapps.biz.baseInfo.GatewayBiz;
import com.chinadovey.parking.webapps.mappers.gen.CarSpaceMapper;
import com.chinadovey.parking.webapps.mappers.gen.CarUserRelCarSpaceMapper;
import com.chinadovey.parking.webapps.pojo.CarLock;
import com.chinadovey.parking.webapps.pojo.CarSpace;
import com.chinadovey.parking.webapps.pojo.CarSpaceExample;
import com.chinadovey.parking.webapps.pojo.CarSpaceExample.Criteria;
import com.chinadovey.parking.webapps.pojo.CarUser;
import com.chinadovey.parking.webapps.pojo.CarUserRelCarSpaceExample;
import com.chinadovey.parking.webapps.pojo.Company;
import com.chinadovey.parking.webapps.pojo.CompanyExample;
import com.chinadovey.parking.webapps.pojo.Gateway;
import com.chinadovey.parking.webapps.pojo.Park;
import com.chinadovey.parking.webapps.utils.DateUtil;
import com.chinadovey.parking.webapps.utils.MathUtil;
import com.chinadovey.parking.webapps.utils.StringUtil;

@Service
public class CarSpaceBizMongo implements CarSpaceBiz {
	@Autowired
	private CarSpaceMapper mapper;
	@Autowired
	private CarUserRelCarSpaceMapper userRelSpaceMapper;
	@Autowired
	private CarUserBiz carUserBiz;
	@Autowired
	private CarLockBiz carLockBiz;
	@Autowired
	private GatewayBiz gatewayBiz;

	@Override
	public CarSpace find(int id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public CarSpace findByCarLockId(int carLockId) {
		CarSpaceExample carSpaceExample = new CarSpaceExample();
//		carSpaceExample.createCriteria().andCarLockIdEqualTo(carLockId);
		List<CarSpace> carSpaces = mapper.selectByExample(carSpaceExample);
		if (carSpaces != null && !carSpaces.isEmpty()) {
			return carSpaces.get(0);
		}
		return null;
	}
	@Override
	public CarSpace findBySlaveId(String id) {
		CarSpaceExample carSpaceExample = new CarSpaceExample();
		carSpaceExample.createCriteria().andSlaveIdEqualTo(id);
		List<CarSpace> carSpaces = mapper.selectByExample(carSpaceExample);
		if (carSpaces != null && !carSpaces.isEmpty()) {
			return carSpaces.get(0);
		}
		return null;
	}
	@Override
	public List<CarSpace> findSlaveIdsByUserId(int id) {
		CarSpaceExample carSpaceExample = new CarSpaceExample();
		carSpaceExample.createCriteria().andCarUserIdEqualTo(id);
		List<CarSpace> carSpaces = mapper.selectByExample(carSpaceExample);
		if (carSpaces != null && !carSpaces.isEmpty()) {
			return carSpaces;
		}
		return null;
	}
	@Override
	public CarSpace findByCode(String code) {
		CarSpaceExample carSpaceExample = new CarSpaceExample();
		carSpaceExample.createCriteria().andSpaceNoEqualTo(code);
		List<CarSpace> carSpaces = mapper.selectByExample(carSpaceExample);
		if (carSpaces != null && !carSpaces.isEmpty()) {
			return carSpaces.get(0);
		}
		return null;
	}
	@Override
	public void save(CarSpace carSpace) {
//		carSpace.setCumulateTime(0L);
//		carSpace.setCumulateTimes(0);
		carSpace.setIsEmpty(1);
//		carSpace.setType(1);
		mapper.insertSelective(carSpace);
		carSpace = mapper.selectByPrimaryKey(carSpace.getId());
//		mongoOps.save(carSpace);
	}

	@Override
	public int saveSpace(CarSpace carSpace) {
//		carSpace.setCumulateTime(0L);
//		carSpace.setCumulateTimes(0);
		carSpace.setIsEmpty(1);
//		carSpace.setType(1);
		return mapper.insertSelective(carSpace);
//		mongoOps.save(carSpace);
	}
	
	@Override
	public void update(CarSpace carSpace) {
		mapper.updateByPrimaryKeySelective(carSpace);
	}

	@Override
	public void delete(int id) {
		mapper.deleteByPrimaryKey(id);
		//deleteCarSpaceById(id);
	}

	public void deleteCarSpaceById(int id) {
		CarUserRelCarSpaceExample carUserRelCarSpaceExample = new CarUserRelCarSpaceExample();
		carUserRelCarSpaceExample.createCriteria().andCarSpaceIdEqualTo(id);
		userRelSpaceMapper.deleteByExample(carUserRelCarSpaceExample);
     }

	@Override
	public Map<String, Object> getList(int page, int rows, List<Integer> parkId) {
		return getList(page, rows, null, null, null, null);
	}

	@Override
	public Map<String, Object> getList(int page, int rows, String search) {
		return getList(page, rows, search, null, null, null);
	}

	@Override
	public Map<String, Object> getList(List<Integer> parkIds, int page, int rows, String sort, String order, String search) {
		CarSpaceExample example = new CarSpaceExample();
		// 进行分页
		if (page <= 0 || rows <= 0) {
			page = 0;
			rows = 10;
		}
		// 进行排序
		if (sort != null && !sort.isEmpty()) {
			example.setOrderByClause(StringUtil.changeOrderStr(sort) + " " + order);
		}
		CarSpaceExample.Criteria criteria = example.createCriteria();
		//查询parkId
		if (parkIds != null && !parkIds.isEmpty()) {
			criteria.andParkIdIn(parkIds);
		}
		//进行模糊查询
		if (search != null && !search.isEmpty()) {
			criteria.andSpaceNoLike("" + search + "%");
		}

		Map<String, Object> map = new HashMap<String, Object>();
		Pagination<?> pagin = PageContext.initialize(page, rows);
		List<CarSpace> list = mapper.selectByExample(example, pagin.getRowBounds());
		setCarSpaces(list);
		map.put("rows", list);
		map.put("total", pagin.getTotalRows());
		return map;
	}
	
	@Override
	public Map<String, Object> getList(int page, int rows, String search, String sort, String order, List<Integer> parkId, String name) {
		CarSpaceExample example = new CarSpaceExample();
		// 进行分页
		if (page <= 0 || rows <= 0) {
			page = 0;
			rows = 10;
		}

		// 进行排序
		if (sort != null && !sort.isEmpty()) {
			example.setOrderByClause(StringUtil.changeOrderStr(sort) + " " + order);
		}

		CarSpaceExample.Criteria criteria = example.createCriteria();

		// 使用ParkId进行查询
		if (parkId != null && parkId.size() > 0) {
			criteria.andParkIdIn(parkId);
			if (search != null && !search.isEmpty()) {
//				criteria.andNameLike("%" + search + "%");
			}
		} else {
			// 进行模糊查询
			if (search != null && !search.isEmpty()) {
//				criteria.andNameLike("%" + search + "%");
			}
		}
		//使用车位号进行查询
		if(name != null){
//			criteria.andNameEqualTo(name);
		}

		Map<String, Object> map = new HashMap<String, Object>();
		Pagination<?> pagin = PageContext.initialize(page, rows);
		List<CarSpace> list = mapper.selectByExample(example, pagin.getRowBounds());
		setCarSpaces(list);
		map.put("rows", list);
		map.put("total", pagin.getTotalRows());
		return map;
	}
	
	@Override
	public Map<String, Object> getList(int page, int rows, String search, String sort, String order) {
		return getList(page, rows, search, sort, order, null);
	}

	@Override
	public Map<String, Object> getList(int page, int rows, String search, List<Integer> parkId) {
		return getList(page, rows, search, null, null, parkId);
	}

	@Override
	public Map<String, Object> getList(int page, int rows) {
		return getList(page, rows, null, null);
	}

	@Override
	public void batchDelete(int[] ids) {
		List<Integer> idList = new ArrayList<Integer>();
		for (int i = 0; i < ids.length; i++) {
			idList.add(ids[i]);
		}
		CarSpaceExample example = new CarSpaceExample();
		example.createCriteria().andIdIn(idList);
		mapper.deleteByExample(example);
	}

	@Override
	public boolean isCodeExit(String spaceNo) {
		CarSpaceExample example = new CarSpaceExample();
		example.createCriteria().andSpaceNoEqualTo(spaceNo);
		List<CarSpace> departs = mapper.selectByExample(example);
		if (departs != null && !departs.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isCodeExitById(String spaceNo, int id) {
		if (mapper.selectByPrimaryKey(id).getSpaceNo().equals(spaceNo)) {
			return false;
		}
		return isCodeExit(spaceNo);
	}

	@Override
	public boolean isCodeExitExcept(String code, String orCode) {
		CarSpaceExample example = new CarSpaceExample();
//		example.createCriteria().andCodeEqualTo(code).andCodeNotEqualTo(orCode);
		List<CarSpace> departs = mapper.selectByExample(example);
		if (departs != null && !departs.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<CarSpace> getAll(Integer parkId) {
		if (parkId != null) {
			//return mongoOps.find(new Query(Criteria.where("parkId").is(parkId)), CarSpace.class);
			CarSpaceExample example = new CarSpaceExample();
			example.createCriteria().andParkIdEqualTo(parkId);
			return mapper.selectByExample(example);
		} else {
			return null;
		}
	}
	@Override
	public List<CarSpace> getAllNoUsed(Integer parkId) {
		if (parkId != null) {
			CarSpaceExample example = new CarSpaceExample();
			example.createCriteria().andParkIdEqualTo(parkId).andCarUserIdEqualTo(0);
			return mapper.selectByExample(example);
		} else {
			return null;
		}
	}

	@Override
	public List<CarSpace> getAll(List<Integer> parkIds) {
		if (parkIds != null && parkIds.size() != 0) {
			//return mongoOps.find(new Query(Criteria.where("parkId").in(parkIds)), CarSpace.class);
			CarSpaceExample example = new CarSpaceExample();
			example.createCriteria().andParkIdIn(parkIds);
			return mapper.selectByExample(example);
		}
		return null;
	}
	@Override
	public List<CarSpace> getAllByCode(List<String> codes) {
		if (codes != null && codes.size() != 0) {
			//return mongoOps.find(new Query(Criteria.where("parkId").in(parkIds)), CarSpace.class);
			CarSpaceExample example = new CarSpaceExample();
			example.createCriteria().andSpaceNoIn(codes);
			return mapper.selectByExample(example);
		}
		return null;
	}
	@Override
	public List<CarSpace> getAllNoUsed(List<Integer> parkIds) {
		if (parkIds != null && parkIds.size() != 0) {
			CarSpaceExample example = new CarSpaceExample();
			example.createCriteria().andParkIdIn(parkIds).andCarUserIdEqualTo(0);
			return mapper.selectByExample(example);
		}
		return null;
	}

	@Override
	public List<CarSpace> getAll(Set<Integer> parkIds) {
		List<Integer> parkIds2 = new ArrayList<Integer>(parkIds);
		return getAll(parkIds2);
	}
//
//	@Override
//	public List<CarSpace> getByUserId(int userId) {
//		CarUserRelCarSpaceExample keyExample = new CarUserRelCarSpaceExample();
//		keyExample.createCriteria().andCarUserIdEqualTo(userId);
//		List<CarUserRelCarSpaceKey> userRelspaces = userRelSpaceMapper.selectByExample(keyExample);
//		if (userRelspaces != null && !userRelspaces.isEmpty()) {
//			List<Integer> spaceIds = new ArrayList<Integer>();
//			for (CarUserRelCarSpaceKey userRelspace : userRelspaces) {
//				spaceIds.add(userRelspace.getCarSpaceId());
//			}
//			CarSpaceExample spaceExample = new CarSpaceExample();
//			spaceExample.createCriteria().andIdIn(spaceIds);
//			List<CarSpace> carSpaces = mapper.selectByExample(spaceExample);
////			mongoOps.find(new Query(Criteria.where("id").in(spaceIds)), CarSpace.class);
//			setCarSpaces(carSpaces);
//			return carSpaces;
//		}
//		return null;
//	}
	@Override
	public List<CarSpace> getByUserId(int userId) {
		CarSpaceExample example = new CarSpaceExample();
		example.createCriteria().andCarUserIdEqualTo(userId);
		List<CarSpace> carSpaces = mapper.selectByExample(example);
		
		return carSpaces;
	}

	private void setCarSpaces(List<CarSpace> carSpaces) {
		for (Iterator<CarSpace> iterator = carSpaces.iterator(); iterator.hasNext();) {
			CarSpace carSpace = (CarSpace) iterator.next();
			setCarSpace(carSpace);
		}
	}

	/**
	 * @param carSpace
	 */
	private void setCarSpace(CarSpace carSpace) {
		/*if (carSpace.getCarLock() == null) {
			CarLock carLock = new CarLock();
			carLock.setLastCloseTime(new Date());
			carLock.setLastOpenTime(new Date());
			carSpace.setCarLock(carLock);
		}
		if (carSpace.getPark() == null) {
			carSpace.setPark(new Park());
		}*/
	}

	@Override
	public List<CarSpace> getByWechat(String wechat) {
		CarUser user = carUserBiz.getByWechat(wechat);
		if (user != null) {
			return getByUserId(user.getId());
		}
		return null;
	}

	@Override
	public Integer getAllCount(List<Integer> parkId) {
		return 0;
	}

	@Override
	public Integer getAllCount() {
		return 0;
	}

	@Override
	public List<CarSpace> getByMobile(String mobile) {
		CarUser user = carUserBiz.getByMobile(mobile);
		if (user != null) {
			return getByUserId(user.getId());
		}
		return null;
	}

	@Override
	public CarSpace getSingleByMobile(String mobile) {
		CarUser user = carUserBiz.getByMobile(mobile);
		if (user != null) {
			List<CarSpace> spaces = getByUserId(user.getId());
			if (spaces != null && !spaces.isEmpty()) {
				return spaces.get(0);
			}
		}
		return null;
	}

	@Override
	public void updateEmpty(Date startDateTime, Date endDateTime) {

		// 根据订阅信息更新公共车位
		updateCarSpaceEmpty();
		updatePublicNoEmpty(startDateTime, endDateTime);

		// 得到处于分享状态的对象
		/*List<Share> shares = getSharesStatus(1, startDateTime, endDateTime);

		// 创建两个list存放处于空的车位和非空的车位
		List<Integer> emptyCarSpaceIds = new ArrayList<Integer>();
		List<Integer> noEmptyCarSpaceIds = new ArrayList<Integer>();

		// 设置车位id到集合中
		setCarSpaceId(startDateTime, endDateTime, shares, emptyCarSpaceIds, noEmptyCarSpaceIds);

		if (emptyCarSpaceIds.size() > 0) {
			// 更新空车位
			updateEmptyCarSpace(emptyCarSpaceIds);
		}

		if (noEmptyCarSpaceIds.size() > 0) {
			// 更新非空车位
			updateNoEmptyCarSpace(noEmptyCarSpaceIds);
		}*/

	}

	/**
	 * 将所有的停车位设置为非空
	 */
	private void updateCarSpaceEmpty() {
		/*mongoOps.updateMulti(new Query(Criteria.where("type").is(1)), Update.update("isEmpty", 1), CarSpace.class);*/
	}

	public void updatePublicEmpty(Date startDateTime, Date endDateTime) {
		/*Criteria startCriteria = new Criteria("startTime").lte(endDateTime);
		Criteria endCriteria = new Criteria("endTime").lte(startDateTime)
				.gte(DateUtil.getNeedDate(startDateTime, "h", -1));
		List<Subscribe> emptySubscribe = mongoOps.find(
				new Query(Criteria.where("status").ne(2).orOperator(startCriteria, endCriteria)), Subscribe.class);
		List<Integer> emptySubscribeCarSpaceIds = new ArrayList<Integer>();
		for (Subscribe subscribe : emptySubscribe) {
			emptySubscribeCarSpaceIds.add(subscribe.getCarSpaceId());
		}
		mongoOps.updateMulti(new Query(Criteria.where("_id").in(emptySubscribeCarSpaceIds).and("isEmpty").is(0)),
				Update.update("isEmpty", 1), CarSpace.class);*/
	}

	public void updatePublicNoEmpty(Date startDateTime, Date endDateTime) {
		/*List<Subscribe> noEmptySubscribe = mongoOps.find(
				new Query(Criteria.where("startTime").lte(endDateTime).and("endTime").gte(startDateTime)),
				Subscribe.class);
		List<Integer> noEmptySubscribeCarSpaceIds = new ArrayList<Integer>();
		for (Subscribe subscribe : noEmptySubscribe) {
			noEmptySubscribeCarSpaceIds.add(subscribe.getCarSpaceId());
		}
		mongoOps.updateMulti(new Query(Criteria.where("_id").in(noEmptySubscribeCarSpaceIds).and("isEmpty").is(1)),
				Update.update("isEmpty", 0), CarSpace.class);*/
	}

	/**
	 * 更新车位列表为非空
	 * 
	 * @param noEmptyCarSpaceIds
	 */
	public void updateNoEmptyCarSpace(List<Integer> noEmptyCarSpaceIds) {
		/*mongoOps.updateMulti(new Query(Criteria.where("_id").in(noEmptyCarSpaceIds).and("isEmpty").is(1)),
				Update.update("isEmpty", 0), CarSpace.class);*/
	}

	/**
	 * 更新车位列表为空
	 * 
	 * @param emptyCarSpaceIds
	 */
	public void updateEmptyCarSpace(List<Integer> emptyCarSpaceIds) {
		/*// 更新空车位
		mongoOps.updateMulti(new Query(Criteria.where("_id").in(emptyCarSpaceIds).and("isEmpty").is(0)),
				Update.update("isEmpty", 1), CarSpace.class);*/
	}

	/**
	 * 根据分享列表和时间段所属的星期设置车位是否为空列表的值
	 * 
	 * @param startDateTime
	 * @param endDateTime
	 * @param shares
	 * @param emptyCarSpaceIds
	 * @param noEmptyCarSpaceIds
	 */
	/*public void setCarSpaceId(Date startDateTime, Date endDateTime, List<Share> shares, List<Integer> emptyCarSpaceIds,
			List<Integer> noEmptyCarSpaceIds) {
		// 将时间转换成周
		String startWeek = DateUtil.getConvertDateToString(startDateTime, "EEEE");
		String endWeek = DateUtil.getConvertDateToString(endDateTime, "EEEE");

		// 迭代器遍历集合
		Iterator<Share> it = shares.iterator();
		while (it.hasNext()) {
			Share share = it.next();

			// 将不在星期内的对象放入非空车位集合
			String[] weeks = share.getWeek().split("、");
			boolean flag = false;
			for (String week : weeks) {
				if (week.equals(startWeek)) {
					flag = false;
					break;
				}
				if (week.equals(endWeek)) {
					flag = false;
					break;
				}
				flag = true;
			}
			if (flag) {
				noEmptyCarSpaceIds.add(share.getCarSpaceId());
				continue;
			}

			// 将满足条件的对象放入空车位集合
			emptyCarSpaceIds.add(share.getCarSpaceId());
		}
	}*/

	/**
	 * 根据时间段和状态获取分享对象列表
	 * 
	 * @param status
	 * @param startDateTime
	 * @param endDateTime
	 * @return
	 */
	/*public List<Share> getSharesStatus(int status, Date startDateTime, Date endDateTime) {
		Date startTime = DateUtil.datetimeInterceptionTime(startDateTime);
		Date startDate = DateUtil.datetimeInterceptionDate(startDateTime);
		Date endTime = DateUtil.datetimeInterceptionTime(endDateTime);
		Date endDate = DateUtil.datetimeInterceptionDate(endDateTime);

		Criteria criteria = Criteria.where("status").is(status).and("startTime").lte(startTime).and("endTime")
				.gte(endTime).and("startDate").lte(startDate).and("endDate").gte(endDate);
		return mongoOps.find(new Query(criteria), Share.class);
		return null;
	}*/

	@Override
	public CarSpace getByCarLockId(String lockId) {
		CarSpaceExample e=new CarSpaceExample();
		e.createCriteria().andSlaveIdEqualTo(lockId);
		List<CarSpace> list = mapper.selectByExample(e);
		if(list!=null && !list.isEmpty()){
			return list.get(0);
		}
		
		return null;
	}
	
	@Override
	public int count(Integer carUserId) {
		CarUserRelCarSpaceExample carUserRelCarSpaceExample = new CarUserRelCarSpaceExample();
		carUserRelCarSpaceExample.createCriteria().andCarUserIdEqualTo(carUserId);
		return userRelSpaceMapper.countByExample(carUserRelCarSpaceExample);
	}
	@Override
	public List<CarSpace> getAll() {
		List<CarSpace> list = mapper.selectByExample(null);
		return list;
	}
	@Override
	public  CarSpace getLockId(String name,Integer parkId){
		CarSpaceExample example = new CarSpaceExample();
		example.createCriteria().andParkIdEqualTo(parkId).andSpaceNoEqualTo(name);
		List<CarSpace> list =  mapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	@Override
	public List<CarSpace> getByStatus(int status) {
		CarSpaceExample example = new CarSpaceExample();
//		example.createCriteria().andStatusEqualTo(status);
		List<CarSpace> carSpaces = mapper.selectByExample(example);
		
		return carSpaces;
	}


	@Override
	public List<CarSpace> findSlaveIdsByParkId(Integer parkId) {
		CarSpaceExample carSpaceExample = new CarSpaceExample();
		carSpaceExample.createCriteria().andParkIdEqualTo(parkId);
		List<CarSpace> list =  mapper.selectByExample(carSpaceExample);
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	@Override
	public CarSpace totalCountOfSpace(String slaveId) {
		CarSpaceExample carSpaceExample = new CarSpaceExample();
		carSpaceExample.createCriteria().andSlaveIdEqualTo(slaveId);
		List<CarSpace> lists = mapper.selectByExample(carSpaceExample);
		if(lists != null && !lists.isEmpty()){
			int spaceCount = 0;
			CarSpace carSpaceCount = new CarSpace();
			for(CarSpace carSpace : lists){
//				spaceCount+=carSpace.getSpaceCount();
			}
//			carSpaceCount.setSpaceCount(spaceCount);
			return carSpaceCount;
		}
		return null;
	}


	@Override
	public CarSpace findBySacode(String code, String slaveId) {
		CarSpaceExample example = new CarSpaceExample();
//		example.createCriteria().andCodeEqualTo(code).andSlaveIdEqualTo(slaveId);
		List<CarSpace> carSpaces =mapper.selectByExample(example);
		if(carSpaces!=null&&carSpaces.size()!=0){
			return carSpaces.get(0);
		}
		return null;
	}

	@Override
	public void update(CarSpace space, String code, String slaveId) {
		CarSpaceExample example = new CarSpaceExample();
//		example.createCriteria().andCodeEqualTo(code).andSlaveIdEqualTo(slaveId);
		mapper.updateByExampleSelective(space, example);
	}

	@Override
	public List<CarSpace> findByParkId(Integer parkId) {
		CarSpaceExample example = new CarSpaceExample();
		example.createCriteria().andParkIdEqualTo(parkId);
		List<CarSpace> lists = mapper.selectByExample(example);
		if(lists != null && !lists.isEmpty()){
			return lists;
		}
		return null;
	}

	@Override
	public Integer countByStatus(int status) {
		CarSpaceExample e = new CarSpaceExample();
		e.createCriteria().andIsEmptyEqualTo(status);
		return mapper.countByExample(e);
	}

	@Override
	public Map<String, Object> getList(int page, int rows, String search,
			String sort, String order, List<Integer> parkId) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 明细控制，得到数据列表 li
	 */
	@Override
	public Map<String, Object> getDetailData(int page, int rows, String search,
			String sort, String order) {
		
		CarSpaceExample e = new CarSpaceExample();
		Criteria criteria = e.createCriteria();
		criteria.andSlaveIdNotEqualTo("0");//等于0，表示此车位未关联车锁。我们只查询关联车锁的车位
		
		if(search!=null){
			criteria.andSpaceNoLikeOrSlaveIdLike(search);
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		Pagination<?> pagin = PageContext.initialize(page, rows);
		List<CarSpace> css = mapper.selectByExample(e);
		List<CarSpace> list = new ArrayList<CarSpace>();
		if(css!=null && !css.isEmpty()){
			for(CarSpace cs:css){
				CarSpace c=new CarSpace();
				CarLock lock = carLockBiz.getBySlaveid(cs.getSlaveId());
				c.setSpaceNo(cs.getSpaceNo());
				c.setSlaveId(cs.getSlaveId());
				if(lock!=null){
					c.setCarlockStatus(lock.getSwitchStatus()==1?"平躺":"竖起");
					c.setVoltage(lock.getVoltage());
					c.setGatewayNo(lock.getGatewayNo());
					Gateway gateway = gatewayBiz.getByDasId(lock.getGatewayNo());
					if(gateway!=null){
						c.setGatewayStatus(gateway.getGatewayStatus()==1?"离线":"在线");
					}
				}
				
				list.add(c);
			}
		}
		
		
		map.put("rows", list);
		map.put("total", pagin.getTotalRows());
		return map;
	}

	@Override
	public List<CarSpace> findNOBindByParkIds(List<Integer> parkIds, String slaveId) {
		CarSpaceExample example = new CarSpaceExample();
		example.createCriteria().andParkIdIn(parkIds).andSlaveIdEqualTo(slaveId);
		List<CarSpace> list = mapper.selectByExample(example);
		if(list != null && !list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public List<CarSpace> findAll(List<Integer> parkIds) {
		CarSpaceExample example = new CarSpaceExample();
		example.createCriteria().andParkIdIn(parkIds);
		List<CarSpace> list = mapper.selectByExample(example);
		if(list != null && !list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public CarSpace findBySpaceName(String spaceName) {
		CarSpaceExample example = new CarSpaceExample();
		example.createCriteria().andSpaceNameEqualTo(spaceName);
		List<CarSpace> list = mapper.selectByExample(example);
		if(list != null && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	

}