package com.chinadovey.parking.webapps.biz.pay;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 
* @author 作者  xqy
* @version 创建时间：2016年9月19日 下午2:55:50 
* 类说明 
*/
public interface  PayBiz {

	
	public String getOrderInfo(int type,String orderNo, String subject, String body, String price,int companyId,HttpServletRequest request, HttpServletResponse response);
}
