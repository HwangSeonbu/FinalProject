<%--
 학생이 복학 신청을 할 수 있는 페이지(처리는 그냥 승인될 것이다)
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 5. 2.   김재웅  		최초작성
* 2022. 5. 12	고성식		기본ui 작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<security:authentication property="principal.realUser" var="authMember"/>
<h3 class="h3-title">복학 신청</h3><hr class="hr-title">
<div class="col-xs-12 col-sm-12 alert alert-warning text-center" style="margin-bottom:0;"><strong>
		복학 신청 방법 : On-line 제출 신청 후 제출서류(복학원서,전역증사본 또는 병적증명서)를 메일 또는 교무처, 학과사무실로  제출 바랍니다.<br />
								E-mail : haksa@ddit.com <br>
                                (문의처:042-222-8202)
</strong>
</div>
<br>
<a href="${cPath }/resources/wordTemplate/복학원서_양식.hwp" download="" class="btn btn-primary btn-sm" style="float: right">양식파일다운로드</a>
<br>
<br>
<table class="table table-boardered" border="1">
	<tr>
		<th>학번</th>
		<td>
			<input class="form-control"  type="text" id="userNo" name="userNo" value="${authMember.userNo }" maxlength="15" readonly="readonly"/>
			<span></span>
		</td>
	</tr>
	<tr>
		<th>이름</th>
		<td>
			<input class="form-control"  type="text" id="userName" name="userName" value="${authMember.userName }" maxlength="15" readonly="readonly"/>
			<span></span>
		</td>
	</tr>
	<tr>
		<th>학과</th>
		<td>
			<input class="form-control"  type="text" id="userDepartmentName" name="userDepartmentName" value="${authMember.userDepartmentName }" maxlength="15" readonly="readonly"/>
			<span></span>
		</td>
	</tr>
	<tr>
		<th>반</th>
		<td>
			<input class="form-control"  type="text" id="stuClass" name="stuClass" value="${authMember.stuClass }" maxlength="15" readonly="readonly"/>
			<span></span>
		</td>
	</tr>
	<tr>
		<th>학년</th>
		<td>
			<input class="form-control"  type="text" id="stuYear" name="stuYear" value="${authMember.stuYear }" maxlength="15" readonly="readonly"/>
			<span></span>
		</td>
	</tr>
	<tr>
		<th>복학 예정일</th>
		<td>
			<select id="lecSems">
				<c:forEach items="${semsdata}" var="list">
					<option value="${list}">${list}</option>
				</c:forEach>
			</select>학기
		</td>
	</tr>
</table>
<hr>
<h6 style="text-align:center"><strong> ✔ 복학 신청후 학기 시작일 7일전까지  교무처 또는 학과 사무실로 증빙서류를 꼭 제출 바랍니다✔ </strong></h6>
<hr>
<div style="float: right">
	<input type="submit" class="btn btn-primary btn-sm" onclick="requestReturnStudent();" value="복학신청">
	<input type="button" class="btn btn-primary btn-sm"value="뒤로" onclick="javascript:history.go(-1);">
</div>
<script>
let token = $("meta[name='_csrf']").attr("content");
let header = $("meta[name='_csrf_header']").attr("content");
var thisSems = ${sessionScope.semsVo.nextSems};

$(function(){
	$("#lecSems").val(thisSems);
});

//이벤트 Function
var eventFunction = function(){
	$("#applyBtn").on("click", function(){
		
	});
	
}

function requestReturnStudent(){
	let jsonEle = {
			"userNo" : $("#userNo").val(),
			"reqSms" : $("#lecSems").val(),
	};
	
	$.ajax({
		url : "${cPath}/schoolReq/requestReturnSchoolStu",
		type : "post",
		data : JSON.stringify(jsonEle),
		contentType : "application/json",
		dataType : "json",
		beforeSend : function(xhr) {
			if (token && header) {
				xhr.setRequestHeader(header, token);
			}
		},
		success : function(resp) {
			alert("복학신청이 완료되었습니다.");
			location.reload();

		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR);
			console.log(textStatus);
			console.log(errorThrown);
		}

	});
}
</script>