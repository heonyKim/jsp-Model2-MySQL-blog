package com.cos.action.user;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.action.Action;
import com.cos.dao.UserDao;
import com.cos.model.User;
import com.cos.util.Script;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class UserProfileAction implements Action {
	@SuppressWarnings("deprecation")
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String path = request.getRealPath("media");
		int size = 1024 * 1024 * 10; // 용량 제한(10mb)

		MultipartRequest multi = new MultipartRequest(request, path, size, "UTF-8", new DefaultFileRenamePolicy());

		try {
			ServletContext context = request.getSession().getServletContext();

			String fileName = multi.getFilesystemName("userProfile");
			String contextPath = context.getContextPath();

			String filePath = contextPath + "/media/" + fileName;

//////////////////////////////////////////////////////////////////////////////////////////////////////////////

			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");

			user.setUserProfile(filePath);

			UserDao dao = new UserDao();
			int result = dao.profileImg(user);

				if (result == 1) {
					response.sendRedirect("/blog/index.jsp");
				} else {
					Script.back(response);
				}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}
};