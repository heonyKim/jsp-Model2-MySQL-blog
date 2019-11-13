package com.cos.action.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.action.Action;
import com.cos.dao.UserDao;
import com.cos.model.User;
import com.cos.util.Script;

public class UserCheckAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email  = request.getParameter("email");
		
		UserDao dao = new UserDao();
		int result = dao.emailcheck(email);
		if(result==1) {

			response.sendRedirect("/blog/index.jsp");
		}else {
			PrintWriter out	= response.getWriter();

			out.println("<script>");
			out.println("alert('중복된 이메일이거나 잘못된 메일입니다.');");
			out.println("history.back()");
			out.println("</script>");
			out.flush(); //버퍼를 비워줌
			
			
		}
	}
}
