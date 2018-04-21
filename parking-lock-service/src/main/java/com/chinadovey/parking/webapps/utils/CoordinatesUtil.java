package com.chinadovey.parking.webapps.utils;

/**
 * 坐标计算工具类
 * @author Administrator
 *
 */
public class CoordinatesUtil {

	private final static double PI = Math.PI;

	private final static double RADIUS = 6378137.0000000;

	public static double MeterToLongitude(int meter) {
		return (meter / (2 * PI * RADIUS)) * 360;
	}

	public static double MeterToLatitude(double latitude, int meter) {
		return (meter / (2 * PI * RADIUS * Math.abs(Math.cos(latitude)))) * 360;
	}

	public static double LongitudeMeter(double longitude) {
		return (longitude / 360) * (2 * PI * RADIUS);
	}

	public static double LatitudeToMeter(double latitude, double latitudeDifference) {
		return (latitudeDifference / 360) * (2 * PI * RADIUS * Math.abs(Math.cos(latitude)));
	}

	private final static double DEF_PI = 3.14159265359; // PI
	private final static double DEF_PI180 = 0.01745329252; // PI/180.0
	private final static double DEF_R = 6370693.5; // radius of earth

	/**
	 * 根据圆心、半径算出经纬度范围
	 * 
	 * @param x
	 *            圆心经度
	 * @param y
	 *            圆心纬度
	 * @param r
	 *            半径（米）
	 * @return double[4] 南侧经度，北侧经度，西侧纬度，东侧纬度
	 */
	public static double[] getRange(double lon, double lat, int r) {
		double[] range = new double[4];
		// 角度转换为弧度
		double ns = lat * DEF_PI180;
		double sinNs = Math.sin(ns);
		double cosNs = Math.cos(ns);
		double cosTmp = Math.cos(r / DEF_R);
		// 经度的差值
		double lonDif = Math.acos((cosTmp - sinNs * sinNs) / (cosNs * cosNs)) / DEF_PI180;
		// 保存经度
		range[0] = lon - lonDif;
		range[1] = lon + lonDif;
		double m = 0 - 2 * cosTmp * sinNs;
		double n = cosTmp * cosTmp - cosNs * cosNs;
		double o1 = (0 - m - Math.sqrt(m * m - 4 * (n))) / 2;
		double o2 = (0 - m + Math.sqrt(m * m - 4 * (n))) / 2;
		// 纬度
		double lat1 = 180 / DEF_PI * Math.asin(o1);
		double lat2 = 180 / DEF_PI * Math.asin(o2);
		// 保存
		range[2] = lat1;
		range[3] = lat2;
		return range;
	}

	public static double getLongDistance(double lon1, double lat1, double lon2, double lat2) {
		double ew1, ns1, ew2, ns2;
		double distance;
		// 角度转换为弧度
		ew1 = lon1 * DEF_PI180;
		ns1 = lat1 * DEF_PI180;
		ew2 = lon2 * DEF_PI180;
		ns2 = lat2 * DEF_PI180;
		// 求大圆劣弧与球心所夹的角(弧度)
		distance = Math.sin(ns1) * Math.sin(ns2) + Math.cos(ns1) * Math.cos(ns2) * Math.cos(ew1 - ew2);
		// 调整到[-1..1]范围内，避免溢出
		if (distance > 1.0)
			distance = 1.0;
		else if (distance < -1.0)
			distance = -1.0;
		// 求大圆劣弧长度
		distance = DEF_R * Math.acos(distance);
		return distance;
	}

	/**
	 * 计算地球上任意两点(经纬度)距离
	 * 
	 * @param long1
	 *            第一点经度
	 * @param lat1
	 *            第一点纬度
	 * @param long2
	 *            第二点经度
	 * @param lat2
	 *            第二点纬度
	 * @return 返回距离 单位：米
	 */
	public static double getDistance(double long1, double lat1, double long2, double lat2) {
		double a, b, R;
		R = 6378137; // 地球半径
		lat1 = lat1 * Math.PI / 180.0;
		lat2 = lat2 * Math.PI / 180.0;
		a = lat1 - lat2;
		b = (long1 - long2) * Math.PI / 180.0;
		double d;
		double sa2, sb2;
		sa2 = Math.sin(a / 2.0);
		sb2 = Math.sin(b / 2.0);
		d = 2 * R * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1) * Math.cos(lat2) * sb2 * sb2));
		return d;
	}

}
