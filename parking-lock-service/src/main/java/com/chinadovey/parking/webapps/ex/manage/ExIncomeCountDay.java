package com.chinadovey.parking.webapps.ex.manage;

public class ExIncomeCountDay{
	
	private int parkId;
	private String parkName;
	private float incomeZ;    //周收入
	private Double turnoverRateZ;//周转换率
	private Double useRateZ;//周利用率 
	private float incomeY;   // 月收入
	private Double turnoverRateY;//月转换率
	private Double useRateY;//月利用率 
	private float incomeR; //日收入
	private float incomeN; //年收入
	
	public int getParkId() {
		return parkId;
	}
	public void setParkId(int parkId) {
		this.parkId = parkId;
	}
	public String getParkName() {
		return parkName;
	}
	public void setParkName(String parkName) {
		this.parkName = parkName;
	}
	public float getIncomeZ() {
		return incomeZ;
	}
	public void setIncomeZ(float incomeZ) {
		this.incomeZ = incomeZ;
	}
	public Double getTurnoverRateZ() {
		return turnoverRateZ;
	}
	public void setTurnoverRateZ(Double turnoverRateZ) {
		this.turnoverRateZ = turnoverRateZ;
	}
	public Double getUseRateZ() {
		return useRateZ;
	}
	public void setUseRateZ(Double useRateZ) {
		this.useRateZ = useRateZ;
	}
	public float getIncomeY() {
		return incomeY;
	}
	public void setIncomeY(float incomeY) {
		this.incomeY = incomeY;
	}
	public Double getTurnoverRateY() {
		return turnoverRateY;
	}
	public void setTurnoverRateY(Double turnoverRateY) {
		this.turnoverRateY = turnoverRateY;
	}
	public Double getUseRateY() {
		return useRateY;
	}
	public void setUseRateY(Double useRateY) {
		this.useRateY = useRateY;
	}
	public float getIncomeR() {
		return incomeR;
	}
	public void setIncomeR(float incomeR) {
		this.incomeR = incomeR;
	}
	public float getIncomeN() {
		return incomeN;
	}
	public void setIncomeN(float incomeN) {
		this.incomeN = incomeN;
	}
	
}
