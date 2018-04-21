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
import com.chinadovey.parking.webapps.mappers.gen.UserJellardMapper;
import com.chinadovey.parking.webapps.mappers.gen.UserMapper;
import com.chinadovey.parking.webapps.pojo.CarUser;
import com.chinadovey.parking.webapps.pojo.CarUserExample;
import com.chinadovey.parking.webapps.pojo.Student;
import com.chinadovey.parking.webapps.pojo.StudentExample;
import com.chinadovey.parking.webapps.pojo.UserJellard;
import com.chinadovey.parking.webapps.pojo.UserJellardExample;
import com.chinadovey.parking.webapps.utils.StringUtil;
import com.thoughtworks.xstream.mapper.Mapper;

@Service
public class UserJellardBizImpl implements UserJellardBiz{
	
	@Resource
	private UserJellardMapper mapper;

	@Override
	public void add(UserJellard userJellard) {
		mapper.insert(userJellard);
	}

	@Override
	public void findById(String id) {
		UserJellardExample example = new UserJellardExample();
		example.createCriteria().andIdEqualTo(id);
		mapper.selectByExample(example);
	}

	@Override
	public void update(UserJellard userJellard) {
		mapper.updateByPrimaryKeySelective(userJellard);
	}

	@Override
	public void delete(Integer id) {
		mapper.deleteByPrimaryKey(id);
	}

	@Override
	public Map<String, Object> getList(int page, int rows, String search, String sort, String order) {
		UserJellardExample example = new UserJellardExample();
		if (page <= 0 || rows <= 0) {
			page = 0;
			rows = 10;
		}
        
        Map<String, Object> map = new HashMap<String, Object>();
		Pagination<?> pagin = PageContext.initialize(page, rows);
		List<UserJellard> list = mapper.selectByExample(example, pagin.getRowBounds());
		map.put("rows", list);
		map.put("total", pagin.getTotalRows());
		return map;
	}

	@Override
	public int findByPassword(String username, String password) {
		UserJellardExample example = new UserJellardExample();
		example.createCriteria().andUsernameEqualTo(username).andPasswordEqualTo(password);
		return mapper.countByExample(example);
		
	}


}
