package com.chinadovey.parking.webapps.biz.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinadovey.parking.core.supports.mybatis.pagination.PageContext;
import com.chinadovey.parking.core.supports.mybatis.pagination.Pagination;
import com.chinadovey.parking.webapps.mappers.gen.PersonMapper;
import com.chinadovey.parking.webapps.pojo.Person;
import com.chinadovey.parking.webapps.pojo.PersonExample;
import com.chinadovey.parking.webapps.pojo.Student;
import com.chinadovey.parking.webapps.pojo.StudentExample;

@Service
public class PersonBizImpl implements PersonBiz{
	
	@Resource
	private PersonMapper mapper;

	@Override
	public void add(Person person) {
		mapper.insert(person);
	}

	@Override
	public void findByName(String name) {
		PersonExample example = new PersonExample();
		example.createCriteria().andNameEqualTo(name);
		mapper.selectByExample(example);
	}

	@Override
	public void update(Person person) {
		mapper.updateByPrimaryKeySelective(person);
	}

	@Override
	public void delete(Integer id) {
		mapper.deleteByPrimaryKey(id);
	}

	@Override
	public Map<String, Object> getList(int page, int rows, String search, String sort, String order) {
		PersonExample example = new PersonExample();
		if (page <= 0 || rows <= 0) {
			page = 0;
			rows = 10;
		}
        
        Map<String, Object> map = new HashMap<String, Object>();
		Pagination<?> pagin = PageContext.initialize(page, rows);
		List<Person> list = mapper.selectByExample(example, pagin.getRowBounds());
		map.put("rows", list);
		map.put("total", pagin.getTotalRows());
		return map;
	}

}
