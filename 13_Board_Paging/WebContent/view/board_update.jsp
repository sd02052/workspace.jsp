<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:set var="dto" value="${edit }" />
	
	<div align="center">
	<hr width="50%" color="gray">	
	<h3>${dto.getBoard_writer() }님 게시물 수정 폼</h3>
	<hr width="50%" color="gray">
	<br> <br>
	
	<form method="post" action="<%=request.getContextPath() %>/board_update_ok.do">
		<input type="hidden" name="no" value="${dto.getBoard_no() }">
		<input type="hidden" name="page" value="${page }">
		<input type="hidden" name="db_pwd" value="${dto.getBoard_pwd() }">
		<table border="1" cellspacing="0" width="400">
			<tr>
				<th>작성자</th>
				<td><input type="text" name="writer" value="${dto.getBoard_writer() }" readonly></td>
			</tr>
			<tr>
				<th>글제목</th>
				<td><input type="text" name="title" value="${dto.getBoard_title() }"></td>
			</tr>
			<tr>
				<th>글내용</th>
				<td> <textarea rows="7" cols="30" name="cont">${dto.getBoard_cont() }</textarea> </td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="pwd"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="수정하기">
					&nbsp;&nbsp;&nbsp;
					<input type="reset" value="다시작성">
				</td>
			</tr>
		</table>
	</form>
	</div>
</body>
</html>