package com.cos.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseData {

	private int statusCode;	//flag������ �־ �������и� ����
	private String status;	//statusCode�� ���� "ok"�� "fail"�� ���� �����ϴ°��� �޸���
	private String statusMessage;	//"�Ľ̿���", "������X"���� ǥ��
}


