<%--
	관리자의 개설강의현황조회
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 4. 28.      김재웅      최초작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<h3 class="h3-title">개설강의 현황 조회</h3><hr class="hr-title">
<style>
	.text-center{text-align: center;}
	a {text-decoration-line: none;}
	a:hover{font-weight: bold;}
	#semsSelection{
		width: 200px;
	}
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
	.table{
		border-top:2px solid black;
		width: 95%;
	}
	.pagination{
		justify-content: center;
	}
	#semsterArea{
		width: 95%;height:50px;
		margin-bottom: 10px;
	}
	.keyword{background-color: yellow;}
</style>

<div id="semsterArea">
	<select class="form-select form-select-lg mb-3" id="semsSelection">
	<c:if test="${not empty semsList }">
		<c:forEach items="${semsList }" var="sems">
			<option value="${sems.semsNo }">${sems.semester }</option>
		</c:forEach>
	</c:if>
	</select>&nbsp;&nbsp;&nbsp;
</div>
<div class="redLine" id="searchArea">
	<label style="color:blue;">개강상태</label>
	<select class="select-search-short" id="statusSelection">
		<option value="" selected>전체</option>
		<option value="개강">개강</option>
		<option value="종강">종강</option>
		<option value="폐강">폐강</option>
	</select>&nbsp;&nbsp;&nbsp;
	<label style="color:blue;">대상학년</label>
	<select class="select-search-short" id="yearSelection">
		<option value="" selected>전체</option>
		<option value="1">1학년</option>
		<option value="2">2학년</option>
		<option value="3">3학년</option>
		<option value="4">4학년</option>
	</select>&nbsp;&nbsp;&nbsp;
	<label style="color:blue;">강의관</label>
	<select class="select-search-long" id="gwanSelection">
		<option value="" selected>전체</option>
		<c:if test="${not empty gwanList }">
			<c:forEach items="${gwanList }" var="gwan">
				<option value="${gwan.gwanName }">${gwan.gwanName }</option>
			</c:forEach>
		</c:if>
	</select><br>
	<br><label style="color:blue;">단어검색</label>
	<select class="select-search-long" id="searchTypeSelection">
		<option value="" selected>전체</option>
		<option value="userName">교수명</option>
		<option value="sjtName">과목명</option>
		<option value="planSmry">강의명</option>
	</select>&nbsp;&nbsp;<input type="text" id="searchWordInput" value="" style="width:350px; text-align:left; padding-left:10px;" />
	&nbsp;<button type="button" id="searchBtn" class="btn btn-primary">검색</button>&nbsp;
	<button type="button" id="defaultBtn" class="btn btn-secondary">검색초기화</button><br><br>
</div>
<!-- <div class="redLine" id="btnArea"></div> -->
<table class="table table-bordered" id="dataTable">
	 <thead>
	    <tr class="tr-headLine">
	    	<th >강의번호</th>	
	    	<th >상태</th>	
	    	<th >강의명</th>	
	    	<th >과목명</th>	
	    	<th >강의<br>교수</th>	
	    	<th >직책</th>	
	    	<th >대상<br>학년</th>	
	    	<th >정원</th>	
	    	<th >수강<br>인원</th>	
	    	<th >강의관</th>	
	    	<th >강의실</th>	
	    	<th >시수</th>	
	    	<th >강의시간</th>	
	    	<th >평가<br>기준</th>	
	    </tr>
	 </thead>
	  <tbody class="text-center" id="dataListTbody">
	 
	 </tbody>
</table>
<div class="redLine" id="pageArea"></div>
<div class="redLine" id="extraArea">
	<form id="searchForm">
	<security:csrfInput/>
		<input type="hidden" name="holdPage"/>
		<input type="hidden" name="holdPlanSts"/>
		<input type="hidden" name="holdPlanYear"/>
		<input type="hidden" name="holdGwanName"/>
		<input type="hidden" name="holdSearchType"/>
		<input type="hidden" name="holdSearchWord"/>
	</form>
</div>

<script>
var noDataCode = "<div class='div-imageWrap' id='imageWrap'><div class='div-imageInnerWrap'>";
noDataCode += "<img alt='' src='${cPath }/resources/img/noData.jpg' style='width:100%;'/></div>";
noDataCode += "<div class='div-textInnerImage'> 조건에 맞는 개설강의가<br>없습니다.</div></div>";
//검색 영역 변수
let statusSelection = $("#statusSelection");
let yearSelection = $("#yearSelection");
let gwanSelection = $("#gwanSelection");
let searchTypeSelection = $("#searchTypeSelection");
let searchWordInput = $("#searchWordInput");

//--hiddenInput
let holdPlanSts = $("input[name=holdPlanSts]");
let holdPlanYear = $("input[name=holdPlanYear]");
let holdGwanName = $("input[name=holdGwanName]");
let holdSearchType = $("input[name=holdSearchType]");
let holdSearchWord = $("input[name=holdSearchWord]");
//버튼 변수--------------------------
let searchBtn = $("#searchBtn");
let defaultBtn = $("#defaultBtn");
//--------------------------------



const thisSems = ${thisSems};
let pickSemsNo = "";

let semsSelection = $("#semsSelection");

let dataListTbody = $("#dataListTbody");
let pageArea= $("#pageArea");


function completeList(){
	var compChk = false;
	let page = $("[name=holdPage]").val();
	let planSts = holdPlanSts.val();
	let planYear = holdPlanYear.val();
	let gwanName = holdGwanName.val();
	let searchType = holdSearchType.val();
	let searchWord = holdSearchWord.val();
	console.log("대상학년 >>>> "+planYear);
	$.ajax({
		url : "${cPath}/lecComp/LectureCompleteListData.do",
		method : "get",
		data:{page:page, targetSems:pickSemsNo, planSts:planSts, planYear:planYear
			, gwanName:gwanName, searchType:searchType, searchWord:searchWord},
		dataType : "json",
		success : function(resp) {
			dataListTbody.empty();
			pageArea.empty();
			let paging = resp.pagingVo;
			let completeList = paging.dataList;
			let trTags = [];
			if(completeList && completeList.length > 0){
				compChk = true;
				$.each(completeList, function(idx, comp){
					var tcnt = comp.planTcnt;
					var dt = tcnt == 1?(comp.assignDt).split(',', 1):comp.assignDt;
					let trTag = $("<tr>").attr("id", comp.lecId).addClass("tr-targetLine tr-targetHover")
						.attr("data-plan-no", comp.planNo).append(
							$("<td>").html(comp.lecId)
// 							.attr("title", comp.lecId).attr("data-toggle", "popover").attr("data-bs-trigger", "hover focus")
							, $("<td>").html(comp.planSts)	
							, $("<td>").addClass("text-left").append($("<a>").attr("href", "#").text(comp.planSmry).addClass("include planSmry"))	
							, $("<td>").html(comp.sjtName).addClass("include sjtName text-left")	
							, $("<td>").append($("<a>").attr("href", "#").text(comp.proName).addClass("include userName"))
							, $("<td>").html(comp.proJob)	
							, $("<td>").html(comp.planYear)	
							, $("<td>").html(comp.planLimit)	
							, $("<td>").html(comp.lecPers)	
							, $("<td>").html(comp.gwanName)	
							, $("<td>").html(comp.roomNo)	
							, $("<td>").html(tcnt)	
							, $("<td>").html(dt)	
							, $("<td>").html(comp.planEval)	
					);
					trTags.push(trTag);
				});
			}else{
				let trTag = $("<tr>").html(
								$("<td>").attr("colspan", "14").attr("rowspan", "5").addClass("text-center")
										 .html(noDataCode)
							);
				trTags.push(trTag);
			} // if end
			dataListTbody.append(trTags);
			pageArea.html(paging.pagingHTMLBS);
			if(compChk){
				if(!(searchType == null|| searchType == "") && !(searchWord == null|| searchWord == "")){
					$("."+searchType+":contains('"+searchWord+"')").each(function(){
						var regex = new RegExp(searchWord, "gi");
						$(this).html($(this).text().replace(regex, "<span class='keyword'>"+searchWord+"</span>"));
					});
				}else if((searchType == null|| searchType == "") && !(searchWord == null|| searchWord == "")){
					$(".include:contains('"+searchWord+"')").each(function(){
						var regex = new RegExp(searchWord, "gi");
						$(this).html($(this).text().replace(regex, "<span class='keyword'>"+searchWord+"</span>"));
					});
				}else{};
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR);
			console.log(textStatus);
			console.log(errorThrown);
		}
	});
};


semsSelection.on("change", function(){
	pickSemsNo = $(this).val();
	$("select:not(#semsSelection)").prop("selectedIndex",0);
	$("[name=holdPage]").val(1);
	searchWordInput.val("");
	holdPlanSts.val("");
	holdPlanYear.val("");
	holdGwanName.val("");
	holdSearchType.val("");
	holdSearchWord.val("");
	completeList();
});

defaultBtn.on("click", function(){
	$("select:not(#semsSelection)").prop("selectedIndex",0);
	$("[name=holdPage]").val(1);
	searchWordInput.val("");
	holdPlanSts.val("");
	holdPlanYear.val("");
	holdGwanName.val("");
	holdSearchType.val("");
	holdSearchWord.val("");
	completeList();
});

pageArea.on("click", "a", function(){
	let page = $(this).data("page");
	$("[name=holdPage]").val(page);
	
	completeList(pickSemsNo);
});

//개강상태 체인지 이벤트 발생시
statusSelection.on("change", function(){
	let planSts = $(this).val();
	holdPlanSts.val(planSts);
});
//학년 체인지 이벤트 발생시
yearSelection.on("change", function(){
	let planYear = $(this).val();
	holdPlanYear.val(planYear);
});
//강의관  체인지 이벤트 발생시
gwanSelection.on("change", function(){
	let gwanName = $(this).val();
	holdGwanName.val(gwanName);
});
//검색조건  체인지 이벤트 발생시
searchTypeSelection.on("change", function(){
	let searchType = $(this).val();
	holdSearchType.val(searchType);
});
searchBtn.on("click", function(){
	let searchWord = $("#searchWordInput").val();
	$("[name=holdPage]").val(1);
	holdSearchWord.val(searchWord);
	
	completeList();
});

//페이지 온로드
$(function(){
	if(pickSemsNo == "" || pickSemsNo == null){
		semsSelection.val(thisSems).trigger("change");
		pickSemsNo = thisSems;
		completeList();
	}else{
		completeList();
	}
});

</script>





