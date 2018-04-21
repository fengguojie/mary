package com.chinadovey.parking.webapps.utils;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.chinadovey.parking.webapps.pojo.CarInOutRecord;
import com.chinadovey.parking.webapps.pojo.Company;
import com.chinadovey.parking.webapps.pojo.Park;
import com.chinadovey.parking.webapps.pojo.base.ChannelIncomeCountBase;
import com.chinadovey.parking.webapps.pojo.base.Entry;
import com.chinadovey.parking.webapps.pojo.base.IncomeCountBase;
import com.chinadovey.parking.webapps.pojo.base.OperatorIncomeCountBase;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

public class ParkingUtil {

	/**
	 * 含通道对象的list转为list
	 * 
	 * @param lists
	 * @param channelIncomeCounts
	 */
	public static List<List<Object>> listChannelChangeLists(List<ChannelIncomeCountBase> channelIncomeCounts) {
		List<List<Object>> lists = new ArrayList<List<Object>>();
		for (ChannelIncomeCountBase channelIncomeCount : channelIncomeCounts) {
			List<Object> list = new ArrayList<Object>();
			list.add(channelIncomeCount.getChannelName());
			list.add(channelIncomeCount.getValue());
			lists.add(list);
		}
		return lists;
	}

	/**
	 * 含操作员对象的list转为list
	 * 
	 * @param lists
	 * @param operatorIncomeCounts
	 */
	public static List<List<Object>> listOperatorChangeLists(List<OperatorIncomeCountBase> operatorIncomeCounts) {
		List<List<Object>> lists = new ArrayList<List<Object>>();
		for (OperatorIncomeCountBase operatorIncomeCount : operatorIncomeCounts) {
			List<Object> list = new ArrayList<Object>();
			list.add(operatorIncomeCount.getOperator());
			list.add(operatorIncomeCount.getValue());
			lists.add(list);
		}
		return lists;
	}

	/**
	 * 含支付对象的list转为list
	 * 
	 * @param lists
	 * @param operatorIncomeCounts
	 */
	public static List<List<Object>> listPayChangeLists(IncomeCountBase payIncomeCount) {
		if (payIncomeCount == null) {
			payIncomeCount = new IncomeCountBase();
		}
		List<List<Object>> lists = new ArrayList<List<Object>>();
		List<Object> cashlist = new ArrayList<Object>();
		cashlist.add("现金支付");
		cashlist.add(payIncomeCount.getCashIncome());
		lists.add(cashlist);
		List<Object> onlinelist = new ArrayList<Object>();
		onlinelist.add("在线支付");
		onlinelist.add(payIncomeCount.getOnlineIncome());
		lists.add(onlinelist);
		return lists;
	}

	/**
	 * 含通道对象的list转为entry对象list
	 * 
	 * @param lists
	 * @param channelIncomeCounts
	 */
	public static List<Entry> listChannelChangeListEntrys(List<ChannelIncomeCountBase> channelIncomeCounts) {
		List<Entry> lists = new ArrayList<Entry>();
		for (ChannelIncomeCountBase channelIncomeCount : channelIncomeCounts) {
			Entry entry = new Entry();
			entry.setKey(channelIncomeCount.getChannelName());
			entry.setValue(channelIncomeCount.getValue());
			lists.add(entry);
		}
		return lists;
	}

	/**
	 * 含操作员对象的list转为entry对象list
	 * 
	 * @param lists
	 * @param operatorIncomeCounts
	 */
	public static List<Entry> listOperatorChangeListEntrys(List<OperatorIncomeCountBase> operatorIncomeCounts) {
		List<Entry> lists = new ArrayList<Entry>();
		for (OperatorIncomeCountBase operatorIncomeCount : operatorIncomeCounts) {
			Entry entry = new Entry();
			entry.setKey(operatorIncomeCount.getOperator());
			entry.setValue(operatorIncomeCount.getValue());
			lists.add(entry);
		}
		return lists;
	}

	/**
	 * 含支付对象的list转为entry对象list
	 * 
	 * @param lists
	 * @param operatorIncomeCounts
	 */
	public static List<Entry> listPayChangeListEntrys(IncomeCountBase payIncomeCount) {
		if (payIncomeCount == null) {
			payIncomeCount = new IncomeCountBase();
		}
		List<Entry> lists = new ArrayList<Entry>();
		Entry cash = new Entry();
		cash.setKey("现金支付");
		cash.setValue(payIncomeCount.getCashIncome());
		lists.add(cash);
		Entry online = new Entry();
		online.setKey("在线支付");
		online.setValue(payIncomeCount.getOnlineIncome());
		lists.add(online);
		return lists;
	}

	/**
	 * 将收入统计数据转换为list
	 * 
	 * @param parks
	 * @param incomeCountBases
	 * @return
	 */
	public static List<List<Object>> incomeListChangeLists(List<Park> parks, List<IncomeCountBase> incomeCountBases) {
		List<List<Object>> lists = new ArrayList<List<Object>>();
		for (IncomeCountBase incomeCountBase : incomeCountBases) {
			
			for (Park park : parks) {
				if (incomeCountBase.getParkId() == park.getId()) {
					List<Object> list = new ArrayList<Object>();
					list.add(park.getName());
					list.add(incomeCountBase.getValue());
					lists.add(list);
					break;
				}
			}
			
		}
		return lists;
	}

	/**
	 * 根据id获取list列表中的数据 直接循环
	 * 
	 * @param parks
	 * @param parkId
	 * @return
	 */
	public static Park getParkByListAndId(List<Park> parks, Integer parkId) {
		for (Park park : parks) {
			if (parkId.equals(park.getId())) {
				return park;
			}
		}
		return null;
	}

	/**
	 * 根据id获取list列表中的数据 直接循环
	 * 
	 * @param parks
	 * @param parkId
	 * @return
	 */
	public static String getParkNameByListAndId(List<Park> parks, Integer parkId) {
		Park park = getParkByListAndId(parks, parkId);
		return park == null ? "没有查询到车场名称" : park.getName();
	}

	public static String getCompanyNameByListAndId(List<Company> companys, Integer id){
		Company company = (Company) getObjectByListAndId(companys, id);
		return company == null ? "没有查询到公司名称" : company.getName();
	}

	private static Object getObjectByListAndId(List<?> objs, Integer id) {
		for (Object obj : objs) {
			Method getId;
			try {
				getId = obj.getClass().getMethod("getId");
				Integer oId = (Integer) getId.invoke(obj);
				if (id.equals(oId)) {
					return obj;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static Object toBean(String data, Class clazz) {
		// try {
		// JSONArray array = JSONArray.fromObject(data);
		// Object[] obj = new Object[array.size()];
		// for (int i = 0; i < array.size(); i++) {
		// JSONObject jsonObject = array.getJSONObject(i);
		// //处理时间避免获取当前系统时间——原因JSONObject不能识别“yyyy-MM-dd”的格式
		// JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new
		// String[]{"yyyy-MM-dd","yyyy-MM-dd"}));
		// obj[i] = JSONObject.toBean(jsonObject, clazz);
		//
		// }
		// return obj;
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		try {
			JSONObject jsonObject = JSONObject.fromObject(data);
			// 处理时间避免获取当前系统时间——原因JSONObject不能识别“yyyy-MM-dd”的格式
			JSONUtils.getMorpherRegistry()
					.registerMorpher(new DateMorpher(new String[] { "yyyy-MM-dd", "yyyy-MM-dd" }));
			Object obj = JSONObject.toBean(jsonObject, clazz);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

	public static String spanFormatter(CarInOutRecord carInOutRecord) {
		if(carInOutRecord==null) return "";
		int parkingSpan = carInOutRecord.getParkingSpan();
		if (parkingSpan < 0) {
			parkingSpan = 0;
		}
		if (DateUtil.toJudge(carInOutRecord.getInTime(), carInOutRecord.getOutTime())) {
			Date now = new Date();
			parkingSpan = DateUtil.getBetweenMinutes(now, carInOutRecord.getInTime());
		}
		if (parkingSpan > 1440) {
			return (int)Math.floor(parkingSpan / 1440) + " 天 " + (int)Math.floor((parkingSpan % 1440) / 60) + " 小时 " + (parkingSpan % 60) + " 分钟";
		} else if (parkingSpan > 60) {
			return (int)Math.floor(parkingSpan / 60) + " 小时 " + (parkingSpan % 60) + " 分钟";
		} else {
			return parkingSpan + "分钟";
		}
	}
	
	
	public static String getPicPath(){
		 return  WebPath.getTomcatPath() + File.separator + "dovey-parking-file"
				+ File.separator;
	}
	
}
