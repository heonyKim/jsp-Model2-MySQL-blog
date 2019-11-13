package com.cos.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reply {
	private ResponseData responseData = new ResponseData();
	private int id;
	private int commentId;//FK
	private int userId;	//FK
	private String content;
	private Timestamp createDate;
	private User user = new User();
}	
	
	
