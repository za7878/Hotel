<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method=get action="/app/login">
	<p>이름:${pl.name}</p>
	<p>로그인 아이디:${pl.userid}</p>
	<p>비밀번호:${pl.passcode1}</p>
	<p>비밀번호 확인:${pl.passcode2}</p>
	<p>모바일: ${pl.mobile}</p>
	<input type=submit value="회원가입 완료">
	</form>
</body>
</html>