package com.chinadovey.parking.webapps.biz.local;

import net.sf.json.JSONArray;

public interface VCarInOutInfoBiz {

	void updateCarSpace(String strVCarInOutInfos);

	void updateCarSpace(JSONArray jsonVCarInOutInfos);

	void getVCarInOutInfosByInTime();

	void getVCarInOutInfosByOutTime();

}
