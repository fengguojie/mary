package com.chinadovey.parking.core.mvc;

public class DataResult {
	
	public DataResult(boolean result) {
		super();
		this.result = result;
	}

	private boolean result;

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}
}
