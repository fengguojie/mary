package com.chinadovey.parking.webapps.controller.baseInfo;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.chinadovey.parking.webapps.biz.baseInfo.CompanyBiz;
import com.chinadovey.parking.webapps.pojo.Company;
import com.chinadovey.parking.webapps.utils.StringUtil;

import net.sf.json.JSONObject;

/**
 * 机构管理
 * @author lizhao
 */
@Controller
@RequestMapping("/baseInfo/company")
public class CompanyController extends AbstractBaseController {
	@Autowired
	private CompanyBiz companyBiz;

	/**
	 * 跳转到列表页
	 * 
	 * @author lizhao
	 * @return
	 */
	@SecurityAccessCheckable(resource = BaseCompany.class)
	@RequestMapping("/list")
	public String list() {
		return "/baseInfo/company_list";
	}
	@SecurityAccessCheckable(resource = BaseCompany.class)
	@RequestMapping("/Pagelist")
	public String list(int page,Model model) {
		model.addAttribute("Newpage",page);
		return "/baseInfo/company_list";
	}
	/**
	 * 获取分页数据
	 * 
	 * @author lizhao
	 * @param page
	 * @param rows
	 * @return
	 */
	@SecurityAccessCheckable(resource = BaseCompany.class)
	@RequestMapping("/getList")
	@ResponseBody
	public Map<String, Object> getList(HttpSession session, int page, int rows, String sort, String order,
			String search) {
		try {
			if (page > 0 && rows > 0) {
				Map<String, Object> map = companyBiz.getList(page, rows, search, sort, order);
				return map;
			}
		} catch (Exception e) {
			logger.error("获取停车场分页数据失败！", e);
		}
		return null;
	}

	

	

	/**
	 * 跳转到添加页面
	 * 
	 * @author lizhao
	 * @return
	 */
	@SecurityAccessCheckable(resource = BaseCompany.class)
	@RequestMapping("/add")
	public String add() {
		return "/baseInfo/company_add";
	}
	/**
	 * 添加页面  检查机构名称是否存在
	 * 
	 * @author lizhao
	 * @return
	 */
	@SecurityAccessCheckable(resource = BaseCompany.class)
	@RequestMapping("/checkName")
	@ResponseBody
	public boolean checkName(String name) {
		
		boolean isExist=companyBiz.checkName(name);
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
	@SecurityAccessCheckable(resource = BaseCompany.class)
	@RequestMapping("/checkNameById")
	@ResponseBody
	public boolean checkNameById(int id,String name) {
		
		boolean isExist=companyBiz.checkNameById(id,name);
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
	@SecurityAccessCheckable(resource = BaseCompany.class)
	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(String company) {
		try {
			if (!StringUtil.isEmpty(company)) {
				Company _company = (Company) JSONObject.toBean(JSONObject.fromObject(company), Company.class);
				// 添加fgj
				_company.setAppId(StringUtil.randomNumber(8));
				_company.setToken(StringUtil.randomNumber(8));
				_company.setCompanyNo(StringUtil.randomNumber(12));//机构编号  唯一标识
				companyBiz.save(_company);
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
	@SecurityAccessCheckable(resource = BaseCompany.class)
	@RequestMapping("/edit")
	public String edit(int id, Model model,int page) {
		try {
			Company company = companyBiz.find(id);
			if (company != null) {
				model.addAttribute("company", company);
				model.addAttribute("colentpage",page);
				return "/baseInfo/company_edit";
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
	@SecurityAccessCheckable(resource = BaseCompany.class)
	@RequestMapping("/update")
	@ResponseBody
	public AjaxResult update(String company) {
		try {
			if (!StringUtil.isEmpty(company)) {
				Company _company = (Company) JSONObject.toBean(JSONObject.fromObject(company), Company.class);
				companyBiz.update(_company);
				return new AjaxResult(Result.SUCCESS);
			}
		} catch (Exception e) {
			logger.error("更新停车场失败！", e);
		}
		return new AjaxResult(Result.FAIL);
	}

	

	/**
	 * 删除
	 * 
	 * @author lizhao
	 * @param id
	 * @return
	 */
	@SecurityAccessCheckable(resource = BaseCompany.class)
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(int id) {
		try {
			if (id > 0) {
				companyBiz.delete(id);
				return new AjaxResult(Result.SUCCESS);
			}
		} catch (Exception e) {
			logger.error("删除机构失败！", e);
		}
		return new AjaxResult(Result.FAIL);
	}

	/**
	 * 得到Companys对象
	 * 
	 * @param session
	 * @param model
	 */
	@SecurityAccessCheckable(resource = Login.class)
	@RequestMapping("/getCompanys")
	@ResponseBody
	public List<Company> getCompanys( Model model) {
		
		List<Company> companys = companyBiz.getAll();

		return companys;
	}

	
}
