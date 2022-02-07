<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/gallery.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.js"></script>

</head>


<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- //header -->
		<!-- //nav -->

		<c:import url="/WEB-INF/views/include/asideGallery.jsp"></c:import>
		<!-- //aside -->


		<div id="content">

			<div id="content-head">
				<h3>갤러리</h3>
				<div id="location">
					<ul>
						<li>홈</li>
						<li>갤러리</li>
						<li class="last">갤러리</li>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			<!-- //content-head -->


			<div id="gallery">
				<div id="list">
					
					<c:if test="${sessionScope.authUser != null}">
						<button id="btnImgUpload">이미지올리기</button>
						<div class="clear"></div>
					</c:if>
					
					<ul id="viewArea">
						
						<!-- 이미지반복영역 -->
						<c:forEach items="${requestScope.lmgList}" var="lmgVo">
							<li>
								<div class="view">
									<img class="imgItem" src="${pageContext.request.contextPath}/upload/${lmgVo.saveName}" data-no="${lmgVo.no}" data-sessionno="${sessionScope.authUser.no}">
									<div class="imgWriter">작성자: <strong>${lmgVo.name}</strong></div>
								</div>
							</li>
						</c:forEach>
						<!-- 이미지반복영역 -->
						
					</ul>
				</div>
				<!-- //list -->
			</div>
			<!-- //board -->
		</div>
		<!-- //content  -->
		<div class="clear"></div>

		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!-- //footer -->

	</div>
	<!-- //wrap -->

	<!-- 이미지등록 팝업(모달)창 -->
	<div class="modal fade" id="addModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">이미지등록</h4>
				</div>
				
				<form method="post" action="${pageContext.request.contextPath }/gallery/upload?userNo=${authUser.no}" enctype="multipart/form-data">
					<div class="modal-body">
						<div class="form-group">
							<label class="form-text">글작성</label>
							<input id="addModalContent" type="text" name="content" value="" >
						</div>
						<div class="form-group">
							<label class="form-text">이미지선택</label>
							<input id="file" type="file" name="file" value="" >
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn" id="btnUpload">등록</button>
					</div>
				</form>

			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	


	<!-- 이미지보기 팝업(모달)창 -->
	<div class="modal fade" id="viewModal">
		<div class="modal-dialog" >
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">이미지보기</h4>
				</div>
				<div class="modal-body">
					
					<div class="formgroup" >
						<img id="viewModelImg" src ="${pageContext.request.contextPath}/upload/${getVo.saveName}"> <!-- ajax로 처리 : 이미지출력 위치-->
					</div>
					
					<div class="formgroup">
						<p id="viewModelContent">${getVo.content}</p>
					</div>
					
				</div>
				<form method="get" action="delete">
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
						<span id="btnDel"></span>
					</div>
					<input id="imgNo" type="text" value="">
				</form>
				
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->	


</body>

<script type="text/javascript">

	//--이미지 등록 버튼 ----------------------------
	$('#btnImgUpload').on('click',function(){
		
		console.log('이미지 등록 클릭1');
		//팝업창
		$('#addModal').modal('show');
		
	})
	
	//--이미지 보기 버튼 ----------------------------
	$('.imgItem').on('click',function(){
		
		console.log('이미지 보기 클릭2');
		
		var no = $(this).data('no');
		var sessionNo = $(this).data('sessionno');
		
		$('#imgNo').val(no);

		//해당 이미지 가져오기 요청
		$.ajax({
			//-------보낼때
			//요청할 컨트롤러 주소
			url : "${pageContext.request.contextPath }/gallery/view",
			//주소창이 변하지 않기 때문에 post,get 방식 모두 동일 
			type : "post",
			//contentType : "application/json",
			data : {no:no},
			
			//-------받을때 
			dataType : "json",
			success : function(getVo){//json-->js로 변환되어 들어옴
				/*성공시 처리해야될 코드 작성*/
				console.log(getVo);
			
				if(sessionNo == getVo.userNo){
					$('#btnDel').html(' <button type="button" class="btn btn-danger" id="btnDel">삭제</button> ');
				}else{
					$('#btnDel').html('');
				}
			},
			error : function(XHR, status, error) {
				sole.error(status + " : " + error);
			}
			
		});
		
		
		//팝업창
		$('#viewModal').modal('show');
		
	});
	
	
	//--이미지 삭제하기 ----------------------------
	
	
	
	
	
	
	

</script>




</html>

