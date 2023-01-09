package com.demo.tenco.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//같은 변수 이름으로 데이터 타입을 다르게 사용해야 할 때 
//제네릭 프로그램을 생각 하자. 
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto<T> {
	private int code; // 1 성공, -1 실패 
	private String message; // 성공 결과, 실패 사유 
	private T body; // 데이터  
}



