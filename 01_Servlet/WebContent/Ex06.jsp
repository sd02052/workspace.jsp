<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%-- [문제] 성적 처리 폼 만들기 --%>
	<div align="center">
		<form method="post" action="score">
			<table border="1" cellspacing="0">
				<tr>
					<th>이름</th>
					<td><input type="text" name="name">
				</tr>
				<tr>
					<th>국어</th>
					<td><input type="text" name="kor">
				</tr>
				<tr>
					<th>영어</th>
					<td><input type="text" name="eng">
				</tr>
				<tr>
					<th>수학</th>
					<td><input type="text" name="math">
				</tr>
				<tr>
					<td  colspan="2" align="center">
						<input type="submit" value="확인">
						&nbsp;&nbsp;&nbsp;
						<input type="reset" value="취소">
					</td>
			</table>
		</form>
	</div>
</body>
</html>