package com.cos.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private ResponseData responseData = new ResponseData();
	private int id;
	private String username;
	private String password;
	private String email;
	private Timestamp createDate;
	private String address;
	private String userProfile;
	private int emailFlag=0;	//1이면 인증 , 0이면 미인증

}
 