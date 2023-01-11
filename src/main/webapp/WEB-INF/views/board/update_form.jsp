<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../layout/header.jsp"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<br><br>
<div class="container">
	<h2>블로그 글 수정하기</h2>
	<br>
	<form>
		<div class="mb-3 mt-3">
			<input type="hidden" name="boardId" id="board--id" value="${boardData.id}">
			<input type="hidden" name="userId" id="user--id" value="${userId}">
			<input type="text" class="form-control" placeholder="Enter title" name="title" id="title" value="${boardData.title}">
		</div>
		<div class="mb-3">
			<textarea class="form-control" rows="8" name="content" id="content" >${boardData.content}</textarea>
		</div>
		<button type="button" id="board--update" class="btn btn-primary">글수정완료</button>
	</form>
</div>

<script type="text/javascript" src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>

