package com.chinadovey.parking.webapps.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.chinadovey.parking.webapps.biz.DasConnectBiz;
import com.chinadovey.parking.webapps.mappers.gen.DasConnectMapper;
import com.chinadovey.parking.webapps.pojo.DasConnect;
import com.chinadovey.parking.webapps.pojo.DasConnectExample;

public class DasConnectBizImpl implements DasConnectBiz{
	@Autowired
	private DasConnectMapper dasConnectMapper;
	
	@Override
	public DasConnect findOne(Long sessionId) {
		DasConnectExample e = new DasConnectExample();
		e.createCriteria().andSessionIdEqualTo(sessionId);
		List<DasConnect> list = dasConnectMapper.selectByExample(e);
		if(list!=null && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	@Override
	public boolean getByDasId(String dasId) {
		DasConnectExample e = new DasConnectExample();
		e.createCriteria().andDasIdEqualTo(dasId);
		List<DasConnect> list = dasConnectMapper.selectByExample(e);
		if(list!=null && !list.isEmpty()){
			return true;
		}
		return false;
	}

	@Override
	public void updateByDasId(DasConnect dasConnect) {
		DasConnectExample e = new DasConnectExample();
		e.createCriteria().andDasIdEqualTo(dasConnect.getDasId());
		dasConnectMapper.updateByExampleSelective(dasConnect, e);
	}

	@Override
	public void insert(DasConnect dasConnect) {
		dasConnectMapper.insert(dasConnect);
	}

	@Override
	public DasConnect getByDasIdAndServerAddress(String dasId,
			String localAddress) {
		DasConnectExample e = new DasConnectExample();
		e.createCriteria().andDasIdEqualTo(dasId).andServerAddressEqualTo(localAddress);
		List<DasConnect> list = dasConnectMapper.selectByExample(e);
		if(list!=null && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	@Override
	public void deleteBySessionIdAndServerAddress(long id, String localAddress) {
		DasConnectExample e = new DasConnectExample();
		e.createCriteria().andSessionIdEqualTo(id).andServerAddressEqualTo(localAddress);
		dasConnectMapper.deleteByExample(e);
	}

	@Override
	public void deleteByServerAddress(String localAddress) {
		DasConnectExample e = new DasConnectExample();
		e.createCriteria().andServerAddressEqualTo(localAddress);
		dasConnectMapper.deleteByExample(e);
	}

}
