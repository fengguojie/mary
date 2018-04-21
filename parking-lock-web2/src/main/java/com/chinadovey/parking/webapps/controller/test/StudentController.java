package com.chinadovey.parking.webapps.controller.test;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinadovey.parking.acl.BaseCarCret;
import com.chinadovey.parking.acl.BaseCarDele;
import com.chinadovey.parking.acl.BaseCarEdit;
import com.chinadovey.parking.acl.BaseCarSpaceRank;
import com.chinadovey.parking.core.annotation.SecurityAccessCheckable;
import com.chinadovey.parking.core.mvc.AbstractBaseController;
import com.chinadovey.parking.core.mvc.AjaxResult;
import com.chinadovey.parking.core.mvc.AjaxResult.Result;
import com.chinadovey.parking.webapps.biz.StudentBiz;
import com.chinadovey.parking.webapps.pojo.Student;
import com.chinadovey.parking.webapps.utils.StringUtil;

import net.sf.json.JSONObject;


@Controller
@RequestMapping("/test/student")
public class StudentController extends AbstractBaseController {
	@Resource
	private StudentBiz studentBiz;
	
	             
    @SecurityAccessCheckable(resource = BaseCarSpaceRank.class)
	@RequestMapping("/list")
	public String list(HttpSession session, Model model) {
		return "/baseInfo/student_list";
	}
	/**
	 * 返回列表页
	 * wy
	 * 
	 * @param page
	 * @param model
	 * @return
	 */
	@SecurityAccessCheckable(resource = BaseCarSpaceRank.class)
	@RequestMapping("/Pagelist")
	public String list(int page, Model model, HttpSession session) {
		model.addAttribute("Newpage",page);
		return "/baseInfo/student_list";
	}
	
	@SecurityAccessCheckable(resource = BaseCarSpaceRank.class)
	@RequestMapping("/getList")
	@ResponseBody
	public Map<String, Object> getList(HttpSession session, int page, int rows, String sort, String order, String search) {
		try {
			if (page > 0 && rows > 0) {
				Map<String, Object> map = studentBiz.getList( page, rows, sort, order, search);
				return map;
			}
		} catch (Exception e) {
			logger.error("获取网关分页数据失败！", e);
		}
		return null;
	}
	
	@SecurityAccessCheckable(resource = BaseCarCret.class)
	@RequestMapping("/add")
	public String add(HttpSession session, Model model) {
		try {
			return "/baseInfo/carSpace_add";
		} catch (Exception e) {
			logger.error("进入车位添加页面失败！", e);
			return "500";
		}
	}
	
	@SecurityAccessCheckable(resource = BaseCarCret.class)
	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(String student, HttpSession session) {
		try {
			if (!StringUtil.isEmpty(student)) {
				Student student2 = (Student) JSONObject.toBean(JSONObject.fromObject(student), Student.class);
				student2.setAddTime(new Date());
				studentBiz.add(student2);
				return new AjaxResult(Result.SUCCESS);
			}
		} catch (Exception e) {
			logger.error("保存车位失败!", e);
		}
		return new AjaxResult(Result.FAIL);
	}
	
	@SecurityAccessCheckable(resource = BaseCarEdit.class)
	@RequestMapping("/edit")
	public String edit(int id, int page, Model model, HttpSession session) {
		try {
			
			model.addAttribute("colentpage", page);
			return "/baseInfo/carSpace_edit";
		} catch (Exception e) {
			logger.error("进入修改页面失败！", e);
		}
		return "500";
	}
	
	@SecurityAccessCheckable(resource = BaseCarEdit.class)
	@RequestMapping("/update")
	@ResponseBody
	public AjaxResult update(String carSpace) {
		try {
			
				return new AjaxResult(Result.SUCCESS);
			
		} catch (Exception e) {
			logger.error("更新车位失败！", e);
		}
		return new AjaxResult(Result.FAIL);
	}
	
	@SecurityAccessCheckable(resource = BaseCarDele.class)
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(int id) {
		try {
			if (id > 0) {
				studentBiz.delete(id);
				return new AjaxResult(Result.SUCCESS);
			}
		} catch (Exception e) {
			logger.error("删除车位失败！", e);
		}
		return new AjaxResult(Result.FAIL);
	}
	
	
	
}
