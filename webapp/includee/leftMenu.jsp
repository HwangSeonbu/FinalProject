<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<security:authentication property="principal.realUser" 
	var="authMember"/>

	<div id="hermes-left-bar" class="sidebar pe-4 pb-3">
		<nav class="navbar bg-light navbar-light" id="hermes_leftTop_logo">
<%-- 			<img alt="" src="${cPath }/resources/img/hermesTitle.jpg"  --%>
<!-- 				style="width: 250px; height:72px; margin-top: -10px; margin-bottom: 10px"/> -->
			<a href="${pageContext.request.contextPath }/main.do" class="navbar-brand mx-4 mb-3">
				<span class="text-primary" style="font-size:25px; color:white !important;"><strong>대덕인재대학교</strong></span>
			</a>
			<div class="d-flex align-items-center ms-4 mb-4">
			    <div class="position-relative">
					<div class="position-relative">
                        <img class="rounded" src="${cPath }/resources/img/profile/${authMember.userSavename}" alt="" style="width: 60px; height: 80px;">
                        <div class="bg-success rounded-circle border border-2 border-white position-absolute end-0 bottom-0 p-1"></div>
                    </div>
				</div>
				
				<div class="ms-3">
					<h4 class="mb-0" style="color:orange; font-size: 20px;"><strong>${authMember.userName}</strong></h4>
<%-- 					<h6 class="mb-0" style="color:white;">(${authMember.userType})</h6> --%>
					<c:if test="${authMember.userType eq '학생' }">
						<span style="font-size: 15px; font-weight: 900; color:orange;">${authMember.stuYear }학년</span><br>
						<span style="font-size: 15px; font-weight: 600; color:white;" >
							${authMember.colName}<br>${authMember.userDepartmentName }</span>
					</c:if>
					<c:if test="${authMember.userType eq '교수' }">
						<span style="font-size: 15px; font-weight: 900; color:orange;">${authMember.userJob }</span><br>
						<span style="font-size: 15px; font-weight: 600; color:white;">
							${authMember.colName}<br>${authMember.userDepartmentName }</span>
					</c:if>
					<c:if test="${authMember.userType eq '학사관리자' }">
						<span style="font-size: 15px; font-weight: 900; color:orange;">${authMember.userJob }</span><br>
					</c:if>
				</div>
			</div>
<!-- 사이드 메뉴 비동기 생성 시작-->
			<div class="navbar-nav" id="sideMenuArea" style="padding-left: 6px; width: 95%;">
			

			</div>
<!-- 사이드 메뉴 비동기 생성 끝-->
		</nav>
	</div>






