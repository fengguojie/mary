package com.chinadovey.parking.webapps.controller.baseInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.chinadovey.parking.core.annotation.SecurityAccessCheckable;
import com.chinadovey.parking.core.mvc.AbstractBaseController;
import com.chinadovey.parking.core.mvc.AjaxResult;
import com.chinadovey.parking.core.mvc.AjaxResult.Result;
import com.chinadovey.parking.core.secu.SessionOpt;
import com.chinadovey.parking.webapps.biz.CarLockBiz;
import com.chinadovey.parking.webapps.biz.baseInfo.CarSpaceBiz;
import com.chinadovey.parking.webapps.biz.baseInfo.GatewayBiz;
import com.chinadovey.parking.webapps.biz.baseInfo.ParkBiz;
import com.chinadovey.parking.webapps.controller.tool.ControlBase;
import com.chinadovey.parking.webapps.pojo.CarLock;
import com.chinadovey.parking.webapps.pojo.CarLockSerial;
import com.chinadovey.parking.webapps.pojo.CarLockSpace;
import com.chinadovey.parking.webapps.pojo.CarSpace;
import com.chinadovey.parking.webapps.pojo.Gateway;
import com.chinadovey.parking.webapps.pojo.Park;
import com.chinadovey.parking.webapps.utils.FileUtil;
import com.chinadovey.parking.webapps.utils.POIUtil;
import com.chinadovey.parking.webapps.utils.ParkingUtil;
import com.chinadovey.parking.webapps.utils.StringUtil;

import net.sf.json.JSONObject;


@Controller
@RequestMapping("/baseInfo/carLock")
public class CarLockController extends AbstractBaseController {
	@Autowired
	private ParkBiz parkBiz;
	
	@Autowired
	private CarLockBiz carLockBiz;
	
	@Autowired
	private CarSpaceBiz carSpaceBiz;
	
	@Autowired
	private GatewayBiz gatewayBiz;
	
	@Autowired
	private ControlBase controlBase;

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
		List<Integer> parkIds = (List<Integer>) session.getAttribute(SessionOpt.PARK_ID);
		List<Park> parks;
		List<CarSpace> carSpaces;
		if (parkIds == null || parkIds.isEmpty()) {
			parks = parkBiz.getAll();
			carSpaces = carSpaceBiz.getAll();
		} else {
			parks = parkBiz.getAll(parkIds);
			//carSpaces = carSpaceBiz.getAll(parkIds);
		}
		model.addAttribute("parks", parks);
		//model.addAttribute("carSpaces", carSpaces);
		return "/baseInfo/carLock_list";
	}
	/**
	 * 返回到list页面
	 * wy 
	 * 
	 * @param page
	 * @param model
	 * @param session
	 * @return
	 */
	@SecurityAccessCheckable(resource = BaseCarLockRank.class)
	@RequestMapping("/Pagelist")
	public String list(int page, Model model, HttpSession session) {
		List<Integer> parkIds = (List<Integer>) session.getAttribute(SessionOpt.PARK_ID);
		List<Park> parks;
		List<CarSpace> carSpaces;
		if (parkIds == null || parkIds.isEmpty()) {
			parks = parkBiz.getAll();
			carSpaces = carSpaceBiz.getAll();
		} else {
			parks = parkBiz.getAll(parkIds);
			//carSpaces = carSpaceBiz.getAll(parkIds);
		}
		model.addAttribute("parks", parks);
		//model.addAttribute("carSpaces", carSpaces);
		model.addAttribute("Newpage",page);
		return "/baseInfo/carLock_list";
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
			List<Integer> parkIds = (List<Integer>) session.getAttribute(SessionOpt.PARK_ID);
			List<Park> parks;
			if (parkIds == null || parkIds.isEmpty()) {
				parks = parkBiz.getAll();
			} else {
				parks = parkBiz.getAll(parkIds);
			}
			
			if (page > 0 && rows > 0) {
				Map<String, Object> map = carLockBiz.getList(parkIds, page, rows, sort, order, search);
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
		try {
			List<Integer> parkIds = (List<Integer>) session.getAttribute(SessionOpt.PARK_ID);
			List<Park> parks;
			//查询尚未绑定车锁的车位信息
			List<CarSpace> carSpaces;
			//车场所属的网关信息
			List<Gateway> gateways;
			
			if (parkIds == null || parkIds.isEmpty()) {
				parks = parkBiz.getAll();
				carSpaces = carSpaceBiz.getAll();
				gateways = gatewayBiz.getAll();
			} else {
				parks = parkBiz.getAll(parkIds);
				carSpaces = carSpaceBiz.findAll(parkIds);
				gateways = gatewayBiz.findByParkIds(parkIds);
			}
			List<String> spaceNoList = new ArrayList<String>();
			for(CarSpace space : carSpaces){
				if(space.getSlaveId() == null || space.getSlaveId().equals("0")){//车锁尚未绑定车位
					spaceNoList.add(space.getSpaceNo());
				}
			}
			model.addAttribute("parks", parks);
			model.addAttribute("spaceNoList", spaceNoList);
			model.addAttribute("gateways", gateways);
			return "/baseInfo/carLock_add";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("进入车位锁添加页面失败！");
			return "500";
		}
	}
	/**
	 * 查询车场对应的网关信息
	 * wy
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
			List<Integer> parkIds = (List<Integer>) session.getAttribute(SessionOpt.PARK_ID);
			List<CarLock> carLocks;
			//查询车场对应的所有网关
			if(parkId != 0){
				carLocks = carLockBiz.findByParkId(parkId);
				obj.put("carLocks", carLocks);
			}else{
				//查询所有车场所属的网管信息
				carLocks = carLockBiz.findByParkIds(parkIds);
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
	 * 验证车锁编号是否已经存在
	 * wy
	 * 
	 * @param slaveId
	 * @return
	 */
	@SecurityAccessCheckable(resource = BaseCarLockRank.class)
	@RequestMapping("/checkSlaveId")
	@ResponseBody
	public boolean checkSlaveId(String slaveId) {
		return !carLockBiz.isSlaveIdExit(slaveId);
	}
	/**
	 * 保存信息
	 * wy
	 * 
	 * @param parkId
	 * @param slaveId
	 * @param gatewayNo
	 * @param spaceNo
	 * @param delay
	 * @param session
	 * @return
	 */
	@SecurityAccessCheckable(resource = BaseCarLockRank.class)
	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(Integer parkId, String slaveId, String gatewayNo, String spaceNo, Integer delay, HttpSession session) {
		try {
			CarLock carLock = new CarLock();
			if(spaceNo.equals("0")){
				carLock.setParkId(parkId);
				carLock.setSlaveId(slaveId);
				carLock.setGatewayNo(gatewayNo);
				carLock.setDelay(delay);
				carLockBiz.save(carLock);
				
			}else{
				//查询车位信息，更新且绑定车锁
				CarSpace carSpace = carSpaceBiz.findByCode(spaceNo);
				carSpace.setSlaveId(slaveId);
				carSpaceBiz.update(carSpace);
				
				carLock.setParkId(parkId);
				carLock.setSlaveId(slaveId);
				carLock.setGatewayNo(gatewayNo);
				carLock.setDelay(delay);
				carLock.setIsBind(1);//车锁绑定车位
				carLockBiz.save(carLock);
			}
			return new AjaxResult(Result.SUCCESS);
		} catch (Exception e) {
			logger.error("保存停车场失败!", e);
		}
		return new AjaxResult(Result.FAIL);
	}
	/**
	 * 删除
	 * wy
	 * 
	 * @param id
	 * @return
	 */
	@SecurityAccessCheckable(resource = BaseCarLockRank.class)
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(int id) {
		try {
			if (id > 0) {
				//解除绑定，更新车位信息
				CarLock carLock = carLockBiz.find(id);
				CarSpace carSpace = carSpaceBiz.findBySlaveId(carLock.getSlaveId());
				if(carSpace != null){
					carSpace.setSlaveId("0");
					carSpaceBiz.update(carSpace);
				}
				//删除
				carLockBiz.delete(id);
				return new AjaxResult(Result.SUCCESS);
			}
		} catch (Exception e) {
			logger.error("删除失败！", e);
		}
		return new AjaxResult(Result.FAIL);
	}
	/**
	 * 修改
	 * wy
	 * 
	 * @param id
	 * @param page
	 * @param session
	 * @param model
	 * @return
	 */
	@SecurityAccessCheckable(resource = BaseCarLockRank.class)
	@RequestMapping("/edit")
	public String edit(int id, int page, HttpSession session, Model model) {
		try {
			List<Integer> parkIds = (List<Integer>) session.getAttribute(SessionOpt.PARK_ID);
			CarLock carLock = carLockBiz.find(id);
			//查询网管信息
			List<CarLock> carLocks;
			//查询尚未绑定车锁的车位信息
			List<CarSpace> carSpaces;
			//车场所属的网关信息
			List<Gateway> gateways;
			
			if (parkIds == null || parkIds.isEmpty()) {
				carSpaces = carSpaceBiz.getAll();
				gateways = gatewayBiz.getAll();
			} else {
				//查询所有网关信息并去重
				gateways = gatewayBiz.findByParkIds(parkIds);
				Iterator<Gateway> iterator =  gateways.iterator();
				while(iterator.hasNext()){
					Gateway gateway = iterator.next();
					if(carLock.getGatewayNo().equals(gateway.getGatewayNo())){
						iterator.remove();
						break;
					}
				}
				carSpaces = carSpaceBiz.findAll(parkIds);
			}
			Park park = null;
			if(carLock.getParkId() != null){
				park = parkBiz.find(carLock.getParkId());
				model.addAttribute("park", park);
			}
			//查询所有车场信息并去重
			List<Park> parks = parkBiz.getAll(parkIds);
			Iterator<Park> iterator =  parks.iterator();
			while(iterator.hasNext()){
				Park parking = iterator.next();
				if(park.getName().equals(parking.getName())){
					iterator.remove();
					break;
				}
			}
			//根据slaveId查询车位信息
			CarSpace carSpace = carSpaceBiz.findBySlaveId(carLock.getSlaveId());
			
			List<String> spaceNoList = new ArrayList<String>();
			for(CarSpace space : carSpaces){
				if(space.getSlaveId() == null || space.getSlaveId().equals("0")){//车锁尚未绑定车位
					spaceNoList.add(space.getSpaceNo());
				}
			}
			model.addAttribute("carSpace", carSpace);
			model.addAttribute("carLock", carLock);
			model.addAttribute("parks", parks);
			model.addAttribute("spaceNoList", spaceNoList);
			model.addAttribute("gateways", gateways);
			model.addAttribute("colentpage", page);
			return "/baseInfo/carLock_edit";
			
		} catch (Exception e) {
			logger.error("进入修改页面失败！", e);
		}
		return "500";
	}
	/**
	 * 根据id验证slaveId是否存在
	 * wy
	 * 
	 * @param slaveId
	 * @param id
	 * @return
	 */
	@SecurityAccessCheckable(resource = BaseCarLockRank.class)
	@RequestMapping("/checkSlaveIdById")
	@ResponseBody
	public boolean checkSlaveIdById(String slaveId, int id) {
		return !carLockBiz.isSlaveIdExitById(slaveId, id);
	}
	/**
	 * 更新
	 * wy
	 * 
	 * @param id
	 * @param parkId
	 * @param slaveId
	 * @param gatewayNo
	 * @param spaceNo
	 * @param delay
	 * @param session
	 * @return
	 */
	@SecurityAccessCheckable(resource = BaseCarLockRank.class)
	@RequestMapping("/update")
	@ResponseBody
	public AjaxResult update(Integer id, Integer parkId, String slaveId, String gatewayNo, String spaceNo, Integer delay, HttpSession session) {
		try {
			CarLock carLock = carLockBiz.find(id);
			carLock.setParkId(parkId);
			carLock.setGatewayNo(gatewayNo);
			carLock.setDelay(delay);
			if(spaceNo.equals("0")){//车锁未绑定车位
				if(carLock.getSlaveId().equals(slaveId)){//没有修改车锁编号
					//根据slaveId解除车位的绑定
					CarSpace carSpace = carSpaceBiz.findBySlaveId(slaveId);
					carSpace.setSlaveId("0");
					carSpaceBiz.update(carSpace);
					//更新车锁
					carLock.setIsBind(0);
					carLockBiz.update(carLock);
				}else{
					CarSpace carSpace = carSpaceBiz.findBySlaveId(carLock.getSlaveId());
					carSpace.setSlaveId("0");
					carSpaceBiz.update(carSpace);
					//更新车锁
					carLock.setSlaveId(slaveId);
					carLock.setIsBind(0);
					carLockBiz.update(carLock);
				}
			}else{
				CarSpace carSpace = carSpaceBiz.findBySlaveId(carLock.getSlaveId());
				if(carSpace != null){
					carSpace.setSlaveId("0");
					carSpaceBiz.update(carSpace);
				}
				//根据spaceNo查询车位信息
				CarSpace carSpace1 = carSpaceBiz.findByCode(spaceNo);
				carSpace1.setSlaveId(slaveId);
				carSpace1.setParkId(parkId);
				carSpaceBiz.update(carSpace1);
				//绑定车锁
				carLock.setSlaveId(slaveId);
				carLock.setIsBind(1);
				carLockBiz.update(carLock);
			}
			return new AjaxResult(Result.SUCCESS);
		} catch (Exception e) {
			logger.error("更新失败！", e);
		}
		return new AjaxResult(Result.FAIL);
	}
	/**
	 * 车位锁配置——手动/自动
	 * wy
	 * 
	 * @param slaveId
	 * @param session
	 * @param model
	 * @return
	 */
	@SecurityAccessCheckable(resource = BaseCarLockRank.class)
	@RequestMapping("/autoOrHandClose")
	@ResponseBody
	public AjaxResult autoOrHandClose(String slaveId, HttpSession session, Model model) {
		try {
			Integer result = null;
			CarLock carLock = carLockBiz.getBySlaveid(slaveId);
			if(carLock.getIsauto() == 3){//自动关闭
				result = controlBase.autoOrHand(slaveId, 4);
			}else if(carLock.getIsauto() == 4){//手动关闭
				result = controlBase.autoOrHand(slaveId, 3);
			}
			if(result == 0){
				return new AjaxResult(Result.SUCCESS);
			}
			
		} catch (Exception e) {
			logger.error("自动关闭配置失败！", e);
		}
		return new AjaxResult(Result.FAIL);
	}
	/**
	 * 车锁-网关关系的配置
	 * wy
	 * 
	 * @param id
	 * @param page
	 * @param session
	 * @param model
	 * @return
	 */
	@SecurityAccessCheckable(resource = BaseCarLockRank.class)
	@RequestMapping("/configView")
	public String configView(int id, int page, HttpSession session, Model model) {
		try {
			//查询车锁信息
			CarLock carLock = carLockBiz.find(id);
			//查询网关对应的串口配置信息
			Gateway gateway = gatewayBiz.getByDasId(carLock.getGatewayNo());
			
			model.addAttribute("carLock", carLock);
			model.addAttribute("serialA", gateway.getSeriala());
			model.addAttribute("serialB", gateway.getSerialb());
			model.addAttribute("colentpage",page);
			return "/baseInfo/carLock_config";
			
		} catch (Exception e) {
			logger.error("进入配置页面失败！", e);
		}
		return "500";
	}
	/**
	 * 车锁网关配置
	 * wy
	 * 
	 * @param slaveId
	 * @param gatewayNo
	 * @param serial
	 * @return
	 */
	@SecurityAccessCheckable(resource = BaseCarLockRank.class)
	@RequestMapping("/config")
	@ResponseBody
	public AjaxResult config(String slaveId, String gatewayNo, String serial) {
		try {
			int result = controlBase.carlockConfig(gatewayNo, slaveId, serial);
			if(result == 0){//配置成功
				return new AjaxResult(Result.SUCCESS);
			}
		} catch (Exception e) {
			logger.error("更新失败！", e);
		}
		return new AjaxResult(Result.FAIL);
	}
	/**
	 * 导入
	 * wy
	 * 
	 * @param type
	 * @param request
	 * @param model
	 * @return
	 */
	@SecurityAccessCheckable(resource = BaseCarLockRank.class)
	@RequestMapping("/upload")
	@ResponseBody
	public JSONObject upload(String type,  HttpServletRequest request, Model model) {
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
			List<CarLockSpace> list = new ArrayList<CarLockSpace>();
			List<String> slaveIdList = new ArrayList<String>();
			List<String> spaceNoList = new ArrayList<String>();
			
			POIUtil poiUtil = new POIUtil();
			List<List<String>> lists = poiUtil.readCompactCosts(fileOriginal.getPath());
			for(int i=0;i<lists.size();i++){
				CarLockSpace carLockSpace = new CarLockSpace();
				
				carLockSpace.setParkId(Integer.parseInt(lists.get(i).get(0)));//车场id
				carLockSpace.setGatewayNo(lists.get(i).get(1));//网关编号
				carLockSpace.setSlaveId(lists.get(i).get(2));//车锁编号
				carLockSpace.setSpaceNo(lists.get(i).get(3));//车位编号
				carLockSpace.setDelay(Integer.parseInt(lists.get(i).get(4)));//延时
				
				slaveIdList.add(lists.get(i).get(2));
				spaceNoList.add(lists.get(i).get(3));
				
				list.add(carLockSpace);
			}
			@SuppressWarnings({ "rawtypes", "unchecked" })
			Set<String> uniqueSet = new HashSet(slaveIdList);  
	        for (String slaveId : uniqueSet) {  
	        	//计算每个对象出现的次数
	        	System.out.println(slaveId + ": " + Collections.frequency(slaveIdList, slaveId));  
	        	int count = Collections.frequency(slaveIdList, slaveId);
	        	if(count > 1){
	        		json.put("slaveId", slaveId);
					json.put("code", 0);//"0"表示slaveId重复
					return json;
	        	}
	        } 
	        @SuppressWarnings({ "rawtypes", "unchecked" })
			Set<String> spaceNos = new HashSet(spaceNoList);
	        for (String spaceNo : spaceNos) {  
	        	//计算每个对象出现的次数
	        	System.out.println(spaceNo + ": " + Collections.frequency(spaceNoList, spaceNo));  
	        	int count = Collections.frequency(spaceNoList, spaceNo);
	        	if(count > 1){
	        		json.put("spaceNo", spaceNo);
					json.put("code", 1);//"1"表示车位编号重复
					return json;
	        	}
	        } 
			//得到每一条车锁信息
	        for(CarLockSpace carLockSpace : list){
	        	CarLock _carLock = carLockBiz.getBySlaveid(carLockSpace.getSlaveId());
	        	if(_carLock == null){
	        		CarSpace carSpace = carSpaceBiz.findByCode(carLockSpace.getSpaceNo());
		        	if(carSpace != null){
		        		carSpace.setSlaveId(carLockSpace.getSlaveId());
		        		carSpaceBiz.update(carSpace);
		        		CarLock carLock = new CarLock();
		        		carLock.setSlaveId(carLockSpace.getSlaveId());
		        		carLock.setGatewayNo(carLockSpace.getGatewayNo());
		        		carLock.setParkId(carLockSpace.getParkId());
		        		carLock.setIsBind(1);//绑定车位
		        		carLock.setDelay(carLockSpace.getDelay());
		        		carLockBiz.save(carLock);
		        		
		        	}else{
		        		json.put("number", carSpace.getSpaceNo());
						json.put("code", 2);//"2"表示车位名称不存在
						return json;
		        	}
	        	}else{
					json.put("fileName", "数据重复");//"3"数据已存在
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
	/**
	 * 批量配置
	 * wy
	 * 
	 * @param type
	 * @param request
	 * @param model
	 * @return
	 */
	@SecurityAccessCheckable(resource = BaseCarLockRank.class)
	@RequestMapping("/banchConfig")
	@ResponseBody
	public JSONObject banchConfig(String type,  HttpServletRequest request, Model model) {
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
			List<CarLockSerial> list = new ArrayList<CarLockSerial>();
			List<String> slaveIdList = new ArrayList<String>();
			
			POIUtil poiUtil = new POIUtil();
			List<List<String>> lists = poiUtil.readCompactCosts(fileOriginal.getPath());
			for(int i=0;i<lists.size();i++){
				CarLockSerial carLockSerial = new CarLockSerial();
				
				carLockSerial.setGatewayNo(lists.get(i).get(0));//网关编号
				carLockSerial.setSlaveId(lists.get(i).get(1));//车锁编号
				carLockSerial.setSerial(lists.get(i).get(2));//串口
				
				slaveIdList.add(lists.get(i).get(1));
				list.add(carLockSerial);
			}
			@SuppressWarnings({ "rawtypes", "unchecked" })
			Set<String> uniqueSet = new HashSet(slaveIdList);  
	        for (String slaveId : uniqueSet) {  
	        	//计算每个对象出现的次数
	        	int count = Collections.frequency(slaveIdList, slaveId);
	        	if(count > 1){
	        		json.put("slaveId", slaveId);
					json.put("code", 0);//"0"表示slaveId重复
					return json;
	        	}
	        } 
			//得到每一条车锁信息
	        for(CarLockSerial carLockSerial : list){
	        	CarLock _carLock = carLockBiz.getBySlaveid(carLockSerial.getSlaveId());
	        	if(_carLock == null){
	        		int result = controlBase.carlockConfig(carLockSerial.getGatewayNo(), carLockSerial.getSlaveId(), carLockSerial.getSerial());
					if(result != 0){//配置失败
						json.put("result", result);
						json.put("code", 1);//"1"表示配置失败
						return json;
					}
	        	}else{
					json.put("fileName", "数据重复");//"2"数据已存在
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
	
	
	
	
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("1");
		Set<String> uniqueSet = new HashSet(list);  
		for (String temp : list) {  
        	//计算每个对象出现的次数
        	System.out.println(temp + ": " + Collections.frequency(list, temp));  
        	int count = Collections.frequency(list, temp);
        	System.out.println(count);
        	if(count > 1){
        		
        	}else{
        		
        	}
        } 
	}
	

}

