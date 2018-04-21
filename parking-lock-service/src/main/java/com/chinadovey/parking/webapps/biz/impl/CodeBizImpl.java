package com.chinadovey.parking.webapps.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinadovey.parking.core.supports.mybatis.pagination.PageContext;
import com.chinadovey.parking.core.supports.mybatis.pagination.Pagination;
import com.chinadovey.parking.webapps.biz.system.CodeBiz;
import com.chinadovey.parking.webapps.mappers.gen.CodeMapper;
import com.chinadovey.parking.webapps.pojo.Code;
import com.chinadovey.parking.webapps.pojo.CodeExample;
import com.chinadovey.parking.webapps.utils.StringUtil;

@Service
public class CodeBizImpl implements CodeBiz {

	@Autowired
	private CodeMapper codeMapper;

	@Override
	public int insert(Code code) {
		return codeMapper.insertSelective(code);
	}

	@Override
	public Map<String, Object> getList(int rows, int page, String sort, String order, String search) {
		CodeExample codeExample = new CodeExample();
		if (rows == 0 || page == 0) {
			rows = 10;
			page = 1;
		}
		// 进行排序
		if (sort != null && !sort.isEmpty()) {
			codeExample.setOrderByClause(StringUtil.changeOrderStr(sort) + " " + order);
		}
		// 进行模糊查询
		if (search != null && !search.isEmpty()) {
			codeExample.createCriteria().andCodeLike("%" + search + "%");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		Pagination<?> pagin = PageContext.initialize(page, rows);
		List<Code> list = codeMapper.selectByExample(codeExample, pagin.getRowBounds());
		map.put("rows", list);
		map.put("total", pagin.getTotalRows());
		return map;
	}

	@Override
	public Code find(int id) {
		return codeMapper.selectByPrimaryKey(id);
	}

	@Override
	public String getValueByKey(String key) {
		Code code = getCodeByKey(key);
		if (code != null) {
			return code.getValue();
		}
		return null;
	}

	@Override
	public String getValueByKey(String key, Integer parkId) {
		Code code = getCodeByKey(key, parkId);
		if (code != null) {
			return code.getValue();
		}
		return "9999";
	}

	@Override
	public Code getCodeByKey(String key) {
		CodeExample codeExample = new CodeExample();
		codeExample.createCriteria().andCodeEqualTo(key);
		List<Code> codes = codeMapper.selectByExample(codeExample);
		if (codes != null && !codes.isEmpty()) {
			return codes.get(0);
		}
		return null;
	}

	@Override
	public Code getCodeByKey(String key, Integer parkId) {
		CodeExample codeExample = new CodeExample();
		codeExample.createCriteria().andCodeEqualTo(key).andParkIdEqualTo(parkId);
		List<Code> codes = codeMapper.selectByExample(codeExample);
		if (codes != null && !codes.isEmpty()) {
			return codes.get(0);
		}
		return null;
	}

	@Override
	public int delete(int id) {
		return codeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateValueByCode(String key, String value) {
		CodeExample codeExample = new CodeExample();
		codeExample.createCriteria().andCodeEqualTo(key);

		Code code = new Code();
		code.setValue(value);

		return codeMapper.updateByExampleSelective(code, codeExample);
	}

	@Override
	public int updateValueByCode(String key, String value, Integer parkId) {
		CodeExample codeExample = new CodeExample();
		codeExample.createCriteria().andCodeEqualTo(key).andParkIdEqualTo(parkId);

		Code code = new Code();
		code.setValue(value);

		return codeMapper.updateByExampleSelective(code, codeExample);
	}
}
