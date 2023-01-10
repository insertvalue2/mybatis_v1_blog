package com.demo.tenco.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.tenco.model.dto.BoardDTO;
import com.demo.tenco.service.BoardService;
import com.demo.tenco.utils.Script;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class BoardController {
	
	private final BoardService boardService;
	
	// board list 
	@GetMapping({"", "/", "/index", "/board-list"})
	public String list(Model model) {
		List<BoardDTO> list = boardService.selectBoardList();
		System.out.println(list.get(0).toString());
		model.addAttribute("boardList", list);
		return "board/main";
	}
	
	// board write form 
	@GetMapping("/board/write")
	public String write() {
		return "board/write_form";
	}
	
	@GetMapping("/board/detail/{id}")
	public String detail(@PathVariable(name = "id") int  boardId, Model model) {
		BoardDTO boardData =  boardService.selectById(boardId);
		model.addAttribute("boardData", boardData);
		return "board/detail";
	}
}
