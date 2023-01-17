package com.demo.tenco.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.tenco.model.dao.BoardDAO;
import com.demo.tenco.model.dto.BoardDTO;
import com.demo.tenco.model.dto.PagingDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	
	private final BoardDAO boardDAO;
	
	@Transactional
	public int saveBoard(BoardDTO boardDTO,  int userId) {
		// 코드 수정
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", boardDTO.getTitle());
		map.put("content", boardDTO.getContent());
		map.put("userId", userId);
		
		return boardDAO.insert(map);
	}
	
	// 1 단계 
	@Transactional
	public List<BoardDTO> selectBoardList(int limit, int offset) {
		return boardDAO.selectAll(limit, offset);
	}
	
	// 2 단계	
	/**
	 * 
	 * @param limit - 갯수 제한 
	 * @param offset - 시작 번호 
	 * @param pageNumber - 0번 부터 시작해야한다. 
	 * @return PagingDTO
	 */
	@Transactional
	public PagingDTO boardList(int limit, int offset, int pageNumber) {
		// 필요 데이터 
		// 현재 페이지 번호 
		// 게시글 시작 번호
		
		// page 쿼리 들고 오기 
		PagingDTO pagingDTO = boardDAO.pagable(pageNumber, PagingDTO.rowSize);
		System.out.println("pagingDTO : " + pagingDTO);
		
		
		// 게시글 리스트 들고 오기
		// 전체 블로그 리스트 중
		// limit -> PagingDTO.rowSize 대체 가능
		// List<BoardDTO> list = boardDAO.selectAll(limit, offset);
		// 게시글 시작 번호 계산 하기
		// 1페이지 1번 부터 시작 1 + (rowSize * page )
		int startNumber = (PagingDTO.rowSize * pageNumber );
		System.out.println("startNumber : " + startNumber);
		// 2페이지 시작 번호   1 + (rowSize * page)
		List<BoardDTO> list = boardDAO.selectAll(limit, startNumber);
		System.out.println("list size : " + list.size());
		System.out.println("list : " + list);
		
		return null; 
	}
	
	
	@Transactional
	public BoardDTO selectById(int boardId) {
		return  boardDAO.findById(boardId);
	}
	
	@Transactional
	public void deleteBoard(int boardId) {
		boardDAO.deleteByBoardId(boardId);
	}
	
	//  코드 추가 	
	@Transactional
	public int modifyBoard(BoardDTO boardDTO) {
		return boardDAO.modifyBoard(boardDTO);
	}
	

	public PagingDTO getPagable(int page, int rowSize) {
		PagingDTO pageDto = boardDAO.pagable(page, rowSize);
		return pageDto;
	}
	
	
}
