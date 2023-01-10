package com.demo.tenco.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.demo.tenco.model.dto.BoardDTO;

@Mapper
public interface BoardDAO {
	public int insert(BoardDTO boardDTO);
	public List<BoardDTO> selectAll(); 
	public BoardDTO findById(int boardId);
	public void deleteByBoardId(int boardId);
}
