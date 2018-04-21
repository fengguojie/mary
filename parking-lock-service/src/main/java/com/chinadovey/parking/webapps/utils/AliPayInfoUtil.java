package com.chinadovey.parking.webapps.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.chinadovey.parking.webapps.alipay.config.AlipayConfig;
import com.chinadovey.parking.webapps.alipay.sign.RSA;

/** 
* @author 作者  xqy
* @version 创建时间：2016年9月19日 下午3:20:29 
* 类说明 
*/
public class AliPayInfoUtil {
	/**
	 * 获取订单信息 （对信息进行RSA加密）
	 * 
	 * @param orderNo
	 * @param subject
	 * @param body
	 * @param price
	 * @param json
	 * @return
	 */
	public static String getSignOrderInfo(String partner,String seller,String notifyUrl,String orderNo, String subject, String body, String price,String rsaPrivate) {
		try {
			String orderInfo = getOrderInfo(partner,seller,notifyUrl,orderNo, subject, body, price);
			// 订单

			// 对订单做RSA 签名
			String sign = sign(orderInfo,rsaPrivate);
			try {
				// 仅需对sign 做URL编码
				sign = URLEncoder.encode(sign, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			// 完整的符合支付宝参数规范的订单信息
			String payInfo = orderInfo + "&sign=\"" + sign + "\"&" + getSignType();
			return payInfo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * create the order info. 创建订单信息
	 * 
	 */
	private static String getOrderInfo(String partner,String seller,String notifyUrl,String orderNo, String subject, String body, String price) {
		// 签约合作者身份ID
		String orderInfo = "partner=" + "\"" + partner + "\"";

		// 签约卖家支付宝账号
		orderInfo += "&seller_id=" + "\"" + seller + "\"";

		// 商户网站唯一订单号
		orderInfo += "&out_trade_no=" + "\"" + orderNo + "\"";

		// 商品名称
		orderInfo += "&subject=" + "\"" + subject + "\"";

		// 商品详情
		orderInfo += "&body=" + "\"" + body + "\"";

		// 商品金额
		orderInfo += "&total_fee=" + "\"" + price + "\"";

		// 服务器异步通知页面路径
		orderInfo += "&notify_url=" + "\"" + notifyUrl + "\"";

		// 服务接口名称， 固定值
		orderInfo += "&service=\"mobile.securitypay.pay\"";

		// 支付类型， 固定值
		orderInfo += "&payment_type=\"1\"";

		// 参数编码， 固定值
		orderInfo += "&_input_charset=\"utf-8\"";

		// 设置未付款交易的超时时间
		// 默认30分钟，一旦超时，该笔交易就会自动被关闭。
		// 取值范围：1m～15d。
		// m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
		// 该参数数值不接受小数点，如1.5h，可转换为90m。
		orderInfo += "&it_b_pay=\"30m\"";

		// extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
		// orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

		// 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
		orderInfo += "&return_url=\"m.alipay.com\"";

		// 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
		// orderInfo += "&paymethod=\"expressGateway\"";

		return orderInfo;
	}

	/**
	 * sign the order info. 对订单信息进行签名
	 * 
	 * @param content
	 *            待签名订单信息
	 */
	private static String sign(String content,String rsaPrivate) {
		return RSA.sign(content, rsaPrivate, AlipayConfig.INPUT_CHARSET);
	}

	/**
	 * get the sign type we use. 获取签名方式
	 * 
	 */
	private static String getSignType() {
		return "sign_type=\"RSA\"";
	}
}
