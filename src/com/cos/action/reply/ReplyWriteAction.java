package com.cos.action.reply;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.action.Action;
import com.cos.dao.ReplyDao;
import com.cos.model.Reply;
import com.cos.model.User;
import com.cos.util.Script;
import com.google.gson.Gson;

public class ReplyWriteAction implements Action {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int userId = Integer.parseInt(request.getParameter("userId"));
		int commentId = Integer.parseInt(request.getParameter("commentId"));
		String content = request.getParameter("reply-content");
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		Reply replyform = new Reply();
		replyform.setUserId(userId);
		replyform.setCommentId(commentId);
		replyform.setContent(content);
		
		ReplyDao dao = new ReplyDao();
		int result = dao.save(replyform);
		
		if(result==1){
			Reply reply = dao.findByMaxId();
			reply.getResponseData().setStatusCode(1);
			reply.getResponseData().setStatus("ok");
			reply.getResponseData().setStatusMessage("reply complete");
			
			Gson gson = new Gson();
			
			String replyJson = gson.toJson(reply);
			System.out.println("(2)replyJson : "+replyJson);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(replyJson);
			out.flush();
		}else {
			Script.back(response);
		}
		
	
	}
}