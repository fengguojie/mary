package com.chinadovey.parking.webapps.biz.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinadovey.parking.core.supports.mybatis.pagination.PageContext;
import com.chinadovey.parking.core.supports.mybatis.pagination.Pagination;
import com.chinadovey.parking.webapps.mappers.gen.PersonMapper;
import com.chinadovey.parking.webapps.mappers.gen.Pp_GoodsMapper;
import com.chinadovey.parking.webapps.mappers.gen.TeacherMapper;
import com.chinadovey.parking.webapps.pojo.Person;
import com.chinadovey.parking.webapps.pojo.PersonExample;
import com.chinadovey.parking.webapps.pojo.Pp_Goods;
import com.chinadovey.parking.webapps.pojo.Pp_GoodsExample;
import com.chinadovey.parking.webapps.pojo.Student;
import com.chinadovey.parking.webapps.pojo.StudentExample;
import com.chinadovey.parking.webapps.pojo.Teacher;
import com.chinadovey.parking.webapps.pojo.TeacherExample;

@Service
public class PpGoodsBizImpl implements PpGoodsBiz{
	
	@Resource
	private Pp_GoodsMapper mapper;

	@Override
	public void add(Pp_Goods pp_Goods) {
		mapper.insert(pp_Goods);
	}

	@Override
	public void findByGname(String gname) {
		Pp_GoodsExample example = new Pp_GoodsExample();
		example.createCriteria().andGnameEqualTo(gname);
		mapper.selectByExample(example);
	}

	@Override
	public void update(Pp_Goods pp_Goods) {
		mapper.updateByPrimaryKeySelective(pp_Goods);
	}

	@Override
	public void delete(Integer id) {
		mapper.deleteByPrimaryKey(id);
	}

	@Override
	public Map<String, Object> getList(int page, int rows, String search, String sort, String order) {
		Pp_GoodsExample example = new Pp_GoodsExample();
		if (page <= 0 || rows <= 0) {
			page = 0;
			rows = 10;
		}
        
        Map<String, Object> map = new HashMap<String, Object>();
		Pagination<?> pagin = PageContext.initialize(page, rows);
		List<Pp_Goods> list = mapper.selectByExample(example, pagin.getRowBounds());
		map.put("rows", list);
		map.put("total", pagin.getTotalRows());
		return map;
	}

	@Override
	public boolean checkName(String gname) {
			Pp_GoodsExample example = new Pp_GoodsExample();
			example.createCriteria().andGnameEqualTo(gname);
			Pp_GoodsMapper pp_GoodsMapper = null;
			List<Pp_Goods> list = pp_GoodsMapper.selectByExample(example);
			if(list!=null && !list.isEmpty()){
				return true;
			}
			return false;
		}

	@Override
	public boolean checkNameById(int id, String gname) {
		Pp_GoodsExample example = new Pp_GoodsExample();
		example.createCriteria().andGnameEqualTo(gname).andIdNotEqualTo(id);
		Pp_GoodsMapper pp_GoodsMapper = null;
		List<Pp_Goods> list = pp_GoodsMapper.selectByExample(example);
		if(list!=null && !list.isEmpty()){
			return true;
		}
		return false;
	}

	@Override
	public Pp_Goods find(int id) {
		Pp_GoodsExample example = new Pp_GoodsExample();
		example.createCriteria().andIdEqualTo(id);
		Pp_Goods pp_Goods = mapper.selectByExample(example).get(0);
		return pp_Goods;
	}
}


