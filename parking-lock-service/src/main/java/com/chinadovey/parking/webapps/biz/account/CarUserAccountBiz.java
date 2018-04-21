package com.chinadovey.parking.webapps.biz.account;

import java.util.List;

import com.chinadovey.parking.webapps.pojo.CarUser;
import com.chinadovey.parking.webapps.pojo.CarUserAccount;

public interface CarUserAccountBiz {

	/**
	 * 根据手机号查询账户信息
	 * @param mobile
	 * @return
	 */
	CarUserAccount findByMobile(String mobile);

	/**
	 * 更新订单
	 * @param orderNo
	 * @return
	 */
	int updateCarUserAccountByOrderNo(String orderNo);

	/**
	 * 更新订单
	 * @param orderNos
	 * @return
	 */
	int updateCarUserAccountByOrderNos(List<String> orderNos);

	/**
	 * 保存提前退款信息
	 */
	void updateAdvanceRefund();

	/**
	 * 保存订单退款信息      
	 */
	void updateOrderRefund();

	/**
	 * 根据用户生成初始账户
	 * @param carUser
	 */
	void saveAccountByCarUser(CarUser carUser);

	/**
	 * 根据手机号得到收入
	 * @param mobile
	 * @return
	 */
	float getIncomeByMobile(String mobile);

	/**
	 * 根据用户id和金额将余额增加
	 * @param carUserId
	 * @param balance
	 */
	void updateCarUserAccountByCarUserIdAndBalance(Integer carUserId, float balance);

	/**
	 * 更新订阅信息，自动扣除金额
	 */
	void updateSubscribe();


}
