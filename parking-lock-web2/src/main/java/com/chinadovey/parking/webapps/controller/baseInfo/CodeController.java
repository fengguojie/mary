package com.chinadovey.parking.webapps.controller.baseInfo;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinadovey.parking.acl.Login;
import com.chinadovey.parking.core.annotation.SecurityAccessCheckable;
import com.chinadovey.parking.core.mvc.AbstractBaseController;
import com.chinadovey.parking.core.mvc.AjaxResult;
import com.chinadovey.parking.core.mvc.AjaxResult.Result;
import com.chinadovey.parking.core.secu.SessionOpt;
import com.chinadovey.parking.webapps.biz.baseInfo.ParkBiz;
import com.chinadovey.parking.webapps.biz.system.CodeBiz;
import com.chinadovey.parking.webapps.pojo.Park;

/**
 * 参数管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/baseInfo/code")
public class CodeController extends AbstractBaseController {

	/*@Resource
	private StayRecordBiz stayRecordBiz;

	@Resource
	private EatRecordBiz eatRecordBiz;*/

	@Resource
	private CodeBiz codeBiz;

	@Resource
	private ParkBiz parkBiz;

	/**
	 * 打开参数设置页面
	 * 
	 * @param model
	 * @return
	 */
	@SecurityAccessCheckable(resource = Login.class)
	@RequestMapping("/ruianCodes")
	public String ruianCodes(HttpSession session, Model model) {
		List<Integer> parkIds = (List<Integer>) session.getAttribute(SessionOpt.PARK_ID);
		List<Park> parks = null;
		// 判断parkId是否存在
		if (parkIds.size() <= 0) {
			parks = parkBiz.getAll();
		} else {
			parks = parkBiz.getAll(parkIds);
		}

		Park park = new Park();
		Integer parkId = 4;
		// 判断是否为空
		if (parks != null && !parks.isEmpty()) {
			park = parks.get(0);
			parkId = park.getId();
			model.addAttribute("parkId", parkId);
		}
		/*int quitRoomTime = stayRecordBiz.getQuitRoomTime(parkId);
		model.addAttribute("quitRoomTime", quitRoomTime);
		int eatTime = eatRecordBiz.getEatTime(parkId);
		model.addAttribute("eatTime", eatTime);
		int eatPromptTime = eatRecordBiz.getEatPromptTime(parkId);
		model.addAttribute("eatPromptTime", eatPromptTime);
		int stayPromptTime = stayRecordBiz.getStayPromptTime(parkId);
		model.addAttribute("stayPromptTime", stayPromptTime);*/
		return "/baseInfo/ruianCodes";
	}

	/**
	 * 更新退房时间
	 * 
	 * @author 张晨刚
	 * @param stayRecord
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@SecurityAccessCheckable(resource = Login.class)
	@RequestMapping("/updateQuitTime")
	@ResponseBody
	public AjaxResult updateQuitTime(Integer quitTime, Integer parkId) {
		try {
//			stayRecordBiz.updateQuitRoomTime(quitTime, parkId);
			return new AjaxResult(Result.SUCCESS);
		} catch (Exception e) {
			logger.error("更新时间失败！", e);
		}
		return new AjaxResult(Result.FAIL);
	}

	/**
	 * 更新用餐时长
	 * 
	 * @author 张晨刚
	 * @param eatRecord
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@SecurityAccessCheckable(resource = Login.class)
	@RequestMapping("/updateEatTime")
	@ResponseBody
	public AjaxResult updateEatTime(Integer eatTime, Integer parkId) {
		try {
//			eatRecordBiz.updateEatTime(eatTime, parkId);
			return new AjaxResult(Result.SUCCESS);
		} catch (Exception e) {
			logger.error("更新时间失败！", e);
		}
		return new AjaxResult(Result.FAIL);
	}

	/**
	 * 更新用餐提醒时长
	 * 
	 * @author 张晨刚
	 * @param eatRecord
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@SecurityAccessCheckable(resource = Login.class)
	@RequestMapping("/updateEatPromptTime")
	@ResponseBody
	public AjaxResult updateEatPromptTime(Integer eatPromptTime, Integer parkId) {
		try {
//			eatRecordBiz.updateEatPromptTime(eatPromptTime, parkId);
			return new AjaxResult(Result.SUCCESS);
		} catch (Exception e) {
			logger.error("更新时间失败！", e);
		}
		return new AjaxResult(Result.FAIL);
	}

	/**
	 * 更新住宿提醒时长
	 * 
	 * @author 张晨刚
	 * @param eatRecord
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@SecurityAccessCheckable(resource = Login.class)
	@RequestMapping("/updateStayPromptTime")
	@ResponseBody
	public AjaxResult updateStayPromptTime(Integer stayPromptTime, Integer parkId) {
		try {
//			stayRecordBiz.updateStayPromptTime(stayPromptTime, parkId);
			return new AjaxResult(Result.SUCCESS);
		} catch (Exception e) {
			logger.error("更新时间失败！", e);
		}
		return new AjaxResult(Result.FAIL);
	}

}
