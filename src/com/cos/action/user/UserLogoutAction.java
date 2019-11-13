package com.cos.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.action.Action;

public class UserLogoutAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//���Ǹ���
		HttpSession session = request.getSession();
		session.invalidate();	//���� ��ȿȭ
		
		//����Ʈ�̵�
		response.sendRedirect("/blog/index.jsp");
		
	}

}
