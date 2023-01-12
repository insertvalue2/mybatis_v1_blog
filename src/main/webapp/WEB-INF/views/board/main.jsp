<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">
	<br />
	<div class="d-flex justify-content-end">
		<div style="width: 300px">
			<form class="d-flex" method="get" action="/">
				<input class="form-control me-2" type="text" placeholder="Search">
				<button class="btn btn-primary" type="submit">Search</button>
			</form>
		</div>
	</div>
	<br />

	<table class="table table-striped">
		<thead>
			<tr>
				<th>번호</th>
				<th>게시글제목</th>
				<th>작성자이름</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="boardItem" items="${boardList}">
				<c:set var="i" value="${i +1}"></c:set>
				<tr>
					<td><c:out value="${i}" /></td>
					<!-- 코드 수정 /auth  -->
					<td><a href="/auth/board/detail/${boardItem.id}">${boardItem.title}</a></td>
					<td>${boardItem.username}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<div class="d-flex justify-content-center">
		<ul class="pagination">
			<li class='page-item disabled'><a class="page-link" href="#">Prev</a></li>
			<li class='page-item'><a class='page-link' href="#">1</a></li>
			<li class='page-item'><a class="page-link" href="#">Next</a></li>
		</ul>
	</div>

</div>

<%@ include file="../layout/footer.jsp"%>
