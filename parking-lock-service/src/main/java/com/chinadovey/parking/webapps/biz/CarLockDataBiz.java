package com.chinadovey.parking.webapps.biz;

import java.util.HashSet;
import java.util.List;

import com.chinadovey.parking.webapps.pojo.CarLock;


public interface CarLockDataBiz{
	boolean find(String slaveId);

	HashSet<String> findInTwohours();
}
