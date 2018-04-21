package com.chinadovey.parking.webapps.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinadovey.parking.core.supports.mybatis.pagination.PageContext;
import com.chinadovey.parking.core.supports.mybatis.pagination.Pagination;
import com.chinadovey.parking.webapps.biz.baseInfo.GatewayBiz;
import com.chinadovey.parking.webapps.mappers.gen.GatewayMapper;
import com.chinadovey.parking.webapps.pojo.Gateway;
import com.chinadovey.parking.webapps.pojo.GatewayExample;
import com.chinadovey.parking.webapps.utils.MathUtil;
import com.chinadovey.parking.webapps.utils.StringUtil;

@Service
public class GatewayBizImpl implements GatewayBiz{

	@Autowired
	private GatewayMapper gatewayMapper;
	
	@Override
	public Gateway getByDasId(String dasId) {
		GatewayExample example = new GatewayExample();
		example.createCriteria().andGatewayNoEqualTo(dasId);
		List<Gateway> list = gatewayMapper.selectByExample(example);
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
	@Override
	public Map<String, Object> getList(int page, int rows, String search, String sort, String order,String companyNo) {
		GatewayExample example = new GatewayExample();
		if (page <= 0 || rows <= 0) {
			page = 0;
			rows = 10;
		}
		if (sort != null && !sort.isEmpty()) {
			example.setOrderByClause(StringUtil.changeOrderStr(sort) + " " + order);
		}
        GatewayExample.Criteria criteria = example.createCriteria();
        if (search != null && !search.isEmpty()) {
			criteria.andGatewayNameLike("%" + search + "%");
//			criteria.andDasIdEqualTo(search);
		}
        if (companyNo != null ) {
//			criteria.andCompanyNoEqualTo(companyNo);
		}
        Map<String, Object> map = new HashMap<String, Object>();
		Pagination<?> pagin = PageContext.initialize(page, rows);
		List<Gateway> list = gatewayMapper.selectByExample(example, pagin.getRowBounds());
		map.put("rows", list);
		map.put("total", pagin.getTotalRows());
		return map;
	}
	@Override
	public boolean isDasIdExitById(String gatewayNo, int id) {
		if (gatewayMapper.selectByPrimaryKey(id).getGatewayNo().equals(gatewayNo.toString())) {
			return false;
		}
		return isDasIdExit(gatewayNo);
	}
	@Override
	public boolean isDasIdExit(String gatewayNo) {
		GatewayExample example = new GatewayExample();
		example.createCriteria().andGatewayNoEqualTo(gatewayNo.toString());
		return gatewayMapper.countByExample(example) > 0;
	}
	@Override
	public List<Gateway> getGateway(String companyNo) {
		GatewayExample example = new GatewayExample();
//		example.createCriteria().andCompanyNoEqualTo(companyNo);
		return gatewayMapper.selectByExample(example);
	}
	@Override
	public List<Gateway> getAll(List<String> companys) {
		GatewayExample example = new GatewayExample();
//		example.createCriteria().andCompanyNoIn(companys);
		return gatewayMapper.selectByExample(example);
	}
	@Override
	public Integer countByStatus(Integer status) {
		GatewayExample example = new GatewayExample();
		example.createCriteria().andGatewayStatusEqualTo(status);
		return gatewayMapper.countByExample(example);
	}
	
	@Override
	public Map<String, Object> getList(List<Integer> parkIds, int page, int rows, String sort, String order, String search) {
		GatewayExample gatewayExample = new GatewayExample();
		//进行分页
		if(page <= 0 || rows <= 0){
			page = 0;
			rows = 0;
		}
		//进行排序
		if (sort != null && !sort.isEmpty()) {
			gatewayExample.setOrderByClause(StringUtil.changeOrderStr(sort) + " " + order);
		}
		
		GatewayExample.Criteria criteria = gatewayExample.createCriteria();
		
		//查询parkId
		if (parkIds != null && !parkIds.isEmpty()) {
			criteria.andParkIdIn(parkIds);
		}
		//进行模糊查询
		if (search != null && !search.isEmpty()) {
			if(MathUtil.isDigit(search)){
				criteria.andGatewayNoLike("%" + search + "%");
			}else{
				criteria.andGatewayNameLike("%" + search + "%");
			}
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		Pagination<?> pagin = PageContext.initialize(page, rows);
		List<Gateway> list = gatewayMapper.selectByExample(gatewayExample, pagin.getRowBounds());
		map.put("rows", list);
		map.put("total", pagin.getTotalRows());
		return map;
	}

	@Override
	public int getCount(String gatewayNo) {
		GatewayExample gatewayExample = new GatewayExample();
		gatewayExample.createCriteria().andGatewayNoEqualTo(gatewayNo);
		return gatewayMapper.countByExample(gatewayExample);
	}

	@Override
	public void save(Gateway gateway) {
		gatewayMapper.insertSelective(gateway);
	}

	@Override
	public Gateway find(Integer id) {
		return gatewayMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public void update(Gateway gateway) {
		gatewayMapper.updateByPrimaryKeySelective(gateway);
	}
	@Override
	public void delete(Integer id) {
		gatewayMapper.deleteByPrimaryKey(id);
	}
	@Override
	public List<Gateway> getAll() {
		return gatewayMapper.selectByExample(null);
	}
	@Override
	public List<Gateway> findByParkIds(List<Integer> parkIds) {
		GatewayExample gatewayExample = new GatewayExample();
		gatewayExample.createCriteria().andParkIdIn(parkIds);
		List<Gateway> list = gatewayMapper.selectByExample(gatewayExample);
		if(list != null && !list.isEmpty()){
			return list;
		}
		return null;
	}
	
}

