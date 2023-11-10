<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내장 객체 request</title>
</head>
<body>
	<ul>
		<li>데이터 전송 방식 : <%= request.getMethod() %></li>
		<li>URL : <%= request.getRequestURL() %></li>
		<li>URI : <%= request.getRequestURI() %></li>
		<li>프로토콜 : <%= request.getProtocol() %></li>
		<li>서버명 : <%= request.getServerName() %></li>
		<li>서버 포트 : <%= request.getServerPort() %></li>
		<li>클라이언트 IP 주소 : <%= request.getRemoteAddr() %></li>
		<li>쿼리스트링 : <%= request.getQueryString() %></li>
		<li>전송된 값 1 (ID) : <%= request.getParameter("id") %></li>
		<li>전송된 값 2 (PASSWORD) : <%= request.getParameter("password") %></li>
	</ul>
</body>
</html>