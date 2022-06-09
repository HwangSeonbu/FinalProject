<%--
학생 리스트 출력 페이지
* [[개정이력(Modification Information)]]
* 수정일                 		수정자     			 수정내용
* ----------  		---------  	-----------------
* 2022. 4. 26.		고성식     		 최초작성
* 2022. 4. 27		고성식		리스트 출력
* 2022. 5. 02		고성식		페이징처리, 검색기능 추가
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<script type="text/javascript" src="${cPath }/resources/js/jquery.form.min.js"></script>
<h3 class="h3-title">재학생 조회</h3><hr class="hr-title">
<style>
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
		<option value="userPhone">휴대폰</option>
	</select>&nbsp;&nbsp;
	<input type="text" id="searchWordInput" value="" style="width:350px; text-align:left; padding-left:10px;" />&nbsp;
	<button type="button" id="searchBtn" class="btn btn-primary">검색</button>&nbsp;
	<button type="button" id="defaultBtn" class="btn btn-secondary">검색초기화</button>
	<button onclick="location.href='${cPath}/student/studentForm.do';" class="btn btn-primary btn-sm" id="insertBtn">신입생등록</button>
</div>		
<table class="table table-sm">
	<thead class="thead-dark">
		<tr>
			<th>학번</th>
			<th>분과대학</th>
			<th>학과</th>
			<th>이름</th>
			<th>학년</th>
			<th>반</th>
			<th>생년월일</th>
			<th>연락처</th>
			<th>이메일</th>
			<th>학적상태</th>
		</tr>
	</thead>
	<tbody id="listBody">
	</tbody>
	<tfoot>
		<tr>
			<td colspan="10">
			    <div id="pagingArea"></div>
			</td>
		</tr>
	</tfoot>
</table>
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
//검색 영역 변수
let collegeSelection = $("#collegeSelection");
let departmentSelection = $("#departmentSelection");
let statusSelection = $("#statusSelection");
let submitSelection = $("#submitSelection");
let searchTypeSelection = $("#searchTypeSelection");
let searchWordInput = $("#searchWordInput");

//버튼
let searchBtn = $("#searchBtn");
let defaultBtn = $("#defaultBtn");

//--hiddenInput
let page = $("input[name=page]");
let colName = $("input[name=colName]");
let deptId = $("input[name=deptId]");
let searchType = $("input[name=searchType]");
let searchWord = $("input[name=searchWord]");
//--------------------------------

const VIEWURLPTRN = "${cPath}/student/studentView.do?what=%V";
let listBody = $("#listBody");
let pagingArea = $("#pagingArea");

let searchForm = $("#searchForm").ajaxForm({
	dataType:"json"
	, success:function(paging){
		listBody.empty();
		searchForm[0].page.value="";
		
		let studentList = paging.dataList;
		let trTags = [];
		if(studentList && studentList.length > 0){
			$.each(studentList, function(idx, student){
				let viewURL = VIEWURLPTRN.replace("%V", student.userNo);
				let trTag = $("<tr>").addClass("linkBtn")
									.data("href", viewURL)
									.append(
												$("<td>").html(student.userNo)
												, $("<td>").html(student.colName)
												, $("<td>").html(student.deptName)
												, $("<td>").html(student.userName)
												, $("<td>").html(student.stuYear)	
												, $("<td>").html(student.stuClass)	
												, $("<td>").html(student.userReg1)
												, $("<td>").html(student.userPhone) 
												, $("<td>").html(student.userMail)
												, $("<td>").html(student.stsCode1)
									);
				trTags.push(trTag);
			});
		}else{
			let trTag = $("<tr>").html(
							$("<td>").attr("colspan", "10")
									 .html(overNoDataCode)
						);
			trTags.push(trTag);
		} // if end
		
		listBody.append(trTags);
		pagingArea.html(paging.pagingHTMLBS)
		
	} // success end
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
	//let colName = $(this).data("colName");	//작동안됨(undefined)
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

</script>