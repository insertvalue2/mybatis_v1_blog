package com.demo.tenco.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.tenco.model.dto.User;
import com.demo.tenco.service.UserService;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/req-test")
	public String reqTest() {
		return "TEST OK";
	} 
	
	@GetMapping("/jsp-test")
	public String jspTest( ) {
		
		return "index";
	}
	 
	@GetMapping("/user-list")
	@ResponseBody // ViewResolve 가 아닌 데이터 리턴 처리
	public String userListTest( ) {
		List<User> list = userService.findByUserAll();
		return list.toString();
	}
	
	@PostMapping("/user-signup")
	@ResponseBody
	public int insertUserTest(@RequestBody User user) {
		userService.saveUser(user);
		return 1;
	}
	
	@DeleteMapping("/user-delete/{userId}")
	@ResponseBody
	public int deleteUserTest(@PathVariable int userId) {
		System.out.println("userId ");
		int resultRow = userService.deleteUser(userId); 
		return resultRow; 
	}
	
	@PutMapping("/user-update/{userId}")
	@ResponseBody
	public int updateUserTest(@PathVariable int userId, @RequestBody User user) {
		user.setId(userId);
		int resultRow = userService.updateUser(user); 
		return resultRow; 
	}

}