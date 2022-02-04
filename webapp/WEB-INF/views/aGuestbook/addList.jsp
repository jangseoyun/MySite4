<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mysite4 addList</title>

<link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.js"></script>

</head>

<body>
	<div id="wrap">

		<!-- //header,nav -->
		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>

		<div id="container" class="clearfix">
			<div id="aside">
				<h2>방명록</h2>
				<ul>
					<li><a href="${pageContext.request.contextPath}/guest/addList">일반방명록</a></li>
					<li><a href="${pageContext.request.contextPath}/api/guest/addList">ajax방명록</a></li>
				</ul>
			</div>
			<!-- //aside -->

			<div id="content">

				<div id="content-head" class="clearfix">
					<h3>일반방명록</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>방명록</li>
							<li class="last">일반방명록</li>
						</ul>
					</div>
				</div>
				<!-- //content-head -->

				<div id="guestbook">
					
						<table id="guestAdd">
							<colgroup>
								<col style="width: 70px;">
								<col>
								<col style="width: 70px;">
								<col>
							</colgroup>
							<tbody>
								<tr>
									<th><label class="form-text" for="input-uname">이름</label></th>
									<td><input id="input-uname" type="text" name="name" value=""></td>
									<th><label class="form-text" for="input-pass">패스워드</label></th>
									<td><input id="input-pass" type="password" name="password" value=""></td>
								</tr>
								<tr>
									<td colspan="4"><textarea name="content" cols="72" rows="5"></textarea></td>
								</tr>
								<tr class="button-area">
									<td colspan="4" class="text-center">
										<button id="btnSubmit2" type="submit">등록</button>
									</td>
								</tr>
							</tbody>
						</table>
					
					<!-- //guestRead -->
					<div id = "listArea">
						<!-- 리스트출력할곳 -->
					</div>
					<!-- //guestRead -->
					

				</div>
				<!-- //guestbook -->

			</div>
			<!-- //content  -->
		</div>
		<!-- //container  -->

		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!-- //footer -->
	</div>
	<!-- //wrap -->

	<!-- 삭제 모달창 -------------------------------------->
	<div id="delModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">비밀번호 입력 모달창</h4>
				</div>
			    <div class="modal-body">
					비밀번호: 
					<input id="modalPassword" type="password" name="password" value="">	<br>
					<input id="modalNo" type="text" name="no" value="">        
			    </div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
					<button id="modalBtnDel" type="button" class="btn btn-danger">삭제</button>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	<!-- 삭제 모달창 -------------------------------------->
	
</body>


<script type="text/javascript">
	
	
	//문서가 준비되면--------------------------------------------------
	//1.로딩되기 전에 요청 
	$(document).ready(function(){
		
		/* 리스트 그리기 */
		fetchList();
		
		console.log("리스트요청");
		
	});
	
	//2. 등록 //저장버튼이 클릭될 때 이벤트 --> 파라미터 방식 요청-------------------
	$("#btnSubmit").on("click",function(){
		
		console.log("버튼클릭");
		
		//폼에 데이터를 모아야한다.
		//var name = $("#input-uname").val(); //이름
		var password = $("#input-pass").val(); //패스워드
		var content = $("[name='content']").val(); //컨텐츠
	
		//js객체 만들기
		var guestbookVo = {
			name : $("#input-uname").val(),
			password : password,
			content : content				
		};
		
		//확인
		console.log(guestbookVo);
		
		//요청 --> 파라미터 방식 요청
		$.ajax({
			//----보낼때
			url : "${pageContext.request.contextPath }/api/guest/write",
			type : "post",
			//contentType : "application/json",
			
			//주소 파라미터로 데이터 전달 {필드명:값}
			//data :{name : name, password : password,content : content},
			data : guestbookVo,
			
			//----받을때
			dataType : "json",
			success : function(guestbookVo){
				/*성공시 처리해야될 코드 작성*/
				console.log(guestbookVo);
				render(guestbookVo, 'up');
				
				//입력화면 초기화
				$("#input-uname").val("");
				$("#input-pass").val("");
				$("[name='content']").val("");
			},
			error : function(XHR, status, error) {
				sole.error(status + " : " + error);
			}
		});
		
	});
	
	
	//저장 버튼이 클릭될 때 ---> json형식으로 요청-----------------------------
	$('#btnSubmit2').on('click',function(){
		
		console.log('json 전송 클릭');
		
		//폼에 데이터를 모아야한다.
		//var name = $("#input-uname").val(); //이름
		var password = $("#input-pass").val(); //패스워드
		var content = $("[name='content']").val(); //컨텐츠
	
		//js객체 만들기
		var guestbookVo = {
			name : $("#input-uname").val(),
			password : password,
			content : content				
		};
		
		//확인
		console.log(guestbookVo);
		
		//요청 --> 파라미터 방식 요청
		$.ajax({
			//----보낼때
			url : "${pageContext.request.contextPath }/api/guest/write2",
			type : "post",
			contentType : "application/json",
			
			//주소 파라미터로 데이터 전달 {필드명:값}
			//js 객체를 json 형식으로 변경해주는 것 
			data : JSON.stringify(guestbookVo),
			
			//----받을때
			dataType : "json",
			success : function(guestbookVo){
				/*성공시 처리해야될 코드 작성*/
				console.log(guestbookVo);
				render(guestbookVo, 'up');
				
				//입력화면 초기화
				$("#input-uname").val("");
				$("#input-pass").val("");
				$("[name='content']").val(""); 
			},
			error : function(XHR, status, error) {
				sole.error(status + " : " + error);
			}
		});
		
		
	})

	
	//삭제 팝업 버튼을 눌렀을때--------------------------------------------------
	//부모에 걸어주고 위임해준다.
	$("#listArea").on("click",".btnDelPop",function(){
		//데이터 수집
		var $this = $(this);
		var no = $this.data("no");
					
		console.log($this);
		//초기화
		$('#modalPassword').val('');
		$('#modalNo').val(no);
		//팝업창
		$('#delModal').modal('show');
	});
	
	
	//● 모달창의 삭제버튼을 클릭했을 때--------------------------------------------------
	$('#modalBtnDel').on('click',function(){
		
		console.log('모달창 삭제버튼 클릭')
		
		//1. 데이터 수집(객체로 만들어서 보내주거나, 직접 넣기)
		var pw = $('#modalPassword').val();
		var no = $('#modalNo').val();
		
		var delInfoVo = {
			no : no,
			password : pw
		}

		console.log(delInfoVo);
		//1-2. ajax 요청 no & password
		 $.ajax({
      
	        url : "${pageContext.request.contextPath }/api/guest/remove",      
	        type : "post",
	        //contentType : "application/json",
	        data : delInfoVo,
	
	        dataType : "json",
	        success : function(state){
	
				if(state=='success'){
			         //2. 화면에서 변경되는 부분 반영
						
						//  해당 테이블 html 삭제
						$('#t'+no).remove();
						//  모달창 닫기
						$('#delModal').modal('hide');
						
				}else {
					//$('#delModal').modal('hide');
					alert("비밀번호를 확인하세요");
					$('#modalPassword').val('');
				}
				
	        },
	        error : function(XHR, status, error) {
	           console.error(status + " : " + error);
	        }
	   	}); 
		
		
	})

	
	//리스트 출력(그리기)--------------------------------------------------
	function fetchList(){
		
		$.ajax({
			//-------보낼때
			//요청할 컨트롤러 주소
			url : "${pageContext.request.contextPath }/api/guest/list",
			//주소창이 변하지 않기 때문에 post,get 방식 모두 동일 
			type : "post",
			/* contentType : "application/json",
			data : {name: "홍길동"}, */
			
			//-------받을때 
			dataType : "json",
			success : function(guestbookList){//json-->js로 변환되어 들어옴
				/*성공시 처리해야될 코드 작성*/
				console.log(guestbookList);
				//console.log(guestbookList[0].name);
				
				//객체 리스트 돌리기 (화면 출력을 시킴!)
				for(var i = 0; i<guestbookList.length; i++){
					//그리기(guestbookList);
					render(guestbookList[i], 'down'); // 그리기
				}

			},
			error : function(XHR, status, error) {
				sole.error(status + " : " + error);
			}
			
		});
		
	}
	
	//리스트 그리기(html)--------------------------------------------------
	function render(guestbookVo, updown){//guestbookList
		
		var str = '';
		str += ' <table id="t'+guestbookVo.no+'" class="guestRead"> ';
		str += '	<colgroup>';
		str += ' 		<col style="width: 10%;"> ';
		str += ' 		<col style="width: 40%;"> ';
		str += ' 		<col style="width: 40%;"> ';
		str += ' 		<col style="width: 10%;"> ';
		str += '	</colgroup>';
		str += '	<tr> ';
		str += ' 		<td>'+guestbookVo.no+'</td> ';
		str += ' 		<td>'+guestbookVo.name+'</td> ';
		str += ' 		<td>'+guestbookVo.regDate+'</td> ';
		str += ' 		<td><button class="btnDelPop" type="button" data-no="'+guestbookVo.no+'">삭제</button></td> ';
		str += '	</tr> ';
		str += ' 	<tr> ';
		str += ' 		<td colspan=4 class="text-left">'+guestbookVo.content+'</td> ';
		str += ' 	</tr> ';
		str += ' </table> ';
		
		if(updown == 'down'){
			$("#listArea").append(str); // id안에 html을 넣어라
		}else if(updown == 'up'){
			$("#listArea").prepend(str);
		} else{
			console.log("방향오류")
		}
	
	};
	
</script>

</html>