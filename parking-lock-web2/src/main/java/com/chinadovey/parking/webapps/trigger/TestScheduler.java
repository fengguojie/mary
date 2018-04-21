package com.chinadovey.parking.webapps.trigger;

import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class TestScheduler {
	
	public void test(){
		System.out.println(new Date()+"我被定时执行了！");
		
	}
		
}
