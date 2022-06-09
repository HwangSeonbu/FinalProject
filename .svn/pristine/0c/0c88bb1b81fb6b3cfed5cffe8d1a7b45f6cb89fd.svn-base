<!--  [[개정이력(Modification Information)]]       -->
<!--  수정일      		  수정자  	   수정내용               -->
<!--  ==========   ======    ==============        -->
<!--  2022. 5. 6.   고성식  	   최초작성             -->
<!--  Copyright (c) 2022 by DDIT All right reserved -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<script type="text/javascript" src="${cPath }/resources/js/jquery.form.min.js"></script>
<style>
.pagination{
	justify-content: center;
}
</style>

<h3 class="h3-title">성적우수장학생선정</h3><hr class="hr-title">
<div>
	<select id="lecSems">
		<c:forEach items="${semsdata}" var="list">
			<option value="${list}">${list}</option>
		</c:forEach>
	</select>
	<button type="submit" id="confrimBtn" class="btn btn-primary btn-sm" onclick="confirmCreditScholarStudent();">성적우수장학생선정</button>
</div>
<table class="table table-sm">
	<thead class="thead-dark">
		<tr>
			<th scope="col" class="frst" >
			<input type="checkbox" id="chkalltop" title="선택" class="check _click"></th>
			<th>학번</th>
			<th>이름</th>
			<th>학과</th>
			<th>전학기성적</th>
			<th>학년</th>
			<th>반</th>
			<th>핸드폰번호</th>
			<th>이메일</th>
			<th>순위</th>
			<th>장학선정여부</th>
		</tr>
	</thead>
	<tbody id="listBody">
	</tbody>
	<tfoot>
		<tr>
			<td colspan="10">
				<div id="pagingArea">
				</div>
			</td>
		</tr>
	</tfoot>
</table>
<form id="searchForm">
	<input type="hidden" name="page" />
	<input type="hidden" name="searchType" />
	<input type="hidden" name="searchWord" />
	<security:csrfInput/>
</form>

<script>

let token = $("meta[name='_csrf']").attr("content");
let header = $("meta[name='_csrf_header']").attr("content");
let listBody = $("#listBody");
var thisSems = ${sessionScope.semsVo.thisSems};
let page = "1";

// 이벤트 Function
let eventFunction = function(){
	
	// 체크박스 전체선택 이벤트
	$("#chkalltop").click(function() {
		if($("#chkalltop").is(":checked")) $("input[name=chk]").prop("checked", true);
		else $("input[name=chk]").prop("checked", false);
	});
	
	// 페이징 클릭 이벤트
	$("#pagingArea").on("click", "a", function(){
		page = $(this).data("page");
		selectList(page);
	});
	
	// 수강학기 변경 이벤트
	$("#lecSems").on("change", function(){
		if(parseInt($("#lecSems").val()) == thisSems){
			$("#confrimBtn").show();
		}else{
			$("#confrimBtn").hide();
		}
		
		selectList(1);
	});
	
}

// 리스트 조회
function selectList(page)  {
	var param = {
		page : 	page,
		lecSems : $("#lecSems").val()
	}
	
	$.ajax({
		url : "${cPath}/setScholar/setCreditScholarStudentList.do",
		method : "post",
		data : param,
		dataType : "json",
		beforeSend : function(xhr) {
			if (token && header) {
				xhr.setRequestHeader(header, token);
			}
		},
		success : function(data) {
			$("#listBody").empty();
			
			let scholarshipList = data.list;
			let trTags = [];
			if(scholarshipList && scholarshipList.length > 0){
				$.each(scholarshipList, function(idx, scholarship){
					var visibility = scholarship.reqStat == null ? "visibile": "hidden"
					let trTag = $("<tr>").attr("id", scholarship.userNo)
										.append(
													$("<td>").append($("<input>")
															.attr("type", "checkbox").attr("name", "chk")
															.attr("class","${scholarship.userNo}").css("visibility", visibility)
													)
													, $("<td>").html(scholarship.userNo)
													, $("<td>").html(scholarship.userName)
													, $("<td>").html(scholarship.deptName)	
													, $("<td>").html(scholarship.stuScore)
													, $("<td>").html(scholarship.stuYear) 
													, $("<td>").html(scholarship.stuClass)
													, $("<td>").html(scholarship.userPhone)
													, $("<td>").html(scholarship.userMail)
													, $("<td>").html(scholarship.stuRank)
													, $("<td>").addClass("td-sts").html(scholarship.stsCode1)
										);
					trTags.push(trTag);
				});
			}else{
				let trTag = $("<tr>").html($("<td>").attr("colspan", "9").html(overNoDataCode));
				trTags.push(trTag);
			}
			
			$("#listBody").append(trTags);
			$("#pagingArea").html(data.paging.pagingHTMLBS);
			$("#chkalltop").prop("checked", false);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR);
			console.log(textStatus);
			console.log(errorThrown);
		}
	});
};	

//성적우수 장학생으로 선정
function confirmCreditScholarStudent()  {
	//선택한 목록 가져오기  
	let schNos = [];
	let schNoList = {};
	
	let names = listBody.find('input[name="chk"]:checked');
	var userNos = "";
	// 장학생이 체크되었는지 확인
	if(names.length == 0){
		alert("성적우수장학생으로 선발할 학생을 선택해주세요.");
		return false;
	}
	
	// 현재학기 포함 이후로 선정 시 선정할 수 없도록 처리
	if(parseInt($("#lecSems").val()) != thisSems){
		alert("해당학기만 성적우수장학생을 선정할 수 있습니다.");
		return false;
	}
	
	
	// 졸업할 학생 리스트 추출
	$.each(names, function(idx, name){
      	let thisName = $(this).parents("tr").attr("id");
      	userNos = thisName;
      	let jsonEle = {
    	  		"userNo" : thisName,
    	  		"lecSems" : $("#lecSems").val()
    	};
      	
      	schNos.push(jsonEle);
   	});
	  
	// 성적우수 장학생으로 처리
	$.ajax({
		url : "${cPath}/setScholar/adminAppointScholar.do",
		method : "post",
		data : JSON.stringify(schNos),
		contentType : "application/json",
		dataType : "json",
		beforeSend : function(xhr) {
			if (token && header) {
				xhr.setRequestHeader(header, token);
			}
		},
		success : function(resp) {
			alert(schNos.length+"명을 성적우수장학생으로 선택 완료");
			selectList(1);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR);
			console.log(textStatus);
			console.log(errorThrown);
		}
	});  
}

$(function(){
	eventFunction();
	$("#lecSems").val(thisSems);
	selectList(1);
	
	
	
});
</script>