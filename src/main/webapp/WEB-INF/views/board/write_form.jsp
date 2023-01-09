<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../layout/header.jsp"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<br><br>
<div class="container">
	<h2>블로그 글쓰기</h2>
	<br>
	<form>
		<div class="mb-3 mt-3">
			<input type="text" class="form-control" placeholder="Enter title" name="title" id="title">
		</div>
		<div class="mb-3">
			<textarea class="form-control" rows="8" name="content" id="content"></textarea>
		</div>
		<button type="button" id="board--save" class="btn btn-primary">글쓰기완료</button>
	</form>
</div>

<script type="text/javascript" src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>

