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
import com.chinadovey.parking.acl.BaseCompany;
import com.chinadovey.parking.acl.Login;
import com.chinadovey.parking.core.annotation.SecurityAccessCheckable;
import com.chinadovey.parking.core.mvc.AbstractBaseController;
import com.chinadovey.parking.core.mvc.AjaxResult;
import com.chinadovey.parking.core.mvc.AjaxResult.Result;
import com.chinadovey.parking.webapps.biz.StudentBiz;
import com.chinadovey.parking.webapps.biz.test.PersonBiz;
import com.chinadovey.parking.webapps.pojo.Student;
import com.chinadovey.parking.webapps.utils.StringUtil;

import net.sf.json.JSONObject;


@Controller
@RequestMapping("/test/person")
public class PersonController extends AbstractBaseController {
	@Resource
	private PersonBiz personBiz;
	
	             
    @SecurityAccessCheckable(resource = Login.class)
	@RequestMapping("/list")
	public String list(HttpSession session, Model model) {
		return "/test/person_list";
	}
	
	
	@SecurityAccessCheckable(resource = Login.class)
	@RequestMapping("/getList")
	@ResponseBody
	public Map<String, Object> getList(HttpSession session, int page, int rows, String sort, String order, String search) {
		try {
			if (page > 0 && rows > 0) {
				Map<String, Object> map = personBiz.getList( page, rows, sort, order, search);
				return map;
			}
		} catch (Exception e) {
			logger.error("获取网关分页数据失败！", e);
		}
		return null;
	}
	
	/**
	 * 删除
	 * 
	 * @author lizhao
	 * @param id
	 * @return
	 */
	@SecurityAccessCheckable(resource = Login.class)
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(int id) {
		try {
			if (id > 0) {
				personBiz.delete(id);
				return new AjaxResult(Result.SUCCESS);
			}
		} catch (Exception e) {
			logger.error("删除机构失败！", e);
		}
		return new AjaxResult(Result.FAIL);
	}
	
	
	
}
