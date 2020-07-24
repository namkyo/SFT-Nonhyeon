package com.sft.nonhyeon.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.java.Log;

@Log
public class Common {
	public final static String COMPANY_NAME = "SFT 논현지사";
	
	public static void toPrintIp(HttpServletRequest req) {
		String ip = req.getHeader("WL-Proxy-Client-IP");
		ip = req.getRemoteAddr();
		String browser = req.getHeader("User-Agent");
		log.info(ip + " 님이 " + browser + " 을 통해 접속");

		log.info("ip = " + ip + "   시간 : " + getthisTime());
		log.info("browser = " + browser);
	}

	public static String getthisTime() {
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = dayTime.format(new Date());
		return str;
	}
	
	public static String getKrThisMonthDay() {
		SimpleDateFormat dayTime = new SimpleDateFormat("MM월 dd일(E)");
		String str = dayTime.format(new Date());
		return str;
	}
	public static String getyoil() {
		SimpleDateFormat dayTime = new SimpleDateFormat("E");
		String str = dayTime.format(new Date());
		return str;
	}
	

	public static String getthisDay() {
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyyMMdd");
		String str = dayTime.format(new Date());
		return str;
	}

	public static String getthisTimeStr() {
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyyMMddHHmmss");
		String str = dayTime.format(new Date());
		return str;
	}

}
