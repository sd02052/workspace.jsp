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
	<c:set var="list" value="${search }" />
	<c:set var="target" value="${target }" />
	<div align="center">
		<hr width="50%" color="orange">
		<h3>"${target }" 검색 결과</h3>
		<hr width="50%" color="orange">
		<br> <br>
		
		<table border="1" cellspacing="0" width="400">
			<tr>
				<th>글번호</th>
				<th>글제목</th>
				<th>조회수</th>
				<th>작성일자</th>
			</tr>
			<c:if test="${!empty list }">
				<c:forEach items="${list }" var="dto">
					<tr>
						<td>${dto.getBoard_no() }</td>
						<td><a href="<%=request.getContextPath() %>/board_cont.do?no=${dto.getBoard_no()}">
						${dto.getBoard_title() }</a></td>
						<td>${dto.getBoard_hit() }</td>
						<td>${dto.getBoard_regdate().substring(0,10) }</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty list }">
				<tr>
					<td colspan="4" align="center">
						<h3>검색된 게시물이 없습니다.</h3>
					</td>
				</tr>
			</c:if>
			<tr>
				<td colspan="4" align="right">
					<input type="button" value="전체목록" onclick="location.href='board_list.do'">
				</td>
			</tr>
		</table>
	</div>
</body>
</html>