<%@page import="com.board1.model.BoardDAO"%>
<%@page import="com.board1.model.BoardDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	List<BoardDTO> list = (List<BoardDTO>) request.getAttribute("List");
	int pageSize = (int) request.getAttribute("pageSize");
	int currentPage = (int) request.getAttribute("currentPage");
	int startRow = (int) request.getAttribute("startRow");
	int endRow = (int) request.getAttribute("endRow");
	int count = (int) request.getAttribute("count");
	
	String pageNum = (String) request.getAttribute("pageNum");
	if(pageNum == null){
		pageNum = "1";
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
a{
	text-decoration: none;
}
</style>
</head>
<body>

	<div align="center">
		<hr width="50%" color="gray">
		<h3>BOARD 테이블 전체 게시물 목록</h3>
		<hr width="50%" color="gray">
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
					int number = count - (currentPage - 1) * pageSize;
					for (int i = 0; i < list.size(); i++) {
						BoardDTO dto = list.get(i);
			%>
			<tr>
				<td><%=dto.getBoard_no()%></td>
				<td> <a href="<%=request.getContextPath() %>/content.do?no=<%=dto.getBoard_no() %>">
				<%=dto.getBoard_title()%></a></td>
				<td><%=dto.getBoard_hit()%></td>
				<td><%=dto.getBoard_regdate().substring(0, 10)%></td>
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
					<input type="button" value="글쓰기" onclick="location.href='view/board_write.jsp'">
				</td>
			</tr>
		</table>
		
		<br> <br>
		
		<form method="post" action="<%=request.getContextPath() %>/search.do">
			<select name="find_field">
				<option value="title">제목</option>
				<option value="content">내용</option>
				<option value="title_content">제목+내용</option>
			</select>
			
			<input type="text" name="find_name" size="15">
			<input type="submit" value="검색">
		</form>
		<br>
		
		<div align="center">
			<%
				if (count > 0) {
					// 총 페이지의 수
					int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
					// 한 페이지에 보여줄 페이지 블럭(링크) 수
					int pageBlock = 10;
					// 한 페이지에 보여줄 시작 및 끝 번호(예 : 1, 2, 3 ~ 10 / 11, 12, 13 ~ 20)
					int startPage = ((currentPage - 1) / pageBlock) * pageBlock + 1;
					int endPage = startPage + pageBlock - 1;

					// 마지막 페이지가 총 페이지 수 보다 크면 endPage를 pageCount로 할당
					if (endPage > pageCount) {
						endPage = pageCount;
					}

					if (startPage > pageBlock) { // 페이지 블록수보다 startPage가 클경우 이전 링크 생성
			%>
			<a href="<%=request.getContextPath() %>/select.do?pageNum=<%=startPage - 10%>">[이전]</a>
			<%
				}

					for (int i = startPage; i <= endPage; i++) { // 페이지 블록 번호
						if (i == currentPage) { // 현재 페이지에는 링크를 설정하지 않음
			%>
			<%=i%>
			<%
				} else { // 현재 페이지가 아닌 경우 링크 설정
			%>
			<a href="<%=request.getContextPath() %>/select.do?pageNum=<%=i%>"><%=i%>
			</a>
			<%
				}
					} // for end

					if (endPage < pageCount) { // 현재 블록의 마지막 페이지보다 페이지 전체 블록수가 클경우 다음 링크 생성
			%>
			<a href="<%=request.getContextPath() %>/select.do?pageNum=<%=startPage + 10%>">[다음]</a>
			<%
				}
				}
			%>
		</div>
	</div>
</body>
</html>