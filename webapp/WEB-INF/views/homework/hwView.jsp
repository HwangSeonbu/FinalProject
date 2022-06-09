<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 5. 23.      작성자명      최초작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<link href="${cPath }/resources/css/offcanvas.css" rel="stylesheet">
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
.shadow-sm {
    box-shadow: 0 0.125rem 0.25rem rgb(0 0 0 / 8%) !important;
}
/* table td:second-child{ */
/* 	text-align: left; */
/* } */
/* #nav1{ */
/* 	background-color: tomato; */
/* } */
/* #nav2{ */
/* 	background-color: gray; */
/* } */
#nav3{
 	box-shadow: 20px; 
}

/* .shadow-sm { */
/*     box-shadow: 30%; */
/* } */
</style>
<h3 class="h3-title">과제 보기</h3><hr class="hr-title">
<nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
  <ol class="breadcrumb">
    <li class="breadcrumb-item"><a href="#">home</a></li>
    <li class="breadcrumb-item"><a href="${cPath}/homework">목록</a></li>
    <li class="breadcrumb-item active" aria-current="page">새글</li>
  </ol>
</nav>
    <hr>

  <security:authentication property="principal" var="user" />
	 <c:if test="${user.realUser.memRole eq 'ROLE_PROFESSOR'}">
	  <button class="btn btn-primary" id="updateBtn" onclick="location.href='${cPath}/homework/${homework.lechwkNo}/form'">수정</button>
<!-- 	  <button id="delete" >삭제</button> -->
	  <form id="deleteForm" method="post" action="${cPath}/homework/${homework.lechwkNo}">
		  <security:csrfInput/> 
	  	<input type="hidden" name="_method" value="delete"/>
	  	<input type="hidden" id="lechwkNo" name="lechwkNo" value="${homework.lechwkNo}"/>
	  	<input class="btn btn-primary" type="submit" value="삭제"/>  
	  </form>
 </c:if>

  <table class="table table-bordered" >
  	<thead class="table-light">
  		<tr>
  			<td class="column">
  			과제명
  			</td>
  			<td>
  			${homework.lechwkName }
  			</td>
  		</tr>
  	</thead>
  	<tbody>
  		<tr>
  			<td class="column">
  			과제 등록일
  			</td>
  			<td>
  			${homework.registDate }
  			</td>
  		</tr>
  		<tr>
  			<td class="column">
  			과제 마감일
  			</td>
  			<td>
  			${homework.lechwkDate }
  			</td>
  		</tr>
  		<tr>
  			<td class="column">
  			조회수
  			</td>
  			<td>
  			${homework.hit }
  			</td>
  		</tr>
  		<tr>
  			<td class="column">
  			과제 제출률
  			</td>
  			<td>
  			${homework.submittedStu}/${homework.wholeStu }
  			<c:set var="num" value="${(homework.submittedStu)/(homework.wholeStu)*100}" />
  			<fmt:formatNumber type="number" pattern="0.0" value="${num }"/>%
  			<br>
  			</td>
  		</tr>
  		<tr>
  			<td colspan="2">
  			${homework.htmlContent }	
  			</td>

  		</tr>
  		<tr>
  			<td class="column">
  			첨부파일
  			</td>
  			<td>
  				<c:set value="${homework.attach }" var="attach"></c:set>
	  			<c:url value="/lecBoard/qna/${homework.lechwkNo }/attach/${attach.attchNo }"  var="downloadURL"></c:url>
				<a href="${downloadURL }">${attach.attchFname }</a>  		 				
  			</td>
  		</tr>
			 <tr>
				<th colspan="2">평가지표</th>
			</tr> 
	  		<c:forEach items="${homework.evaList }" var="eva">
			<tr>
			  	<th>
			  		${eva.evaStd }
			  	</th> 
			  	<th>
			  		${eva.evaScore }
			  	</th> 	  		  		  	
		  	</tr>	
	  		</c:forEach>
  	</tbody>
  </table>
  <security:authorize access="hasRole('ROLE_PROFESSOR')">
	  <table class="table table-bordered">
		  	<thead></thead>
		  	<tbody>
		  		 
		  	<tr>
			  	<th>
			  		학번
			  	</th>
			  	<th>
			  		이름
			  	</th> 	  	
			  	<th>
			  		제출일
			  	</th> 	
			  	<th>
			  	</th>
		 	
		  	</tr>
		   	<c:if test="${not empty homework.hwkList}">
		  		<c:forEach items="${homework.hwkList }" var="hw">
				  	<tr>
					  	<th>
					  		${hw.stuNo }
					  	</th> 
					  	<th>
					  		${hw.stuName }
					  	</th> 	  		  	
					  	<th>
					  		${hw.hwkDate}
					  	</th> 	  	  	
					  	<th>
					  		<button class="btn-primary" type="button" onclick="window.open('${cPath}/homework/${homework.lechwkNo}/stu/${hw.hwkNo}')">과제 평가</button>
					  	</th> 	  	
				  	</tr>
		  		</c:forEach>  	
		  	</c:if>
		  	<c:if test="${empty homework.hwkList }">
		  		<tr>
		  			<th colspan="4">
		  				제출한 학생이 없습니다.
		  			</th>
		  		</tr>
		  	</c:if>       
       </security:authorize>
	  
 
  	
  	</tbody>
  </table>
	  <security:authorize access="hasRole('ROLE_STUDENT')">
		<!-- Button trigger modal -->
			<c:if test="${empty hwk }">
			
				<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#hwModal">
				  과제 제출하기
				</button>							
			</c:if>
			<c:if test="${not empty hwk }">
				<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#hwModal2">
				  과제 수정하기
				</button>											
			</c:if>
			
       </security:authorize>
  
  
  
  
  
<!--   <main class="container"> -->
<!--   <div class="d-flex align-items-center p-3 my-3 text-white bg-purple rounded shadow-sm" border="1"> -->
<!--     <img class="me-3" src="/docs/5.1/assets/brand/bootstrap-logo-white.svg" alt="" width="48" height="38"> -->
<!--     <div class="lh-1"> -->
<!--       <h1 class="h6 mb-0 text-white lh-1">Bootstrap</h1> -->
<!--       <small>Since 2011</small> -->
<!--     </div> -->
<!--   </div> -->

<!--   <div id="nav2" class="my-3 p-3 bg-body rounded shadow-sm" border="1"> -->
<!--     <h6 class="border-bottom pb-2 mb-0">Recent updates</h6> -->
<!--     <div class="d-flex text-muted pt-3"> -->
<!--       <svg class="bd-placeholder-img flex-shrink-0 me-2 rounded" width="32" height="32" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: 32x32" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#007bff"/><text x="50%" y="50%" fill="#007bff" dy=".3em">32x32</text></svg> -->

<!--       <p class="pb-3 mb-0 small lh-sm border-bottom"> -->
<!--         <strong class="d-block text-gray-dark">@username</strong> -->
<!--         Some representative placeholder content, with some information about this user. Imagine this being some sort of status update, perhaps? -->
<!--       </p> -->
<!--     </div> -->
<!--     <div class="d-flex text-muted pt-3"> -->
<!--       <svg class="bd-placeholder-img flex-shrink-0 me-2 rounded" width="32" height="32" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: 32x32" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#e83e8c"/><text x="50%" y="50%" fill="#e83e8c" dy=".3em">32x32</text></svg> -->

<!--       <p class="pb-3 mb-0 small lh-sm border-bottom"> -->
<!--         <strong class="d-block text-gray-dark">@username</strong> -->
<!--         Some more representative placeholder content, related to this other user. Another status update, perhaps. -->
<!--       </p> -->
<!--     </div> -->
<!--     <div class="d-flex text-muted pt-3"> -->
<!--       <svg class="bd-placeholder-img flex-shrink-0 me-2 rounded" width="32" height="32" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: 32x32" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#6f42c1"/><text x="50%" y="50%" fill="#6f42c1" dy=".3em">32x32</text></svg> -->

<!--       <p class="pb-3 mb-0 small lh-sm border-bottom"> -->
<!--         <strong class="d-block text-gray-dark">@username</strong> -->
<!--         This user also gets some representative placeholder content. Maybe they did something interesting, and you really want to highlight this in the recent updates. -->
<!--       </p> -->
<!--     </div> -->
<!--     <small class="d-block text-end mt-3"> -->
<!--       <a href="#">All updates</a> -->
<!--     </small> -->
<!--   </div> -->

<!--   <div id="nav3" class="my-3 p-3 bg-body rounded shadow-sm"> -->
<!--     <h6 class="border-bottom pb-2 mb-0">Suggestions</h6> -->
<!--     <div class="d-flex text-muted pt-3"> -->
<!--       <svg class="bd-placeholder-img flex-shrink-0 me-2 rounded" width="32" height="32" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: 32x32" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#007bff"/><text x="50%" y="50%" fill="#007bff" dy=".3em">32x32</text></svg> -->

<!--       <div class="pb-3 mb-0 small lh-sm border-bottom w-100"> -->
<!--         <div class="d-flex justify-content-between"> -->
<!--           <strong class="text-gray-dark">Full Name</strong> -->
<!--           <a href="#">Follow</a> -->
<!--         </div> -->
<!--         <span class="d-block">@username</span> -->
<!--       </div> -->
<!--     </div> -->
<!--     <div class="d-flex text-muted pt-3"> -->
<!--       <svg class="bd-placeholder-img flex-shrink-0 me-2 rounded" width="32" height="32" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: 32x32" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#007bff"/><text x="50%" y="50%" fill="#007bff" dy=".3em">32x32</text></svg> -->

<!--       <div class="pb-3 mb-0 small lh-sm border-bottom w-100"> -->
<!--         <div class="d-flex justify-content-between"> -->
<!--           <strong class="text-gray-dark">Full Name</strong> -->
<!--           <a href="#">Follow</a> -->
<!--         </div> -->
<!--         <span class="d-block">@username</span> -->
<!--       </div> -->
<!--     </div> -->
<!--     <div class="d-flex text-muted pt-3"> -->
<!--       <svg class="bd-placeholder-img flex-shrink-0 me-2 rounded" width="32" height="32" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: 32x32" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#007bff"/><text x="50%" y="50%" fill="#007bff" dy=".3em">32x32</text></svg> -->

<!--       <div class="pb-3 mb-0 small lh-sm border-bottom w-100"> -->
<!--         <div class="d-flex justify-content-between"> -->
<!--           <strong class="text-gray-dark">Full Name</strong> -->
<!--           <a href="#">Follow</a> -->
<!--         </div> -->
<!--         <span class="d-block">@username</span> -->
<!--       </div> -->
<!--     </div> -->
<!--     <small class="d-block text-end mt-3"> -->
<!--       <a href="#">All suggestions</a> -->
<!--     </small> -->
<!--   </div> -->
<!-- </main> -->
  
  
  <!-- Modal -->
<div class="modal fade" id="hwModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
   <form action="${cPath }/homework/submit" method="post" enctype="multipart/form-data">
    <security:csrfInput />
    
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">제출</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
       	<input type="hidden" name="lechwkNo" value="${homework.lechwkNo}"/>
          <div class="mb-3">
            <label for="recipient-name" class="col-form-label">첨부파일</label>
            <input type="file" name="boFile" class="form-control" id="recipient-name">
          </div>
          <div class="mb-3">
            <label for="message-text" class="col-form-label" >첨부할 내용</label>
            <textarea class="form-control" name="hwkContent" id="message-text" style="height: 100px"></textarea>
          </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="submit" class="btn btn-primary">과제 제출</button>
      </div>
    </div>
        </form>
  </div>
</div>


  <!-- Modal -->
<div class="modal fade" id="hwModal2" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
   <form id="updateForm" action="${cPath }/homework/submitUpdate" method="post" enctype="multipart/form-data">
    <security:csrfInput />
    
	  <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">제출</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
      	<input type="hidden" name="hwkNo" value="${hwk.hwkNo}"/>
       	<input type="hidden" name="lechwkNo" value="${homework.lechwkNo}"/>
          <div class="mb-3">
            <label for="recipient-name" class="col-form-label">등록일</label>
            <h6>${hwk.hwkDate }</h6>
          </div>
			<c:set value="${hwk.attach}" var="attach" />
          <div class="mb-3">
            <label for="recipient-name" class="col-form-label">첨부파일</label>
			<c:if test="${not empty attach.attchNo }">
				<span>
					${attach.attchFname}
					<input type="button" value="삭제" class="attatchDelBtn"
						data-att-no="${attach.attchNo }"/>
			<!-- 이미지를 넣어도됨  -->
				</span>					
			</c:if>		
            <input id="file" type="file" name="boFile" class="form-control" id="recipient-name">
          </div>
          <div class="mb-3">
            <label for="message-text" class="col-form-label" >첨부할 내용</label>
            <textarea class="form-control" name="hwkContent" id="message-text" style="height: 100px">${hwk.hwkContent}</textarea>
          </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="submit" class="btn btn-primary">다시 제출</button>
      </div>
    </div>
        </form>
  </div>
</div>
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  <script>
  
  let boardEditForm = $("#updateForm")
  <c:if test="${not empty attach.attchNo }">
  $("#file").hide();
  </c:if>
  $(".attatchDelBtn").on("click",function(){
		$("#file").show();
		let attNo = $(this).data("attNo");
		$(this).parents("span:first").hide();
		let newInput = $("<input>").attr({
							"type":"hidden"
							,"name":"delAttNo"
							,"value":attNo
						})
		boardEditForm.append(newInput);
	});
  $("#list").on("click",function(){
	  location.href="${cPath}/homework";
  })
  $("#updateBtn").on("click",function(){
	  location.href="${cPath}/homework/${homework.lechwkNo}/form";
  })
  
  
  </script>