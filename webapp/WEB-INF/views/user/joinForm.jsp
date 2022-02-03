<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mysite4 joinForm</title>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/user.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-1.12.4.js"></script>

</head>

<body>
	<div id="wrap">

		<!-- //header,nav -->
		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>

		<div id="container" class="clearfix">
			<div id="aside">
				<h2>회원</h2>
				<ul>
					<li>회원정보</li>
					<li>로그인</li>
					<li>회원가입</li>
				</ul>
			</div>
			<!-- //aside -->

			<div id="content">

				<div id="content-head">
					<h3>회원가입</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>회원</li>
							<li class="last">회원가입</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<!-- //content-head -->

				<div id="user">
					<div id="joinForm">
						<form action="/mysite4/user/join" method="get">

							<!-- 아이디 -->
							<div class="form-group">
								<label  class="form-text" for="input-uid">아이디</label> 
								<input type="text" id="input-uid" name="id" value="" placeholder="아이디를 입력하세요">
								<button type="button" id="btnIdCheck">중복체크</button>
							</div>
							<div id="idAlert" value=""></div>

							<!-- 비밀번호 -->
							<div class="form-group">
								<label class="form-text" for="input-pass">패스워드</label> 
								<input type="text" id="input-pass" name="password" value="" placeholder="비밀번호를 입력하세요">
							</div>

							<!-- 이름 -->
							<div class="form-group">
								<label class="form-text" for="input-name">이름</label> 
								<input type="text" id="input-name" name="name" value="" placeholder="이름을 입력하세요">
							</div>

							<!-- 성별 -->
							<div class="form-group">
								<span class="form-text">성별</span> 
								
								<label for="rdo-male">남</label>
								<input type="radio" id="rdo-male" name="gender" value="male">
								 
								<label for="rdo-female">여</label>
								<input type="radio" id="rdo-female" name="gender" value="female">
							</div>

							<!-- 약관동의 -->
							<div class="form-group">
								<span class="form-text">약관동의</span> <input type="checkbox" id="chk-agree" value="" name=""> <label for="chk-agree">서비스 약관에 동의합니다.</label>
							</div>

							<!-- 버튼영역 -->
							<div class="button-area">
								<button type="submit" id="btn-submit">회원가입</button>
							</div>

						</form>
					</div>
					<!-- //joinForm -->
				</div>
				<!-- //user -->
			</div>
			<!-- //content  -->
		</div>
		<!-- //container  -->

		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!-- //footer -->

	</div>
	<!-- //wrap -->

</body>

<script type="text/javascript">
	
	/* 아이디 중복테스트 버튼 이벤트 */
	$("#btnIdCheck").on("click",function(){
		
		console.log("클릭");
		
		//데이터 모으기
		var idBox = {id : $("#input-uid").val()}; 

		$.ajax({
			//-------보낼때
			//요청할 컨트롤러 주소
			url : "${pageContext.request.contextPath}/user/idCheck",
			//주소창이 변하지 않기 때문에 post,get 방식 모두 동일 
			type : "post",
			//contentType : "application/json",
			data : idBox, 
			
			//-------받을때 
			dataType : "json",
			success : function(count){//json-->js로 변환되어 들어옴
				/*성공시 처리해야될 코드 작성*/
				console.log(count);
				
				if(count==1){
					$("#idAlert").html("<strong>이미 사용중인 아이디입니다</strong>");
					$("#input-uid").val("");
				}else{
					$("#idAlert").html("<strong>사용 가능한 아이디입니다</strong>");
				}

			},
			error : function(XHR, status, error) {
				csole.error(status + " : " + error);
			}
			
		})

	
	})
	
	
	//---------------------------------------------------------

	$("#btn-submit").on("click",function(){
		
		console.log("회원가입 버튼 클릭");
		
		 var id = $("#input-uid").val();
		var pw = $("#input-pass").val();
		
		
		if(id == ""){
			alert('아이디를 입력해 주세요');
			//하던 일 멈춰 stop
			return false;
		}
		if(pw == ""){
			alert('비밀번호를 입력해 주세요');
			//하던 일 멈춰 stop
			return false;
		}
		
		return true; 
		
	})
		
	//---------------------------------------------------------

	
</script>

</html>