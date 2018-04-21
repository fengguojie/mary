package com.chinadovey.parking.webapps.alipay.config;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
	
 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {
	
	//商户PID
	public static final String PARTNER = "2088021437985533";
	//商户收款账号
	public static final String SELLER = "liyx@zhrhq.com";
	//商户私钥，pkcs8格式
	public static final String RSA_PRIVATE = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAL9W5ShEayLR2a0+V+b09frbQZZ8Dq8PymONA1HGK3nudBdvKlH/Dn6YkOtGkYBdZ1p4X+DZkQgCo3z3Thi6/eTHxS+KHA5eWUp3lLiu5TkOEKpCQIrEHTkxUwUSZD12yh2bzFye64r0i7xVU8olFgzNqL5jK1VIqWnmEoMChoRFAgMBAAECgYAYnKbRbSuSyQeE/1XzQWM6p2ViZAbHgfJ4p7TbmfARuRh5wzTgYzyMEDfj66nfpyQaQQ2kMJg1PZGmC3GiQLtTr1Upni+FBEwzdqsGuLz1Nosfc8ylHRy2nrgEceR+AkobWcgYfUTZIBGt/8OrUB0NdSOdLX31zwkgnZxLwjqEqQJBAOhtSVpHUfhLw0hGPNg/W0xXqa80nMOpcUJKbxORs0cJbyEEcLqsKkByoeIcTrMp6fwktiOahE96AtQJrFWSh+8CQQDSvtEhpSS/ayDwy37xcSY6spB9TOOQCUJVO2Lb3v5ZiP+BDbLCQX3UJFZaJJ8ac8V2UbvPOzNVqCfC/XCAqyMLAkEAujdNDLqVbuRsegxkFTF1mU2m0ovJiW3JIz51je0vAJwLYB2pn7XuSCJPltPtLdhH8k8Laq0SkoaDm5SAcTLsGwJBAKSm5vfVcc+IIHVhWC3FDGxBxLfQ0d5gDY5P1p4YLDrB2XX3wNkal/ed6EWHZTMijn0xwH1ZMHNU7LPglPxCOR8CQQCE6huQkfczf1rZML6LqwuSQ+eFz0PEAunmq/pixuOLnsvbNClnAAHg1FDTJTA0GC9AFyK9s3GzXMkZ6Tem1oU0";
	//支付宝公钥
	public static final String RSA_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
    //编码格式
	public static String INPUT_CHARSET = "utf-8";
	//签名方式
    public static String SIGN_TYPE = "RSA";
    
	// 调试用，创建TXT日志文件夹路径
	public static String log_path = "../logs/";
	
//	public static String NOTIFY_URL = "http://112.124.9.133:8080/parking/android/alipay/notify/default";
//	public static String NOTIFY_URL = "http://dovey-parking.ngrok.natapp.cn/dovey-parking/android/alipay/notify/default";
//	public static String NOTIFY_URL = "http://115.28.209.219:8080/parking-app-client-1.0/android/newApi/alipay/notify/default";
//	public static String NOTIFY_URL = "http://park.ngrok.cc/parking-app-client/android/newApi/alipay/notify/default";
	public static String NOTIFY_URL = "http://service.huaching.com/parking-app-client/android/newApi/alipay/notify/default";


}
