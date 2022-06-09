<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h3 class="h3-title">강의평가 상세 입력</h3><hr class="hr-title">

<style>
	#formTabel{
		width: 65%;
		min-width: 850px;
		float: left;
	}
	#btnArea{width: 65%;min-width: 850px; margin-top: 10px;}
	#infoArea{
		width: 65%;height:150px;
		min-width: 850px;
	}
	#extraArea{
		width: 20%;height:150px;
		float: left;
		margin-left: 15px;
	}
	#blankArea{
		width: 65%;height:150px;
		min-width: 850px;
		clear: both;
	}
	.tr-headLine{
		font-weight: bold;
	}
	.unComplete{
		background-color: yellow;
	}
	#infoTable tr th{background-color: orange; text-align: center; width: 110px;}
	#infoTable tr td{padding-left: 20px;}
</style>
<%-- <form id="registerForm" action="${pageContext.request.contextPath }/reqCounsel/register" method="post">      --%>
	<div id="infoArea">	
		<c:set value="${lectureVo }" var="lec"/>
		<table class="table table-bordered table-sm" id="infoTable">
			<tr><th>과목명</th><td colspan="3">${lec.lecName }</td><th>교수명</th><td colspan="3">${lec.proName }</td></tr>
			<tr><th>강의명</th><td colspan="7" style="color:blue;"><strong>${lec.planSmry }</strong></td></tr>
			<tr>
				<th>대상학년</th><td>${lec.planYear }</td><th>시수</th><td>${lec.planTcnt }</td>
				<th>정원</th><td>${lec.planLimit }</td><th>수강인원</th><td>${lec.lecPers }</td>
			</tr>
			<tr><th>평가기준</th>
				<td colspan="7">중간고사 비율 : <strong>${lec.mrate }</strong>%&nbsp;|&nbsp;기말고사 비율 : <strong>${lec.frate }</strong>%&nbsp;
					|&nbsp;과제 비율 : <strong>${lec.hwrate }</strong>%&nbsp;|&nbsp;출석 비율 : <strong>${lec.attrate }</strong>%
				</td>
			</tr>
		</table>
	</div>
<div class="redLine" id="btnArea">
	<input id="enrollBtn" type="button" class="btn btn-primary" value="평가등록"/>
	<input type="button" class="btn btn-secondary" value="목록으로" onclick="javascript:history.go(-1);"/>
</div>
<form id="registerForm" action="#" method="post">     
<security:csrfInput/>
	<br>
	<table id="formTabel" class="table table-bordered">
		<thead>
			<tr class="tr-headLine">
				<th width="8%;" rowspan="2">평가<br>번호</th>
				<th width="55%;" rowspan="2">평가내용</th>
				<th colspan="5">평가답변</th>
			</tr>
			<tr class="tr-headLine">
				<th>매우<br>그렇다</th>
				<th>그렇다</th>
				<th>보통</th>
				<th>아니다</th>
				<th>매우<br>아니다</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td class="text-center" style="background-color: #e3f2fd;" colspan="7">
					강의 준비
				</td>
			</tr>
			<tr class="tr-question">
				<td class="text-center">1</td>
				<td>강의계획서가 충실히 작성되어 강의선택에 도움이 되었다.</td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q1" value="5"/></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q1" value="4"/></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q1" value="3"/></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q1" value="2"/></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q1" value="1"/></td>
			</tr>
			<tr class="tr-question">
				<td class="text-center">2</td>
				<td>주차별 강의계획이 충분히 안내되었다.</td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q2" value="5"/></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q2" value="4"/></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q2" value="3"/></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q2" value="2"/></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q2" value="1"/></td>
			</tr>
			<tr class="tr-question">
				<td class="text-center">3</td>
				<td>강의자료가 적절히 준비되어 학습에 도움이 되었다.</td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q3" value="5"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q3" value="4"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q3" value="3"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q3" value="2"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q3" value="1"></td>
			</tr>
			<tr>
				<td class="text-center" style="background-color: #e3f2fd;" colspan="7">
					강의 진행
				</td>
			</tr>
			<tr class="tr-question">
				<td class="text-center">4</td>
				<td>강의가 전반적으로 출결관리가 잘 되었다.</td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q4" value="5"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q4" value="4"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q4" value="3"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q4" value="2"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q4" value="1"></td>
			</tr>
			<tr class="tr-question">
				<td class="text-center">5</td>
				<td>시험, 과제 등 평가기준이 명확하게 제시되었다.</td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q5" value="5"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q5" value="4"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q5" value="3"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q5" value="2"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q5" value="1"></td>
			</tr>
			<tr class="tr-question">
				<td class="text-center">6</td>
				<td>강의실 및 기자재 등이 적절히 활용되어 강의가 진행되었다.</td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q6" value="5"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q6" value="4"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q6" value="3"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q6" value="2"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q6" value="1"></td>
			</tr>
			<tr class="tr-question">
				<td class="text-center">7</td>
				<td>강의내용이 효과적으로 전달되어 이해하기 쉬었다.</td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q7" value="5"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q7" value="4"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q7" value="3"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q7" value="2"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q7" value="1"></td>
			</tr>
			<tr class="tr-question">
				<td class="text-center">8</td>
				<td>본 강의를 통해 해당분야에 대한 충분한 지적 자극을 받았다.</td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q8" value="5"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q8" value="4"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q8" value="3"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q8" value="2"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q8" value="1"></td>
			</tr>
			<tr class="tr-question">
				<td class="text-center">9</td>
				<td>본 강의는 전반적으로 유익하였다.</td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q9" value="5"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q9" value="4"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q9" value="3"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q9" value="2"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q9" value="1"></td>
			</tr>
			<tr class="tr-question">
				<td class="text-center">10</td>
				<td>본 강의를 다른학생에게 추천하고 싶다.</td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q10" value="5"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q10" value="4"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q10" value="3"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q10" value="2"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q10" value="1"></td>
			</tr>
			<tr>
				<td class="text-center" style="background-color: #e3f2fd;" colspan="7">
					시험 평가
				</td>
			</tr>
			<tr class="tr-question">
				<td class="text-center">11</td>
				<td>시험 일정 및 기준 등이 명확히 안내되었고 안내되로 진행되었다.</td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q11" value="5"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q11" value="4"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q11" value="3"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q11" value="2"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q11" value="1"></td>
			</tr>
			<tr class="tr-question">
				<td class="text-center">12</td>
				<td>시험 진행 및 평가가 공정하게 이루어졌다.</td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q12" value="5"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q12" value="4"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q12" value="3"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q12" value="2"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q12" value="1"></td>
			</tr>
			<tr class="tr-question">
				<td class="text-center">13</td>
				<td>강의내용을 기반으로 한 시험문제가 출제되었다.</td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q13" value="5"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q13" value="4"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q13" value="3"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q13" value="2"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q13" value="1"></td>
			</tr>
			<tr>
				<td class="text-center" style="background-color: #e3f2fd;" colspan="7">
					기타의견이 있으면 입력해주세요.(필수 아님)
				</td>
			</tr>
			<tr>
				<td class="text-center" style="vertical-align: middle;">의견<br>입력</td>
				<td colspan="6">
					<textarea class="form-control" style="height:100px;width:100%;"></textarea>
				</td>
			</tr>
		</tbody>
	</table>
</form>
<div id="extraArea">	
</div>

<div id="blankArea"></div>

<script>
let enrollBtn = $("#enrollBtn");
let formTabel = $("#formTabel");
let lecId = ${lec.lecId };

enrollBtn.on("click", function(event){
	$(".tr-question").removeClass("unComplete");
	let question = $(".tr-question");
	var count = 0;
	let qList = "";
	$.each(question, function(idx, item){
		var qNo = $(this).find("td:first-child").text();
		var qValue = $("input[name=q"+qNo+"]:checked").val();
		if(qValue == null || qValue == ""){
			$(this).addClass("unComplete");
			count++;
		}
		qList += qValue+",";
	});
	qList = qList.slice(0, -1);
	if(count > 0){
		alert(count+"개의 답변이 누락되었습니다.")
	}else{
		var opnion = $("textarea").val();
		console.log(qList);
		console.log(opnion);
		location.href
			="${cPath}/astEnroll/lecAstSunmit.do?qList="+qList+"&opnion="+opnion+"&lecId="+lecId;
	};
});
</script>