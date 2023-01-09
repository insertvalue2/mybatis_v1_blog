<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<br><br>
<div class="container">
	<h2>로그인 하기</h2>
	<p>다양한 기록들을 남기고 공유해 주세요</p>
	<br>
	<form action="/user/signin-proc" method="post">
		<div class="mb-3 mt-3">
			<input
				name = "username"
				type="text" class="form-control"
				placeholder="Enter username" value="tenco">
		</div>
		<div class="mb-3">
			<input
			    name = "password"
				type="password" class="form-control" 
				placeholder="Enter password" value="asd123" >
		</div>
		<span> 
		<c:if test="${isNotSignin}">
				<p id="valid" class="alert alert-danger">잘못된 요청입니다</p>
			</c:if>
		</span>
		
		<button type="submit" class="btn btn-primary">로그인</button>
	</form>
</div>

<%@ include file="../layout/footer.jsp"%>
