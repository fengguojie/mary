package com.chinadovey.parking.webapps.biz.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinadovey.parking.core.supports.mybatis.pagination.PageContext;
import com.chinadovey.parking.core.supports.mybatis.pagination.Pagination;
import com.chinadovey.parking.webapps.mappers.gen.NoteMapper;
import com.chinadovey.parking.webapps.mappers.gen.TeacherMapper;
import com.chinadovey.parking.webapps.pojo.Note;
import com.chinadovey.parking.webapps.pojo.NoteExample;
import com.chinadovey.parking.webapps.pojo.Teacher;
import com.chinadovey.parking.webapps.pojo.TeacherExample;
import com.chinadovey.parking.webapps.pojo.Note;
import com.chinadovey.parking.webapps.pojo.NoteExample;;

@Service
public class NoteBizImpl implements NoteBiz{
	
	@Resource
	private NoteMapper mapper;

	@Override
	public void add(Note note) {
		mapper.insert(note);
	}

	@Override
	public void findById(Integer id) {
		NoteExample example = new NoteExample();
		example.createCriteria().andIdEqualTo(id);
		mapper.selectByExample(example);
	}

	@Override
	public void update(Note note) {
		mapper.updateByPrimaryKeySelective(note);
	}

	@Override
	public void delete(Integer id) {
		mapper.deleteByPrimaryKey(id);
	}

	@Override
	public Map<String, Object> getList(int page, int rows, String search, String sort, String order) {
		NoteExample example = new NoteExample();
		if (page <= 0 || rows <= 0) {
			page = 0;
			rows = 10;
		}
        
        Map<String, Object> map = new HashMap<String, Object>();
		Pagination<?> pagin = PageContext.initialize(page, rows);
		List<Note> list = mapper.selectByExample(example, pagin.getRowBounds());
		map.put("rows", list);
		map.put("total", pagin.getTotalRows());
		return map;
	}

	@Override
	public Note find(int id) {
		NoteExample example = new NoteExample();
		example.createCriteria().andIdEqualTo(id);
		Note note = mapper.selectByExample(example).get(0);
		return note;
	}

	@Override
	public boolean checkName(String title) {
		NoteExample  example = new NoteExample();
		example.createCriteria().andTitleNotEqualTo(title);
		NoteMapper  noteMapper  = null;
		List<Note> list = noteMapper .selectByExample(example);
		if(list!=null && !list.isEmpty()){
			return true;
		}
		return false;
	}

	@Override
	public boolean checkNameById(int id, String title) {
		NoteExample example = new NoteExample();
		example.createCriteria().andTitleNotEqualTo(title).andIdNotEqualTo(id);
		NoteMapper  noteMapper  = null;
		List<Note> list = noteMapper .selectByExample(example);
		if(list!=null && !list.isEmpty()){
			return true;
		}
		return false;
	}

	/*@Override
	public boolean checkNameById(int teacherId, String teacherName) {
		TeacherExample example = new TeacherExample();
		example.createCriteria().andTeacherNameEqualTo(teacherName).andTeacherIdNotEqualTo(teacherId);
		TeacherMapper teacherMapper = null;
		List<Teacher> list = teacherMapper.selectByExample(example);
		if(list!=null && !list.isEmpty()){
			return true;
		}
		return false;
	}*/

}
