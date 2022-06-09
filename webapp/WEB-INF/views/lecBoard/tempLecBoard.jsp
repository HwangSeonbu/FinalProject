<%--
강의게시판 메인으로 가는 분기페이지
(제목을 클릭하면 점수 비중을 설정하는 jsp로 이동한다)
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 4. 28.      김재웅      최초작성
* 2022. 5. 03.	민진홍	UI
* 2022. 5. 13.  황선부           분기 페이지 작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!-- <nav> -->
<!--   <div class="nav nav-tabs" id="nav-tab" role="tablist"> -->
<!--     <button class="nav-link active" id="nav-home-tab" data-bs-toggle="tab" data-bs-target="#nav-home" type="button" role="tab" aria-controls="nav-home" aria-selected="true">공통</button> -->
<!--     <button class="nav-link" id="nav-profile-tab" data-bs-toggle="tab" data-bs-target="#nav-profile" type="button" role="tab" aria-controls="nav-profile" aria-selected="false">강의게시판</button> -->
<!--     <button class="nav-link" id="nav-contact-tab" data-bs-toggle="tab" data-bs-target="#nav-contact" type="button" role="tab" aria-controls="nav-contact" aria-selected="false">과제게시판</button> -->
<!--   </div> -->
<!-- </nav> -->
<style>
tr td:first-child, th{
	text-align: center;
	width: 5px;
}
tr td:first-chlid{
}
#lecTable{
	width:75%;
	margin: 0px auto;
/* 	margin-left: 10%; */
}
#clsTable{
	width : 60%;
 	margin: 0px auto;
/* 	margin-left: 2%; */
}
.lecTr td:first-child{
	width: 5%;
} 
.lecTr td:nth-child(2){
	width: 8%;
	text-align: center;
} 
.lecTr td:nth-child(4),td:nth-child(5){
	width: 8%;
	text-align: center;
}

.cls td:nth-child(1){
	width:8%;
}
.cls td:nth-child(3),td:nth-child(4){
	width:15%;
	text-align: center;
}

</style>
	<security:authorize access="hasRole('ROLE_PROFESSOR')">
	<h3 class="h3-title">강의 과목</h3><hr class="hr-title">
      <table id="lecTable" class="table table-bordered table-hover">
      	<thead>
	      	<tr>
	      		<th>#</th>
	      		<th>강의번호</th>
	      		<th>교과목이름</th>
	      		<th>수강인원</th>
	      		<th>전공분류</th>
	      		<th>강의실</th>
	      	</tr>
      	</thead>
      		<tbody>
      
      		<c:forEach items="${lecList }" var="lec" varStatus="status">
      			<tr class="lecTr" data-lec-sems="${lec.SEMS_DATE}" data-lec-id="${lec.LEC_ID }" id="${lec.LEC_ID }">
		 			<td>${status.count}</td>
		 			<td>${lec.LEC_ID }</td>
		 			<td>&nbsp;&nbsp;&nbsp;${lec.SJT_NAME }</td>
		 			<td>${lec.LEC_PERS }</td>
		 			<td>${lec.SJT_MAJOR }</td>
		 			<td>&nbsp;&nbsp;&nbsp;${lec.GWAN_NAME } - ${lec.ROOM_NO}호</td>			
		 		</tr>
      		</c:forEach>
      	
      		</tbody>
      </table>    
    </security:authorize>
	
	<security:authorize access="hasRole('ROLE_STUDENT')"> 
<h3 class="h3-title">수강과목</h3><hr class="hr-title">
	 	<hr>
	 	<table id="clsTable" class="table table-bordered table-hover">
		 		<thead>
			 		<tr>
			 			<th>#</th>
			 			<th>강의명</th>
			 			<th>학점</th>
			 			<th>교수명</th>
			 		</tr>
	 			</thead>
	 		<c:forEach items="${lecList }" var="lec" varStatus="status" >
	 			<tbody>
		 		<tr class="cls" data-lec-sems2="${lec.SEMS_DATE}" data-lec-id2=${lec.LEC_ID } id="${lec.LEC_ID }">
		 			<td>${status.count}</td>
		 			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${lec.SJT_NAME }</td>
		 			<td>${lec.SJT_CREDIT }</td>			
		 			<td>${lec.USER_NAME }</td>					
		 		</tr>
	 			</tbody>
	 		</c:forEach>
	 	</table>
  </security:authorize>
 
 <script>
 $(".lecTr").on("click",function(){
	 let lecSems = $(this).data("lecSems");
	 let lecId = $(this).data("lecId");
	 location.href ="${cPath}/lecBoard/main?lecSems="+lecSems+"&lecId="+lecId; 
	 
 }).css('cursor', 'pointer');
 
 $(".cls").on("click",function(){
	 let lecSems2 = $(this).data("lecSems2");
	 let lecId2 = $(this).data("lecId2");
	 
	 location.href = "${cPath }/lecBoard/main?lecSems="+lecSems2+"&lecId="+lecId2
 }).css('cursor', 'pointer');
 
 </script>
 
  