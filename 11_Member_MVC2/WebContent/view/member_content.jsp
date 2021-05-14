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
	
	<c:set var="dto" value="${cont }" />
	<div align="center">
		<hr width="50%" color="red">
		<h3>${dto.getMemname() } 회원 정보 상세 내역</h3>
		<hr width="50%" color="red">
		<br> <br>
		
		
		<table border="1" cellspacing="0" width="400">
			<c:if test="${!empty dto }">
				<tr>
					<th>회원 번호</th>
					<td> ${dto.getNum() }</td>
				</tr>
				<tr>
					<th>회원 ID</th>
					<td> ${dto.getMemid() }</td>
				</tr>
				<tr>
					<th>회원 이름</th>
					<td> ${dto.getMemname() }</td>
				</tr>
				<tr>
					<th>회원 비밀번호</th>
					<td>
						<c:if test="${dto.getPwd().length() != 0 }">
							<c:forEach begin="1" end="${dto.getPwd().length()}" step="1" var="i">
								*
							</c:forEach>
						</c:if>
					</td>
				</tr>
				<tr>
					<th>회원 나이</th>
					<td> ${dto.getAge() }</td>
				</tr>
				<tr>
					<th>회원 마일리지</th>
					<td> ${dto.getMileage() }</td>
				</tr>
				<tr>
					<th>회원 직업</th>
					<td> ${dto.getJob() }</td>
				</tr>
				<tr>
					<th>회원 주소</th>
					<td> ${dto.getAddr() }</td>
				</tr>
			</c:if>
			<c:if test="${empty dto }">
				<tr>
					<td colspan="2" align="center">
						<h3>검색된 회원의 정보가 없습니다.</h3>
					</td>
				</tr>
			</c:if>
			
			<tr>
				<td colspan="2" align="center">
					<input type="button" value="회원 수정" onclick="location.href='update.do?num=${dto.getNum()}'">
					<input type="button" value="회원 삭제" onclick="location.href='delete.do?num=${dto.getNum()}'">
				</td>
			</tr>
		</table>
	</div>
</body>
</html>