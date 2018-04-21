package com.chinadovey.parking.webapps.controller.baseInfo;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinadovey.parking.acl.BasecontrolRank;
import com.chinadovey.parking.core.annotation.SecurityAccessCheckable;
import com.chinadovey.parking.core.mvc.AbstractBaseController;
import com.chinadovey.parking.core.mvc.AjaxResult;
import com.chinadovey.parking.core.mvc.AjaxResult.Result;
import com.chinadovey.parking.core.secu.SecuObject;
import com.chinadovey.parking.core.secu.SessionOpt;
import com.chinadovey.parking.webapps.biz.CarLockBiz;
import com.chinadovey.parking.webapps.biz.baseInfo.CarSpaceBiz;
import com.chinadovey.parking.webapps.biz.baseInfo.CarUserBiz;
import com.chinadovey.parking.webapps.biz.baseInfo.CompanyBiz;
import com.chinadovey.parking.webapps.biz.logManage.OperateLogBiz;
import com.chinadovey.parking.webapps.biz.system.UserBiz;
import com.chinadovey.parking.webapps.controller.tool.ControlBase;
import com.chinadovey.parking.webapps.controller.tool.ControlBaseImpl;
import com.chinadovey.parking.webapps.mina.client.ParkCloudClient;
import com.chinadovey.parking.webapps.mina.protocol.CMDPackets;
import com.chinadovey.parking.webapps.mina.protocol.ProtocolConst;
import com.chinadovey.parking.webapps.mina.protocol.RESPackets;
import com.chinadovey.parking.webapps.pojo.CarLock;
import com.chinadovey.parking.webapps.pojo.CarSpace;
import com.chinadovey.parking.webapps.pojo.CarUser;
import com.chinadovey.parking.webapps.pojo.Company;
import com.chinadovey.parking.webapps.pojo.OperateLog;
import com.chinadovey.parking.webapps.pojo.User;
import com.chinadovey.parking.webapps.pojo.base.CarLockTemp;
import com.chinadovey.parking.webapps.utils.ByteUtil;
import com.chinadovey.parking.webapps.utils.ConfUtils;
import com.chinadovey.parking.webapps.utils.HttpClientUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/baseInfo/operator")
public class OperateController extends AbstractBaseController {
    @Autowired
    private OperateLogBiz operateLogBiz;
    @Resource
  	private CompanyBiz companyBiz;
  	@Resource
  	private UserBiz userBiz;
  	@Resource
  	private CarSpaceBiz carSpaceBiz;
  	@Resource
  	private CarUserBiz carUserBiz;
  	@Resource
  	private CarLockBiz carLockBiz;
  	@Resource
  	private ControlBase controlBase;
  	
	
	
	
	@SecurityAccessCheckable(resource = BasecontrolRank.class)
	@RequestMapping("/newoperate")
	@ResponseBody
	public Integer newoperate(String slaveId , Integer action , HttpSession session){
		try {
			Integer stat = controlBase.operate(slaveId, action);
			saveLog(stat, slaveId, action, session);
			return stat;
		} catch (Exception e) {
			return -1;
		}
	
	}

	private void saveLog(int result , String slaveId ,Integer status, HttpSession session){
			try {
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
			
	
	
	/**
	 * 入场时 车位锁 联动
	 * @param equiId
	 * @param status
	 * @param session
	 * @return
	 */
	@RequestMapping("/linkoperate")
	@ResponseBody
	public Integer linkoperate(Integer parkId,String carNo){
		try {
			CarUser carUser = carUserBiz.getByPlateNumber(carNo,parkId);
			if (carUser != null) {
				List<CarSpace> list = carSpaceBiz.getByUserId(carUser.getId());
				for (CarSpace carSpace : list) {
					if (carSpace.getSlaveId() != null) {      //找到第一个 绑定车位锁的车位
						Map<String, String> params = new HashMap<String, String>();
			        	params.put("slaveId", carSpace.getSlaveId());
			        	params.put("action", "1");
			        	String url = ConfUtils.getUrlHead()+"/control/operate";
						String res = HttpClientUtil.getInstance().httpPost(url, params);
						JSONObject res2 = JSONObject.fromObject(res);
						if(res2.getBoolean("result")){
							Integer stat = res2.getInt("stat");
							return stat;
						}
						break;
					}
				}
			}
        	return -1;
		} catch (Exception e) {
			return -1;
		}
	
	}

}
