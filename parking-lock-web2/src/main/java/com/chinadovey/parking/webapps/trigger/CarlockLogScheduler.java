package com.chinadovey.parking.webapps.trigger;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinadovey.parking.webapps.biz.CarLockBiz;
import com.chinadovey.parking.webapps.biz.CarLockDataBiz;
import com.chinadovey.parking.webapps.biz.CarlockLogBiz;
import com.chinadovey.parking.webapps.pojo.CarLock;
import com.chinadovey.parking.webapps.pojo.CarlockLog;

@Service
public class CarlockLogScheduler {
	@Autowired
	private CarLockBiz carLockBiz;
	@Autowired
	private CarLockDataBiz carLockDataBiz;
	@Autowired
	private CarlockLogBiz carlockLogBiz;
	/**
	 * data表中两小时内出现的车锁，都是正常车锁。  没出现的车锁都是离线车锁。
	 */
	public void statisticCarlockLog(){
		HashSet<String> slaveIds =carLockDataBiz.findInTwohours();
		if(slaveIds!=null && !slaveIds.isEmpty()){
			for(String s:slaveIds){
				CarlockLog log=new CarlockLog();
				log.setSlaveid(s);
				log.setStatus(0);//恢复
				log.setTime(new Date());
				carlockLogBiz.save(log);
			}
		}
		//其余车锁就是离线车锁。
		List<String> offlineLocks =carLockBiz.getOfflineLock(slaveIds);
		if(offlineLocks!=null && !offlineLocks.isEmpty()){
			for(String s:offlineLocks){
				CarlockLog log=new CarlockLog();
				log.setSlaveid(s);
				log.setStatus(5);//离线
				log.setTime(new Date());
				carlockLogBiz.save(log);
			}
		}
		
	}
		
}
