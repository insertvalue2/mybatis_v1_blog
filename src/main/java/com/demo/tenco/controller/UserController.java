package com.demo.tenco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.tenco.model.dto.User;
import com.demo.tenco.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {
	
	// 의존 주입 (DI 처리) 
	private final UserService userService;
	
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
	
	/**
	 * 회원 가입 처리 
	 * MIME TYPE - form(application/x-www-form-urlencoded)
	 * 파싱 : Object mapper 사용 
	 */
	@PostMapping("/signup-proc")
	public String signupProc(User user) {
		int result = userService.saveUser(user);
		System.out.println("result : " + result);
		return "redirect:/";
	}
}



