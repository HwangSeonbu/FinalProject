<%--
 교수의 강의별 출석 데이터 등록 페이지
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 5. 2.      김재웅      최초작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h3 class="h3-title">출결등록 관리</h3><hr class="hr-title">
<style>
#lectureSelection{width: 350px; float: left; margin-right: 15px;}
#dateSelection{width: 250px; float: left; margin-right: 15px;}
#searchArea{height: 65px;}
#prevDateBtn, #nextDateBtn{margin-top: 10px;}
#btnArea{width: 70%;}
.table{
	border-top:2px solid black;
	width: 70%;
	text-align: center;
}
.td-unSet{color:red; font-weight: bold;}
.td-set{color:green; font-weight: bold;}

/* 노데이터 css code 있던 자리 */
</style>
<div class="redLine" id="searchArea">
	<select class="form-select form-select-lg mb-3" id="lectureSelection">
		<c:set value="${proLectureList }" var="lecList"/>
		<c:if test="${not empty lecList }">
			<option>강의선택</option>
			<c:forEach items="${lecList }" var="lecture">
				<option id="${lecture.lecId}" data-plan-no="${lecture.planNo}">${lecture.lecName }</option>
			</c:forEach>
		</c:if>
		<c:if test="${empty lecList }">
			<option>진행강의 없음</option>
		</c:if>
	</select>
	<select class="form-select form-select-lg mb-3" id="dateSelection">
		<option>강의선택 필요</option>
	</select>
	<input type="button" id="prevDateBtn" value="이전일자" class="btn btn-primary" disabled="disabled"/>
	<input type="button" id="nextDateBtn" value="다음일자" class="btn btn-primary" disabled="disabled"/>
</div>

<div class="redLine" id="btnArea">
	<input type="button" id="allCancelBtn" value="초기화" class="btn btn-secondary" disabled="disabled"/>
	<input type="button" id="allSaveBtn" value="전체저장" class="btn btn-success" disabled="disabled"/>
</div>

<table class="table table-bordered" id="dataTable">
	<thead>
		<tr class="tr-headLine">
			<th rowspan="2" style="width:80px;">순번</th>
			<th rowspan="2" style="width:150px;">학번</th>
			<th rowspan="2" style="width:100px;">이름</th>
			<th rowspan="2" style="width:150px;">상태</th>
			<th>출석</th>
			<th>결석</th>
			<th>지각</th>
			<th>조퇴</th>
			<th rowspan="2" style="width:90px;">저장</th>
		</tr>
		<tr class="tr-headLine">
			<th><input class="form-check-input" type="checkbox" id="mainD101Chk"/></th>
			<th><input class="form-check-input" type="checkbox" id="mainD102Chk"/></th>
			<th><input class="form-check-input" type="checkbox" id="mainD103Chk"/></th>
			<th><input class="form-check-input" type="checkbox" id="mainD104Chk"/></th>
		</tr>
	</thead>
	<tbody id="mainTbody">
		<tr><td colspan="9">
			<div class="div-imageWrap" id="imageWrap">
				<div class="div-imageInnerWrap">
					<img alt="" src="${cPath }/resources/img/noData.jpg" style="width:100%;"/>
				</div>
				<div class="div-textInnerImage"> 강의와 조회일자를<br>선택해주세요.</div>
			</div>
		</td></tr>
	</tbody>
</table>

<div class="redLine" id="extraArea">
</div>


<script>
//----noData image code
var noDataCode = "<div class='div-imageWrap' id='imageWrap'><div class='div-imageInnerWrap'>";
noDataCode += "<img alt='' src='${cPath }/resources/img/noData.jpg' style='width:100%;'/></div>";
noDataCode += "<div class='div-textInnerImage'> 조건에 맞는 데이터가<br>없습니다.</div></div>";

var needDataCode = "<div class='div-imageWrap' id='imageWrap'><div class='div-imageInnerWrap'>";
needDataCode += "<img alt='' src='${cPath }/resources/img/noData.jpg' style='width:100%;'/></div>";
needDataCode += "<div class='div-textInnerImage'> 강의와 조회일자를<br>선택해주세요.</div></div>";
//-----------




let listFlag = false;
let changeFlag = false;

let pickLecId = "";
let pickPlanNo = "";
let pickDate = "";

let mainTbody = $("#mainTbody");

let lectureSelection = $("#lectureSelection");
let dateSelection = $("#dateSelection");
let currentDateOption = "";

let mainD101Chk = $("#mainD101Chk");
let mainD102Chk = $("#mainD102Chk");
let mainD103Chk = $("#mainD103Chk");
let mainD104Chk = $("#mainD104Chk");

let allSaveBtn = $("#allSaveBtn");
let allCancelBtn = $("#allCancelBtn");
let nextDateBtn = $("#nextDateBtn");
let prevDateBtn = $("#prevDateBtn");

lectureSelection.on("change", function(){
	nextDateBtn.attr("disabled", true);
	prevDateBtn.attr("disabled", true);
	allSaveBtn.attr("disabled", true);
	allCancelBtn.attr("disabled", true);
	mainTbody.empty();
	for(var i=0;i<10000;i++){}
	console.log("포문끝");
	mainTbody.append($("<tr>").append($("<td>").attr("colspan", "9").html(needDataCode)));
	
	pickLecId = $(this).find(":selected").attr("id");
	pickPlanNo = $(this).find(":selected").data("planNo");
	
	if(pickLecId == null){
		dateSelection.empty();
		dateSelection.append($("<option>").text("출결일자"));
	}else{
		$.ajax({
			url : "${cPath}/enrollAttabs/attabsDateListData.do",
			method : "get",
			data : {lecId : pickLecId},
			dataType : "json",
			success : function(resp) {
				dateSelection.empty();
				let dateList = resp.dateList;
				let options = [];
				options.push($("<option>").attr("value", "").text("날짜선택"));
				$(dateList).each(function(index, date){
					let option = $("<option>").attr("value", date)
											  .text(date.substr(4,2)+"월 "+date.substr(6)+"일");
					options.push(option);
				});
				dateSelection.append(options);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR);
				console.log(textStatus);
				console.log(errorThrown);
			}
		});
	}
	
});

dateSelection.on("change", function(){
	nextDateBtn.attr("disabled", false);
	prevDateBtn.attr("disabled", false);
	currentDateOption = $(this).find(":selected");
	pickDate = currentDateOption.val();
	
	studentList();
});
nextDateBtn.on("click", function(){
	let nextDateOption = currentDateOption.next().val();
	if(changeFlag){
		if(confirm("변경된 값이 저장되지 않았습니다. 이동하시겠습니까?")){
			changeFlag = false;
			pickDate = nextDateOption;
			dateSelection.val(nextDateOption).trigger("change");
			studentList();
		}
	}else{
		changeFlag = false;
		pickDate = nextDateOption;
		dateSelection.val(nextDateOption).trigger("change");
		studentList();
	}
});
prevDateBtn.on("click", function(){
	let prevDateOption = currentDateOption.prev().val();
	if(changeFlag){
		if(confirm("변경된 값이 저장되지 않았습니다. 이동하시겠습니까?")){
			changeFlag = false;
			pickDate = prevDateOption;
			dateSelection.val(prevDateOption).trigger("change");
			studentList();
		}
	}else{
		changeFlag = false;
		pickDate = prevDateOption;
		dateSelection.val(prevDateOption).trigger("change");
		studentList();
	}
});

function studentList(){
	mainD101Chk.prop("checked", false);
	mainD102Chk.prop("checked", false);
	mainD103Chk.prop("checked", false);
	mainD104Chk.prop("checked", false);
	$.ajax({
		url : "${cPath}/enrollAttabs/attabsStudentListData.do",
		method : "get",
		data : {lecId : pickLecId, attabsDate : pickDate, planNo : pickPlanNo},
		dataType : "json",
		success : function(resp) {
			mainTbody.empty();
			let studentList = resp.studentList;
			
			let trTags = [];
			if(studentList && studentList.length > 0){
				var stuCnt = 0;
				$.each(studentList, function(idx, student){
					stuCnt++;
					let trTag = $("<tr>").addClass("attabsTr").attr("data-attabs-code", student.attabsCode)
						.attr("data-attabs-no", student.attabsNo).attr("data-assign-no", student.assignNo)
						.attr("id", student.userNo).append(
							$("<td>").html(stuCnt)
							, $("<td>").html(student.userNo)
							, $("<td>").html(student.userName)
							, $("<td>").addClass("D100")
							, $("<td>").append($("<input>").addClass("D101").attr("type", "radio").attr("name", student.userNo).attr("data-attabs-code", "D101"))
							, $("<td>").append($("<input>").addClass("D102").attr("type", "radio").attr("name", student.userNo).attr("data-attabs-code", "D102"))
							, $("<td>").append($("<input>").addClass("D103").attr("type", "radio").attr("name", student.userNo).attr("data-attabs-code", "D103"))
							, $("<td>").append($("<input>").addClass("D104").attr("type", "radio").attr("name", student.userNo).attr("data-attabs-code", "D104"))
							, $("<td>").append($("<input>").addClass("btn btn-primary btn-sm saveBtn").attr("type", "button").attr("value", "저장"))
					);	
					trTags.push(trTag);
				});
				allSaveBtn.attr("disabled", false);
				allCancelBtn.attr("disabled", false);
			}else{
				allSaveBtn.attr("disabled", true);
				allCancelBtn.attr("disabled", true);
				let trTag = $("<tr>").append($("<td>").attr("colspan", "9").html(noDataCode));
				trTags.push(trTag);
			}
			mainTbody.append(trTags);
			attabsDispatcher();
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR);
			console.log(textStatus);
			console.log(errorThrown);
		}
	});
};


function attabsDispatcher(){
	let attabsTrs = mainTbody.find(".attabsTr");
	
	$.each(attabsTrs, function(){
		let attabsCode = $(this).data("attabsCode");
		if(attabsCode == null || attabsCode == ""){
			$(this).find("[class=D100]").addClass("td-unSet").text("미입력");
		}else{
			$(this).find("[class=D100]").addClass("td-set").text("입력");
			$(this).find("input[class="+attabsCode+"]").prop("checked", true);
		}
	})
	
	mainTbody.find("input[type=radio]").addClass("form-check-input");
}

mainTbody.on("click", ".saveBtn", function(){
	let targetTr = $(this).parents("tr");
	
	let attabsNo = targetTr.data("attabsNo");
	let assignNo = targetTr.data("assignNo");
	let userNo = targetTr.attr("id");
	let attabsCode = targetTr.find("input[type=radio]:checked").data("attabsCode");
	
	if(attabsCode == null){
		alert("출결을 선택해주세요.")
	}else{
		if(changeFlag){
			changeFlag = false;
			$.ajax({
				url : "${cPath}/enrollAttabs/attabsInsertOrUpdateOne.do",
				method : "get",
				data : {attabsNo:attabsNo, attabsDate:pickDate, attabsCode:attabsCode
					, lecId:pickLecId, userNo:userNo, assignNo:assignNo, planNo:pickPlanNo
				},
				dataType : "json",
				success : function(resp) {
					alert("출결저장 완료");
					studentList();
				},
				error : function(jqXHR, textStatus, errorThrown) {
					console.log(jqXHR);
					console.log(textStatus);
					console.log(errorThrown);
				}
			});
		}else{
			alert("변경사항이 없습니다.");
		}
	}
});

$(document).on("change", "input[type=radio]", function(){
	changeFlag = true;
});

allCancelBtn.on("click", function(){
	if(changeFlag){
		changeFlag = false;
		mainD101Chk.prop("checked", false);
		mainD102Chk.prop("checked", false);
		mainD103Chk.prop("checked", false);
		mainD104Chk.prop("checked", false);
		studentList();
	}else{
		alert("변경사항이 없습니다.");
	}
});

allSaveBtn.on("click", function(){
	if(changeFlag){
		changeFlag = false;
		alert("일괄저장");
		let attabsTrs = $(".attabsTr");
		let dataList = [];
		
		$.each(attabsTrs, function(idx, attabsTr){
			let attabsNo = $(this).data("attabsNo");
			let assignNo = $(this).data("assignNo");
			let userNo = $(this).attr("id");
			let attabsCode = $(this).find("input[type=radio]:checked").data("attabsCode");
			
			let data = {attabsNo:attabsNo, attabsDate:pickDate, attabsCode:attabsCode
				, lecId:pickLecId, userNo:userNo, assignNo:assignNo, planNo:pickPlanNo
			};
			dataList.push(data);
		});
		
		let jsonEle = { "targetList" : JSON.stringify(dataList) };
		
		$.ajax({
			url : "${cPath}/enrollAttabs/attabsInsertOrUpdate.do",
			method : "get",
			data : jsonEle,
			dataType : "json",
			success : function(resp) {
				alert("출결저장 완료");
				studentList();
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR);
				console.log(textStatus);
				console.log(errorThrown);
			}
		});
	}else{
		alert("저장할 데이터가 없습니다.");
	}
});
mainD101Chk.prop("checked", false);
mainD102Chk.prop("checked", false);
mainD103Chk.prop("checked", false);
mainD104Chk.prop("checked", false);

mainD101Chk.on("click", function(){
	changeFlag = true;
	mainD102Chk.prop("checked", false);
	mainD103Chk.prop("checked", false);
	mainD104Chk.prop("checked", false);
	if(mainD101Chk.prop("checked")){ 
		mainTbody.find(".D101").prop("checked", true);
	}else{ 
		mainTbody.find(".D101").prop("checked", false);W
	}
});
mainD102Chk.on("click", function(){
	changeFlag = true;
	mainD101Chk.prop("checked", false);
	mainD103Chk.prop("checked", false);
	mainD104Chk.prop("checked", false);
	if(mainD102Chk.prop("checked")){ 
		mainTbody.find(".D102").prop("checked", true);
	}else{ 
		mainTbody.find(".D102").prop("checked", false);
	}
});
mainD103Chk.on("click", function(){
	changeFlag = true;
	mainD101Chk.prop("checked", false);
	mainD102Chk.prop("checked", false);
	mainD104Chk.prop("checked", false);
	if(mainD103Chk.prop("checked")){ 
		mainTbody.find(".D103").prop("checked", true);
	}else{ 
		mainTbody.find(".D103").prop("checked", false);
	}
});
mainD104Chk.on("click", function(){
	changeFlag = true;
	mainD101Chk.prop("checked", false);
	mainD102Chk.prop("checked", false);
	mainD103Chk.prop("checked", false);
	if(mainD104Chk.prop("checked")){ 
		mainTbody.find(".D104").prop("checked", true);
	}else{ 
		mainTbody.find(".D104").prop("checked", false);
	}
});

</script>