<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
<div class="Startlogin">
<form method=post action="/app/check_user" id="frmLogin">
<div class=aa>
   &nbsp;&nbsp;&nbsp;LoginId:<input type=text name=userid><br><br>
   PassWord:<input type="password" name="passcode"><br><br>
   <input type=submit name=btn value="로그인">&nbsp;
   <a href='/app/newbie'><input type=button value="회원가입"></a>
</div>
</div>

</form>
</body>
<script src='https:code.jquery.com/jQuery-3.5.0.js'></script>
<script>
$(document)
   .on('submit','#frmLogin',function(){
      let pstr=$.trim($('input[name=userid]').val());
      $('input[name=userid]').val(pstr);
      pstr=$.trim($('input[name=passcode]').val());
      $('input[name=passcode]').val(pstr);
      if($('input[name=userid]').val()==''){
         alert("로그인 아이디를 입력하시오.")
         return false;
      }
      if($('input[name=passcode]').val()==''){
         alert("비밀번호를 입력하시오.");
         return false;
      }
      return true;
   });
</script>
<style>
.Startlogin{
text-align : center;
   /*  padding: 300px; */
    max-width: 100%;
    height: 100%;
}
.aa { 
   border: solid 0px;
   padding: 200px 0px 200px 0px;
    width: 100%;
    height: 50%;
    overflow: auto;
    margin: auto;
    /* position: absolute; */
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
   /*  background-color: #ffffff; */
    /* box-shadow: 22px 0.4px 37.8px 24.2px rgb(52 52 52 / 3%); */
    background-image: url(C:/Users/LGZ36/Pictures/Saved Pictures/abc.png);
	    font-size: 20px;
}
 body { /* background-color: lightblue; */
 				background-image:url('https://cdn.pixabay.com/photo/2016/03/17/06/49/renovation-1262389_960_720.png');
 				width: 100%;
				height: 100vh;
				background-size: cover;
				background-position: center;
 				background-repeat:no-repeat;
 				
 }
</html>