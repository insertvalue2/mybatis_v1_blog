package com.demo.tenco.advice;

import java.util.ArrayList;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.tenco.model.SiteError;
import com.demo.tenco.utils.Script;

@ControllerAdvice
public class GlobalAdvice {

	@ExceptionHandler(value = Exception.class)
	public void exception(Exception e) {
		System.out.println("------------------");
		System.out.println(e.getClass().getName());
		System.out.println(e.getMessage());
		System.out.println("------------------");
	}

	// 2 단계 DuplicateKeyException 예외 잡기
	@ExceptionHandler(value = DuplicateKeyException.class)
	public String duplicateKeyException(DuplicateKeyException e, Model model) {
		System.out.println("DuplicateKeyException ~~~~~");
		model.addAttribute("error", "중복된 이름이 있어요");
		return "user/signup_form";
	}

	// 3 단계
	/**
	 * 직접 예외 클래스를 만들고 사용해 보기
	 */
	@ExceptionHandler(value = UniqueUsernameException.class)
	public String uniqueUsernameException(UniqueUsernameException e, Model model) {
		
		ArrayList<SiteError> errors = new ArrayList<>();
		SiteError siteError = new SiteError();
		siteError.setMessage(e.getDefaultMessage());
		siteError.setField(e.getFieldName());
		siteError.setInvalidValue(e.getInvaildValue()); // 예외 클래스 수정
		errors.add(siteError);
		
		model.addAttribute("isError", true);
		model.addAttribute("error", siteError);
		
		return "user/signup_form";
	}
	
	
	// 코드 추가 
	@ExceptionHandler(value = WrongApproach.class)
	@ResponseBody // 데이터로 리턴 처리 
	public String wrongApproachException(WrongApproach e) {
		// 다른 방식으로 예외 처리를 해 보자. 
		return Script.back(e.getMessage());
	}

}
