<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
</head>
<body>
	<h2 style="text-align:center">등록 페이지</h2>
	<form style="text-align:center" name="frm">
		<div>
			<input name="etitle" id="etitle" size=80 placeholder="제목을 입력해주세요.">
		</div>
		<div style="margin-top:15px">
			<input style="height:300px" size=80 name="econtent" id="econtent" placeholder="내용을 입력해주세요.">
		</div>
		<div style="margin-top:15px">
			<input size=80 name="ewriter" id="ewriter" value="${aid}" placeholder="작성자를 입력해주세요.">
		</div>
		<div>
			<button type="submit">등록</button>
			<button type="button"><a href="/event/list?page=1&num=6&searchType=&keyword=">뒤로가기</a></button>
		</div>
	</form>
</body>
<script>
	$(frm).on("submit",function(e){
		e.preventDefault();
			
			var etitle = $("#etitle").val();
			var econtent = $("#econtent").val();
			var ewriter = $("#ewriter").val();

			if(etitle == ""){
				alert("제목을 입력하세요!")
				document.frm.etitle.focus();
				return;
			}
			if(econtent == ""){
				alert("내용을 입력하세요!")
				document.frm.econtent.focus();
				return;
			}
			if(ewriter == ""){
				alert("작성자를 입력하세요!")
				document.frm.ewriter.focus();
				return;
			}
			
			
			//입력한 데이터를 전송
			$.ajax({
				type:"post",
				url:"/api/event",
				data:{
					etitle,
					econtent,
					ewriter
				}
			}).done(function(){
				console.log("등록 성공");
				location.href="/event/list?page=1&num=6&searchType=&keyword="
			})

	});
</script>
</html>