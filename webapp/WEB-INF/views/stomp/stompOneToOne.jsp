<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.5.0/sockjs.min.js"></script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
	<script src="${pageContext.request.contextPath }/resources/js/jquery-3.6.0.min.js"></script>
	
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
	 <link href="${pageContext.request.contextPath }/resources/js/bootstrap-5.1.3-dist/css/bootstrap.css" rel="stylesheet">
	 <!--     alert (MJH) -->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
    
	<security:csrfMetaTags/>
	<style>
	span{
		margin-left: 4px;
	}
	.user-list{
		width: 300px;
		height: 500px;
		overflow: auto;
	}
.modal-dialog .modal-fullsize {
  width: 100%;
  height: 90%;
  margin: 0;
  padding: 0;
}
.receiveText{
overflow: hidden;
text-overflow: ellipsis;
display: -webkit-box;
line-height: 20px;
max-height: 40px;
-webkit-line-clamp: 2; /* 표시하고자 하는 라인 수 */
-webkit-box-orient: vertical;
}
.receiveText2{
overflow: hidden;
text-overflow: ellipsis;
max-width: 60%;
-webkit-line-clamp: 1; /* 표시하고자 하는 라인 수 */
-webkit-box-orient: vertical;
}
#receiveMessageArea{
	max-height: 250px;
	overflow: auto;
}
.messageCard:hover{
	background-color : gray;
	cursor: pointer;
}
.messageDeleteBtn:hover{
	background-color : white;
}
	
	</style>
	<div class="d-flex flex-nowrap" style="height: -webkit-fill-available;">
<div class="d-flex flex-column flex-shrink-0 bg-light" style="width: 4.5rem;">
    <ul class="nav nav-pills nav-flush flex-column mb-auto text-center">
      <li id="friendsHome" class="nav-item">
        <a href="#" class="d-block p-3 link-dark text-decoration-none">
     <i class="bi bi-person-circle" style="font-size: 2.25rem !important;" id="personIcon"></i>
        </a>
      </li>
      <li id="messageHome" class="nav-item">
        <a href="#" class="d-block p-3 link-dark text-decoration-none">
         <i class="bi bi-envelope" style="font-size: 2.25rem !important; color:#0d6efd;"></i>
    </a>
      </li>
      <li id="sendmessageHome" class="nav-item">
        <a href="#" class="d-block p-3 link-dark text-decoration-none">
         <i class="bi bi-send-check" style="font-size: 2.25rem !important;"></i>
    </a>
      </li>
    </ul>
  </div>
	
<!-- 	<div class="form-check"> -->
<!--   <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault"> -->
<!--   <label class="form-check-label" for="flexCheckDefault"> -->
<!--     Default checkbox -->
<!--   </label> -->
<!-- </div> -->
<!-- <div class="form-check"> -->
<!--   <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" checked> -->
<!--   <label class="form-check-label" for="flexCheckChecked"> -->
<!--     Checked checkbox -->
<!--   </label> -->
<!-- </div> -->
	<div class="container bg-light" id="messageListBody" style="margin:0; margin-top: 10px;">
		
		
		
</div>
    </div>

    


<!--메시지 보내기 Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-fullsize modal-xl">
    <div class="modal-content modal-fullsize modal-xl">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">메시지 보내기</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <form id="sendMessageForm">
      
        	<div class="form-floating">
 				<textarea class="form-control" placeholder="Leave a comment here" id="sendMemoContent" style="height: 100%" required="required"></textarea>
  				<label for="sendMemoContent">내용</label>
			</div>
      
<!-- 			<h4>메시징!!</h4> -->
<!-- 			<input type="text" id="message" onchange="messageSend(event);" placeholder="메시지 입력 후 엔터"/> -->
<!-- 			<input type="button" value="종료" onclick="disconnect(event);"> -->
<!-- 			<div id="messageArea"></div> -->
<!-- Button trigger modal -->
        
        
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" id="messageSubmitBtn">보내기</button>
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
      </div>
    </div>
  </div>
</div>
	
<!-- 	읽고 답장하기 modal -->
	<div class="modal fade" id="readNwriteModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-fullsize modal-xl">
    <div class="modal-content modal-fullsize modal-xl">
 
      <div class="modal-body">
      <div class="bd-example">
      <div class="card">
		  <div class="card-body">
		    <h5 class="card-title" id="receiveMessageSender"></h5>
		    <p class="card-text" id="receiveMessageArea"> </p>
		  </div>
		</div>
      </div>
      <hr>
        <form id="sendMessageForm">
      
        	<div class="form-floating">
 				<textarea class="form-control" placeholder="Leave a comment here" id="replyMemoContent" style="height: 200px;" required="required"></textarea>
  				<label for="replyMemoContent">답장내용</label>
			</div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" id="replyMessageSubmitBtn">보내기</button>
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
      </div>
    </div>
  </div>
</div>

<security:authentication property="principal.username" var="username"/>
<script type="text/javascript">

//토스트
var Toast = Swal.mixin({
    toast: true,
    position: 'center-center',
    showConfirmButton: false,
    timer: 1000,
    timerProgressBar: true,
    didOpen: (toast) => {
        toast.addEventListener('mouseenter', Swal.stopTimer),
        toast.addEventListener('mouseleave', Swal.resumeTimer)
    }
})



$('#messageSubmitBtn').on('click',function(){
	sendMemoContent = $('#sendMemoContent').val();
	if(sendMemoContent=='' || sendMemoContent==null || sendMemoContent.length == 0 || sendMemoContent.trim()==''){
		Swal.fire({
			width : '400px',	
			text:'메시지내용을 입력하세요.'}
		);
	}else{
	//모달창닫고 내용지우기
	$('#exampleModal').modal('hide');
	$('#sendMemoContent').val('');
	messageSend(event);
	}
});




var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
var receiver = []; //수신자목록
var sendMemoContent = ""; //보낼메시지
	let client = null;
	let headers = {};
	let messageArea = $("#messageArea");
	function writeMessage(messageBody){
		let sender = messageBody.sender;
		let messageType = messageBody.messageType;
		let message = messageBody.message;
		let data = messageBody.data?JSON.stringify(messageBody.data):"";
		messageArea.append(
			$("<p>").html(sender + ": " + message)
		);
		
	}
// 	ㅡㅡㅡㅡㅡㅡㅡㅡㅡ소켓연결 시작
	 function init(event) {
		var sockJS = new SockJS("${cPath}/stomp/echo");
		client = Stomp.over(sockJS);
		client.connect(headers, function(connectFrame) {
			client.subscribe("/user/queue/DM", function(messageFrame) {
				//메시지 받았을때 하는일
				console.log(messageFrame);
				let messageBody = JSON.parse(messageFrame.body);
				messageHome();
			},{id:"check"});
		}, function(error) {
			console.log(error);
		});
	} 
	function messageSend(event) {
		if (!client || !client.connected)
			throw "stomp 연결 수립 전";
		var sendData = {"message" : sendMemoContent, "receiver" : receiver};
		
	
		
		
		$.ajax({
			url : "${pageContext.request.contextPath}/sendMessage",
			method : 'POST',
			data: JSON.stringify(sendData),
			contentType : 'application/json; charset=UTF-8',
			dataType : "json",
			beforeSend : function(xhr){
				if(token && header) {
		            xhr.setRequestHeader(header, token);
		        }
			},
			success : function(paging) {
				$.each(sendData.receiver, function(idx,receiverId){
					
				
				let body = {
					messageType : "DM"
					, message : sendData.message
					, receiver : receiverId
				}
				
				body.sender = "${username}";
				client.send("/user/"+receiverId+"/queue/DM", headers, JSON.stringify(body));
				});
				
				 Toast.fire({
	                    icon: 'success',
	                    title: '메시지보내기 성공~'
	                });
				
				
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR);
				console.log(textStatus);
				console.log(errorThrown);
			},
		});

		
	}
	function disconnect(event) {
		if (!client || !client.connected)
			throw "stomp 연결 수립 전";
		client.disconnect();
	}
	$(document).ready(init);
	
	$(window).on("unload", disconnect);
</script>

<script>


//받은메세지 확인 시작
var messageListBody = $("#messageListBody");
messageHome(); //페이지 첫시작시 메세지홈
function messageHome(){
	

	$.ajax({
		url : "${pageContext.request.contextPath}/messageList",
		dataType : "json",
		success : function(messageList) {
			messageListBody.empty();
			
			let trTags = [];
			
			messageListBody.append($("<h4>").html("메시지함"));
			
			
			
			if(messageList && messageList.length > 0){
			$.each(messageList, function(idx, data){
// 				<div class="card border-info text-dark" style="margin-top: 10px;">

				let trTag = $("<div>").addClass("card messageCard").css("margin-top","10px").append(
						
// 						<button type="button" class="btn-close position-absolute top-0 end-0"></button>
						$("<button>").attr('type','button').addClass("btn-close position-absolute top-0 end-0 messageDeleteBtn").attr('id',data.MEMO_NO).css({'margin-top':'10px', 'margin-right':'10px'}),
// 				  <div class="card-header bg-info">
							$("<div>").addClass("card-body").append($("<h6>").addClass("card-title messageSenderName").text(data.USER_NAME)
									,$("<p>").addClass("card-text receiveText").html(data.MEMO_CONTENT)
									,$("<p>").attr('id','messageSenderNo').attr('hidden','hidden').text(data.USER_NO)
									,$("<p>").attr('id','messageMemoNo').attr('hidden','hidden').text(data.MEMO_NO)
									,$("<span>").addClass("badge "+(data.VIEW_DATE == null ? 'bg-danger' : 'bg-info')).html(data.VIEW_DATE == null ? '안읽음' : '읽음') 
									,$("<span>").addClass("badge bg-secondary").html(data.SEND_DATE) 
									
							)
							
				)
				trTags.push(trTag);	
			});
			}else{
				let trTag = $("<tr>").html(
								$("<td>").attr("colspan", "10")
										 .html("받은메시지가 없습니다.")
							);
				trTags.push(trTag);
			} // if end		
			messageListBody.append(trTags);
			
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR);
			console.log(textStatus);
			console.log(errorThrown);
		},
	});
}
//받은메세지 확인 끝

//sidebar 메세지홈버튼
$('#messageHome').on('click',function(){
	$('#sendmessageHome').find('.bi').css('color','black');
	$('#friendsHome').find('.bi').css('color','black');
	$(this).find('.bi').css('color','#0d6efd');
	messageHome();
});


//보낸메시지함
function sendMessageHome(){
	
	$.ajax({
		url : "${pageContext.request.contextPath}/sendMessageList",
		dataType : "json",
		success : function(messageList) {
			messageListBody.empty();
			
			let trTags = [];
			
			messageListBody.append($("<h4>").html("보낸메시지함"));
			
			
			
			if(messageList && messageList.length > 0){
			$.each(messageList, function(idx, data){
// 				<div class="card border-info text-dark" style="margin-top: 10px;">

				let trTag = $("<div>").addClass("card").css("margin-top","10px").append(
						
// 				  <div class="card-header bg-info">
							$("<div>").addClass("card-body").append($("<p>").addClass("card-title badge bg-success receiveText2").text("받는사람 : "+data.MEMBERLIST)
									,$("<p>").addClass("card-title badge bg-secondary position-absolute top-0 end-0").html("보낸 날짜 : "+data.SEND_DATE).css({'margin-top':'13px','margin-right':'10px'}) 
									,$("<p>").addClass("card-text").html(data.MEMO_CONTENT)

									
							)
							
				)
				trTags.push(trTag);	
			});
			}else{
				let trTag = $("<tr>").html(
								$("<td>").attr("colspan", "10")
										 .html("보낸메시지가 없습니다.")
							);
				trTags.push(trTag);
			} // if end		
			messageListBody.append(trTags);
			
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR);
			console.log(textStatus);
			console.log(errorThrown);
		},
	});
};



$('#sendmessageHome').on('click',function(){
	$('#messageHome').find('.bi').css('color','black');
	$('#friendsHome').find('.bi').css('color','black');
	$(this).find('.bi').css('color','#0d6efd');
	sendMessageHome();
});


$('#friendsHome').on('click',function(){
	$('#messageHome').find('.bi').css('color','black');
	$('#sendmessageHome').find('.bi').css('color','black');
	$(this).find('.bi').css('color','#0d6efd');
		     $.ajax({  
		      type : "GET", 
		       url : "${pageContext.request.contextPath}/messageUserList2", 
		       dataType : "html", 
		       error :	 function() {    
			      alert('통신실패!!');    
			    },       
			 success : function(data) {
				          messageListBody.html(data);
				        }		       
			});   
	
});

// 받은메시지 열기(읽음처리)
$(document).on('click','.messageCard .card-body',function(){
	let text = $(this).find('.receiveText').html();
	$('#receiveMessageArea').html(text);
	let messageSender = $(this).find('.messageSenderName').text();
	$('#receiveMessageSender').text(messageSender);
	receiver = [];
	receiver.push($(this).find('#messageSenderNo').text());
	let memoNo = $(this).find('#messageMemoNo').text();
	let senddata = "memoNo="+memoNo;
	let myid = ${username};
	   $.ajax({  
		       url : "${pageContext.request.contextPath}/messageReadFlag", 
				data : senddata,
		       dataType : "json", 
		       error :	 function() {    
			      alert('메시지 읽음처리실패..ㅠ');    
			    },       
			 success : function(data) {
				
				let body = {
						messageType : "DM"
						, message : "메시지읽음"
						, receiver : myid
					}
					
					body.sender = "${username}";
					client.send("/app/DM/check", headers, JSON.stringify(body));
	
	
				         messageHome();
				
				
				        }		       
			});   
	
	
	
	$('#readNwriteModal').modal('show');
});
//답장하기
$('#replyMessageSubmitBtn').on('click',function(){
sendMemoContent = $('#replyMemoContent').val();
if(sendMemoContent=='' || sendMemoContent==null || sendMemoContent.length == 0 || sendMemoContent.trim()==''){
	Swal.fire({
		width : '400px',	
		text:'메시지내용을 입력하세요.'}
	);
}else{
//모달창닫고 내용지우기
$('#readNwriteModal').modal('hide');
$('#replyMemoContent').val('');
messageSend(event);
}
});

//메시지삭제
$(document).on('click','.messageCard .messageDeleteBtn',function(){
	Swal.fire({
		   title: '메시지를 삭제 하시겠습니까?',
		   text: '삭제한 메시지는 다시 볼 수 없습니다.',
		   icon: 'warning',
		   width : '500px',
		   
		   
		   showCancelButton: true, // cancel버튼 보이기. 기본은 원래 없음
		   confirmButtonColor: '#3085d6', // confrim 버튼 색깔 지정
		   cancelButtonColor: '#d33', // cancel 버튼 색깔 지정
		   confirmButtonText: '확인', // confirm 버튼 텍스트 지정
		   cancelButtonText: '취소', // cancel 버튼 텍스트 지정
		   
// 		   reverseButtons: true, // 버튼 순서 거꾸로
		   
		}).then(result => {
		   // 만약 Promise리턴을 받으면,
		   if (result.isConfirmed) { // 만약 모달창에서 confirm 버튼을 눌렀다면
				let memoNo = $(this).attr('id');
				let senddata = "memoNo="+memoNo;
				let myid = ${username};
				   $.ajax({  
					       url : "${pageContext.request.contextPath}/messageDelete", 
							data : senddata,
					       dataType : "json", 
					       error :	 function() {    
						      alert('메시지 삭제처리실패..ㅠ');    
						    },       
						 success : function(data) {
							let body = {
									messageType : "DM"
									, message : "메시지삭제"
									, receiver : myid
								}
								
								body.sender = "${username}";
								client.send("/app/DM/check", headers, JSON.stringify(body));
							         messageHome();
							
							
							        }		       
						}); 
		      Swal.fire('삭제가 완료되었습니다.');
		   }
		});  
	
});
		</script>




    <script src="${pageContext.request.contextPath }/resources/js/bootstrap-5.1.3-dist/js/bootstrap.bundle.min.js"></script>
