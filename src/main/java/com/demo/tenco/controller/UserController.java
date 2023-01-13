package com.demo.tenco.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.tenco.model.dto.SigninDTO;
import com.demo.tenco.model.dto.User;
import com.demo.tenco.service.UserService;
import com.demo.tenco.utils.Script;

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

	// 로그인 화면 요청이 들어 왔을 때 쿠키를 조회를 하고 데이터를 내려 주어야 한다.
	@GetMapping("/sign-in")
	public String signinForm(Model model, HttpServletRequest request) {

		Cookie[] cookies = request.getCookies();
		boolean isRemember = false;
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("username")) {
				model.addAttribute(cookie.getName(), cookie.getValue());
				isRemember = true;
			}
			System.out.println("============");
			System.out.println(cookie.getName());
			System.out.println(cookie.getValue());
			System.out.println("============");
		}
		model.addAttribute("isError", false);
		model.addAttribute("isRemember", isRemember);
		return "/user/signin_form";
	}

	/**
	 * 회원 가입 처리 MIME TYPE - form(application/x-www-form-urlencoded) 파싱 : Object
	 * mapper 사용
	 */
	@PostMapping("/signup-proc")
	public String signupProc(User user) {
		userService.saveUser(user);
		return "redirect:/";
	}

	/**
	 * 파싱 전력 object mapper MIME TYPE - form(application/x-www-form-urlencoded)
	 * 
	 * @return
	 */
	// 하나더 받아도 될까?? ok
	@PostMapping("/signin-proc")
	public String signinProc(SigninDTO signinDto, Model model, HttpServletResponse response) {
		// check box (String) -> on, null
		// check box (boolean) -> true, false
		// System.out.println(signinDto);
		if (signinDto.isRemember()) {
			Cookie cookie = new Cookie("username", signinDto.getUsername());
			cookie.setMaxAge(60 * 60 * 24);
			response.addCookie(cookie);
			// response.setHeader("Set-Cookie", "username="+loginDto.getUsername()+";
			// HttpOnly");
		} else {
			Cookie cookie = new Cookie("username", null);
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}

		User principal = userService.searchUser(signinDto.getUsername());
		if (principal == null) {
			model.addAttribute("isNotSignin", true);
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
