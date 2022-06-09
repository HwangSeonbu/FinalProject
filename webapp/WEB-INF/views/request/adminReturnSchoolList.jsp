<!--  학사관리자가 복학 신청 리스트를 확인하는 페이지(승인가능)  -->
<!--  [[개정이력(Modification Information)]]       -->
<!--  수정일        수정자     수정내용               -->
<!--  ==========   ======    ==============        -->
<!--  2022. 5. 7.    고성식 	 	   최초작성          -->
<!--  Copyright (c) 2022 by DDIT All right reserved -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<script type="text/javascript" src="${cPath }/resources/js/jquery.form.min.js"></script> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<style>
#processBtnBody{
		text-align: right;
}

.pagination{
	justify-content: center;
}
</style>
<h3 class="h3-title">복학 신청 현황</h3><hr class="hr-title">
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
		<option value="userNo">학번</option>
	</select>&nbsp;&nbsp;
	<input type="text" id="searchWordInput" value="" style="width:350px; text-align:left; padding-left:10px;" />&nbsp;
	<button type="button" id="searchBtn" class="btn btn-primary">검색</button>&nbsp;
	<button type="button" id="defaultBtn" class="btn btn-secondary">검색초기화</button>
	<div id="processBtnBody">
		<button type="submit" class="btn btn-primary btn-sm" onclick="getCheckboxValue();">선택승인</button>&nbsp;
		<button style="visibility:hidden;" type="button" id="realDenialBtn" data-bs-toggle="modal" data-bs-target="#denailModal"></button>
	</div>
</div>

<table class="table table-hover table-sm">
	<thead class="thead-dark">
		<tr>
			<th scope="col" class="frst">
			<input type="checkbox" id="chkalltop" title="선택" onclick="checkAll()"></th>
			<th>요청번호</th>
			<th>학번</th>
			<th>이름</th>
			<th>분과대학</th>
			<th>전공</th>
			<th>학년</th>
			<th>반</th>
			<th>복학학기</th>
			<th>진행상태</th>
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
let listBody = $("#listBody");
let pagingArea = $("#pagingArea");
let token = $("meta[name='_csrf']").attr("content");
let header = $("meta[name='_csrf_header']").attr("content");
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

$("#chkalltop").click(function() {
	if($("#chkalltop").is(":checked")) $("input[name=chk]").prop("checked", true);
	else $("input[name=chk]").prop("checked", false);
});
const VIEWURLPTRN = "${cPath}/req/returnSchoolList.do";
let searchForm = $("#searchForm").ajaxForm({
	dataType:"json"
	, success:function(paging){
		listBody.empty();
		pagingArea.empty();
		searchForm[0].page.value="";
		
		let stuReturnRequestList = paging.dataList;
		let trTags = [];
		if(stuReturnRequestList && stuReturnRequestList.length > 0){
			$.each(stuReturnRequestList, function(idx, stuReturnRequest){
				let trTag =  "<tr id=" + stuReturnRequest.userNo + ">";
					trTag += "<td>";
					// 대기인 경우만 체크박스 표시
					if(stuReturnRequest.reqStat == "B101"){
						trTag += "<input type='checkbox' name='chk'>";
					}
					trTag += "</td>";
					trTag += "<td>" + stuReturnRequest.reqId + "</td>";
					trTag += "<td>" + stuReturnRequest.userNo + "</td>";
					trTag += "<td>" + stuReturnRequest.userName + "</td>";
					trTag += "<td>" + stuReturnRequest.colName + "</td>";
					trTag += "<td>" + stuReturnRequest.deptName + "</td>";
					trTag += "<td>" + stuReturnRequest.stuYear + "</td>";
					trTag += "<td>" + stuReturnRequest.stuClass + "</td>";
					trTag += "<td>" + stuReturnRequest.reqSms + "</td>";
					trTag += "<td>" + stuReturnRequest.stsCode1 + "</td>";

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

//페이징 버튼 이벤트
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


//승인버튼 눌렀을때 이벤트
function getCheckboxValue()  {
	  // 선택된 목록 가져오기
	let returnNos = [];
	let returnNosList = {};
	
	let names = listBody.find('input[name="chk"]:checked');
	
	// 복학승인 할 학생이 체크되었는지 확인
	if(names.length ==0){
		alert("승인 할 학생을 선택해 주세요.");
		return false;
	}
	 
	// 복학을 승인할 학생을 추출
	$.each(names, function(idx, name){
		let thisName = $(this).parents("tr").attr("id");
	  	
	  	let jsonEle = {
	  		"userNo" : thisName
	  	};
	  	
	  	returnNos.push(jsonEle);
 	});
	//복학처리
	if(returnNos.length > 0){
		if(confirm("승인처리 후 변경할수 없습니다.\n"+returnNos.length+"명의 학생의 휴학을 승인하시겠습니까?")){
		}
		$.ajax({
			url : "${cPath}/req/modifyStuReturnTarget.do",
			method : "post",
			data : JSON.stringify(returnNos),
			contentType : "application/json",
			dataType : "json",
			beforeSend : function(xhr){
				if (token && header) {
					xhr.setRequestHeader(header, token);
				}
			},
			success : function(resp){
				alert("승인되었습니다.");
				searchForm.submit();
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR);
				console.log(textStatus);
				console.log(errorThrown);
			}
		});
	};
	
	  
}

</script>
