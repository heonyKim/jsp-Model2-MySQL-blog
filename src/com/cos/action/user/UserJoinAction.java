package com.cos.action.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.action.Action;
import com.cos.dao.UserDao;
import com.cos.model.User;
import com.cos.util.SHA256;
import com.cos.util.Script;

public class UserJoinAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// form태그의 name값들을 받아서 DB에 insert하고 페이지 이동
		
		// 나중에 null값 처리하기, 유효성검사(나중에)
		
		String username = request.getParameter("username");
		String rawPassword  = request.getParameter("password");
		String email  = request.getParameter("email");
		String address = request.getParameter("address");
		String password = SHA256.getEncrypt(rawPassword, email);
		
		
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);	//Encryption(암호화) 해야댐다.
		user.setEmail(email);
		user.setAddress(address);
		
		//Dao접근해서 insert
		UserDao dao = new UserDao();
		int result = dao.save(user);
		
		if(result==1) {
			
			response.sendRedirect("/blog/email/sendEmailAction.jsp?email="+email);
			
		}else {
			
			Script.back(response);
		}
		
		
		
	}

}
