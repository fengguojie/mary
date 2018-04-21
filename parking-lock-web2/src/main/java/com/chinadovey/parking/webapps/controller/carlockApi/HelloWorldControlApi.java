package com.chinadovey.parking.webapps.controller.carlockApi;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinadovey.parking.core.annotation.SecurityIgnoreHandler;
import com.chinadovey.parking.core.mvc.AbstractBaseController;
import com.chinadovey.parking.webapps.biz.CarLockBiz;
import com.chinadovey.parking.webapps.biz.CarLockDataBiz;
import com.chinadovey.parking.webapps.biz.baseInfo.CarSpaceBiz;
import com.chinadovey.parking.webapps.biz.baseInfo.CompanyBiz;
import com.chinadovey.parking.webapps.biz.logManage.OperateLogBiz;
import com.chinadovey.parking.webapps.biz.system.UserBiz;
import com.chinadovey.parking.webapps.controller.tool.ControlBase;
import com.chinadovey.parking.webapps.pojo.CarLock;
import com.chinadovey.parking.webapps.pojo.CarSpace;
import com.chinadovey.parking.webapps.pojo.Company;
import com.chinadovey.parking.webapps.pojo.User;
import com.chinadovey.parking.webapps.utils.ConfUtils;
import com.chinadovey.parking.webapps.utils.DateUtil;
import com.chinadovey.parking.webapps.utils.HttpClientUtil;
import com.chinadovey.parking.webapps.utils.MD5;
import com.chinadovey.parking.webapps.utils.MailUtilNew;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/hello")
public class HelloWorldControlApi extends AbstractBaseController {
	@Autowired
	private CarSpaceBiz carSpaceBiz;
	@Autowired
	private UserBiz userBiz;

	/**
	 * 车锁控制
	 * @param salveId  车位锁编号
	 * @param action   1 车锁下降 2 车锁上升
	 * @return
	 */
	
	@SecurityIgnoreHandler
	@RequestMapping("/test")
	@ResponseBody
	public JSONObject ctrl(String username) {
		JSONObject json = new JSONObject();
		try {
			User user = userBiz.findcompanyid(username);
			json.put("user", user);
			
			json.put("rs", 1);
			json.put("msg", "helloworld");
			return json;
		} catch (Exception e) {
			json.put("rs", 1);
			json.put("msg", "车位锁控制失败");
			return json;
		}
	}
	
	
	

	
}
