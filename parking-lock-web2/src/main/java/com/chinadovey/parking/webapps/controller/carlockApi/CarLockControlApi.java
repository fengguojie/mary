package com.chinadovey.parking.webapps.controller.carlockApi;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
@RequestMapping("/carlock")
public class CarLockControlApi extends AbstractBaseController {
	@Autowired
	private CarSpaceBiz carSpaceBiz;
	@Autowired
	private OperateLogBiz logBiz;
	@Autowired
	private CompanyBiz companyBiz;
	@Autowired
	private CarLockBiz carLockBiz;
	@Autowired
	private CarLockDataBiz carLockDataBiz;
	@Autowired
	private UserBiz userBiz;
	@Autowired
	private ControlBase controlBase;

	/**
	 * 旧的方案：暂时不用
	 * @param salveId  设备id
	 * @param action  1车锁下降 2 车锁上升
	 * @param userId  用户的id，由平台统一分配
	 * @param token   用户令牌，token为MD5（用户id&key）其中key由平台统一分配
	 * @return
	 */
	@RequestMapping("/control")
	@ResponseBody
	public JSONObject control(String carSpace,Integer parkingId,String action,String userId,String token) {
		JSONObject json = new JSONObject();
		try {
			    CarSpace space = carSpaceBiz.getLockId(carSpace,parkingId);
			    String slaveId = "";
			    if (space != null ) {
			    	slaveId = space.getSlaveId();
				}else{
					json.put("rs", 1);
					json.put("msg", "根据车位号未查到车位锁，请查看是否绑定！");
					return json;
				}
			    Map<String, String> params = new HashMap<String, String>();
	        	params.put("slaveId", slaveId);
	        	String url = ConfUtils.getUrlHead()+"/carlock/findCompanyNo";
				String res = HttpClientUtil.getInstance().httpPost(url, params);
				JSONObject resobj = JSONObject.fromObject(res);
				if(resobj.getBoolean("result")){
					String companyNo = resobj.getString("companyNo");
					Company company = companyBiz.findByCompanyNo(companyNo);
				    if (MD5.MD5Encode(company.getAppId()+company.getToken()).equals(token)) {
				    	//执行车位锁 开关指令
				    	Map<String, String> params2 = new HashMap<String, String>();
			        	params2.put("slaveId", slaveId);
			        	params2.put("action", action);
			        	String url2 = ConfUtils.getUrlHead()+"/control/operate";
						String res2 = HttpClientUtil.getInstance().httpPost(url2, params2);
						JSONObject resobj2 = JSONObject.fromObject(res2);
						if(resobj2.getBoolean("result")){
							Integer stat = resobj2.getInt("stat");
							saveLog(stat,slaveId,Integer.parseInt(action));
							if (stat == 0x00 || stat == 0x02 ) {
					    		json.put("rs", 0);
								json.put("status", "打开");
								return json;
							}else if (stat == 0x01 || stat == 0x03) {
								json.put("rs", 0);
								json.put("status", "关闭");
								return json;
							}else{
								String content = "车位号"+carSpace+"车锁编号"+slaveId+"联动失败";
								String mail = ConfUtils.getMail();
								MailUtilNew.sendMail("车位锁联动失败", content, mail);
								List<User> list = userBiz.getAll();
								for (User user : list) {
									mail = user.getEmail();
									MailUtilNew.sendMail("车位锁联动失败", content, mail);
								}
								json.put("rs", 1);
								json.put("msg", "车位锁控制失败");
								return json;
							}
						}else {
							String content = "车位号"+carSpace+"车锁编号"+slaveId+"联动失败";
							String mail = ConfUtils.getMail();
							MailUtilNew.sendMail("车位锁联动失败", content, mail);
							List<User> list = userBiz.getAll();
							for (User user : list) {
								mail = user.getEmail();
								MailUtilNew.sendMail("车位锁联动失败", content, mail);
							}
							json.put("rs", 1);
							json.put("msg", "车位锁控制失败");
							return json;
						}
				    } else {
						json.put("rs", 1);
						json.put("msg", "用户验证失败");
						return json;
	                }
				}else {
					json.put("rs", 1);
					json.put("msg", "机构查询失败");
					return json;
				}
		} catch (Exception e) {
			logger.error("车锁联动失败！",e);
			json.put("rs", 1);
			json.put("msg", "服务器出现异常");
			return json;
		}
	}
	private void saveLog(int result , String slaveId ,Integer action){
		/*NewOperateLog log = new NewOperateLog();
		log.setSlaveId(slaveId);
		log.setOperateStatus(action);
		log.setOperateResult(result);
		log.setOperateTime(new Date());
		log.setUserName("车位—-锁联动");
		log.setUserType(1);
		logBiz.saveNewLog(log);*/
	}
	/**
	 * 车位所控制
	 * @param salveId  车位锁编号
	 * @param action   1 车锁下降 2 车锁上升
	 * @param bindNo   车位锁绑定码
	 * @return
	 */
	@RequestMapping("/controls")
	@ResponseBody
	public JSONObject controls(String slaveId,String action,String bindNo) {
		JSONObject json = new JSONObject();
		try {
			  /* if (carLockBiz.isSlaveIdExitBybindNo(slaveId, bindNo)) {
			    	//执行车位锁 开关指令
			    	Integer result = carLockBiz.operateNew(slaveId,Integer.parseInt(action));
			    	if (result == 0x00 || result == 0x02 ) {
			    		json.put("rs", 0);
						json.put("status", "打开");
						return json;
					}else if (result == 0x01 || result == 0x03) {
						json.put("rs", 0);
						json.put("status", "关闭");
						return json;
					}else{
						json.put("rs", 1);
						json.put("msg", "车位锁控制失败");
						return json;
					}
			    } else {
					json.put("rs", 1);
					json.put("msg", "车位锁和绑定码不匹配");
					return json;
                }*/
		} catch (Exception e) {
			json.put("rs", 1);
			json.put("msg", "车位锁和绑定码不匹配");
			return json;
		}
		return json;
	}
	@RequestMapping("/getLock")
	@ResponseBody
	public JSONObject getLock(String slaveId,String userId,String token) {
		JSONObject json = new JSONObject();
		try {
			/*CarLock carLock = carLockBiz.getBySlaveId(slaveId);
			Integer companyId = carLock.getCompanyId();
		    Company company = companyBiz.find(companyId);
		    String valid = MD5.MD5Encode(company.getAppId()+company.getToken());
		    if (valid.equals(token)) {
		    	CarLockData date = carLockDataBiz.findByEquid(carLock.getEquiId());
		    	if (date != null) {
		    		json.put("rs", 0);
					json.put("id",carLock.getId());
					json.put("openState",date.getOpenState());
					json.put("carState",date.getCarState());
					json.put("voltage",date.getVoltage());
					json.put("equiState",date.getEquiState());
					json.put("collectTime",DateUtil.dateConvertString(date.getCollectTime(),3));
					return json;
				}else{
					json.put("rs", 1);
					json.put("msg", "获取数据失败");
					return json;
				}
		    } else {
				json.put("rs", 1);
				json.put("msg", "用户验证失败");
				return json;
            }*/
		} catch (Exception e) {
			json.put("rs", 1);
			json.put("msg", "用户验证失败");
			return json;
		}
		return json;
	}
	
	
	/**
	 * 车锁控制
	 * @param salveId  车位锁编号
	 * @param action   1 车锁下降 2 车锁上升
	 * @return
	 */
	@RequestMapping("/ctrl")
	@ResponseBody
	public JSONObject ctrl(String slaveId,String action) {
		JSONObject json = new JSONObject();
		try {
			Integer stat = controlBase.operate(slaveId, Integer.parseInt(action));
			json.put("rs", 1);
			json.put("msg", "车位锁控制成功");
			return json;
		} catch (Exception e) {
			json.put("rs", 1);
			json.put("msg", "车位锁控制失败");
			return json;
		}
	}
	
	
	

	
}
