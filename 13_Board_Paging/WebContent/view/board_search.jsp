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

	<div align="center">
		<hr width="50%" color="blue">
		<h3>"${name }" 검색 결과 폼</h3>
		<hr width="50%" color="blue">
		<br> <br>
		
		<table border="1" cellspacing="0" width="500">
			<tr>
				<th>글번호</th>
				<th>글제목</th>
				<th>조회수</th>
				<th>작성일자</th>
			</tr>
			<c:set var="list" value="${List }" />
			<c:if test="${!empty list }">
				<c:forEach items="${list}" var="dto">
					<tr>
						<td> ${dto.getBoard_no() }</td>
						<td> <a href="<%=request.getContextPath() %>/board_cont.do?no=${dto.getBoard_no() }&page=${page }">
						 ${dto.getBoard_title() }</a></td>
						<td> ${dto.getBoard_hit() }</td>
						<td> ${dto.getBoard_regdate().substring(0,10) }</td>
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
		</table>
		<br>
		
		<c:if test="${page > block }">
			<a href="board_search.do?page=1&search_field=${field }&search_name=${name }">◀◀</a>
			<a href="board_search.do?page=${startBlock - 1 }&search_field=${field }&search_name=${name }">◀</a>
		</c:if>
		<c:forEach begin="${startBlock }" end="${endBlock }" var="i">
			<c:if test="${i == page }">
				<b><a href="board_search.do?page=${i }&search_field=${field }&search_name=${name }">[${i }]</a></b>
			</c:if>
			
			<c:if test="${i != page }">
				<a href="board_search.do?page=${i }&search_field=${field }&search_name=${name }">[${i }]</a>
			</c:if>
		</c:forEach>
		
		<c:if test="${endBlock < allPage }">
			<a href="board_search.do?page=${endBlock+1 }&search_field=${field }&search_name=${name }">▶</a>
			<a href="board_search.do?page=${allPage }&search_field=${field }&search_name=${name }">▶▶</a>
		</c:if>
		<br><br>
		<input type="button" value="전체목록" onclick="location.href='board_list.do?page=1'">
	</div>
</body>
</html>