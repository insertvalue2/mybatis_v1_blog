package com.demo.tenco.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.tenco.model.dto.SigninDTO;
import com.demo.tenco.model.dto.User;
import com.demo.tenco.service.Script;
import com.demo.tenco.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {
	
	// 의존 주입 (DI 처리) 
	private final UserService userService;
	// 의존 주입 (DI 처리)
	private final HttpSession session;
	
	// 회원 가입 화면
	// http://localhost:8080/user/sign-up
	@GetMapping("/sign-up")
	public String signUpForm() {
		return "/user/signup_form";
	}
	
	// 로그인 화면
	// http://localhost:8080/user/sign-in
	@GetMapping("/sign-in")
	public String signinForm(Model model) {
		model.addAttribute("isError", false);
		return "/user/signin_form";
	}
	
	/**
	 * 회원 가입 처리 
	 * MIME TYPE - form(application/x-www-form-urlencoded)
	 * 파싱 : Object mapper 사용 
	 */
	@PostMapping("/signup-proc")
	public String signupProc(User user) {
		userService.saveUser(user);
		return "redirect:/";
	}
	
	
	/**
	 * 파싱 전력 object mapper 
	 * MIME TYPE - form(application/x-www-form-urlencoded)
	 * @return
	 */
	@PostMapping("/signin-proc")
	public String signinProc(SigninDTO dto, Model model) {
		User principal = userService.searchUser(dto.getUsername());
		System.out.println("user 확인 : " + principal);
		if(principal == null) {
			return "user/signin_form";
		} 
		session.setAttribute("principal", principal);
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
	}
	
}



