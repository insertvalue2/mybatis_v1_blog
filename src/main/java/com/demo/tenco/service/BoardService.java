package com.demo.tenco.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.tenco.model.dao.BoardDAO;
import com.demo.tenco.model.dto.BoardDTO;

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
	
	@Transactional
	public List<BoardDTO> selectBoardList() {
		return boardDAO.selectAll();
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
	
}
