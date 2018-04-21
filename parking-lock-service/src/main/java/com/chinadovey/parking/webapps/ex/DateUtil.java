package com.chinadovey.parking.webapps.ex;

import java.text.SimpleDateFormat;

/** 
* @author 作者  xqy
* @version 创建时间：2016年4月22日 上午10:46:20 
* 类说明 
*/
public class DateUtil {
	public static String dateToStr(java.util.Date dateDate) {
		   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		   String dateString = formatter.format(dateDate);
		   return dateString;
		}
	public static String timeToStr(java.util.Date dateDate) {
		   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		   String dateString = formatter.format(dateDate);
		   return dateString;
		}
}
