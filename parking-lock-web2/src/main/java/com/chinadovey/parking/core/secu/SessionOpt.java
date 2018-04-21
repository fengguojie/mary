package com.chinadovey.parking.core.secu;

import java.util.Enumeration;

import javax.servlet.http.HttpSession;

/**
 * 用户登录信息工具类
 * @author Bean
 *
 */
public class SessionOpt {

	public static String SECU_IN_SESSION = "_dovey_session_secu_object_";
	public static String UI_MENU_IN_SESSION = "_dovey_session_secu_ui_menu_";
	public static String PARK_ID = "park_id";
	public static String COMPANY_IDS = "company_ids";
	public static String BUSINESS_IDS = "business_ids";
	
	/**
	 * 返回当前会话中的用户登录信息
	 * @param session
	 * @return
	 */
	public static SecuObject getSecuObject(HttpSession session){
		return (SecuObject) session.getAttribute(SECU_IN_SESSION);
	}

	/**
	 * 将当前登录的用户信息从当前会话中清除
	 * @param session
	 */
	public static void logout(HttpSession session){
		@SuppressWarnings("unchecked")
		Enumeration<String> en = session.getAttributeNames();
		
		while(en.hasMoreElements()){
			session.removeAttribute(en.nextElement());
		}
		session.invalidate();
	}
}
