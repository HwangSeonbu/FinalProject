<%--
 학사관리자가 졸업대상자를 조회하고 처리하는 페이지
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 5. 2.   김재웅 	     최초작성
* 2022. 5. 17.	고성식	     리스트 출력
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<script type="text/javascript" src="${cPath }/resources/js/jquery.form.min.js"></script>    
<h3 class="h3-title">졸업가능대상자관리</h3><hr class="hr-title">
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
	<button type="submit" class="btn btn-primary btn-sm" onclick="getCheckboxValue();" id="insertBtn">졸업</button>
</div>		

<table class="table table-sm table-hover">
	<thead class="thead-dark">
	
		<tr>
			<th scope="col" class="frst">
				<input type="checkbox" id="chkalltop" title="선택" onclick="checkAll()"></th>
			<th>학번</th>
			<th>이름</th>
			<th>분과대학명</th>
			<th>학과</th>
			<th>이수학점</th>
			<th>졸업가능기준학점</th>
			<th>학적상태</th>
			<th>휴대폰번호</th>
			<th>주소</th>
			<th>이메일</th>
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
	<input type="hidden" name="colName"/>
	<input type="hidden" name="deptId"/>
	<input type="hidden" name="searchType" />
	<input type="hidden" name="searchWord" />
	<security:csrfInput/>
</form>

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



const VIEWURLPTRN = "${cPath}/graduate/adminGraduateTargetList";
let listBody = $("#listBody");
let pagingArea = $("#pagingArea");
let token = $("meta[name='_csrf']").attr("content");
let header = $("meta[name='_csrf_header']").attr("content");
var nextSems = ${sessionScope.semsVo.nextSems};

let searchForm = $("#searchForm").ajaxForm({
	dataType:"json"
	, success:function(paging){
		listBody.empty();
		pagingArea.empty();
		searchForm[0].page.value="";
		
		let graduateList = paging.dataList;
		let trTags = [];
		if(graduateList && graduateList.length > 0){
			$.each(graduateList, function(idx, graduate){
				let trTag = $("<tr>")
									.attr("id", graduate.userNo)
									.append(
											$("<td>").append($("<input>")
													.attr("type", "checkbox").attr("name", "chk")
											)
												, $("<td>").html(graduate.userNo)
												, $("<td>").html(graduate.userName)
												, $("<td>").html(graduate.colName)
												, $("<td>").html(graduate.deptName)
												, $("<td>").html(graduate.stuCdtEarn)
												, $("<td>").html(graduate.stuGduCdt) 
												, $("<td>").html(graduate.stsCode1)
												, $("<td>").html(graduate.userPhone)
												, $("<td>").html(graduate.userAddr)
												, $("<td>").html(graduate.userMail)
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

$("#chkalltop").click(function() {
	if($("#chkalltop").is(":checked")) $("input[name=chk]").prop("checked", true);
	else $("input[name=chk]").prop("checked", false);
});


function getCheckboxValue()  {
	// 선택된 목록 가져오기
	let graduateNos = [];
	let graduateNoList = {};
	
	let names = listBody.find('input[name="chk"]:checked');
	
	// 졸업할 학생이 체크되었는지 확인
	if(names.length == 0){
		alert("졸업시킬 학생을 선택해주세요.");
		return false;
	}
	
	// 졸업할 학생 리스트 추출
	$.each(names, function(idx, name){
	  	let thisName = $(this).parents("tr").attr("id");
	  	
	  	let jsonEle = {
	  		"userNo" : thisName
	  		,"enrSems" : nextSems
	  	};
	  	
	  	graduateNos.push(jsonEle);
	});
	
	// 졸업 처리
	$.ajax({
		url : "${cPath}/targetGraduate/modifyAdminGraduateTarget.do",
		method : "post",
		data : JSON.stringify(graduateNos),
		contentType : "application/json",
		dataType : "json",
		beforeSend : function(xhr) {
			if (token && header) {
				xhr.setRequestHeader(header, token);
			}
		},
		success : function(resp) {
			alert("'졸업'으로 학적 변경이 완료되었습니다.");
			searchForm.submit();
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR);
			console.log(textStatus);
			console.log(errorThrown);
		}
	});
	  
}

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


