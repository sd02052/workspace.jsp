<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	Cookie[] cookies = request.getCookies();
	if (cookies != null) {
		for (int i = 0; i < cookies.length; i++) {
			String str = cookies[i].getName();
			if (str.equals("name")) {
				out.println("cookies[" + i + "] name >>> " + cookies[i].getName() + "<br>");
				out.println("cookies[" + i + "] value >>> " + cookies[i].getValue() + "<br>");

				// 쿠키를 삭제해 보자
				cookies[i].setMaxAge(0);
				response.addCookie(cookies[i]);
			}
		}
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<hr>
	<br>
	<br>

	<form method="post" action="Ex01_3.jsp">
		<input type="submit" value="쿠키 확인">
	</form>
</body>
</html>