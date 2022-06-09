<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
	<head>
		<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	</head>
	<script>
		$(document).ready(function(){
			var formObj = $("form[name='noticeForm']");
			//삭제
			$("#deleteBtn").on("click", function(){
				formObj.attr("action", "${pageContext.request.contextPath }/board/noticeListView");
				formObj.attr("method", "post");
				formObj.submit();
			})
			//수정
			$("#updateBtn").on("click", function(){
				formObj.attr("action", "${pageContext.request.contextPath }/board/noticeEditView");
				formObj.attr("method", "post");
				formObj.submit();				
			})
			
			$(document).on("click", ".linkBtn", function(){
				let href = $(this).data("href");
				if(href)
					location.href=href;
			}).css("cursor", "pointer");
			
		})
	</script>
	<style>
		.table-bordered{
			border: 1px solid #C0C0C0;
		}
	</style>
	<form name="noticeForm" role="form" method="post" enctype="multipart/form-data">
	<security:csrfInput/>
			<input type="hidden" id="noticeNo" name="noticeNo" value="${nBoard.noticeNo }" />
	<table class="table table-bordered" >
	 <colgroup>
            <col width="15%">
            <col width="35%">
            <col width="15%">
            <col width="*">
       </colgroup>
       <tbody>
		<tr>
			<th class="table-active">제목</th>
			<td>${nBoard.noticeTitle }</td>
			<th class="table-active">조회</th>
			<td>${nBoard.noticeHit }</td>
		</tr>
		<tr>
			<th class="table-active">작성자</th>
			<td>${nBoard.userName }</td>
			<th class="table-active">등록일시</th>
			<td>${nBoard.noticeDate }</td>
		</tr>
		<tr>
			<th class="table-active">첨부파일</th>
			<td colspan="3">
		 		<c:forEach items="${nBoard.attchList }" var="attch" varStatus="vs">
					<c:url value="/board/${nBoard.noticeNo }/attch/${attch.attchNo }" var="downloadURL" />
					<a href="${downloadURL }">${attch.attchFname }</a> 
					<c:if test="${not vs.last }">
						| &nbsp;&nbsp;&nbsp;
					</c:if>
				</c:forEach> 
			</td>
		</tr>
		<tr>
			<td colspan="4" id="noticeContent" height="500">${nBoard.noticeContentDisplay }</td>
		</tr>
	</tbody>
	</table>
		<security:authentication property="principal.realUser" var="authMember"/>
		<c:if test="${authMember.userNo eq nBoard.userNo}">
		<security:authorize access="hasRole('ROLE_MANAGER')">	
			<button type="submit"  id="updateBtn" class="btn btn-primary linkBtn">수정</button>
			<button type="submit"  id="deleteBtn" class="btn btn-primary linkBtn">삭제</button>
		</security:authorize>
		</c:if>
		<input type="button" class="btn btn-primary linkBtn"
					data-href="<c:url value='/board/noticeListView.do'/>"
					value="목록으로"
				/>
	</form>

	

