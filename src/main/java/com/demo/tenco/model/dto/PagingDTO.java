package com.demo.tenco.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PagingDTO {
	public static int rowSize = 3; 
	private Integer totalCount; 
	private Integer currentPage; 
	private Integer totalPage; 
	private boolean isFirst; // boolean is 로 시작 
	private boolean isLast; 
	private List<BoardDTO> list;
}
