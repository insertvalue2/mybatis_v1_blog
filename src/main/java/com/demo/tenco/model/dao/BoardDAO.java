package com.demo.tenco.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.demo.tenco.model.dto.BoardDTO;
import com.demo.tenco.model.dto.PagingDTO;

@Mapper
public interface BoardDAO {
	public int insert(Map<String, Object> map);
	public List<BoardDTO> selectAll(@Param("limit") int limit, @Param("offset") int offset); 
	public BoardDTO findById(int boardId);
	public void deleteByBoardId(int boardId);
	public int modifyBoard(BoardDTO board);  // 코드 추가 
	// 코드 추가 
	public PagingDTO pagable(@Param("page") int page, @Param("rowSize") int rowSize);
}
