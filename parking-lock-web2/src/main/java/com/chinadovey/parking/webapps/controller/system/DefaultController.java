package com.chinadovey.parking.webapps.controller.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinadovey.parking.acl.Login;
import com.chinadovey.parking.core.annotation.SecurityAccessCheckable;
import com.chinadovey.parking.core.annotation.SecurityIgnoreHandler;

/**
 * 默认界面 登录系统后的首页
 * @author Bean
 *
 */
@Controller("com.chinadovey.webapps.rbac.DefaultController")
public class DefaultController {

	/**
	 * 显示系统首页
	 * @deprecated 兼容性入口
	 * @return
	 */
	@Deprecated
	@SecurityAccessCheckable(resource=Login.class)
	@RequestMapping(value="/_default")
	public String _default(
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session,
			Model model
			) {
		return "/secu/default_secu_only";
	}

	@SecurityIgnoreHandler
	@RequestMapping(value="/_permission_denied")
	public String _permissionDenied(
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session,
			Model model
			) {
		return "/secu/default_permission_denied";
	}

	@SecurityIgnoreHandler
	@RequestMapping(value="/_permission_undefined")
	public String _permissionUndefined(
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session,
			Model model
			) {
		return "/secu/default_permission_undefined";
	}
	
	@SecurityIgnoreHandler
	@RequestMapping(value="/global-js")
	@ResponseBody
	public String globaljs(HttpServletRequest request){
		return String.format("var $ROOT$ = '%s';", request.getContextPath());
	}
}