package com.chinadovey.parking.webapps.controller.baseInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinadovey.parking.acl.BaseCarSpaceRank;
import com.chinadovey.parking.core.annotation.SecurityAccessCheckable;
import com.chinadovey.parking.core.mvc.AbstractBaseController;
import com.chinadovey.parking.core.secu.SessionOpt;
import com.chinadovey.parking.webapps.biz.baseInfo.CarSpaceBiz;
import com.chinadovey.parking.webapps.biz.logManage.OperateLogBiz;
import com.chinadovey.parking.webapps.pojo.CarSpace;
import com.chinadovey.parking.webapps.utils.ConfUtils;
import com.chinadovey.parking.webapps.utils.HttpClientUtil;

import net.sf.json.JSONObject;
/**
 * 车位控制控制层
 * @author wangshengdong
 *
 */
@Controller
@RequestMapping("/baseInfo/carSpaceOperate")
public class CarSpaceOperateController extends AbstractBaseController {
    @Autowired
    private OperateLogBiz operateLogBiz;
  	@Resource
  	private CarSpaceBiz carSpaceBiz;
  	
	@SecurityAccessCheckable(resource = BaseCarSpaceRank.class)
	@RequestMapping("/default")
	public String list() {
		return "carSpaceControl/operate";
	}
	
	/**
	 * 获取分页数据
	 * 
	 * @author 王生栋
	 * @param page
	 * @param rows
	 * @return
	 */
	@SecurityAccessCheckable(resource = BaseCarSpaceRank.class)
	@RequestMapping("/getList")
	@ResponseBody
	public Map<String, Object> getList(HttpSession session, int page, int rows,
			String sort, String order, String search) {
		try {
			List<Integer> parkId = (List<Integer>) session.getAttribute(SessionOpt.PARK_ID);
			if (page > 0 && rows > 0) {
				Map<String, Object> map = null;

				// 判断parkId是否存在
				if (parkId.size() > 0) {
					map = carSpaceBiz.getList(page, rows, search, sort, order,
							parkId);
				} else {
					map = carSpaceBiz.getList(page, rows, search, sort, order);
				}
				
				List<CarSpace> list = (List<CarSpace>) map.get("rows");
				for(CarSpace space : list){
				 	Map<String, String> params = new HashMap<String, String>();
		        	params.put("id", space.getSlaveId());
			    	String url = ConfUtils.getUrlHead()+"/carlock/detailBySlaveId";
					String resStr = HttpClientUtil.getInstance().httpPost(url, params);
					JSONObject res = JSONObject.fromObject(resStr);
					if(res.getBoolean("result")){
						JSONObject carlockJson = JSONObject.fromObject(res.get("data"));
						/*CarLock carLock = (CarLock) JSONObject.toBean(carlockJson, CarLock.class);
						space.setCarLock(carLock);*/
					}
				}
				return map;
			}
		} catch (Exception e) {
			logger.error("获取车位分页数据失败！", e);
		}
		return null;
	}
	
	
	
	@SecurityAccessCheckable(resource = BaseCarSpaceRank.class)
	@RequestMapping("/newoperate")
	@ResponseBody
	public Integer newoperate(String equiId , Integer status , HttpSession session){
		try {
        	Map<String, String> params = new HashMap<String, String>();
        	params.put("slaveId", equiId);
        	params.put("action", String.valueOf(status));
        	String url = ConfUtils.getUrlHead()+"/control/operate";
			String res = HttpClientUtil.getInstance().httpPost(url, params);
			JSONObject res2 = JSONObject.fromObject(res);
			if(res2.getBoolean("result")){
				Integer stat = res2.getInt("stat");
//				saveLog(stat,equiId,status,session);
				return stat;
			}
			return -1;
		} catch (Exception e) {
			return -1;
		}
	
	}

		/*private void saveLog(int result , String slaveId ,Integer status, HttpSession session){
			NewOperateLog log = new NewOperateLog();
			SecuObject secObj = (SecuObject) session.getAttribute(SessionOpt.SECU_IN_SESSION);
			User user = secObj.getUser();
			log.setSlaveId(slaveId);
			log.setOperateStatus(status);
			log.setOperateResult(result);
			
			log.setOperateTime(new Date());
			log.setUserId(user.getId());
			log.setUserName(user.getName());
			log.setUserType(1);
			operateLogBiz.saveNewLog(log);
		}*/
			

	
	

	
}
