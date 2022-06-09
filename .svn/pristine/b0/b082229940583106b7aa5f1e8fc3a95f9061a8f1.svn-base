<%--
 학사관리자가 당학기 등록금을 부과하는 페이지
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 5. 2.      김재웅      최초작성
* 2022. 5. 6.      고성식      기본 ui 작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<script type="text/javascript" src="${cPath }/resources/js/jquery.form.min.js"></script>    	
<h3 class="h3-title">등록금부과</h3><hr class="hr-title">
<style>
th{
	border-bottom: 1px solid #444444;
}

#action_in{
	text-align: right;
}

.pagination{
		justify-content: center;
}

#insertBtn{
	float: right;
}
</style>
<div id="searchArea">
	<label style="color:blue;">분과대학</label>
	<select class="select-search-long" id="collegeSelection">
		<option selected>전체</option>
	</select>&nbsp;&nbsp;&nbsp;
	<label style="color:blue;">소속학과</label>
	<select class="select-search-long" id="departmentSelection">
		<option selected>전체</option>
	</select>&nbsp;&nbsp;&nbsp;
	
	<br>
	<label style="color:blue;">단어검색</label>
	<select class="select-search-long" id="searchTypeSelection">
		<option value="" selected>전체</option>
		<option value="userName">이름</option>
		<option value="userPhone">전화번호</option>
	</select>&nbsp;&nbsp;
	<input type="text" id="searchWordInput" value="" style="width:350px; text-align:left; padding-left:10px;" />&nbsp;
	<button type="button" id="searchBtn" class="btn btn-primary">검색</button>&nbsp;
	<button type="button" id="defaultBtn" class="btn btn-secondary">검색초기화</button>
	<button type="submit" class="btn btn-primary btn-sm" onclick="confirmCreditEnrollStudent();" id="insertBtn">등록금부과</button>
</div>	
<div class="action_in">
</div>
<br>
<table class="table table-hover table-sm">
	<thead>
		<tr>
			<th scope="col" class="frst">
			<input type="checkbox" id="chkalltop" title="선택" class="check _click"></th>
			<th>학번</th>
			<th>이름</th>
			<th>분과대학</th>
			<th>학과</th>
			<th>학년</th>
			<th>학반</th>
			<th>등록금액(원)</th>
			<th>전화번호</th>
			<th>이메일</th>
		</tr>
	</thead>
	<tbody id="listBody">
	</tbody>
</table>
<div>
	<div id="pagingArea">
	</div>
</div>
<div id="extraArea">
<form id="searchForm">
	<input type="hidden" name="page" />
	<input type="hidden" name="colName"/>
	<input type="hidden" name="deptId"/>
	<input type="hidden" name="searchType" />
	<input type="hidden" name="searchWord" />
	<security:csrfInput/>
</form>
</div>
<script type="text/javascript">
let listBody = $("#listBody");
let pagingArea = $("#pagingArea");
let token = $("meta[name='_csrf']").attr("content");
let header = $("meta[name='_csrf_header']").attr("content");
let floatingTextarea = $("floatingTextarea");
//버튼
let searchBtn = $("#searchBtn");
let checkBtn = $("#checkBtn");
let cancelBtn = $("#cancelBtn");
let allApproveBtn = $("#allApproveBtn");
let allDenialBtn = $("#allDenialBtn");
let realDenialBtn = $("#realDenialBtn");
let denialBtn = $("denialBtn");
let defaultBtn = $("#defaultBtn");

//검색 영역 변수
let collegeSelection = $("#collegeSelection");
let departmentSelection = $("#departmentSelection");
let statusSelection = $("#statusSelection");
let submitSelection = $("#submitSelection");
let searchTypeSelection = $("#searchTypeSelection");
let searchWordInput = $("#searchWordInput");


//--hiddenInput
let page = $("input[name=page]");
let colName = $("input[name=colName]");
let deptId = $("input[name=deptId]");
let searchType = $("input[name=searchType]");
let searchWord = $("input[name=searchWord]");
//--------------------------------

$("#chkalltop").click(function() {
	if($("#chkalltop").is(":checked")) $("input[name=chk]").prop("checked", true);
	else $("input[name=chk]").prop("checked", false);
});


const VIEWURLPTRN = "${cPath}/setEnroll/enrollSetForm.do";
let searchForm = $("#searchForm").ajaxForm({
	dataType:"json"
	, success:function(paging){
		listBody.empty();
		pagingArea.empty();
		searchForm[0].page.value="";
		
		let enrollList = paging.dataList;
		let trTags = [];
		if(enrollList && enrollList.length > 0){
			$.each(enrollList, function(idx, enroll){
				var visibility = enroll.reqStat == null ? "visibile": "hidden"
				let trTag = $("<tr>").attr("id", enroll.userNo)
									.append(
												$("<td>").append($("<input>")
														.attr("type", "checkbox").attr("name", "chk")
														.attr("class","${enroll.userNo},${enroll.deptEnr}").css("visibility", visibility)
												)
												, $("<td>").html(enroll.userNo)
												, $("<td>").html(enroll.userName) 
												, $("<td>").html(enroll.colName)
												, $("<td>").html(enroll.deptName)
												, $("<td>").html(enroll.stuYear)
												, $("<td>").html(enroll.stuClass) 
												, $("<td>").html(enroll.deptEnr)
												, $("<td>").html(enroll.userPhone)
												, $("<td>").html(enroll.userMail)
									);
				trTags.push(trTag);
			});
		}else{
			let trTag = $("<tr>").html($("<td>").attr("colspan", "10").html(overNoDataCode));
			trTags.push(trTag);
		}
		
		listBody.append(trTags);
		pagingArea.html(paging.pagingHTMLBS)
	}
	, resetForm : false
}).submit();

pagingArea.on("click", "a", function(){
	let page = $(this).data("page");
	searchForm.find("[name=page]").val(page);
	searchForm.submit();
});

//분과대학 체인지 이벤트 발생시
collegeSelection.on("change", function(){
	let colName = $(this).val();
	departmentSelection.empty();
	$("input[name=deptId]").val("");
	
	if(colName == ""){
		departmentSelection.append($("<option>").attr("value", "").text("전체"));
	}else{
		departmentListFunc(colName);
	}
	$("input[name=colName]").val(colName);
});

//디파트먼트 체인지 이벤트 발생시
departmentSelection.on("change", function(){
	let deptId = $(this).val();
	$("input[name=deptId]").val(deptId);
});
//검색조건  체인지 이벤트 발생시
searchTypeSelection.on("change", function(){
	let searchType = $(this).val();
	$("input[name=searchType]").val(searchType);
});
//검색버튼 클릭시
searchBtn.on("click", function(){
	let searchWord = $("#searchWordInput").val();
	$("input[name=page]").val(1);
	$("input[name=searchWord]").val(searchWord);
	
	searchForm.submit();
});

//검색초기화
defaultBtn.on("click", function(){
	$("select").prop("selectedIndex",0);
	$("[name=page]").val(1);
	searchWordInput.val("");
	colName.val("");
	deptId.val("");
	searchType.val("");
	searchWord.val("");
	
	searchForm.submit();
});

function departmentListFunc(colName){
	$.ajax({
		url : "${cPath}/lecReq/departmentSelectionData.do",
		method : "get",
		data : {colName:colName},
		dataType : "json",
		success : function(resp) {
			departmentSelection.empty();
			
			let departList = resp.departList;
			let options = [];
			if(departList && departList.length > 0){
				let option = $("<option>").attr("value","").text("전체");
				options.push(option);
				$.each(departList, function(idx, depart){
					option = $("<option>").attr("value", depart.deptId).text(depart.deptName)
								.attr("data-col-name", depart.colName);
					options.push(option);
				});
			}else{
			} 
			departmentSelection.append(options);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR);
			console.log(textStatus);
			console.log(errorThrown);
		}
	});
};

function CollegeListFunc(){
	$.ajax({
		url : "${cPath}/lecReq/collegeSelectionData.do",
		method : "get",
		dataType : "json",
		success : function(resp) {
			collegeSelection.empty();
			let collegeList = resp.collegeList;
			let options = [];
			if(collegeList && collegeList.length > 0){
				let option = $("<option>").attr("value","").text("전체");
				options.push(option);
				$.each(collegeList, function(idx, college){
					option = $("<option>").attr("value", college.colName).text(college.colName);
					options.push(option);
				});
			}else{
			} 
			collegeSelection.append(options);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR);
			console.log(textStatus);
			console.log(errorThrown);
		}
	});
};
CollegeListFunc();

function confirmCreditEnrollStudent(){
	let enrNos = []
	let enrNoList = {};
	let names = listBody.find('input[name="chk"]:checked');
	
	if(names.length == 0){
		alert("등록금을 부과할 학생을 선택해주세요");
		return false;
	}
	
	$.each(names, function(idx, name){
		let thisName = $(this).parents("tr").attr("id");
		let thisEnr = $(this).parents('tr').children().eq(7).text();
		let jsonEle = {
			"userNo" : thisName
			,"enrAmt" : thisEnr
		};
		
		enrNos.push(jsonEle);
	});
	
	// 등록금 부과 처리
	$.ajax({
		url : "${cPath}/setEnroll/adminAppointEnroll.do",
		method : "post",
		data : JSON.stringify(enrNos),
		contentType : "application/json",
		dataType : "json",
		beforeSend : function(xhr) {
			if (token && header) {
				xhr.setRequestHeader(header, token);
			}
		},
		success : function(resp) {
			alert(enrNos.length+"명에게 등록금부과 완료");
			searchForm.submit();
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR);
			console.log(textStatus);
			console.log(errorThrown);
		}
	});  
	
}

//이벤트 Funtion
let eventFunction = function(){
	
	$("#confrimBtn").click(function(){
		confirmCreditEnrollStudent();
	});
	
}

// $(document).ready(function(){
// 	eventFunction();
// });
</script>
