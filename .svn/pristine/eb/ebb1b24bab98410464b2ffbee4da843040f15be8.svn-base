<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 5. 24.      민진홍	아이디찾기 팝업창
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<%-- 	<c:if test="${userNo eq null}"> --%>
	<div class="card">
				<article class="card-body">
					<h4 class="card-title text-center mb-4 mt-1">HERMES</h4>
					<hr>
					<p class="text-info text-center">학번(교번) 찾기</p>
					
					<form action="/team3Project_LMS/login/searchId" method="post" id="searchIdForm">
					<security:csrfInput/> 
					  <input type="hidden" name="_csrf" value="facfe399-aecc-4774-872f-a7fbdb4de9d1">
						<div class="form-group">
							<div class="input-group">
								<div class="input-group-prepend">
									<span class="input-group-text">이름</span>
								</div>
								  <input type="text" id="userName" name="userName" class="form-control" placeholder="Name" required="required" autofocus="autofocus">
							</div> <!-- input-group.// -->
						</div> <!-- form-group// -->
						<div class="form-group">
							<div class="input-group">
								<div class="input-group-prepend">
									<span class="input-group-text primary">이메일</span>
								</div>
								<input type="email" id="userMail" name="userMail" class="form-control" placeholder="Email">
							</div> <!-- input-group.// -->
						</div> <!-- form-group// -->
						<div class="form-group">
							<div class="input-group">
								<div class="input-group-prepend">
									<span class="input-group-text primary">생년월일</span>
								</div>
								<input type="number" id="userReg1" name="userReg1" class="form-control" placeholder="YY/MM/DD">
							</div> <!-- input-group.// -->
						</div> <!-- form-group// -->
						<div class="form-group">
							<button type="submit" class="btn btn-primary btn-block">아이디 찾기</button>
						</div> <!-- form-group// -->
					</form>
				</article>
			</div>
<%-- 		</c:if> --%>
	<div class="container text-center">
		<c:if test="${userNo ne null}">
		<br>
					<span class="text-primary">찾은 학번(교번)은 ${userNo } 입니다.</span>
		</c:if>
		<c:if test="${message ne null}">
		<br>
					<span class="text-primary">${message }</span>
		</c:if>
		</div>
</body>
</html>