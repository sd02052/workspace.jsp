<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.6.0.js"></script>
<script type="text/javascript">
	$(function() {
		$.ajax({
			type : "get",
			url : "data/book.xml",
			dataType : "xml",
			success : function(data) {
				// $(data)는 $.ajax() 메서드가  book.xml 파일에서 
				// 불러온 데이터 객체를 말함. 
				// find() 메서드로 하위 book 태그를 찾음. 
				// 작성된 book 태그만큼 each() 함수를 이용하여 반복적으로 안의 내용을 수행하게 됨.
				$(data).find("book").each(function() {
					// this는 현재의 book 객체를 의미
					let title = $("title", this).text();
					let author = $("author", this).text();
					let price = $("price", this).text();
					let txt = "<li>책 제목 : " + title + "</li>"
							+ "<li>책 저자 : " + author + "</li>"
							+ "<li>책 가격 : " + price + "</li><hr>";
					$("body").append(txt);
				});
			},
			error : function(){
				alert("데이터 통신 오류입니다.");
			}
		});
	});
</script>
</head>
<body>

</body>
</html>