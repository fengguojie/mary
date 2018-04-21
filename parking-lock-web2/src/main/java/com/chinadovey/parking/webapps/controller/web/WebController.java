package com.chinadovey.parking.webapps.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chinadovey.parking.core.mvc.AbstractBaseController;

/**
 * 前台页面
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/web")
public class WebController extends AbstractBaseController {
	/**
	 * 网站首页
	 * @return
	 */
	@RequestMapping("/index")
	public String defaultPage() {
		return "/web/default";
	}
	
	/**
	 * 合作页面
	 * @return
	 */
	@RequestMapping("/cooperation")
	public String cooperation() {
		return "/web/cooperation";
	}
	
	/**
	 * 关于我们
	 * @return
	 */
	@RequestMapping("/about")
	public String about() {
		return "/web/about";
	}
}
