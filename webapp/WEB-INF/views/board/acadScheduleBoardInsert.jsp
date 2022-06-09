<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 5. 19.      이유정      최초작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<h3 class="h3-title">학사일정등록</h3><hr class="hr-title">
<style>
	.table-bordered{
			border: 1px solid #C0C0C0;
		}
</style>
<form:form modelAttribute="acadscVo" 
	action="${cPath }/board/acadScSave.do" method="post">
<security:csrfInput/>
<table class="table table-bordered">
	<form:input type="hidden" path="acadscNo" class="form-control"  value="${acadscNo}"/>
	<tr>
		<th class="table-active">시작일</th>
		<td>
			<form:input path="acadscStart" type="date" class="form-control"/>
		</td>
	</tr>
	<tr>
		<th class="table-active">마감일</th>
		<td>
			<form:input path="acadscEnd" type="date" class="form-control" />
		</td>
	</tr>
	<tr>
		<th class="table-active">일정내용</th>
		<td>
			<form:textarea path="acadscCont" class="form-control" />
		</td>
	</tr>
</table>
<div style="float: right">
	<input type="submit" class="btn btn-primary" value="등록"
	onClick="location.href='${pageContext.request.contextPath }/board/acadScSave.do'"
	>
	<input type="button" class="btn btn-primary"value="뒤로" onclick="javascript:history.go(-1);">
</div>
</form:form>

