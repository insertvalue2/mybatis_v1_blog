package com.demo.tenco.model.dao;

import org.apache.ibatis.annotations.Mapper;
import com.demo.tenco.model.dto.BoardDTO;

@Mapper
public interface BoardDAO {
	public int insert(BoardDTO boardDTO);
}
