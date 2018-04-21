package com.chinadovey.parking.webapps.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinadovey.parking.core.supports.mybatis.pagination.PageContext;
import com.chinadovey.parking.core.supports.mybatis.pagination.Pagination;
import com.chinadovey.parking.webapps.biz.DeviceBiz;
import com.chinadovey.parking.webapps.mappers.gen.DeviceMapper;
import com.chinadovey.parking.webapps.pojo.Device;
import com.chinadovey.parking.webapps.pojo.DeviceExample;
import com.chinadovey.parking.webapps.pojo.ex.DeviceEx;
import com.chinadovey.parking.webapps.utils.StringUtil;
@Service
public class DeviceBizImpl  implements DeviceBiz{

	
	@Autowired
	private DeviceMapper mapper;
	


	@Override
	public Map<String, Object> getList(int page, int rows, String search, String sort, String order, List<String> slaveIds) {
		return getList( page,  rows,  search,  sort,  order, slaveIds,null);
	}
	@Override
	public Map<String, Object> getList(int page, int rows, String search, String sort, String order, List<String> slaveIds,List<String> companyNo) {
		DeviceExample example = new DeviceExample();
		// 进行分页
		if (page <= 0 || rows <= 0) {
			page = 0;
			rows = 10;
		}
		// 进行排序
		if (sort != null && !sort.isEmpty()) {
			example.setOrderByClause(StringUtil.changeOrderStr(sort) + " " + order);
		}
		
		DeviceExample.Criteria criteria = example.createCriteria();
		
		
		
		// 进行模糊查询
		if (search != null && !search.isEmpty()) {
			criteria.andSlaveIdLike("%" + search + "%");
		}
		
		if (slaveIds != null&&slaveIds.size()>0) {
			criteria.andJoinSlaveIdIn(slaveIds);
		}
		if (companyNo != null&&companyNo.size()>0) {
			criteria.andCompanyNoIn(companyNo);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		Pagination<?> pagin = PageContext.initialize(page, rows);
		List<DeviceEx> list = mapper.selectJoinByPrimaryKey(example, pagin.getRowBounds());
		map.put("rows", list);
		map.put("total", pagin.getTotalRows());
		return map;
	}

	@Override
	public Device getBySlaveid(String slaveId) {
		DeviceExample example = new DeviceExample();
		example.createCriteria().andSlaveIdEqualTo(slaveId);
		List<Device> list = mapper.selectByExample(example);
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
	@Override
	public Device getByDeviceId(String deviceId,String deviceType) {
		DeviceExample example = new DeviceExample();
		example.createCriteria().andDeviceIdEqualTo(deviceId).andTypeEqualTo(deviceType);
		List<Device> list = mapper.selectByExample(example);
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	

}
