<%--
* [[개정이력(Modification Information)]]
* 수정일                 		수정자     			 수정내용
* ----------  		---------  	-----------------
* 2022. 4. 28.		고성식     		 최초작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<table class="table table-bordered">
	<tr>
		<th>학번</th>
		<td>${student.userNo }</td>
	</tr>
	<tr>
		<th>이름</th>
		<td>${student.userName }</td>
	<tr>
		<th>사진</th>
		<td>
			<spring:eval expression="@appInfo.studentImages" var="studentImages"></spring:eval>		
			<img src="${pageContext.request.contextPath }${studentImages}/${student.userSaveName}">
		</td>
	</tr>
	<tr>
		<th>분과대학명</th>
		<td>${student.colName }</td>
	</tr>
	<tr>
		<th>학과명</th>
		<td>${student.deptName }</td>
	</tr>
	<tr>
		<th>학년</th>
		<td>${student.stuYear }</td>
	</tr>
	<tr>
		<th>반</th>
		<td>${student.stuClass }</td>
	</tr>
	<tr>
		<th>성별</th>
		<td>${student.userGender }</td>
	</tr>
	<tr>
		<th>전화번호</th>
		<td>${student.userPhone }</td>
	</tr>
	<tr>
		<th>주소</th>
		<td>${student.userAdder }</td>
	</tr>
	<tr>
		<th>생년월일</th>
		<td>${student.userReg1 }</td>
	</tr>
	<tr>
		<th>이메일</th>
		<td>${student.userMail }</td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="button" class="btn btn-primary linkBtn" value="목록으로"
				data-href="<c:url value='/student/studentList.do'/>"
			/>
<%-- 			<c:url value="/student/studentUpdate.do" var="updateURL"> --%>
<%-- 					<c:param name="what" value="${prod.prodId }"/> --%>
<%-- 			</c:url> --%>
<!-- 			<input type="button" class="linkBtn" value="수정"  -->
<%-- 				data-href="${updateURL }" --%>
<!-- 			/> -->
		</td>			
	</tr>
	</table>