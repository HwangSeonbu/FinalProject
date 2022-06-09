<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 5. 6.      이유정      최초작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<script type="text/javascript" src="${cPath }/resources/js/fullckeditor/ckeditor.js"></script>
<style>
	.table-bordered{
			border: 1px solid #C0C0C0;
		}
</style>
<form:form modelAttribute="nBoard" action="${cPath }/board/noticeSave"  enctype="multipart/form-data" method="post">	
<security:csrfInput/>
<table class="table table-bordered">
	<form:input type="hidden" path="userNo" class="form-control" value="${userNo}"/>
	<tr>
		<th class="table-active">제목</th>
		<td>
			<form:input path="noticeTitle" class="form-control" />
		</td>
	</tr>
	<tr>
		<th class="table-active">내용</th>
		<td>
			<form:textarea path="noticeContent" class="form-control"/>
		</td>
	</tr>
	<tr>
		<th class="table-active">신규 첨부파일</th>
		<td>
			<input type="file" name="boFiles" class="form-control"/>
			<input type="file" name="boFiles" class="form-control"/>
			<input type="file" name="boFiles" class="form-control"/>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="저장" class="btn btn-primary"
				onClick="location.href='${pageContext.request.contextPath }/board/noticeSave'"
			/>
			<input type="button" value="목록으로" class="btn btn-secondary linkBtn"
				onClick="location.href='${pageContext.request.contextPath }/board/noticeListView.do'"
			/>
		</td>
	</tr>
</table>
</form:form>
<script type="text/javascript">
	CKEDITOR.replace('noticeContent',{height: 800 
		,filebrowserImageUploadUrl : "${cPath}/board/image?type=image"
		});
</script>



    