package com.chinadovey.parking;

import java.io.File;
import java.util.Date;

//import com.chinadovey.parking.webapps.util.DateUtil;
import com.chinadovey.parking.webapps.utils.WebPath;

/**
 * 常量
 * @author Administrator
 *
 */
public interface Constants {

	public static String EMPTY = "";
	public static String SPACE = " ";
	public static String ZERO = "0";

	public static String WEB_CHARSET = "UTF-8";
	/**
	 * 加密字符串
	 */
	public static String TOKEN = "WR8xcVH5G35oFc2r";

	/**
	 * 项目文件目录，位于tomcat同级目录下的dovey-parking-file
	 */
	public static String PROJECT_FILE_DIRECTORY = WebPath.getTomcatPath() + File.separator + "dovey-parking-file"
			+ File.separator;
	

	/**
	 * 初始时间
	 */
//	final static Date FIXED_DATE = DateUtil.stringConvertDate("2000-01-01 00:00:00", 3);
	
//	public static final String CarLock_Control_URL = "http://cloud.huaching.com/parking-carlock-server/control/operate";
//	public static final String CarLock_Detail_URL = "http://cloud.huaching.com/parking-carlock-server/carlock/detail";
	

	public static final String charge_inout_server_url = "http://192.168.1.22:8885/parking-charge-inout-server";

	/**
	 * 循环车场数
	 */
	public static final int PARK_NUM = 500;

}
