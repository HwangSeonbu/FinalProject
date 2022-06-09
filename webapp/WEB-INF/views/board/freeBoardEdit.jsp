<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 5. 13.     이유정      최초작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<script type="text/javascript" src="${cPath }/resources/js/fullckeditor/ckeditor.js"></script>
<style>
	.table-bordered{
			border: 1px solid #C0C0C0;
		}
</style>
<form:form modelAttribute="fBoardVO" method="post"  action="${cPath }/board/freeBoardView">	
<security:csrfInput/>
	<table class="table table-bordered">
		<form:input type="hidden" path="boardNo" class="form-control" />
		<form:input type="hidden" path="userNo" class="form-control" />
	<tr>
		<th class="table-active">글제목</th>
		<td>
			<form:input path="boardTitle" class="form-control" />
		</td>
	</tr>
	<tr>
		<th class="table-active">내용</th>
			<td>
			<TEXTAREA rows="333" cols="333" name="boardContent">${fBoardVO.boardContentDisplay}</TEXTAREA>
		</td>
	</tr>
	</table>
			<form:button type="submit" class="btn btn-success">저장 <security:csrfInput/></form:button> 
			<input type="button" value="목록으로" class="btn btn-secondary linkBtn"
			onClick="location.href='${pageContext.request.contextPath }/board/freeBoardList.do'"
		/>
</form:form>
<script type="text/javascript">
	CKEDITOR.replace('boardContent',{height: 800 
		,filebrowserImageUploadUrl : "${cPath}/board/image?type=image"
	});
</script>




