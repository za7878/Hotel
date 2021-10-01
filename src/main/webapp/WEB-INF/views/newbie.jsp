<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
<div class=new>
<form method=POST action="/app/signin" id="frmSignin">
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   이 름: <input type=text name=realname><br><br>
   로그인 아이디: <input type=text name=userid><br><br>
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   비밀번호: <input type=password name=passcode1><br><br>
   비밀번호 확인: <input type=password name=passcode2><br><br>
   <input type=submit value="회원가입">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   <a href="/app/">취소</a>
   </form>
   </div>
</body>
<script src='https://code.jquery.com/jquery-3.5.0.js'></script>
<script>
$(document)
.on('submit','#frmSignin',function(){
	if($('input[name=realname]').val()==''){
		alert("이름을 입력하시오.");
		return false;
	}
	if($('input[name=userid]').val()==''){
		alert("로그인 아이디를 입력하시오.");
	}
	if($('input[name=passcode1]').val()==''){
		alert("비밀번호를 입력하시오.");
		return false;
	}
	if($('input[name=passcode1]').val()!=$('input[name=passcode2]').val()){
		alert('비밀번호가 일치하지 않습니다');
		return false;
	}
		return true;
});
</script>
<style>
	.new {
	   width: 100%;
    height: 50%;
    overflow: auto;
    margin: auto;
    /* position: absolute; */
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    text-align : center;
    padding:300px 0px 300px 0px;
    width:100%;
	}
	 body { background-color: #9dcc9e;}
</style>
</html>