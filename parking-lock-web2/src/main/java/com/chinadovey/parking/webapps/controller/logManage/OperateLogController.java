package com.chinadovey.parking.webapps.controller.logManage;

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

import com.chinadovey.parking.acl.LogCarlockRank;
import com.chinadovey.parking.acl.LogGatewayRank;
import com.chinadovey.parking.acl.LogOperateRank;
import com.chinadovey.parking.acl.Login;
import com.chinadovey.parking.core.annotation.SecurityAccessCheckable;
import com.chinadovey.parking.core.mvc.AbstractBaseController;
import com.chinadovey.parking.core.mvc.AjaxResult;
import com.chinadovey.parking.core.mvc.AjaxResult.Result;
import com.chinadovey.parking.core.secu.SessionOpt;
import com.chinadovey.parking.webapps.biz.CarlockLogBiz;
import com.chinadovey.parking.webapps.biz.GatewayLogBiz;
import com.chinadovey.parking.webapps.biz.logManage.OperateLogBiz;
import com.chinadovey.parking.webapps.pojo.OperateLog;
import com.chinadovey.parking.webapps.pojo.CarlockLog;
import com.chinadovey.parking.webapps.pojo.base.GatewayLog;
import com.chinadovey.parking.webapps.utils.ConfUtils;
import com.chinadovey.parking.webapps.utils.HttpClientUtil;
import com.chinadovey.parking.webapps.utils.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Controller
@RequestMapping("/logManage/operateLog")
public class OperateLogController extends AbstractBaseController {
	@Resource
	private OperateLogBiz operateLogBiz;
	@Autowired
	private GatewayLogBiz gatewayLogBiz;
	@Autowired
	private CarlockLogBiz carlockLogBiz;

	/**
	 * 跳转到车锁控制日志
	 * @author lizhao
	 * @return
	 */
	@SecurityAccessCheckable(resource = LogOperateRank.class)
	@RequestMapping("/default")
	public String list() {
		return "/logManage/operateLog";
	}
	/**
	 * 车锁控制日志，列表
	 * 
	 * @author lizhao
	 * @return
	 */
	@SecurityAccessCheckable(resource = LogOperateRank.class)
	@RequestMapping("/getList")
	@ResponseBody
	public Map<String, Object> getList(HttpSession session, int page, int rows,
			String sort, String order, String search, String start, String end,Integer type) {
		try {
			if (page > 0 && rows > 0) {
				Map<String, Object> map = operateLogBiz.getList(page, rows, search, sort, order, start, end,type);
				return map;
			}
		} catch (Exception e) {
			logger.error("获取操作日志分页数据失败！", e);
		}
		return null;
	}
	/**
	 * 跳转到网关离线日志列表页
	 * 
	 * @author lizhao
	 * @return
	 */
	@SecurityAccessCheckable(resource = LogGatewayRank.class)
	@RequestMapping("/gatewayOfflinelist")
	public String gatewayOfflinelist() {
		return "/logManage/gatewayOfflineLog";
	}
	@SecurityAccessCheckable(resource = LogGatewayRank.class)
	@RequestMapping("/getGatewayList")
	@ResponseBody
	public Map<String, Object> getGatewayList(HttpSession session, int page, int rows, String sort, String order,
			String search,String start,String end) {
		try {
			
			Map<String, Object> map = gatewayLogBiz.getList(page, rows, search,sort,order,start,end);
			
			return map;
		} catch (Exception e) {
			logger.error("获取分页数据失败！", e);
		}
		return null;
	}
	/**
	 * 车锁离线日志的列表数据
	 * @author lizhao
	 * @param search
	 * @param start
	 * @param end
	 */
	@SecurityAccessCheckable(resource = LogCarlockRank.class)
	@RequestMapping("/getCarlockList")
	@ResponseBody
	public Map<String, Object> getCarlockList(HttpSession session, int page, int rows, String sort, String order,
			String search,String start,String end) {
		try {
			Map<String, Object> map=carlockLogBiz.getList(page,rows,sort,order,search,start,end);
			return map;
		} catch (Exception e) {
			logger.error("获取车锁日志分页数据失败！", e);
		}
		return null;
	}
	/**
	 * 跳转到车锁离线日志列表页
	 * 
	 * @author lizhao
	 * @return
	 */
	@SecurityAccessCheckable(resource = LogCarlockRank.class)
	@RequestMapping("/carlockOfflinelist")
	public String carlockOfflinelist() {
		return "/logManage/carlockOfflineLog";
	}

	/**
	 * 跳转到添加页面
	 * 
	 * @author 王生栋
	 * @return
	 */
	@SecurityAccessCheckable(resource = Login.class)
	@RequestMapping("/add")
	public String add(HttpSession session) {
		try {
			return "/baseInfo/operateLog_add";
		} catch (Exception e) {
			logger.error("进入车位锁添加页面失败！");
			return "500";
		}
	}

	/**
	 * 保存
	 * 
	 * @author 王生栋
	 * @param operateLog
	 * @return
	 */
	@SecurityAccessCheckable(resource = Login.class)
	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(String operateLog, HttpSession session) {
		try {
			
		} catch (Exception e) {
			logger.error("保存操作日志失败!", e);
		}
		return new AjaxResult(Result.FAIL);
	}

	/**
	 * 跳转到修改页面
	 * 
	 * @author 王生栋
	 * @param id
	 * @param model
	 * @return
	 */
	@SecurityAccessCheckable(resource = Login.class)
	@RequestMapping("/edit")
	public String edit(int id, Model model) {
		try {
			OperateLog operateLog = operateLogBiz.find(id);
			if (operateLog != null) {
				model.addAttribute("operateLog", operateLog);
				return "/baseInfo/operateLog_edit";
			}
		} catch (Exception e) {
			logger.error("进入修改页面失败！", e);
		}
		return "500";
	}

	/**
	 * 更新
	 * 
	 * @author 王生栋
	 * @param operateLog
	 * @return
	 */
	@SecurityAccessCheckable(resource = Login.class)
	@RequestMapping("/update")
	@ResponseBody
	public AjaxResult update(String operateLog) {
		try {
			if (!StringUtil.isEmpty(operateLog)) {
				OperateLog _operateLog = (OperateLog) JSONObject.toBean(
						JSONObject.fromObject(operateLog), OperateLog.class);
				operateLogBiz.update(_operateLog);
				return new AjaxResult(Result.SUCCESS);
			}
		} catch (Exception e) {
			logger.error("更新操作日志失败！", e);
		}
		return new AjaxResult(Result.FAIL);
	}

	/**
	 * 跳转到详情页面
	 * 
	 * @author 王生栋
	 * @param model
	 * @param id
	 * @return
	 */
	@SecurityAccessCheckable(resource = Login.class)
	@RequestMapping("/detail")
	public String detail(Model model, int id) {
		try {
			OperateLog operateLog = operateLogBiz.find(id);
			if (operateLog != null) {
				model.addAttribute("operateLog", operateLog);
				return "/baseInfo/operateLog_detail";
			}
		} catch (Exception e) {
			logger.error("进入详情页面失败！", e);
		}
		return "500";
	}

	

	

}
