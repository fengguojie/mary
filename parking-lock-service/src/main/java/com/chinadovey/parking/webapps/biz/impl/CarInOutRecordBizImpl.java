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
import com.chinadovey.parking.webapps.biz.statistics.CarInOutRecordBiz;
import com.chinadovey.parking.webapps.mappers.gen.CarInOutRecordMapper;
import com.chinadovey.parking.webapps.pojo.CarInOutRecord;
import com.chinadovey.parking.webapps.pojo.CarInOutRecordExample;
import com.chinadovey.parking.webapps.utils.DateUtil;
import com.chinadovey.parking.webapps.utils.StringUtil;

@Service
public class CarInOutRecordBizImpl implements CarInOutRecordBiz {

	@Autowired
	private CarInOutRecordMapper carInOutRecordMapper;


	private final static Date FIXED_DATE = DateUtil.stringConvertDate("2000-01-01 00:00:00", 3);

	@Override
	public Map<String, Object> getList(int page, int rows, String sort, String order, String search, String start,
			String end, Integer cardType) {
		return getList(page, rows, sort, order, null, search, start, end, cardType);
	}

	@Override
	public Map<String, Object> getList(int page, int rows, Integer parkId, String search, String start, String end) {
		List<Integer> parkIds = new ArrayList<Integer>();
		parkIds.add(parkId);
		if (parkId == null || parkId == 0) {
			parkIds = null;
		}
		String timeStr = "inTime";
		return getList(page, rows, timeStr, "desc", parkIds, search, start, end, 0);
	}

	@Override
	public Map<String, Object> getList(Integer page, Integer rows, Integer parkId, String search, String start,
			String end, Integer timeType) {
		List<Integer> parkIds = new ArrayList<Integer>();
		parkIds.add(parkId);
		if (parkId == null || parkId == 0) {
			parkIds = null;
		}
		String timeStr = "inTime";
		if (timeType == null) {
			timeType = 1;
		}
		if (2 == timeType) {
			timeStr = "outTime";
		} else {
			timeStr = "inTime";
		}
		return getList(page, rows, timeStr, "desc", parkIds, search, start, end, timeType, 0);
	}

	@Override
	public Map<String, Object> getList(int page, int rows, String sort, String order, List<Integer> parkIds,
			String search, String start, String end, Integer cardType) {
		return getList(page, rows, sort, order, parkIds, search, start, end, 1, cardType);
	}

	@Override
	public Map<String, Object> getList(int page, int rows, String sort, String order, List<Integer> parkIds,
			String search, String start, String end, Integer timeType, Integer cardType) {
		CarInOutRecordExample carInOutRecordExample = new CarInOutRecordExample();

		// 进行分页的设置
		if (page <= 0 && rows <= 0) {
			page = 1;
			rows = 10;
		}

		// 进行排序
		if (sort != null && !sort.isEmpty()) {
			carInOutRecordExample.setOrderByClause(StringUtil.changeOrderStr(sort) + " " + order);
		}

		CarInOutRecordExample.Criteria criteria1 = carInOutRecordExample.createCriteria();

		if (parkIds != null && parkIds.size() > 0) {
			criteria1.andParkIdIn(parkIds);
			// 设置时间段
		}
		if (start != null && !start.isEmpty() && end != null && !end.isEmpty()) {
			Date startTime = DateUtil.stringConvertDate(start + ":00", 3);
			Date endTime = DateUtil.stringConvertDate(end + ":59", 3);
			if (null != timeType && 0 != timeType) {
				if (2 == timeType) {
					criteria1.andOutTimeBetween(startTime, endTime);
				} else if(1 == timeType) {
					criteria1.andInTimeBetween(startTime, endTime);
				}
			} else {
				criteria1.andInTimeBetween(startTime, endTime);
			}
		}
		if (search != null && !search.isEmpty()) {
			criteria1.andCarNoLike("%" + search + "%");
		}

		if (cardType != null && 0 != cardType) {
			String cardTypeStr = getCardTypeStr(cardType);
			criteria1.andCardTypeLike(cardTypeStr + "%");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		Pagination<?> pagin = PageContext.initialize(page, rows);
		List<CarInOutRecord> list = carInOutRecordMapper.selectByExample(carInOutRecordExample, pagin.getRowBounds());

		map.put("rows", list);
		map.put("pages", pagin.getTotalPages());
		map.put("total", pagin.getTotalRows());
		return map;

	}

	@Override
	public CarInOutRecord find(String id) {
		return carInOutRecordMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<CarInOutRecord> getNoOutCarInOutRecord(Integer parkId) {
		CarInOutRecordExample carInOutRecordExample = new CarInOutRecordExample();
		carInOutRecordExample.createCriteria().andParkIdEqualTo(parkId).andInTimeEqualToOutTime();
		List<CarInOutRecord> carInOutRecords = carInOutRecordMapper.selectByExample(carInOutRecordExample);
		return carInOutRecords;
	}

	@Override
	public List<CarInOutRecord> getCarInOutRecordByInTime(CarInOutRecord newestCarInOutRecord, Integer parkId) {
		List<CarInOutRecord> carInOutRecords = new ArrayList<CarInOutRecord>();
		/*if (null == newestCarInOutRecord) {
			Query query = new Query(Criteria.where("parkId").is(parkId).and("outTime").is(FIXED_DATE));
			query.with(new Sort("inTime")).limit(100);
			carInOutRecords = mongoOps.find(query, CarInOutRecord.class);
		} else {
			carInOutRecords = mongoOps.find(new Query(Criteria.where("parkId").is(parkId).and("inTime")
					.gt(DateUtil.getNeedDate(newestCarInOutRecord.getInTime(), "ms", 900)).and("outTime")
					.is(FIXED_DATE)).with(new Sort("inTime")).limit(100), CarInOutRecord.class);
		}
		return carInOutRecords;*/
		return null;
	}

	@Override
	public List<CarInOutRecord> getCarInOutRecordsByOutTime(Date newestCarOutRecordDate, Integer parkId) {
		/*List<CarInOutRecord> carInOutRecords = mongoOps
				.find(new Query(Criteria.where("outTime").gt(DateUtil.getNeedDate(newestCarOutRecordDate, "ms", 900))
						.and("parkId").is(parkId)).with(new Sort("outTime")).limit(500), CarInOutRecord.class);
		return carInOutRecords;*/
		return null;
	}

	@Override
	public List<CarInOutRecord> getCarInOutRecordsByOutTime(Date newestCheckDate, Date mongoNewestCheckDate,
			Integer parkId) {
		/*List<CarInOutRecord> carInOutRecords = mongoOps.find(new Query(
				Criteria.where("outTime").gt(newestCheckDate).lt(mongoNewestCheckDate).and("parkId").is(parkId))
						.with(new Sort("outTime")).limit(500),
				CarInOutRecord.class);
		return carInOutRecords;*/
		return null;
	}

	@Override
	public CarInOutRecord getNewestCarInOutRecordByInTime(Integer parkId) {
		CarInOutRecordExample carInOutRecordExample = new CarInOutRecordExample();
		carInOutRecordExample.createCriteria().andParkIdEqualTo(parkId).andOutTimeEqualTo(FIXED_DATE);
		carInOutRecordExample.setOrderByClause("in_time desc limit 1");
		List<CarInOutRecord> carInOutRecords = carInOutRecordMapper.selectByExample(carInOutRecordExample);
		if (carInOutRecords != null && !carInOutRecords.isEmpty()) {
			return carInOutRecords.get(0);
		}
		return null;
	}

	@Override
	public CarInOutRecord getNewestCarInOutRecordByOutTime(Integer parkId) {
		CarInOutRecordExample carInOutRecordExample = new CarInOutRecordExample();
		carInOutRecordExample.createCriteria().andParkIdEqualTo(parkId).andOutTimeGreaterThanInTime();
		carInOutRecordExample.setOrderByClause("out_time desc limit 1");
		List<CarInOutRecord> carInOutRecords = carInOutRecordMapper.selectByExample(carInOutRecordExample);
		if (carInOutRecords != null && !carInOutRecords.isEmpty()) {
			return carInOutRecords.get(0);
		}
		return null;
	}

	@Override
	public void insertCarInOutRecords(List<CarInOutRecord> carInOutRecords) {
		if (carInOutRecords.isEmpty()) {
			return;
		}
		carInOutRecordMapper.insertSelectiveBatch(carInOutRecords);
	}

	@Override
	public void updateCarInOutRecords(List<CarInOutRecord> carInOutRecords) {
		if (carInOutRecords.isEmpty()) {
			return;
		}
		for (CarInOutRecord carInOutRecord : carInOutRecords) {
			if (isExist(carInOutRecord)) {
				carInOutRecordMapper.updateByPrimaryKeySelective(carInOutRecord);
			} else {
				carInOutRecordMapper.insertSelective(carInOutRecord);
			}
		}
	}

	@Override
	public Date checkCarInOutRecords(List<CarInOutRecord> carInOutRecords, Date newestDate) {
		if (newestDate == null) {
			newestDate = FIXED_DATE;
		}
		for (CarInOutRecord carInOutRecord : carInOutRecords) {
			if (!isExist(carInOutRecord)) {
				carInOutRecordMapper.insertSelective(carInOutRecord);
			}
			if (newestDate.before(carInOutRecord.getOutTime())) {
				newestDate = carInOutRecord.getOutTime();
			}
		}
		return newestDate;
	}

	@Override
	public boolean isExist(CarInOutRecord carInOutRecord) {
		CarInOutRecord carInOutRecord2 = carInOutRecordMapper.selectByPrimaryKey(carInOutRecord.getId());
		if (carInOutRecord2 == null) {
			return false;
		} else {
			return true;
		}

	}

	@Override
	public void updateNotOutCarInOutRecords(List<CarInOutRecord> carInOutRecords) {
		if (carInOutRecords.isEmpty()) {
			return;
		}
		for (CarInOutRecord carInOutRecord : carInOutRecords) {
			CarInOutRecord carInOutRecordMongodb = findByMongodb(carInOutRecord.getId());
			if (carInOutRecordMongodb.getOutTime().after(carInOutRecord.getOutTime())) {
				carInOutRecordMapper.updateByPrimaryKeySelective(carInOutRecord);
			}
		}
	}

	@Override
	public CarInOutRecord findByMongodb(String id) {
		/*return mongoOps.findOne(new Query(Criteria.where("_id").is(id)), CarInOutRecord.class);*/
		return null;
	}

	@Override
	public CarInOutRecord findByCarNoAndOutTimeNull(String carNo) {

		CarInOutRecordExample example = new CarInOutRecordExample();
		example.createCriteria().andCardNoEqualTo(carNo).andOutTimeIsNotNull();

		List<CarInOutRecord> clist = carInOutRecordMapper.selectByExample(example);
		if (clist != null && !clist.isEmpty()) {
			return clist.get(0);
		}
		return null;
	}

	@Override
	public List<CarInOutRecord> getList(int page, Integer rows, List<Integer> parkIds, Date startTime, Date endTime) {
		CarInOutRecordExample carInOutRecordExample = new CarInOutRecordExample();

		// 进行分页的设置
		if (page <= 0 && rows <= 0) {
			page = 1;
			rows = 10;
		}

		// 进行排序
		carInOutRecordExample.setOrderByClause("in_time desc");

		CarInOutRecordExample.Criteria criteria1 = carInOutRecordExample.createCriteria();
		if (parkIds != null && parkIds.size() > 0) {
			criteria1.andParkIdIn(parkIds);
			// 设置时间段
		}
		criteria1.andInTimeBetween(startTime, endTime);

		Pagination<?> pagin = PageContext.initialize(page, rows);
		List<CarInOutRecord> list = carInOutRecordMapper.selectByExample(carInOutRecordExample, pagin.getRowBounds());
		return list;
	}

	@Override
	public String getCardTypeStr(Integer cardType) {
		if (0 == cardType) {
			return "";
		} else if (1 == cardType) {
			return "月租";
		} else if (2 == cardType) {
			return "月临";
		} else if (3 == cardType) {
			return "临时";
		} else if (40 == cardType) {
			return "临免";
		} else if (4 == cardType) {
			return "免费";
		} else {
			return "";
		}
	}
	
	@Override
	public Integer countOutRecord(Date startDate, Date endDate, List<Integer> parkIds) {
		CarInOutRecordExample example = new CarInOutRecordExample();
		example.createCriteria().andParkIdIn(parkIds).andOutTimeBetween(startDate, endDate);
		return carInOutRecordMapper.countByExample(example);
	}
	
	@Override
	public Integer countParkingSpan(Date startDate, Date endDate, List<Integer> parkIds) {
		CarInOutRecordExample example = new CarInOutRecordExample();
		example.createCriteria().andParkIdIn(parkIds).andOutTimeBetween(startDate, endDate);
		return carInOutRecordMapper.sumParkingSpanByExample(example);
	}

	public String getOldCardTypeStr(Integer cardType) {
		if (0 == cardType) {
			return "";
		} else if (1 == cardType) {
			return "月租";
		} else if (2 == cardType) {
			return "月临";
		} else if (3 == cardType) {
			return "临时";
		} else if (40 == cardType) {
			return "临免";
		} else if (4 == cardType) {
			return "免费";
		} else {
			return "";
		}
	}

	@Override
	public Integer countOutRecord(Date startDate, Date endDate, Integer parkId) {
		CarInOutRecordExample example = new CarInOutRecordExample();
		example.createCriteria().andParkIdEqualTo(parkId).andOutTimeBetween(startDate, endDate);
		return carInOutRecordMapper.countByExample(example);
	}
	@Override
	public Integer countInRecord(Date startDate, Date endDate, Integer parkId) {
		CarInOutRecordExample example = new CarInOutRecordExample();
		example.createCriteria().andParkIdEqualTo(parkId).andInTimeBetween(startDate, endDate);
		return carInOutRecordMapper.countByExample(example);
	}
	@Override
	public Integer countParkingSpan(Date startDate, Date endDate, Integer parkId) {
		CarInOutRecordExample example = new CarInOutRecordExample();
		example.createCriteria().andParkIdEqualTo(parkId).andOutTimeBetween(startDate, endDate).andInTimeBetween(startDate, endDate);
		return carInOutRecordMapper.sumParkingSpanByExample(example);
	}

	@Override
	public CarInOutRecord findByParam(Integer parkId, Date inTime, String carNo) {
		CarInOutRecordExample example = new CarInOutRecordExample();
		//String out = "2000-01-01 00:00:00";
		//Date outTime = DateUtil.stringConvertDate(out, 3);
		example.createCriteria().andCarNoEqualTo(carNo).andParkIdEqualTo(parkId).andInTimeEqualTo(inTime);
		List<CarInOutRecord> clist = carInOutRecordMapper.selectByExample(example);
		if (clist != null && !clist.isEmpty()) {
			if (clist.size() != 0) {
				return clist.get(0);
			}
		}
		return null;
	}
	
	@Override
	public boolean isExist(String carNo, Date inTime , int parkId) {
	    CarInOutRecordExample example = new CarInOutRecordExample();
	    example.createCriteria().andCarNoEqualTo(carNo).andInTimeEqualTo(inTime).andParkIdEqualTo(parkId);
	    int count = carInOutRecordMapper.countByExample(example);
		if(count>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public CarInOutRecord find(String carNo, Date inTime  , int parkId) {
		 CarInOutRecordExample example = new CarInOutRecordExample();
		 example.createCriteria().andCarNoEqualTo(carNo).andInTimeEqualTo(inTime).andParkIdEqualTo(parkId);
		 List<CarInOutRecord> list = carInOutRecordMapper.selectByExample(example);
		 if(list!=null && !list.isEmpty()){
			 return list.get(0);
		 }
		return null;
	}

	@Override
	public void save(CarInOutRecord carInOut) {
		carInOutRecordMapper.insert(carInOut);
	}

	@Override
	public void update(CarInOutRecord carInOut) {
		carInOutRecordMapper.updateByPrimaryKeySelective(carInOut);
	}

	@Override
	public void saveAndUpdateStatistics(CarInOutRecord carInOut) {
		carInOutRecordMapper.insert(carInOut);
		int parkId = carInOut.getParkId();
		Date time = carInOut.getInTime();
		
/*		Date hourDate = DateUtil.getDateByType(time, 1);
		carInOutCountBiz.updateHoursCount(hourDate, parkId, "1");
		
		Date dayDate = DateUtil.getDateByType(time, 2);
		carInOutCountBiz.updateDayCount(dayDate, parkId, "1");
		
		Date monthDate = DateUtil.getDateByType(time, 3);
		carInOutCountBiz.updateMonthCount(monthDate, parkId, "1");
		
		Date yearDate = DateUtil.getDateByType(time, 4);
		carInOutCountBiz.updateYearCount(yearDate, parkId, "1");
*/		
	}

	@Override
	public void updateAndUpdateStatistics(List<CarInOutRecord> carInOutRecords) {
		if (carInOutRecords.isEmpty()) {
			return;
		}
		/*for (CarInOutRecord carInOutRecord : carInOutRecords) {
			Date time = carInOutRecord.getInTime();
			if (isExist(carInOutRecord)) {
				carInOutRecordMapper.updateByPrimaryKeySelective(carInOutRecord);
				Date hourDate = DateUtil.getDateByType(time, 1);
				carInOutCountBiz.updateHoursCount(hourDate, carInOutRecord.getParkId(), "2");
				
				Date dayDate = DateUtil.getDateByType(time, 2);
				carInOutCountBiz.updateDayCount(dayDate, carInOutRecord.getParkId(), "2");
				
				Date monthDate = DateUtil.getDateByType(time, 3);
				carInOutCountBiz.updateMonthCount(monthDate, carInOutRecord.getParkId(), "2");
				
				Date yearDate = DateUtil.getDateByType(time, 4);
				carInOutCountBiz.updateYearCount(yearDate, carInOutRecord.getParkId(), "2");
				
			} else {
				carInOutRecordMapper.insertSelective(carInOutRecord);
				
				Date hourDate = DateUtil.getDateByType(time, 1);
				carInOutCountBiz.updateHoursCount(hourDate, carInOutRecord.getParkId(), "3");
				
				Date dayDate = DateUtil.getDateByType(time, 2);
				carInOutCountBiz.updateDayCount(dayDate, carInOutRecord.getParkId(), "3");
				
				Date monthDate = DateUtil.getDateByType(time, 3);
				carInOutCountBiz.updateMonthCount(monthDate, carInOutRecord.getParkId(), "3");
				
				Date yearDate = DateUtil.getDateByType(time, 4);
				carInOutCountBiz.updateYearCount(yearDate, carInOutRecord.getParkId(), "3");
			}
		}*/
		
	}

	@Override
	public List<CarInOutRecord> getListByInTime(Date startTime, Date endTime) {
		CarInOutRecordExample example = new CarInOutRecordExample();
		//String out = "2000-01-01 00:00:00";
		//Date outTime = DateUtil.stringConvertDate(out, 3);
		example.createCriteria().andInTimeBetween(startTime, endTime);
		List<CarInOutRecord> clist = carInOutRecordMapper.selectByExample(example);
		return clist;
	}
	@Override
	public List<CarInOutRecord> getListByInTimeAndParkId(Date startTime, Date endTime,int parkId) {
		CarInOutRecordExample example = new CarInOutRecordExample();
		//String out = "2000-01-01 00:00:00";
		//Date outTime = DateUtil.stringConvertDate(out, 3);
		example.createCriteria().andInTimeBetween(startTime, endTime).andParkIdEqualTo(parkId);
		List<CarInOutRecord> clist = carInOutRecordMapper.selectByExample(example);
		return clist;
	}
	@Override
	public List<CarInOutRecord> getListByOutTime(Date startTime, Date endTime) {
		CarInOutRecordExample example = new CarInOutRecordExample();
		example.createCriteria().andOutTimeBetween(startTime, endTime);
		List<CarInOutRecord> clist = carInOutRecordMapper.selectByExample(example);
		return clist;
	}
	@Override
	public CarInOutRecord getLastOne(Integer parkId) {
		CarInOutRecordExample example = new CarInOutRecordExample();
		example.createCriteria().andParkIdEqualTo(parkId);
		example.setOrderByClause("out_time desc limit 1");
		List<CarInOutRecord> clist = carInOutRecordMapper.selectByExample(example);
		if(clist.size()>0){
			return clist.get(0);
		}
		return null;
	}
	@Override
	public CarInOutRecord delect(CarInOutRecord carInOutCharge) {
		CarInOutRecordExample carInOutChargeExample = new CarInOutRecordExample();
		CarInOutRecordExample.Criteria criteria1 = carInOutChargeExample.createCriteria();
		criteria1.andCarNoEqualTo(carInOutCharge.getCarNo());
		Integer parkId=carInOutCharge.getParkId();
		if (parkId != null) {
			criteria1.andParkIdEqualTo(parkId);
		}
		criteria1.andInTimeEqualTo(carInOutCharge.getInTime());
		carInOutRecordMapper.deleteByExample(carInOutChargeExample);
		return null;
	}

	@Override
	public CarInOutRecord find(CarInOutRecord carInOutCharge) {
		// TODO Auto-generated method stub
		CarInOutRecordExample carInOutChargeExample = new CarInOutRecordExample();
		CarInOutRecordExample.Criteria criteria1 = carInOutChargeExample.createCriteria();
		criteria1.andCarNoEqualTo(carInOutCharge.getCarNo());
		Integer parkId=carInOutCharge.getParkId();
		if (parkId != null) {
			criteria1.andParkIdEqualTo(parkId);
		}
		criteria1.andInTimeEqualTo(carInOutCharge.getInTime());
		carInOutCharge=carInOutRecordMapper.selectByChageExample(carInOutChargeExample);
	     System.err.println(carInOutCharge);
		return carInOutCharge;
	}
}
