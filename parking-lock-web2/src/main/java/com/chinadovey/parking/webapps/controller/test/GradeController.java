package com.chinadovey.parking.webapps.controller.test;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinadovey.parking.acl.BaseCarDele;
import com.chinadovey.parking.acl.Login;
import com.chinadovey.parking.core.annotation.SecurityAccessCheckable;
import com.chinadovey.parking.core.mvc.AbstractBaseController;
import com.chinadovey.parking.core.mvc.AjaxResult;
import com.chinadovey.parking.core.mvc.AjaxResult.Result;
import com.chinadovey.parking.webapps.biz.test.GradeBiz;


@Controller
@RequestMapping("/test/grade")
public class GradeController extends AbstractBaseController {
	@Resource
	private GradeBiz gradeBiz;
	
	             
    @SecurityAccessCheckable(resource = Login.class)
	@RequestMapping("/list")
	public String list(HttpSession session, Model model) {
		return "/test/grade_list";
	}
	
	
	@SecurityAccessCheckable(resource = Login.class)
	@RequestMapping("/getList")
	@ResponseBody
	public Map<String, Object> getList(HttpSession session, int page, int rows, String sort, String order, String search) {
		try {
			if (page > 0 && rows > 0) {
				Map<String, Object> map = gradeBiz.getList( page, rows, sort, order, search);
				return map;
			}
		} catch (Exception e) {
			logger.error("获取网关分页数据失败！", e);
		}
		return null;
	}
	
	@SecurityAccessCheckable(resource = BaseCarDele.class)
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(int id) {
		try {
			if (id > 0) {
				gradeBiz.delete(id);
				return new AjaxResult(Result.SUCCESS);
			}
		} catch (Exception e) {
			logger.error("删除车位失败！", e);
		}
		return new AjaxResult(Result.FAIL);
	}
	
	
	
}
