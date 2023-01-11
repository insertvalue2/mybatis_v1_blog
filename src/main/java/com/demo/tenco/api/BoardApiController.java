package com.demo.tenco.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.tenco.model.dto.BoardDTO;
import com.demo.tenco.model.dto.ResponseDto;
import com.demo.tenco.model.dto.User;
import com.demo.tenco.service.BoardService;
import com.demo.tenco.utils.Define;
import com.demo.tenco.utils.Script;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/board")
public class BoardApiController {
	
	private final BoardService boardService;
	
	// localhost:8080/api/board/save
	@PostMapping("/save")
	public ResponseEntity<?> saveBoardProc(@RequestBody BoardDTO boardDTO, HttpServletRequest request) {
		// 코드 수정 - Map 으로 넣는 방식 배워보기 
		HttpSession session = request.getSession();
		User principal = null; 
		if(session.getAttribute(Define.PRINCIPAL) != null) {
			principal = (User)session.getAttribute(Define.PRINCIPAL);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body("잘못된 접근 입니다");
		}
		 
		int result = boardService.saveBoard(boardDTO, principal.getId());
		ResponseDto<Integer> responseDto 
		= new ResponseDto<Integer>(1, "글 등록이 완료 되었습니다", result);
		return  ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}
	
	// 삭제 처리 
	@GetMapping("/delete/{boardId}")
	public String deleteBoard(@PathVariable int boardId, HttpSession session) {
		// 현재 글 작성자 id - 1 번으로 무조건 저장 됨
		if(session.getAttribute("principal") == null) {
			return Script.back("잘못된 접근입니다");
		}
		User principal  = (User) session.getAttribute("principal");
		BoardDTO boardDTO = boardService.selectById(boardId);
		if(principal.getUsername().equals(boardDTO.getUsername())) {
			boardService.deleteBoard(boardId);
		} else {
			return Script.back("권한이 없습니다");
		}
		return Script.back("게시글이 정상적으로 삭제 되었습니다"); 
	}
	
	
	/**
	 * MIME TYPE : application/json 
	 * @param boardId 
	 * @param id : 사용자 id 
	 * @param boardDTO : 수정할 데이터 
	 * @param request : 세션 확인 
	 * @return 1 성공, -1 실패 
	 */
	@PutMapping("/update/{boardId}/userid/{id}")
	public ResponseEntity<?> updateBoardProc(@PathVariable int boardId, @PathVariable int id,
			@RequestBody BoardDTO boardDTO,	HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		User principal = null; 
		if(session.getAttribute(Define.PRINCIPAL) != null) {
			principal = (User)session.getAttribute(Define.PRINCIPAL);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body("잘못된 접근 입니다");
		}
		int result = -1; 
		if(principal.getId() == id) {
			result = boardService.modifyBoard(boardDTO); 
		}
		ResponseDto<Integer> responseDto = 
				new ResponseDto<Integer>(1, "글이 정상적으로 수정 되었습니다", result);
		
		return ResponseEntity.status(HttpStatus.OK).body(responseDto); 
	}
}
