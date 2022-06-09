<%--
 학생의 출결관련 통계를 종합하여 대시보드로 한눈에 보는 페이지
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 5. 2.      김재웅      최초작성
  2022. 5. 6.    주창규      실질적으로 사용하는 데이터 담기
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
<security:authentication property="principal.realUser" var="authMember"/>
<h3 class="h3-title">나의 출결 대시보드</h3><hr class="hr-title">
<style>
	a {
	  text-decoration: none;
	  color: black;
}
</style>

<div class="row row-cols-2">
<!--   	datailTable -->
	<div class="row row-cols-4">
		<div class="col">
	  		<div class="bd-highlight align-self-center">
	  			<div class="card border-dark mb-3 text-center" style="width: 102rem;">
	  				<div class="card-header fw-bold" id="lecNameDetail">출석상세정보</div>
	  					<div class="card-body">
	    					<table class="table table-bordered" id = "titleTable">
							    <thead id="detailHead">
							    	<tr>
										<td>과목</td>
										<td colspan="4">-</td>
									</tr>
								</thead>
								<tbody id="detailBody" >
								<tr>
									<td>총출석</td>
									<td>출석</td>
									<td>지각</td>
									<td>조퇴</td>
									<td>결석</td>
								</tr>
								<tr>
									<td>-</td>
									<td>-</td>
									<td>-</td>
									<td>-</td>
									<td>-</td>
								</tr>
								</tbody>
								<tfoot>
								</tfoot>
						    </table>
	  					</div>
	  			</div>
	  		</div>
		</div>
	</div>
</div>

<br>

<select name="semsdataSelection" id="semsdataSelectionId">
	<option></option>
</select>   
<table class="table table-bordered" id = "titleTable">
	<thead>
	</thead>
	<tbody id="attBody">
	
	</tbody>
	<tfoot>
	
	</tfoot>
</table>
<div id="extraArea"></div>
<script>
let semsdataSelection = $("#semsdataSelectionId");
let attBody = $("#attBody");
let detailBody = $("#detailBody");
let lecNameDetail = $("#lecNameDetail");


function startAjax(){
	$.ajax({
		url :"${cPath}/studentAttendance/semsdataForm",
		method : "get",
		dataType : "json",
		success : function(resp) {
			semsdataSelection.empty();
			let semsdataList = resp.semsdata;
			let firstLecSems = ${student.lecSems } +""
			let options = [];
			options.push($("<option>").attr("value",firstLecSems)
					.attr("selected", false)
					.text(firstLecSems.substr(0,4)+"년"+firstLecSems.substr(5,6)+"학기"));
			
			$(semsdataList).each(function(index,semsdata ){
				let option = $("<option>").attr("value",semsdata)
										  .text(semsdata.substr(0,4)+"년"+semsdata.substr(5,6)+"학기");
				options.push(option);
					});
			
			semsdataSelection.append(options);
			attendance($("#semsdataSelectionId").val());
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR);
			console.log(textStatus);
			console.log(errorThrown);
		}
	});
};
startAjax(); 
 
semsdataSelection.on("change",function(){
	let semsdata = $(this).val();
	if(semsdata == ""){
		alert("데이터가  존재하지 않습니다!");
	}else{
		attendance(semsdata); //년도 학기
	}
});

function attendance(semsdata){
	$.ajax({
		url :"${pageContext.request.contextPath}/studentAttendance/attendanceListAjax",
		method : "get",
		data : {semsdata:semsdata},
		dataType : "json",
		success : function(resp) {
			attBody.empty();
			let attBodyVo = resp.student;
			let attList = attBodyVo.subjectList;
			let dataArr = [];
			let canvasEl = "<tr>";
			
			if(attList.length <= 0){
				canvasEl += "<td>"+overNoDataCode+"</td>";
			}else{
				$.each(attList, function(idx,att){
					let attLecId = att.lecId;
					let attLecLecture = att.lecName;
					canvasEl += "<td><a href='#' onclick='javascript:attLecName(\""+ attLecId+"\", \""+att.lecName+"\");'>" +att.lecName+"</a>"+"<canvas class='myChart' ></canvas></td>";
					let attcnt = 0;
					let abscnt = 0;
					let earcnt = 0;
					let tarcnt = 0;
					if(att.attendanceCountVo){
						attcnt = att.attendanceCountVo.attendanceCount ? att.attendanceCountVo.attendanceCount : 0;
						abscnt = att.attendanceCountVo.absentCount ? att.attendanceCountVo.absentCount : 0;
						earcnt = att.attendanceCountVo.earlyLeaveCount ? att.attendanceCountVo.earlyLeaveCount : 0;
						tarcnt = att.attendanceCountVo.tardinessCount ? att.attendanceCountVo.tardinessCount : 0;
					}
					let cntArr = [
						attcnt, abscnt
	      	 		, earcnt
	      	 		, tarcnt];
					dataArr	.push(cntArr);
					if(idx ==3){
						canvasEl += "</tr><tr>";
					}
	      	 	});
			}
				canvasEl += "</tr>";
				attBody.html(canvasEl);
				
			$(".myChart").each(function(i,v){
				mychartContext = v.getContext('2d');
				var myChart = new Chart(mychartContext, {
				    type: 'pie', // 차트의 형태
				    data: { // 차트에 들어갈 데이터
				        labels: [
				     	    '출석'
				             ,'지각'
				             ,'조퇴'
				             ,'결석'
				        ],
				        datasets: [
				            { //데이터
				                label: '#11111', //차트 제목
				                fill: false, // line 형태일 때, 선 안쪽을 채우는지 안채우는지
				                data: dataArr[i] 	                           //x축 label에 대응되는 데이터 값
				                ,
				                backgroundColor: [
				                    //색상
				                    'rgba(255, 99, 132, 0.2)',
				                    'rgba(54, 162, 235, 0.2)',
				                    'rgba(255, 206, 86, 0.2)',
				                    'rgba(75, 192, 192, 0.2)',
				                    'rgba(153, 102, 255, 0.2)',
				                    'rgba(255, 159, 64, 0.2)'
				                ],
				                borderColor: [
				                    //경계선 색상
				                    'rgba(255, 99, 132, 1)',
				                    'rgba(54, 162, 235, 1)',
				                    'rgba(255, 206, 86, 1)',
				                    'rgba(75, 192, 192, 1)',
				                    'rgba(153, 102, 255, 1)',
				                    'rgba(255, 159, 64, 1)'
				                ],
				                borderWidth: 1 //경계선 굵기
				            }
				        ]
				    },
				    options: {
				    	responsive : false,
				        scales: {
				            yAxes: [
				                {
				                    ticks: {
				                        beginAtZero: true
				                    }
				                }
				            ]
				        }
				    }
				});
			});
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR);
			console.log(textStatus);
			console.log(errorThrown);
		}
	});
};	

function attLecName(attLecId, attLecNm){
	
	$.ajax({
		url :"${cPath}/studentAttendance/lectureList",
		method : "get",
		data : {
					 'attLecId':attLecId
					,'attLecNm':attLecNm
				},
		dataType : "json",
		success : function(resp) {
			
			detailBody.empty();
			let attlecture = resp.attLecture;
			var html = '';
			var htmlHead = '';
			console.log("attlecture : ", attlecture);
			console.log("attlecture : ", attlecture.TOTALCOUNT);
			
			html += '<tr><td>총출석</td><td>출석</td><td>지각</td><td>조퇴</td><td>결석</td></tr>';
			html += '<tr><td>'+ (attlecture.TOTALCOUNT != null ? attlecture.TOTALCOUNT : "-")  +'</td>';
			html += '<td>'+ (attlecture.ATTENDANCECOUNT  != null ? attlecture.ATTENDANCECOUNT : "-") +'</td>';
			html += '<td>'+ (attlecture.ABSENTCOUNT  != null ? attlecture.ABSENTCOUNT : "-") + '</td>';
			html += '<td>'+ (attlecture.EARLYLEAVECOUNT  != null ? attlecture.EARLYLEAVECOUNT : "-") + '</td>';
			html += '<td>'+ (attlecture.TARDINESSCOUNT  != null ? attlecture.TARDINESSCOUNT : "-") + '</td></tr>';
			
			
			$('#detailBody').html(html);
			
			htmlHead += '<tr><td>과목</td>';
			htmlHead += '<td colspan="4">' + attlecture.LEC_NAME + '</td></tr>';
			$('#detailHead').html(htmlHead);
					
		},
		error : function(jqXHR, textStatus, errorThrown) {
// 			jqXHR : 서버에서 발생한 에러 response 정보 전체를 반환해줌. web.xml 및 스프링 프레임워크에서 설정해준 에러 페이지 정보도 가지고 있음
			console.log(JSON.stringify(jqXHR));
			console.log(textStatus);
			console.log(errorThrown); // 에러 리턴 정보가 없음..

			var htmlHead = '<tr><td>과목</td>';
			htmlHead += '<td colspan="4">' + textStatus + ' 서버 에러가 발생 하였습니다. 관리자에게 문의 요청 바람니다. 040----</td></tr>';
			$('#detailHead').html(htmlHead);
			
		}
	});
};





</script>