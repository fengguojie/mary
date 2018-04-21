package com.chinadovey.parking.webapps.pojo.wechat.pay;

public class ServiceProviderPayBase extends PayBase {

	/**
	 * 子商户公众账号ID
	 */
	private String subAppid;
	/**
	 * 子商户号
	 */
	private String subMchId;
	public String getSubAppid() {
		return subAppid;
	}
	public void setSubAppid(String subAppid) {
		this.subAppid = subAppid;
	}
	public String getSubMchId() {
		return subMchId;
	}
	public void setSubMchId(String subMchId) {
		this.subMchId = subMchId;
	}
}
