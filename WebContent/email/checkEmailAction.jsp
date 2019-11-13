<%@page import="java.io.PrintWriter"%>
<%@page import="com.cos.util.SHA256"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	String to = request.getParameter("email");
	String code=request.getParameter("code");
	

//send code값과 return code값이 같으면
	boolean rightCode = SHA256.getEncrypt(to, to).equals(code)?true:false;
		
	PrintWriter script = response.getWriter();
	if(rightCode == true){
		//DB에 컬럼 emailCheck(Number) 1:인증, 0:미인증  >1로 업데이트 시켜줌
		script.println("<script>");
		script.println("alert('이메일 인증에 성공하였습니다.')");
		script.println("location.href='/blog/user?cmd=check&email="+to+"'");
		script.println("</script>");
	} else{
		script.println("<script>");
		script.println("alert('이메일 인증을 실패하였습니다.')");
		script.println("location.href='error.jsp'");
		script.println("</script>");
	}

	

//인증완료후 로그인페이지로 이동

//미인증시 error페이지로 이동
%>