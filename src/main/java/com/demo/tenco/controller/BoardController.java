package com.demo.tenco.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.tenco.advice.WrongApproach;
import com.demo.tenco.model.dto.BoardDTO;
import com.demo.tenco.model.dto.User;
import com.demo.tenco.service.BoardService;
import com.demo.tenco.utils.Define;
import com.demo.tenco.utils.Script;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class BoardController {

	private final BoardService boardService;

	// board list
	@GetMapping({ "", "/", "/index", "/board/list" })
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
	
	//* 코드 추가 
	@GetMapping("/board/update-form/{boardId}")
	public String updateForm(@PathVariable int boardId, Model model, HttpSession session) throws WrongApproach {
		User principal = null; 
		// 로그인 여부 확인 
		if(session.getAttribute(Define.PRINCIPAL) == null) {
			throw new WrongApproach("잘못된 접근 방식입니다");
		}
		// 본인 쓴 글 확인
		principal = (User)session.getAttribute(Define.PRINCIPAL);
		BoardDTO boardDTO = boardService.selectById(boardId);
		if(principal.getUsername().equals(boardDTO.getUsername())) {
			model.addAttribute("boardData", boardDTO);
			model.addAttribute("userId", principal.getId());
		} else {
			throw new WrongApproach("본인이 작성한 글이 아닙니다");
		}
		
		// 데이터 내려주기 
		return "board/update_form";
	}
}
