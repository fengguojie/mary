package com.chinadovey.parking.webapps.pojo.base;

import com.chinadovey.parking.webapps.pojo.ChargingRules;
import com.chinadovey.parking.webapps.pojo.Park;

public class ChargingRulesSubBase extends ChargingRules{

	private Park park;

	

	public Park getPark() {
		return park;
	}

	public void setPark(Park park) {
		this.park = park;
	}


	public static ChargingRulesSubBase change(ChargingRules chargingRules,Park park) {
		ChargingRulesSubBase chargingRulesSubBase = new ChargingRulesSubBase();
		chargingRulesSubBase.setId(chargingRules.getId());
		chargingRulesSubBase.setCarType(chargingRules.getCarType());
		chargingRulesSubBase.setChargeType(chargingRules.getChargeType());
		chargingRulesSubBase.setCycleTime(chargingRules.getCycleTime());
		chargingRulesSubBase.setDayEndTime(chargingRules.getDayEndTime());
		chargingRulesSubBase.setDayFirstTime(chargingRules.getDayFirstTime());
		chargingRulesSubBase.setDayFirstTimePrice(chargingRules.getDayFirstTimePrice());
		chargingRulesSubBase.setDayMaximumAmount(chargingRules.getDayMaximumAmount());
		chargingRulesSubBase.setDayRemainTimePrice(chargingRules.getDayRemainTimePrice());
		chargingRulesSubBase.setFreeTime(chargingRules.getFreeTime());
		chargingRulesSubBase.setName(chargingRules.getName());
		chargingRulesSubBase.setNightEndTime(chargingRules.getNightEndTime());
		chargingRulesSubBase.setNightFirstTime(chargingRules.getNightFirstTime());
		chargingRulesSubBase.setNightFirstTimePrice(chargingRules.getNightFirstTimePrice());
		chargingRulesSubBase.setNightMaximumAmount(chargingRules.getNightMaximumAmount());
		chargingRulesSubBase.setNightRemainTimePrice(chargingRules.getNightRemainTimePrice());
		chargingRulesSubBase.setParkId(chargingRules.getParkId());
		chargingRulesSubBase.setRuleType(chargingRules.getRuleType());
		chargingRulesSubBase.setAlldayMaximumAmount(chargingRules.getAlldayMaximumAmount());
		chargingRulesSubBase.setDayStartTime(chargingRules.getDayStartTime());
		chargingRulesSubBase.setNightStartTime(chargingRules.getNightStartTime());
		chargingRulesSubBase.setDayTimesPrice(chargingRules.getDayTimesPrice());
		chargingRulesSubBase.setNightTimesPrice(chargingRules.getNightTimesPrice());
		chargingRulesSubBase.setTimeType(chargingRules.getTimeType());
		if(park == null){
			park = new Park();
		}
		chargingRulesSubBase.setPark(park);
		return chargingRulesSubBase;
	}
	
}
