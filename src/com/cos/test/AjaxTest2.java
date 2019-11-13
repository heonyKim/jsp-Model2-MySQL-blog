package com.cos.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.model.Reply;
import com.google.gson.Gson;


@WebServlet("/test2")
public class AjaxTest2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AjaxTest2() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");	//MIME Ÿ��
		
		try {
			System.out.println("����");
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		PrintWriter out = response.getWriter();
		out.println(100);
		out.flush();
	}

}
