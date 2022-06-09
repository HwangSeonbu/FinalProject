<%--
  교수평가 결과조회
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 4. 29.      김재웅      최초작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<style>
.div-textInnerImage{
	font-size: 30px;
}
#dataTable{
	width: 65%;
	min-width: 850px;
	float: left;
}
#extraBtnArea{
/* 	border: 1px solid blue; */
	width: 30%;
	float: left;
	margin-left: 15px;
	margin-bottom: 15px;
}
#extraArea, #extraArea2{
/* 	border: 1px solid blue; */
	width: 30%;
	float: left;
	margin-left: 15px;
}
#blankArea{
/* 	border: 1px solid red; */
	width: 65%;height:150px;
	overflow: hidden; 
	min-width: 850px;
	clear: both;
}
#buttonArea{
/* 	border: 1px solid red; */
	width: 65%;height:50px;
	min-width: 850px;
}
.tr-subHeadLine{text-align: center;vertical-align: middle;background-color:skyblue;}
.td-score{text-align: center;vertical-align: middle;
	color:red; font-weight: bold; font-size: 18px;}
.navActive{background-color: orange; font-weight: bold;}
.nav-link:hover{cursor: pointer; font-weight: bold;}
#orderComment{color:red; font-size: 18px; font-weight: bold;}
#detailDataListTbody2{text-align: left; padding-left: 15px;}
</style>
<h3 class="h3-title">교수평가 결과 조회</h3><hr class="hr-title">

<div id="buttonArea">
	<button type="button" class="btn btn-primary orderBtn" data-except-rate="0"><i class="bi bi-sort-alpha-down"></i> 이름순</button>
	<button type="button" class="btn btn-success orderBtn" data-except-rate="-1" value="DESC"><i class="bi bi-sort-down"></i> 점수 높은순</button>
	<button type="button" class="btn btn-warning orderBtn" data-except-rate="101" value="ASC"
		style="margin-right: 25px;"><i class="bi bi-sort-down-alt"></i> 점수 낮은순</button>
	<span id="orderComment">*이름순으로 정렬중입니다.</span>
</div>
<table class="table table-bordered" id="dataTable">
	 <thead>
	    <tr id="trTitle" class="tr-headLine">
			<th >교수<br>번호</th>	
	    	<th >성별</th>	
	    	<th >직책</th>	
	    	<th >교수명</th>	
	    	<th >진행<br>강의수</th>	
	    	<th >수강<br>인원</th>	
	    	<th >평가<br>인원</th>	
	    	<th >평가<br>진행률</th>	
	    	<th >평가<br>점수</th>	
	    	<th >평가<br>상세</th>
	    </tr>
	 </thead>
	  <tbody class="text-center" id="dataListTbody">
	 
	 </tbody>
</table>
<div id="extraBtnArea">
	<ul class="nav nav-tabs">
		<li class="nav-item navActive">
		  <a id="question" class="nav-link" style="color:black;">문항점수</a>
		</li>
		<li class="nav-item">
		  <a id="opinion" class="nav-link" style="color:black;">기타의견</a>
		</li>
	</ul>
</div>

<div id="extraArea">
	<table class="table" id="detailDataTable">
		 <thead>
		    <tr id="trTitle" class="tr-subHeadLine">
				<th style="width: 50px;">문항<br>번호</th>	
		    	<th >문항</th>	
		    	<th style="width: 80px;">평균<br>점수</th>	
		    </tr>
		 </thead>
		  <tbody class="text-center" id="detailDataListTbody">
		 	<tr><td>1</td><td>강의에 필요한 정보를 시기에 맞게 적절히 제공하였다.</td><td class="td-score" id="score_1"></td></tr>
		 	<tr><td>2</td><td>정규 강의일에 휴강 등 차질없이 강의를 진행하였다.</td><td class="td-score" id="score_2"></td></tr>
		 	<tr><td>3</td><td>시험, 과제, 조별 학습 등에 합리적인 평가기준을 적용하였다.</td><td class="td-score" id="score_3"></td></tr>
		 	<tr><td>4</td><td>양질의 학습분위기 유지를 위해 노력하였다.</td><td class="td-score" id="score_4"></td></tr>
		 	<tr><td>5</td><td>본인 분야에 대한 지식이 충분하고 학생에게 효과적으로 전달하였다.</td><td class="td-score" id="score_5"></td></tr>
		 	<tr><td>6</td><td>학생의 집중을 이끌어 내는 강의 역량이 충분하였다.</td><td class="td-score" id="score_6"></td></tr>
		 	<tr><td>7</td><td>교내 공인임을 인지하고 학생에게 예의있게 행동하였다.</td><td class="td-score" id="score_7"></td></tr>
		 	<tr><td>8</td><td>강의와 상관없는 불필요한 언행을 삼가고 강의 내용에 집중하였다.</td><td class="td-score" id="score_8"></td></tr>
		 	<tr><td>9</td><td>인종, 종교 등의 이유로 학생들을 차별하지 않았다.</td><td class="td-score" id="score_9"></td></tr>
		 	<tr><td>10</td><td>본 교수는 전반적으로 훌륭하다.</td><td class="td-score" id="score_10"></td></tr>
		 	<tr><td>11</td><td>질문, 면담, 상담 등의 학생 요청에 적극적으로 응하였다.</td><td class="td-score" id="score_11"></td></tr>
		 	<tr><td>12</td><td>본인 분야의 지식 함양을 위해 지속적으로 노력하는 모습을 보였다.</td><td class="td-score" id="score_12"></td></tr>
		 	<tr><td>13</td><td>교내의 주요 학사 일정 및 학사관련 업무를 이해하고 있다.</td><td class="td-score" id="score_13"></td></tr>
		 </tbody>
	</table>
</div>
<div id="extraArea2">
	<table class="table" id="detailDataTable2">
		<thead>
		    <tr id="trTitle" class="tr-subHeadLine">
		    	<th >기타의견</th>	
		    </tr>
		 </thead>
		 <tbody id="detailDataListTbody2">
		 	<tr><td>
		 		<div class='div-imageWrap' id='imageWrap'><div class='div-imageInnerWrap'>
				<img alt='' src='${cPath }/resources/img/noData.jpg' style='width:100%;'/></div>
				<div class='div-textInnerImage'> 조회할 평가항목을<br>선택하세요.</div></div>
		 	</td></tr>
		 </tbody>
	</table>
</div>

<div id="blankArea">
	<form id="searchForm">
	<security:csrfInput/>
		<input type="hidden" name="holdExceptRate"/>
		<input type="hidden" name="holdOrderBy"/>
	</form>
</div>

<script>
var noOpnionCode = "<div class='div-imageWrap' id='imageWrap'><div class='div-imageInnerWrap'>";
noOpnionCode += "<img alt='' src='${cPath }/resources/img/noData.jpg' style='width:100%;'/></div>";
noOpnionCode += "<div class='div-textInnerImage'> 등록된 기타의견이<br>없습니다.</div></div>";
var yetAstCode = "<div class='div-imageWrap' id='imageWrap'><div class='div-imageInnerWrap'>";
yetAstCode += "<img alt='' src='${cPath }/resources/img/noData.jpg' style='width:100%;'/></div>";
yetAstCode += "<div class='div-textInnerImage'> 평가가 진행되지<br>않았습니다.</div></div>";
var needClickCode = "<div class='div-imageWrap' id='imageWrap'><div class='div-imageInnerWrap'>";
needClickCode += "<img alt='' src='${cPath }/resources/img/noData.jpg' style='width:100%;'/></div>";
needClickCode += "<div class='div-textInnerImage'> 조회할 평가항목을<br>선택하세요.</div></div>";

let dataListTbody = $("#dataListTbody");
let tdScore = $(".td-score");

let question = $("#question");
let opinion = $("#opinion");

let extraArea = $("#extraArea");
let extraArea2 = $("#extraArea2");
let detailDataListTbody2 = $("#detailDataListTbody2");

let orderBtn = $(".orderBtn");
let orderComment = $("#orderComment");
let holdExceptRate = $("input[name=holdExceptRate]");
let holdOrderBy = $("input[name=holdOrderBy]");

extraArea2.hide();

function astList(){
	let exceptRate = holdExceptRate.val();
	let orderBy = holdOrderBy.val();
	$.ajax({
		url : "${cPath}/astResult/ProfessorAstResultData.do",
		method : "get",
		data : {exceptRate:exceptRate, orderBy:orderBy},
		dataType : "json",
		success : function(resp) {
			let compList = resp.compList;
			dataListTbody.empty();
			let trTags = [];
			$.each(compList, function(idx, comp){
				let astComprate = comp.astComprate;
				var btn = astComprate=="-"?"btn-secondary":"btn-primary";
				var disabled = astComprate=="-"?true:false;
				let trTag = $("<tr>").addClass("tr-targetLine tr-targetHover").append(
								$("<td>").text(comp.userNo)
								, $("<td>").text(comp.userGender)
								, $("<td>").text(comp.proJob)
								, $("<td>").text(comp.userName)
								, $("<td>").text(comp.lecCnt)
								, $("<td>").text(comp.lecRealpers)
								, $("<td>").text(comp.astPers)
								, $("<td>").text(comp.astComprate)
								, $("<td>").text(comp.astScore)
								, $("<td>").append(
										$("<input>").attr("type", "button").attr("disabled", disabled)
										.attr("value", "보기").addClass("btn btn-sm astBtn").addClass(btn)
										.attr("data-pro-no", comp.userNo))
				);
				trTags.push(trTag);
			});
			dataListTbody.append(trTags);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR);
			console.log(textStatus);
			console.log(errorThrown);
		}
	});
	if(orderBy=="DESC"){
		orderComment.text("*점수 높은순으로 정렬중입니다.\t");
	}else if(orderBy=="ASC"){
		orderComment.text("*점수 낮은순으로 정렬중입니다.\t");
	}else{
		orderComment.text("*이름순으로 정렬중입니다.\t");
	}
};
astList();

question.on("click", function(){
	opinion.parent("li").removeClass("navActive");
	$(this).parent("li").addClass("navActive");
	extraArea2.hide();
	extraArea.show();
});

opinion.on("click", function(){
	question.parent("li").removeClass("navActive");
	$(this).parent("li").addClass("navActive");
	extraArea.hide();
	extraArea2.show();
});


dataListTbody.on("click", ".astBtn", function(){
	let proNo = $(this).data("proNo");
	$.ajax({
		url : "${cPath}/astResult/ProfessorDetailResultData.do",
		method : "get",
		data : {proNo:proNo},
		dataType : "json",
		success : function(resp) {
			detailDataListTbody2.empty();
			let astVo = resp.astVo
			if(astVo.astScore != 0){
				let etcList = astVo.astEtcList;
				let astAnswerList = astVo.astAnswerList;
				$.each(astAnswerList, function(idx, answer){
					$("#score_"+(idx+1)).html(answer);
				});
				let etcTrTags = [];
				if(etcList && etcList.length > 0){
					$.each(etcList, function(idx, etc){
						let etcTrTag = $("<tr>").append($("<td>").html(etc));
						etcTrTags.push(etcTrTag);
					});
				}else{
					let etcTrTag = $("<tr>").append($("<td>").html(noOpnionCode));
					etcTrTags.push(etcTrTag);
				}
				detailDataListTbody2.append(etcTrTags);
			}else{
				tdScore.text("0");
				let etcTrTag = $("<tr>").append($("<td>").html(yetAstCode));
				detailDataListTbody2.append(etcTrTag);
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR);
			console.log(textStatus);
			console.log(errorThrown);
		}
	});
});
$(document).on("click", ".tr-targetLine", function(){
	$(this).find("input[type=button]").trigger("click");
});

orderBtn.on("click", function(){
	tdScore.text("-");
	detailDataListTbody2.empty();
	detailDataListTbody2.html(needClickCode);
	let exceptRate = $(this).data("exceptRate");
	let orderBy = $(this).val();
	
	holdExceptRate.val(exceptRate);
	holdOrderBy.val(orderBy);
	
	astList();
});
</script>