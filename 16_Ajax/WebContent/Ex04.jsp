<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.6.0.js"></script>
<script type="text/javascript">
	$(function(){
		$.ajax({
			type: "get",
			dataType: "json",
			url: "data/data.json",
			success: function(data){
				// index : data 객체 내의 인덱스를 말함.
				// item : data 객체 내의 이름과 값을 가지고 있는 객체를 말함.
				$.each(data, function(index, item){
					var txt = "<li>책 제목 : " + item.title + "</li>" + 
							"<li>책 저자 : " + item.author + "</li>" +
							"<li>책 가격 : " + item.price + "</li><hr>";
					$("body").append(txt);
				});
			},
			error : function(){
				alert("통신 오류 에러입니다.");
			}
		});
	});
</script>
</head>
<body>

</body>
</html>