package com.chinadovey.parking.webapps.biz.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinadovey.parking.core.supports.mybatis.pagination.PageContext;
import com.chinadovey.parking.core.supports.mybatis.pagination.Pagination;
import com.chinadovey.parking.webapps.mappers.gen.TeacherMapper;
import com.chinadovey.parking.webapps.pojo.Company;
import com.chinadovey.parking.webapps.pojo.CompanyExample;
import com.chinadovey.parking.webapps.pojo.Teacher;
import com.chinadovey.parking.webapps.pojo.TeacherExample;

@Service
public class TeacherBizImpl implements TeacherBiz{
	
	@Resource
	private TeacherMapper mapper;

	@Override
	public void add(Teacher teacher) {
		mapper.insert(teacher);
	}

	@Override
	public void findByTeacherName(String teacherName) {
		TeacherExample example = new TeacherExample();
		example.createCriteria().andTeacherNameEqualTo(teacherName);
		mapper.selectByExample(example);
	}

	@Override
	public void update(Teacher teacher) {
		mapper.updateByPrimaryKeySelective(teacher);
	}

	@Override
	public void delete(Integer teacherId) {
		mapper.deleteByPrimaryKey(teacherId);
	}

	@Override
	public Map<String, Object> getList(int page, int rows, String search, String sort, String order) {
		TeacherExample example = new TeacherExample();
		if (page <= 0 || rows <= 0) {
			page = 0;
			rows = 10;
		}
        
        Map<String, Object> map = new HashMap<String, Object>();
		Pagination<?> pagin = PageContext.initialize(page, rows);
		List<Teacher> list = mapper.selectByExample(example, pagin.getRowBounds());
		map.put("rows", list);
		map.put("total", pagin.getTotalRows());
		return map;
	}


	@Override
	public boolean checkName(String teacherName) {
		TeacherExample example = new TeacherExample();
		example.createCriteria().andTeacherNameEqualTo(teacherName);
		TeacherMapper teacherMapper = null;
		List<Teacher> list = teacherMapper.selectByExample(example);
		if(list!=null && !list.isEmpty()){
			return true;
		}
		return false;
	}

	@Override
	public boolean checkNameById(int teacherId, String teacherName) {
		TeacherExample example = new TeacherExample();
		example.createCriteria().andTeacherNameEqualTo(teacherName).andTeacherIdNotEqualTo(teacherId);
		TeacherMapper teacherMapper = null;
		List<Teacher> list = teacherMapper.selectByExample(example);
		if(list!=null && !list.isEmpty()){
			return true;
		}
		return false;
	}

	@Override
	public Teacher find(int id) {
		TeacherExample example = new TeacherExample();
		example.createCriteria().andTeacherIdEqualTo(id);
		Teacher teacher = mapper.selectByExample(example).get(0);
		return teacher;
	}




}
