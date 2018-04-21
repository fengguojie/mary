package com.chinadovey.parking.webapps.mina.temp;

import java.util.List;

import com.chinadovey.parking.webapps.mina.pojo.Equipment0710;

/**
 * 本地 车位锁数据 上报
 * @author feng
 */
public class CarlockDateReport extends Thread{
	
	private String name;
	private List<Equipment0710> list;

	public CarlockDateReport(String name, List<Equipment0710> list) {
		this.name = name;
		this.list = list;
	}

	@Override
	public void run() {
		try {
			System.out.println(getName()+"线程开始运行……");
			/*Map<String, String> params = new HashMap<String, String>();
			JSONArray array = JSONArray.fromObject(list);
	    	params.put("list", array.toString());
	    	String url = ConfUtils.getCloudUrlHead()+"/carlockData/report";
			String res = HttpClientUtil.getInstance().httpPost(url, params);
			JSONObject res2 = JSONObject.fromObject(res);*/
			System.out.println(getName()+"线程结束运行……");
		} catch (Exception e) {
			//出现异常缓存值本地数据库
			e.printStackTrace();
		}
		
		
	}
	

}
