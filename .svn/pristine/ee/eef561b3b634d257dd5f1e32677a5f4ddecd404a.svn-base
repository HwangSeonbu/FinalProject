<%--
	학생이 성적을 종합적으로 볼수 있는 페이지
* [[개정이력(Modification Information)]]
* 수정일                 수정자      	수정내용
* ----------  ---------  -----------------
* 2022. 5. 2.   김재웅      	최초작성
* 2022. 5. 3. 	민진홍     	성적조회페이지 작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
	 <%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
    <security:authentication property="principal.realUser" 
	var="authMember"/>
<script
	src="${pageContext.request.contextPath }/resources/js/jquery-3.6.0.min.js"></script>
<!-- Icon Font Stylesheet -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css"
	rel="stylesheet">

<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap"
	rel="stylesheet">

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
<!-- Customized Bootstrap Stylesheet -->

<link
	href="${pageContext.request.contextPath }/resources/js/bootstrap-5.1.3-dist/css/bootstrap.css"
	rel="stylesheet">
	
<style>
.card {
	margin-top: 30px;
	border : 0;
}
hr {
    border: none;
    border-top: 1px solid black;
    overflow: visible;
    text-align: center;
    margin-top: 24px;
    height: 17px;
}

hr:after {
    content: "학기상세정보";
    position: relative;
    top: -15px;
    background: black; 
    padding: 0 10px;
    color: white;
    font-size: 0.8em;
}

/* 이미지 흐림현상제거 */
img{
image-rendering: -webkit-optimize-contrast;
transform: translateZ(0);
backface-visibility: hidden;
}

</style>
	
	<hr>
<div class="container">
 <div class="card top-0 start-50 translate-middle-x " style="max-width: 540px;">
  <div class="row g-0">
    <div class="col-md-4" style="padding:20px;">
      <img src="${cPath }/resources/img/ina.jpg" class="rounded rounded-circle" alt="..." height="100%" width="100%">
    </div>
    <div class="col-md-8">
      <div class="card-body fw-bold">
       							<div class="col-xs-7 " >
								<ul class="myInfomation" style="border-left:3px solid #677794; list-style: none;">
									<li><strong>학번 :  </strong>${authMember.userNo }</li>
									<li><strong>성명 :  </strong>${authMember.userName }</li>
									<li><strong>학년 :  </strong>${authMember.stuYear }학년</li>
									<li><strong>학과 :  </strong>${authMember.userDepartmentName }</li>
									<li><strong>학적상태 :  </strong>${authMember.stuCode}</li>
								</ul>
							</div>
      </div>
    </div></div>
  </div>
  <hr>
<table class="table text-center table-hover">
	<thead>
		<tr>
			<th>강좌정보</th>
			<th>이수구분</th>
			<th>학점</th>
			<th>등급</th>
			<th>평점평균</th>			
			<th>배점평균</th>			
			<th>담당교수</th>			
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${scoreList}" var="score">
	<tr>
		<td>${score.LEC_NAME}</td>
		<td>${score.SJT_MAJOR}</td>
		<td>${score.SJT_CREDIT}</td>
		<td>${score.CLS_CRDT}</td>
		<c:if test="${score.CLS_CRDT eq 'A+' }">
		<td>4.5</td>
		</c:if>
		<c:if test="${score.CLS_CRDT eq 'A' }">
		<td>4</td>
		</c:if>
		<c:if test="${score.CLS_CRDT eq 'B+' }">
		<td>3.5</td>
		</c:if>
		<c:if test="${score.CLS_CRDT eq 'B' }">
		<td>3</td>
		</c:if>
		<c:if test="${score.CLS_CRDT eq 'C+' }">
		<td>2.5</td>
		</c:if>
		<c:if test="${score.CLS_CRDT eq 'C' }">
		<td>2</td>
		</c:if>
		<c:if test="${score.CLS_CRDT eq 'D+' }">
		<td>1.5</td>
		</c:if>
		<c:if test="${score.CLS_CRDT eq 'D' }">
		<td>1</td>
		</c:if>
		<c:if test="${score.CLS_CRDT eq 'F' }">
		<td>0</td>
		</c:if>
		<c:if test="${score.CLS_CRDT eq '0' }">
		<td>0</td>
		</c:if>
		<td>${score.CLS_PCT}</td>
		<td>${score.PRO_NAME}</td>
	</tr>
	</c:forEach>
</tbody>

</table>
</div>


    <script src="${pageContext.request.contextPath }/resources/js/jquery-3.6.0.min.js"></script>
    <script src="${pageContext.request.contextPath }/resources/js/bootstrap-5.1.3-dist/js/bootstrap.bundle.min.js"></script>

