<%--
	관리자의 강의계획 개설처리
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 4. 29.      김재웅      최초작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<h3 class="h3-title">강의개설 처리</h3><hr class="hr-title">
<style>
	.col{
		border: 1px solid black;
		height: 40px;
		padding-top: 5px;
	}
	.title{
		text-align: center;
		background-color: #dee2e6;
		font-weight: bold;
	}
	#searchArea{
/*  		border: 1px solid red;  */
		width: 95%;
		height:100px;
	}
	.select-search-long{
		width: 150px;
	}
	.select-search-short{
		width: 70px;
	}
	#btnArea{
/*  		border: 1px solid red;  */
		width: 95%;
		height:50px;
		text-align: right;
	}
	.table{
		border-top:2px solid black;
		width: 100%;
	}
	#planListBody{
/* 		border: 1px solid red; */
		width: 95%;
		overflow-y: scroll;
	}
	#planPageBody{
/* 		border: 1px solid red; */
		width: 95%;
		height: 50px;border-top: 2px solid black;
		padding-top: 10px;
	}
	.pagination{
		justify-content: center;
	}
	#extraArea{
/*  		border: 1px solid red;  */
		width: 95%;
		height:300px;
	}
	
	.tr-headLine{
		text-align: center;
		vertical-align: middle;
		background-color:#ebebeb;
	}
	.tr-targetHover:hover{
		background-color:#ededed;
	}
	.enrollBtn{font-size: 8px;}
	a {
  		text-decoration-line: none;
	}
	
	.tr-pickTarget{
		background-color: #212f52;
		color:white;
	}
	.tr-pickTarget a{
		color:yellow;
		font-weight: bold;
	}
	.td-leftAlign{
		text-align: left;
	}
	.btn-sfont{
		margine:2px;
	}
	#divView { 
		position:absolute; 
		display:none; 
		background-color:#ffffff; 
		border:solid 2px #d0d0d0; 
		width:350px; height:150px; 
		padding:10px; 
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
	<label style="color:blue;">배정상태</label>
	<select class="select-search-short" id="statusSelection">
		<option value="" selected>전체</option>
		<option value="배정">배정</option>
		<option value="미배정">미배정</option>
	</select>&nbsp;&nbsp;&nbsp;
	
	<label style="color:blue;">배정제출상태</label>
	<select class="select-search-short" id="submitSelection">
		<option value="" selected>전체</option>
		<option value="Y">제출</option>
		<option value="N">미제출</option>
	</select>&nbsp;&nbsp;&nbsp;<br>
	
	<br><label style="color:blue;">단어검색</label>
	<select class="select-search-long" id="searchTypeSelection">
		<option value="" selected>전체</option>
		<option value="userName">교수명</option>
		<option value="sjtName">과목명</option>
		<option value="planSmry">강의계획명</option>
	</select>&nbsp;&nbsp;<input type="text" id="searchWordInput" value="" style="width:350px; text-align:left; padding-left:10px;" />
	&nbsp;<button type="button" id="searchBtn" class="btn btn-primary">검색</button>&nbsp;
	<button type="button" id="defaultBtn" class="btn btn-secondary">검색초기화</button><br><br>
</div>
<div id="btnArea">
	<button type="button" id="possibleBtn" class="btn btn-primary" value="">개설가능 강의만 보기</button>
	<button type="button" id="allEstablishBtn" class="btn btn-success" value="B104">선택개설</button>
</div>
<div id="planListBody">
	<table class="table table-bordered" id="planTable">
		 <thead>
		    <tr class="tr-headLine">
		    	<th ><input id="checkAll" type="checkbox" /></th>	
		    	<th >계획<br>번호</th>	
		    	<th >분과대학</th>	
		    	<th >소속학과</th>	
		    	<th >학과<br>교수</th>	
		    	<th >강의<br>교수</th>	
		    	<th >강의과목</th>	
		    	<th >강의계획명</th>
		    	<th >시수</th>	
		    	<th >강의관</th>	
		    	<th >강의실</th>	
		    	<th >시간1</th>	
		    	<th >시간2</th>
		    	<th >배정<br>상태</th>
		    	<th >배정<br>제출</th>
		    	<th >개설</th>
		    </tr>
		 </thead>
		 <tbody class="text-center" id="planListTbody">
		 </tbody>
	</table>
</div>

<div id="planPageBody"></div>
<div id="extraArea">
	<form id="searchForm">
	<security:csrfInput/> 
		<input type="hidden" name="holdPage"/>
		<input type="hidden" name="holdColName"/>
		<input type="hidden" name="holdDeptId"/>
		<input type="hidden" name="holdAssignSts"/>
		<input type="hidden" name="holdAssignSubmit"/>
		<input type="hidden" name="holdSearchType"/>
		<input type="hidden" name="holdSearchWord"/>
	</form>
</div>
<div id="divView"></div>


<script>
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
let possibleBtn = $("#possibleBtn");

//--hiddenInput
let holdAssignSts = $("input[name=holdAssignSts]");
let holdColName = $("input[name=holdColName]");
let holdDeptId = $("input[name=holdDeptId]");
let holdAssignSubmit = $("input[name=holdAssignSubmit]");
let holdSearchType = $("input[name=holdSearchType]");
let holdSearchWord = $("input[name=holdSearchWord]");
//--------------------------------


let divView = $("#divView");
let planListTbody = $("#planListTbody");
let planPageBody = $("#planPageBody");
let allEstablishBtn = $("#allEstablishBtn");

function compTargetList(){
	let page = $("[name=holdPage]").val();
	let colName = holdColName.val();
	let deptId = holdDeptId.val();
	let assignSts = holdAssignSts.val();
	let assignSubmit = holdAssignSubmit.val();
	let searchType = holdSearchType.val();
	let searchWord = holdSearchWord.val();
	//-^페이지네이션+검색 데이터-----------------------------
	
	$.ajax({
		url : "${cPath}/lecComp/LectureCompleteFormData.do",
		method : "get",
		data : {page : page, colName:colName, deptId:deptId, assignSts:assignSts
			, assignSubmit:assignSubmit, searchType:searchType, searchWord:searchWord},
		dataType : "json",
		success : function(resp) {
			planListTbody.empty();
			planPageBody.empty();
			
			let paging = resp.pagingVo;
			let planList = paging.dataList;
			
			let trTags = [];
			if(planList && planList.length > 0){
				$.each(planList, function(idx, plan){
					let roomNo = plan.roomNo;
					let assignSubmit = plan.assignSubmit;
					if(roomNo == null || roomNo == ""){
						roomNo = "미정";
					}
					
					let trTag = $("<tr>").attr("data-plan-no", plan.planNo).addClass("tr-targetLine tr-targetHover");
					if(assignSubmit == "미제출"){
						trTag.append($("<td>"));
					}else{
						trTag.append($("<td>").append($("<input>").attr("type", "checkbox")
								.attr("id", plan.planNo).addClass("checkEach")));
					}
					trTag.append(
							$("<td>").html(plan.planNo)
							, $("<td>").html(plan.colName)	
							, $("<td>").html(plan.deptName)	
							, $("<td>").append($("<a>").attr("href", "#").attr("onclick" ,"autoScrollBlock(event)")
									.addClass("nameClick").text(plan.headproName).attr("data-user-no", plan.proNo))
							, $("<td>").append($("<a>").attr("href", "#").attr("onclick" ,"autoScrollBlock(event)")
									.addClass("nameClick").text(plan.proName).attr("data-user-no", plan.userNo))
							, $("<td>").html(plan.sjtName).addClass("td-leftAlign")
							, $("<td>").html(plan.planSmry).addClass("td-leftAlign")
							, $("<td>").html(plan.planTcnt)	
							, $("<td>").html(plan.gwanName)	
							, $("<td>").html(roomNo)	
							, $("<td>").html(plan.assignDt1)	
							, $("<td>").html(plan.assignDt2)	
							, $("<td>").html(plan.assignSts)
							, $("<td>").html(plan.assignSubmit));
						if(assignSubmit == "미제출"){
							trTag.append($("<td>").append($("<input>").attr("type", "button").attr("disabled", true)
									.attr("value", "불가").addClass("btn btn-secondary btn-sm btn-sfont")));
						}else{
							trTag.append($("<td>").append($("<input>").attr("type", "button")
									.attr("value", "개설").addClass("btn btn-primary btn-sm establishBtn btn-sfont").attr("data-plan-no", plan.planNo)
									.attr("data-bs-toggle", "modal").attr("data-bs-target", "#detailModal")));
						}
					trTags.push(trTag);
				});
			}else{
				let trTag = $("<tr>").html(
								$("<td>").attr("colspan", "16")
										 .html(overNoDataCode)
							);
				trTags.push(trTag);
			} // if end
			planListTbody.append(trTags);
			planPageBody.html(paging.pagingHTMLBS);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR);
			console.log(textStatus);
			console.log(errorThrown);
		}
	});
};
compTargetList();


planListTbody.on("click", ".nameClick", function(e){
	let userNo = $(this).data("userNo");
	//alert(userNo);
	var divTop = e.clientY-40;	//상단 좌표 위치 안맞을시 e.pageY 
	var divLeft = e.clientX;	//좌측 좌표 위치 안맞을시 e.pageX 
	
	var pickNo = userNo;
	var pickName = $(this).text(); 
	
	divView.empty();
	divView.append($("<div>").css({"position":"absolute", "top":"5px", "right":"5px"})
						.append($("<span>").attr("id", "close").css({"cursor":"pointer", "font-size":"1.5em"})
								.text("X"))
				, $("<br>"), $("<a>").attr("href", "#").text(pickNo), $("<br>"), $("<a>").attr("href", "#").text(pickName)
	);
	divView.css({"top":divTop, "left":divLeft , "position":"absolute"}).show(); 
	
	$("#close").click(function(){
		divView.hide();
	}); 
});

//페이지네이션 클릭시
planPageBody.on("click", "a", function(){
	page = $(this).data("page");
	$("[name=holdPage]").val(page);
	
	compTargetList();
});

//전체 개설 버튼 클릭
allEstablishBtn.on("click", function(){
	let planNos = [];
	let checkBoxes = planListTbody.find("input[class=checkEach]:checked");
	$.each(checkBoxes, function(idx, checkBox){
		let planNo = $(this).attr("id");
		let jsonEle = {"planNo":planNo};
		planNos.push(jsonEle);
	});
	
	if(planNos.length > 0){
		if(confirm("개설처리 후 변경할수 없습니다.\n"+planNos.length+"건의 강의를 개설하시겠습니까?")){
			establishLecture(planNos);
		}
	}else{
		alert("개설할 승인 강의을 선택해주세요.");
	};
});

//개별 개설 버튼 클릭시
planListTbody.on("click", ".establishBtn", function(){
	let planNos = [];
	
	let planNo = $(this).data("planNo");
	let jsonEle = {"planNo":planNo};
	planNos.push(jsonEle);
	
	if(confirm("개설처리 후 변경할수 없습니다.\n"+planNo+"번 강의를 개설하시겠습니까?")){
		establishLecture(planNos);
	};
});

function establishLecture(planNos){
	//console.log(planNos);
	let planNoList = { "planNoList" : JSON.stringify(planNos) };
	
	$.ajax({
		url : "${cPath}/lecComp/LectureEstablish.do",
		method : "get",
		data : planNoList,
		dataType : "json",
		success : function(resp) {
			alert("개설강의는 [개설강의현황조회]에서 확인 가능합니다.");
			compTargetList();
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

//분과대학 체인지 이벤트 발생시
collegeSelection.on("change", function(){
	let colName = $(this).val();
	if(colName == ""){
		departmentSelection.empty();
		departmentSelection.append($("<option>").attr("value", "").text("전체"));
	}else{
		departmentListFunc(colName);
	}
	holdColName.val(colName);
	holdDeptId.val("");
});

//디파트먼트 체인지 이벤트 발생시
departmentSelection.on("change", function(){
	let deptId = $(this).val();
	let colName = $(this).data("colName");	//작동안됨(undefined)
	holdDeptId.val(deptId);
});
//배정상태 체인지 이벤트 발생시
statusSelection.on("change", function(){
	let assignSts = $(this).val();
	holdAssignSts.val(assignSts);
});
//배정제출상태  체인지 이벤트 발생시
submitSelection.on("change", function(){
	let assignSubmit = $(this).val();
	holdAssignSubmit.val(assignSubmit);
});
//검색조건  체인지 이벤트 발생시
searchTypeSelection.on("change", function(){
	let searchType = $(this).val();
	holdSearchType.val(searchType);
});
//검색버튼 클릭시
searchBtn.on("click", function(){
	let searchWord = $("#searchWordInput").val();
	$("[name=holdPage]").val(1);
	holdSearchWord.val(searchWord);
	
	compTargetList();
});
//검색초기화
defaultBtn.on("click", function(){
	$("select").prop("selectedIndex",0);
	$("[name=holdPage]").val(1);
	searchWordInput.val("");
	holdColName.val("");
	holdDeptId.val("");
	holdAssignSts.val("");
	holdAssignSubmit.val("");
	holdSearchType.val("");
	holdSearchWord.val("");
	compTargetList();
});
//개설가능강의만 보기
possibleBtn.on("click", function(){
	$("select").prop("selectedIndex",0);
	$("[name=holdPage]").val(1);
	searchWordInput.val("");
	holdColName.val("");
	holdDeptId.val("");
	holdAssignSts.val("배정");
	holdAssignSubmit.val("Y");
	holdSearchType.val("");
	holdSearchWord.val("");
	compTargetList();
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
</script>








