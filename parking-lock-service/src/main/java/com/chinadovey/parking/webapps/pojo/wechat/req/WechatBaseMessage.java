package com.chinadovey.parking.webapps.pojo.wechat.req;

/**
 * 微信消息基类
 * @author Administrator
 *
 */
public class WechatBaseMessage {
	//开发者微信号
	private String ToUserName;
	//发送方帐号（一个openId）
	private String FromUserName;
	//消息创建时间
	private long createTime;
	//消息类型
	private String MsgType;	
	//消息id（64位整形）
	private long MsgId;

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}


	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public long getMsgId() {
		return MsgId;
	}

	public void setMsgId(long msgId) {
		MsgId = msgId;
	}


}
