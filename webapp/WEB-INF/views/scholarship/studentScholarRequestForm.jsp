<%--
 학생이 장학금 신청을 할수 있는 페이지
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 5. 2.      김재웅      최초작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<security:csrfMetaTags />
<security:authentication property="principal.realUser" var="authMember" />
<h3 class="h3-title">장학금 신청</h3><hr class="hr-title">

  <div class="row">
<div class="col">
	<div class="p-3 border bg-light">
		<div class="panel-heading">
			<span class="enrTitle">장학금 리스트</span>
		</div>
		<table class="table table-hover text-center" id="enrTable">
			<colgroup>
				<col width="10%">
				<col width="10%">
				<col width="20%">
				<col width="10%">
				<col width="20%">
				<col width="10%">
			</colgroup>
			<thead>
				<tr>
					<th>장학금명</th>
					<th>성적기준</th>
					<th>신청자격</th>
					<th>장학금액</th>
					<th>장학금안내</th>
					<th>신청하기</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach items="${schList}" var="schvo">
					<tr class="align-middle">
						<td>${schvo.schName }</td>
						<td>${schvo.schScore }</td>
						<td>${schvo.schQuali }</td>
						<td><fmt:formatNumber value="${schvo.schAmount}"
								pattern="#,###" />원</td>
						<td>${schvo.schContent}</td>
						<c:set var="doneLoop" value="true" />
						<c:forEach items="${aleardList }" var="aleard">
							<c:if test="${aleard eq schvo.schNo}">
								<c:set var="flag" value="true" />
							</c:if>
						</c:forEach>
						<c:choose>
							<c:when test="${flag}">
								<td><button type="button" class="btn btn-info btn-sm"
										id="applyBtn" name="${schvo.schNo }"
										disabled="disabled">신청하기</button></td>
							</c:when>
							<c:otherwise>
								<td><button type="button" class="btn btn-info btn-sm"
										id="applyBtn" name="${schvo.schNo }">신청하기</button></td>
							</c:otherwise>
						</c:choose>
						<c:set var="flag" value="false" />
					</tr>
				</c:forEach>


			</tbody>
		</table>
	</div>
	</div>


<div class="col-4">
	<div class="p-3 border bg-light">
		<div class="panel-heading">
			<span class="enrTitle">신청내역</span>
		</div>
		<table class="table table-hover text-center" id="applyTable">
			<colgroup>
				<col width="20%">
				<col width="10%">
				<col width="10%">
			</colgroup>
			<thead>
				<tr>
					<th>장학금명</th>
					<th>신청상태</th>
					<th>비고</th>					
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${reqList}" var="req">
					<tr class="align-middle">
						<td>${req.SCHNAME }</td>
						<td>${req.REQSTAT }</td>
						<c:if test="${req.REQSTAT eq '대기'}">
						<td><button type="button" class="btn btn-danger btn-sm"
										id="cancelBtn" name="${req.REQID}">취소</button></td>
						</c:if>	
					<c:if test="${req.REQSTAT eq '반려'}">
						<td><button type="button" class="btn btn-danger btn-sm"
										id="viewReasonBtn" name="${req.REQID}">사유보기</button>
							<input type="text" value="${req.REQDENL }" hidden="hidden"/>
										</td>
						</c:if>
							
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
</div>

<script>
	$(function() {
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");

		$(document).on("click", "#applyBtn", function() {
			let schNo = $(this).attr("name");
			$.ajax({
				url : "${cPath}/applyScholar/studentScholarApply",
				data : {
					"schNo" : schNo
				},
				type : "post",
				beforeSend : function(xhr) {
					if (token && header) {
						xhr.setRequestHeader(header, token);
					}
				},
				success : function(resp) {
					if (resp == 1) {
						const Toast = Swal.mixin({
						    toast: true,
						    position: 'center-center',
						    showConfirmButton: false,
						    timer: 1000,
						    timerProgressBar: true,
						    didOpen: (toast) => {
						        toast.addEventListener('mouseenter', Swal.stopTimer)
						        toast.addEventListener('mouseleave', Swal.resumeTimer)
						    }
						});
						
						Swal.fire({
							   title: '신청 완료',
							   text: '신청 완료',
							   width : '300px'
							   
							}).then(function(){
								// 이벤트
								location.reload();
							});
							
						
						
					} else {
						alert("신청실패");
						location.reload();
					}
					

				},
				error : function(jqXHR, textStatus, errorThrown) {
					console.log(jqXHR);
					console.log(textStatus);
					console.log(errorThrown);
				}

			});
		});
		
		$(document).on("click", "#viewReasonBtn", function() {
			
			let content = $(this).next('input').val();
			
			Swal.fire({
				   title: '반려 사유',
				   text: content,
				   width : '300px'
				   
				});
		});
		
		
		$(document).on("click", "#cancelBtn", function() {
			let reqId = $(this).attr("name");
			$.ajax({
				url : "${cPath}/applyScholar/cancelStudentScholarshipApply",
				data : {
					"reqId" : reqId
				},
				type : "post",
				beforeSend : function(xhr) {
					if (token && header) {
						xhr.setRequestHeader(header, token);
					}
				},
				success : function(resp) {	
					if (resp == 1) {
						Swal.fire({
							   title: '취소 완료',
							   width : '300px'
							   
							}).then(function(){
								// 이벤트
								location.reload();
							});
					} else {
						alert("취소실패");			
					location.reload();
						}

				},
				error : function(jqXHR, textStatus, errorThrown) {
					console.log(jqXHR);
					console.log(textStatus);
					console.log(errorThrown);
				}

			});
		});
	});
</script>