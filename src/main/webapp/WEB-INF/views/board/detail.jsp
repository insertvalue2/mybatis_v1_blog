<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<link rel="stylesheet" href="/css/board.css">
<br>
<br>
<div class="container">
	<div class="board--title">
		<h2>${boardData.title}</h2>
		<div class="board--info">
			<span>${boardData.createDate} (${boardData.username})</span> <span class="board-urlcopy">URL 복사</span> <input type="text"
				value="localhost:8080/board/detail/1" id="urlAddress" style="display: none;">
		</div>
		<c:if test="${isWriter eq true}">
			<div class="board--btns">
				<a type="button" href="/board/update-form/${boardData.id}" class="btn btn-warning">update</a>
				&nbsp;&nbsp;
				<a type="button" href="/api/board/delete/${boardData.id}" class="btn btn-danger">delete</a>
			</div>
		</c:if>
		<hr>
	</div>
	<div class="board--content">${boardData.content}</div>
</div>

<script>
	$(document).ready(function() {
		$(".board-urlcopy").bind("click", function() {
			var urlAddress = $('#urlAddress');
			urlAddress.css('display', 'block').select();
			document.execCommand("Copy");
			urlAddress.css('display', 'none');
			alert('URL 주소가 복사 되었습니다');
			return false;
		});
	});
</script>
<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>
