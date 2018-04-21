package com.chinadovey.parking.webapps.biz.assist;

public interface UrlBiz {
	
	static final String SINA_SHORTEN = "http://jump.sinaapp.com/api.php";
	
	public String shortUrl(String longUrl);
	
	public String generateShortUrl(String url);
}
