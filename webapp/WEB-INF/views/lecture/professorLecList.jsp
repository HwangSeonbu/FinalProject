<%--
교수가 자신이 맡은 강의에 대한 목록을 조회하는 jsp
(제목을 클릭하면 점수 비중을 설정하는 jsp로 이동한다)
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 4. 28.      김재웅      최초작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>
 <?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h3 class="h3-title">진행강의 현황 조회</h3><hr class="hr-title">
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery.form.min.js"></script>
<style>
	.table-hover{
		cursor: pointer;
	}
</style>
<table class="table table-bordered">
 <thead >
    <tr class="text-center">
    	<th>강의명</th>	
    	<th>이수학점</th>	
    	<th>강의구분</th>	
    	<th>대상학년</th>	
    	<th>강의관</th>	
    	<th>강의실</th>	
    	<th>배정시간</th>	
    </tr>
 </thead>
 <tbody class="text-center" id="listBody">
 
 </tbody>
</table>
<script>
var noDataCode = "<div class='div-imageWrap' id='imageWrap'><div class='div-imageInnerWrap'>";
noDataCode += "<img alt='' src='${cPath }/resources/img/noData.jpg' style='width:100%;'/></div>";
noDataCode += "<div class='div-textInnerImage'> 당학기 진행 중인<br>강의가 없습니다.</div></div>";


let listBody = $("#listBody");

$.ajax({
	url : "${pageContext.request.contextPath}/lecView/lectureDataList.do",
	method : "get",
	dataType : "json",
	success : function(resp) {
		listBody.empty();
// 		searchForm[0].page.value="";
		let proLectureList = resp.proLectureList;
		let trTags = [];
		if(proLectureList && proLectureList.length > 0 && proLectureList[0].lecId != null){
			$.each(proLectureList, function(idx, lecture){
// 				let viewURL = VIEWURLPTRN.replace("%V", prod.prodId);
				let trTag = $("<tr>").addClass("linkBtn")
									.attr("data-plan-no", lecture.planNo)
									.append(
										$("<td>").html(lecture.lecName)	
										, $("<td>").html(lecture.sjtCredit)	
										, $("<td>").html(lecture.sjtMajor)	
										, $("<td>").html(lecture.sjtGrade)	
										, $("<td>").html(lecture.gwanName)	
										, $("<td>").html(lecture.roomNo)	
										, $("<td>").html(lecture.assignDt)	
									);
				trTags.push(trTag);
			});
		}else{
			let trTag = $("<tr>").append(
							$("<td>").attr("colspan", "7")
									 .html(noDataCode)
						);
			trTags.push(trTag);
		} // if end
		
		listBody.append(trTags);
// 		pagingArea.html(paging.pagingHTMLBS)
	},
	error : function(jqXHR, textStatus, errorThrown) {
		console.log(jqXHR);
		console.log(textStatus);
		console.log(errorThrown);
	}
});

$(document).on("click", ".linkBtn", function(){
	let planNo = $(this).data("planNo");
	alert(planNo);
});

</script>