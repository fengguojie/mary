package com.chinadovey.parking.webapps.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.chinadovey.parking.Constants;
import com.cloopen.rest.sdk.utils.encoder.BASE64Decoder;
import com.cloopen.rest.sdk.utils.encoder.BASE64Encoder;

import net.sf.json.JSONObject;

public class SecurityUtil {

	public static String MD5(String str) {
		byte[] md5 = {};
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md5 = md.digest(str.getBytes("UTF-8"));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5.length; i++) {
			int val = ((int) md5[i]) & 0xff;
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}

	/**
	 * 将byte[]转为各种进制的字符串
	 * 
	 * @param bytes
	 *            byte[]
	 * @param radix
	 *            可以转换进制的范围，从Character.MIN_RADIX到Character.MAX_RADIX，超出范围后变为10进制
	 * @return 转换后的字符串
	 */
	public static String binary(byte[] bytes, int radix) {
		return new BigInteger(1, bytes).toString(radix);// 这里的1代表正数
	}

	/**
	 * base 64 encode
	 * 
	 * @param bytes
	 *            待编码的byte[]
	 * @return 编码后的base 64 code
	 */
	public static String base64Encode(byte[] bytes) {
		return new BASE64Encoder().encode(bytes);
	}

	/**
	 * base 64 decode
	 * 
	 * @param base64Code
	 *            待解码的base 64 code
	 * @return 解码后的byte[]
	 * @throws Exception
	 */
	public static byte[] base64Decode(String base64Code) throws Exception {
		return StringUtil.isEmpty(base64Code) ? null : new BASE64Decoder().decodeBuffer(base64Code);
	}

	/**
	 * 获取byte[]的md5值
	 * 
	 * @param bytes
	 *            byte[]
	 * @return md5
	 * @throws Exception
	 */
	public static byte[] md5(byte[] bytes) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(bytes);

		return md.digest();
	}

	/**
	 * 获取字符串md5值
	 * 
	 * @param msg
	 * @return md5
	 * @throws Exception
	 */
	public static byte[] md5(String msg) throws Exception {
		return StringUtil.isEmpty(msg) ? null : md5(msg.getBytes());
	}

	/**
	 * 结合base64实现md5加密
	 * 
	 * @param msg
	 *            待加密字符串
	 * @return 获取md5后转为base64
	 * @throws Exception
	 */
	public static String md5Encrypt(String msg) throws Exception {
		return StringUtil.isEmpty(msg) ? null : base64Encode(md5(msg));
	}
	
	public static String md5AndBase64(String msg) throws Exception{
		return StringUtil.isEmpty(msg)? null : base64Encode(md5(base64Decode(msg)));
	}

	public static String MD5Encrypt(String msg) {
		try {
			return base64Encode(md5(base64Decode(msg)));
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	

	/**
	 * 使用sha1加密字符串
	 * @param content
	 * @return
	 */
	public static String sha1(String content) {
		String sha1 = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			// 将三个参数字符串拼接成一个字符串进行sha1加密
			byte[] digest = md.digest(content.getBytes());
			sha1 = StringUtil.byteToStr(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} 
		return sha1;
	}
	

	/**
	 * 对json进行md5签名
	 * 
	 * @param json
	 * @return
	 */
	public static JSONObject encryptJson(JSONObject json) {
		JSONObject md5 = new JSONObject();
		String signature = MD5(json.toString() + Constants.TOKEN);
		md5.put("sign", signature);
		md5.put("data", json);
		md5.put("flag", "web");
		return md5;
	}

	/**
	 * 验证json的md5签名
	 * 
	 * @param json
	 * @return
	 */
	public static boolean checkJson(JSONObject json) {
		JSONObject md5 = json.getJSONObject("data");
		String signature = MD5(md5.toString() + Constants.TOKEN);
		return json.get("sign").equals(signature);
	}

	/**
	 * 得到签名之后的json中数据，若无，则返回原始数据
	 * 
	 * @param json
	 * @return
	 */
	public static JSONObject getData(JSONObject json) {
		JSONObject data = json.getJSONObject("data");
		return data.toString().equals("null") ? json : data;
	}
}
