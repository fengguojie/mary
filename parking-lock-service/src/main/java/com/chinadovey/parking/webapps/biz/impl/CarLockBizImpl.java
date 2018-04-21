package com.chinadovey.parking.webapps.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinadovey.parking.core.supports.mybatis.pagination.PageContext;
import com.chinadovey.parking.core.supports.mybatis.pagination.Pagination;
import com.chinadovey.parking.webapps.biz.CarLockBiz;
import com.chinadovey.parking.webapps.biz.baseInfo.CarSpaceBiz;
import com.chinadovey.parking.webapps.mappers.gen.CarLockMapper;
import com.chinadovey.parking.webapps.mappers.gen.CarSpaceMapper;
import com.chinadovey.parking.webapps.pojo.CarLock;
import com.chinadovey.parking.webapps.pojo.CarLockExample;
import com.chinadovey.parking.webapps.pojo.CarSpace;
import com.chinadovey.parking.webapps.pojo.CarSpaceExample;
import com.chinadovey.parking.webapps.utils.StringUtil;
@Service
public class CarLockBizImpl  implements CarLockBiz{

	@Autowired
	private CarLockMapper mapper;
	@Autowired
	private CarSpaceBiz carSpaceBiz;
	
	@Override
	public CarLock getBySlaveid(String slaveId) {
		CarLockExample example = new CarLockExample();
		example.createCriteria().andSlaveIdEqualTo(slaveId);
		List<CarLock> list = mapper.selectByExample(example);
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
	@Override
	public boolean isSlaveIdExit(String slaveId) {
		CarLockExample example = new CarLockExample();
		example.createCriteria().andSlaveIdEqualTo(slaveId);
		return mapper.countByExample(example) > 0;
	}
	@Override
	public boolean isSlaveIdExitById(String slaveId, int id) {
		if (mapper.selectByPrimaryKey(id).getSlaveId().equals(slaveId)) {
			return false;
		}
		return isSlaveIdExit(slaveId);
	}
	@Override
	public Map<String, Object> getList(int page, int rows, String search, String sort, String order,Integer isauto,String companyNo) {
		CarLockExample example = new CarLockExample();
		// 进行分页
		if (page <= 0 || rows <= 0) {
			page = 0;
			rows = 10;
		}
		// 进行排序
		if (sort != null && !sort.isEmpty()) {
			example.setOrderByClause(StringUtil.changeOrderStr(sort) + " " + order);
		}

		CarLockExample.Criteria criteria = example.createCriteria();

		
		if (companyNo != null ) {
			criteria.andCompanyNoEqualTo(companyNo);
		}

		// 进行模糊查询
		if (search != null && !search.isEmpty()) {
			criteria.andSlaveIdLike("%" + search + "%");
		}
		
		if (isauto != null) {
			criteria.andIsautoEqualTo(isauto);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		Pagination<?> pagin = PageContext.initialize(page, rows);
		List<CarLock> list = mapper.selectByExample(example, pagin.getRowBounds());
		map.put("rows", list);
		map.put("total", pagin.getTotalRows());
		return map;
	}
	@Override
	public CarLock update(String slaveId) {
		CarLockExample example = new CarLockExample();
		example.createCriteria().andSlaveIdEqualTo(slaveId);
		CarLock lock=  new CarLock();
		lock.setRunStatus(2);
		mapper.updateByExampleSelective(lock, example);
		return null;
	}
	@Override
	public Integer getAll(List<String> dasIds) {
		CarLockExample example = new CarLockExample();
		example.createCriteria().andGatewayNoIn(dasIds);
		return mapper.countByExample(example);
	}
	@Override
	public Integer countByStatus(Integer status) {
		CarLockExample example = new CarLockExample();
		example.createCriteria().andRunStatusEqualTo(status);
		return mapper.countByExample(example);
	}
	@Override
	public Integer countByCarStatus(Integer status) {
		CarLockExample example = new CarLockExample();
		example.createCriteria().andCarStatusEqualTo(status);
		return mapper.countByExample(example);
	}
	@Override
	public List<CarLock> getAll(Integer status) {
		CarLockExample example = new CarLockExample();
		example.createCriteria().andCarStatusEqualTo(status);
		return mapper.selectByExample(example);
	}
	@Override
	public List<CarLock> getAllByIds(List<Integer> ids) {
		CarLockExample example = new CarLockExample();
		example.createCriteria().andIdIn(ids);
		List<CarLock> list = mapper.selectByExample(example);
		if (list != null && !list.isEmpty()) {
			return list;
		}
		return null;
	}
	@Override
	public List<CarLock> getAllBySlaveIds(List<String> ids) {
		CarLockExample example = new CarLockExample();
		example.createCriteria().andSlaveIdIn(ids);
		List<CarLock> list = mapper.selectByExample(example);
		if (list != null && !list.isEmpty()) {
			return list;
		}
		return null;
	}
	@Override
	public Integer countBySwitchStatus(int status) {
		CarLockExample example = new CarLockExample();
		example.createCriteria().andSwitchStatusEqualTo(status);
		return mapper.countByExample(example);
	}
	@Override
	public List<CarLock> getAll() {
		List<CarLock> list = mapper.selectByExample(null);
		return list;
	}
	@Override
	public void update(CarLock carLock) {
		mapper.updateByPrimaryKeySelective(carLock);
	}
	@Override
	public List<CarLock> findByParkId(int parkId, int isBind) {
		CarLockExample example = new CarLockExample();
		example.createCriteria().andParkIdEqualTo(parkId).andIsBindEqualTo(isBind);
		List<CarLock> list = mapper.selectByExample(example);
		if (list != null && !list.isEmpty()) {
			return list;
		}
		return null;
	}
	@Override
	public List<CarLock> findAllNOBind(int isBind) {
		CarLockExample example = new CarLockExample();
		example.createCriteria().andIsBindEqualTo(isBind);
		List<CarLock> list = mapper.selectByExample(example);
		if (list != null && !list.isEmpty()) {
			return list;
		}
		return null;
	}
	@Override
	public Map<String, Object> getList(List<Integer> parkIds, int page, int rows, String sort, String order, String search) {
		CarLockExample example = new CarLockExample();
		// 进行分页
		if (page <= 0 || rows <= 0) {
			page = 0;
			rows = 10;
		}
		// 进行排序
		if (sort != null && !sort.isEmpty()) {
			example.setOrderByClause(StringUtil.changeOrderStr(sort) + " " + order);
		}
		CarLockExample.Criteria criteria = example.createCriteria();
		//查询parkId
		if (parkIds != null && !parkIds.isEmpty()) {
			criteria.andParkIdIn(parkIds);
		}
		//根据车锁编号进行模糊查询
		if (search != null && !search.isEmpty()) {
			criteria.andSlaveIdLike("%" + search + "%");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		Pagination<?> pagin = PageContext.initialize(page, rows);
		List<CarLock> list = mapper.selectByExample(example, pagin.getRowBounds());
		for(CarLock lock:list){ //赋予，车锁对应的车位名称
			CarSpace space = carSpaceBiz.getByCarLockId(lock.getSlaveId());
			if(space!=null){
				lock.setSpaceName(space.getSpaceName());
			}
		}
		map.put("rows", list);
		map.put("total", pagin.getTotalRows());
		return map;
	}
	@Override
	public List<CarLock> findByParkIds(List<Integer> parkIds) {
		CarLockExample example = new CarLockExample();
		example.createCriteria().andParkIdIn(parkIds);
		List<CarLock> list = mapper.selectByExample(example);
		if(list != null && !list.isEmpty()){
			return list;
		}
		return null;
	}
	@Override
	public List<CarLock> findByParkId(Integer parkId) {
		CarLockExample example = new CarLockExample();
		example.createCriteria().andParkIdEqualTo(parkId);
		List<CarLock> list = mapper.selectByExample(example);
		if(list != null && !list.isEmpty()){
			return list;
		}
		return null;
	}
	@Override
	public void save(CarLock carLock) {
		mapper.insertSelective(carLock);
	}
	@Override
	public void delete(Integer id) {
		mapper.deleteByPrimaryKey(id);
	}
	@Override
	public CarLock find(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}
	@Override
	public List<String> getOfflineLock(HashSet<String> slaveIds) {
		CarLockExample e = new CarLockExample();
		
		List<String> ids=new ArrayList<String>();
		for(String s:slaveIds){
			ids.add(s);
		}
		if(ids!=null && !ids.isEmpty()){
			e.createCriteria().andSlaveIdNotIn(ids);
		}
		List<CarLock> list = mapper.selectByExample(e);//离线车锁
		List<String> temp=new ArrayList<String>();
		for(CarLock c:list){
			temp.add(c.getSlaveId());
		}
		return temp;
	}
}
