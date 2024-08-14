<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/table.css" />
<link rel="stylesheet" href="/css/wrapper.css"/>
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="include/nav.jsp"></jsp:include>
	<div class = "container">
		<div class="detail-data-container">
	        <div class="wrapper">
	        	<div class="title">게시판종류생성</div>
	        	<div class="form">
			          <div class="inputfield">
			            <label for="boradCategory">게시판종류 : </label>
			            <input
			              id="boradCategory"
			              type="text"
			              value=""
			              placeholder="..."
			            />
			          </div>
			          <div class="inputfield">
			            <label for="boradCategoryDesc">게시판종류-설명 : </label>
			            <input
			              id="boradCategoryDesc"
			              type="text"
			              value=""
			              placeholder="..."
			            />
			          </div>
			          <div class="btn-area">
			            <a href="#" class="btn-cancel">취소</a>
			            <a id="contentSubmit" href="#" class="btn-success">등록</a>
			          </div>
		          </div>
	        </div>
      </div>
      
		<!-- 카테고리 검색 -->
		<div class="search">
			<label> <input id="searchBar" type="text" placeholder="게시판 종류 검색..." /> <input
			id="searchBarHidden" type="hidden" value="${search}" />
			</label>
		</div>
		<div class = "row">
			<div class"data-container">
				<div class="content-table">
	            <h2>게시판 생성</h2>
	            <button class="CategoryInsertBtn" id="userIsertBtn" type="button">
	              게시판 종류 생성
	            </button>
	          </div>
	          <table>
	          	<thead>
	          		<tr>
	          			<th>게시판 종류</th>
	          			<th>게시판 설명</th>
	          			<th>게시판 생성날짜</th>
	          		</tr>
	          	</thead>
	          	<tbody>
	          		<tr>
	          			<td></td>
	          			<td></td>
	          			<td></td>
	          		</tr>
	          	</tbody>
	          </table>
			</div>
		</div>
	</div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
	$("#CategoryInsertBtn").click(function () {
	    $(".detail-data-container").css("display", "block");
	  });
	$(".btn-cancel").click(function () {
	    $(".detail-data-container").css("display", "none");
	  });
	
	//게시판추가
	$("#contentSubmit").click(function () {
		if(confirm("게시판 종류를 추가하시겠습니까?")){
			var boradCategory = $("#boradCategory").val();
			var boradCategoryDesc = $("#boradCategoryDesc").val();
			
			if (boradCategory == "") {
	            alert("게시판 종류를 입력해 주십시오");
	            $("boradCategory").focus();
	            return false;
	          }
	          if (boradCategoryDesc == "") {
	            alert("게시판 설명(을)를 입력해 주십시오");
	            $("#boradCategoryDesc").focus();
	            return false;
	          }
	          
	          var jsonData = {
	        		  boradCategory: boradCategory,
	        		  boradCategoryDesc: boradCategoryDesc,
	          };
	          
	          $.ajax({
	              url: "/addcategory.do",
	              type: "POST",
	              contentType: "application/json", //서버에 json타입으로 요청
	              dataType: "json", //서버결과 json으로요청
	              data: JSON.stringify(jsonData),
	              success: function (reponse) {
	                if (reponse > 0) {
	                  //작성 화면 감추기
	                  $(".Category-popup").css("display", "none");
	                  //내가 작성한 글 지우기
	                  $("#Category").val("");
	                  $("#boradCategoryDesc").val("");

	                }
	              }, //success끝
	            }); //ajax끝
		}//if끝
	});
</script>	
</body>
</html>