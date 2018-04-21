package com.chinadovey.parking.webapps.biz.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinadovey.parking.core.supports.mybatis.pagination.PageContext;
import com.chinadovey.parking.core.supports.mybatis.pagination.Pagination;
import com.chinadovey.parking.webapps.biz.CarlockLogBiz;
import com.chinadovey.parking.webapps.mappers.gen.CarlockLogMapper;
import com.chinadovey.parking.webapps.pojo.CarlockLog;
import com.chinadovey.parking.webapps.pojo.CarlockLogExample;
import com.chinadovey.parking.webapps.pojo.CarlockLogExample.Criteria;
import com.chinadovey.parking.webapps.utils.DateUtil;
@Service
public class CarLockLogBizImpl  implements CarlockLogBiz{

	@Autowired
	private CarlockLogMapper mapper;

	@Override
	public void save(CarlockLog log) {
		mapper.insert(log);
	}

	@Override
	public Map<String, Object> getList(int page, int rows, String sort,
			String order, String search, String start, String end) {
		CarlockLogExample e =new CarlockLogExample();
		e.setOrderByClause("time desc");
		
		Criteria criteria = e.createCriteria();
		if(search!=null){
			criteria.andSlaveidLike("%"+search+"%");
		}
		if(start!=null && end!=null){
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date endTemp =new Date();
			try {
				end=end+" 23:59:59";
				endTemp = sdf.parse(end);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			criteria.andTimeBetween(DateUtil.stringConvertDate(start, 0),endTemp);
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		Pagination<?> pagin = PageContext.initialize(page, rows);
		List<CarlockLog> clist = mapper.selectByExample(e, pagin.getRowBounds());

		map.put("rows", clist);
		map.put("total", pagin.getTotalRows());
		
		return map;
	}
	
	
	
	

}
