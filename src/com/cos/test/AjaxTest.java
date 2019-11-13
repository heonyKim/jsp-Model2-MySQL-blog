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


@WebServlet("/test")
public class AjaxTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AjaxTest() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");	//MIME Ÿ��
		
		//��û������ ó�� 
		BufferedReader in = request.getReader();	//BR	�ٱ�
		
		String replyJsonString = in.readLine();	//replyJsonString
		System.out.println("��û ������ : " + replyJsonString);
		
		
		Gson gson = new Gson();
		Reply reply = gson.fromJson(replyJsonString, Reply.class);
		
		System.out.println(reply.getId());
		System.out.println(reply.getUserId());
		System.out.println(reply.getContent());
		System.out.println(reply.getCreateDate());
		
		//���� ������ ó��
		String jsonData = "{\"name\":\"�����\", \"sal\":100}";
		

		PrintWriter out =response.getWriter();		//PW
		out.println(jsonData);
		out.flush();
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
