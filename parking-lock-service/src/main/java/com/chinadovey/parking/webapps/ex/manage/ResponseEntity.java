package com.chinadovey.parking.webapps.ex.manage;

import javax.xml.crypto.Data;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import net.sf.json.JSONObject;

/** 
* @author 作者  xqy
* @version 创建时间：2016年5月19日 下午4:10:29 
* 类说明 
*/
public class ResponseEntity {

		
	/**
	 * 返回结果标识
	 */
	private Boolean result;
	/**
	 * 消息
	 */
	private String msg;
	
	private Object data = new Object();
	
	public ResponseEntity() {
		this.result = false;
	}
	public ResponseEntity(Boolean result) {
		this.result = result;
	}

	public ResponseEntity(Boolean result,String msg) {
		this.result = result;
		this.msg = msg;
	}

	public Boolean getResult() {
		return result;
	}

	public String getMsg() {
		return msg;
	}
		
		
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public void setResult(Boolean result) {
		this.result = result;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

		
	public JSONObject toJson() {
			JSONObject json = new JSONObject();
			return json.fromObject(this);
	}
}

