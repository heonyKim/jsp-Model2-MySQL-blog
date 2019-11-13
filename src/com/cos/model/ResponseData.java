package com.cos.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseData {

	private int statusCode;	//flag역할을 주어서 성공실패를 구분
	private String status;	//statusCode에 따라 "ok"나 "fail"에 따라 실행하는것을 달리함
	private String statusMessage;	//"파싱에러", "페이지X"등을 표현
}


