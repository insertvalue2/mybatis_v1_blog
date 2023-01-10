package com.demo.tenco.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.tenco.model.dto.BoardDTO;
import com.demo.tenco.model.dto.User;
import com.demo.tenco.service.BoardService;
import com.demo.tenco.utils.Script;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class BoardController {

	private final BoardService boardService;

	// board list
	@GetMapping({ "", "/", "/index", "/board-list" })
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
	public String detail(@PathVariable(name = "id") int boardId, Model model, HttpSession session) {
		
		User principal = (User)session.getAttribute("principal");
		BoardDTO boardData = boardService.selectById(boardId);
		boolean isWriter = false; 
		if(principal != null) {
			if(boardData.getUsername().equals(boardData.getUsername())) {
				isWriter  = true;
			}
		}
		model.addAttribute("isWriter", isWriter);
		model.addAttribute("boardData", boardData);
		return "board/detail";
	}
}
