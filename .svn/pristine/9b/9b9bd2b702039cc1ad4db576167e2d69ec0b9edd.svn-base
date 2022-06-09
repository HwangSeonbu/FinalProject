<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 5. 20.      작성자명      최초작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<head>
		<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	</head>
	<script>
	$(document).ready(function(){
		var formObj = $("form[name='acadForm']");
		//상세화면 뷰 
		$(document).on("click", ".linkBtn", function(){
			let href = $(this).data("href");
			if(href)
				location.href=href;
		}).css("cursor", "pointer");
		
		//삭제
			$("#deleteBtn").on("click", function(){
				formObj.attr("action", "${pageContext.request.contextPath }/board/deleteSchedule");
				formObj.attr("method", "post");
				formObj.submit();
			})
			
		//수정
		$("#updateBtn").on("click", function(){
			formObj.attr("action", "${pageContext.request.contextPath }/board/acadScheduleEdit.do");
			formObj.attr("method", "post");
			formObj.submit();				
		})
	})
	</script>
	<style>
		.table-bordered{
			border: 1px solid #C0C0C0;
		}
	</style>
	<form name="acadForm" role="form" method="post">
			<input type="hidden" id="acadscNo" name="acadscNo" value="${acadscVo.acadscNo }" />
			<security:csrfInput/>
	<table class="table table-bordered">
		<tr>
			<th class="table-active">시작일</th>
			<td>${acadscVo.acadscStart }</td>
		</tr>
		<tr>
			<th class="table-active">마감일</th>
			<td>${acadscVo.acadscEnd }</td>
		</tr>
		<tr>
			<td colspan="3" id="boardContent" height="200" >${acadscVo.acadscCont }</td>
		</tr>
	</table>	
		<security:authorize access="hasRole('ROLE_MANAGER')">
		<button type="submit"  id="updateBtn" class="btn btn-primary linkBtn">수정</button>
		<button type="submit"  id="deleteBtn" class="btn btn-primary linkBtn">삭제</button>
		</security:authorize>
		<input type="button" class="btn btn-primary linkBtn"
				data-href="<c:url value='/board/acadScheduleList.do'/>"
				value="목록으로"
			/>  
</form>





   