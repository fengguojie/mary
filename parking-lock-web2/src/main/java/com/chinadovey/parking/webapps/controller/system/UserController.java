package com.chinadovey.parking.webapps.controller.system;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinadovey.parking.acl.Login;
import com.chinadovey.parking.acl.SecuRoleManagerView;
import com.chinadovey.parking.acl.SecuUserManagerCret;
import com.chinadovey.parking.acl.SecuUserManagerDele;
import com.chinadovey.parking.acl.SecuUserManagerEdit;
import com.chinadovey.parking.acl.SecuUserManagerRank;
import com.chinadovey.parking.core.annotation.SecurityAccessCheckable;
import com.chinadovey.parking.core.mvc.AbstractBaseController;
import com.chinadovey.parking.core.mvc.AjaxResult;
import com.chinadovey.parking.core.mvc.AjaxResult.Result;
import com.chinadovey.parking.core.secu.SecuObject;
import com.chinadovey.parking.core.secu.SessionOpt;
import com.chinadovey.parking.webapps.biz.UserRelParkBiz;
import com.chinadovey.parking.webapps.biz.baseInfo.CompanyBiz;
import com.chinadovey.parking.webapps.biz.baseInfo.ParkBiz;
import com.chinadovey.parking.webapps.biz.system.RoleBiz;
import com.chinadovey.parking.webapps.biz.system.UserBiz;
import com.chinadovey.parking.webapps.pojo.Company;
import com.chinadovey.parking.webapps.pojo.Park;
import com.chinadovey.parking.webapps.pojo.Role;
import com.chinadovey.parking.webapps.pojo.User;
import com.chinadovey.parking.webapps.pojo.UserRelPark;
import com.chinadovey.parking.webapps.utils.StringUtil;

import edu.emory.mathcs.backport.java.util.Collections;
import net.sf.json.JSONObject;

/**
 * 用户控制层 
 * 
 * @author lizhao 
 */
@Controller
@RequestMapping("/system/user")
public class UserController extends AbstractBaseController {
	@Resource
	private UserBiz userBiz;
	@Resource
	private RoleBiz roleBiz;
	@Resource
	private CompanyBiz companyBiz;
	@Resource
	private ParkBiz parkBiz;
	@Resource
	private UserRelParkBiz userRelParkBiz;

	/**
	 * 跳转到列表页
	 * @author lizhao
	 * @return
	 */
	@SecurityAccessCheckable(resource = SecuUserManagerRank.class)
	@RequestMapping("/list")
	public String list() {
		return "/system/user_list";
	}
	
	@SecurityAccessCheckable(resource = SecuUserManagerRank.class)
	@RequestMapping("/Pagelist")
	public String list(int page,Model model) {
		model.addAttribute("Newpage",page);
		return "/system/user_list";
	}
	
	/**
	 * 获取分页数据
	 * @author lizhao
	 * @param page
	 * @param rows
	 * @return
	 */
	@SecurityAccessCheckable(resource = SecuUserManagerRank.class)
	@RequestMapping("/getList")
	@ResponseBody
	public Map<String, Object> getList(int page, int rows, String sort, String order, String search) {
		try {
			if (page > 0 && rows > 0) {
				Map<String, Object> map = userBiz.getList(page, rows, search, sort, order);
				return map;
			}
		} catch (Exception e) {
			logger.error("获取用户分页数据失败！", e);
		}
		return null;
	}



	/**
	 * 跳转到添加页面
	 * @author lizhao
	 * @return
	 */
	@SecurityAccessCheckable(resource = SecuUserManagerCret.class)
	@RequestMapping("/add")
	public String add(HttpSession session, Model model) {
		List<Company> companys = companyBiz.getAll();
		List<Park> parks = parkBiz.getAll();
		model.addAttribute("parks", parks);

		// 判断是否为空
		if (companys != null && !companys.isEmpty()) {
			model.addAttribute("companys", companys);
		}
		return "/system/user_add";
	}

	/**
	 * 保存新用户
	 * @author lizhao
	 * @param user
	 * @return
	 */
	@SecurityAccessCheckable(resource = SecuUserManagerCret.class)
	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(String user,HttpSession session) {
		try {
			if (!StringUtil.isEmpty(user)) {
				@SuppressWarnings("serial")
				User u = (User) JSONObject.toBean(JSONObject.fromObject(user), User.class,
						new HashMap<String, Object>() {
							{
								put("roles", Role.class);
							}
						});
				userBiz.save(u);
				//用户关联车锁后，保存关联信息进session
				String[] parkIds = u.getParkIds();
				List<Integer> ids = new ArrayList<Integer>();//要保存近session的数据
				for(String s:parkIds){
					int i = Integer.parseInt(s);
					ids.add(i);
				}
				session.setAttribute(SessionOpt.PARK_ID, ids);
				
				return new AjaxResult(Result.SUCCESS);
			}
		} catch (Exception e) {
			logger.error("保存用户失败!", e);
		}
		return new AjaxResult(Result.FAIL);
	}

	/**
	 * 跳转到修改页面
	 * @author lizhao
	 * @param id
	 * @param model
	 * @return
	 */
	@SecurityAccessCheckable(resource = SecuUserManagerEdit.class)
	@RequestMapping("/edit")
	public String edit(int id, Model model, HttpSession session, int page) {
		try {
			
			List<Company> companys = null;
			List<Park> parks = parkBiz.getAll();
			
			
			companys = companyBiz.getAll();
			
			List<UserRelPark> urps = userRelParkBiz.getByUserId(id);
			List<Park> hasparks = new ArrayList<Park>();//用户 已 关联的车场
			List<Park> noparks = new ArrayList<Park>();//用户 未 关联的车场
			List<Integer> hasparkIds = new ArrayList<Integer>();
			if(urps!=null && !urps.isEmpty()){
				for(UserRelPark urp:urps){
					Park park = parkBiz.findById(urp.getParkId());
					hasparks.add(park);
					hasparkIds.add(park.getId());
				}
				model.addAttribute("hasparks", hasparks);
			}
			//得到用户 未 关联的车场()
			noparks = parkBiz.getNoParks(hasparkIds);
			
			model.addAttribute("noparks", noparks);

			// 判断是否为空
			if (companys != null && !companys.isEmpty()) {
				model.addAttribute("companys", companys);
			}
			User user = userBiz.find(id);
			int[] ids = userBiz.getUserRoles(id);
			List<Role> roles = roleBiz.getAll();
			if (user != null) {
				model.addAttribute("user", user);
				model.addAttribute("ids", ids);
				model.addAttribute("roles", roles);
				model.addAttribute("colentpage",page);
				return "/system/user_edit";
			}
		} catch (Exception e) {
			logger.error("进入修改页面失败！", e);
		}
		return "500";
	}

	/**
	 * 更新
	 * @author lizhao
	 * @param user
	 * @return
	 */
	@SecurityAccessCheckable(resource = SecuUserManagerEdit.class)
	@RequestMapping("/update")
	@ResponseBody
	public AjaxResult update(String user, HttpSession session) {
		try {
			if (!StringUtil.isEmpty(user)) {
				@SuppressWarnings("serial")
				User u = (User) JSONObject.toBean(JSONObject.fromObject(user), User.class,
						new HashMap<String, Object>() {
							{
								put("roles", Role.class);
							}
						});
				userBiz.update(u);
				
				//用户关联车锁后，保存关联信息进session
				String[] parkIds = u.getParkIds();
				List<Integer> ids = new ArrayList<Integer>();//要保存近session的数据
				if(parkIds!=null && parkIds.length != 0){
					for(String s:parkIds){
						int i = Integer.parseInt(s);
						ids.add(i);
					}
				}
				session.setAttribute(SessionOpt.PARK_ID, ids);
				
				SecuObject secuObject = SessionOpt.getSecuObject(session);
				if (secuObject != null) {
					if (u.getId().equals(secuObject.getUserKey())) {
						User userObj = userBiz.find(u.getId());
						session.setAttribute(SessionOpt.SECU_IN_SESSION,
								userBiz.getSecuObject(userObj.getName(), userObj.getPassword()));
					}
				}
				return new AjaxResult(Result.SUCCESS);
			}
		} catch (Exception e) {
			logger.error("更新用户失败！", e);
		}
		return new AjaxResult(Result.FAIL);
	}

	/**
	 * 删除
	 * @author lizhao
	 * @param id
	 * @return
	 */
	@SecurityAccessCheckable(resource = SecuUserManagerDele.class)
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(int id) {
		try {
			if (id > 0) {
				userBiz.delete(id);
				return new AjaxResult(Result.SUCCESS);
			}
		} catch (Exception e) {
			logger.error("删除用户失败！", e);
		}
		return new AjaxResult(Result.FAIL);
	}

	/**
	 * 判断用户名是否存在
	 * @return
	 * @auther lizhao
	 */
	@SecurityAccessCheckable(resource = Login.class)
	@RequestMapping("/checkName")
	@ResponseBody
	public boolean checkName(String name) {
		return !userBiz.isNameExit(name);
	}

	/**
	 * 判断用户名是否存在
	 * @return
	 * @auther lizhao
	 */
	@SecurityAccessCheckable(resource = Login.class)
	@RequestMapping("/checkNameById")
	@ResponseBody
	public boolean checkNameById(String name, int id) {
		return !userBiz.isNameExitById(name, id);
	}

	/**
	 * 获取所有角色
	 * @author lizhao
	 * @return
	 */
	@SecurityAccessCheckable(resource = SecuRoleManagerView.class)
	@RequestMapping("/getRoles")
	@ResponseBody
	public List<Role> getRoles(HttpSession session) {
		try {
			List<Role> roles = new ArrayList<Role>();
			
			Integer userId = SessionOpt.getSecuObject(session).getUser().getId();
			User user = userBiz.find(userId);
			if(user.getName().equals("admin")){ //admin是超级管理员,可以看到所有角色
				roles = roleBiz.getAll();
			}else{
				roles = roleBiz.getByUserId(userId);
			}
			
			if (roles != null && !roles.isEmpty()) {
				return roles;
			}
		} catch (Exception e) {
			logger.error("获取角色失败！", e);
		}
		return null;
	}
	/**
	 * 获取所有停车场
	 * @author lizhao
	 * @return
	 */
	@SecurityAccessCheckable(resource = SecuRoleManagerView.class)
	@RequestMapping("/getParks")
	@ResponseBody
	public List<Park> getParks() {
		try {
			List<Park> parks = parkBiz.getAll();
			if (parks != null && !parks.isEmpty()) {
				return parks;
			}
		} catch (Exception e) {
			logger.error("获取角色失败！", e);
		}
		return null;
	}

	/**
	 * 获取用户所有角色
	 * @author lizhao
	 * @return
	 */
	@SecurityAccessCheckable(resource = SecuRoleManagerView.class)
	@RequestMapping("/getUserRoles")
	@ResponseBody
	public List<Role> getUserRoles(int id) {
		try {
			List<Role> roles = userBiz.getRolesByUserId(id);
			if (roles != null && !roles.isEmpty()) {
				return roles;
			}
		} catch (Exception e) {
			logger.error("获取角色失败！", e);
		}
		return null;
	}

	/**
	 * 获取不是用户所有角色
	 * @author lizhao
	 * @return
	 */
	@SecurityAccessCheckable(resource = SecuRoleManagerView.class)
	@RequestMapping("/getNoUserRoles")
	@ResponseBody
	public List<Role> getNoUserRoles(int id) {
		try {
			List<Role> roles = userBiz.getNoRolesByUserId(id);
			if (roles != null && !roles.isEmpty()) {
				return roles;
			}
		} catch (Exception e) {
			logger.error("获取角色失败！", e);
		}
		return null;
	}

	/**
	 * 验证密码是否正确
	 * @author lizhao
	 * @param id
	 * @param oldPassword
	 * @return
	 */
	@SecurityAccessCheckable(resource = Login.class)
	@RequestMapping("/checkPassword")
	@ResponseBody
	public boolean checkPassword(int id, String oldPassword) {
		return userBiz.isPasswordCorrect(id, oldPassword);
	}
	/**
	 * 初始化密码
	 * @author lizhao
	 * @param ids
	 * @param session
	 * @return
	 */
	@SecurityAccessCheckable(resource = Login.class)
	@RequestMapping("/resetPassword")
	@ResponseBody
	public AjaxResult delete(String ids,HttpSession session) {
		try {
			if (ids.length() == 1) {
				userBiz.resetPassword(Integer.parseInt(ids));
			}else{
				String[] ds = ids.split(",");
				for(int i=0;i<ds.length;i++){
					userBiz.resetPassword(Integer.parseInt(ds[i]));
				}
			}
				return new AjaxResult(Result.SUCCESS);
		} catch (Exception e) {
			logger.error("初始化密码失败！", e);
		}
		return new AjaxResult(Result.FAIL);
	}
	
	
	
	

}
