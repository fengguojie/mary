package com.chinadovey.parking.webapps.pojo.wechat;

/**
 * 微信配置常量
 * @author Administrator
 *
 */
public class WechatConfigureConstant {

	/**
	 * 项目路径
	 */
	public final static String WEB_PATH = "web_path";

	/**
	 * 车辆入场通知模版id
	 */
	public final static String CAR_IN_TEMPLATE_ID = "car_in_template_id";
	/**
     * 车锁损坏通知
	 */
	public final static String SUB_CAR_LOCK = "sub_car_lock";
	/**
	 * 车辆出场通知模板id
	 */
	public final static String CAR_OUT_TEMPLATE_ID = "car_out_template_id";

	/**
	 * 回复消息内容设置
	 */
	public final static String RESP_CONTENT = "resp_content";

	/**
	 * 关注公众号回复类型,SubType
	 */
	public final static String SUB_TYPE = "sub_type";

	public enum SubType {
		NEWS("news"), TEXT("text");

		private String type;

		public String getType() {
			return type;
		}

		SubType(String type) {
			this.type = type;
		}

	}

	/**
	 * 关注公众号回复图文信息内容
	 */
	public final static String SUB_DESCRIPTION = "sub_description";

	/**
	 * 关注公众号回复图片链接
	 */
	public final static String SUB_PIC_URL = "sub_pic_url";

	/**
	 * 关注公众号回复图文标题
	 */
	public final static String SUB_TITLE = "sub_title";

	/**
	 * 关注公众号回复图文信息点击链接
	 */
	public final static String SUB_URL = "sub_url";

	/**
	 * 关注公众号回复文字信息
	 */
	public final static String SUB_RESP_CONTENT = "sub_resp_content";
	
	
	/**
	 * 关注公众号回复图文信息内容
	 */
	public final static String DIDI_SUB_DESCRIPTION = "didi_sub_description";

	/**
	 * 关注公众号回复图片链接
	 */
	public final static String DIDI_SUB_PIC_URL = "didi_sub_pic_url";

	/**
	 * 关注公众号回复图文标题
	 */
	public final static String DIDI_SUB_TITLE = "didi_sub_title";

	/**
	 * 关注公众号回复图文信息点击链接
	 */
	public final static String DIDI_SUB_URL = "didi_sub_url";
	
	/**
	 * 关注公众号回复图文信息内容
	 */
	public final static String COUPON_SUB_DESCRIPTION = "coupon_sub_description";

	/**
	 * 关注公众号回复图片链接
	 */
	public final static String COUPON_SUB_PIC_URL = "coupon_sub_pic_url";

	/**
	 * 关注公众号回复图文标题
	 */
	public final static String COUPON_SUB_TITLE = "coupon_sub_title";

	/**
	 * 关注公众号回复图文信息点击链接
	 */
	public final static String COUPON_SUB_URL = "coupon_sub_url";
	
	
	/**
	 * 关注公众号回复图文信息内容
	 */
	public final static String 	CAR_NO_SUB_DESCRIPTION = "carno_sub_description";

	/**
	 * 关注公众号回复图片链接
	 */
	public final static String CAR_NO_SUB_PIC_URL = "carno_sub_pic_url";

	/**
	 * 关注公众号回复图文标题
	 */
	public final static String CAR_NO_SUB_TITLE = "carno_sub_title";

	/**
	 * 关注公众号回复图文信息点击链接
	 */
	public final static String CAR_NO_SUB_URL = "carno_sub_url";
	
	
	
}
