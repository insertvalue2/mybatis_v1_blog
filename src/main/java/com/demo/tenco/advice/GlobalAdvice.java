package com.demo.tenco.advice;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalAdvice {

	@ExceptionHandler(value = Exception.class)
	public void exception(Exception e) {
		System.out.println("------------------");
		System.out.println(e.getClass().getName());
		System.out.println(e.getMessage());
		System.out.println("------------------");
	}
	
	// 1 단계	
	@ExceptionHandler(value = DuplicateKeyException.class)
	public ResponseEntity<?> duplicateKeyException(DuplicateKeyException e) {
		System.out.println(e.getMessage());
		return new ResponseEntity<>("동일한 이름이 존재 합니다", HttpStatus.BAD_REQUEST);
	}

}
