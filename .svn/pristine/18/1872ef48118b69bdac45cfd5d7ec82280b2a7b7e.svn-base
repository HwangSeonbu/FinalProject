<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 4. 26.     민진홍      최초작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h3 class="h3-title">(UI, 강의게시판으로 이동, 수업계획서 조회 추가예정)</h3><hr class="hr-title">
<table class="table table-hover align-middle sugangTable">
  <thead>
		<tr>
			<th>강의번호</th>
			<th>강의명</th>
			<th>강의실명</th>
			<th>강의시간</th>
			<th>이수구분</th>
			<th>학점</th>
			<th>교수이름</th>
		</tr>
	</thead>

	<tbody id="listbody">
		
	</tbody>
</table>

<script>
const VIEWURLPTRN = "${pageContext.request.contextPath}/lecture/apply?lecId=%V&lecSems=%C";

let listBody = $("#listbody");


//수강목록조회
$.ajax({
	url : "${pageContext.request.contextPath}/sugang/lecturesList",
	dataType:"json" // Accept(request)/Content-Type(response)
	, success:function(paging){
		listBody.empty();
		let trTags = [];
		if(paging && paging.length > 0){
			$.each(paging, function(idx, data){
				let trTag = $("<tr>").addClass("linkBtn")
									.append(
										$("<td>").html(data.LEC_ID)	
										, $("<td>").html(data.SJT_NAME)	
										, $("<td>").html(data.GWAN_NAME+"/"+data.ROOM_NO+"호")
										, $("<td>").html(data.LEC_TIME)
										, $("<td>").html(data.SJT_MAJOR)	 
										, $("<td>").html(data.SJT_CREDIT)	
										, $("<td>").html(data.USER_NAME)	
									);
				trTags.push(trTag);
			});
		}else{
			let trTag = $("<tr>").html(
							$("<td>").attr("colspan", "6")
									 .html("수강중인 강의가 없음")
						);
			trTags.push(trTag);
		} // if end
		
		listBody.append(trTags);

	} // success end
	, resetForm : false
});



</script>


