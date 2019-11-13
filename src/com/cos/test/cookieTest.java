package com.cos.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Test;

/**
 * Servlet implementation class cookieTest
 */

@WebServlet("/cookieTest")
public class cookieTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public cookieTest() {
		super();

	}
	@Test
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = "jay";
		Cookie c = new Cookie("userId", id);
		c.setComment("접속자 아이디");
		c.setPath("/");
		c.setMaxAge(60 * 60 * 24);
		
		response.addCookie(c);
		
		Cookie[] cs = request.getCookies();
		for (Cookie cookie : cs) {
			System.out.println(c.getPath());
			System.out.println("쿠키명 : " + c.getName());
			System.out.println("쿠키값 : " + c.getValue());
			System.out.println("//////////////////////////");
		}

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
