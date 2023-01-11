package com.demo.tenco.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import com.demo.tenco.model.dto.BoardDTO;

@Mapper
public interface BoardDAO {
	public int insert(Map<String, Object> map);
	public List<BoardDTO> selectAll(); 
	public BoardDTO findById(int boardId);
	public void deleteByBoardId(int boardId);
	public int modifyBoard(BoardDTO board);  // 코드 추가 
}
