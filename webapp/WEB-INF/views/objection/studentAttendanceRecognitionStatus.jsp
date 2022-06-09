<%--
학생이 출석인정현황목록을 조회하는 JSP
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 4. 29.      주창규      최초작성
ㅎㅎ
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
학생이 수강 중인 과목에 대한 출석 현황을 조회하는 jsp


<div class="my-info-img">
	<img class="rounded-circle me-lg-2"
		src="${pageContext.request.contextPath }/resources/img/user.jpg"
		alt="" style="width: 150px; height: 150px;">
</div>
<div class="col-xs-7 col-sm-7" style="vertical-align: middle;">
	<ul class="my-info-list">
		<li><strong>학번</strong>${student.userNo}</li>
		<li><strong>성명</strong>${student.userName }</li>
		<li><strong>학부</strong>${student.colName }</li>
		<li><strong>전공</strong>${student.deptName }</li>
		<li><strong>학년</strong>${student.stuYear }</li>
		<li><strong>학적상태</strong>${student.stuCode }</li>
	</ul>
</div>
<h3>출석인정신청목록</h3>

<table class="table table-bordered">
	<thead class="thead-dark">
		<tr>
			<th>학기</th>
			<th>강의명</th>
			<th>교수명</th>
			<th>출결일자</th>
			<th>출결상태</th>
		</tr>
	</thead>
	<tbody>
		<c:set var="attendanceObjectList" value="${student.attendanceObjectList }" />
		<c:forEach items="${attendanceObjectList }" var="attendanceObject">
			<tr>
				<th>${attendanceObject.lecSems }</th>
				<th>${attendanceObject.lecName }</th>
				<th>${attendanceObject.proName }</th>
				<th>${attendanceObject.attabsDate }</th>
				<c:if test="${attendanceObject.attabsCode eq 'D101'}">
				<th>출석</th>
				</c:if>
			</tr>
		</c:forEach>
	</tbody>
	<tfoot>
	
	</tfoot>
</table>
<button onclick="location.href='${pageContext.request.contextPath}/objection/studentAttendanceForm'">등록</button>