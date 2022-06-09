<%--강의 공지 게시판 리스트 조회
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 5. 14.   황선부      최초작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<script type="text/javascript" src="${cPath }/resources/js/jquery.form.min.js"></script>
<style>
th{
	text-align: center;
}
#searchForm{
	display: none;

}
#searchDIV{
	padding: 1px;
}

#searchDIV select{
	width: 100px; 
 	display: inline; 
 }
#searchWord{
	width: 200px; 
 	display: inline; 
}
#searchBtn{
	width: 100px; 
 	display: inline; 
 }
 #pagingArea{
	margin-left: 43%;
}
#pagingTd{
	border-color: white;
}
#searchDIV{
	margin-left: 35%;
}
</style>


    <nav class="navbar navbar-expand-lg navbar-light bg-light">
		  <div class="container-fluid">
		    <div class="collapse navbar-collapse" id="navbarText">  
		      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
		            <li class="nav-item dropdown">
		          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
		           ${lecMap.LEC_NAME }
		          </a>
		          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
		            <li><a class="dropdown-item" href="#">Action</a></li>
		            <li><a class="dropdown-item" href="#">Another action</a></li>
		            <li><hr class="dropdown-divider"></li>
		            <li><a class="dropdown-item" href="#">Something else here</a></li>
		          </ul>
		        </li>
		        <li class="nav-item">
		          <a class="nav-link" aria-current="page" href="${cPath }/lecBoard/main?lecSems=${lecSems}&lecId=${lecId}">Home</a>
		        </li>
		        <li class="nav-item">
		          <a class="nav-link active" href="${cPath }/lecBoard/notice">강의공지</a>
		        </li>
		        <li class="nav-item">
		          <a class="nav-link" href="${cPath }/lecBoard/qna">질문과 답변</a>
		        </li>
		        <li class="nav-item">
		          <a class="nav-link" href="${cPath }/homework">과제 게시판</a>
		        </li>
		      </ul>
		      <span class="navbar-text">
<!-- 		        문화인류학 -->
		      </span>
		    </div>
		  </div>
	</nav>
<br>
<h3 class="h3-title">강의 공지사항</h3><hr class="hr-title">
<table class="table table-bordered">
	<thead class="thead thead-dark">
		<tr>
			<th>글번호</th>
			<th>글제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
	</thead>
	<tbody id="listBody">

	</tbody>
	  <security:authorize access="hasRole('ROLE_PROFESSOR')">

<!--             <button>ROLE_PROFESSOR</button> -->
            <button type="button" class="btn btn-primary btn-sm" onclick="location.href='${cPath}/lecBoard/new'">글쓰기</button>

       </security:authorize>
<%-- 	  <security:authorize access="hasRole('ROLE_STUDENT')"> --%>
<!--           <li class="nav-item text-nowrap"> -->
<!--             <button>ROLE_STUDENT</button> -->
<!--           </li> -->
<%--        </security:authorize> --%>
	<tfoot>
		<tr>
			<td colspan="6">
				<div id="pagingArea"></div>
<!-- 				<h4>검색용 UI</h4> -->
			</td>
		</tr>
	</tfoot>
</table>
				<div id="searchDIV">
					<select name="searchType" class="form-control mr-2">
						<option>전체</option>
						<option value="TITLE">제목</option>
						<option value="CONTENT">내용</option>
					</select> <input type="text" id="searchWord" name="searchWord" class="form-control mr-2" />
					<input type="button" value="검색" id="searchBtn"
						class="btn btn-primary" />
				</div>

<!-- 	<h4>Hidden Form</h4> -->
	<form id="searchForm">
		<input type="text" name="page" /> <input type="text" name="searchType" />
		<input type="text" name="searchWord" />
	</form>

<script type="text/javascript">
// <c:if test="${not empty message }">
// alert("${message}");
// </c:if>
	const VIEWURLPTRN = CONTEXTPATH+"/lecBoard/notice/%V";
// 	let modalWindow = $("#exampleModal").on("hidden.bs.modal", function(){
// 		$(this).find(".modal-body").empty();
// 	});
	let listBody = $("#listBody").on("click", "tr", function(){
		let viewURL = $(this).data("href");
		location.href=viewURL	
	}).css("cursor", "pointer");
	
	let pagingArea = $("#pagingArea");
	
	let searchForm = $("#searchForm").ajaxForm({
		dataType:"json"		
		, success:function(paging){
			listBody.empty();
			pagingArea.empty();
			searchForm[0].page.value="";
			
			let boardList = paging.dataList;
			let trTags = [];
			if(boardList && boardList.length > 0){
				$.each(boardList, function(idx, board){
					let viewURL = VIEWURLPTRN.replace("%V", board.lecboNo);
					let trTag = $("<tr>").data("href", viewURL)
										.prop("id","TR_"+board.lecboNo)//아이디를 숫자로만하면 무시됨.영문자와 숫자를 혼합해야함.
										.append(
											$("<td>").html(board.lecboNo)	
											, $("<td>").html(board.lecboTitle)	
											, $("<td>").html(board.userName)	
											, $("<td>").html(board.lecboDate)	
											, $("<td>").html(board.lecboHit)	
										);
					trTags.push(trTag);
				});
			}else{
				let trTag = $("<tr>").html(
								$("<td>").attr("colspan", "6")
										 .html(overNoDataCode)
							);
				trTags.push(trTag);
			} // if end
			
			listBody.append(trTags);
			pagingArea.html(paging.pagingHTMLBS)
			
		} // success end
		, resetForm : false
	}).submit();
	
	pagingArea.on("click", "a", function(event){
		event.preventDefault();
		let page = $(this).data("page");
		searchForm.find("[name=page]").val(page);
		searchForm.submit();
		return false;
	});
	
	let searchDIV = $("#searchDIV").on("click", "#searchBtn", function(){
		let inputs = searchDIV.find("[name]");
		$(inputs).each(function(index, ipt){
	//			console.log(this.name)
			let name = this.name;
			let value = $(this).val();
			searchForm.find("[name="+name+"]").val(value);
		});
		searchForm.submit();
	});
	
</script>
