package com.demo.tenco.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.tenco.advice.WrongApproach;
import com.demo.tenco.model.dto.BoardDTO;
import com.demo.tenco.model.dto.PagingDTO;
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
	@GetMapping({ "", "/", "/board/list" })
	public String list( Model model, String page) {
		// Tip - int, Integer : 오류 발생 
		// - String 일 때 오류 발생하지 않음 
		int limit = 3; 
		int offset = 0;
		int pageNumber = 0; 
		try {
			pageNumber = Integer.parseInt(page);
		} catch (Exception e) {
			System.out.println("예외 발생");
		}
		
		
		// 코드 수정 
		//List<BoardDTO> list = boardService.boardList(limit, offset);
		PagingDTO pagingDTO = boardService.boardList(limit, offset, pageNumber);
		model.addAttribute("boardList", pagingDTO);
		return "board/main";
	}

	// board write form
	@GetMapping("/auth/board/write")
	public String write() {
		return "board/write_form";
	}
	
	//	코드 수정 
	@GetMapping("/auth/board/detail/{id}")
	public String detail(@PathVariable(name = "id") int boardId, Model model, HttpSession session) {
		
		User principal = (User)session.getAttribute("principal");
		BoardDTO boardData = boardService.selectById(boardId);
		boolean isWriter = false; 
		if(principal != null) {
			if(principal.getUsername().equals(boardData.getUsername())) {
				isWriter  = true;
			}
		}
		model.addAttribute("isWriter", isWriter);
		model.addAttribute("boardData", boardData);
		return "board/detail";
	}
	
	@GetMapping("/auth/board/update-form/{boardId}")
	public String updateForm(@PathVariable String boardId, Model model, HttpSession session) {
		User principal = null; 
		// 로그인 여부 확인 
		if(session.getAttribute(Define.PRINCIPAL) == null) {
			throw new WrongApproach("잘못된 접근 방식입니다");
		}
		// 본인 쓴 글 확인
		principal = (User)session.getAttribute(Define.PRINCIPAL);
		BoardDTO boardDTO = boardService.selectById(Integer.parseInt(boardId));
		if(principal.getUsername().equals(boardDTO.getUsername())) {
			model.addAttribute("boardData", boardDTO);
			model.addAttribute("userId", principal.getId());
		} else {
			throw new WrongApproach("본인이 작성한 글이 아닙니다");
		}
		return "board/update_form";
	}
}
