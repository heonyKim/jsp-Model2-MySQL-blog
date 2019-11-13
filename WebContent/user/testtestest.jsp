<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String path = request.getRealPath("media");
	int size = 1024*1024*2; //2MB
	
	MultipartRequest multi = new MultipartRequest(
			request,
			path,
			size,	
			"UTF-8",
			new DefaultFileRenamePolicy()	//동일한 파일명이 들어오면 파일명 뒤에 숫자를 붙임.
			);
	
	String username = multi.getParameter("username");
	String fileName = multi.getFilesystemName("userProfile");	//정책에 의해서 변경된 이름
	String originFileName=multi.getOriginalFileName("userProfile");//원래 파일 이름
	String contextPath = getServletContext().getContextPath();
	String downloadPath = getServletContext().getRealPath("media");
	
	String filePath = contextPath+"/media/"+fileName;
%>

path : <%=path %><br>
username : <%=username %><br>
fileName : <%=fileName %><br>
originFileName : <%=originFileName %><br>
contextPath : <%=contextPath %><br>
downloadPath : <%=downloadPath %><br>
<img src = "<%=filePath%>"width="300px" height="300px">


</body>
</html>