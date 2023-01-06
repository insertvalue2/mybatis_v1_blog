package com.demo.tenco.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SiteError {
	private String field; 
	private String message; 
	private String invalidValue; 
}
