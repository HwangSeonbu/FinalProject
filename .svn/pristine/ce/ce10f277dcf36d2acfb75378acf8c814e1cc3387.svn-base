<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.5.0/sockjs.min.js"></script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
	<script src="${pageContext.request.contextPath }/resources/js/jquery-3.6.0.min.js"></script>

	
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
	 <link href="${pageContext.request.contextPath }/resources/js/bootstrap-5.1.3-dist/css/bootstrap.css" rel="stylesheet">
	 
	 <style>
	    main{
	    height: -webkit-fill-available;
    }
	 </style>
	 <main class="d-flex flex-nowrap">
	<div class="d-flex flex-column flex-shrink-0 bg-light" style="width: 4.5rem;">
    <a href="/" class="d-block p-3 link-dark text-decoration-none" data-bs-toggle="tooltip" data-bs-placement="right">
      <svg class="bi pe-none" width="40" height="32"><use xlink:href="#bootstrap"></use></svg>
      <span class="visually-hidden">Icon-only</span>
    </a>
    <ul class="nav nav-pills nav-flush flex-column mb-auto text-center">
      <li class="nav-item">
        <a href="#" class="nav-link active py-3 border-bottom rounded-0" aria-current="page" data-bs-toggle="tooltip" data-bs-placement="right">
          <svg class="bi pe-none" width="24" height="24" role="img" aria-label="Home"><use xlink:href="#home"></use></svg>
        </a>
      </li>
      <li>
        <a href="#" class="nav-link py-3 border-bottom rounded-0" data-bs-toggle="tooltip" data-bs-placement="right">
          <svg class="bi pe-none" width="24" height="24" role="img" aria-label="Dashboard"><use xlink:href="#speedometer2"></use></svg>
        </a>
      </li>
      <li>
        <a href="#" class="nav-link py-3 border-bottom rounded-0" data-bs-toggle="tooltip" data-bs-placement="right">
          <svg class="bi pe-none" width="24" height="24" role="img" aria-label="Orders"><use xlink:href="#table"></use></svg>
        </a>
      </li>
      <li>
        <a href="#" class="nav-link py-3 border-bottom rounded-0" data-bs-toggle="tooltip" data-bs-placement="right">
          <svg class="bi pe-none" width="24" height="24" role="img" aria-label="Products"><use xlink:href="#grid"></use></svg>
        </a>
      </li>
      <li>
        <a href="#" class="nav-link py-3 border-bottom rounded-0" data-bs-toggle="tooltip" data-bs-placement="right">
          <svg class="bi pe-none" width="24" height="24" role="img" aria-label="Customers"><use xlink:href="#people-circle"></use></svg>
        </a>
      </li>
    </ul>
    <div class="dropdown border-top">
      <a href="#" class="d-flex align-items-center justify-content-center p-3 link-dark text-decoration-none dropdown-toggle" id="dropdownUser3" data-bs-toggle="dropdown" aria-expanded="false">
        <img src="https://github.com/mdo.png" alt="mdo" width="24" height="24" class="rounded-circle">
      </a>
      <ul class="dropdown-menu text-small shadow" aria-labelledby="dropdownUser3">
        <li><a class="dropdown-item" href="#">New project...</a></li>
        <li><a class="dropdown-item" href="#">Settings</a></li>
        <li><a class="dropdown-item" href="#">Profile</a></li>
        <li><hr class="dropdown-divider"></li>
        <li><a class="dropdown-item" href="#">Sign out</a></li>
      </ul>
    </div>
  </div>
<h4>PUSH message using WebSocket and STOMP</h4>
<div> LoginController 와 BoardInsertController 를 확인할 것. </div>
<div id="messageArea"></div>	

</main>
<script type="text/javascript">
	let client = null;
	let headers = {}
	let messageArea = $("#messageArea");
	function init(event) {
		var sockJS = new SockJS("${cPath}/stomp/echo");
		client = Stomp.over(sockJS);
		client.connect(headers, function(connectFrame) {
			client.subscribe("/topic/push", function(messageFrame) {
				let messageBody = JSON.parse(messageFrame.body);
				let messageType = messageBody.messageType;
				let message = messageBody.message;
				let data = messageBody.data?JSON.stringify(messageBody.data):"";
				messageArea.empty();
				messageArea.append(
					$("<p>").html("메시지 타입 : " + messageType)
					, $("<p>").html("메시지 : " + message)
					, $("<p>").html("데이터 : " + data)
				);
			});
		}, function(error) {
			console.log(error);
		});
	}
	function disconnect(event) {
		if (!client || !client.connected)
			throw "stomp 연결 수립 전";
		client.disconnect();
	}
	$(document).ready(init);
	
</script>

    <script src="${pageContext.request.contextPath }/resources/js/bootstrap-5.1.3-dist/js/bootstrap.bundle.min.js"></script>