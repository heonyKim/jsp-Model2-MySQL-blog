package com.cos.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

//���Ϸ� ������ ���� ���̵�, ��й�ȣ �ʿ�
public class Gmail extends Authenticator {
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication("ez3complex@gmail.com", Password.GOOGLEPASSWORD);
	}
}
