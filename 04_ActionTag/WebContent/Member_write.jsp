<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="50%" color="gray">
		<h3>MEMBER 테이블 회원가입 폼</h3>
		<hr width="50%" color="gray">
		<br>
		<form method="post" action="Member_join.jsp">
			<table border="1" cellspacing="0" width="400px">
				<tr>
					<th>회원 아이디</th>
					<td><input type="text" name="memid"></td>
				</tr>
				<tr>
					<th>회원 이름</th>
					<td><input type="text" name="memname"></td>
				</tr>
				<tr>
					<th>회원 비밀번호</th>
					<td><input type="password" name="pwd"></td>
				</tr>
				<tr>
					<th>회원 나이</th>
					<td><input type="text" name="age"></td>
				</tr>
				<tr>
					<th>회원 마일리지</th>
					<td><input type="text" name="mileage"></td>
				</tr>
				<tr>
					<th>회원 직업</th>
					<td><input type="text" name="job"></td>
				</tr>
				<tr>
					<th>회원 주소</th>
					<td><input type="text" name="addr"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="회원 등록">
						&nbsp;&nbsp;&nbsp;
						<input type="reset" value="다시 작성">
					</td>
				</tr>
			</table>
		</form>
	</div>
	
</body>
</html>