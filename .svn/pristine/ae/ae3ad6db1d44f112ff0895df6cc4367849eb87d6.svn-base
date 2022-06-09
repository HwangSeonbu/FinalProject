<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h3 class="h3-title">교수평가 상세 입력</h3><hr class="hr-title">
<style>
	#formTabel{
		width: 65%;
		min-width: 850px;
		float: left;
	}
	#btnArea{width: 65%;min-width: 850px;}
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
	<div id="infoArea">	
		<c:set value="${professorVo }" var="pro"/>
		<table class="table table-bordered table-sm" id="infoTable">
			<tr><th>분과대학</th><td colspan="3">${pro.colName }</td><th>학과</th><td colspan="3">${pro.deptName }</td></tr>
			<tr><th>교수명</th><td colspan="3" style="color:blue;"><strong>${pro.userName }</strong></td>
				<th>성별</th><td>${pro.userGender }</td>
				<th>직책</th><td>${pro.proJob }</td>
				</tr>
			<tr><th>연락처</th><td colspan="3">${pro.userPhone }</td><th>이메일</th><td colspan="3">${pro.userMail }</td></tr>
			
			<tr><th>진행강의</th>
				<td colspan="7">
					<c:set value="${professorVo.mylecList }" var="mylecList"/>
					<c:choose>
						<c:when test="${not empty mylecList }">
							<c:forEach items="${mylecList }" var="lec">
								${lec.lecName }&nbsp;&nbsp;
							</c:forEach>
						</c:when>
						<c:otherwise>
							진행강의 없음.&nbsp;&nbsp;
						</c:otherwise>
					</c:choose>
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
					교수의 강의 운영
				</td>
			</tr>
			<tr class="tr-question">
				<td class="text-center">1</td>
				<td>강의에 필요한 정보를 시기에 맞게 적절히 제공하였다.</td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q1" value="5"/></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q1" value="4"/></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q1" value="3"/></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q1" value="2"/></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q1" value="1"/></td>
			</tr>
			<tr class="tr-question">
				<td class="text-center">2</td>
				<td>정규 강의일에 휴강 등 차질없이 강의를 진행하였다.</td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q2" value="5"/></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q2" value="4"/></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q2" value="3"/></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q2" value="2"/></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q2" value="1"/></td>
			</tr>
			<tr class="tr-question">
				<td class="text-center">3</td>
				<td>시험, 과제, 조별 학습 등에 합리적인 평가기준을 적용하였다.</td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q3" value="5"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q3" value="4"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q3" value="3"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q3" value="2"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q3" value="1"></td>
			</tr>
			<tr class="tr-question">
				<td class="text-center">4</td>
				<td>양질의 학습분위기 유지를 위해 노력하였다.</td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q4" value="5"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q4" value="4"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q4" value="3"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q4" value="2"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q4" value="1"></td>
			</tr>
			<tr class="tr-question">
				<td class="text-center">5</td>
				<td>본인 분야에 대한 지식이 충분하고 학생에게 효과적으로 전달하였다.</td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q5" value="5"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q5" value="4"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q5" value="3"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q5" value="2"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q5" value="1"></td>
			</tr>
			<tr class="tr-question">
				<td class="text-center">6</td>
				<td>학생의 집중을 이끌어 내는 강의 역량이 충분하였다.</td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q6" value="5"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q6" value="4"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q6" value="3"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q6" value="2"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q6" value="1"></td>
			</tr>
			<tr>
				<td class="text-center" style="background-color: #e3f2fd;" colspan="7">
					교수의 강의 태도
				</td>
			</tr>
			<tr class="tr-question">
				<td class="text-center">7</td>
				<td>교내 공인임을 인지하고 학생에게 예의있게 행동하였다.</td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q7" value="5"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q7" value="4"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q7" value="3"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q7" value="2"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q7" value="1"></td>
			</tr>
			<tr class="tr-question">
				<td class="text-center">8</td>
				<td>강의와 상관없는 불필요한 언행을 삼가고 강의 내용에 집중하였다.</td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q8" value="5"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q8" value="4"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q8" value="3"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q8" value="2"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q8" value="1"></td>
			</tr>
			<tr class="tr-question">
				<td class="text-center">9</td>
				<td>인종, 종교 등의 이유로 학생들을 차별하지 않았다.</td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q9" value="5"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q9" value="4"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q9" value="3"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q9" value="2"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q9" value="1"></td>
			</tr>
			<tr class="tr-question">
				<td class="text-center">10</td>
				<td>본 교수는 전반적으로 훌륭하다.</td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q10" value="5"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q10" value="4"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q10" value="3"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q10" value="2"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q10" value="1"></td>
			</tr>
			<tr>
				<td class="text-center" style="background-color: #e3f2fd;" colspan="7">
					교수의 학사 지원
				</td>
			</tr>
			<tr class="tr-question">
				<td class="text-center">11</td>
				<td>질문, 면담, 상담 등의 학생 요청에 적극적으로 응하였다.</td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q11" value="5"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q11" value="4"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q11" value="3"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q11" value="2"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q11" value="1"></td>
			</tr>
			<tr class="tr-question">
				<td class="text-center">12</td>
				<td>본인 분야의 지식 함양을 위해 지속적으로 노력하는 모습을 보였다.</td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q12" value="5"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q12" value="4"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q12" value="3"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q12" value="2"></td>
				<td class="text-center"><input type="radio" class="form-check-input" name="q12" value="1"></td>
			</tr>
			<tr class="tr-question">
				<td class="text-center">13</td>
				<td>교내의 주요 학사 일정 및 학사관련 업무를 이해하고 있다.</td>
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
let proNo = "${pro.proNo }";

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
			="${cPath}/astEnroll/proAstSunmit.do?qList="+qList+"&opnion="+opnion+"&proNo="+proNo;
	};
});
</script>