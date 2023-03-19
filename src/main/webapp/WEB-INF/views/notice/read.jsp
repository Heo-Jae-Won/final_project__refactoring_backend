<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공지사항</title>

<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
</head>
<style>
body {
	background: #eee;
}

textarea{
resize: none;
}

#button1{
margin-top:30px;
}
</style>
<body>
	<h2 style="text-align: center">공지사항</h2>

	<form style="text-align: center" name="frm">
		<div>
			제목 ㅡ <input name="ntitle" value="${noticeDto.ntitle}" id="ntitle" size=80
				placeholder="제목을 입력해주세요.">
		</div>
		
		<div style="margin-top: 15px">
			<textarea  cols=100 rows=10 name="ncontent" id="ncontent"
				placeholder="내용을 입력해주세요.">"${noticeDto.ncontent}"</textarea>
		</div>
		<input type="hidden" name=nwriter value="${noticeDto.nwriter}" /> <input
			type="hidden" name=ncode value="${noticeDto.ncode}" />
		<div id="button1">
			<button type="submit" id="btnSave">수정하기</button>
			<button style='margin-left:70px' type="button" id="back">뒤로가기</button>
		</div>
	</form>
</body>
<script>
	$("#back").on("click", function() {
		location.href = "/notice/list?page=1&num=6&searchType=&keyword=";
	});

	$(frm).on("submit", function(e) {
		e.preventDefault();
		var ntitle = $("#ntitle").val();
		var ncontent = $("#ncontent").val();
		var nwriter=$(frm.nwriter).val();
		var ncode=$(frm.ncode).val();
		
		var data={
			ntitle:ntitle,
			ncontent:ncontent,
			nwriter:nwriter,
			ncode:ncode
		}
		if (ntitle == "") {
			alert("제목을 입력하세요!")
			ntitle.focus();
			return;
		}
		if (ncontent == "") {
			alert("내용을 입력하세요!")
			ncontent.focus();
			return;
		}
		
		if(!confirm("정말로 수정하시겠습니까?")) 
			return;
		
		//입력한 데이터를 전송
		$.ajax({
		type:"patch",
		url:"/api/notice",
		data:JSON.stringify(data),
		contentType: "application/json; charset=utf-8"
		}).done(function(){
			alert("수정 성공");
		})
	});
</script>
</html>