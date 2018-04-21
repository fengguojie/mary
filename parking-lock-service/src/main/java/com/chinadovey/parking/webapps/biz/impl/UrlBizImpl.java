package com.chinadovey.parking.webapps.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinadovey.parking.webapps.biz.assist.UrlBiz;

import net.sf.json.JSONObject;

@SuppressWarnings("deprecation")
public class UrlBizImpl implements UrlBiz {
	
	private static Logger logger = LoggerFactory.getLogger(UrlBizImpl.class);    
	
	@Override
	public String shortUrl(String longUrl) {
		JSONObject responObject = null;
		/*try {
			responObject = HttpUtil.httpGet(SINA_SHORTEN + "?url_long="+ URLEncoder.encode(longUrl, "UTF-8"));
			if (1 == responObject.getInt("result")) {
				return responObject.getString("url_short");
			} else {
				System.err.println(responObject);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}*/
		return longUrl;
	}


	/**
	 * 生成端连接信息
	 * 
	 * @author: Jerri
	 * @date: 2014年3月22日下午5:31:15
	 */
	@Override
	public String generateShortUrl(String url) {
		@SuppressWarnings({ "resource", "deprecation" })
		DefaultHttpClient httpclient = new DefaultHttpClient();
		try {
			HttpPost httpost = new HttpPost("http://dwz.cn/create.php");
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("url", url)); // 用户名称
			httpost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
			HttpResponse response = httpclient.execute(httpost);
			String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
			System.out.println(jsonStr);
			JSONObject object = JSONObject.fromObject(jsonStr);
			System.out.println(object.getString("tinyurl"));
			return object.getString("tinyurl");
		} catch (Exception e) {
			e.printStackTrace();
			return url;
		}

	}
}
