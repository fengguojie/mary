package com.chinadovey.parking.webapps.controller.test;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinadovey.parking.acl.Login;
import com.chinadovey.parking.core.annotation.SecurityAccessCheckable;
import com.chinadovey.parking.core.mvc.AbstractBaseController;
import com.chinadovey.parking.core.mvc.AjaxResult;
import com.chinadovey.parking.core.mvc.AjaxResult.Result;
import com.chinadovey.parking.webapps.biz.test.PpGoodsBiz;
import com.chinadovey.parking.webapps.pojo.Pp_Goods;
import com.chinadovey.parking.webapps.utils.StringUtil;

import net.sf.json.JSONObject;


@Controller
@RequestMapping("/test/pp_Goods")
public class PpGoodsController extends AbstractBaseController {
	@Resource
	private PpGoodsBiz ppGoodsBiz;
	
	             
    @SecurityAccessCheckable(resource = Login.class)
	@RequestMapping("/list")
	public String list(HttpSession session, Model model) {
		return "/test/ppGoods_list";
	}
	
	
	@SecurityAccessCheckable(resource = Login.class)
	@RequestMapping("/getList")
	@ResponseBody
	public Map<String, Object> getList(HttpSession session, int page, int rows, String sort, String order, String search) {
		try {
			if (page > 0 && rows > 0) {
				Map<String, Object> map = ppGoodsBiz.getList( page, rows, sort, order, search);
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
				ppGoodsBiz.delete(id);
				return new AjaxResult(Result.SUCCESS);
			}
		} catch (Exception e) {
			logger.error("删除机构失败！", e);
		}
		return new AjaxResult(Result.FAIL);
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
		return "/test/ppGoods_add";
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
	public boolean checkName(String gname) {
		
		boolean isExist=ppGoodsBiz.checkName(gname);
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
	public boolean checkNameById(int id,String gname) {
		
		boolean isExist=ppGoodsBiz.checkNameById(id,gname);
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
	public AjaxResult save(String pp_Goods) {
		try {
			if (!StringUtil.isEmpty(pp_Goods)) {
				Pp_Goods _pp_Goods = (Pp_Goods) JSONObject.toBean(JSONObject.fromObject(pp_Goods), Pp_Goods.class);
				ppGoodsBiz.add(_pp_Goods);
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
			Pp_Goods pp_Goods = ppGoodsBiz.find(id);
			if (pp_Goods != null) {
				model.addAttribute("pp_Goods", pp_Goods);
				return "/test/ppGoods_edit";
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
	public AjaxResult update(String pp_Goods) {
		try {
			if (!StringUtil.isEmpty(pp_Goods)) {
				Pp_Goods _pp_Goods = (Pp_Goods) JSONObject.toBean(JSONObject.fromObject(pp_Goods), Pp_Goods.class);
				ppGoodsBiz.update(_pp_Goods);
				return new AjaxResult(Result.SUCCESS);
			}
		} catch (Exception e) {
			logger.error("更新停车场失败！", e);
		}
		return new AjaxResult(Result.FAIL);
	}
	
	
}
