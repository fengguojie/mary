package com.chinadovey.parking.webapps.pojo.wechat.button;

/**
 * 普通按钮（子按钮）
 * 打开一个页面
 * 
 */
public class ViewButton extends Button {
	private String type;
	private String url;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
