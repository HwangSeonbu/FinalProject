<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <style>
    .bi-caret-right-fill{
    font-size: 3rem;
    color : #0d6efd;
	}
	#searchSum{
		width:80%;
	}
    </style>

<h4>친구목록</h4>
  <div class="row p-2">
  <div class="col p-0">
<div id="searchUserListDiv">
<div class="form-check form-check-inline">
  <input class="form-check-input" name="student" type="checkbox" value="학생" id="flexCheckChecked">
  <label class="form-check-label">
    학생만
  </label>
</div>
<div class="form-check form-check-inline">

  <input class="form-check-input" name="professor" type="checkbox" value="교수" id="flexCheckChecked">
  <label class="form-check-label">
    교수만
  </label>
</div>
<div class="form-check form-check-inline">

  <input class="form-check-input" name="manager" type="checkbox" value="학사관리자" id="flexCheckChecked">
  <label class="form-check-label">
    학사관리자만
  </label>
</div>


<select name="deptName" class="form-select p-0" id="searchSum">
		</select>
 <input type="text" class="form-control p-0" placeholder="이름으로 검색" name="searchName" id="searchSum"/>	
 
</div>
</div>
 <div class="col p-0 align-items-center" style="text-align:center;">
 	<button type="button" id="sendMessageBtn" class="btn btn-primary btn-lg">메세지 보내기</button>
 </div>
</div>
<!-- 유저리스트 -->
  <div class="row p-2">
  <div class="col p-0">
  <div class="form-check">
  <input class="form-check-input" type="checkbox" id="allCheck">
  <label class="form-check-label" for="defaultCheck1">
    전체선택
  </label>
</div>
<div class="container p-0 border border-dark" id="messageUserListBody" style="margin:0; margin-top: 10px;">

</div>
</div>
<!-- 선택한유저리스트 --><div class="col-1 p-0 align-items-center" style="align-self:center; text-align:center;"> <i class="bi bi-caret-right-fill"></i></div>
<div class="col p-0">
<div class="form-check">
  <input class="form-check-input" type="checkbox" id="allUnCheck">
  <label class="form-check-label" for="defaultCheck1">
    전체해제
  </label>
</div>
<div class="container p-0 border border-dark" style="margin:0; margin-top: 10px;">
<div class="list-group user-list w-100" id="sendMessageUserListBody">
</div>
</div>
</div>
</div>




<script>

var messageUserListBody = $("#messageUserListBody");
var sendMessageUserListBody = $("#sendMessageUserListBody");

// 추가
$('#messageUserListBody').change('input[type=checkbox]',function(){
	sendMessageUserListBody.prepend($("#messageUserListBody input[type='checkbox']:checked").parent());
	$("#allUnCheck").prop("checked",true);
});
//전체선택
$('#allCheck').on('click',function(){
	var checked = $('#allCheck').is(':checked');
	if(checked){
	$("#messageUserListBody input[type='checkbox']").prop("checked", true);
	sendMessageUserListBody.prepend($("#messageUserListBody input[type='checkbox']:checked").parent());
	$("#allCheck").prop("checked",false);
	$("#allUnCheck").prop("checked",true);
	}

	
});
//전체해제
$('#allUnCheck').on('click',function(){
	var checked = $('#allUnCheck').is(':checked');
	if(!checked){
	$("#sendMessageUserListBody input[type='checkbox']").prop("checked", false);
	var uncheck = $("#messageUserListBody .user-list");
	uncheck.prepend($("#sendMessageUserListBody input:checkbox:not(:checked)").parent());
	$("#allUnCheck").prop("checked",false);
	}

	
});

// 뺴기
$('#sendMessageUserListBody').change('input[type=checkbox]',function(){
	var uncheck = $("#messageUserListBody .user-list");
	uncheck.prepend($("#sendMessageUserListBody input:checkbox:not(:checked)").parent());
});


var searchDIV = $('#searchUserListDiv');
//deptName 학과 조회
$.ajax({
	url : "${pageContext.request.contextPath}/messageDeptNameList",
	dataType : "json",
	success : function(resp) {
		let options = [];
		options.push($("<option>").attr("value", "").text("학과"));
		$(resp).each(function(index, data){
			let option = $("<option>").attr("value", data.DEPT_NAME)
									  .text(data.DEPT_NAME);
			options.push(option);
		});
		searchDIV.find('[name=deptName]').append(options);
		//이 밑에꺼는 학과 골랐을때 옆에 메뉴도 바꾸고싶으면 만들까..?
// 		$(prodLguTag).val("${paging.detailCondition.prodLgu}");
// 		$(prodLguTag).trigger("change");			
	},
	error : function(jqXHR, textStatus, errorThrown) {
		console.log(jqXHR);
		console.log(textStatus);
		console.log(errorThrown);
	}
});


    
    	$.ajax({
		url : "${pageContext.request.contextPath}/messageUserList",
		dataType : "json",
		success : function(messageUserList) {
			messageUserListBody.empty();
		
			let trTags = [];
	
			let listDiv =  $("<div>").addClass("list-group user-list w-100");
			if(messageUserList && messageUserList.length > 0){
			$.each(messageUserList, function(idx, data){
			
				let trTag = listDiv.append(
							$("<label>").addClass("list-group-item").append(
							$("<input>").addClass("form-check-input me-1").attr('type','checkbox')
							,$("<input>").addClass("receiverUser").attr('hidden','hidden').text(data.userNo)
							,$("<span>").addClass("badge " + (data.userType == '학생' ? "bg-primary" : data.userType == '교수' ? "bg-success" : data.userType == '학사관리자' ? "bg-warning" : "bg-info" ))
							.html((data.userType == '학생' ? "학생" : data.userType == '교수' ? "교수" : data.userType == '학사관리자' ? "학사관리자" : "소속없음" ))
							,$("<span>").addClass("badge bg-secondary").html(data.userDepartmentName)
							,$("<span>").addClass("badge bg-dark").html(data.userName)
							
							)
						)
			
				trTags.push(trTag);	
			});
			}else{
				let trTag = $("<tr>").html(
								$("<td>").attr("colspan", "10")
										 .html("조건에 맞는 데이터가 없음.")
							);
				trTags.push(trTag);
			} // if end		
			messageUserListBody.append(trTags);
		
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR);
			console.log(textStatus);
			console.log(errorThrown);
		},
	});
    	
    	
    	//체크박스
    $('#searchUserListDiv input[type=checkbox]').change(function(){
    	searchUserList();
    });
    $('#searchUserListDiv select[name=deptName]').change(function(){
    	searchUserList();
    });
    $('#searchUserListDiv input[name=searchName]').on('input', function() {
    	searchUserList();
    });
    
    	
    
    	
    	
    	function searchUserList(){
    	var userTypeList = [];
    	  $("#searchUserListDiv input[id='flexCheckChecked']:checked").each(function(i){   //jQuery로 for문 돌면서 check 된값 배열에 담는다
    		  userTypeList.push($(this).val());
    	  });
    	  var deptName = searchDIV.find('[name=deptName]').val();
    	  var searchName = searchDIV.find('[name=searchName]').val();
    	  var data = {"userTypeList":userTypeList, "deptName" : deptName, "searchName" : searchName};
    	  
    	$.ajax({
    		url : "${pageContext.request.contextPath}/messageUserList",
    		data : data,
    		dataType : "json",
    		success : function(messageUserList) {
    			messageUserListBody.empty();
    		
    			let trTags = [];
    	
    			let listDiv =  $("<div>").addClass("list-group user-list w-100");
    			if(messageUserList && messageUserList.length > 0){
    			$.each(messageUserList, function(idx, data){
    			
    				let trTag = listDiv.append(
    							$("<label>").addClass("list-group-item w-100").append(
    							$("<input>").addClass("form-check-input me-1").attr('type','checkbox')
    							,$("<input>").addClass("receiverUser").attr('hidden','hidden').text(data.userNo)
    							,$("<span>").addClass("badge " + (data.userType == '학생' ? "bg-primary" : data.userType == '교수' ? "bg-success" : data.userType == '학사관리자' ? "bg-warning" : "bg-info" ))
    							.html((data.userType == '학생' ? "학생" : data.userType == '교수' ? "교수" : data.userType == '학사관리자' ? "학사관리자" : "소속없음" ))
    							,$("<span>").addClass("badge bg-secondary").html(data.userDepartmentName)
    							,$("<span>").addClass("badge bg-dark").html(data.userName)
    							
    							)
    						)
    			
    				trTags.push(trTag);	
    			});
    			}else{
    				let trTag = $("<tr>").html(
    								$("<td>").attr("colspan", "10")
    										 .html("조건에 맞는 데이터가 없음.")
    							);
    				trTags.push(trTag);
    			} // if end		
    			messageUserListBody.append(trTags);
    		
    		},
    		error : function(jqXHR, textStatus, errorThrown) {
    			console.log(jqXHR);
    			console.log(textStatus);
    			console.log(errorThrown);
    		},
    	});
    	
    }
    
    	
    	$('#sendMessageBtn').on('click',function(){ 
    		receiver = [];
    		$("#sendMessageUserListBody input[type='checkbox']:checked").next('.receiverUser').each(function(i,val){
    			receiver.push($(this).text());
    		});
    		//배열비어있으면
    		if(Array.isArray(receiver) && receiver.length === 0){
    			Swal.fire({
    				width : '400px',	
    				text:'보낼사람을 먼저 선택하세요.'}
    			);
    		}else{
    	
    		sendMemoContent = $('#sendMemoContent').val();
    		$('#exampleModal').modal('show');
    		}
    	})
    	
    </script>