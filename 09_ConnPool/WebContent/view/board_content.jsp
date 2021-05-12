<%@page import="com.board1.model.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	BoardDTO dto = (BoardDTO) request.getAttribute("cont");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div align="center">
		<table border="1" cellspacing="0" width="450">
			<%
				if (dto != null) {
			%>
			<tr>
				<td colspan="2" align="center">
					<h3><%=dto.getBoard_writer()%>님 게시물 상세 내역
					</h3>
				</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><%=dto.getBoard_writer()%></td>
			</tr>
			<tr>
				<th>글제목</th>
				<td><%=dto.getBoard_title()%></td>
			</tr>
			<tr>
				<th>글내용</th>
				<td><textarea rows="8" cols="30"><%=dto.getBoard_cont()%></textarea>
				</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td>
					<%
						if (dto.getBoard_pwd().length() != 0) {
								for (int i = 0; i < dto.getBoard_pwd().length(); i++) {
					%>* <%
						}
							}
					%>
				</td>
			</tr>
			<tr>
				<th>조회수</th>
				<td><%=dto.getBoard_hit()%></td>
			</tr>
			<tr>
				<th>작성일자</th>
				<td><%=dto.getBoard_regdate()%></td>
			</tr>
			<%
				} else {
			%>
			<tr>
				<td colspan="2" align="center">
					<h3>검색된 게시물이 없습니다.</h3>
				</td>
			</tr>
			<%
				}
			%>
			<tr>
				<td colspan="2" align="center">
					<input type="button" value="글 수정" onclick="location.href='update.do?no=<%=dto.getBoard_no() %>'">
					<input type="button" value="글 삭제" onclick="location.href='view/board_delete.jsp?no=<%=dto.getBoard_no() %>'">
					<input type="button" value="전체 게시물" onclick="location.href='select.do'">
				</td>
			</tr>
		</table>
	</div>
</body>
</html>