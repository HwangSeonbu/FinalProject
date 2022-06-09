<%--
* 강의 공지사항 게시판 상세페이지
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 5. 16.   황선부      최초작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<style>
.table{
	width: 1200px;
}
#title{
	font-weight: bold;
	font-size: x-large;
	text-align: center;
}
.column{
	font-weight: bold;
	text-align: center;
}
table td:first-child{
	width: 150px;
}
#deleteForm{
	display: inline;
}
/* table td:second-child{ */
/* 	text-align: left; */
/* } */
</style>
<h3 class="h3-title">강의 공지사항</h3><hr class="hr-title">

<nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
  <ol class="breadcrumb">
    <li class="breadcrumb-item"><a href="#">home</a></li>
    <li class="breadcrumb-item"><a href="${cPath}/lecBoard/notice">목록</a></li>
    <li class="breadcrumb-item active" aria-current="page">새글</li>
  </ol>
</nav>

    <hr>
  <table class="table table-bordered">
  	<thead class="table-light">
  		<tr>
  			<td id="title" colspan="2">${board.lecboTitle}</td>
  		</tr>
  	</thead>
  	<tbody>
  		<tr>
  			<td class="column">
  			작성자
  			</td>
  			<td>
  			${board.userName }
  			</td>
  		</tr>
  		<tr>
  			<td class="column">
  			등록일
  			</td>
  			<td>
  			${board.lecboDate }
  			</td>
  		</tr>
  		<tr>
  			<td class="column">
  			조회수
  			</td>
  			<td>
  			${board.lecboHit }
  			</td>
  		</tr>
  		<tr>
  			<td class="column">
  			첨부파일
  			</td>
  			<td>
		  		<c:forEach items="${board.attchList }" var="attach" varStatus="vs">
		  			<c:url value="/lecBoard/notice/${board.lecboNo }/attach/${attach.attchNo }"  var="downloadURL"></c:url>
					<a href="${downloadURL }">${attach.attchFname }</a>  		
			  		<c:if test="${not vs.last}">
			  			<hr>
			  		</c:if>
		  		</c:forEach>
  			
  			</td>
  		</tr>
  		<tr>
  			<td colspan="2">
  			${board.lecboContent }
  			</td>
  		</tr>
  	</tbody>
  	
  	<tfoot>
  	<tr>
	  	<th>
	  		FOOTER
	  	</th> 	
  	</tr>
  	</tfoot>
  </table>
  <hr>
  <button id="list">목록</button>
  <security:authorize access="hasRole('ROLE_PROFESSOR')">
	  <button id="edit" onclick="location.href='${cPath}/lecBoard/notice/${board.lecboNo}/form'">수정</button>
<!-- 	  <button id="delete" >삭제</button> -->
	  <form id="deleteForm" method="post" action="${cPath}/lecBoard/notice/${board.lecboNo}">
  <security:csrfInput/> 
	  	<input type="hidden" name="_method" value="delete"/>
	  	<input type="hidden" name="lecboNo" value="${board.lecboNo }"/>
	  	<input type="submit" value="삭제"/>  
	  </form>
   </security:authorize>
  
  <script>
  $("#list").on("click",function(){
	  location.href="${cPath}/lecBoard/notice";
  })
  
  </script>