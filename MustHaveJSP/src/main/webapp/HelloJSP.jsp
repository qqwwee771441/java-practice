<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    errorPage = "IsErrorPage.jsp" 
    trimDirectiveWhitespaces="true"%>    
<%@ include file="IncludeFile.jsp" %>
<%!
public int add(int num1, int num2) {
	return num1 + num2 ;
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HelloJSP</title>
</head>
<body>
	<h2>JSP Test</h2>
	<div>
		<h3>0. 테스트</h3>
		<p>
			<%
				out.println("오늘 날짜 : " + today);
				out.println("<br>");
				out.println("어제 날짜 : " + yesterday);
				out.println("<br>");
				out.println("내일 날짜 : " + tomorrow);
			%> <br>
			덧셈 결과 : <%= add(10, 20) %>
		</p>
		<h3>1. 클라이언트와 서버의 환경정보 읽기</h3>
		<a href="RequestWebInfo.jsp?id=qqwwee41&password=aassdd41">GET 방식 전송</a> <br>
		<form action="RequestWebInfo.jsp" method="post">
			ID : <input type="text" name="id" value="qqwwee41"> <br>
			PASSWORD : <input type="text" name="password" value="aassdd41"> <br>
			<input type="submit" value="POST 방식 전송">
		</form>
		<h3>2. 클라이언트의 요청 매개변수 읽기</h3>
		<form action="RequestParameter.jsp" method="post">
			아이디 : <input type="text" name="id" value=""> <br>
			성별 : 
			<input type="radio" name="gender" value="man">남자
			<input type="radio" name="gender" value="woman">여자 <br>
			관심사항 :
			<input type="checkbox" name="favo" value="eco">경제
			<input type="checkbox" name="favo" value="pol">정치
			<input type="checkbox" name="favo" value="ent">연예 <br>
			자기소개 :
			<textarea name="intro" cols="30" rows="4"></textarea> <br>
			<input type="submit" value="전송하기">
		</form>
		<h3>3. HTTP 요청 헤더 정보 읽기</h3>
		<a href="RequestHeader.jsp">요청 헤더 정보 읽기</a>
		
		<hr>
		
		<h2>1. 로그인 폼</h2>
		<%
		String loginErr = request.getParameter("loginErr");
		if (loginErr != null) out.print("로그인 실패");
		%>
		<form action="ResponseLogin.jsp" method="post">
			아이디 : <input type="text" name="user_id" placeholder="아이디를 입력하세요."> <br>
			패스워드 : <input type="password" name="user_pwd" placeholder="비밀번호를 입력하세요."> <br>
			<input type="submit" value="로그인">
		</form>
		<h2>2. HTTP 응답 헤더 설정하기</h2>
		<form action="ResponseHeader.jsp" method="get">
			날짜 형식 : <input type="text" name="add_date" value="2022-05-02"> <br>
			숫자 형식 : <input type="text" name="add_int" value="8282"> <br>
			문자 형식 : <input type="text" name="add_str" value="가나다"> <br>
			<input type="submit" value="응답 헤더 설정 & 출력">
		</form>
	</div>
</body>
</html>
