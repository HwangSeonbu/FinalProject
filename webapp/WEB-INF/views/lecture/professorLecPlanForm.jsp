<%--
교수의 강의계획성 입력 양식 
(제목을 클릭하면 점수 비중을 설정하는 jsp로 이동한다)
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 4. 28.      김재웅      최초작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<security:authentication property="principal.realUser" var="authMember"/>
<h3 class="h3-title">강의계획 작성</h3><hr class="hr-title">
<style>
	#planTable{
		width: 65%;
		table-layout: fixed;
	}
	#planTable td{
		overflow: hidden;
	}
 	#checkboxTh{width: 50px;}
	#planNoTh{width: 100px;}
	#sjtNameTh{width: 200px;}
	.th-numberTh{width: 70px;}
/* 	#planSmryTh{width: 350px;} */
	#planStsTh{width: 100px;}
	.planTd{color:blue;}
	.planTd:hover{cursor: pointer; text-decoration: underline;font-weight: bold; }
</style>

<input type="button" class="btn btn-success" id="addPlanBtn" value="계획추가"/>
<input type="button" class="btn btn-danger" id="deletePlanBtn" value="계획삭제"/>

<br><br>

<table class="table table-bordered" id="planTable">
 <thead >
    <tr class="text-center" style="vertical-align: middle;">
    	<th id="checkboxTh"><input id="checkAll" type="checkbox" /></th>	
    	<th id="planNoTh">계획번호</th>	
    	<th id="sjtNameTh">과목명</th>	
    	<th class="th-numberTh">대상<br>학년</th>	
    	<th class="th-numberTh">강의<br>시수</th>	
    	<th class="th-numberTh">정원</th>	
    	<th id="planSmryTh">강의계획명</th>	
    	<th id="planStsTh">처리상태</th>	
    </tr>
 </thead>
 <tbody class="text-center" id="planListBody">
 </tbody>
</table>

<hr>
<!-- <input type="button" class="btn btn-primary" value="제출" id="submitBtn"/> -->


<script>
var noPlanDataCode = "<div class='div-imageWrap' id='imageWrap'><div class='div-imageInnerWrap'>";
noPlanDataCode += "<img alt='' src='${cPath }/resources/img/noData.jpg' style='width:100%;'/></div>";
noPlanDataCode += "<div class='div-textInnerImage'> 작성중인 강의계획이<br>없습니다.</div></div>";

let planListBody = $("#planListBody");
let detailFormURL = "${cPath}/lecPlan/LecturePlanDetailForm.do?planNo=";

function planDataList() {
	$.ajax({
		url : "${cPath}/lecPlan/LecturePlanFormData.do",
		method : "get",
		dataType : "json",
		success : function(resp) {
			planListBody.empty();
			let planList = resp.planList;
			let trTags = [];
			if(planList && planList.length > 0){
				$.each(planList, function(idx, plan){
					let planSts = plan.planSts;
					var stsColor = planSts == '대기'?'blue':'red';
					let trTag = $("<tr>").attr("id", plan.planNo)
										 .append($("<td>").append(
													$("<input>").attr("type", "checkbox").addClass("checkEach") 
										 		)
												 , $("<td>").text(plan.planNo)
												 , $("<td>").text(plan.sjtName)
												 , $("<td>").text(plan.planYear)
												 , $("<td>").text(plan.planTcnt)
												 , $("<td>").text(plan.planLimit)
												 , $("<td>").addClass("planTd").text(plan.planSmry)
												 , $("<td>").text(planSts).css({"color":stsColor, "font-weight":"bold"})
										 );
					trTags.push(trTag);
				});
			}else{
				let trTag = $("<tr>").append($("<td>").attr("colspan", "8").html(noPlanDataCode));
				trTags.push(trTag);
			}
			planListBody.append(trTags);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR);
			console.log(textStatus);
			console.log(errorThrown);
		}
	});
};
planDataList();

// $(document).on("click", "#checkAll", function(){
// 	if($("#checkAll").is(":checked")){
// 		$(".checkEach").prop("checked", true);
// 	}else{
// 		$(".checkEach").prop("checked", false);
// 	}
// });

$(document).on("click", "#checkAll", function(){
	if($("#checkAll").is(":checked")){
		$(".checkEach").prop("checked", true);
	}else{
		$(".checkEach").prop("checked", false);
	}
});

$(document).on("click", ".checkEach", function(){
	if($("input[class=checkEach]:checked").length == $(".checkEach").length){
		$("#checkAll").prop("checked", true);
	}else{
		$("#checkAll").prop("checked", false);
	}
});


$(document).on("click", ".planTd", function(){
	let targetURL = detailFormURL;
	let pickPlanNo = $(this).parent("tr").attr("id");
	targetURL += pickPlanNo;

	if(targetURL)
		location.href=targetURL;
});

function addPlan(){
	$.ajax({
		url : "${cPath}/lecPlan/addPlan.do",
		method : "get",
		dataType : "json",
		success : function(resp) {
			if(resp.result == "성공")
				planDataList();
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR);
			console.log(textStatus);
			console.log(errorThrown);
		}
	});
};

$("#addPlanBtn").on("click", function(){
	addPlan();
});

$("#deletePlanBtn").on("click", function(){
	let planNos = [];
	let checkBoxes = planListBody.find("input[class=checkEach]:checked");
	$.each(checkBoxes, function(idx, checkBox){
		let jsonEle = "";
		let thisid = $(this).parents("tr").attr("id");
		jsonEle = { "planNo" : thisid };
		planNos.push(jsonEle);
	});
	
	if(planNos.length > 0){
		let planNoList = { "planNoList" : JSON.stringify(planNos) };
		deletePlan(planNoList);
	}else{
		alert("삭제할 계획을 선택해주세요.");
	}
});

function deletePlan(planNoList){
	$.ajax({
		url : "${cPath}/lecPlan/deletePlan.do",
		method : "get",
		data : planNoList,
		dataType : "json",
		success : function(resp) {
			let result = resp.result;
			if(result != 0){
				alert(result+"건의 계획을 삭제했습니다.");
				planDataList();
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR);
			console.log(textStatus);
			console.log(errorThrown);
		}
	});
};

</script>








