<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<style>
.board--info {
	display: flex;
	margin-top: 10px;
	justify-content: space-between;
}

.board--info>span {
	color: gray;
	font-size: 12px;
	font-style: italic;
}

.board--content {
	margin: 10px;
}
</style>
<br>
<br>
<div class="container">

	<div class="board--title">
		<h2>${boardData.title}</h2>
		<div class="board--info">
			<span>${boardData.createDate} (${boardData.username})</span> <span class="board-urlcopy">URL 복사</span> <input type="text"
				value="localhost:8080/board/detail/1" id="urlAddress" style="display: none;">
		</div>
		<hr>
	</div>
	<div class="board--content">
		${boardData.content}
	</div>
</div>
<script>
	$(document).ready(function() {
		$(".board-urlcopy").bind("click", function() {
			var urlAddress = $('#urlAddress');
			urlAddress.css('display','block').select();
			document.execCommand("Copy");
			urlAddress.css('display','none');
			alert('URL 주소가 복사 되었습니다');
			return false;
		});
	});
</script>

<%@ include file="../layout/footer.jsp"%>
