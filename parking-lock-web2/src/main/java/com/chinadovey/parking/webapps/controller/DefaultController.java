package com.chinadovey.parking.webapps.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chinadovey.parking.acl.Login;
import com.chinadovey.parking.core.annotation.SecurityAccessCheckable;
import com.chinadovey.parking.core.mvc.AbstractBaseController;
import com.chinadovey.parking.core.secu.SessionOpt;
import com.chinadovey.parking.webapps.biz.baseInfo.ParkBiz;
import com.chinadovey.parking.webapps.mappers.base.AuthorityBaseMapper;
import com.chinadovey.parking.webapps.pojo.Authority;
import com.chinadovey.parking.webapps.pojo.Park;
import com.chinadovey.parking.webapps.pojo.Role;

/**
 * 系统首页
 * @author Administrator
 *
 */
@Controller
public class DefaultController extends AbstractBaseController {

	/*@Autowired
	private EatRecordBiz eatRecordBiz;

	@Autowired
	private StayRecordBiz stayRecordBiz;*/

	@Resource
	private ParkBiz parkBiz;
	@Resource
	private AuthorityBaseMapper zAuthMapper;

	/**
	 * 显示系统首页
	 * 
	 * @deprecated 兼容性入口
	 * @return
	 */
	@Deprecated
	@SecurityAccessCheckable(resource = Login.class)
	@RequestMapping("/default")
	public String defaultPage(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) {
		List<Integer> parkIds = (List<Integer>) session.getAttribute(SessionOpt.PARK_ID);
		List<Park> parks = null;
		// 判断parkId是否存在
		if (parkIds.size() <= 0) {
			parks = parkBiz.getAll();
		} else {
			parks = parkBiz.getAll(parkIds);
		}

		Park park = new Park();
		Integer parkId = 4;
		// 判断是否为空
		if (parks != null && !parks.isEmpty()) {
			park = parks.get(0);
			parkId = park.getId();
		}
//		model.addAttribute("eatPromptTime", eatRecordBiz.getEatPromptTime(parkId));
//		model.addAttribute("stayPromptTime", stayRecordBiz.getStayPromptTime(parkId));
		
		//保安登录后，只看到操作平台
		
		List<String> names=new ArrayList<String>();
		names.add("登录系统");
		names.add("操作平台");
		List<String> temp=new ArrayList<String>();
		Integer userId = SessionOpt.getSecuObject(session).getUser().getId();
		List<Authority> auths = zAuthMapper.selectAuthorityByUserId(userId);
		for(Authority auth:auths){
			String name = auth.getName();
			temp.add(name);
		}
		if(temp.size()==2 &&temp.equals(names)){
			model.addAttribute("guard", 1);
		}else{
			model.addAttribute("guard", 2);
		}
		
		return "/default";
	}

	@SecurityAccessCheckable(resource = Login.class)
	@RequestMapping("/404")
	public String pageNofond() {
		return "/404";
	}

	@SecurityAccessCheckable(resource = Login.class)
	@RequestMapping("/500")
	public String serverError() {
		return "/500";
	}
}
