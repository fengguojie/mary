package com.chinadovey.parking.webapps.biz.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinadovey.parking.webapps.biz.baseInfo.OperatorBiz;
import com.chinadovey.parking.webapps.mappers.gen.OperatorMapper;
import com.chinadovey.parking.webapps.pojo.Operator;
import com.chinadovey.parking.webapps.pojo.OperatorExample;

@Service
public class OperatorBizImpl implements OperatorBiz {

	@Autowired
	private OperatorMapper operatorMapper;

	@Override
	public Operator find(String id) {
		return operatorMapper.selectByPrimaryKey(id);
	}

	@Override
	public void save(Operator operator) {
		operatorMapper.insertSelective(operator);
	}

	@Override
	public void update(Operator operator) {
		operatorMapper.updateByPrimaryKeySelective(operator);
	}

	@Override
	public void delete(String id) {
		operatorMapper.deleteByPrimaryKey(id);
	}

	@Override
	public boolean isExit(String operator, String operatorId, Integer parkId) {
		OperatorExample operatorExample = new OperatorExample();
		operatorExample.createCriteria().andUsernameEqualTo(operator).andUserIdEqualTo(operatorId)
				.andParkIdEqualTo(parkId);
		return operatorMapper.countByExample(operatorExample) > 0;
	}
	@Override
	public boolean isExitId(String id) {
		OperatorExample operatorExample = new OperatorExample();
		operatorExample.createCriteria().andIdEqualTo(id);
		return operatorMapper.countByExample(operatorExample) > 0;
	}
	@Override
	public List<Operator> getAllByParkId(Integer parkId) {
		OperatorExample operatorExample = new OperatorExample();
		operatorExample.createCriteria().andParkIdEqualTo(parkId);
		List<Operator> operators = operatorMapper.selectByExample(operatorExample);
		for (Iterator iterator = operators.iterator(); iterator.hasNext();) {
			Operator operator = (Operator) iterator.next();
			if("未知".equals(operator.getUsername())){
				iterator.remove();
			}
		}
		return operators;
	}

	@Override
	public int backupMongodbToMysql(Integer parkId) {
		/*List<Operator> operators = mongoOps.find(new Query(Criteria.where("parkId").is(parkId)), Operator.class);
		for (Operator operator : operators) {
			if (!isExit(operator.getUsername(), operator.getUserId(),operator.getParkId())) {
				if(!isExitId(operator.getId())){
					save(operator);
				}
			}
		}*/
		return 1;
	}
}
