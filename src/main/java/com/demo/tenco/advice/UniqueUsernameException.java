package com.demo.tenco.advice;

import org.springframework.dao.DuplicateKeyException;

import lombok.Getter;

@Getter
public class UniqueUsernameException extends DuplicateKeyException {

	private static final long serialVersionUID = 1L;
	private String fieldName; 
	private String defaultMessage; 
	private String invaildValue; 
	
	public UniqueUsernameException(String msg, String fieldName, String invaildValue) {
		super(msg);
		this.defaultMessage = msg;
		this.fieldName = fieldName;
		this.invaildValue = invaildValue; 
	}

}
