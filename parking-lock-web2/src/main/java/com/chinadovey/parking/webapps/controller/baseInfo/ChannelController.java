package com.chinadovey.parking.webapps.controller.baseInfo;

import java.util.ArrayList;
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

/**
 * 停车场控制层 Copyright ©2014 中瑞华清（北京）智能科技有限公司 http://zhrhq.com
 * 
 * @author 王生栋 2014-12-31 下午4:34:24
 */
@Controller
@RequestMapping("/baseInfo/channel")
public class ChannelController extends AbstractBaseController {
	/*@Autowired
	private ChannelBiz channelBiz;*/

	/**
	 * 跳转到列表页
	 * 
	 * @author 王生栋
	 * @return
	 */
	@SecurityAccessCheckable(resource = BaseParkRank.class)
	@RequestMapping("/list")
	public String list() {
		return "/baseInfo/park_list";
	}

	/**
	 * 获取分页数据
	 * 
	 * @author 王生栋
	 * @param page
	 * @param rows
	 * @return
	 */
	@SecurityAccessCheckable(resource = BaseParkRank.class)
	@RequestMapping("/getList")
	@ResponseBody
	public Map<String, Object> getList(HttpSession session, int page, int rows, String sort, String order,
			String search) {
		
		return null;
	}

	/**
	 * 跳转到添加页面
	 * 
	 * @author 王生栋
	 * @return
	 */
	@SecurityAccessCheckable(resource = BaseParkCret.class)
	@RequestMapping("/add")
	public String add() {
		return "/baseInfo/park_add";
	}

	/**
	 * 保存
	 * 
	 * @author 王生栋
	 * @param Park
	 * @return
	 */
	@SecurityAccessCheckable(resource = BaseParkCret.class)
	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(String park) {
		try {
			
		} catch (Exception e) {
			logger.error("保存停车场失败!", e);
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
	@SecurityAccessCheckable(resource = BaseParkEdit.class)
	@RequestMapping("/edit")
	public String edit(int id, Model model) {
		try {
		
		} catch (Exception e) {
			logger.error("进入修改页面失败！", e);
		}
		return "500";
	}

	/**
	 * 更新
	 * 
	 * @author 王生栋
	 * @param Park
	 * @return
	 */
	@SecurityAccessCheckable(resource = BaseParkEdit.class)
	@RequestMapping("/update")
	@ResponseBody
	public AjaxResult update(String park) {
		try {
			
		} catch (Exception e) {
			logger.error("更新停车场失败！", e);
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
	@SecurityAccessCheckable(resource = BaseParkView.class)
	@RequestMapping("/detail")
	public String detail(Model model, int id) {
		try {
		} catch (Exception e) {
			logger.error("进入详情页面失败！", e);
		}
		return "500";
	}

	/**
	 * 删除
	 * 
	 * @author 王生栋
	 * @param id
	 * @return
	 */
	@SecurityAccessCheckable(resource = BaseParkDele.class)
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(int id) {
		try {
			
		} catch (Exception e) {
			logger.error("删除停车场失败！", e);
		}
		return new AjaxResult(Result.FAIL);
	}

	/*@SecurityAccessCheckable(resource = Login.class)
	@RequestMapping("/getChannelByParkId")
	@ResponseBody
	public List<Channel> getChannelByParkId(Integer parkId){
		try{
			List<Channel> channels = channelBiz.getAllByParkId(parkId);
			return channels;
		}catch(Exception e){
			logger.error("获取数据失败",e);
		}
		return new ArrayList<Channel>();
	}*/
}
