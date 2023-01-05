package com.demo.tenco.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class TimeUtil {
	private final String SIME_TYPE = "yyyy-MM-dd hh:mm:ss";
	private Timestamp currentTime;
	
	public TimeUtil(Long systemCurrentTimeMillis) {
		this.currentTime = new Timestamp(systemCurrentTimeMillis);
	}
	
	public String formatTimeSimple() {
		SimpleDateFormat formater = new SimpleDateFormat(SIME_TYPE);
		return formater.format(formater.format(currentTime)); 
	}
}
