$(function() {
	// 여러 ajax에서 동일하게 사용되는 속성 설정
	$.ajaxSetup({
		ContentType : 'application/x-www-form-urlencoded;charset=UTF-8',
		type : "post"
	});

	// 모든 데이터를 가져오기 - 전체 데이터를 가져오는 함수
	function getData() {
		$.ajax({
			url : "/16_Ajax/select.do",
			dataType : "xml", // 결과 데이터 타입
			success : function(data) {
				// 테이블 태그의 첫번째 행을 제외하고 나머지 행을 모두 제거하라는 의미
				$("#listTable tr:gt(0)").remove();
				let table = "";

				$(data).find("customer").each(function() {
					table += "<tr>";
					table += "<td>" + $(this).find("no").text() + "</td>";
					table += "<td>" + $(this).find("id").text() + "</td>";
					table += "<td>" + $(this).find("name").text() + "</td>";
					table += "<td>" + $(this).find("age").text() + "</td>";
					table += "<td>" + $(this).find("phone").text() + "</td>";
					table += "<td>" + $(this).find("addr").text() + "</td>";
					table += "<td id='tdDel'><input type='button' value='삭제' id='del' name='" +
					$(this).find("no").text()+"'></td>";
					table += "</tr>";
				})
				
				$("#listTable tr:eq(0)").after(table);
			},
			error : function() {
				alert("에러 발생");
			}
		});
	} // getData() 함수 끝
	
	// 아이디 중복 여부 확인
	$("#id").keyup(function(){
		$.ajax({
			url: "/16_Ajax/idCheck.do",
			dataType: "text",
			data: "id=" + $("#id").val(),
			success: function(data){
				$("span").html(data);
			},
			error : function(){
				alert("에러 발생");
			}
		});
	}); // keyup() 함수 끝
	
	// 가입하기 버튼을 클릭했을 때 처리
	$("#btn").click(function(){
		$.ajax({
			url: "/16_Ajax/insert.do",
			dataType: "text",
			data: $("#inForm").serialize(), // 폼 데이터를 전송
			success: function(data){
				if(data > 0){
					alert("가입 완료");
					getData(); // 가입 완료 후 다시 전체 레코드를 화면에 출력.
					
					// input 태그에 입력된 내용을 지우는 작업
					$("input[type=text]").each(function(){
						$(this).val("");
					});
				} else {
					alert("가입 실패했습니다.");
				}
			},
			error: function(){
				alert("에러 발생");
			}
		});
	});
	
	// 삭제 버튼을 클릭했을 때
	// 삭제 버튼은 동적으로 생성된 요소이므로 on() 함수를 이용해야 함.
	$("table").on("click","#del", function(){
		$.ajax({
			url: "/16_Ajax/delete.do",
			dataType: "text",
			data: "no=" + $(this).attr("name"),
			success: function(data){
				if(data > 0){
					alert("회원이 삭제되었습니다.");
					getData();
				} else {
					alert("삭제 실패");
				}
			},
			error: function(){
				alert("에러 발생");
			}
		});
	});
	
	// onload 되면 바로 전체리스트를 가져와야 함.
	getData();
});