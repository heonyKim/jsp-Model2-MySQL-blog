package com.cos.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

//메일로 접근할 구글 아이디, 비밀번호 필요
public class Gmail extends Authenticator {
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication("ez3complex@gmail.com", Password.GOOGLEPASSWORD);
	}
}
