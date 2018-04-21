package com.chinadovey.parking.webapps.controller.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinadovey.parking.acl.Login;
import com.chinadovey.parking.acl.SecuRoleManagerCret;
import com.chinadovey.parking.acl.SecuRoleManagerDele;
import com.chinadovey.parking.acl.SecuRoleManagerEdit;
import com.chinadovey.parking.acl.SecuRoleManagerRank;
import com.chinadovey.parking.acl.SecuRoleManagerView;
import com.chinadovey.parking.core.annotation.SecurityAccessCheckable;
import com.chinadovey.parking.core.mvc.AbstractBaseController;
import com.chinadovey.parking.core.mvc.AjaxResult;
import com.chinadovey.parking.core.mvc.AjaxResult.Result;
import com.chinadovey.parking.core.secu.SecuObject;
import com.chinadovey.parking.core.secu.SessionOpt;
import com.chinadovey.parking.core.secu.SystemAuthorityHelper;
import com.chinadovey.parking.webapps.biz.system.RoleBiz;
import com.chinadovey.parking.webapps.biz.system.UserBiz;
import com.chinadovey.parking.webapps.biz.system.UserRelRoleBiz;
import com.chinadovey.parking.webapps.pojo.Authority;
import com.chinadovey.parking.webapps.pojo.Role;
import com.chinadovey.parking.webapps.pojo.User;
import com.chinadovey.parking.webapps.pojo.UserRelRole;
import com.chinadovey.parking.webapps.utils.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 角色控制层 Copyright ©2014 中瑞华清（北京）智能科技有限公司 http://zhrhq.com
 * 
 * @author 王生栋 2014-12-31 下午4:34:24
 */
@Controller
@RequestMapping("/system/role")
public class RoleController extends AbstractBaseController {
	@Resource
	private RoleBiz roleBiz;
	
	@Autowired
	private UserBiz userBiz;
	
	@Autowired
	private UserRelRoleBiz userRelRoleBiz;

	/**
	 * 显示列表页
	 * wy
	 * @param model
	 * @param session
	 * @return
	 */
	@SecurityAccessCheckable(resource = SecuRoleManagerRank.class)
	@RequestMapping("/list")
	public String list(Model model, HttpSession session) {
		return "/system/role_list";
	}
	/**
	 * 跳转页面
	 * wy
	 * @param page
	 * @param model
	 * @param session
	 * @return
	 */
	@SecurityAccessCheckable(resource = SecuRoleManagerRank.class)
	@RequestMapping("/Pagelist")
	public String list(int page, Model model, HttpSession session) {
		//從session中获得该登录用户的信息
		SecuObject secuObject = (SecuObject) session.getAttribute(SessionOpt.SECU_IN_SESSION);
		User user = userBiz.find(secuObject.getUser().getId());
		
		model.addAttribute("Newpage",page);
		model.addAttribute("userId", user.getId());
		return "/system/role_list";
	}
	/**
	 * 获取分页数据
	 * wy
	 * @param page
	 * @param rows
	 * @return
	 */
	@SecurityAccessCheckable(resource = SecuRoleManagerView.class)
	@RequestMapping("/getList")
	@ResponseBody
	public Map<String, Object> getList(int page, int rows, String sort, String order, String search, HttpSession session) {
		try {
			//從session中获得该登录用户的信息
			SecuObject secuObject = (SecuObject) session.getAttribute(SessionOpt.SECU_IN_SESSION);
			User user = userBiz.find(secuObject.getUser().getId());
			if(user != null){
				Map<String, Object> map = null;
				//判断登录用户是否是管理员
				if(user.getName().equals("admin")){
					if (page > 0 && rows > 0) {
						map = roleBiz.getList(page, rows, sort, order, search);
					}
				}else{
					//根据用户查询对应的所有角色信息
					List<UserRelRole> list = userRelRoleBiz.findByUserId(user.getId());
					List<Integer> roleList = new ArrayList<Integer>();
					if(list != null && !list.isEmpty()){
						for(UserRelRole userRelRole : list){
							roleList.add(userRelRole.getRoleId());
						}
					}
					map = roleBiz.getList(roleList, page, rows, sort, order, search);
				}
				return map;
			}
		} catch (Exception e) {
			logger.error("获取角色分页数据失败！", e);
		}
		return null;
	}
	/**
	 * 跳转到添加页面
	 * wy
	 * @param roleIds
	 * @param model
	 * @return
	 */
	@SecurityAccessCheckable(resource = SecuRoleManagerCret.class)
	@RequestMapping("/add")
	public String add(String roleIds, Model model) {
		return "/system/role_add";
	}
	/**
	 * 显示权限列表
	 * wy
	 * @param roleIds
	 * @return
	 */
	@SecurityAccessCheckable(resource = SecuRoleManagerView.class)
	@RequestMapping("/getAddRoleAuthorityList")
	@ResponseBody
	public JSONArray getAddRoleAuthorityList(HttpSession session) {
		List<Integer> roleList = new ArrayList<Integer>();
		//從session中获得该登录用户的信息
		SecuObject secuObject = (SecuObject) session.getAttribute(SessionOpt.SECU_IN_SESSION);
		User user = userBiz.find(secuObject.getUser().getId());
		if(user.getName().equals("admin")){//显示所有权限
			return JSONArray.fromObject(SystemAuthorityHelper.searchControllerAuthority());
		}else{
			//根据用户查询对应的所有角色信息
			List<UserRelRole> list = userRelRoleBiz.findByUserId(user.getId());
			if(list != null && !list.isEmpty()){
				for(UserRelRole userRelRole : list){
					roleList.add(userRelRole.getRoleId());
				}
			}
		}
		//查询用户对应的权限
		List<Authority> authoritys = roleBiz.getInfoByRoleIds(roleList);
		return JSONArray.fromObject(authoritys);
	}
	/**
	 * 保存
	 * @author wy
	 * @param Role
	 * @return
	 */
	@SecurityAccessCheckable(resource = SecuRoleManagerCret.class)
	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(String role) {
		try {
			if (!StringUtil.isEmpty(role)) {
				@SuppressWarnings("serial")
				Role rol = (Role) JSONObject.toBean(JSONObject.fromObject(role), Role.class,
					new HashMap<String, Object>() {
						{
							put("authoritys", Authority.class);
						}

					});
				roleBiz.save(rol);
				return new AjaxResult(Result.SUCCESS);
			}
		} catch (Exception e) {
			logger.error("保存角色失败!", e);
		}
		return new AjaxResult(Result.FAIL);
	}
	/**
	 * 修改
	 * wy
	 * @param roleId
	 * @param page
	 * @param model
	 * @return
	 */
	@SecurityAccessCheckable(resource = SecuRoleManagerEdit.class)
	@RequestMapping("/edit")
	public String edit(int page, int roleId, Model model, HttpSession session) {
		try {
			List<Authority> authoritys = null;
			//從session中获得该登录用户的信息
			SecuObject secuObject = (SecuObject) session.getAttribute(SessionOpt.SECU_IN_SESSION);
			User user = userBiz.find(secuObject.getUser().getId());
			if(user.getName().equals("admin")){//查询所有权限
				authoritys = SystemAuthorityHelper.searchControllerAuthority();
			}else{
				//根据用户查询对应的所有角色信息
				List<UserRelRole> list = userRelRoleBiz.findByUserId(user.getId());
				List<Integer> roleList = new ArrayList<Integer>();
				if(list != null && !list.isEmpty()){
					for(UserRelRole userRelRole : list){
						roleList.add(userRelRole.getRoleId());
					}
				}
				//查询用户对应的权限
				authoritys = roleBiz.getInfoByRoleIds(roleList);
			}
			String[] resources = roleBiz.getAutorityResources(roleId);
			//查询角色信息
			Role role = roleBiz.find(roleId);
			if(role != null){
				model.addAttribute("authoritys", authoritys);
				model.addAttribute("resources", resources);
				model.addAttribute("colentpage",page);
				model.addAttribute("role", role);
				return "/system/role_edit";
			}
		} catch (Exception e) {
			logger.error("进入修改页面失败！", e);
		}
		return "500";
	}
	/**
	 * 更新
	 * @author wy
	 * @param Role
	 * @return
	 */
	@SecurityAccessCheckable(resource = SecuRoleManagerEdit.class)
	@RequestMapping("/update")
	@ResponseBody
	public AjaxResult update(String role) {
		try {
			if (!StringUtil.isEmpty(role)) {
				Role r = (Role) JSONObject.toBean(JSONObject.fromObject(role), Role.class,
						new HashMap<String, Object>() {
							{
								put("authoritys", Authority.class);
							}

						});
				roleBiz.update(r);
				return new AjaxResult(Result.SUCCESS);
			}
		} catch (Exception e) {
			logger.error("更新角色失败！", e);
		}
		return new AjaxResult(Result.FAIL);
	}
	/**
	 * 跳转到详情页面
	 * @author wy
	 * @param model
	 * @param id
	 * @return
	 */
	@SecurityAccessCheckable(resource = SecuRoleManagerView.class)
	@RequestMapping("/detail")
	public String detail(Model model, int id,int page) {
		try {
			Role role = roleBiz.find(id);
			if (role != null) {
				model.addAttribute("role", role);
				model.addAttribute("colentpage",page);
				return "/system/role_detail";
			}
		} catch (Exception e) {
			logger.error("进入详情页面失败！", e);
		}
		return "500";
	}
	/**
	 * 删除
	 * @author wy
	 * @param id
	 * @return
	 */
	@SecurityAccessCheckable(resource = SecuRoleManagerDele.class)
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(int id) {
		try {
			if (id > 0) {
				roleBiz.delete(id);
				return new AjaxResult(Result.SUCCESS);
			}
		} catch (Exception e) {
			logger.error("删除角色失败！", e);
		}
		return new AjaxResult(Result.FAIL);
	}
	/**
	 * 获取系统内所有权限
	 * @return
	 */
	@SecurityAccessCheckable(resource = SecuRoleManagerView.class)
	@RequestMapping("/getAuthorityList")
	@ResponseBody
	public JSONArray getSearchControllerAuthority() {
		return JSONArray.fromObject(SystemAuthorityHelper.searchControllerAuthority());
	}
	/**
	 * 获取角色权限
	 * @return
	 */
	@SecurityAccessCheckable(resource = SecuRoleManagerView.class)
	@RequestMapping("/getRoleAuthorityList")
	@ResponseBody
	public JSONArray getRoleAuthority(int id) {
		List<Authority> authoritys = roleBiz.getAutoritys(id);
		return JSONArray.fromObject(authoritys);
	}
	/**
	 * 判断名称是否存在
	 * @param name
	 * @return
	 */
	@SecurityAccessCheckable(resource = Login.class)
	@RequestMapping("/checkName")
	@ResponseBody
	public boolean checkName(String name) {
		return !roleBiz.isNameExit(name);
	}

	/**
	 * 判断角色是否存在
	 * @return
	 * @auther wy
	 */
	@SecurityAccessCheckable(resource = Login.class)
	@RequestMapping("/checkNameById")
	@ResponseBody
	public boolean checkNameById(String name, int id) {
		return !roleBiz.isNameExitById(name, id);
	}
	
}
