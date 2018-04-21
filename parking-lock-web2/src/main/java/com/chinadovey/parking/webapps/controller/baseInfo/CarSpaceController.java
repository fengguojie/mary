package com.chinadovey.parking.webapps.controller.baseInfo;

import java.io.File;
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
import com.chinadovey.parking.acl.BaseCarDele;
import com.chinadovey.parking.acl.BaseCarEdit;
import com.chinadovey.parking.acl.BaseCarSpaceRank;
import com.chinadovey.parking.acl.Login;
import com.chinadovey.parking.core.annotation.SecurityAccessCheckable;
import com.chinadovey.parking.core.mvc.AbstractBaseController;
import com.chinadovey.parking.core.mvc.AjaxResult;
import com.chinadovey.parking.core.mvc.AjaxResult.Result;
import com.chinadovey.parking.core.secu.SessionOpt;
import com.chinadovey.parking.webapps.biz.CarLockBiz;
import com.chinadovey.parking.webapps.biz.baseInfo.CarSpaceBiz;
import com.chinadovey.parking.webapps.biz.baseInfo.ParkBiz;
import com.chinadovey.parking.webapps.pojo.CarLock;
import com.chinadovey.parking.webapps.pojo.CarSpace;
import com.chinadovey.parking.webapps.pojo.Park;
import com.chinadovey.parking.webapps.utils.FileUtil;
import com.chinadovey.parking.webapps.utils.POIUtil;
import com.chinadovey.parking.webapps.utils.ParkingUtil;
import com.chinadovey.parking.webapps.utils.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Controller
@RequestMapping("/baseInfo/carSpace")
public class CarSpaceController extends AbstractBaseController {
	@Resource
	private ParkBiz parkBiz;
	
	@Resource
	private CarSpaceBiz carSpaceBiz;
	
	@Autowired
	private CarLockBiz carLockBiz;               

	/**
	 * 显示列表页
	 * wy
	 * 
	 * @param session
	 * @param model
	 * @return
	 */
	@SecurityAccessCheckable(resource = BaseCarSpaceRank.class)
	@RequestMapping("/list")
	public String list(HttpSession session, Model model) {
		List<Integer> parkIds = (List<Integer>) session.getAttribute(SessionOpt.PARK_ID);
		List<Park> parks;
		if (parkIds == null || parkIds.isEmpty()) {
			parks = parkBiz.getAll();
		} else {
			parks = parkBiz.getAll(parkIds);
		}
		model.addAttribute("parks", parks);
		return "/baseInfo/carSpace_list";
	}
	/**
	 * 返回列表页
	 * wy
	 * 
	 * @param page
	 * @param model
	 * @return
	 */
	@SecurityAccessCheckable(resource = BaseCarSpaceRank.class)
	@RequestMapping("/Pagelist")
	public String list(int page, Model model, HttpSession session) {
		List<Integer> parkIds = (List<Integer>) session.getAttribute(SessionOpt.PARK_ID);
		List<Park> parks;
		if (parkIds == null || parkIds.isEmpty()) {
			parks = parkBiz.getAll();
		} else {
			parks = parkBiz.getAll(parkIds);
		}
		model.addAttribute("parks", parks);
		model.addAttribute("Newpage",page);
		return "/baseInfo/carSpace_list";
	}
	/**
	 * 列表分页
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
	@SecurityAccessCheckable(resource = BaseCarSpaceRank.class)
	@RequestMapping("/getList")
	@ResponseBody
	public Map<String, Object> getList(HttpSession session, int page, int rows, String sort, String order, String search) {
		try {
			List<Integer> parkIds = (List<Integer>) session.getAttribute(SessionOpt.PARK_ID);
			List<Park> parks;
			if (parkIds == null || parkIds.isEmpty()) {
				parks = parkBiz.getAll();
			} else {
				parks = parkBiz.getAll(parkIds);
			}
			
			if (page > 0 && rows > 0) {
				Map<String, Object> map = carSpaceBiz.getList(parkIds, page, rows, sort, order, search);
				return map;
			}
		} catch (Exception e) {
			logger.error("获取网关分页数据失败！", e);
		}
		return null;
	}
	/**
	 * 添加页面
	 * wy
	 * 
	 * @param session
	 * @param model
	 * @return
	 */
	@SecurityAccessCheckable(resource = BaseCarCret.class)
	@RequestMapping("/add")
	public String add(HttpSession session, Model model) {
		try {
			//查询所有车场信息
			List<Park> parks = parkBiz.getAll();
			if(parks != null && !parks.isEmpty()){
				model.addAttribute("parks", parks);
			}
			//获得所有车锁信息
			List<CarLock> carLocks = carLockBiz.findAllNOBind(0);
			model.addAttribute("carLocks", carLocks);
			return "/baseInfo/carSpace_add";
		} catch (Exception e) {
			logger.error("进入车位添加页面失败！", e);
			return "500";
		}
	}
	/**
	 * 查询车场对应的未绑定的车锁信息
	 * 
	 * @param session
	 * @param parkId
	 * @return
	 */
	@SecurityAccessCheckable(resource = BaseCarCret.class)
	@RequestMapping("/getCarLock")
	@ResponseBody
	public JSONObject getCarLock(HttpSession session, Integer parkId) {
		JSONObject obj = new JSONObject();
		try {
			List<CarLock> carLocks;
			//查询车场对应的所有未绑定的车锁 '0'表示车锁未绑定
			if(parkId != 0){
				carLocks = carLockBiz.findByParkId(parkId, 0);
				obj.put("carLocks", carLocks);
			}else{
				//查询所有未绑定的车锁
				carLocks = carLockBiz.findAllNOBind(0);
				obj.put("carLocks", carLocks);
			}
			obj.put("result", true);
			
		} catch (Exception e) {
			obj.put("result", false);
		    obj.put("error", "获取信息失败");
			logger.error("获取信息失败", e);
		}
		return obj;
	}
	/**
	 * 验证车位编号是否存在
	 * wy
	 * 
	 * @param spaceNo
	 * @return
	 */
	@SecurityAccessCheckable(resource = Login.class)
	@RequestMapping("/checkCode")
	@ResponseBody
	public boolean checkCode(String spaceNo) {
		return !carSpaceBiz.isCodeExit(spaceNo);
	}
	/**
	 * 保存
	 * wy
	 * 
	 * @param carSpace
	 * @param session
	 * @return
	 */
	@SecurityAccessCheckable(resource = BaseCarCret.class)
	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(String carSpace, HttpSession session) {
		try {
			if (!StringUtil.isEmpty(carSpace)) {
				CarSpace _carSpace = (CarSpace) JSONObject.toBean(JSONObject.fromObject(carSpace), CarSpace.class);
				//获得当前系统时间
				_carSpace.setAddTime(new Date());
				carSpaceBiz.save(_carSpace);
				//根据slaveId查询车锁信息
				if(!_carSpace.getSlaveId().equals("0")){
					CarLock carLock = carLockBiz.getBySlaveid(_carSpace.getSlaveId());
					carLock.setIsBind(1);
					carLockBiz.update(carLock);
				}
				
				return new AjaxResult(Result.SUCCESS);
			}
		} catch (Exception e) {
			logger.error("保存车位失败!", e);
		}
		return new AjaxResult(Result.FAIL);
	}
	/**
	 * 修改
	 * wy
	 * 
	 * @param id
	 * @param page
	 * @param model
	 * @param session
	 * @return
	 */
	@SecurityAccessCheckable(resource = BaseCarEdit.class)
	@RequestMapping("/edit")
	public String edit(int id, int page, Model model, HttpSession session) {
		try {
			CarSpace carSpace = carSpaceBiz.find(id);
			Park park = null;
			if(carSpace.getParkId() != null){
				park = parkBiz.find(carSpace.getParkId());
				model.addAttribute("park", park);
			}
			//查询所有车场信息并去重
			List<Park> parks = parkBiz.getAll();
			Iterator<Park> iterator =  parks.iterator();
			while(iterator.hasNext()){
				Park parking = iterator.next();
				if(park.getName().equals(parking.getName())){
					iterator.remove();
					break;
				}
			}
			//获得所有车锁信息并去重
			List<CarLock> carLocks = carLockBiz.findByParkId(carSpace.getParkId(), 0);
			if(carLocks != null && !carLocks.isEmpty()){
				Iterator<CarLock> iterator1 =  carLocks.iterator();
				while(iterator1.hasNext()){
					CarLock carLock = iterator1.next();
					if(carSpace.getSlaveId().equals(carLock.getSlaveId())){
						iterator1.remove();
						break;
					}
				}
				model.addAttribute("carLocks", carLocks);
				model.addAttribute("carSpace", carSpace);
				model.addAttribute("parks", parks);
			}else{
				model.addAttribute("carSpace", carSpace);
				model.addAttribute("parks", parks);
			}
			model.addAttribute("colentpage", page);
			return "/baseInfo/carSpace_edit";
		} catch (Exception e) {
			logger.error("进入修改页面失败！", e);
		}
		return "500";
	}
	/**
	 * 根据id验证编号是否存在
	 * wy
	 * 
	 * @param spaceNo
	 * @param id
	 * @return
	 */
	@SecurityAccessCheckable(resource = Login.class)
	@RequestMapping("/checkCodeById")
	@ResponseBody
	public boolean checkCodeById(String spaceNo, int id) {
		return !carSpaceBiz.isCodeExitById(spaceNo, id);
	}
	/**
	 * 更新
	 * wy
	 * 
	 * @param carSpace
	 * @return
	 */
	@SecurityAccessCheckable(resource = BaseCarEdit.class)
	@RequestMapping("/update")
	@ResponseBody
	public AjaxResult update(String carSpace) {
		try {
			if (!StringUtil.isEmpty(carSpace)) {
				CarSpace _carSpace = (CarSpace) JSONObject.toBean(JSONObject.fromObject(carSpace), CarSpace.class);
				//根据车位编号查询修改之前的slaveId
				CarSpace carSpace2 = carSpaceBiz.findByCode(_carSpace.getSpaceNo());
				if(!carSpace2.getSlaveId().equals("0")){
					CarLock _carLock = carLockBiz.getBySlaveid(carSpace2.getSlaveId());
					_carLock.setIsBind(0);
					carLockBiz.update(_carLock);
				}
				carSpaceBiz.update(_carSpace);
				//根据slaveId查询车锁信息
				if(!_carSpace.getSlaveId().equals("0")){
					CarLock carLock = carLockBiz.getBySlaveid(_carSpace.getSlaveId());
					carLock.setIsBind(1);
					carLockBiz.update(carLock);
				}
				return new AjaxResult(Result.SUCCESS);
			}
		} catch (Exception e) {
			logger.error("更新车位失败！", e);
		}
		return new AjaxResult(Result.FAIL);
	}
	/**
	 * 删除
	 * 
	 * @author 王生栋
	 * @param id
	 * @return
	 */
	@SecurityAccessCheckable(resource = BaseCarDele.class)
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(int id) {
		try {
			if (id > 0) {
				//先解除车位和车锁的绑定
				CarSpace carSpace = carSpaceBiz.find(id);
				CarLock carLock = carLockBiz.getBySlaveid(carSpace.getSlaveId());
				if(carLock != null){
					carLock.setIsBind(0);
					carLockBiz.update(carLock);
				}
				//删除
				carSpaceBiz.delete(id);
				return new AjaxResult(Result.SUCCESS);
			}
		} catch (Exception e) {
			logger.error("删除车位失败！", e);
		}
		return new AjaxResult(Result.FAIL);
	}
	/**
	 * 导入表格
	 * wy
	 * 
	 * @param type
	 * @param request
	 * @param model
	 * @return
	 */
	@SecurityAccessCheckable(resource = Login.class)
	@RequestMapping("/upload")
	@ResponseBody
	public JSONObject upload(String type, HttpServletRequest request, Model model) {
		JSONObject json = new JSONObject();
		try {
			// 页面控件的文件流
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			MultipartFile multipartFile = multiRequest.getFile("file");
			MultipartFile multipartFileCheck = multipartFile;
			// 获取文件的后缀 ,并设置文件名
			String fileNo = StringUtil.randomOrderNo();
			String fileName = fileNo + "." + type;
			// 设置文件上传路径
			String path = "excel";
			path = ParkingUtil.getPicPath()+ path;
			File fileOriginal = new File(path, fileName);
			// 判断文件夹是否存在
			if (!fileOriginal.exists()) {
				fileOriginal.mkdirs();
			}
            // 验证文件是否存在
			String fileNameExist = FileUtil.checkAndGetFileExist(path, multipartFileCheck);
			if ("".equals(fileNameExist)) {
				// 上传原文件到文件夹
				multipartFile.transferTo(fileOriginal);
				fileNameExist = fileName;
			} else {
				fileOriginal.delete();
				String fileNameExistSub = fileNameExist;
				fileNo = fileNameExistSub.substring(0,fileNameExistSub.endsWith(".") ? fileNameExistSub.lastIndexOf(".") : 0);
			}
			POIUtil poiUtil = new POIUtil();
			List<List<String>> lists = poiUtil.readCompactCosts(fileOriginal.getPath());
			for(int i=0;i<lists.size();i++){
				CarSpace _carSpace = carSpaceBiz.findByCode(lists.get(i).get(0));
				if(_carSpace == null){
					CarSpace space = new CarSpace();
					space.setSpaceNo(lists.get(i).get(0));
					space.setSpaceName(lists.get(i).get(0));
					Integer parkId = Integer.parseInt(lists.get(i).get(1).toString());
					space.setParkId(parkId);
					space.setSlaveId(lists.get(i).get(2));
					space.setAddTime(new Date());
					carSpaceBiz.save(space);
				}else{
					json.put("fileName", "数据重复");//"0"表示数据重复
					return json;
				}
			}
			json.put("fileName", "批量导入成功");
			json.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			json.put("error", false);
			json.put("fileName", "批量导入失败");
		} 
		return json;
	}
	
	
}
