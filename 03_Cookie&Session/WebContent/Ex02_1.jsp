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
		String userId = request.getParameter("id").trim();
		String userPwd = request.getParameter("pwd").trim();

		String db_id = "hong";
		String db_pwd = "1234";

		if (userId.equals(db_id)) {
			if (userPwd.equals(db_pwd)) {
				// 회원인 경우
				// 메인 페이지로 이동
				// 세션 설정
				session.setAttribute("id", userId);
				session.setAttribute("pwd", db_pwd);
	%>
	<script>
		location.href = "Ex02_2.jsp";
	</script>
	<%
		}
		}
	%>
</body>
</html>