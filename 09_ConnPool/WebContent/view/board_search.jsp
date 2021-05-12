<%@page import="com.board1.model.BoardDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	List<BoardDTO> list = (List<BoardDTO>) request.getAttribute("search");
	String name = (String) request.getAttribute("name");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div align="center">
		<hr width="50%" color="pink">
		<h3>
			"<%=name%>" 검색 결과
		</h3>
		<hr width="50%" color="pink">
		<br> <br>

		<table border="1" cellspacing="0" width="450">
			<tr>
				<th>글번호</th>
				<th>글제목</th>
				<th>조회수</th>
				<th>작성일자</th>
			</tr>
			<%
				if (list.size() != 0) {
					for (int i = 0; i < list.size(); i++) {
						BoardDTO dto = list.get(i);
			%>
			<tr>
				<td><%=dto.getBoard_no()%></td>
				<td><a
					href="<%=request.getContextPath()%>/content.do?no=<%=dto.getBoard_no()%>">
						<%=dto.getBoard_title()%></a></td>
				<td><%=dto.getBoard_hit()%></td>
				<td><%=dto.getBoard_regdate()%></td>
			</tr>
			<%
				}
				} else {
			%>
			<tr>
				<td colspan="4" align="center">
					<h3>검색된 게시물이 없습니다.</h3>
				</td>
			</tr>
			<%
				}
			%>
			<tr>
				<td colspan="4" align="right">
					<input type="button" value="전체목록" onclick="location.href='<%=request.getContextPath() %>/select.do'">
				</td>
			</tr>
		</table>
	</div>
</body>
</html>