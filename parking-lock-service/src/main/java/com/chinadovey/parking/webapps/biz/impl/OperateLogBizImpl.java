package com.chinadovey.parking.webapps.biz.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinadovey.parking.core.supports.mybatis.pagination.PageContext;
import com.chinadovey.parking.core.supports.mybatis.pagination.Pagination;
import com.chinadovey.parking.webapps.biz.logManage.OperateLogBiz;
import com.chinadovey.parking.webapps.mappers.gen.CarUserMapper;
import com.chinadovey.parking.webapps.mappers.gen.OperateLogMapper;
import com.chinadovey.parking.webapps.pojo.CarUser;
import com.chinadovey.parking.webapps.pojo.OperateLog;
import com.chinadovey.parking.webapps.pojo.OperateLogExample;
import com.chinadovey.parking.webapps.pojo.OperateLogExample.Criteria;
import com.chinadovey.parking.webapps.utils.DateUtil;
import com.chinadovey.parking.webapps.utils.StringUtil;

@Service
public class OperateLogBizImpl implements OperateLogBiz {
	@Autowired
	private OperateLogMapper mapper;
	
	@Autowired
	private CarUserMapper carUserMapper;

	@Override
	public OperateLog find(int id) {
		return mapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<OperateLog> findBySpaceIdAndTime(Date startTime,Date endTime,int carSpaceId) {
		return null;
	}

	@Override
	public void save(OperateLog depart) {
		mapper.insertSelective(depart);
	}

	@Override
	public void update(OperateLog depart) {
		mapper.updateByPrimaryKeySelective(depart);
	}

	@Override
	public void delete(int id) {
		mapper.deleteByPrimaryKey(id);
	}

	@Override
	public Map<String, Object> getList(int page, int rows, String search, String sort, String order,
			String start, String end,Integer type) {
		return null;
	}

	@Override
	public void batchDelete(int[] ids) {
		List<Integer> idList = new ArrayList<Integer>();
		for (int i = 0; i < ids.length; i++) {
			idList.add(ids[i]);
		}
		OperateLogExample example = new OperateLogExample();
		example.createCriteria().andIdIn(idList);
		mapper.deleteByExample(example);
	}

	@Override
	public CarUser getCarUserByCarLockId(Integer carLockId) {
		return null;
	}

}