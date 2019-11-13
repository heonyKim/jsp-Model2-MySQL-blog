package com.cos.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.action.Action;
import com.cos.dao.BoardDao;
import com.cos.dao.CommentDao;
import com.cos.model.Board;
import com.cos.model.Comment;
import com.cos.util.Script;
import com.cos.util.Utils;



public class BoardDetailAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("id") == null || request.getParameter("id").equals(""))
			return;

		int id = Integer.parseInt(request.getParameter("id"));

		BoardDao dao = new BoardDao();
		Board board = dao.findById(id);

		CommentDao commentDao = new CommentDao();

		List<Comment> comments = commentDao.findByBoardId(id);
		
		
		if (board != null) {
		
			String cookieBoardId=id+"";
			Cookie c = new Cookie("boardId", cookieBoardId);
			c.setMaxAge(60 * 60 * 24);
			c.setPath("/");
			
			int result = 0;
			Utils.readCounts.add(c.getValue()+"");
			
			for(int i=0;i<Utils.readCounts.size();i++) {
				
				if(cookieBoardId!=Utils.readCounts.get(i)) {
					result = dao.increaseReadCount(id);
				}
				
			}
			System.out.println(Utils.readCounts);

			if (result == 1||result==0) {

				// À¯Æ©ºê ÁÖ¼Ò ÆÄ½Ì
				Utils.setPreviewYoutube(board);

				request.setAttribute("board", board);
				request.setAttribute("comments", comments);
				

				RequestDispatcher dis = request.getRequestDispatcher("board/detail.jsp");
				dis.forward(request, response);

				
			} else {
				Script.back(response);
			}
		} else {
			Script.back(response);
		}

	}
}
