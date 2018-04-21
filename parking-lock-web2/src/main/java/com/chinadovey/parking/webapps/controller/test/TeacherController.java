package com.chinadovey.parking.webapps.controller.test;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinadovey.parking.acl.BaseCompany;
import com.chinadovey.parking.acl.Login;
import com.chinadovey.parking.core.annotation.SecurityAccessCheckable;
import com.chinadovey.parking.core.mvc.AbstractBaseController;
import com.chinadovey.parking.core.mvc.AjaxResult;
import com.chinadovey.parking.core.mvc.AjaxResult.Result;
import com.chinadovey.parking.webapps.biz.test.TeacherBiz;
import com.chinadovey.parking.webapps.pojo.Company;
import com.chinadovey.parking.webapps.pojo.Teacher;
import com.chinadovey.parking.webapps.utils.StringUtil;

import net.sf.json.JSONObject;


@Controller
@RequestMapping("/test/teacher")
public class TeacherController extends AbstractBaseController {
	@Resource
	private TeacherBiz teacherBiz;
	
	             
    @SecurityAccessCheckable(resource = Login.class)
	@RequestMapping("/list")
	public String list(HttpSession session, Model model) {
		return "/test/teacher_list";
	}
	
	
	@SecurityAccessCheckable(resource = Login.class)
	@RequestMapping("/getList")
	@ResponseBody
	public Map<String, Object> getList(HttpSession session, int page, int rows, String sort, String order, String search) {
		try {
			if (page > 0 && rows > 0) {
				Map<String, Object> map = teacherBiz.getList( page, rows, sort, order, search);
				return map;
			}
		} catch (Exception e) {
			logger.error("获取网关分页数据失败！", e);
		}
		return null;
	}
	
	/**
	 * 跳转到添加页面
	 * 
	 * @author lizhao
	 * @return
	 */
	@SecurityAccessCheckable(resource = Login.class)
	@RequestMapping("/add")
	public String add() {
		return "/test/teacher_add";
	}
	/**
	 * 添加页面  检查机构名称是否存在
	 * 
	 * @author lizhao
	 * @return
	 */
	@SecurityAccessCheckable(resource = Login.class)
	@RequestMapping("/checkName")
	@ResponseBody
	public boolean checkName(String teacherName) {
		
		boolean isExist=teacherBiz.checkName(teacherName);
		if(isExist){
			return false;
		}
		return true;
	}
	/**
	 * 修改页面 检查机构名称是否存在
	 * 
	 * @author lizhao
	 * @return
	 */
	@SecurityAccessCheckable(resource = Login.class)
	@RequestMapping("/checkNameById")
	@ResponseBody
	public boolean checkNameById(int teacherId,String teacherName) {
		
		boolean isExist=teacherBiz.checkNameById(teacherId,teacherName);
		if(isExist){
			return false;
		}
		return true;
	}

	/**
	 * 保存
	 * 
	 * @author lizhao
	 * @param Park
	 * @return
	 */
	@SecurityAccessCheckable(resource = Login.class)
	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(String teacher) {
		try {
			if (!StringUtil.isEmpty(teacher)) {
				Teacher _teacher = (Teacher) JSONObject.toBean(JSONObject.fromObject(teacher), Teacher.class);
				teacherBiz.add(_teacher);
				return new AjaxResult(Result.SUCCESS);
			}
		} catch (Exception e) {
			logger.error("保存停车场失败!", e);
		}
		return new AjaxResult(Result.FAIL);
	}
	
	/**
	 * 跳转到修改页面
	 * 
	 * @author lizhao
	 * @param id
	 * @param model
	 * @return
	 */
	@SecurityAccessCheckable(resource = Login.class)
	@RequestMapping("/edit")
	public String edit(int id, Model model,int page) {
		try {
			Teacher teacher = teacherBiz.find(id);
			if (teacher != null) {
				model.addAttribute("teacher", teacher);
				return "/test/teacher_edit";
			}
		} catch (Exception e) {
			logger.error("进入修改页面失败！", e);
		}
		return "500";
	}

	/**
	 * 更新
	 * 
	 * @author lizhao
	 * @param Park
	 * @return
	 */
	@SecurityAccessCheckable(resource = Login.class)
	@RequestMapping("/update")
	@ResponseBody
	public AjaxResult update(String teacher) {
		try {
			if (!StringUtil.isEmpty(teacher)) {
				Teacher _teacher = (Teacher) JSONObject.toBean(JSONObject.fromObject(teacher), Teacher.class);
				teacherBiz.update(_teacher);
				return new AjaxResult(Result.SUCCESS);
			}
		} catch (Exception e) {
			logger.error("更新停车场失败！", e);
		}
		return new AjaxResult(Result.FAIL);
	}
	
	@SecurityAccessCheckable(resource = Login.class)
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(int teacherId) {
		try {
			if (teacherId > 0) {
				teacherBiz.delete(teacherId);
				return new AjaxResult(Result.SUCCESS);
			}
		} catch (Exception e) {
			logger.error("删除车位失败！", e);
		}
		return new AjaxResult(Result.FAIL);
	}
	
	
	
}
