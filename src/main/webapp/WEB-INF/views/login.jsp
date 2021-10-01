<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
<form method=post action="/app/check_user">
	LoginId:<input type=text name=userid><br>
	PassWord:<input type="password" name=passcode1><br>
	<input type=submit name=btn value="확인">&nbsp;
	<input type=reset value="취소">
	<a href="/app/newbie">회원가입</a>
</form>
</body>
</html>