<%@page import="com.board.model.BoardDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	List<BoardDTO> list = (List<BoardDTO>) request.getAttribute("List");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
td {
	text-align: center;
}
</style>
</head>
<body>

	<div align="center">
		<hr width="50%" color="red">
		<h3>BOARD 테이블 전체 게시물 목록</h3>
		<hr width="50%" color="red">
		<br> <br>

		<table border="1" cellspacing="0" width="450">
			<tr>
				<th>글번호</th>
				<th>글제목</th>
				<th>조회수</th>
				<th>작성일자</th>
			</tr>
			<%
				if (list.size() != 0) { // 검색된 레코드가 있는 경우
					for (int i = 0; i < list.size(); i++) {
						BoardDTO dto = list.get(i);
			%>
			<tr>
				<td><%=dto.getBoard_no()%></td>
				<td>
					<a href="<%=request.getContextPath() %>/content.do?no=<%=dto.getBoard_no() %>">
					<%=dto.getBoard_title()%>
					</a>
				</td>
				<td><%=dto.getBoard_hit()%></td>
				<td><%=dto.getBoard_regdate().substring(0, 10)%></td>
			</tr>
			<%
				} // for 문 끝
				} else { // 검색된 레코드가 없는 경우
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
					<input type="button" value="글쓰기" onclick="location.href='view/board_write.jsp'">
				</td>
			</tr>			
		</table>
		<br> <br>
		
		<form method="post" action="<%=request.getContextPath() %>/search.do">
			<select name="find_field">
				<%-- String find_field = "title"; --%>
				<option value="title">제목</option>
				<option value="content">내용</option>
				<option value="title_content">제목+내용</option>
			</select>
			
			<input type="text" name="find_name" size="20">
			<input type="submit" value="검색">
		</form>
		
	</div>
</body>
</html>