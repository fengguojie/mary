package com.chinadovey.parking.webapps.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinadovey.parking.core.supports.mybatis.pagination.PageContext;
import com.chinadovey.parking.core.supports.mybatis.pagination.Pagination;
import com.chinadovey.parking.webapps.biz.baseInfo.CompanyBiz;
import com.chinadovey.parking.webapps.biz.baseInfo.ParkBiz;
import com.chinadovey.parking.webapps.mappers.gen.CompanyMapper;
import com.chinadovey.parking.webapps.pojo.Company;
import com.chinadovey.parking.webapps.pojo.CompanyExample;
import com.chinadovey.parking.webapps.pojo.Park;
import com.chinadovey.parking.webapps.utils.StringUtil;

@Service
public class CompanyBizImpl implements CompanyBiz {

	@Autowired
	private CompanyMapper companyMapper;


	@Autowired
	private ParkBiz parkBiz;


	@Override
	public void save(Company company) {
		// TODO Auto-generated method stub
		companyMapper.insertSelective(company);
	}

	@Override
	public void save(String name, String addrss, String remark) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		companyMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Company find(Integer id) {
		return companyMapper.selectByPrimaryKey(id);
	}

	@Override
	public void update(Company company) {
		// TODO Auto-generated method stub
		companyMapper.updateByPrimaryKeySelective(company);
	}

	@Override
	public List<Park> getParks(Integer id) {
		List<Integer> parkIds = getParkIds(id);
		List<Park> parks = parkBiz.getAll(parkIds);
		return parks;
	}


	@Override
	public Map<String, Object> getList(int page, int rows, String search, String sort, String order) {
		CompanyExample example = new CompanyExample();
		// 进行分页
		if (page <= 0 || rows <= 0) {
			page = 0;
			rows = 10;
		}
		if (sort != null && !sort.isEmpty()) {
			example.setOrderByClause(StringUtil.changeOrderStr(sort) + " " + order);
		}
		if (search != null && !search.isEmpty()) {
			example.createCriteria().andNameLike("%" + search + "%");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		Pagination<?> pagin = PageContext.initialize(page, rows);
		List<Company> clist = companyMapper.selectByExample(example, pagin.getRowBounds());

		map.put("rows", clist);
		map.put("total", pagin.getTotalRows());
		return map;

	}

	@Override
	public List<Company> getList() {
		return companyMapper.selectByExample(null);
	}

	@Override
	public List<Company> getAll() {
		return companyMapper.selectByExample(null);
	}

	@Override
	public List<Company> getAll(List<Integer> companyIds) {
		if (companyIds == null || companyIds.isEmpty()) {
			return getAll();
		}
		CompanyExample companyExample = new CompanyExample();
		companyExample.createCriteria().andIdIn(companyIds);
		return companyMapper.selectByExample(companyExample);
	}

	@Override
	public Company findByParkId(Integer parkId) {
		Integer companyId = findIdByParkId(parkId);
		if (companyId != null) {
			Company company = find(companyId);
			return company;
		}
		return null;
	}

	@Override
	public Company findByCompanyNo(String companyNo) {
		CompanyExample example = new CompanyExample();
		example.createCriteria().andCompanyNoEqualTo(companyNo);
		List<Company> list = companyMapper.selectByExample(example);
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public Integer findServiceProviderId(Integer companyId) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 检查机构名称是否已存在
	 * @param name
	 * @return
	 */
	@Override
	public boolean checkName(String name) {
		CompanyExample example = new CompanyExample();
		example.createCriteria().andNameEqualTo(name);
		List<Company> list = companyMapper.selectByExample(example);
		if(list!=null && !list.isEmpty()){
			return true;
		}
		return false;
	}
	/**
	 * 检查机构名称是否已存在 修改页面
	 */
	@Override
	public boolean checkNameById(int id,String name) {
		CompanyExample example = new CompanyExample();
		example.createCriteria().andNameEqualTo(name).andIdNotEqualTo(id);
		List<Company> list = companyMapper.selectByExample(example);
		if(list!=null && !list.isEmpty()){
			return true;
		}
		return false;
	}

	@Override
	public List<Integer> getParkIds(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> getCompanyIdsByParkIds(List<Integer> parkIds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer findIdByParkId(Integer parkId) {
		// TODO Auto-generated method stub
		return null;
	}
}
