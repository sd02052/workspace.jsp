<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=UTF-8");

	// trim() : 맨 앞과 맨 뒤의 공백을 제거해줌.
	/* String id = request.getParameter("id").trim();
	String pwd = request.getParameter("pwd").trim();
	String name = request.getParameter("name").trim();
	String gender = request.getParameter("gender").trim();
	String addr = request.getParameter("addr").trim();
	String phone = request.getParameter("phone").trim();
	String email = request.getParameter("email").trim(); */
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<table border="1" cellspacing="0" width="400px">
			<tr>
				<th>아이디</th>
				<td><%=request.getParameter("id").trim()%></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><%=request.getParameter("pwd").trim()%></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><%=request.getParameter("name").trim()%></td>
			</tr>
			<tr>
				<th>성별</th>
				<td><%=request.getParameter("gender").trim()%></td>
			</tr>
			<tr>
				<th>주소</th>
				<td><%=request.getParameter("addr").trim()%></td>
			</tr>
			<tr>
				<th>연락처</th>
				<td><%=request.getParameter("phone").trim()%></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><%=request.getParameter("email").trim()%></td>
			</tr>
		</table>
	</div>
</body>
</html>