package com.demo.tenco.model.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	private int id; 
	private String username; 
	private String password; 
	private String email; 
	private String profile; 
	private String role;
	private Timestamp createDate; 
}
