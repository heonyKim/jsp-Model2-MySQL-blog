package com.cos.action.comment;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.action.Action;
import com.cos.dao.CommentDao;
import com.cos.model.Comment;
import com.cos.model.User;
import com.cos.util.Script;
import com.google.gson.Gson;

public class CommentWriteAction implements Action {
@Override
public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	int userId = Integer.parseInt(request.getParameter("userId"));	
	int boardId = Integer.parseInt(request.getParameter("boardId"));
	String content = request.getParameter("content");
	
	HttpSession session = request.getSession();
	User user = (User) session.getAttribute("user");
	
	

	
	Comment commentForm = new Comment();
	commentForm.setUserId(userId);
	commentForm.setBoardId(boardId);
	commentForm.setContent(content);
	

	CommentDao dao = new CommentDao();
	//form���� ���� �����͸� Comment ��ü�� ��ȯ
	int result = dao.save(commentForm);
	System.out.println(result);
	if(result==1) {
		//Comment ���̺� ���� �������� ������� Comment�� Ʃ���� �ʿ�
		Comment comment = dao.findByMaxId();
		comment.getResponseData().setStatusCode(1);
		comment.getResponseData().setStatus("ok");
		comment.getResponseData().setStatusMessage("Write completed");
		//Gson�� �̿��ؼ� Json���� ��ȯ
		
		Gson gson = new Gson();
		String commentJson = gson.toJson(comment);
		
		System.out.println("(1)commentJson : " + commentJson);
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(commentJson);
		out.flush();
		
	}else {
		Script.back(response);
	}
	
	
	System.out.println("userId : " + userId);
	System.out.println("boardId : " + boardId);
	System.out.println("content : " + content);
}
}
