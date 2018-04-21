package com.chinadovey.parking.webapps.utils;

import java.io.IOException;
import java.util.Properties;

import org.quartz.core.SampledStatistics;

public class ConfUtils {
	
    public static final String PROPERTIES_URL = "/config/parking.properties";
	
	public static Properties loadConf(String res) throws IOException {
		Properties prop = new Properties();
		prop.load(ConfUtils.class.getResourceAsStream(res));
		return prop;
	}
	
	public static Properties loadConf() throws IOException {
		Properties prop = new Properties();
		prop.load(ConfUtils.class.getResourceAsStream(PROPERTIES_URL));
		return prop;
	}
	
	
	public static String getUrlHead() throws IOException{
		Properties prop = loadConf(PROPERTIES_URL);
		return  prop.getProperty("carlockServer.address");
	}
	/**
	 * 车位锁控制 
	 * @return 返回服务器的ip和端口
	 */
	public static String getControlAddress() throws IOException{
		Properties prop = loadConf(PROPERTIES_URL);
		return  prop.getProperty("controlServer.ip");
	}
	public static String getMail() throws IOException{
		Properties prop = loadConf(PROPERTIES_URL);
		return  prop.getProperty("mail.linkedOperate");
	}
	public static String getMailUsername() throws IOException{
		Properties prop = loadConf(PROPERTIES_URL);
		return  prop.getProperty("mail.username");
	}
	public static String getMailPassword() throws IOException{
		Properties prop = loadConf(PROPERTIES_URL);
		return  prop.getProperty("mail.password");
	}
	public static String getJdbcAddress() throws IOException{
		Properties prop = loadConf(PROPERTIES_URL);
		return  prop.getProperty("proxool.jdbc.url");
	}
	public static String getJdbcUsername() throws IOException{
		Properties prop = loadConf(PROPERTIES_URL);
		return  prop.getProperty("proxool.jdbc.username");
	}
	public static String getJdbcPwd() throws IOException{
		Properties prop = loadConf(PROPERTIES_URL);
		return  prop.getProperty("proxool.jdbc.password");
	}
	public static String getValueByKey(String key) throws IOException{
		Properties prop = loadConf(PROPERTIES_URL);
		return  prop.getProperty(key);
	}
	
}
