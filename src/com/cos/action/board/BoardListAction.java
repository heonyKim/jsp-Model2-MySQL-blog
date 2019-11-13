package com.cos.action.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.action.Action;
import com.cos.dao.BoardDao;
import com.cos.model.Board;
import com.cos.util.Utils;

public class BoardListAction implements Action{

	private final static String TAG="BoardListAction : ";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("page")==null) return;
		
		int page = Integer.parseInt(request.getParameter("page"));
		
		
		BoardDao bDao = new BoardDao();
		List<Board> boards = null;
		List<Board> hotBoards = bDao.findOrderByReadCountDesc();
		//page<=0 Ȥ�� page>maxNum ��ư ��Ȱ��ȭ
		
		if(page <=0) {
			page = 1;
		}
		
		
		//search�� ����� �б�
		if(request.getParameter("search")==null || request.getParameter("search").equals("")) {
			boards = bDao.findAll(page);
			request.setAttribute("search", null);
		}else {
			String search = request.getParameter("search");
			boards = bDao.findAll(page, search);
			request.setAttribute("search", search);
		}

		
		
		
		int maxPage = bDao.findMaxPage();
		
		Utils.setPreviewImg(boards);		//�̹����� previewImg�� ����
		Utils.setPreviewContent(boards);	//�̹��� �±� ����
		Utils.setPreviewImg(hotBoards);		//�̹����� prviewImg�� ����(�߰�)
		
		request.setAttribute("boards", boards);
		request.setAttribute("hotBoards", hotBoards);
		request.setAttribute("maxPage", maxPage);
		RequestDispatcher dis =request.getRequestDispatcher("/board/list.jsp");
		dis.forward(request, response);
	}
	
}
