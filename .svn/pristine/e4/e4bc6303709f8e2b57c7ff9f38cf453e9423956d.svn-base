<%--상담신청현황 조회 및 메시지 전송, 상담신청서 조회, 상담일지 작성 페이지
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -------------------
* 2022. 4. 29. 황선부      최초작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="kr.or.ddit.vo.CounselVO"%>
<%@page import="kr.or.ddit.vo.PagingVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<link rel="stylesheet" href="${cPath }/resources/js/tablesorter-master/dist/css/theme.default.min.css">
<script type="text/javascript" src="${cPath}/resources/js/tablesorter-master/dist/js/jquery.tablesorter.js"></script>
<script type="text/javascript" src="${cPath}/resources/js/tablesorter-master/dist/js/jquery.tablesorter.widgets.js"></script>

<style>
#yet{
	color:red;
}
th,td{
	
	text-align: center;
}
.message{
	color: white;
}
#title{
	color: white;
	font-size : 30px;
	font-style: italic;
}
#paging{
	text-align: center;
}
/* #div{ */
/* 	display : inline; */
/* 	float : right; */
/* } */
#paging nav{
	margin-left: 45%;
}
#div{
	margin:auto 100px;
}
#myTable th{
	font-size: 1.4em;
}
td{
	font-size: 1.4em;
}
#sendBtn{
	border-radius: 10px;
}
</style>

<!-- <h1>상담신청현황 조회 및 메시지 전송, 상담신청서 조회, 상담일지 작성 페이지, poi도 추가해야함</h1> -->
<h3 class="h3-title">상담신청 관리</h3><hr class="hr-title">

	  <div class="row row-cols-1">
	    <div class="col p-4 p-md-5 mb-4 text-white rounded bg-dark">
	    <form:form modelAttribute="counselVO" method="post" action="${cPath }/respCounsel/message">
	
		<table  class="table mx-auto caption-top" style="width: 1000px;">
			<CAPTION><span id="title">상담 메시지 전송</span></CAPTION>
			<tr  class="message">
				<th>수신 학생번호</th>
				<th>수신 학생명</th>
				<th>날짜<th>
				<th>시간<th>
				<th id="location" class="col-lg-3">장소<th>
				<th><th>
			</tr>
			<tr>
				<td class="align-middle"><form:input class="form-control my-auto" id="stuNo" path="stuNo" /></td>
				<td class="align-middle"><form:input class="form-control my-auto" id="userName" path="userName"/>
				</td>
				<td class="align-middle"><input class="form-control my-auto" type="date" name="date"/><td>
				<td class="align-middle"><input class="form-control my-auto" type="time" name="time"/><td>
				<td class="align-middle"><input class="form-control my-auto" type="text" name="cnslLo"/><td>
				<td class="align-middle"><BUTTON id="sendBtn" class="btn btn-primary form-control my-auto" type="submit"><i style="font-size: 2rem;" class="bi bi-send-plus"></i></BUTTON><td>		
			</tr>
		</table>
			<input type="hidden" id="cnslId" name="cnslId"/>
			<input type="hidden" id="userPhone" name="userPhone"/>
			
	    </form:form>
		
		</div>
	    <div class="col">
	    	<div id="div">
	    	<button id="btn" type="button" class="btn btn-secondary">엑셀로 저장(xls)</button>
<%-- 	    	<a id="btn" href='${cPath}/respCounsel/counselExcel'>엑셀로 저장(xls)</a></div> --%>
	    	<table id="myTable" class="table table-striped table-hover tablesorter">
				<thead>
					<tr>
						<th>#</th>
						<th>신청서번호</th>
						<th>학번</th>
						<th>성명</th>
						<th>전화번호</th>
						<th>학과</th>
						<th>학적상태</th>
						<th>구분</th>
						<th>상담접수일</th>
						<th>예정상담일</th>
						<th>상담일시</th>				
						<th>상담일지</th>
					</tr>
				</thead>
				<tbody>
					<c:set var="counselList" value="${paging.dataList}"/>
					<c:if test="${empty counselList}">
						<tr>
							<td colspan="10">본인에게 상담 신청한 학생이 없습니다.</td>
						<tr>
					</c:if>
					<c:if test="${not empty counselList}" >
	<!-- 				dataList.counsel.studVO -->
						<c:forEach items="${counselList}" var="counsel">
								
								<tr class="studentTr" data-user-no="${counsel.userNo}">
									<td>
									<input class="form-check-input radioClass"
									data-cnsl-id="${counsel.cnslId }"
									data-user-name="${counsel.userName }"
									data-stu-no="${counsel.userNo }"
									data-user-phone="${counsel.userPhone }"
									 name="radioName" type="radio">
									</td>
									<td>${counsel.cnslId}</td>
									<td>${counsel.userNo }</td>
									<td>${counsel.userName }</td>
									<td>${counsel.userPhone }</td>
									<td>${counsel.deptName }</td>
									<td>${counsel.stuCodename }</td>
									<td>
										<c:if test="${not empty counsel.dyDate }">일지등록완료</c:if>
										<c:if test="${empty counsel.dyDate }"><span id="yet">미등록</span></c:if>								
									</td>
									<td>${counsel.cnslDate }</td>
									<td>${counsel.cnslDay }</td>								
									<td>${counsel.dyDate }</td>
														
									<td>
									
									
									<c:if test="${empty counsel.dyDate }">							
									<form action="${cPath }/respCounsel/counselLog" method="post">
									<security:csrfInput/> 
									<input type="text" name="userNo" hidden="true" value="${counsel.userNo}"/>									
									<input type="text" name="cnslId" hidden="true" value="${counsel.cnslId}"/>	
									<button type="submit" id="${counsel.userNo}" class="btn btn-sm btn-success">일지등록</button>
									</form>
									</c:if>
									
									<c:if test="${not empty counsel.dyDate }">								
									<form action="${cPath }/respCounsel/counselLogView" method="post">
									<security:csrfInput/> 
									<input type="text" name="userNo" hidden="true" value="${counsel.userNo}"/>									
									<input type="text" name="cnslId" hidden="true" value="${counsel.cnslId}"/>									
									<button type="submit" id="${counsel.userNo}" class="btn btn-sm btn-warning">일지조회</button>
									</form>
									</c:if>
									
									
									</td>						
								</tr>			
						</c:forEach>
					</c:if>
				</tbody>
				<tfoot>
					<tr>
						<td id="paging" colspan="12">
							${paging.pagingHTMLBS }
						</td>
					</tr>
				</tfoot>
	    	</table>
	    <form id="searchForm">
			<input type="hidden" name="page" />
		</form> 
	    
	    </div>
	  </div>

<script>
$("#btn").on("click",function(){
	location.href='${cPath}/respCounsel/counselExcel';
	
})
// <c:if test="${not empty message }">
// alert("${message}");
// </c:if>

// $("#excelBtn").on("click",function(){
// 	window.location.href = '${cPath}/counselExcel';
	
// })

$("input[name=radioName]").on("change",function(){
	let cnslId = $("input[name=radioName]:checked").data("cnslId");
	let userName = $("input[name=radioName]:checked").data("userName");
	let stuNo = $("input[name=radioName]:checked").data("stuNo");
	let userPhone = $("input[name=radioName]:checked").data("userPhone").replaceAll("-","");
	
	$("#stuNo").val(stuNo);
	$("#userName").val(userName);
	$("#cnslId").val(cnslId);
	$("#userPhone").val(userPhone);
})
let registerForm = $("#registerForm");
const searchForm = $("#searchForm");
const searchDIV = $("#searchDIV").on("click", "[type=button]", function(){
	let inputs = searchDIV.find(":input[name]");
	$(inputs).each(function(index, ipt){
		let name = this.name;
		let value = $(this).val();
		searchForm.find("[name="+name+"]").val(value);
	});
	searchForm.submit();
});

$(".pagination").on("click", "a", function(){
	let page = $(this).data("page");
	searchForm.find("[name=page]").val(page);
	searchForm.submit();
});

$("#myTable").tablesorter();
$("#myTable").tablesorter({ sortList: [[0,0], [1,0]] });

</script>