package com.cos.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.action.Action;
import com.cos.action.user.UserFactory;

/*
 *  http://localhost:8000/blog/user?cmd=list
 */

@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UserController() { super();}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. ������ ��� ���ڸ� UTF-8�� ���ڵ�
		request.setCharacterEncoding("UTF-8");
		//2. command ó��
		String cmd = request.getParameter("cmd");
		//3. command ����
		if(cmd == null || cmd.equals("")) {
			cmd = "list";
		}
		//4. Factory ����
		Action action = UserFactory.getAction(cmd);
		//5. execute ����
		if(action != null) {
			action.execute(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
