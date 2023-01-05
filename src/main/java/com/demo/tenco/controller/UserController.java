package com.demo.tenco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
	
	// 회원 가입 화면
	// http://localhost:8080/user/sign-up
	@GetMapping("/sign-up")
	public String signUpForm() {
		return "/user/signup_form";
	}
	
	// 로그인 화면
	// http://localhost:8080/user/sign-in
	@GetMapping("/sign-in")
	public String signinForm() {
		return "/user/signin_form";
	}
}
