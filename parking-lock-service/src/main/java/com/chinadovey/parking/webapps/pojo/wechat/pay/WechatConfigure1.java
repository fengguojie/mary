package com.chinadovey.parking.webapps.pojo.wechat.pay;

import com.chinadovey.parking.webapps.pojo.wechat.AccessToken;

public class WechatConfigure1 {

	/**
	 * 第三方用户唯一凭证 公众账号ID
	 */
	private String appId;
	
	/**
	 * 商户号
	 */
	private String mchId;

	/**
	 * 验证信息
	 */
	private String token;

	/**
	 * 消息加密使用的key
	 */
	private String encodingAesKey;

	/**
	 * 第三方用户唯一凭证密钥
	 */
	private String appSecret;

	/**
	 * 交易过程生成签名的密钥
	 */
	private String mchKey;


	/**
	 * jsapi-ticket 每隔一段时间刷新一次
	 */
	private String ticket;

	/**
	 * 存放在内存中的AccessToken，每隔一段时间刷新一次
	 */
	private String accessToken;
	
	/**
	 * 初始化
	 * @param appId 
	 * @param token
	 * @param encodingAesKey
	 * @param appSecret
	 */
	public WechatConfigure1(String appId, String token, String encodingAesKey, String appSecret) {
		super();
		this.appId = appId;
		this.token = token;
		this.encodingAesKey = encodingAesKey;
		this.appSecret = appSecret;
	}
	
	public WechatConfigure1(String appId, String mchId, String token, String encodingAesKey, String appSecret,
			String mchKey) {
		super();
		this.appId = appId;
		this.mchId = mchId;
		this.token = token;
		this.encodingAesKey = encodingAesKey;
		this.appSecret = appSecret;
		this.mchKey = mchKey;
	}

	public WechatConfigure1() {
		// TODO Auto-generated constructor stub
	}
	
	
	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getEncodingAesKey() {
		return encodingAesKey;
	}

	public void setEncodingAesKey(String encodingAesKey) {
		this.encodingAesKey = encodingAesKey;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getMchKey() {
		return mchKey;
	}

	public void setMchKey(String mchKey) {
		this.mchKey = mchKey;
	}

	public  String getTicket() {
		return ticket;
	}

	public  void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

}
