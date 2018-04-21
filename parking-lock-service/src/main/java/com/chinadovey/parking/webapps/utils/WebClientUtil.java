package com.chinadovey.parking.webapps.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import com.chinadovey.client.WebClient;

import net.sf.json.JSONObject;

/**
 * 最好提到上层
 * 下发工具类，待修改
 * @author Administrator
 *
 */
public class WebClientUtil {

	private static Logger log = LoggerFactory.getLogger(WebClientUtil.class);

	private static String cloudAddress = getCloudaddress();

	/**
	 * 错误码
	 */
	private static int ERROR_CODE = -8;

	/**
	 * 支付
	 * 
	 * @param carNo
	 *            车牌号
	 * @param inDate
	 *            进场时间
	 * @param payChargeDate
	 *            支付时间
	 * @param parkId
	 *            停车场id
	 * @param payCharge
	 *            支付费用
	 * @return
	 */
	public static int markCarNo(String carNo, Date inDate, Date payChargeDate, Integer parkId, float payCharge) {
		JSONObject requestPay = new JSONObject();
		requestPay.put("carNo", carNo);

		String inTime = DateUtil.dateConvertString(inDate, 3);
		String payChargeTime = DateUtil.dateConvertString(payChargeDate, 3);

		requestPay.put("inTime", inTime);
		requestPay.put("payCharge", payCharge);
		requestPay.put("payChargeTime", payChargeTime);

		JSONObject json = new JSONObject();
		json.put("tag", "0013");
		json.put("requestPay", requestPay);
		json.put("parkId", parkId);
		try {
			JSONObject res = execute(json);
			int result = Integer.parseInt(res.getString("result"));
			return result;
		} catch (Exception e) {
			log.error("支付请求失败", e);
			return ERROR_CODE;
		}
	}

	/**
	 * 根据车牌获取收费金额
	 * 
	 * @param carNo
	 *            车牌号
	 * @param inDate
	 *            进场时间
	 * @param outDate
	 *            出场时间
	 * @param parkId
	 *            停车场id
	 * @return
	 */
	public static float getValueByCarNo(String carNo, Date inDate, Date outDate, Integer cardType, Integer parkId) {
		JSONObject requestCost = new JSONObject();
		requestCost.put("cardType", cardType);
		requestCost.put("carNo", carNo);
		String inTime = DateUtil.dateConvertString(inDate, 3);
		String outTime = DateUtil.dateConvertString(outDate, 3);
		requestCost.put("carInTime", inTime);
		requestCost.put("carOutTime", outTime);

		JSONObject json = new JSONObject();
		json.put("tag", "0009");
		json.put("requestCost", requestCost);
		json.put("parkId", parkId);
		try {
			JSONObject res = execute(json);
			Float result = Float.parseFloat(res.getString("result"));
			return result;
		} catch (Exception e) {
			log.error("获取收费金额失败", e);
			return ERROR_CODE;
		}

	}
	
	
	/**
	 * 免费住宿
	 * 
	 * @param carNo
	 *            车牌号
	 * @param inDate
	 *            进场时间
	 * @param outDate
	 *            出场时间
	 * @param parkId
	 *            停车场id
	 * @return
	 */
	public static int freeStay(String carNo, Date inDate, Date outDate, Integer parkId) {
		JSONObject obj = new JSONObject();
		obj.put("carNo", carNo);
		String inTime = DateUtil.dateConvertString(inDate, 3);
		String outTime = DateUtil.dateConvertString(outDate, 3);
		obj.put("inTime", inTime);
		obj.put("chargeTime", outTime);
		obj.put("flag", "hotel");
		obj.put("tag", "0023");
		obj.put("parkId", parkId);
		try {
			JSONObject res = execute(obj);
			int result = Integer.parseInt(res.getString("result"));
			return result;
		} catch (Exception e) {
			log.error("住宿免费失败", e);
			return ERROR_CODE;
		}

	}

	/**
	 * 免费吃饭
	 * 
	 * @param carNo
	 *            车牌号
	 * @param inDate
	 *            进场时间
	 * @param outDate
	 *            出场时间
	 * @param parkId
	 *            停车场id
	 * @return
	 */
	public static int freeEat(String carNo, Date inDate, Date outDate, Integer parkId) {
		JSONObject obj = new JSONObject();
		obj.put("carNo", carNo);
		String inTime = DateUtil.dateConvertString(inDate, 3);
		String outTime = DateUtil.dateConvertString(outDate, 3);
		obj.put("inTime", inTime);
		obj.put("chargeTime", outTime);
		obj.put("flag", "eat");
		obj.put("tag", "0023");
		obj.put("parkId", parkId);
		try {
			JSONObject res = execute(obj);
			int result = Integer.parseInt(res.getString("result"));
			return result;
		} catch (Exception e) {
			log.error("用餐免费失败", e);
			return ERROR_CODE;
		}
	}

	/**
	 * 预约
	 * 
	 * @param carNo
	 *            车牌 号
	 * @param startDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @param type
	 *            类型
	 * @param parkId
	 *            停车场id
	 * @return
	 */
	public static int reserve(String carNo, Date startDate, Date endDate, Integer type, Integer parkId) {

		JSONObject subscribe = new JSONObject();
		subscribe.put("carNo", carNo);
		subscribe.put("startTime", DateUtil.dateConvertString(startDate, 3));
		subscribe.put("endTime", DateUtil.dateConvertString(endDate, 3));
		subscribe.put("type", type);

		JSONObject json = new JSONObject();
		json.put("tag", "0005");
		json.put("subscribe", subscribe);
		json.put("parkId", parkId);

		try {
			JSONObject res = execute(json);
			int result = res.getInt("result");
			return result;
		} catch (Exception e) {
			log.error("预约失败", e);
			return ERROR_CODE;
		}
	}

	/**
	 * 免费
	 * 
	 * @param carNo
	 *            车牌号
	 * @param startDate
	 *            开始时 间
	 * @param endDate
	 *            结束时间
	 * @param type
	 *            类型 <font color="red">1、住宿 其他用餐</font>
	 * @param parkId
	 *            停车场id <font color="red">不能重复</font>
	 * @return
	 */
	public static boolean free(String carNo, Date startDate, Date endDate, Integer parkId, Integer type) {
		// TODO 这是一段无用的代码，但是谁知道以后会不会用，所以先不改了
		String flag = "eat";
		if (1 == type) {
			flag = "hotel";
		}

		JSONObject json = new JSONObject();
		json.put("carNo", carNo);
		json.put("startTime", DateUtil.dateConvertString(startDate, 3));
		json.put("endTime", DateUtil.dateConvertString(endDate, 3));
		json.put("parkId", parkId);
		json.put("tag", "0031");
		json.put("flag", flag);
		json.put("type", 1);
		try {
			JSONObject res = execute(json);
			return res.getBoolean("result");
		} catch (Exception e) {
			log.error("免费失败", e);
			return false;
		}
		// return true;
	}

	/**
	 * 取消免费
	 * 
	 * @param carNo
	 *            车牌号
	 * @param endTime
	 *            结束时间
	 * @param parkId
	 *            停车场id
	 * @return 失败返回也true
	 */
	public static boolean cancelFree(String carNo, Date endTime, Integer parkId, Integer type) {
		// TODO 这也是一段无用的代码，以后估计也用不到了
		String flag = "eat";
		if (1 == type) {
			flag = "hotel";
		}
		JSONObject json = new JSONObject();
		json.put("carNo", carNo);
		// json.put("startTime", DateUtil.dateConvertString(startTime, 3));
		json.put("endTime", DateUtil.dateConvertString(endTime, 3));
		json.put("parkId", parkId);
		json.put("tag", "0035");
		json.put("flag", flag);
		json.put("type", 2);

		try {
			JSONObject res = execute(json);
			return res.getBoolean("result");
		} catch (Exception e) {
			log.error("取消免费失败", e);
			return true;
		}
	}

	/**
	 * 获取本地图片
	 * 
	 * @param parkId
	 *            本地停车场id
	 * @param cardNo
	 *            进出记录传入卡序列号，异常开闸需传入通道号的字符串格式
	 * @param time
	 *            进出记录传入相应的时间，异常开闸传入开闸时间
	 * @param type
	 *            进出记录：0入场1出场2证件 异常开闸：1
	 * @return 返回图片名称 错误返回空串
	 */
	public static String picture(Integer parkId, String cardNo, Date time, Integer type) {
		JSONObject json = new JSONObject();
		String picName = "";
		json.put("tag", "0041");
		json.put("parkId", parkId);
		json.put("cardNo", cardNo);
		json.put("inOutTime", DateUtil.dateConvertString(time, 3));
		json.put("type", type);
		// json.put("parkId", 10);
		// json.put("cardNo", "04D5E3433836363642");
		// json.put("inOutTime", "2015-12-26 14:38:35");
		// json.put("type", 0);
		try {
			JSONObject res = execute(json);
			boolean result = res.getBoolean("result");
			if (result) {
				picName = res.getString("picName");
				return picName;
			}
		} catch (Exception e) {
			log.error("获取图片失败", e);
			return picName;
		}
		return picName;
	}

	/**
	 * 会员开卡
	 * 
	 * @param memberName
	 *            会员名 <font color="red">不能重复</font>
	 * @param memberCardNo
	 *            会员号 <font color="red">不能重复</font>
	 * @param operateType
	 *            操作类型
	 * @param carNo
	 *            车牌号 <font color="red">不能重复</font>
	 * @param cardType
	 *            卡类型
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @param monthType
	 *            月类型
	 * @param payCharge
	 *            支付金额
	 * @param remark
	 *            备注
	 * @param parkId
	 *            停车场id
	 * @return 1-人事编号不存在，2-月租模式不符合规则，3-开户类型不符合规则，4-车牌号码不符合规则，5-有效日期不符合规则，6-车牌号码已存在
	 */
	public static JSONObject member(String memberName, String memberCardNo, Integer operateType, String carNo,
			Integer cardType, Date startDate, Date endDate, Integer monthType, Integer payCharge, String remark,
			Integer parkId) {
		JSONObject json = new JSONObject();
		json.put("tag", "0051");
		json.put("memberName", memberName);
		json.put("memberId", memberCardNo);
		json.put("operateType", operateType);
		json.put("carNo", carNo);
		json.put("cardType", cardType);
		json.put("startTime", DateUtil.dateConvertString(startDate, 0));
		json.put("endTime", DateUtil.dateConvertString(endDate, 0));
		json.put("monthType", monthType);
		json.put("payCharge", payCharge);
		json.put("remark", remark);
		json.put("parkId", parkId);

		try {
			JSONObject res = execute(json);
			return res;
		} catch (Exception e) {
			log.error("开卡失败", e);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("result", false);
			jsonObject.put("type", ERROR_CODE);
			jsonObject.put("msg", ERROR_CODE);
			jsonObject.put("flag", ERROR_CODE);
			return jsonObject;
		}

	}
	
	
	public static JSONObject stay(String memberName, String memberCardNo, Integer operateType, String carNo,
			Integer cardType, Date startDate, Date endDate, Integer monthType, Integer payCharge, String remark,
			Integer parkId) {
		JSONObject json = new JSONObject();
		json.put("tag", "0079");
		json.put("carNo", carNo);
		json.put("startTime", DateUtil.dateConvertString(startDate, 3));
		json.put("endTime", DateUtil.dateConvertString(endDate, 3));
		json.put("parkId", parkId);

		try {
			JSONObject res = execute(json);
			return res;
		} catch (Exception e) {
			log.error("开户失败", e);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("result", false);
			jsonObject.put("type", ERROR_CODE);
			jsonObject.put("msg", ERROR_CODE);
			jsonObject.put("flag", ERROR_CODE);
			return jsonObject;
		}

	}
	public static JSONObject cancelStay(String carNo, Integer parkId) {
		JSONObject json = new JSONObject();
		json.put("tag", "0081");
		json.put("carNo", carNo);
		json.put("parkId", parkId);

		try {
			JSONObject res = execute(json);
			return res;
		} catch (Exception e) {
			log.error("销户失败", e);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("result", false);
			jsonObject.put("type", ERROR_CODE);
			jsonObject.put("msg", ERROR_CODE);
			return jsonObject;
		}

	}

	/**
	 * 会员销卡
	 *
	 * @param localId
	 *            会员卡id
	 * @param parkId
	 *            停车场id
	 * @return integer    =0成功，-1资料不存在，-2状态不可销户
	 */
	public static JSONObject cancellation(Integer localId, Integer parkId) {
		JSONObject json = new JSONObject();
		json.put("tag", "0059");
		json.put("yktid", localId);
		json.put("payCharge", 0);
		json.put("parkId", parkId);

		try {
			JSONObject res = execute(json);
			return res;
		} catch (Exception e) {
			log.error("销卡失败", e);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("result", false);
			jsonObject.put("type", ERROR_CODE);
			jsonObject.put("msg", ERROR_CODE);
			return jsonObject;
		}

	}

	/**
	 * 会员续费
	 * 
	 * @param localId
	 *            会员卡id
	 * @param endTime
	 *            上次结束时间
	 * @param newStartTime
	 *            新开始时间
	 * @param newEndTime
	 *            新结束时间
	 * @param payCharge
	 *            支付金额
	 * @param remark
	 *            备注
	 * @param parkId
	 *            停车场id
	 * @return integer =0成功，-1资料不存在，-2状态不可延期，-3生效日期错误
	 */
	public static JSONObject recharge(Integer localId, Date endTime, Date newStartTime, Date newEndTime,
			Integer payCharge, String remark, Integer parkId) {
		JSONObject json = new JSONObject();
		json.put("tag", "0055");
		json.put("yktid", localId);
		json.put("endTime", DateUtil.dateConvertString(endTime, 0));
		json.put("newStartTime", DateUtil.dateConvertString(newStartTime, 0));
		json.put("newEndTime", DateUtil.dateConvertString(newEndTime, 0));
		json.put("payCharge", payCharge);
		json.put("remark", remark);
		json.put("parkId", parkId);

		try {
			JSONObject res = execute(json);
			return res;
		} catch (Exception e) {
			log.error("续费失败", e);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("result", false);
			jsonObject.put("type", ERROR_CODE);
			jsonObject.put("msg", ERROR_CODE);
			return jsonObject;
		}

	}

	/**
	 * 商家设置
	 * 
	 * @param merchantNo
	 *            商家号
	 * @param merchantName
	 *            商家名称
	 * @param parkId
	 *            停车场id
	 * @param type
	 *            类型
	 * @param remark
	 *            备注
	 * @return
	 */
	public static JSONObject merchant(String merchantNo, String merchantName, Integer parkId, Integer type,
			String remark) {
		JSONObject json = new JSONObject();
		json.put("tag", "0063");
		json.put("parkId", parkId);
		json.put("merchantNo", merchantNo);
		json.put("merchantName", merchantName);
		json.put("type", type);
		json.put("remark", remark);

		try {
			JSONObject res = execute(json);
			return res;
		} catch (Exception e) {
			log.error("商家设置失败", e);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("result", false);
			jsonObject.put("type", ERROR_CODE);
			return jsonObject;
		}
	}

	/**
	 * 打折设置
	 * 
	 * @param parkId
	 *            停车场id
	 * @param merchantNo
	 *            商家号
	 * @param discountNo
	 *            打折号
	 * @param discountName
	 *            打折名称
	 * @param disAmount
	 *            打折金额
	 * @param type
	 *            类型
	 * @param operateType
	 *            操作类型
	 * @param remark
	 *            备注
	 * @return
	 */
	public static JSONObject discount(Integer parkId, String merchantNo, String discountNo, String discountName,
			Integer disAmount, Integer type, Integer operateType, String remark) {
		JSONObject json = new JSONObject();
		json.put("tag", "0067");
		json.put("parkId", parkId);
		json.put("merchantNo", merchantNo);
		json.put("discountNo", discountNo);
		json.put("discountName", discountName);
		json.put("disAmount", disAmount);
		json.put("type", type);
		json.put("operateType", operateType);
		json.put("remark", remark);
		try {
			JSONObject res = execute(json);
			return res;
		} catch (Exception e) {
			log.error("商家设置失败", e);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("result", false);
			jsonObject.put("type", ERROR_CODE);
			return jsonObject;
		}
	}

	/**
	 * 商家折扣（新版）
	 * 
	 * @param parkId
	 * @param discountName
	 * @param merchantName
	 * @param disAmount
	 * @param type
	 * @param operateType
	 *            1添加，2修改，3删除；
	 * @param remark
	 * @return
	 */
	public static JSONObject discountMerchant(Integer parkId, String merchantName, String discountName,
			String merchantNo, String discountNo, String disAmount, Integer type, Integer operateType, String remark) {
		JSONObject json = new JSONObject();
		json.put("tag", "0067");
		json.put("parkId", parkId);
		json.put("discountName", discountName);
		json.put("merchantName", merchantName + StringUtil.randomNumber(4));
		json.put("discountNo", discountNo);
		json.put("merchantNo", merchantNo);
		json.put("type", type);
		json.put("operateType", operateType);
		json.put("disAmount", disAmount);
		json.put("remark", remark == null ? "" : remark);

		try {
			JSONObject res = execute(json);
			return res;
		} catch (Exception e) {
			log.error("商家折扣设置失败", e);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("result", false);
			jsonObject.put("type", ERROR_CODE);
			return jsonObject;
		}
	}

	/**
	 * 打折
	 * 
	 * @param carNo
	 *            车牌号
	 * @param parkId
	 *            停车场id
	 * @param inTime
	 *            进场时间
	 * @param discountNo
	 *            打折号
	 * @return
	 */
	public static JSONObject dinner(String carNo, Integer parkId, Date inTime, String discountNo, String merchantNo) {
		JSONObject json = new JSONObject();
		json.put("tag", "0071");
		json.put("parkId", parkId);
		json.put("carNo", carNo);
		json.put("inTime", DateUtil.dateConvertString(inTime, 3));
		json.put("discountNo", discountNo);
		json.put("merchantNo", merchantNo);
		try {
			JSONObject res = execute(json);
			return res;
		} catch (Exception e) {
			log.error("打折失败", e);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("result", false);
			jsonObject.put("type", ERROR_CODE);
			return jsonObject;
		}

	}

	/**
	 * 请求本地服务
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	public static JSONObject execute(JSONObject json) throws Exception {
		/*WebClient cc = new WebClient(cloudAddress);
		JSONObject res = null;
		JSONObject obj = SecurityUtil.encryptJson(json);
		try {
			res = cc.execute(obj, 1000l * 800);

			if (SecurityUtil.checkJson(res)) {
				res = SecurityUtil.getData(res);
			} else {
				throw new Exception("md5检验失败");
			}
		} catch (Exception e) {
			throw new Exception("通信异常");
		}
		return res;*/
		return null;//lizhao
	}

	/**
	 * 得到链接地址
	 * 
	 * @return
	 */
	public static String getCloudaddress() {
		if (cloudAddress == null || cloudAddress.equals("")) {
			cloudAddress = getProperty("mina.host") + ":" + getProperty("mina.port");
		}
		return cloudAddress;
	}

	private static Properties prop = null;

	/**
	 * 读取配置文件
	 * 
	 * @param key
	 * @return
	 */
	public static String getProperty(String key) {
		try {
			if (prop == null) {
				InputStream in = WebClientUtil.class
						.getResourceAsStream("/com/chinadovey/parking/webapps/config/parking.properties");
				prop = new Properties();
				prop.load(in);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop.getProperty(key);
	}

}
