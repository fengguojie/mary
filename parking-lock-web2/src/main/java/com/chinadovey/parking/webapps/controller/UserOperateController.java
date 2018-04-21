package com.chinadovey.parking.webapps.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinadovey.parking.core.mvc.AbstractBaseController;
import com.chinadovey.parking.webapps.biz.baseInfo.CarSpaceBiz;
import com.chinadovey.parking.webapps.biz.baseInfo.CarUserBiz;
import com.chinadovey.parking.webapps.biz.baseInfo.ParkBiz;
import com.chinadovey.parking.webapps.biz.logManage.OperateLogBiz;
import com.chinadovey.parking.webapps.pojo.CarSpace;
import com.chinadovey.parking.webapps.pojo.CarUser;
import com.chinadovey.parking.webapps.utils.ConfUtils;
import com.chinadovey.parking.webapps.utils.HttpClientUtil;

/**
 * 用户自己输入手机号和车位号控制车锁
 * @author 王生栋 2014-12-31 下午4:34:24
 */

@Controller
@RequestMapping("/userOperate")
public class UserOperateController extends AbstractBaseController {
	@Autowired
	private CarUserBiz carUserBiz;
	@Autowired
	private CarSpaceBiz carSpaceBiz;
	@Autowired
	private ParkBiz parkBiz;
    @Autowired
    private OperateLogBiz operateLogBiz;

	@RequestMapping("/default")
	public String _default() {
		return "/user_operate";
	}
	
	@RequestMapping("/validate")
	@ResponseBody
	public JSONObject validate(String mobile , String spaceNo) {
		JSONObject json = new JSONObject();
		json.put("result",false);
		json.put("msg","验证失败！");
		try {
			CarSpace space = carSpaceBiz.getSingleByMobile(mobile);
			if(space!=null && space.getSpaceNo().equals(spaceNo)){
				json.put("result",true);
				json.put("spaceNo",space.getSpaceNo());
				json.put("slaveId",space.getSlaveId());
			}
		} catch (Exception e) {
			logger.error("用户验证手机号和车位号失败！",e);
		}
		return json;
	}
	
	@RequestMapping("/newoperate")
	@ResponseBody
	public Integer newoperate(String equiId , Integer status , String mobile,  HttpSession session){
		try {
        	Map<String, String> params = new HashMap<String, String>();
        	params.put("slaveId", equiId);
        	params.put("action", String.valueOf(status));
        	String url = ConfUtils.getUrlHead()+"/control/operate";
			String res = HttpClientUtil.getInstance().httpPost(url, params);
			JSONObject res2 = JSONObject.fromObject(res);
			if(res2.getBoolean("result")){
				Integer stat = res2.getInt("stat");
//				saveLog(stat,equiId,status,mobile);
				return stat;
			}
			return -1;
		} catch (Exception e) {
			logger.error("车锁操作失败",e);
			return -1;
		}
	
	}

		/*private void saveLog(int result , String slaveId ,Integer status, String mobile){
			NewOperateLog log = new NewOperateLog();
			CarUser user = carUserBiz.getByMobile(mobile);
			log.setSlaveId(slaveId);
			log.setOperateStatus(status);
			log.setOperateResult(result);
			
			log.setOperateTime(new Date());
			log.setUserId(user.getId());
			log.setUserName(user.getName());
			log.setUserType(2);
			operateLogBiz.saveNewLog(log);
		}*/
	
	
}
