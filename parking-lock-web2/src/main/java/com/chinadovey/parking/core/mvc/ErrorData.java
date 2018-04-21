package com.chinadovey.parking.core.mvc;

public class ErrorData extends DataResult {

	public ErrorData(Integer errcode, String errmsg) {
		super(false);
		this.errcode = errcode;
		this.errmsg = errmsg;
	}

	public ErrorData(Integer errcode) {
		super(false);
		this.errcode = errcode;
	}

	public ErrorData() {
		super(false);
	}

	private Integer errcode;

	private String errmsg;

	public Integer getErrcode() {
		return errcode;
	}

	public void setErrcode(Integer errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
}
