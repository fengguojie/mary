package com.chinadovey.parking.webapps.pojo.base;

/**
 * 会员常量
 * 
 * @author Administrator
 *
 */
public class MemberConstants {

	/**
	 * 未支付-3
	 */
	public final static Integer STATUS_NO_PAY = 9;

	/**
	 * 会员续费未支付-2
	 */
	public final static Integer STATUS_RENEW_NO_PAY = -2;
	
	/**
	 * 已支付未审核-1
	 */
	public final static Integer STATUS_PAY_UNAUDITED = -1;

	/**
	 * 未审核0
	 */
	public final static Integer STATUS_UNAUDITED = 0;

	/**
	 * 正常1
	 */
	public final static Integer STATUS_NORMAL = 1;

	/**
	 * 锁定2
	 */
	public final static Integer STATUS_LOCKED = 2;

	/**
	 * 过期3
	 */
	public final static Integer STATUS_EXPIRED = 3;

	/**
	 * 挂失4
	 */
	public final static Integer STATUS_REPORT_LOSS = 4;

	/**
	 * 无效5
	 */
	public final static Integer STATUS_INVALID = 5;

	/**
	 * 新增操作类型1
	 */
	public final static Integer OPERATE_ADD_TYPE = 1;

	/**
	 * 续费操作类型2
	 */
	public final static Integer OPERATE_RENEWAL_TYPE = 2;

	/**
	 * 取消操作类型3
	 */
	public final static Integer OPERATE_CANNEL_TYPE = 3;

	/**
	 * 卡类型11
	 */
	public final static Integer CARD_TYPE = 11;

	/**
	 * 月类型0
	 */
	public final static Integer MONTH_TYPE = 0;

	/**
	 * 季度类型1
	 */
	public final static Integer QUARTER_TYPE = 1;

	/**
	 * 半年类型2
	 */
	public final static Integer HALF_YEAR_TYPE = 2;

	/**
	 * 年类型3
	 */
	public final static Integer YEAR_TYPE = 3;

}
