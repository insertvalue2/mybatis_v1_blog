package com.demo.tenco.model.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class BoardDTO {
	
	private int id; 
	private String title; 
	private String content; 
	private Timestamp createDate; 
	
}
