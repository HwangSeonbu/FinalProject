<%--
교수의 강의계획 상세 입력양식
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 5. 10.      김재웅      최초작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<h3 class="h3-title">강의계획서 상세정보 작성</h3><hr class="hr-title">
<style>
	#subjectArea{
		width: 1000px;
		height: 300px;
	}
	#subjectContainer{
		position: absolute;
/* 		border: 1px solid blue; */
		width: 990px;
		height: 260px;
		margin-top: 10px;
	}
	#subjectListBody{
		position: absolute;
		border: 1px solid black;
		width: 1000px;
		height: 210px;
		overflow-y: scroll;
	}
	#subjectPageBody{
		position: absolute;
		top: 215px;
/* 		border: 1px solid black; */
		width: 985px;
		height: 40px;
	}
	#sbjTbl{width: 100%; border-collapse:collapse;
		text-align: center;}
	#sbjTbl th{background-color:#dee2e6;}
	#detailFormTbl{
		width: 1000px;
	}
	#detailWplanTbl{width: 1000px;}
	th{
		text-align:center;
		width: 100px;
		background-color: skyblue;
	}
	.detailInput{width: 100%; height: 100%;}
	.pagination{
		padding-left: 350px;
	}
	#stsTd{text-align: center; font-weight: bold; font-size: 15px;}
</style>

<input type="button" class="btn btn-primary" id="saveBtn" value="임시저장" />
<input type="button" class="btn btn-success" id="submitBtn" value="제출" />
<input type="button" class="btn btn-secondary" id="backBtn" value="목록" />
<br><hr><br>
<div id="subjectArea">
	<select class="select-search-short" id="majorSelection">
		<option value="" selected>전체</option>
		<option value="전">전공</option>
		<option value="교">교양</option>
	</select>
	<input type="text" placeholder="과목명" />
	<input type="button" class="btn btn-primary btn-sm" id="searchBtn" value="과목검색" />
	<input type="button" class="btn btn-danger btn-sm" id="cancelBtn" value="선택취소" /><br>
	<div id="subjectContainer">
		<div id="subjectListBody">
			<table class="table" id="sbjTbl">
				<tr>
					<th>코드</th>
					<th>과목구분</th>
					<th>과목명</th>
					<th>대상학년</th>
					<th>이수학점</th>
					<th>선택</th>
				</tr>
				<tbody id="subjectListRealBody">
					<tr>
						<td colspan="6">과목을 검색해주세요.</td>
					</tr>
				</tbody>
			</table>
		</div><br>
		<div id="subjectPageBody">
		</div>
	</div>
</div>
<%-- <form:form modelAttribute="planEditVo" method="post" id="planEditForm" enctype="multipart/form-data"> --%>
<form id="planEditForm" action="${cPath }/lecPlan/LecturePlanSubmit.do" method="post" >
<security:csrfInput/>
<table id="detailFormTbl" class="table table-bordered">
	<thead>
		<tr>
			<th>계획번호</th>
			<td>${planEditVo.planNo }<input type="hidden" value="${planEditVo.planNo }" name="planNo" /></td>
			<th>교수명</th>
			<td colspan="3">${planEditVo.userName }</td>
			<th>처리상태</th>
			<td id="stsTd">${planEditVo.planSts }</td>
		</tr>
	</thead>
	<tbody id="requiredTbl">
		<tr>
			<th>소속</th>
			<td colspan="7">${planEditVo.deptName }</td>
		</tr>
		<tr>
			<th>과목</th>
			<td colspan="7">
				<input class="detailInput input-target input-required" value="${planEditVo.sjtName }" type="text" name="sjtName" readonly />
				<input class="detailInput input-target" value="${planEditVo.sjtId }" type="hidden" name="sjtId" readonly />
			</td>
		</tr>
		<tr>
			<th>강의명</th>
			<td colspan="7">
				<input class="detailInput input-target input-required" value="${planEditVo.planSmry }" name="planSmry" type="text" />
			</td>
		</tr>
		<tr>
			<th>대상학년</th>
			<td>
				<input class="detailInput input-target input-required" value="${planEditVo.planYear }" name="planYear" type="text" readonly/>
			</td>
			<th>정원</th>
			<td>
				<input type="number" 
					class="input-target input-required" value="${planEditVo.planLimit }" name="planLimit" style="width:100px;" />
			</td>
			<th>시수</th>
			<td>
				<label><input class="input-target" type="radio" name="planTcnt" value="1"/>1시수</label>
				<label><input class="input-target" type="radio" name="planTcnt" value="2"/>2시수</label>
			</td>
			<th>평가구분</th>
			<td>
				<label><input class="input-target" type="radio" name="planEval" value="상대"/>상대</label>
				<label><input class="input-target" type="radio" name="planEval" value="절대"/>절대</label>
			</td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td colspan="7"></td>
		</tr>
		
	</tbody>
</table>
	<input type="hidden" name="wplanParamList"/>
</form>

<table class="table table-bordered table-hover" id="detailWplanTbl">
	<thead>
		<tr><th colspan="2">주차별 강의계획 설명</th></tr>
	</thead>
	<tbody>
		<tr><th>1주차</th><td><input id="w1" name="wplanCont" class="detailInput input-target" type="text" /></td></tr>
		<tr><th>2주차</th><td><input id="w2" name="wplanCont" class="detailInput input-target" type="text" /></td></tr>
		<tr><th>3주차</th><td><input id="w3" name="wplanCont" class="detailInput input-target" type="text" /></td></tr>
		<tr><th>4주차</th><td><input id="w4" name="wplanCont" class="detailInput input-target" type="text" /></td></tr>
		<tr><th>5주차</th><td><input id="w5" name="wplanCont" class="detailInput input-target" type="text" /></td></tr>
		<tr><th>6주차</th><td><input id="w6" name="wplanCont" class="detailInput input-target" type="text" /></td></tr>
		<tr><th>7주차</th><td><input id="w7" name="wplanCont" class="detailInput input-target" type="text" /></td></tr>
		<tr><th>8주차</th><td><input id="w8" name="wplanCont" class="detailInput input-target" type="text" /></td></tr>
		<tr><th>9주차</th><td><input id="w9" name="wplanCont" class="detailInput input-target" type="text" /></td></tr>
		<tr><th>10주차</th><td><input id="w10" name="wplanCont" class="detailInput input-target" type="text" /></td></tr>
		<tr><th>11주차</th><td><input id="w11" name="wplanCont" class="detailInput input-target" type="text" /></td></tr>
		<tr><th>12주차</th><td><input id="w12" name="wplanCont" class="detailInput input-target" type="text" /></td></tr>
		<tr><th>13주차</th><td><input id="w13" name="wplanCont" class="detailInput input-target" type="text" /></td></tr>
		<tr><th>14주차</th><td><input id="w14" name="wplanCont" class="detailInput input-target" type="text" /></td></tr>
		<tr><th>15주차</th><td><input id="w15" name="wplanCont" class="detailInput input-target" type="text" /></td></tr>
		<tr><th>16주차</th><td><input id="w16" name="wplanCont" class="detailInput input-target" type="text" /></td></tr>
	</tbody>
</table>
<div class="redLine" id="extraArea"></div>
<c:set var="WPlanList" value="${planEditVo.WPlanList }" />

<form id="searchForm">
<security:csrfInput/>
		<input type="hidden" name="holdPage"/>
		<input type="hidden" name="holdMajor"/>
		<input type="hidden" name="holdName"/>
</form>
<script>
let subjectListRealBody = $("#subjectListRealBody");
let subjectPageBody = $("#subjectPageBody");
let majorSelection = $("#majorSelection");

let inputSjtId = $("[name=sjtId]");
let inputSjtName = $("[name=sjtName]");
let inputSjtGrade = $("[name=planYear]");

let defaultSjtId = "${planEditVo.sjtId }";
let defaultSjtName = "${planEditVo.sjtName }";
let defaultSjtGrade = "${planEditVo.planYear }";
let defaultPlanSts = "${planEditVo.planSts }";

function planStsColor(){
	if(defaultPlanSts == "대기"){
		$("#stsTd").css("color", "blue");
	}else{
		$("#stsTd").css("color", "red");
	}
};
planStsColor();

let planEditForm = $("#planEditForm");
let changeFlag = "";

	let planEval = "${planEditVo.planEval}";
	let planTcnt = "${planEditVo.planTcnt}";
	if(planEval != null)
		$("input[value='"+planEval+"']").prop("checked", true);
	if(planTcnt != null)
		$("input[value='"+planTcnt+"']").prop("checked", true);
	
	let wplanNo = "";
	let wplanW = "";
	let wplanCont = "";
	
<c:if test="${not empty WPlanList}">
	<c:forEach items="${WPlanList }" var="wplan">
	
		wplanNo = "${wplan.wplanNo }";
		wplanW = "${wplan.wplanW }";
		wplanCont = "${wplan.wplanCont}";
		
		$("#w"+wplanW).val(wplanCont).attr("data-wplan-no", wplanNo);
		
	</c:forEach>
</c:if>	


$("#saveBtn").on("click", function(){
	if(changeFlag == 0){
		alert("저장할 데이터가 없습니다.");
		return false;
	}
	let wplanInputs = $("input[name='wplanCont']");
	
	let wplans = [];
	
	$.each(wplanInputs, function(idx, wplaninput){
		let targetInputValue = $(this).val();
		if(targetInputValue != ""){
			let targetData = $(this).data("wplanNo");
			let targetInputId = $(this).attr("id");
			targetInputId = targetInputId.substring(1);
			let jsonEle = "";
			jsonEle = { "wplanW" : targetInputId
					, "wplanCont" : targetInputValue
					, "wplanNo" : targetData};
			wplans.push(jsonEle);
		}
	});
	
	$("input[name='wplanParamList']").val(JSON.stringify(wplans));
	
	if(confirm("임시저장하시겠습니까?")){
		changeFlag = "";
		planEditForm.submit();
	}else{
		return false;
	}
});

$(".input-target").on("input", function() {
	changeFlag = 1;
});

$("#backBtn").on("click", function(){
	if(changeFlag == 1){
		if(confirm("변경된 값이 저장되지 않았습니다. 이동하시겠습니까?")){
			location.href = "${cPath}/lecPlan/LecturePlanForm.do";
		}
	}else{
		location.href = "${cPath}/lecPlan/LecturePlanForm.do";
	}
});

function subjectList() {
	let sjtMajor = $("[name=holdMajor]").val();
	let sjtName = $("[name=holdName]").val();
	let page = $("[name=holdPage]").val();
	
	$.ajax({
		url : "${cPath}/lecPlan/subjectListData.do",
		method : "get",
		data : {sjtMajor : sjtMajor
				, sjtName : sjtName
				, page : page},
		dataType : "json",
		success : function(resp) {
			subjectListRealBody.empty();
			subjectPageBody.empty();
			
			let paging = resp.pagingVo;
			let subjectList = paging.dataList;
			
			let trTags = [];
			if(subjectList && subjectList.length > 0){
				$.each(subjectList, function(idx, subject){
					let trTag = $("<tr>").append(
											$("<td>").html(subject.sjtId)	
											, $("<td>").html(subject.sjtMajor)	
											, $("<td>").html(subject.sjtName)	
											, $("<td>").html(subject.sjtGrade)	
											, $("<td>").html(subject.sjtCredit)	
											, $("<td>").append($("<input>").attr("type", "button")
															.attr("value", "선택").addClass("clickSubject btn btn-primary btn-sm")
															.data("sjt-name", subject.sjtName)
															.data("sjt-grade", subject.sjtGrade)
															.data("sjt-id", subject.sjtId)
											)
										);
					trTags.push(trTag);
				});
			}else{
				let trTag = $("<tr>").html(
								$("<td>").attr("colspan", "6")
										 .html("조건에 맞는 과목이 없습니다.")
							);
				trTags.push(trTag);
			} // if end
			subjectListRealBody.append(trTags);
			subjectPageBody.html(paging.pagingHTMLBS)
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR);
			console.log(textStatus);
			console.log(errorThrown);
		}
	});
};

$("#searchBtn").on("click", function(){
	let searchName = $(this).siblings("input[type=text]").val();
	$("[name=holdName]").val(searchName);
	$("[name=holdPage]").val("");
	
	subjectList();
}); 

$("#cancelBtn").on("click", function(){
	inputSjtId.val(defaultSjtId);
	inputSjtName.val(defaultSjtName);
	inputSjtGrade.val(defaultSjtGrade);
});

$("#submitBtn").on("click", function(){
	if(changeFlag != 0){
		alert("저장을 먼저 해주세요.");
		return false;
	}
	$("#requiredTbl").find("td").css("background-color", "");
	let requiredInputs = $(".input-required");
	let chkCnt = 0;
	
	$.each(requiredInputs, function(idx, input){
		let valChk = $(this).val();
		if(valChk == null || valChk == ""){
			$(this).parent("td").css("background-color", "yellow");
			alert("필수입력사항이 누락 되었습니다.");
			return false;
		}
	});
	
	let requiredTcnt = $("input[name=planTcnt]:checked").val();
	let requiredEval = $("input[name=planEval]:checked").val();
	
	if(requiredTcnt == null || requiredTcnt == ""){
		$("input[name=planTcnt]").parents("td").css("background-color", "yellow");
		alert("강의시수를 선택해주세요.");
		return false;
	}
	
	if(requiredEval == null || requiredEval == ""){
		$("input[name=planEval]").parents("td").css("background-color", "yellow");
		alert("평가구분을 선택해주세요.");
		return false;
	}
	planSubmit();
});


subjectPageBody.on("click", "a", function(){
	let page = $(this).data("page");
	$("[name=holdPage]").val(page);
	
	subjectList();
});

majorSelection.on("change", function(){
	let searchMajor = $(this).val();
	
	$("[name=holdMajor]").val(searchMajor);
});

subjectListRealBody.on("click", ".clickSubject", function(){
	let sjtId = $(this).data("sjtId");
	let sjtName = $(this).data("sjtName");
	let sjtGrade = $(this).data("sjtGrade");
	
	inputSjtId.val(sjtId);
	inputSjtName.val(sjtName);
	inputSjtGrade.val(sjtGrade);
	
	changeFlag = 1;
});


function planSubmit(){
	if(confirm("제출하시면 수정할 수 없습니다. 제출하시겠습니까?")){
		let planNo = $("input[name=planNo]").val();
		location.href = "${cPath}/lecPlan/LecturePlanSubmit.do?planNo="+planNo;
	}
};
</script>




