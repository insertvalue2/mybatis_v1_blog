<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<br>
<br>
<div class="container">
	<h2>회원 가입하기</h2>
	<p>My Blog에 친구가 되어주세요</p>
	<br>
	<form action="/user/signup-proc" method="post">
		<div class="mb-3 mt-3">
			<input type="text" name="username" class="form-control" placeholder="Enter username" value="tenco">
		</div>
		<div class="mb-3">
			<input type="password" name="password" class="form-control" placeholder="Enter password" value="asd123">
		</div>
		<div class="mb-3">
			<input type="email" name="email" class="form-control" placeholder="Enter email" value="a@naver.com">
		</div>
		<div class="mb-3">
			<input type="text" name="profile" class="form-control" placeholder="Enter profile" maxlength="150" value="신입개발자">
		</div>
		<span> <c:if test="${isError}">
				<p id="valid" class="alert alert-danger">${error.message}</p>
			</c:if>
		</span>

		<button type="submit" class="btn btn-primary">회원가입</button>
	</form>

</div>

<%@ include file="../layout/footer.jsp"%>
