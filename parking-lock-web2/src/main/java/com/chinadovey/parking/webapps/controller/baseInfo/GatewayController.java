package com.chinadovey.parking.webapps.controller.baseInfo;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinadovey.parking.acl.BaseGatewayRank;
import com.chinadovey.parking.core.annotation.SecurityAccessCheckable;
import com.chinadovey.parking.core.mvc.AbstractBaseController;
import com.chinadovey.parking.core.mvc.AjaxResult;
import com.chinadovey.parking.core.mvc.AjaxResult.Result;
import com.chinadovey.parking.core.secu.SessionOpt;
import com.chinadovey.parking.webapps.biz.baseInfo.GatewayBiz;
import com.chinadovey.parking.webapps.biz.baseInfo.ParkBiz;
import com.chinadovey.parking.webapps.mina.client.ParkCloudClient;
import com.chinadovey.parking.webapps.mina.protocol.DASSerialPortPackets;
import com.chinadovey.parking.webapps.mina.protocol.ProtocolConst;
import com.chinadovey.parking.webapps.mina.protocol.RESPackets;
import com.chinadovey.parking.webapps.pojo.Gateway;
import com.chinadovey.parking.webapps.pojo.Park;
import com.chinadovey.parking.webapps.utils.ByteUtil;
import com.chinadovey.parking.webapps.utils.ConfUtils;

import net.sf.json.JSONObject;


@Controller
@RequestMapping("/baseInfo/gateway")
public class GatewayController extends AbstractBaseController {
	@Autowired
	private ParkBiz parkBiz;
	
	@Autowired
	private GatewayBiz gatewayBiz;
	
	
	/**
	 * 显示列表页
	 * wy
	 * 
	 * @param session
	 * @param model
	 * @return
	 */
	@SecurityAccessCheckable(resource = BaseGatewayRank.class)
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
		return "/baseInfo/gateway_list";
	}
	/**
	 * 获取分页数据
	 * wy
	 * 
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @param search
	 * @return
	 */
	@SecurityAccessCheckable(resource = BaseGatewayRank.class)
	@RequestMapping("/getList")
	@ResponseBody
	public Map<String, Object> getList(int page, int rows, String sort, String order, String search, HttpSession session) {
		try {
			List<Integer> parkIds = (List<Integer>) session.getAttribute(SessionOpt.PARK_ID);
			List<Park> parks;
			if (parkIds == null || parkIds.isEmpty()) {
				parks = parkBiz.getAll();
			} else {
				parks = parkBiz.getAll(parkIds);
			}
			
			if (page > 0 && rows > 0) {
				Map<String, Object> map = gatewayBiz.getList(parkIds, page, rows, sort, order, search);
				return map;
			}
		} catch (Exception e) {
			logger.error("获取网关分页数据失败！", e);
		}
		return null;
	}
	/**
	 * 返回列表页
	 * wy
	 * 
	 * @param page
	 * @param model
	 * @param session
	 * @return
	 */
	@SecurityAccessCheckable(resource = BaseGatewayRank.class)
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
		return "/baseInfo/gateway_list";
	}
	/**
	 * 添加
	 * wy
	 * 
	 * @param session
	 * @param model
	 * @return
	 */
	@SecurityAccessCheckable(resource = BaseGatewayRank.class)
	@RequestMapping("/add")
	public String add(HttpSession session, Model model) {
		try {
			List<Park> parks = parkBiz.getAll();
			if(parks != null && !parks.isEmpty()){
				model.addAttribute("parks", parks);
			}
			return "/baseInfo/gateway_add";
		} catch (Exception e) {
			logger.error("进入网关添加页面失败！");
			return "500";
		}
	}
	/**
	 * 保存
	 * wy
	 * 
	 * @param gatewayNo
	 * @param gatewayName
	 * @param parkId
	 * @param remark
	 * @param session
	 * @return
	 */
	@SecurityAccessCheckable(resource = BaseGatewayRank.class)
	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(String gatewayNo, String gatewayName, Integer parkId, String remark, HttpSession session) {
		try {
			Gateway gateway = new Gateway();
			gateway.setGatewayNo(gatewayNo);
			gateway.setGatewayName(gatewayName);
			gateway.setParkId(parkId);
			gateway.setRemark(remark);
			gatewayBiz.save(gateway);

			return new AjaxResult(Result.SUCCESS);
		} catch (Exception e) {
			logger.error("添加网关失败!", e);
		}
		return new AjaxResult(Result.FAIL);
	}
	/**
	 * 查看详情
	 * wy
	 * 
	 * @param id
	 * @param page
	 * @param model
	 * @return
	 */
	@SecurityAccessCheckable(resource = BaseGatewayRank.class)
	@RequestMapping("/detail")
	public String detail(int id, int page, Model model) {
		try {
			Gateway gateway = gatewayBiz.find(id);
			if(gateway != null){
				Park park = parkBiz.find(gateway.getParkId());
				if(park != null){
					model.addAttribute("park", park);
				}
				model.addAttribute("gateway", gateway);
			}
			model.addAttribute("colentpage",page);
			return "/baseInfo/gateway_detail";
		} catch (Exception e) {
			logger.error("进入详情页面失败！", e);
		}
		return "500";
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
	@SecurityAccessCheckable(resource = BaseGatewayRank.class)
	@RequestMapping("/edit")
	public String edit(int id, int page, HttpSession session, Model model) {
		try {
			Gateway gateway = gatewayBiz.find(id);
			Park park = parkBiz.find(gateway.getParkId());
			model.addAttribute("park", park);
			model.addAttribute("gateway", gateway);
			//查询所有车场信息
			List<Park> parks = parkBiz.getAll();
			//除去重复车场信息
			Iterator<Park> iterator =  parks.iterator();
			while(iterator.hasNext()){
				Park parking = iterator.next();
				if(park.getName().equals(parking.getName())){
					iterator.remove();
					break;
				}
			}
			model.addAttribute("parks", parks);
			model.addAttribute("colentpage",page);
			return "/baseInfo/gateway_edit";
		} catch (Exception e) {
			logger.error("进入修改页面失败！", e);
		}
		return "500";
	}
	/**
	 * 更新
	 * wy
	 * 
	 * @param id
	 * @param gatewayNo
	 * @param gatewayName
	 * @param parkId
	 * @param remark
	 * @param session
	 * @return
	 */
	@SecurityAccessCheckable(resource = BaseGatewayRank.class)
	@RequestMapping("/update")
	@ResponseBody
	public AjaxResult update(Integer id, String gatewayNo, String gatewayName, Integer parkId, String remark, HttpSession session ) {
		try {
			//查询一条记录
			Gateway gateway = gatewayBiz.find(id);
			if(gateway != null){
				gateway.setGatewayNo(gatewayNo);
				gateway.setGatewayName(gatewayName);
				gateway.setParkId(parkId);
				gateway.setRemark(remark);
				gatewayBiz.update(gateway);
				return new AjaxResult(Result.SUCCESS);
			}
		} catch (Exception e) {
			logger.error("更新停车场失败！", e);
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
	@SecurityAccessCheckable(resource = BaseGatewayRank.class)
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(Integer id) {
		try {
			if(id != null){
				gatewayBiz.delete(id);
				return new AjaxResult(Result.SUCCESS);
			}
		} catch (Exception e) {
			logger.error("删除网关失败！", e);
		}
		return new AjaxResult(Result.FAIL);
	}
	/**
	 * 串口配置
	 * wy
	 * 
	 * @param id
	 * @param session
	 * @param model
	 * @param page
	 * @return
	 */
	@SecurityAccessCheckable(resource = BaseGatewayRank.class)
	@RequestMapping("/config")
	public String config(int id, int page, HttpSession session, Model model) {
		try {
			Gateway gateway = gatewayBiz.find(id);
			model.addAttribute("gateway", gateway);
			model.addAttribute("colentpage",page);
			return "/baseInfo/gateway_config";
		} catch (Exception e) {
			logger.error("进入配置页面失败！", e);
		}
		return "500";
	}
	/**
	 * 保存串口配置信息
	 * wy
	 * 
	 * @param gateway
	 * @return
	 */
	@SecurityAccessCheckable(resource = BaseGatewayRank.class)
	@RequestMapping("/saveconfig")
	@ResponseBody
	public AjaxResult saveconfig(String gateway) {
		try {
			JSONObject json = JSONObject.fromObject(gateway);
			Gateway _gateway = (Gateway) json.toBean(json, Gateway.class);
			DASSerialPortPackets dspp = new DASSerialPortPackets();
			dspp.setDasTag(ProtocolConst.CMD_CONF_DAS_PORT);
			
			String dasIdStr = _gateway.getGatewayNo();
			dspp.setDasId(ByteUtil.writeInt4(Integer.parseInt(dasIdStr,16), 0));
			
			String port1Str = _gateway.getSeriala();
			dspp.setPort1(ByteUtil.writeInt4(Integer.parseInt(port1Str,16), 0));
			
			String wire1Str = _gateway.getWirea();
			dspp.setWire1(ByteUtil.writeInt2(Integer.parseInt(wire1Str,16), 0));
			
			String channel1Str = _gateway.getChannela();
			dspp.setChannel1((byte)Integer.parseInt(channel1Str,16));
			
			String port2Str = _gateway.getSerialb();
			dspp.setPort2(ByteUtil.writeInt4(Integer.parseInt(port2Str,16), 0));
			
			String wire2Str = _gateway.getWireb();
			dspp.setWire2(ByteUtil.writeInt2(Integer.parseInt(wire2Str,16), 0));
			
			String channel2Str = _gateway.getChannelb();
			dspp.setChannel2((byte)Integer.parseInt(channel2Str,16));
			
			String ip = ConfUtils.getControlAddress();
			ParkCloudClient cc = new ParkCloudClient(ip);
			byte[] tag = new byte[]{0x02,0x05};
			RESPackets res = cc.execute(tag, dspp, 1000l * 30);
            
			String msg = "";
			byte value[] = res.getValue();
			byte res1 = value[4];
			byte res2 = value[9];
			System.err.println("串口1========"+res1+"串口2=========="+res2);
			_gateway.setSerialaStatus((int)res1);
			_gateway.setSerialbStatus((int)res2);
			if((int)res1 == 0){
				if((int)res2 == 0){
					msg = "串口1,2都配置成功";
				}else{
					msg = "串口1配置成功";
				}
			}else{
				if((int)res2 == 0){
					msg = "串口2配置成功";
				}else{
					msg = "串口1,2都配置失败";
				}
			}
			gatewayBiz.update(_gateway);
			
			return new AjaxResult(Result.SUCCESS,msg);
				
		} catch (Exception e) {
			logger.error("更新停车场失败！", e);
		}
		return new AjaxResult(Result.FAIL);
	}
	/**
	 * 根据编号验证是否存在
	 * wy
	 * 
	 * @param gatewayNo
	 * @return
	 */
	@SecurityAccessCheckable(resource = BaseGatewayRank.class)
	@RequestMapping("/checkDasId")
	@ResponseBody
	public boolean checkDasId(String gatewayNo) {
		try {
			int count = gatewayBiz.getCount(gatewayNo);
			if(count == 0){
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			logger.error("验证失败！", e);
			return false;
		}
    }
	/**
	 * 根据id验证编号是否存在
	 * @param dasId
	 * @param id
	 * @return
	 */
	@SecurityAccessCheckable(resource = BaseGatewayRank.class)
	@RequestMapping("/checkDasIdById")
	@ResponseBody
	public boolean checkDasIdById(String gatewayNo, int id) {
		return !gatewayBiz.isDasIdExitById(gatewayNo, id);
    }
	


}
