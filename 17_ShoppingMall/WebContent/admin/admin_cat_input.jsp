<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<jsp:include page="../include/admin_top.jsp" />
	
	<div align="center">
		<hr width="65%" color="maroon">
		<h3>카테고리 등록 폼 페이지</h3>
		<hr width="65%" color="maroon">
		<br> <br>
		
		<form method="post" action="<%=request.getContextPath() %>/admin_cat_input_ok.do">
			<table border="1" cellspacing="0" width="300">
				<tr>
					<th>카테고리 코드</th>
					<td> <input type="text" name="cat_code"> </td>
				</tr>
				<tr>
					<th>카테고리 이름</th>
					<td> <input type="text" name="cat_name"> </td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="카테고리 등록">
						&nbsp;&nbsp;&nbsp;
						<input type="reset" value="다시작성">
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<jsp:include page="../include/admin_bottom.jsp" />
</body>
</html>