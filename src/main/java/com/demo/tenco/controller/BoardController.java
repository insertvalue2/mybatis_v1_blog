package com.demo.tenco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	// board list 
	@GetMapping({"", "/list"})
	public String list() {
		return "board/main";
	}
	
	// board write form 
	@GetMapping("/write")
	public String write() {
		return "board/write_form";
	}
}
