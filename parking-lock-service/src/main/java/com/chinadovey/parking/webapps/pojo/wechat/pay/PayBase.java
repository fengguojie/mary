package com.chinadovey.parking.webapps.pojo.wechat.pay;

public class PayBase {
	/**
	 * 公众账号ID
	 */
	private String appid;
	/**
	 * 商户号
	 */
	private String mchId;
	/**
	 * 随机字符串
	 */
	private String nonceStr;
	/**
	 * 签名
	 */
	private String sign;

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
}
