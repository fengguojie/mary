package com.chinadovey.parking.webapps.biz.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinadovey.parking.core.supports.mybatis.pagination.PageContext;
import com.chinadovey.parking.core.supports.mybatis.pagination.Pagination;
import com.chinadovey.parking.webapps.mappers.gen.GradeMapper;
import com.chinadovey.parking.webapps.pojo.Grade;
import com.chinadovey.parking.webapps.pojo.GradeExample;

@Service
public class GradeBizImpl implements GradeBiz{
	
	@Resource
	private GradeMapper mapper;

	@Override
	public void add(Grade grade) {
		mapper.insert(grade);
	}

	@Override
	public void findByGradeType(String gradeType) {
		GradeExample example = new GradeExample();
		example.createCriteria().andGradeTypeEqualTo(gradeType);
		mapper.selectByExample(example);
	}

	@Override
	public void update(Grade grade) {
		mapper.updateByPrimaryKeySelective(grade);
	}

	@Override
	public void delete(Integer id) {
		mapper.deleteByPrimaryKey(id);
	}

	@Override
	public Map<String, Object> getList(int page, int rows, String search, String sort, String order) {
		GradeExample example = new GradeExample();
		if (page <= 0 || rows <= 0) {
			page = 0;
			rows = 10;
		}
        
        Map<String, Object> map = new HashMap<String, Object>();
		Pagination<?> pagin = PageContext.initialize(page, rows);
		List<Grade> list = mapper.selectByExample(example, pagin.getRowBounds());
		map.put("rows", list);
		map.put("total", pagin.getTotalRows());
		return map;
	}

}
