package com.chinadovey.parking.webapps.controller.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.chinadovey.parking.core.annotation.SecurityIgnoreHandler;
import com.chinadovey.parking.core.mvc.AbstractBaseController;
import com.chinadovey.parking.core.secu.SecuObject;
import com.chinadovey.parking.core.secu.SessionOpt;
import com.chinadovey.parking.webapps.biz.baseInfo.CompanyBiz;
import com.chinadovey.parking.webapps.biz.system.UiMenuBiz;
import com.chinadovey.parking.webapps.biz.system.UserBiz;
import com.chinadovey.parking.webapps.utils.SecurityUtil;

import net.sf.json.JSONObject;

/**
 * 用户会话控制器
 * 
 * @author Bean
 *
 */
@Controller
@RequestMapping(value = "/login")
@SessionAttributes(value = { "_dovey_session_secu_object_", 
		"_dovey_session_secu_ui_menu_", "park_id", "company_ids"})
public class SessionController extends AbstractBaseController{

	@Autowired
	private UiMenuBiz menuBiz;
	@Autowired
	private UserBiz userBiz;
	@Autowired
	private CompanyBiz companyBiz;

	/**
	 * 跳转到登录界面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@SecurityIgnoreHandler
	@RequestMapping(value = "/input")
	public String input(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "/login";
	}

	/**
	 * 执行登录请求，并验证
	 * 
	 * @param request
	 * @param response
	 * @param username
	 * @param password
	 * @param model
	 * @return
	 */
	@SecurityIgnoreHandler
	@ResponseBody
	@RequestMapping(value = "/dologin")
	public Object doLogin(String username, String password,
			Model model) {
		JSONObject json = new JSONObject();
		try {
			password = SecurityUtil.MD5Encrypt(password);
            SecuObject secuObj = userBiz.getSecuObject(username, password);
            if (secuObj != null) {
                model.addAttribute(SessionOpt.SECU_IN_SESSION, secuObj);
				model.addAttribute(SessionOpt.UI_MENU_IN_SESSION, menuBiz.getUserMenu(secuObj));
				List<Integer> parkIds = userBiz.getParkIdsByUser(secuObj.getUser());
				model.addAttribute(SessionOpt.PARK_ID, parkIds);
				List<Integer> companyIds = companyBiz.getCompanyIdsByParkIds(parkIds);
				model.addAttribute(SessionOpt.COMPANY_IDS, companyIds);
                json.put("status", true);
				json.put("message", "");
			} else {
				json.put("status", false);
				json.put("message", "用户名或密码错误");
			}
			return json;
		} catch (Exception e) {
			json.put("status", false);
			json.put("message", "登录失败！");
			logger.error("登录失败！",e);
			return json;
		}

		
	}

}