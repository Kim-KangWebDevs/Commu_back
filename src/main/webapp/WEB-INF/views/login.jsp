<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>로그인</title>
</head>
<body>
	<div class="container">
		<div class="login-form">
		<form id="loginForm" method="post" action="/login">
			<div class = "loginText">
				<input id="userId" type="text" required>
				<label>UserId</label>
			</div>
			<div class = "loginText">
				<input id="userPw" type="password" required>
				<label>UserPw</label>
			</div>
			<input class="login-btn" type="butten" value="로그인" />
		</div>
	</div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">

	$('.login-btn').click(function(){
		if(isInputData()){
			isLogin()
		}
	  });
	
	$('.userPw').click(function(){
		if(key.keyCode == 13){
			if(isInputData()){
				isLogin()
			}			
		}
	  });
	
	function isInputData(){
		var userId = $('#userId').val();
		var userPassword = $('#userPassword').val();
		if (userId == '') {
			alert('아이디를 입력하세요~');
			$('#userId').focus();
			return false;
		}
		if (userPw == '') {
			alert('비밀번호를 입력하세요~');
			$('#userPw').focus();
			return false;
		}
		return true
	}
	
	function isLogin(){
		var jsonData = {
			userId : userId,
			userPw : userPw
		}
		$.ajax({
	          url : "/login",
	          type : "POST",
	          contentType : "application/json",//서버에 json타입으로 요청
	          dataType : "json",//서버결과 json으로요청
	          data : JSON.stringify(jsonData),
	          success : function(reponse){
	            if(reponse){
	              location.href = "/main";
	            }else{
	              alert('비밀번호 혹은 이름이 틀렸습니다.')
	            }
	          } 
	        })
	}
	
</script>
</body>
</html>