package com.chinadovey.parking.webapps.biz.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinadovey.parking.webapps.biz.account.CarUserAccountBiz;
import com.chinadovey.parking.webapps.biz.baseInfo.CarUserBiz;
import com.chinadovey.parking.webapps.mappers.gen.CarUserAccountMapper;
import com.chinadovey.parking.webapps.pojo.CarUser;
import com.chinadovey.parking.webapps.pojo.CarUserAccount;
import com.chinadovey.parking.webapps.pojo.CarUserAccountExample;

@Service
public class CarUserAccountBizImpl implements CarUserAccountBiz {

	@Autowired
	private CarUserAccountMapper carUserAccountMapper;

	@Autowired
	private CarUserBiz carUserBiz;

	@Override
	public CarUserAccount findByMobile(String mobile) {
		CarUserAccountExample carUserAccountExample = new CarUserAccountExample();
		carUserAccountExample.createCriteria().andMobileEqualTo(mobile);
		List<CarUserAccount> carUserAccounts = carUserAccountMapper.selectByExample(carUserAccountExample);
		if (carUserAccounts != null && !carUserAccounts.isEmpty()) {
			return carUserAccounts.get(0);
		}
		return null;
	}

	@Override
	public int updateCarUserAccountByOrderNo(String orderNo) {
		/*if (orderNo == null || orderNo.isEmpty()) {
			return 0;
		}
		CarUserOrder carUserOrder = carUserOrderBiz.getByOrderNo(orderNo);
		Subscribe subscribe = subscribeMapper.selectByPrimaryKey(carUserOrder.getSubscribeId());
		CarUserAccount carUserAccount = findByMobile(carUserOrder.getMobile());
		if (carUserAccount.getBalance() < carUserOrder.getValue()) {
			System.err.println("余额不足");
			return -1;
		}
		Order order = orderBiz.getOrderByOrderNo(orderNo);
		order.setStatus(2);
		carUserOrder.setStatus(2);
		orderBiz.updateStatus(orderNo, 2);
		carUserOrderBiz.update(carUserOrder);
		subscribeBiz.updateStatusById(subscribe.getId(), 3);
		saveCarUserBalanceByCarUserOrder(carUserOrder);
		carUserAccount.setBalance(carUserAccount.getBalance() - carUserOrder.getValue());
		carUserAccountMapper.updateByPrimaryKeySelective(carUserAccount);*/
		return 1;
	}

	@Override
	public int updateCarUserAccountByOrderNos(List<String> orderNos) {
		/*if (orderNos == null || orderNos.isEmpty()) {
			return 0;
		}
		if (orderNos.size() == 1) {
			return updateCarUserAccountByOrderNo(orderNos.get(0));
		}
		List<CarUserOrder> carUserOrders = carUserOrderBiz.getByOrderNos(orderNos);
		if (carUserOrders == null || carUserOrders.isEmpty()) {
			return 0;
		}

		float balance = 0f;
		for (CarUserOrder carUserOrder : carUserOrders) {
			balance += carUserOrder.getValue();
			saveCarUserBalanceByCarUserOrder(carUserOrder);
		}

		Subscribe subscribe = subscribeMapper.selectByPrimaryKey(carUserOrders.get(0).getSubscribeId());

		// 查询账户
		CarUserAccount carUserAccount = findByMobile(subscribe.getMobile());

		OrderExample orderExample = new OrderExample();
		orderExample.createCriteria().andOrderNoIn(orderNos);
		Order order = new Order();
		order.setStatus(2);
		orderMapper.updateByExampleSelective(order, orderExample);

		CarUserOrder carUserOrder = new CarUserOrder();
		carUserOrder.setStatus(2);
		updateCarUserOrderStatusBySubscribeId(subscribe, carUserOrder);

		subscribeBiz.updateStatusById(subscribe.getId(), 3);
		carUserAccount.setBalance(carUserAccount.getBalance() - balance);
		carUserAccountMapper.updateByPrimaryKeySelective(carUserAccount);*/
		return 1;
	}


	@Override
	public void saveAccountByCarUser(CarUser carUser) {
		float initialAmount = 20f;
		CarUserAccount carUserAccount = new CarUserAccount();
		carUserAccount.setBalance(initialAmount);
		carUserAccount.setCarUserId(carUser.getId());
		carUserAccount.setMobile(carUser.getMobile());
		carUserAccountMapper.insertSelective(carUserAccount);
	}

	/**
	 * 增加一条账单记录
	 * 
	 * @param carUserOrder
	 */
	/*private void saveCarUserBalanceByCarUserOrder(CarUserOrder carUserOrder) {
		CarUserBalance carUserBalance = new CarUserBalance();
		carUserBalance.setCarUserId(carUserOrder.getCarUserId());
		carUserBalance.setDate(new Date());
		carUserBalance.setOrderNo(carUserOrder.getOrderNo());
		carUserBalance.setDescription("预约车位付款" + (carUserOrder.getValue() - carUserOrder.getCouponValue()) + "元");
		carUserBalance.setStatus(0);
		carUserBalance.setType(1);
		carUserBalance.setValue(carUserOrder.getValue());
		carUserBalanceMapper.insertSelective(carUserBalance);
	}*/

	@Override
	public void updateAdvanceRefund() {
		/*AdvanceRefundExample advanceRefundExample = new AdvanceRefundExample();
		advanceRefundExample.createCriteria().andStatusEqualTo(0);
		List<AdvanceRefund> advanceRefunds = advanceRefundMapper.selectByExample(advanceRefundExample);
		if (advanceRefunds != null && !advanceRefunds.isEmpty()) {
			for (AdvanceRefund advanceRefund : advanceRefunds) {
				updateCarUserAccountByCarUserIdAndBalance(advanceRefund.getCarUserId(), advanceRefund.getValue());
			}
			AdvanceRefund advanceRefund = new AdvanceRefund();
			advanceRefund.setStatus(1);
			advanceRefundMapper.updateByExampleSelective(advanceRefund, advanceRefundExample);
		}*/
	}

	@Override
	public void updateOrderRefund() {
		/*OrderRefundExample orderRefundExample = new OrderRefundExample();
		orderRefundExample.createCriteria().andStatusEqualTo(0);
		List<OrderRefund> orderRefunds = orderRefundMapper.selectByExample(orderRefundExample);
		if (orderRefunds != null && !orderRefunds.isEmpty()) {
			for (OrderRefund orderRefund : orderRefunds) {
				updateCarUserAccountByCarUserIdAndBalance(orderRefund.getCarUserId(), orderRefund.getFee());
			}
			OrderRefund orderRefund = new OrderRefund();
			orderRefund.setStatus(1);
			orderRefundMapper.updateByExampleSelective(orderRefund, orderRefundExample);
		}*/
	}

	/**
	 * 根据carUserId将余额增加
	 * 
	 * @param carUserId
	 * @param balance
	 */
	@Override
	public void updateCarUserAccountByCarUserIdAndBalance(Integer carUserId, float balance) {
		CarUserAccountExample carUserAccountExample = new CarUserAccountExample();
		carUserAccountExample.createCriteria().andCarUserIdEqualTo(carUserId);
		CarUserAccount carUserAccount = carUserAccountMapper.selectByExample(carUserAccountExample).get(0);
		carUserAccount.setBalance(carUserAccount.getBalance() + balance);
		carUserAccountMapper.updateByExampleSelective(carUserAccount, carUserAccountExample);
	}

	@Override
	public float getIncomeByMobile(String mobile) {
		return 0;
		/*CarUser carUser = carUserBiz.getByMobile(mobile);
		CarUserBalanceExample carUserBalanceExample = new CarUserBalanceExample();
		carUserBalanceExample.createCriteria().andCarUserIdEqualTo(carUser.getId()).andTypeEqualTo(4);
		List<CarUserBalance> carUserBalances = carUserBalanceMapper.selectByExample(carUserBalanceExample);
		float income = 0f;
		for (CarUserBalance carUserBalance : carUserBalances) {
			income += carUserBalance.getValue();
		}

		return income;*/
	}

	@Override
	public void updateSubscribe() {
		/*CarUserOrderExample carUserOrderExample = new CarUserOrderExample();
		carUserOrderExample.createCriteria().andStatusEqualTo(1);
		List<CarUserOrder> carUserOrders = carUserOrderMapper.selectByExample(carUserOrderExample);
		List<String> orderNos = new ArrayList<String>();
		for (CarUserOrder carUserOrder : carUserOrders) {
			orderNos.add(carUserOrder.getOrderNo());
		}
		updateCarUserAccountByOrderNos(orderNos);
	*/
	}
}
