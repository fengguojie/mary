package com.chinadovey.parking.webapps.biz.member;

import java.util.Date;

public interface ParkUserBiz {
	int  Count();
	
	int CountMonth(Date start,Date end);
}
