package com.chinadovey.parking.webapps.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinadovey.parking.core.supports.mybatis.pagination.PageContext;
import com.chinadovey.parking.core.supports.mybatis.pagination.Pagination;
import com.chinadovey.parking.webapps.biz.CarlockCheckLogBiz;
import com.chinadovey.parking.webapps.mappers.gen.CarlockLogMapper;
import com.chinadovey.parking.webapps.pojo.CarlockLog;
import com.chinadovey.parking.webapps.pojo.CarlockLogExample;
import com.chinadovey.parking.webapps.utils.DateUtil;
import com.chinadovey.parking.webapps.utils.StringUtil;
@Service
public class CarLockCheckLogBizImpl  implements CarlockCheckLogBiz{

	@Autowired
	private CarlockLogMapper mapper;
	
	@Override
	public Map<String, Object> getList(int page, int rows, String search, String sort, String order, String start,
			String end) {
		CarlockLogExample example = new CarlockLogExample();
		if (page <= 0 || rows <= 0) {
			page = 0;
			rows = 10;
		}
		if (sort != null && !sort.isEmpty()) {
			example.setOrderByClause(StringUtil.changeOrderStr(sort) + " " + order);
		}
		CarlockLogExample.Criteria criteria = example.createCriteria();
        if (search != null && !search.isEmpty()) {
			criteria.andSlaveidLike("%" + search + "%");
		}
        if (!start.equals("0")) {
        	criteria.andTimeBetween(DateUtil.stringConvertDate(start, 0), DateUtil.stringConvertDate(end, 0));
		}
		Map<String, Object> map = new HashMap<String, Object>();
		Pagination<?> pagin = PageContext.initialize(page, rows);
		List<CarlockLog> list = mapper.selectByExample(example, pagin.getRowBounds());
		map.put("rows", list);
		map.put("total", pagin.getTotalRows());
		return map;
	}
	
	
	

}
