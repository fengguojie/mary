package com.chinadovey.parking.webapps.biz;

import com.chinadovey.parking.webapps.pojo.DasConnect;

public interface DasConnectBiz {

	DasConnect findOne(Long sessionId);

	boolean getByDasId(String dasId);

	void updateByDasId(DasConnect dasConnect);

	void insert(DasConnect dasConnect);

	DasConnect getByDasIdAndServerAddress(String dasId, String localAddress);

	void deleteBySessionIdAndServerAddress(long id, String localAddress);

	void deleteByServerAddress(String localAddress);

}
