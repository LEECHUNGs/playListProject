<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>PlayList 회원가입</title>
	<link rel="stylesheet" href="/resources/css/default.css">
</head>
<body>
	<header><h1>회원정보 입력</h1></header>
	<main>
		<section class="signup-section">
			<form action="/signup" method="post">
				<div>
					<p>닉네임</p>
					<input type="text" id="inputNm" name="inputNm" required>
					<span id="nmMessage"></span>
				</div>

				<div>
					<p>아이디</p>
					<input type="text" id="inputId" name="inputId" required>
				</div>

				<div>
					<p>비밀번호</p>
					<input type="password" id="inputPw" name="inputPw" required>
				</div>

				<div>
					<p>비밀번호 확인</p>
					<input type="password" id="inputPwc" name="inputPwc" required>
					<span id="pwMessage"></span>
				</div>

				<button>가입하기</button>
			</form>
		</section>
	</main>

	<script src="/resources/js/signup.js"></script>
</body>
</html>