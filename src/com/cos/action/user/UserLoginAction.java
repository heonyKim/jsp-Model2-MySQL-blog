package com.cos.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.action.Action;
import com.cos.dao.UserDao;
import com.cos.model.User;
import com.cos.util.SHA256;
import com.cos.util.Script;

public class UserLoginAction implements Action {

	private static final String TAG = "UserLoginAction : ";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserDao dao = new UserDao();
		// ��ȿ�� �˻縦 ���߿� �ؾ���
		String username = request.getParameter("username");
		String email = dao.findByEmail(username);
		
		String rawPassword = request.getParameter("password");
		String rememberMe= request.getParameter("rememberMe");
		String password = SHA256.getEncrypt(rawPassword, email);
		
		System.out.println(TAG + "username : " + username);
		System.out.println(TAG + "password : " + password);
		System.out.println(TAG + "rememberMe : " + rememberMe);

		
		int result = dao.findByUsernameAndPassword(username, password);

		if (result == 1) {
			
			if(rememberMe !=null) {
				System.out.println(TAG+"��Ű����");
				Cookie c = new Cookie("username", username);
				System.out.println(c.getValue());
				c.setMaxAge(6000);	//100��
				response.addCookie(c);
			}else {
				System.out.println(TAG+"��Ű����");
				Cookie c = new Cookie("username", null);
				c.setMaxAge(0);
				response.addCookie(c);
				
			}
			
			//���ǵ��
			HttpSession session = request.getSession();
			
			//User ��ü ��������
			User user = dao.findByUsername(username);
			session.setAttribute("user", user);
			
			response.sendRedirect("/blog/index.jsp");
			
		} else {

			Script.back(response);
			
		}
	}

}
