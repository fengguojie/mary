package com.chinadovey.parking.webapps.controller.baseInfo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinadovey.parking.acl.BaseParkCret;
import com.chinadovey.parking.acl.BaseParkDele;
import com.chinadovey.parking.acl.BaseParkEdit;
import com.chinadovey.parking.acl.BaseParkRank;
import com.chinadovey.parking.acl.BaseParkView;
import com.chinadovey.parking.acl.Login;
import com.chinadovey.parking.core.annotation.SecurityAccessCheckable;
import com.chinadovey.parking.core.mvc.AbstractBaseController;
import com.chinadovey.parking.core.mvc.AjaxResult;
import com.chinadovey.parking.core.mvc.AjaxResult.Result;
import com.chinadovey.parking.core.secu.SessionOpt;
import com.chinadovey.parking.webapps.biz.baseInfo.CompanyBiz;
import com.chinadovey.parking.webapps.biz.baseInfo.ParkBiz;
import com.chinadovey.parking.webapps.pojo.ChargingRules;
import com.chinadovey.parking.webapps.pojo.Company;
import com.chinadovey.parking.webapps.pojo.Park;
import com.chinadovey.parking.webapps.pojo.base.ParkSubBase;
import com.chinadovey.parking.webapps.utils.StringUtil;

import net.sf.json.JSONObject;


@Controller
@RequestMapping("/baseInfo/park")
public class ParkController extends AbstractBaseController {
	@Autowired
	private ParkBiz parkBiz;
	@Autowired
	private CompanyBiz companyBiz;
	/**
	 * 跳转到车场管理页面
	 * @author lizhao
	 * @return
	 */
	@SecurityAccessCheckable(resource = BaseParkRank.class)
	@RequestMapping("/list")
	public String list() {
		return "/baseInfo/park_list";
	}
	@SecurityAccessCheckable(resource = BaseParkRank.class)
	@RequestMapping("/Pagelist")
	public String list(int page,Model model) {
		model.addAttribute("Newpage",page);
		return "/baseInfo/park_list";
	}
	/**
	 * 展示车场管理页面
	 * @author lizhao
	 * @return
	 */
	@SecurityAccessCheckable(resource = BaseParkRank.class)
	@RequestMapping("/getList")
	@ResponseBody
	public Map<String, Object> getList(HttpSession session, int page, int rows, String sort, String order,
			String search) {
		try {
			List<Integer> parkIds = (List<Integer>) session.getAttribute(SessionOpt.PARK_ID);
			Map<String, Object> map = null;
			if (page > 0 && rows > 0) {
				if (parkIds.size() > 0) {
					map = parkBiz.getList(page, rows, sort, order, search, parkIds);
				} else {
					map = parkBiz.getList(page, rows, sort, order, search,null);
				}
				return map;
			}
		} catch (Exception e) {
			logger.error("获取停车场分页数据失败！", e);
		}
		return null;
	}

	
	/**
	 * 跳转到添加车场页面
	 * @author lizhao
	 * @return
	 */
	@SecurityAccessCheckable(resource = BaseParkCret.class)
	@RequestMapping("/add")
	public String add(Model model) {
		List<Company> companys = companyBiz.getList();
		model.addAttribute("companys", companys);
		return "/baseInfo/park_add";
	}

	/**
	 * 保存车场
	 * @author lizhao
	 * @return
	 */
	@SecurityAccessCheckable(resource = BaseParkCret.class)
	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(String park) {
		try {
			if (!StringUtil.isEmpty(park)) {
				Park _park = (Park) JSONObject.toBean(JSONObject.fromObject(park), Park.class);
				parkBiz.save(_park);
				return new AjaxResult(Result.SUCCESS);
			}
		} catch (Exception e) {
			logger.error("保存停车场失败!", e);
		}
		return new AjaxResult(Result.FAIL);
	}

	/**
	 * 跳转到修改车场
	 * @author lizhao
	 * @return
	 */
	@SecurityAccessCheckable(resource = BaseParkEdit.class)
	@RequestMapping("/edit")
	public String edit(int id, Model model,int page) {
		try {
			Park park = parkBiz.findById(id);
			if (park != null) {
				model.addAttribute("park", park);
				List<Company> companys = companyBiz.getList();
				model.addAttribute("companys", companys);
				model.addAttribute("colentpage",page);
				return "/baseInfo/park_edit";
			}
		} catch (Exception e) {
			logger.error("进入修改页面失败！", e);
		}
		return "500";
	}

	/**
	 * 更新车场
	 * @author lizhao
	 * @return
	 */
	@SecurityAccessCheckable(resource = BaseParkEdit.class)
	@RequestMapping("/update")
	@ResponseBody
	public AjaxResult update(String park) {
		try {
			if (!StringUtil.isEmpty(park)) {
				Park _park = (Park) JSONObject.toBean(JSONObject.fromObject(park), Park.class);
				parkBiz.update(_park);
				return new AjaxResult(Result.SUCCESS);
			}
		} catch (Exception e) {
			logger.error("更新停车场失败！", e);
		}
		return new AjaxResult(Result.FAIL);
	}
	/**
	 * 删除车场
	 * @author lizhao
	 * @return
	 */
	@SecurityAccessCheckable(resource = BaseParkDele.class)
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(int id) {
		try {
			if (id > 0) {
				parkBiz.delete(id);
				return new AjaxResult(Result.SUCCESS);
			}
		} catch (Exception e) {
			logger.error("删除停车场失败！", e);
		}
		return new AjaxResult(Result.FAIL);
	}
	
	
	
	
	@SecurityAccessCheckable(resource = BaseParkEdit.class)
	@RequestMapping("/chargeRule")
	public String chargeRule(int id, Model model) {
		try {
		/*	ChargeRule chargeRule = chargeRuleBiz.getByParkId(id);
			if (chargeRule != null) {
				model.addAttribute("chargeRule", chargeRule);
			}
			model.addAttribute("parkId", id);*/
			return "/baseInfo/park_charge_rule";
		} catch (Exception e) {
			logger.error("进入修改页面失败！", e);
		}
		return "500";
	}

	
	@SecurityAccessCheckable(resource = BaseParkEdit.class)
	@RequestMapping("/updateChargeRule")
	@ResponseBody
	public AjaxResult updateChargeRule(String chargeRule) {
		try {
			if (!StringUtil.isEmpty(chargeRule)) {
				/*ChargeRule _chargeRule = (ChargeRule) JSONObject.toBean(JSONObject.fromObject(chargeRule),
						ChargeRule.class);
				if (_chargeRule.getId() != null && _chargeRule.getId() != 0) {
					chargeRuleBiz.update(_chargeRule);
				} else {
					chargeRuleBiz.save(_chargeRule);
				}*/
				return new AjaxResult(Result.SUCCESS);
			}
		} catch (Exception e) {
			logger.error("更新停车场收费规则失败！", e);
		}
		return new AjaxResult(Result.FAIL);
	}

	
	@SecurityAccessCheckable(resource = BaseParkView.class)
	@RequestMapping("/detail")
	public String detail(Model model, int id,int page) {
		try {
			ParkSubBase park = parkBiz.findSub(id);
			if (park != null) {
				model.addAttribute("park", park);
				ChargingRules cr = null;
				if(park.getChargingRulesId()!=null){
//					cr = chargingRulesBiz.find(park.getChargingRulesId());
				}
				model.addAttribute("chargingRules", cr);
				model.addAttribute("colentpage",page);
				return "/baseInfo/park_detail";
			}
		} catch (Exception e) {
			logger.error("进入详情页面失败！", e);
		}
		return "500";
	}



	
	@SecurityAccessCheckable(resource = BaseParkDele.class)
	@RequestMapping("/batchDelete")
	@ResponseBody
	public AjaxResult batchDelete(int[] ids) {
		try {
			if (ids.length > 0) {
				parkBiz.batchDelete(ids);
				return new AjaxResult(Result.SUCCESS);
			}
		} catch (Exception e) {
			logger.error("批量删除停车场失败！", e);
		}
		return new AjaxResult(Result.FAIL);
	}

	
	
	@SecurityAccessCheckable(resource = Login.class)
	@RequestMapping("/checkCode")
	@ResponseBody
	public boolean checkCode(String code) {
		return !parkBiz.isCodeExit(code);
	}

	
	@SecurityAccessCheckable(resource = Login.class)
	@RequestMapping("/checkCodeById")
	@ResponseBody
	public boolean checkCodeById(String code, int id) {
		return !parkBiz.isCodeExitById(code, id);
	}

	@SecurityAccessCheckable(resource = BaseParkEdit.class)
	@RequestMapping("getParkList")
	@ResponseBody
	public List<Park> getParkList(HttpSession session) {
		try {
			List<Integer> parkIds = (List<Integer>) session.getAttribute(SessionOpt.PARK_ID);
			List<Integer> onLines = null;
			List<Park> parks = null;
			if (parkIds.size() <= 0) {
				parks = parkBiz.getAll();
				onLines = parkBiz.getOnlineList();
			} else {
				parks = parkBiz.getAll(parkIds);
				onLines = parkBiz.getOnlineList(parkIds);
			}
			if (parks != null && !parks.isEmpty()) {
				for (Iterator it = parks.iterator(); it.hasNext();) {
					Park park = (Park) it.next();
					if (onLines.contains(park.getId())) {
						park.setEmpty(1);
					} else {
						park.setEmpty(0);
					}
				}
				return parks;
			}
		} catch (Exception e) {
			logger.error("获取停车场分页数据失败！", e);
		}
		return new ArrayList<Park>();
	}
	@SecurityAccessCheckable(resource = BaseParkRank.class)
	@RequestMapping("/getNoLinkList")
	@ResponseBody
	public Map<String, Object> getNoLinkList(HttpSession session, int page, int rows, String sort, String order,
			String search) {
		try {
			Map<String, Object> map = parkBiz.getList(page, rows, sort, order, search);
			return map;
		} catch (Exception e) {
			logger.error("获取停车场分页数据失败！", e);
		}
		return null;
	}

	@SecurityAccessCheckable(resource = BaseParkRank.class)
	@RequestMapping("/getListByUserId")
	@ResponseBody
	public Map<String, Object> getListByUserId(int page, int rows, String sort, String order, String search,
			Integer userId) {
		try {
			List<Integer> parkIds = parkBiz.getParkIdsByUserId(userId);
			if (parkIds.isEmpty()) {
				parkIds.add(0);
			}
			Map<String, Object> map = null;
			if (page > 0 && rows > 0) {
				map = parkBiz.getList(page, rows, sort, order, search, parkIds);
				return map;
			}
		} catch (Exception e) {
			logger.error("获取数据失败", e);
		}
		return null;
	}
}
