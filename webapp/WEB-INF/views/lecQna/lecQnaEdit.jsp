<%--
*  강의 문의게시판 수정 폼.
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 5. 21.      황선부      최초작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<script type="text/javascript" src="${cPath}/resources/js/fullckeditor/ckeditor.js"></script>
<style>
#buttonDiv{
	margin : 5px auto;
	width : 300px;
}
th{
	width: 200px;
	text-align: left;
	margin: 20px
}
 .errorMessage{ 
 	display:none;		
} 
</style>
<h3 class="h3-title">게시글 수정하기</h3><hr class="hr-title">
<hr>

<form:form modelAttribute="board" method="post" enctype="multipart/form-data" id="boardEditForm"
	 action="${cPath}/lecBoard/qna/${board.lecboNo}" class="border border-primary">
	<form:hidden path="lecboNo"/>
	<table class="table">
<!-- 		<tr> -->
<!-- 			<th>게시물구분</th> -->
<%-- 			<td><form:input path="lecDiv" class="form-control" /> --%>
<%-- 				<form:errors path="lecDiv" class="error" element="span" /></td> --%>
<!-- 		</tr> -->
		<tr>
			<th>글제목</th>
			<td><form:input path="lecboTitle" class="form-control" />
				<form:errors path="lecboTitle" class="error " cssClass="errorMessage" element="span" /></td>
		</tr>
		<tr>
			<th>글내용</th>
			<td><form:textarea path="lecboContent" class="form-control" />
				<form:errors path="lecboContent" class="error" cssClass="errorMessage" element="span" /></td>
		</tr>
				<tr>
			<th>첨부파일 등록</th>
			<td>
				<c:set value="${board.attach}" var="attach" />
					<c:if test="${not empty attach.attchNo }">
						<span>
							${attach.attchFname}
							<input type="button" value="삭제" class="attatchDelBtn"
								data-att-no="${attach.attchNo }"/>
					<!-- 이미지를 넣어도됨  -->
						</span>					
					</c:if>
				
				<input id="file" type="file" name="boFile" class="form-control"/>			
			</td>
		</tr>
<!-- 		<tr> -->
<!-- 			<th>비밀번호</th> -->
<%-- 			<td><form:password path="lecboPass" required="true" --%>
<%-- 					class="form-control" /> --%>
<%-- 				<form:errors path="boPass" class="error" element="span" /></td> --%>
<!-- 		</tr> -->
		<tr>
			<td colspan="2">
				<div id=buttonDiv>
					<form:button type="submit" class="btn btn-success">저장</form:button>
					<input type="button" onclick="location.href='${cPath}/lecBoard/notice'" 
					class="btn btn-secondary linkBtn" value="목록으로"/>
				</div>
			</td>
		</tr>
	</table>
</form:form>
<script>
$(document).ready(function(){
	
    var name = $(".errorMessage").html();
//     console.log("=========== ", typeof name);
//     console.log("=========== ", name);
//     console.log("=========== ", typeof name != "undefined");
    
    if (typeof name != 'undefined'){
    	if ("" != name || null != name){
//     		var newName = name.substring(0,name.length-1)
        	alert(name);	
        }	
    }
});

// <c:if test="${not empty message }">
// alert("${message}");
// </c:if>
// CKEDITOR.replace('lecboContent'
//         , {height: 800                                                  
//          });
CKEDITOR.replace('lecboContent',{height: 800 
// 	,filebrowserImageUploadUrl : "${cPath}/board/image?type=image"
});
<c:if test="${not empty attach.attchNo }">
$("#file").hide();
</c:if>
let boardEditForm =$("#boardEditForm");
$(".attatchDelBtn").on("click",function(){
$("#file").show();
	
	let attNo = $(this).data("attNo");
	$(this).parents("span:first").hide();
	let newInput = $("<input>").attr({
						"type":"text"
						,"name":"delAttNo"
						,"value":attNo
					})
	boardEditForm.append(newInput);
});
</script>