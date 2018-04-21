package com.chinadovey.parking.webapps.biz.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinadovey.parking.core.supports.mybatis.pagination.PageContext;
import com.chinadovey.parking.core.supports.mybatis.pagination.Pagination;
import com.chinadovey.parking.webapps.biz.StudentBiz;
import com.chinadovey.parking.webapps.mappers.gen.StudentMapper;
import com.chinadovey.parking.webapps.pojo.CarUser;
import com.chinadovey.parking.webapps.pojo.CarUserExample;
import com.chinadovey.parking.webapps.pojo.Student;
import com.chinadovey.parking.webapps.pojo.StudentExample;
import com.chinadovey.parking.webapps.utils.StringUtil;
import com.thoughtworks.xstream.mapper.Mapper;

@Service
public class StudentBizImpl implements StudentBiz{
	
	@Resource
	private StudentMapper mapper;

	@Override
	public void add(Student student) {
		mapper.insert(student);
	}

	@Override
	public void findBySno(String sno) {
		StudentExample example = new StudentExample();
		example.createCriteria().andSnoEqualTo(sno);
		mapper.selectByExample(example);
	}

	@Override
	public void update(Student student) {
		mapper.updateByPrimaryKeySelective(student);
	}

	@Override
	public void delete(Integer id) {
		mapper.deleteByPrimaryKey(id);
	}

	@Override
	public Map<String, Object> getList(int page, int rows, String search, String sort, String order) {
		StudentExample example = new StudentExample();
		if (page <= 0 || rows <= 0) {
			page = 0;
			rows = 10;
		}
        
        Map<String, Object> map = new HashMap<String, Object>();
		Pagination<?> pagin = PageContext.initialize(page, rows);
		List<Student> list = mapper.selectByExample(example, pagin.getRowBounds());
		map.put("rows", list);
		map.put("total", pagin.getTotalRows());
		return map;
	}

}
