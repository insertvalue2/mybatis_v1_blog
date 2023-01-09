package com.demo.tenco.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.tenco.model.dto.BoardDTO;
import com.demo.tenco.model.dto.ResponseDto;
import com.demo.tenco.service.BoardService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/board")
public class BoardApiController {
	
	private final BoardService boardService;
	
	// localhost:8080/api/board/save
	@PostMapping("/save")
	public ResponseEntity<?> saveBoardProc(@RequestBody BoardDTO boardDTO, HttpServletRequest request) {
		
		System.out.println("boardDTO " + boardDTO);
		// 서비스 로직 요청 ... 
		// 응답 처리 테스트 
		int result = boardService.saveBoard(boardDTO);
		System.out.println("result  확인 : " + result);
		ResponseDto<Integer> responseDto 
		= new ResponseDto<Integer>(1, "글 등록이 완료 되었습니다", result);
		return  ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}
	
	
	
}