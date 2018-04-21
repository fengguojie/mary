package com.chinadovey.parking.webapps.controller.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinadovey.parking.core.annotation.SecurityIgnoreHandler;
import com.chinadovey.parking.core.secu.SessionOpt;

import net.sf.json.JSONObject;

/**
 * 用户会话控制器
 * 
 * @author Bean
 *
 */
@Controller
@RequestMapping(value = "/login")
public class SessionInvalidateController {

	@SecurityIgnoreHandler
	@RequestMapping(value = "/logout")
	@ResponseBody
	public String logout(HttpSession session, HttpServletRequest request) {
		SessionOpt.logout(session);
		JSONObject json = new JSONObject();
		json.put("status", true);
		return json.toString();
	}
}