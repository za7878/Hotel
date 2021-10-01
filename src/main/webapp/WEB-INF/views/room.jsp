<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예약 관리</title>
</head>
<%-- <%
   String loginid=(String)session.getAttribute("loginid");
   out.println(loginid);
   if(!loginid.equals("jyw")){
     response.sendRedirect("http://localhost:8080/app/login");
   }
   %> --%>
<body>

    <a href="logout"><h4 style="margin: 0px 15px;">로그아웃</h4></a>
   <div class="table">
   <h3><a>객실관리</a>    <a style="cursor: pointer;" href="/app/booking">예약관리</a></h3>
    <p class="room">객실목록</p>
    <div class="bor">
    <p class="just">
        <select size="20" id="selRoom">
       <c:forEach items="${list}" var="room">
         <option value="${room.roomcode}">
         ${room.roomname},${room.typename},${room.howmany},${room.howmuch}</option>
       </c:forEach>
    </select>
        </div>
    </p>
   </div>


    <p>객실 이름</p>
    <input type="search" name=rooms id="rooms">
    <input type=hidden id=roomcode>
    <input type="button" name="search" value="찾기">
    <br>
    <p>객실 분류</p>
    <select size="10" id="roomtype">
    <c:forEach items="${roomtype}" var="roomtype">
<%--          <option>${roomtype.typecode},${roomtype.name}</option> --%>
         <option value="${roomtype.typecode}">${roomtype.name}</option>
       </c:forEach>
    </select>
   
    <p>숙박 가능 인원</p>
    <input type="number" name="human" id="human">명
    <p>1박 요금</p>
    <input type="money" name="money" id="money">원
   
    <div class="btn">
    <input type="button" name="upDate" id="btnAdd" value="등록">
        <input type="button" name="clear" id="no" value="취소">
        <input type="button" name="delete" id="delete" value="삭제">
    </div>

    <style>
    h4 {
    text-align: right;
    }
li{
    list-style: none;
    margin: 20px 20px;
    text-align: left;
}
.li2 {
    padding: 30px;
}
.li3 {
    text-align: center;
    padding: 20px;
}
.Go {
    border: 1px solid;
}
.bor {
    margin: 0px 300px 0px 0px;
}
.btn{
    margin: 10px 10px 10px 520px;
}
.bb {
    float: left;
    /* border: 1px solid; */
    padding: 20px;
    margin: -200px 50px 150px 20px;
}
.def {
    border: 1px solid;
    display: flow-root;
    margin: 0px 50px 0px 20px;
}
.table{
      margin: 0px 200px 50px 300px;
    float: left;
        width: 100%;
    max-width: 200px;
}

.table3{
    border: 1px solid;
    box-sizing: border-box;
    padding: 5px;
    margin: 10px;
    float:left;
}
.table4{
    margin: 0px 200px 0px 210px;
    float:left;
}
body {
background-image:url('https://cdn.pixabay.com/photo/2016/04/11/10/02/watercolour-1321645_960_720.jpg');
width: 100%;
	height: 100vh;
	background-size: cover;
	background-position: center;
background-repeat:no-repeat;
}
.room {
    font-weight: bold;
    font-size: 18px;
}
</style>
</body>
<script src='https://code.jquery.com/jquery-3.5.0.js'></script>
<script>
$(document)
.ready(function(){
   $.post("http://localhost:8080/app/getRoomList",{},function(result){
      console.log(result);
      $.each(result,function(ndx,value){
         str='<option value="'+value['roomcode']+'">'+value['roomname']+','
         +value['typename']+','+value['howmany']+','+value['howmuch']+'</option>';
      $('#selRoom').append(str);
      // <option value="2">백두산,Suite Room,8,500000</option>
      });
   },'json');
})

.on('click','#selRoom option',function(){
   let str=$(this).text();
   let ar=str.split(',');
   $('#rooms').val(ar[0]);
   $('#roomtype option:contains("'+ar[1]+'")').prop('selected','true');
   $('#human').val(ar[2]);
   $('#money').val(ar[3]);
   let code=$(this).val();
   $('#roomcode').val(code);
   return false;
})
.on('click','#no',function(){
   $('#rooms,#roomtype,#human,#money').val('');
   return false;
})
.on('click','#delete',function(){
   $.post('http://localhost:8080/app/deleteRoom',{roomcode:$('#roomcode').val()},
         function(result){
      console.log(result);
      if(result=="ok"){
         $('#no').trigger('click'); //입력란 비우기
         $('#selRoom option:selected').remove(); //room리스트에서 제거.
      }
   },'text');
   return false;
})
.on('click','#btnAdd',function(){
   let roomname=$('#rooms').val();
   let roomtype=$('#roomtype').val();
   let howmany=$('#human').val();
   let howmuch=$('#money').val();
   //validation(유효성 검사)
   if( roomname=='' || roomtype=='' || howmany=='' || howmuch=='') {
      alert("누락된 값이 있습니다.");
      return false;
   }
   let roomcode=$('#roomcode').val();
   if(roomcode=='') { //insert
      $.post('http://localhost:8080/app/addRoom',
         {roomname:roomname,roomtype:roomtype,howmany:howmany,howmuch:howmuch},
      function(result){
         if(result=='ok'){
            location.reload();
               }
            },'text');
      } else { //update
      $.post('http://localhost:8080/app/updateRoom',
            {roomcode:roomcode,roomname:roomname,
            roomtype:roomtype,howmany:howmany,howmuch:howmuch},
            function(result){
               if(result=='ok'){
                  location.reload();
               }
            },'text');
      }
   })
</script>
</html>