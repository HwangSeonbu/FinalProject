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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<script type="text/javascript" src="${cPath }/resources/js/fullckeditor/ckeditor.js"></script>
<script>
$(document).ready(function(){
	let boardEditForm = $("#boardEditForm");
	$(".attchDelBtn").on("click", function(){
		let attchNo = $(this).data("attchNo");
		$(this).parents("span:first").hide();
		let newInput = $("<input>").attr({
							"type":"hidden"
							, "name":"delAttNos"
							, "value":attchNo
						});
		boardEditForm.append(newInput);
	});
})
</script>
<style>
	.table-bordered{
			border: 1px solid #C0C0C0;
		}
</style>
<form:form modelAttribute="nBoardVO" id="boardEditForm" method="post" enctype="multipart/form-data" action="${cPath }/board/noticeBoardView" >	
<security:csrfInput/>
	<table class="table table-bordered">
		<form:input type="hidden" path="noticeNo" class="form-control" readonly="true"/>
		<form:input type="hidden" path="userNo" class="form-control" readonly="true"/>
	<tr>
		<th class="table-active">글제목</th>
		<td>
			<form:input path="noticeTitle" class="form-control" />
		</td>
	</tr>
	<tr>
		<th class="table-active">기존 첨부파일</th>
		<td>
			<c:forEach items="${nBoardVO.attchList }" var="attch">
				<span>
					${attch.attchFname }
					<input type="button" value="삭제" class="attchDelBtn" data-attch-no="${attch.attchNo }" />
				</span>
			</c:forEach>
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
		<th class="table-active">내용</th>
		<td>
			<TEXTAREA rows="333" cols="333" name="noticeContent">${nBoardVO.noticeContentDisplay}</TEXTAREA>
		</td>
	</tr>
</table>
		<form:button type="submit" class="btn btn-success">저장 <security:csrfInput/></form:button> 
				<input type="button" value="목록으로" class="btn btn-secondary linkBtn"
				onClick="location.href='${pageContext.request.contextPath }/board/noticeListView.do'"/>
</form:form>

<script type="text/javascript">
	CKEDITOR.replace('noticeContent',{height: 800 
		,filebrowserImageUploadUrl:CONTEXTPATH+"/board/image?type=image"
	});
</script>




