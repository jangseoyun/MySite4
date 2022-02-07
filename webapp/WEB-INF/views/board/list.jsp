<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board list</title>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">

</head>


<body>
	<div id="wrap">

		<!-- //header, nav -->
		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>

		<div id="container" class="clearfix">
			<div id="aside">
				<h2>게시판</h2>
				<ul>
					<li><a href="${pageContext.request.contextPath}/board/list">일반게시판</a></li>
					<li><a href="${pageContext.request.contextPath}/board/replyList">댓글게시판</a></li>
				</ul>
			</div>
			<!-- //aside -->

			<div id="content">

				<div id="content-head">
					<h3>게시판</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>게시판</li>
							<li class="last">일반게시판</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<!-- //content-head -->

				<div id="board">
					<div id="list">
						<form action="" method="">
							<div class="form-group text-right">
								<input type="text">
								<button type="submit" id=btn_search>검색</button>
							</div>
						</form>
						<table>
							<thead>
								<tr>
									<th>번호</th>
									<th>제목</th>
									<th>글쓴이</th>
									<th>조회수</th>
									<th>작성일</th>
									<th>관리</th>
								</tr>
							</thead>

							<c:forEach items="${pMap.boardList}" var="boardVo">
								<tbody>
									<tr>
										<td>${boardVo.bNo}</td>
										<td class="text-left">
											<a href="${pageContext.request.contextPath}/board/read?bNo=${boardVo.bNo}">${boardVo.title}</a>
										</td>
										<td>${boardVo.uName}</td>
										<td>${boardVo.hit}</td>
										<td>${boardVo.regDate}</td>
										<c:if test="${sessionScope.authUser.no == boardVo.userNo}">
											<td><a href="${pageContext.request.contextPath}/board/delete?bNo=${boardVo.bNo}">[삭제]</a></td>
										</c:if>
									</tr>							
								</tbody>
							</c:forEach>	
									
						</table>

						<div id="paging">
							<ul>
								<c:if test="${pMap.prev == true}">
									<li><a href="${pageContext.request.contextPath}/board/list2?crtPage=${pMap.startPageBtnNo-1}">◀</a></li>								
								</c:if>

								<c:forEach begin="${pMap.startPageBtnNo}" end="${pMap.endPageBtnNo}" step="1" var="page">
									<li class="active">
										<a href="${pageContext.request.contextPath}/board/list2?crtPage=${page}">${page}</a>
									</li>
								</c:forEach>
								
								<c:if test="${pMap.next == true}">
									<li><a href="${pageContext.request.contextPath}/board/list2?crtPage=${pMap.endPageBtnNo+1}">▶</a></li>								
								</c:if>
							</ul>


							<div class="clear"></div>
						</div>
						
						<c:if test="${sessionScope.authUser != null}">
							<a id="btn_write" href="${pageContext.request.contextPath}/board/writeForm?uNo=${sessionScope.authUser.no}">글쓰기</a>
						</c:if>

					</div>
					<!-- //list -->
				</div>
				<!-- //board -->
			</div>
			<!-- //content  -->

		</div>
		<!-- //container  -->


		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!-- //footer -->
	</div>
	<!-- //wrap -->

</body>

</html>
