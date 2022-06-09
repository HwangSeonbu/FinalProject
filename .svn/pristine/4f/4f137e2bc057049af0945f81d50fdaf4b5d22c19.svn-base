<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 5. 20.      이유정      최초작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<style>
	.table-bordered{
			border: 1px solid #C0C0C0;
		}
</style>
<form:form modelAttribute="acadscVo" method="post"  action="${cPath }/board/saveSchedule">	
<security:csrfInput/>
	<table class="table table-bordered">
		<form:input type="hidden" path="acadscNo" class="form-control" />
	<tr>
		<th class="table-active">시작일</th>
		<td>
			<form:input type="date" path="acadscStart" class="form-control"/>
		</td>
	</tr>
	<tr>
		<th class="table-active">마감일</th>
		<td>
			<form:input type="date" path="acadscEnd" class="form-control" />
		</td>
	</tr>
	<tr>
		<th class="table-active">일정내용</th>
		<td>
			<form:textarea path="acadscCont" class="form-control" />
		</td>
	</tr>
</table>
		<form:button type="submit" class="btn btn-success">저장 <security:csrfInput/></form:button> 
		<input type="button" value="목록으로" class="btn btn-secondary linkBtn"
		onClick="location.href='${pageContext.request.contextPath }/board/acadScheduleList.do'"
	/>
</form:form>


