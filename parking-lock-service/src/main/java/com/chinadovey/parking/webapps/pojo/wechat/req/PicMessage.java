package com.chinadovey.parking.webapps.pojo.wechat.req;

public class PicMessage extends WechatBaseMessage{

	private String PicUrl;
	
	private String MediaId;

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String MediaId) {
		this.MediaId = MediaId;
	}

}
