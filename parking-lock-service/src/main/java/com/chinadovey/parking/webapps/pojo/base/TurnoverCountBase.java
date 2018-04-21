package com.chinadovey.parking.webapps.pojo.base;

import java.util.Date;

public class TurnoverCountBase {

	private Date endTime;
	
	private Double turnover;//利用率
	
	private Double utilization;//周转率

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Double getTurnover() {
		return turnover;
	}

	public void setTurnover(Double turnover) {
		this.turnover = turnover;
	}

	public Double getUtilization() {
		return utilization;
	}

	public void setUtilization(Double utilization) {
		this.utilization = utilization;
	}

	
}
