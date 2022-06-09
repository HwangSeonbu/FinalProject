<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 5. 24.      민진홍      마이페이지
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<h3 class="h3-title">마이페이지</h3><hr class="hr-title">
<security:authentication property="principal.realUser" 
	var="authMember"/>

<security:csrfMetaTags/>

<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery.form.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");



<c:if test="${not empty myPageMessage }">
<c:if test="${myPageSuccess eq 'success'}">
alert("${myPageMessage}");
var frm = document.getElementById("logoutFrm");
frm.submit();
</c:if>
<c:if test="${myPageSuccess ne 'success'}">
alert("${myPageMessage}");
</c:if>
</c:if>


$(document).ready(function(){
	(async () => {
		const { value: plainPass } = await Swal.fire({
		title: '비밀번호를 입력하세요',
		text: '초기비밀번호는 생년월일(YYMMDD)입니다.',
		input: 'password',
		inputPlaceholder: '비밀번호 입력',
		})
		// 이후 처리되는 내용.
		if (plainPass) {
			
			$.ajax({
				url : "${pageContext.request.contextPath}/myPageValidate",
				type : "POST",
				data : {"plainPass" : plainPass},
				dataType : "text",
				success : function(resp) {
					if(resp == "검증실패"){
						location.href="${cPath}/myPage";
					}
					const Toast = Swal.mixin({
						toast: true,
						position: 'center-center',
						showConfirmButton: false,
						timer: 1000,
						timerProgressBar: true,
						didOpen: (toast) => {
						toast.addEventListener('mouseenter', Swal.stopTimer)
						toast.addEventListener('mouseleave', Swal.resumeTimer)
						}
						});
						Toast.fire({
						icon: 'success',
						title: '비밀번호 검증 성공~'
						});

				},
				error : function(jqXHR, textStatus, errorThrown) {
					console.log(jqXHR);
					console.log(textStatus);
					console.log(errorThrown);
				},
				beforeSend : function(xhr){
					if(token && header) {
			            xhr.setRequestHeader(header, token);
			        }
				}
			
			});
			
		}else{
			
			location.href="${cPath}/main.do";
		}
		})()
});

<c:if test="${not empty firstLoginMessage }">
$(document).ready(function(){
	Swal.fire({ 
		icon: 'warning',// Alert 타입
		title: '알림', // Alert 제목
		text: '최초로그인시, 비밀번호를 변경 필수!', // Alert 내용
		});

	
});


</c:if>

</script>
<style>
.myPageDiv{
	width : 600px;

}
</style>
	<div class="editMyInfo">
<form class="row g-3" id="editMyInfoForm" action="${pageContext.request.contextPath }/editMyInfo" method="post">
<security:csrfInput/>
  <div class="col-md-8">
    <label for="inputName" class="form-label">이름</label>
    <input type="text" class="form-control" id="inputName" name="userName" readonly="readonly" value="${authMember.userName}">
  </div>
  
  <div class="col-md-4">
    <label for="inputGender" class="form-label">성별</label>
    <input type="text" class="form-control" id="inputGender" name="userGender" readonly="readonly" value="${authMember.userGender}">
  </div>
  
  <div class="col-12">
    <label for="inputUserNo" class="form-label">아이디</label>
    <input type="text" class="form-control" id="inputUserNo" name="userNo" value="${authMember.userNo }" readonly="readonly">
  </div>
  
  <div class="col-12 userPass">
    <label for="inputPassword" class="form-label">비밀번호</label>
    <input type="password" class="form-control" name="userPass" id="inputPassword" required="required">
  </div> 
  <div class="col-12 userPassConfirm">
    <label for="inputPassword" class="form-label">비밀번호 확인</label>
    <input type="password" class="form-control" name="userPassConfirm" id="userPassConfirm" required="required">
  </div> 
  
  <div class="col-12">
    <label for="inputPhone" class="form-label">전화번호</label>
    <input type="tel" class="form-control" id="inputPhone" name="userPhone" placeholder="000-0000-0000" value="${authMember.userPhone }">
  </div>
  
  <div class="col-12">
    <label for="inputEmail" class="form-label">이메일</label>
    <input type="email" class="form-control" id="inputEmail" name="userEmail" placeholder="Email" value="${authMember.userMail }">
  </div>
  
  <div class="col-12">
    <label for="inputAddress" class="form-label">주소</label>
    <input type="text" class="form-control" id="inputAddress" name="userAddr" placeholder="주소" value="${authMember.userAddr }">
  </div>
  <div class="col-12">
    <label for="inputAddress" class="form-label">상세주소</label>
    <input type="text" class="form-control" id="inputAddress2" name="userAddr2" placeholder="상세주소">
  </div>
 

  <div class="col-12">
    <button type="button" id="editMyInfoFormBtn" class="btn btn-primary" onclick="confirm(this.form);">저장하기</button>
  </div>
</form>
</div>

<script>
window.onload = function(){
    document.getElementById("inputAddress").addEventListener("click", function(){ //주소입력칸을 클릭하면
        //카카오 지도 발생
        new daum.Postcode({
            oncomplete: function(data) { //선택시 입력값 세팅
                document.getElementById("inputAddress").value = data.address; // 주소 넣기         
            }
        }).open();
    });
}




function confirm(infoForm){
	let pass1 = $('#inputPassword').val();
	  let pass2 = $('#userPassConfirm').val();
	  if(pass1 != pass2 || pass1 == "" || pass1 == null){
		  const Toast = Swal.mixin({
				toast: true,
				position: 'center-center',
				showConfirmButton: false,
				timer: 500,
				timerProgressBar: true
				})
				
				Toast.fire({
				icon: 'error',
				title: '비밀번호를 확인하세요.'
				})
		  $('#userPassConfirm').focus();
	  }
	  else{
		  infoForm.submit();
	  }
	  
};

$(document).ready(function () {
document.querySelector("#userPassConfirm").addEventListener("input", function(){
	  let pass1 = $('#inputPassword').val();
	  let pass2 = $('#userPassConfirm').val();
	  
	  if(pass1 == pass2){
		  $('.passtext').empty();
// 		  $('.userPass').append($("<span>").addClass("text-primary passtext").html("비밀번호 확인"));
		  $('.userPassConfirm').append($("<span>").addClass("text-primary passtext").html("비밀번호 확인"));
	  }else{
		  $('.passtext').empty();
// 		  $('.userPass').append($("<span>").addClass("text-danger passtext").html("비밀번호 확인"));
		  $('.userPassConfirm').append($("<span>").addClass("text-danger passtext").html("비밀번호 확인"));
	  }
});
document.querySelector("#inputPassword").addEventListener("input", function(){
	  let pass1 = $('#inputPassword').val();
	  let pass2 = $('#userPassConfirm').val();
	  
	  if(pass1 == pass2){
		  $('.passtext').empty();
// 		  $('.userPass').append($("<span>").addClass("text-primary passtext").html("비밀번호 확인"));
		  $('.userPassConfirm').append($("<span>").addClass("text-primary passtext").html("비밀번호 확인"));
	  }else{
		  $('.passtext').empty();
// 		  $('.userPass').append($("<span>").addClass("text-danger passtext").html("비밀번호 확인"));
		  $('.userPassConfirm').append($("<span>").addClass("text-danger passtext").html("비밀번호 확인"));
	  }
});
});
</script>

