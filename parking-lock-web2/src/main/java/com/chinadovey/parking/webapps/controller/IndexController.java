package com.chinadovey.parking.webapps.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chinadovey.parking.acl.PlatformOverviewRank;
import com.chinadovey.parking.core.annotation.SecurityAccessCheckable;
import com.chinadovey.parking.core.mvc.AbstractBaseController;
import com.chinadovey.parking.core.secu.SessionOpt;
import com.chinadovey.parking.webapps.biz.CarLockBiz;
import com.chinadovey.parking.webapps.biz.baseInfo.CarSpaceBiz;
import com.chinadovey.parking.webapps.biz.baseInfo.GatewayBiz;
import com.chinadovey.parking.webapps.biz.baseInfo.ParkBiz;
import com.chinadovey.parking.webapps.biz.statistics.CarInOutRecordBiz;
import com.chinadovey.parking.webapps.pojo.CarSpace;

@Controller
@RequestMapping("/")
public class IndexController extends AbstractBaseController {

	@Autowired
	private CarLockBiz carLockBiz;
	@Autowired
	private ParkBiz parkBiz;

	@Autowired
	private CarInOutRecordBiz carInOutRecordBiz;

	@Autowired
	private CarSpaceBiz carSpaceBiz;
	
	@Autowired
	private  GatewayBiz gatewayBiz;

	@SecurityAccessCheckable(resource = PlatformOverviewRank.class)
	@RequestMapping("/index")
	public String _index(HttpSession session, Model model) {
		try {
			List<Integer> parkIds = (List<Integer>) session.getAttribute(SessionOpt.PARK_ID);
		    /**
		     * 网关
		     */
		    int Allstatus = 0;//总网关
			int onlinestatus = gatewayBiz.countByStatus(0);//在线网关
			if (gatewayBiz.getAll() != null) {
				Allstatus = gatewayBiz.getAll().size();//总网关
			}
		    
		    model.addAttribute("parkCount", Allstatus);
			model.addAttribute("onLineTotal", onlinestatus);
			model.addAttribute("offLineTotal", Allstatus - onlinestatus);
			/**
			 * 车位
			 */
			Integer total = 0;//车场总车位
			Integer hasCar = 0;//有车的车位
			List<CarSpace>  space=carSpaceBiz.getAll();
			if(space!=null){
			    total=space.size();//车场总车位
			}
			
			hasCar =  carSpaceBiz.countByStatus(1);
			int noCar =  carSpaceBiz.countByStatus(0);
			
    		model.addAttribute("CountTotal", total);//车场总车位
 			model.addAttribute("TotalonLine", hasCar);//有车的车位
 			model.addAttribute("TotaloffLine", noCar);
 			
 			
 			 /**
 			  * 车锁
 			  */
 			 
 			int LockTotal = 0;
			if (carLockBiz.getAll() != null) {
				LockTotal = carLockBiz.getAll().size();
			}
			int elect = carLockBiz.countByStatus(1);//电量低
			int LockonLine = carLockBiz.countByStatus(2);//电量低
 			
 			model.addAttribute("LockTotal", LockTotal);
 			model.addAttribute("elect",elect);
  			model.addAttribute("LockonLine", LockonLine);
  			model.addAttribute("LockoffLine", LockTotal - LockonLine - elect);
 			
			return "/index";
		} catch (Exception e) {
			logger.error("进入首页失败！", e);
			return "500";
		}

	}

	

	
}
