<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/css/table.css" />
<link rel="stylesheet" href="/css/wrapper.css"/>

</head>

<body>
<!-- table -->
	<jsp:include page="include/nav.jsp"/>
    <div class="container">
    		<!-- 검색 -->
    		
		<div class="search">
			<label> <input id="searchBar" type="text" placeholder="유저 검색" /> <input
			id="searchBarHidden" type="hidden" value="${search}" />
			</label>
		</div>
		
	    <div class="row">
	        <div class="data-container">
	          <div class="content-table">
	            <h2>유저</h2>
	            <button class="insertBtn" id="userIsertBtn" type="button">
	              유저 추가
	            </button>
	          </div>
	          <table>
	            <thead>
	              <tr>
	                <th>유저 번호</th>
	                <th>유저 이름</th>
	                <th>비밀 번호</th>
	                <th>닉네임</th>
	                <th>이메일</th>
	                <th>가입 날짜</th>
	              </tr>
	            </thead>
	            <tbody>
	            	<c:choose>	 		
		 				<c:when test="${fn:length(pageHelper.list) > 0}">
		 					<c:forEach items="${pageHelper.list}" var="item">
	                            <tr id="userNo${item.userNo}" onclick ="getUsersData(${item.userNo})">	
					    			<td>${item.userNo}</td>
					    			<td>${item.userId}</td>
					    			<td>${item.userPw}</td>
	                                <td>${item.userChr}</td>
					    			<td>${item.userEmail}</td>
					    			<td>${item.userRegdate}</td>    							    			
	  							</tr>
	                        </c:forEach>
	                    </c:when>
	                </c:choose> 
	            </tbody>
	          </table>  	
		        <div class="pagination">
		            <c:if test="${pageHelper.hasPreviousPage}">
		                <a onclick="getUsersList(${pageHelper.pageNum-1},10)" href="#">Previous</a>
		            </c:if>
		            <c:forEach items="${pageHelper.navigatepageNums}" var="pageNum">
		                <a id="pageNum${pageNum}"
		                    onclick="getUsersList(${pageNum},10)"> ${pageNum}</a>
		            </c:forEach>
		            <c:if test="${pageHelper.hasNextPage}">
		                <a onclick="getUsersList(${pageHelper.pageNum+1},10)" href="#">Next</a>
		            </c:if>
		            <input id="nowPageNum" type="hidden" value="${pageHelper.pageNum}">
		        </div>
	        </div>	        	        
		    <div class="detail-data-container">
				<div class="wrapper">
					<div class="title">User정보</div>
					<div class="form">
						<div class="inputfield">
							<label>유저 ID :</label> <input id="userId" type="text"
								class="input" />
						</div>
						<div class="inputfield">
							<label>유저 Password :</label> <input id="userPw" type="text"
								class="input" />
						</div>
						<div class="inputfield">
							<label>유저 Email :</label> <input id="userEmail" type="text"
								class="input" readonly />
						</div>
						<div class="inputfield">
							<label>유저 닉네임 :</label> <input id="userChr" type="text"
								class="input" />
						</div>
						<div class="inputfield">
							<label>가입날짜 :</label> <input id="createAt" type="text"
								class="input" readonly />
						</div>
						<div class="inputfield" id="selectBoxDiv">
									<label>Authority</label>
									<div class="custom_select">
										<select>
											<option value="null">SELECT</option>
											<option value="guest">role_guset</option>
											<option value="user">role_user</option>
											<option value="admin">role_admin</option>
										</select>
									</div>
									<input id="userAuth" type="hidden" value="${auth.get(0).getRoleName()}">
								</div>
						<div class="inputfield">
							<label style="font-size : 30px">file :</label>
							<div id="fileName" style="width:100%; height: 230px; background-color: black;"></div>
						</div>
						<div class="inputfield">
							<input id="userNoHidden" type="hidden" /> <input
								id="userUpdate" type="submit" value="수정하기" class="btn" />
						</div>
					</div>
				</div>
			</div>
	    </div>
	    
    </div>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"
		integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
		crossorigin="anonymous"></script>	
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script type="text/javascript">

	selectBoxDisplay();
	getPageNum();
	//페이지버튼누를시 이동

	//페이징
	function getPageNum() {
		var pageNum = $('#nowPageNum').val();
		$("#pageNum" + pageNum).css("background-color", "#e3e6f0");
		$("#pageNum" + pageNum).css("color", "#fff");
	}
		
	//유저리스트
	function getUsersList(pageNum, pageSize) {
	    var search = $("#searchBarHidden").val();
		var controllUrl =
			"/user/search?pageNum="+pageNum+"&pageSize="+pageSize;
	    if (search != "null") {
	    	controllUrl = 
	    		"/user/search?search="+search+"&pageNum="+pageNum+"&pageSize="+pageSize;
	    }
		location.href = controllUrl
	}
	//유저 정보가져오기
	function getUsersData(userNo){
		$('#userNoHidden').val("usertNo"+userNo);	
		$.ajax({
			url : "/user/"+userNo,
			data : "GET",
			dataType : "json",
			success : function(response){
				console.log(response);
				$('#userId').val(response.userId);
				$('#userPw').val(response.userPw);
				$('#userChr').val(response.userChr);
				$('#userEmail').val(response.userEmail);
				$('#userRegdate').val(response.userRegdate);
				$('#fileName').val(response.fileName);
			}
		})
	}
	
	// 특정 계정 수정하기
	function userUpdate(){
		var userNo = $('#userNoHidden').val().trim();
		var userId = $('#userId').val().trim();
		var userPw = $('#userPw').val().trim();
		var userChr = $('#userChr').val().trim();
		var userEmail = $('#userEmail').val().trim();
		var regEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;//이메일 정규 표현식
		var fileName = $('#fileName').val().trim();
		
		var jsonData = {
				userNo : userNo,
				userId : userId,
				userPw : userPw,
				userChr : userChr,
				userEmail : userEmail,
				fileName : fileName
				
		}
		//특정 계정 수정 중 input값 체크 구간 start
		// 빈칸 여부 확인 start	
		if(userId !== "" && userPw !=="" && userChr !== "" && userEmail !== ""){
		}else{
			alert("정보를 입력해주세요!")
			return false;
		}
		// 빈칸 여부 확인 end	
				
		// 비밀번호 길이 확인 start
		if(userPw !== "" && userPw !== ""){
			if(userPw.length < 6 && userPw.length < 6){
				alert("비밀번호 길이는 6글자 이상 작성해주세요!")
				return false;
			}
		}
		// 비밀번호 길이 확인 end
		// 이메일 정규 표현식 start
		if (regEmail.test(userEmail) === true) {
	    	console.log("email OK!")
	   	}
		else{
			alert('이메일 형식이 아닙니다.');
			$('#userEmail').focus();
		    return false;
		}
		// 이메일 정규 표현식 end		
		if(confirm("수정하시겠습니까?")){
		//ajax start
			$.ajax({
				url : '/user/update',
				type : 'PATCH',
				contentType : 'application/json',
				dataType : 'json', 
				data : JSON.stringify(jsonData),
				success: function(response){
					if(response > 0){
						console.log(response)
						}
					}
				}) 
			alert("수정되었습니다.")
		}
	}
	
	
	$('#searchBar').keyup(function(key) {
		var pageSize = 10;
		var pageNum = $('#nowPageNum').val();
		if (key.keyCode == 13) {
			var search = $('#searchBar').val().trim();//input에 작성한 작성자를 가져옴
			if (search != '') {
				location.href="/user/search?search="+search+"&pageNum="+pageNum+"&pageSize="+pageSize
			}
		}
	});
	
	function selectBoxDisplay(){
		var userAuth = $('#userAuth').val();
		if(userAuth !== "role_admin"){
			$('#selectBoxDiv').css("display","none");
		}
	}
</script>
</body>
</html>