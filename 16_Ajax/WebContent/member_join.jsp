<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.6.0.js"></script>
<script type="text/javascript">
	
	// 웹 문서가 브라우저로 로딩될 때 문서의 본문을 읽고 난 후 jquery를 실행하라는 의미.
	$(function(){
		// 회원가입 폼 페이지 중에서 아이디중복체크라는 버튼에 마우스가 
		// 올라갔을 때 호출되는 무명함수
		$("#idcheck_btn").mouseover(function(){
			$("#idcheck").hide(); // span 태그 영역을 숨겨라.
			
			var userId = $("#member_id").val();
			
			// 입력 길이 체크
			if($.trim($("#member_id").val()).length < 4){
				var warningTxt = '<font color="red">아이디는 4자 이상이어야 합니다.</font>';
				$("#idcheck").text(''); // idcheck 영억을 초기화.
				$("#idcheck").show(); // span 태그 영역을 보여줘라.
				$("#idcheck").append(warningTxt);
				$("#member_id").val("").focus();
				return false; // action 페이지로 안넘어가게 해줌
			}
			
			// 입력 길이 체크
			if($.trim($("#member_id").val()).length > 16){
				var warningTxt = '<font color="red">아이디는 16자 이하이어야 합니다.</font>';
				$("#idcheck").text(''); // idcheck 영억을 초기화.
				$("#idcheck").show(); // span 태그 영역을 보여줘라.
				$("#idcheck").append(warningTxt);
				$("#member_id").val("").focus();
				return false;
			}	
			
			// 아이디 중복 여부 확인 - Ajax 기술(비동기 통신)
			$.ajax({
				type: "post",
				url: "data/idCheck.jsp",
				data: {"paramId" : userId},
				dataType: "text",
				success: function(data){ // 통신이 성공할 경우 결과값을 data라는 변수에 저장
					console.log(data);
					if(data == 1){ // DB에 아이디가 존재하는 경우(중복인 경우)
						var warningTxt = '<font color="red">중복 아이디입니다.</font>';
						$("#idcheck").text(''); // idcheck 영억을 초기화.
						$("#idcheck").show(); // span 태그 영역을 보여줘라.
						$("#idcheck").append(warningTxt);
						$("#member_id").val("").focus();
						return false;
					} else { // DB에 아이디가 존재하지 않는 경우(중복이 아닌 경우)
						var warningTxt = '<font color="blue">사용 가능한 아이디입니다.</font>';
						$("#idcheck").text(''); // idcheck 영억을 초기화.
						$("#idcheck").show(); // span 태그 영역을 보여줘라.
						$("#idcheck").append(warningTxt);
					}
				},
				error: function(){
					alert("통신 오류입니다.");
				}
			});
		});
	});
</script>
</head>
<body>

	<div align="center">
		<hr width="50%" color="red">
		<h3>회원 가입 폼</h3>
		<hr width="50%" color="red">
		<br> <br>
		
		<form method="post" action="http://www.google.com">
			<table border="1" cellspacing="0" width="450">
				<tr>
					<th>회원 아이디</th>
					<td>
						<input name="member_id" id="member_id" size="20">
						<input type="button" value="아이디중복체크" id="idcheck_btn">
						<br>
						<span id="idcheck"></span>
					</td>
				</tr>
				<tr>
					<th>회원 이름</th>
					<td> <input name="member_name"> </td> 
				</tr>
			</table>
		</form>
	</div>
</body>
</html>