package com.demo.tenco.service;

import java.util.List;

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
	public int saveBoard(BoardDTO boardDTO) {
		// userId - fk 일단 무시 
		// 시간 - 일단 무시 
		return boardDAO.insert(boardDTO);
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
	
}
