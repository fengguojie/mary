package com.chinadovey.parking.webapps.pojo.wechat;

/**
 * 微信通用接口凭证
 * 
 */
public class CodeAccessToken extends AccessToken{
	private String refreshToken;
	
	private String openid;
	
	private String scope;
	
	private String unionid;

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
}
