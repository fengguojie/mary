package com.chinadovey.parking.webapps.utils;

public class MathUtil {
	/**
	 * 检查字符串中，是否全部为数字。如果全部是，则返回true。
	 * @param search
	 * @return
	 */
	public static boolean isDigit(String search){
		try {
			Long i = Long.parseLong(search,16);
			return true;
		} catch (Exception e) {
			return false;
		}
		
		
	}

}
