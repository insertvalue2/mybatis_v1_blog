package com.demo.tenco.model;

import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString	
public class ErrorResponse {
	private String  statusCode; 
	private String  requestUri; 
	private int code; 
	private String message; 
	private int resultCode; 
	
	private List<SiteError> errorList; 
}
