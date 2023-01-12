package com.demo.tenco.intercepter;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.demo.tenco.model.SiteError;
import com.demo.tenco.model.dto.ResponseDto;
import com.demo.tenco.model.dto.User;
import com.demo.tenco.utils.Define;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LoginIntercepter implements HandlerInterceptor {
		
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("================");
		System.out.println("** LoginIntercepter 동작 ** ");
		System.out.println(request.getRequestURI());
		System.out.println("================");
		
		String uri = request.getRequestURI();
		HttpSession session = request.getSession();
		User principal = (User) session.getAttribute(Define.PRINCIPAL);
		
		if(principal == null) {
			if(uri.contains("api")) {
				// data 를 리턴 
				System.out.println("===========");
				System.out.println("API 가 주소에 있음");
				response.setContentType("application/json; charset=utf-8");
				PrintWriter out = response.getWriter();
				ResponseDto<String> responseDto = new ResponseDto<String>(-1, "인증이 필요 합니다", "");
				ObjectMapper om = new ObjectMapper();
				String json = om.writeValueAsString(responseDto);
				out.println(json);
				out.flush();  // 반드시 사용하자! 
			} else {
				// 페이지를 리턴 
				System.out.println("===========");
				System.out.println("API 가 주소에 없음");
				response.sendRedirect("/user/sign-in");
			}
		}
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	
	}
	
	
	
}
