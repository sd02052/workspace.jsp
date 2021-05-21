<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:set var="dto" value="${delete }"></c:set>
	<div align="center">
		<hr width="50%" color="blue">
		<h3>${dto.getBoard_writer() }님 게시물 삭제 폼</h3>
		<hr width="50%" color="blue">
		<br> <br>
		
		<form method="post" action="<%=request.getContextPath() %>/bbs_delete_ok.do">
			<input type="hidden" name="no" value="${dto.getBoard_no() }">
			<input type="hidden" name="page" value="${page }">
			<input type="hidden" name="db_pwd" value="${dto.getBoard_pwd() }">
			<input type="hidden" name="group" value="${dto.getBoard_group() }">
			<table border="1" cellspacing="0" width="350">
				<tr>
					<th>삭제할 비밀번호</th>
					<td><input type="password" name="pwd"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="삭제하기">
						&nbsp;&nbsp;&nbsp;
						<input type="reset" value="다시작성">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>