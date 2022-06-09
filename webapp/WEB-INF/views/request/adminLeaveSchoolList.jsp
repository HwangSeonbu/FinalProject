<!--  학사관리자가 휴학 신청 리스트를 확인하는 페이지(승인,반려가능)  -->
<!--  [[개정이력(Modification Information)]]       -->
<!--  수정일        수정자     수정내용               -->
<!--  ==========   ======    ==============        -->
<!--  2022. 5. 7.    고성식 	 	   최초작성          -->
<!--  Copyright (c) 2022 by DDIT All right reserved -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<script type="text/javascript" src="${cPath }/resources/js/jquery.form.min.js"></script>
<h3 class="h3-title">휴학 신청 현황</h3><hr class="hr-title">
<style>
#processBtnBody{
		text-align: right;
}

.pagination{
		justify-content: center;
}

</style>

<!-- 반려사유 모달 HTML START -->
<div class="modal fade" id="denailModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="denailModalLabel">반려사유 입력</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body" id="denailModalBody">
      	<div class="form-floating">
		  <textarea class="form-control" id="floatingTextarea" style="height: 300px"></textarea>
		  <label for="floatingTextarea">반려사유 입력</label>
		</div>
      </div>
      <div class="modal-footer">
        <button type="button" id="denialBtn" class="btn btn-danger btn-sm" data-req-id="">반려확정</button>
        <button type="button" id="cancelBtn" class="btn btn-secondary btn-sm" data-bs-dismiss="modal">취소</button>
      </div>
    </div>
  </div>
</div>
<!-- 반려사유 모달 HTML END -->
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
		<button type="button" class="btn btn-danger btn-sm denialBtn" data-bs-toggle="modal" data-bs-target="#denailModal">선택반려</button>
		<button style="visibility:hidden;" type="button" id="realDenialBtn" data-bs-toggle="modal" data-bs-target="#denailModal"></button>
	</div>
</div>
<div style="overflow-x: scroll;">
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
				<th>휴학종류</th>
				<th>휴학학기</th>
				<th>휴학시작일</th>
				<th>휴학종료일</th>
				<th>진행상태</th>
				<th>반려</th>
			</tr>
		</thead>		
		<tbody id="listBody">
		</tbody>
	</table>
</div>
<div>
	<div id="pagingArea">
	</div>
</div>

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

$("#chkalltop").click(function() {
	if($("#chkalltop").is(":checked")) $("input[name=chk]").prop("checked", true);
	else $("input[name=chk]").prop("checked", false);
});

const VIEWURLPTRN = "${cPath}/req/leaveSchoolList.do";
let searchForm = $("#searchForm").ajaxForm({
	dataType:"json"
	, success:function(paging){
		listBody.empty();
		pagingArea.empty();
		searchForm[0].page.value="";
		
		let stuLeaveRequestList = paging.dataList;
		let trTags = [];
		if(stuLeaveRequestList && stuLeaveRequestList.length > 0){
			$.each(stuLeaveRequestList, function(idx, stuLeaveRequest){
				let trTag =  "<tr id=" + stuLeaveRequest.reqId + ">";
					trTag += "<td>";
					// 대기인 경우만 체크박스 표시
					if(stuLeaveRequest.reqStat == "B101"){
						trTag += "<input type='checkbox' name='chk'>";
					}
					trTag += "</td>";
					trTag += "<td>" + stuLeaveRequest.reqId + "</td>";
					trTag += "<td>" + stuLeaveRequest.userNo + "</td>";
					trTag += "<td>" + stuLeaveRequest.userName + "</td>";
					trTag += "<td>" + stuLeaveRequest.colName + "</td>";
					trTag += "<td>" + stuLeaveRequest.deptName + "</td>";
					trTag += "<td>" + stuLeaveRequest.stuYear + "</td>";
					trTag += "<td>" + stuLeaveRequest.stuClass + "</td>";
					trTag += "<td>" + stuLeaveRequest.stsCode1 + "</td>";
					trTag += "<td>" + stuLeaveRequest.reqSms + "</td>";
					trTag += "<td>" + stuLeaveRequest.semsSdate + "</td>";
					trTag += "<td>" + stuLeaveRequest.semsEdate + "</td>";
					trTag += "<td>" + stuLeaveRequest.reqCode + "</td>";
					
					trTag += "<td>";
					// 대기인 경우만 반려 버튼 표시
					if(stuLeaveRequest.reqStat == "B101"){
						trTag += "<input type='button' class='btn btn-danger btn-sm denialBtn' data-req-id=" + stuLeaveRequest.reqId + " data-user-name=" + stuLeaveRequest.userName +
						" data-bs-toggle='modal' data-bs-target='#denailModal' value='반려'>";
					}
					trTag += "</td>";
				trTags.push(trTag);
			});
		}else{
			let trTag = $("<tr>").html($("<td>").attr("colspan", "13").html(overNoDataCode));
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
	let leaveNos = [];
	let leaveNosList = {};
	
	let names = listBody.find('input[name="chk"]:checked');
	
	// 휴학승인 할 학생이 체크되었는지 확인
	if(names.length ==0){
		alert("승인 할 학생을 선택해 주세요.");
		return false;
	}
	 
	// 휴학을 승인할 학생을 추출
	$.each(names, function(idx, name){
		let reqId = $(this).parents("tr").attr("id");
	  	let thisName = $(this).parents("tr").childe
		
	  	let jsonEle = {
	  		"userNo" : $("#userNo").val()
	  		,"reqId" : reqId
	  	};
	  	
	  	leaveNos.push(jsonEle);
 	});
	//휴학처리
	if(leaveNos.length > 0){
		if(confirm("승인처리 후 변경할수 없습니다.\n"+leaveNos.length+"명의 학생의 휴학을 승인하시겠습니까?")){
			$.ajax({
				url : "${cPath}/req/modifyStuLeaveTarget.do",
				method : "post",
				data : JSON.stringify(leaveNos),
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
		}else{
			return false;
		}
	};
	
	  
}	

// 반려확정 이벤트 : pickReqId 있으면 개인반려, 없으면 선택된 학생들 반려
$("#denialBtn").click(function(){
	let pickReqId = $("#denialBtn").data("reqId");
	let reqDenl = $("#floatingTextarea").val();
	let leaveNos = [];
	let leaveNosList = {};
	
	if(pickReqId != ""){	// 개인 반려
		let jsonEle = {
	  		"reqId" : pickReqId
	  		,"reqDenl" : reqDenl
	  	};
	  	
	  	leaveNos.push(jsonEle);
	}else{					// 선택(여러명) 반려
		let reqIds = listBody.find('input[name="chk"]:checked');
		
		// 반려 할 학생이 체크되었는지 확인
		if(reqIds.length ==0){
			alert("반려 할 학생을 선택해 주세요.");
			return false;
		}
		 
		// 반려할 승인할 학생을 추출
		$.each(reqIds, function(idx, name){
			let reqId = $(this).parents("tr").attr("id");
			
		  	let jsonEle = {
		  		"reqId" : reqId
		  		,"reqDenl" : reqDenl
		  	};
		  	
		  	leaveNos.push(jsonEle);
	 	});
	}

	//반려처리
	if(leaveNos.length > 0){
		$.ajax({
			url : "${cPath}/req/modifyStuReferLeaveTarget.do",
			method : "post",
			data : JSON.stringify(leaveNos),
			contentType : "application/json",
			dataType : "json",
			beforeSend : function(xhr){
				if (token && header) {
					xhr.setRequestHeader(header, token);
				}
			},
			success : function(resp){
				$("#cancelBtn").click();
				alert("반려처리되었습니다.");
				searchForm.submit();
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR);
				console.log(textStatus);
				console.log(errorThrown);
			}
		});
	};
	
});

//이벤트 Function
let eventFunction = function(){
	// 모달창 종료 시 이벤트
	$("#denailModal").on('hide.bs.modal', function () {
		$("#denailModalLabel").text("반려사유 입력");
		$("#denialBtn").data("reqId", "");
	});
	
	// 반려 버튼 이벤트 : 모달창 show > 확정반려 버튼에 reqId 값을 넣어준다.
	$(".denialBtn").on("click", function(){
		let pickReqId = $(this).data("reqId");
		let pickUserName = $(this).data("userName");
		
		$("#floatingTextarea").val("");
		
		if(pickReqId != null){
			$("#denailModalLabel").text("[학생 : "+pickUserName+"] 반려사유 입력");
		}
		
		$("#denialBtn").data("reqId", pickReqId);
	});
};



$(document).ready(function(){
	eventFunction();
});




	
</script>