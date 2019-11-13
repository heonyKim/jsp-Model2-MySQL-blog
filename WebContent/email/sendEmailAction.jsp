<%@page import="java.util.Properties"%>
<%@page import="com.cos.util.Gmail"%>
<%@page import="com.cos.util.SHA256"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@page import="java.io.PrintWriter"%>
<%@page import="javax.mail.Authenticator"%>
<%@page import="javax.mail.Transport"%>
<%@page import="javax.mail.Message"%>
<%@page import="javax.mail.internet.InternetAddress"%>
<%@page import="javax.mail.Address"%>
<%@page import="javax.mail.internet.MimeMessage"%>
<%@page import="javax.mail.Session"%>
<%@page import="java.util.Properties"%>

<%	
	//메일을 전송해보자.
	String to =request.getParameter("email");	//메일받기
	String from = "ez2complex@gmail.com";
	String code = SHA256.getEncrypt(to, to);	//code값을 SHA256
	
	//사용자에게 보낼 메세지(3)
	String subject = "HeonyBlog 회원가입을 위한 이메일 인증입니다.";
	StringBuffer sb = new StringBuffer();
	sb.append("다음 링크에 접속하여 이메일 인증을 진행해주세요. \n");
	sb.append("<a href = 'http://localhost:8000/blog/email/checkEmailAction.jsp?code="+code+"&email="+to+"'>");
	sb.append("이메일 인증하기</a>");
	String content=sb.toString();
	
	//설정값
	Properties p = new Properties();
	p.put("mail.smtp.user", from);
	p.put("mail.smtp.host", "smtp.googlemail.com");
	p.put("mail.smtp.port", "465"); //TLS 587, SSL 465
	p.put("mail.smtp.starttls.enable", "true");
	p.put("mail.smtp.auth", "true");
	p.put("mail.smtp.debug", "true");
	p.put("mail.smtp.socketFactory.port", "465"); 
	p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	p.put("mail.smtp.sockerFactory.fallback", "false");


	
	
	try{
		Authenticator auth = new Gmail(); //(5)내가 설정한 아이디 비번 넘겨주기
		Session ses = Session.getInstance(p, auth);
		ses.setDebug(true);
		MimeMessage msg = new MimeMessage(ses);
		msg.setSubject(subject);
		Address fromAddr = new InternetAddress(from);
		msg.setFrom(fromAddr);
		Address toAddr = new InternetAddress(to);
		msg.addRecipient(Message.RecipientType.TO, toAddr);
		msg.setContent(content, "text/html; charset=UTF8");
		Transport.send(msg);	//메일전송 최종함수
	
	}catch(Exception e){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('이메일 인증 오류')");
		script.println("history.back();");
		script.println("</script>");
	}

	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>이메일로 인증메일이 전송되었습니다.</h1>
<h3>이메일에 들어가서 인증부탁드립니다.</h3>
</body>
</html>