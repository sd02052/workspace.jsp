<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String userId = request.getParameter("id").trim();
	String userPwd = request.getParameter("pwd").trim();
	
	// 원래는 DB의 회원 관련 테이블에서 입력한 id와 pwd가 맞는지 
	// 확인하여서 회원이면 메인 페이지로 이동
	String dbId = "hong";
	String dbPwd = "1234";
	
	if(userId.equals(dbId)){ // 입력한 아이디와 DB 테이블 상의 아이디가 같은 경우
		if(userPwd.equals(dbPwd)){ // 입력한 비밀번호와 DB 테이블 상의 비밀번호가 같은 경우
			// 회원인 경우
			// 회원인 경우에는 메인 페이지로 이동 ==> 페이지 이동
			request.setAttribute("name", "홍길동");
			request.setAttribute("addr", "서울시 마포구");
			
			// 페이지 이동
			response.sendRedirect("Ex08.jsp");
		} else {
			out.println("비밀번호가 틀립니다.");
		}
	} else {
		out.println("아이디를 확인해 주세요.");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>