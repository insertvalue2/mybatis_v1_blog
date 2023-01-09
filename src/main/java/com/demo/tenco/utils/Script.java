package com.demo.tenco.utils;

public class Script {
	
	public static String back(String msg) {
		// 멀티 스레드 환경에서도 안전하게 동작
		StringBuffer sb = new StringBuffer();
		sb.append("<script>");
		sb.append("<alert("+msg+")>");
		sb.append("history.back();");
		sb.append("</script>");
		return sb.toString();
	}
	
	public static String href(String url) {
		StringBuffer sb = new StringBuffer();
		sb.append("<script>");
		sb.append("location.href='"+url+"';");
		sb.append("</script>");
		return sb.toString();
	}
	
	public static String href(String url, String msg) {
		StringBuffer sb = new StringBuffer();
		sb.append("<script>");
		sb.append("alert('"+msg+"');");
		sb.append("location.href='"+url+"';");
		sb.append("</script>");
		return sb.toString();
	}
	
}
