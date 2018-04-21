package com.chinadovey.parking.webapps.controller.map;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinadovey.parking.acl.ParkMapRank;
import com.chinadovey.parking.core.annotation.SecurityAccessCheckable;
import com.chinadovey.parking.core.mvc.AbstractBaseController;
import com.chinadovey.parking.webapps.biz.CarLockBiz;
import com.chinadovey.parking.webapps.biz.baseInfo.CarSpaceBiz;
import com.chinadovey.parking.webapps.biz.baseInfo.GatewayBiz;
import com.chinadovey.parking.webapps.biz.baseInfo.ParkMapBiz;
import com.chinadovey.parking.webapps.ex.manage.ResponseEntity;
import com.chinadovey.parking.webapps.pojo.CarLock;
import com.chinadovey.parking.webapps.pojo.CarSpace;
import com.chinadovey.parking.webapps.pojo.Gateway;
import com.chinadovey.parking.webapps.pojo.ParkMap;
import com.chinadovey.parking.webapps.utils.ConfUtils;
import com.chinadovey.parking.webapps.utils.HttpClientUtil;
import com.chinadovey.parking.webapps.utils.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Controller
@RequestMapping("/map/map")
public class MapController extends AbstractBaseController {
	@Autowired
	private ParkMapBiz parkMapBiz;
	@Autowired
	private CarSpaceBiz carSpaceBiz;
	@Autowired
	private CarLockBiz carLockBiz;
	@Autowired
	private GatewayBiz gatewayBiz;
	
	
	@SecurityAccessCheckable(resource = ParkMapRank.class)
	@RequestMapping("/getMap")
	public String getMap(Model model) {
		return "/maps/map";
	}
	@SecurityAccessCheckable(resource = ParkMapRank.class)
	@RequestMapping("/getMapData")
	@ResponseBody
	public JSONObject getMapData(String pic) {
		Date date1 = new Date();
		ResponseEntity responseEntity = new ResponseEntity(false);
		try {
		/**
		 * 业务逻辑，从车场地图表中，查询一层的所有数据，取得所有车位名称。2、根据车位名称，在车位表中获得对应的所有车位锁编号。
		 * 
		 * 3、根据车锁编号，从车锁表中得到所有的车锁信息
		 * 
		 */
		Map<String,String> map = new HashMap<String,String>();
		List<ParkMap> pList = parkMapBiz.getAllByparkIdAndPic(1,pic);//查询车场一层的所有数据。如：车位名称
		List<String> codes = new ArrayList<String>();
		for(ParkMap p:pList){
			codes.add(p.getName());//得到车位名称，的集合
		}
		List<CarSpace> spaceList = carSpaceBiz.getAllByCode(codes);//根据车位名称，从车场表中查询每个车位对应的信息，如：车位锁编号
		List<String> slaveIds = new ArrayList<String>();
		for(CarSpace s:spaceList){
			slaveIds.add(s.getSlaveId());//得到所有的车位锁编号
		}
		
		//lizhao
		List<CarLock> carLocks = carLockBiz.getAllBySlaveIds(slaveIds);//根据车位锁编号，从车锁表中，拿到对应的车位锁集合。
		Date date2 = new Date();
		
		
		Map<String,String> carsData = new HashMap<String,String>(); //有车
		Map<String,String> noCarsData = new HashMap<String,String>(); //无车
		List<String> carsName = new ArrayList<String>();
		List<String> noCarsName = new ArrayList<String>();
		/**
		 * 根据车锁找到车位。再根据车位名称得到车场地图实例。
		 */
		if(carLocks!=null && !carLocks.isEmpty()){
			for(CarLock cl:carLocks){
				CarSpace carSpace = carSpaceBiz.findBySlaveId(cl.getSlaveId());//根据车位锁编号，得到对应车场信息
				if(2==cl.getSwitchStatus()){ //关，车锁竖起。是无车。
					
					if(carSpace!=null){
						ParkMap pm = parkMapBiz.getByName(carSpace.getSpaceNo());//根据车位名称，得到车场地图的一个实例
						if(pm!=null){
							noCarsData.put("'"+pm.getName()+"'", "["+pm.getxAxis()+","+pm.getyAxis()+"]");//无车map中，放入车位名称、车位地址
							
							/**
							 * li
							 */
						/*	Map<String,String> mapName = new HashMap<String,String>();
							mapName.put("name","'"+pm.getName()+"'");
							noCarsName.add(MapToString(mapName));//车位名称     	
*/						
							String name=new String();
							name="{name:'" +pm.getName()+ "'}";
							noCarsName.add(name);
							
					}
					}
				}else if(1==cl.getSwitchStatus()){//开，车锁平台。有车。
					if(carSpace!=null){
						ParkMap pm = parkMapBiz.getByName(carSpace.getSpaceNo());
						if(pm!=null){
							carsData.put("'"+pm.getName()+"'", "["+pm.getxAxis()+","+pm.getyAxis()+"]");
							
							/**
							 * li 下面的代码，用了迭代，
							 */
						/*	Map<String,String> mapName = new HashMap<String,String>();
							mapName.put("name","'"+pm.getName()+"'");
							carsName.add(MapToString(mapName));   // carName :[{name:li}, {name:li}]
*/						
							String name=new String();
							name="{name:'" +pm.getName()+ "'}";
							carsName.add(name);
							
						}
					}
				}
			}
		}
		
		Date date3 = new Date();
		
		long cha1 = (date2.getTime()-date1.getTime());//这是？
		System.out.println("拿到车锁集合了："+cha1);
		
		long cha2 = (date3.getTime()-date2.getTime());//这是？
		System.out.println("后一半："+cha2);
		
		map.put("carsData", MapToString(carsData));// carsData={'0436':[718,648],'0541':[185,355],
		map.put("carsName", carsName.toString());  // carsName=[{name:'0760'}, {name:'0759'},
		map.put("noCarsData", MapToString(noCarsData));
		map.put("noCarsName", noCarsName.toString());
		responseEntity.setData(map);
		responseEntity.setResult(true);
		
		Date date4 = new Date();
		long cha3 = (date4.getTime()-date3.getTime());//这是？
		System.out.println("zui后一半："+cha3);
		
		return responseEntity.toJson();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return responseEntity.toJson();
		}
	}
	
	
	
	@SecurityAccessCheckable(resource = ParkMapRank.class)
	@RequestMapping("/getDate")
	@ResponseBody
	public JSONObject getDate() {
		ResponseEntity responseEntity = new ResponseEntity(false);
		
		List<ParkMap> list = parkMapBiz.getAllByparkIdAndPic(1,"B");
//		{'A102': [39, 45],'B408': [71, 45]};
		Map<String,String> map = new HashMap<String,String>();
		
		
		for(ParkMap p:list){
			
			map.put(p.getName(), "["+p.getxAxis()+","+p.getyAxis()+"]");
		}
		Map<String,Object> maps = new HashMap<String,Object>();
		maps.put("zbs", MapToString(map));
		
		List<String> lists = new ArrayList<String>();
		for(ParkMap p:list){
			Map<String,String> map1 = new HashMap<String,String>();
			map1.put("name","'"+p.getName()+"'");
			lists.add(MapToString(map1));
		}
		maps.put("names", lists);
		
		responseEntity.setResult(true);
		responseEntity.setData(maps);
		return responseEntity.toJson();
	}

	private String MapToString(Map map){
		java.util.Map.Entry entry; 
		 StringBuffer sb = new StringBuffer();  
		 sb.append("{");
		  for(Iterator iterator = map.entrySet().iterator(); iterator.hasNext();)  

		  {  
		    entry = (java.util.Map.Entry)iterator.next();  
		      sb.append(entry.getKey().toString()).append( ":" ).append(null==entry.getValue()?"":  
		      entry.getValue().toString()).append (iterator.hasNext() ? "," : "");  
		  }  
		  sb.append("}");
		  return sb.toString(); 
	}
	@SecurityAccessCheckable(resource = ParkMapRank.class)
	@RequestMapping("/lockDetail")
	@ResponseBody
	public JSONObject lockDetail(String name) {
		ResponseEntity responseEntity = new ResponseEntity(false);
		try {
		CarSpace carSpace = carSpaceBiz.getLockId(name, 1);// li 本来写的1（这是车场id）
		if(carSpace==null){
			responseEntity.setMsg("车位不存在");
			return responseEntity.toJson();
		}
		
		CarLock carlock = carLockBiz.getBySlaveid(carSpace.getSlaveId());
		//根据车锁查询网关信息
		Gateway gateway = gatewayBiz.getByDasId(carlock.getGatewayNo());
		if(gateway!=null){
			carlock.setGatewayStatus(gateway.getGatewayStatus()==1?"离线":"在线");
			if(carlock.getConfigStatus()==0){
				carlock.setSerial(gateway.getSeriala());
			}else if(carlock.getConfigStatus()==1){
				carlock.setSerial(gateway.getSerialb());
			}else if(carlock.getConfigStatus()==2){
				carlock.setSerial("未配置");
			}
		}
		
		JSONObject json  = JSONObject.fromObject(carlock);
		json.put("result", true);//li
		return json;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return responseEntity.toJson();
		}
	}
	/**
	 * 车位锁控制
	 * @param slaveId
	 * @param action  1：下降   2：上升 3:自动上升 4;手动上升  （实际上只能传1或者2）
	 * @return
	 */
	@SecurityAccessCheckable(resource = ParkMapRank.class)
	@RequestMapping("/control")
	@ResponseBody
	public JSONObject control(String slaveId,String action) {
		Map<String, String> params = new HashMap<String, String>();
		try {
		params.put("slaveId", slaveId);
		params.put("action", action);
		String res;
		
	        res = HttpClientUtil.getInstance().httpPost(ConfUtils.getUrlHead()+"/control/operate", params);
		
		    JSONObject json = JSONObject.fromObject(res); 
		    int stat = json.getInt("stat");
			if(stat==-1 || stat==14){
				json.put("result",false);
			}
		return json;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	@SecurityAccessCheckable(resource = ParkMapRank.class)
	@RequestMapping("/save")
	@ResponseBody
	public JSONObject save(String parkMap) {
		ResponseEntity responseEntity = new ResponseEntity(false);
		try {
			if (!StringUtil.isEmpty(parkMap)) {
				ParkMap _parkMap = (ParkMap) JSONObject.toBean(
						JSONObject.fromObject(parkMap), ParkMap.class);
				_parkMap.setParkId(1);
				parkMapBiz.save(_parkMap);
				responseEntity.setResult(true);
			}
		} catch (Exception e) {
			logger.error("保存失败!", e);
			responseEntity.setMsg("保存失败!");
		}
		return responseEntity.toJson();
	}
	@SecurityAccessCheckable(resource = ParkMapRank.class)
	@RequestMapping("/a")
	public String a(Model model) {

		return "a";
	}
	@SecurityAccessCheckable(resource = ParkMapRank.class)
	@RequestMapping("/b")
	public String b(Model model) {
		
		return "b";
	}
	/**
	 * 原server中的方法。
	 * @param ids
	 * @return
	 */
	public JSONObject getBySlaveIds(String ids){
		JSONObject json = new JSONObject();
		try {
			List<String> _ids = new ArrayList<String>();
			for(String id: ids.substring(1,ids.length()-1).split(",")){
				_ids.add(id.trim());
			}
			List<CarLock> carLocks = carLockBiz.getAllBySlaveIds(_ids);
			
			JSONArray array = JSONArray.fromObject(carLocks);
			json.put("carLocks", array.toString());
			json.put("result", true);
			return json;
		} catch (Exception e) {
			json.put("result", false);
			e.printStackTrace();
			return json;
		}
	}
	/**
	 * lizhao ceshi
	 * @param map
	 * @return
	 */
	private static String MapToString2(Map map){
		java.util.Map.Entry entry; 
		 StringBuffer sb = new StringBuffer();  
		 sb.append("{");
		  for(Iterator iterator = map.entrySet().iterator(); iterator.hasNext();)  

		  {  
		    entry = (java.util.Map.Entry)iterator.next();  
		      sb.append(entry.getKey().toString()).append( ":" ).append(null==entry.getValue()?"":  
		      entry.getValue().toString()).append (iterator.hasNext() ? "," : "");  
		  }  
		  sb.append("}");
		  return sb.toString(); 
	}
	public static void main(String[] args) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("name", "li");
		Map<String,String> map2 = new HashMap<String,String>();
		map2.put("name", "li");
		String s = MapToString2(map);
		String s2 = MapToString2(map2);
		
		List<String> list = new ArrayList<String>();
		list.add(s);
		list.add(s2);
		System.out.println(list);
		
		System.out.println(map);//{name3=li3, name=li, name2=li2}
		System.out.println(s);//{name3:li3,name:li,name2:li2}
		String li ="li";
		String name="{name:" +li+ "}";
		String name2="{name:" +li+ "}";
		System.out.println(name);
		
		List<String> list2 = new ArrayList<String>();
		list2.add(name);
		list2.add(name2);
		System.out.println(list2);
	}
	/**
	 * 明细控制数据列表
	 * @return 
	 */
	@SecurityAccessCheckable(resource=ParkMapRank.class)
	@RequestMapping("/detailControl")
	@ResponseBody
	public Map<String, Object> detailControl(int page, int rows, String sort, String order,String search){
		Map<String, Object> map = carSpaceBiz.getDetailData(page, rows, search, sort, order);
		return map;
	}
}
