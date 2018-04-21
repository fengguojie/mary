package com.chinadovey.parking.webapps.controller.test;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.chinadovey.parking.acl.BaseCarCret;
import com.chinadovey.parking.acl.BaseCarLockRank;
import com.chinadovey.parking.acl.Login;
import com.chinadovey.parking.core.annotation.SecurityAccessCheckable;
import com.chinadovey.parking.core.mvc.AbstractBaseController;
import com.chinadovey.parking.core.mvc.AjaxResult;
import com.chinadovey.parking.core.mvc.AjaxResult.Result;
import com.chinadovey.parking.core.secu.SecuObject;
import com.chinadovey.parking.core.secu.SessionOpt;
import com.chinadovey.parking.webapps.biz.CarLockBiz;
import com.chinadovey.parking.webapps.biz.baseInfo.CarSpaceBiz;
import com.chinadovey.parking.webapps.biz.baseInfo.GatewayBiz;
import com.chinadovey.parking.webapps.biz.baseInfo.ParkBiz;
import com.chinadovey.parking.webapps.biz.logManage.OperateLogBiz;
import com.chinadovey.parking.webapps.biz.test.NoteBiz;
import com.chinadovey.parking.webapps.controller.tool.ControlBase;
import com.chinadovey.parking.webapps.pojo.CarLock;
import com.chinadovey.parking.webapps.pojo.CarLockSerial;
import com.chinadovey.parking.webapps.pojo.CarLockSpace;
import com.chinadovey.parking.webapps.pojo.CarSpace;
import com.chinadovey.parking.webapps.pojo.Gateway;
import com.chinadovey.parking.webapps.pojo.Note;
import com.chinadovey.parking.webapps.pojo.OperateLog;
import com.chinadovey.parking.webapps.pojo.Park;
import com.chinadovey.parking.webapps.pojo.Teacher;
import com.chinadovey.parking.webapps.pojo.User;
import com.chinadovey.parking.webapps.utils.FileUtil;
import com.chinadovey.parking.webapps.utils.POIUtil;
import com.chinadovey.parking.webapps.utils.ParkingUtil;
import com.chinadovey.parking.webapps.utils.StringUtil;

import net.sf.json.JSONObject;


@Controller
@RequestMapping("/test/note")
public class NoteController extends AbstractBaseController {
	@Autowired
	private NoteBiz noteBiz;
	
	@Resource
	private OperateLogBiz operateLogBiz;
	
	/**
	 * 跳转到list页面
	 * wy
	 * 
	 * @param session
	 * @param model
	 * @return
	 */
	@SecurityAccessCheckable(resource = BaseCarLockRank.class)
	@RequestMapping("/list")
	public String list(HttpSession session, Model model) {
		return "/test/note_list";
	}
	
	/**
	 * 获得分页数据
	 * wy
	 * 
	 * @param session
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @param search
	 * @return
	 */
	@SecurityAccessCheckable(resource = BaseCarLockRank.class)
	@RequestMapping("/getList")
	@ResponseBody
	public Map<String, Object> getList(HttpSession session, int page, int rows, String sort, String order, String search) {
		try {
			if (page > 0 && rows > 0) {
				Map<String, Object> map = noteBiz.getList( page, rows, sort, order, search);
				return map;
			}
		} catch (Exception e) {
			logger.error("获取分页数据失败！", e);
		}
		return null;
	}
	/**
	 * 添加
	 * wy
	 * 
	 * @param session
	 * @param model
	 * @return
	 */
	@SecurityAccessCheckable(resource = BaseCarLockRank.class)
	@RequestMapping("/add")
	public String add(HttpSession session, Model model) {
		return "/test/note_add";
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
	public boolean checkName(String title) {
		System.out.println("title");
		boolean isExist=noteBiz.checkName(title);
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
	public boolean checkNameById(int id,String title) {
		System.out.println("title");
		boolean isExist=noteBiz.checkNameById(id,title);
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
	public AjaxResult save(String note,String name,HttpServletRequest request,HttpSession session) {
		try {
			String  note_ = request.getParameter("note");
			
			if (!StringUtil.isEmpty(note)) {
				Note _note = (Note) JSONObject.toBean(JSONObject.fromObject(note), Note.class);
				noteBiz.add(_note);
				OperateLog operateLog = new OperateLog();
				SecuObject secuObj = (SecuObject) session.getAttribute(SessionOpt.SECU_IN_SESSION);
				User user = secuObj.getUser();
				operateLog.setUsername(user.getName());
				operateLog.setContent("添加笔记");
				operateLog.setTime(new Date());
				operateLogBiz.save(operateLog);
				return new AjaxResult(Result.SUCCESS);
			}
		} catch (Exception e) {
			logger.error("保存停车场失败!", e);
		}
		return new AjaxResult(Result.FAIL);
	}
	

	
	
	@SecurityAccessCheckable(resource = Login.class)
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(int id,HttpSession session) {
		try {
			if (id > 0) {
				noteBiz.delete(id);
				OperateLog operateLog = new OperateLog();
				SecuObject secuObj = (SecuObject) session.getAttribute(SessionOpt.SECU_IN_SESSION);
				User user = secuObj.getUser();
				operateLog.setUsername(user.getName());
				operateLog.setContent("删除笔记");
				operateLog.setTime(new Date());
				operateLogBiz.save(operateLog);
				return new AjaxResult(Result.SUCCESS);
			}
		} catch (Exception e) {
			logger.error("删除车位失败！", e);
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
			Note note = noteBiz.find(id);
			if (note != null) {
				model.addAttribute("note", note);
				return "/test/note_edit";
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
	public AjaxResult update(String note) {
		try {
			if (!StringUtil.isEmpty(note)) {
				Note _note = (Note) JSONObject.toBean(JSONObject.fromObject(note), Note.class);
				noteBiz.update(_note);
				return new AjaxResult(Result.SUCCESS);
			}
		} catch (Exception e) {
			logger.error("更新停车场失败！", e);
		}
		return new AjaxResult(Result.FAIL);
	}

}

