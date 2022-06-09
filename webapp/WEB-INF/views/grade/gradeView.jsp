<%--
교수가  성적을 입력하는 jsp
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 4. 27.      작성자명      최초작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="${cPath }/resources/js/tablesorter-master/dist/css/theme.default.min.css">
<script type="text/javascript" src="${cPath}/resources/js/tablesorter-master/dist/js/jquery.tablesorter.js"></script>
<script type="text/javascript" src="${cPath}/resources/js/tablesorter-master/dist/js/jquery.tablesorter.widgets.js"></script>
<style>
	#titleTable {
	    height: auto;
	}
	#myTable{
		width: 95%;
		margin-top: 20px;
		vertical-align : middle;
	}
	#myTable th {
 	  text-align: center; 
	}
	#myTable td{
		height: 60px;
	}
	.rateInfo{font-weight: bold;}
	.rateNumber{color : red;}
</style>

<h3 class="h3-title">시험성적 조회 및 입력</h3><hr class="hr-title">

<select class="form-select" style="width:150px; display: inline-block;" name="semsdataSelection" id="semsdataSelectionId">
   <option>학기를 선택하세요</option>
</select>
<select class="form-select" style="width:300px; display: inline-block;" name="lectureSelection" id="lectureSelectionId">
   <option>강의를 선택하세요</option>
</select>
<span class="rateInfo">&nbsp;&nbsp;&nbsp;&nbsp; * 중간고사 : <span class="rateNumber">30</span>% 
	| 기말고사 : <span class="rateNumber">40</span>% 
	| 과제 : <span class="rateNumber">20</span>% 
	| 출결 : <span class="rateNumber">10</span>% </span>
<!-- <div class="container mt-3"> -->
<!--   <table id="myTable" class="table"> -->
  <table class="table table-bordered" id="myTable">
    <thead>
      <tr>
        <th>NO</th>
        <th>강의명</th>
        <th>학번</th>
        <th>성명</th>
        <th>학년</th>
        <th>중간고사(비율점수)</th>
        <th>기말고사(비율점수)</th>
        <th>과제성적(비율점수)</th>
        <th>출결성적(비율점수)</th>
        <th>최종점수</th>
        <th>등수</th>
        <th>최종학점</th>
      </tr>
    </thead>
    <tbody id ="gradeBody">
    </tbody>
  </table>   
<!-- </div> -->
<div id="extraArea"></div>
<script>
let semsdataSelection = $("#semsdataSelectionId");
let lectureSelection = $("#lectureSelectionId");
let gradeBody = $("#gradeBody");
let lecSems = "";
let lecId = "";
let clsMexam = "";
let userNo = "";
let sjtId = "";

// 년도와 학기를 조회
function startAjax(){
   $.ajax({
      url :"${cPath}/studentGrade/semsdataForm",
      method : "get",
      dataType : "json",
      success : function(resp) {
         semsdataSelection.empty();
         let semsdataList = resp.semsdata;
         
         let options = [];
         options.push($("<option>").attr("value","")
               .attr("selected", false)
               .text(" 학기 선택").css("color", "blue"));
         
         $(semsdataList).each(function(index,semsdata ){
            let option = $("<option>").attr("value",semsdata)
                                .text(semsdata.substr(0,4)+"년"+semsdata.substr(5,6)+"학기");
            options.push(option);
               });
         
         semsdataSelection.append(options);
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
      lecture(semsdata); //년도 학기
   }
});

function lecture(semsdata){
   $.ajax({
      url :"${pageContext.request.contextPath}/studentGrade/selectLecture",
      method : "get",
      data : {semsdata:semsdata},
      dataType : "json",
      success : function(resp) {
         lectureSelection.empty();
         let lectureList = resp.professorLecture;
         let options = [];
         options.push($("<option>").attr("value","")
               .attr("selected", false)
               .text("과목 선택").css("color", "blue"));
         $(lectureList).each(function(index, lecture ){
            
            
            let option = $("<option>").attr("value",lecture.PLAN_SEMS)
                                .attr("class",lecture.LEC_ID)
                                .text(lecture.LEC_NAME);
            options.push(option);
               });
         
         lectureSelection.append(options);
      },
      error : function(jqXHR, textStatus, errorThrown) {
         console.log(jqXHR);
         console.log(textStatus);
         console.log(errorThrown);
      }
   });
};


lectureSelection.on("change",function(){
   lecSems = $("#semsdataSelectionId option:selected").val();
   lecId = $("#lectureSelectionId option:selected").attr("class");
   if(lecSems =="" || lecId == ""){
      alert("데이터가  존재하지 않습니다!");
   }else{
      studentGrade(lecSems, lecId); 
   }
});

 function studentGrade(lecSems, lecId){
   $.ajax({
      url :"${pageContext.request.contextPath}/studentGrade/selectStudentGrade",
      method : "get",
      data : {lecSems:lecSems, lecId:lecId},
      dataType : "json",
      success : function(resp) {
         gradeBody.empty();
         let gradeList = resp.studentGrade;
         let rateList = resp.lectureRate;
         let trTags = [];
         
         if(gradeList && gradeList.length > 0){
            
            $.each(gradeList, function(idx,grade){
               
               let trTag = $("<tr>").append(
                     $("<td>").html(grade.RNUM).addClass("text-center")
                     ,$("<td>").html(grade.LEC_NAME).addClass("text-center")
                     ,$("<td>").html(grade.USER_NO).addClass("text-center")
                     ,$("<td>").html(grade.USER_NAME).addClass("text-center")
                     ,$("<td>").html(grade.STU_YEAR).addClass("text-center")
                     ,$("<td>").html(grade.CLS_MEXAM +"("+(grade.CLS_MEXAM / 100 * rateList.MRATE).toFixed(2)+")")
                              .attr("class","clsMexam text-right")
                              .attr("data-id",grade.USER_NO)
                     ,$("<td>").html(grade.CLS_FEXAM +"("+(grade.CLS_FEXAM / 100 * rateList.FRATE).toFixed(2)+")")
                              .attr("class","clsFexam text-right")
                              .attr("data-id",grade.USER_NO)
                     ,$("<td>").html(grade.CLS_HWK +"("+(grade.CLS_HWK / 100 * rateList.HWRATE).toFixed(2)+")")
                             .attr("class","clsHwk text-right")
                             .attr("data-id",grade.USER_NO)
                      ,$("<td>").addClass("text-right").html(grade.CLS_ATTABS +"("+(grade.CLS_ATTABS / 100 * rateList.ATTRATE).toFixed(2)+")")
                     ,$("<td>").addClass("text-right").html(grade.CLS_PCT +"(" +(parseFloat(grade.CLS_RCT)).toFixed(2) + ")")
                     ,$("<td>").html(grade.RANK).addClass("text-center")
                     ,$("<td>").html(grade.CLS_CRDT).addClass("text-center")
                           

               );
            trTags.push(trTag);
            });
         
         }else{
            let trTag = $("<tr>").append($("<td>").attr("colspan", "11").text("검색자료가없습니다"));
            trTags.push(trTag);
         }
         gradeBody.append(trTags);
         
      },
      error : function(jqXHR, textStatus, errorThrown) {
         console.log(jqXHR);
         console.log(textStatus);
         console.log(errorThrown);
      }
   });
};

//중간고사 점수 입력
gradeBody.on("dblclick",".clsMexam",function(e){
   clsMexam  =$(this).text();
   clsMexam = clsMexam.split("(");
   clsMexam = clsMexam[0];
   $(this).empty();
   $(this).append($("<input>").attr("type","text").css("width", "100px")
                        .val(clsMexam)
                        .attr("onkeyup","enterkeyClsMexam($(this).val(),lecId,userNo,lecSems)"));
   lecId = $("#lectureSelectionId option:selected").attr("class");
   userNo = $(this).data("id");
});

function enterkeyClsMexam(clsMexam,lecId,userNo,lecSems){
   if (window.event.keyCode == 13) {
      if(clsMexam >= 101 ||clsMexam == "" || clsMexam < 0){
         alert("잘못입력하셨습니다.");
      }else{
         $.ajax({
            url : "${pageContext.request.contextPath}/studentGrade/dblclickClsMexam",
            method : "get",
            data : {clsMexam:clsMexam, lecId:lecId, userNo:userNo, lecSems:lecSems},
            dataType : "json",
            success : function(resp) {
               studentGrade(lecSems, lecId);
            },
            error : function(jqXHR, textStatus, errorThrown) {
               console.log(jqXHR);
               console.log(textStatus);
               console.log(errorThrown);
            }
         });
      }
   }
}
//기말고사점수입력
gradeBody.on("dblclick",".clsFexam",function(e){
   clsFexam  =$(this).text();
   clsFexam = clsFexam.split("(");
   clsFexam = clsFexam[0];
   $(this).empty();
   $(this).append($("<input>").attr("type","text").css("width", "100px")
                        .val(clsFexam)
                        .attr("onkeyup","enterkeyClsFexam($(this).val(),lecId,userNo,lecSems)"));
   lecId = $("#lectureSelectionId option:selected").attr("class");
   userNo = $(this).data("id");
});

function enterkeyClsFexam(clsFexam,lecId,userNo,lecSems){
   if (window.event.keyCode == 13) {
      if(clsFexam >= 101 || clsFexam < "" || clsFexam < 0 ){
         alert("잘못입력하셨습니다.");
      }else{
         $.ajax({
            url : "${pageContext.request.contextPath}/studentGrade/dblclickClsFexam",
            method : "get",
            data : {clsFexam:clsFexam, lecId:lecId, userNo:userNo, lecSems:lecSems },
            dataType : "json",
            success : function(resp) {
               studentGrade(lecSems, lecId);
            },
            error : function(jqXHR, textStatus, errorThrown) {
               console.log(jqXHR);
               console.log(textStatus);
               console.log(errorThrown);
            }
         });
      }
   }
}

// 과제점수입력
gradeBody.on("dblclick",".clsHwk",function(e){
   clsHwk  =$(this).text();
   clsHwk = clsHwk.split("(");
   clsHwk = clsHwk[0];
   $(this).empty();
   $(this).append($("<input>").attr("type","text").css("width", "100px")
                        .val(clsMexam)
                        .attr("onkeyup","enterkeyClsHwk($(this).val(),lecId,userNo,lecSems)"));
   lecId = $("#lectureSelectionId option:selected").attr("class");
   userNo = $(this).data("id");
});

function enterkeyClsHwk(clsHwk,lecId,userNo,lecSems){
   if (window.event.keyCode == 13) {
      if(clsHwk >= 101 ||  clsHwk == "" || clsHwk < 0){
         alert("잘못입력하셨습니다.");
      }else{
         $.ajax({
            url : "${pageContext.request.contextPath}/studentGrade/dblclickClsHwk",
            method : "get",
            data : {clsHwk:clsHwk, lecId:lecId, userNo:userNo, lecSems:lecSems },
            dataType : "json",
            success : function(resp) {
               studentGrade(lecSems, lecId);
            },
            error : function(jqXHR, textStatus, errorThrown) {
               console.log(jqXHR);
               console.log(textStatus);
               console.log(errorThrown);
            }
         });
      }
   }
}
// $("#myTable").tablesorter();
// $("#myTable").tablesorter({ sortList: [[0,0], [1,0]] });


 </script> 