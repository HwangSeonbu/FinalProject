<%--
	
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 4. 29.      김재웅      최초작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>
 <?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery.form.min.js"></script>
<h3 class="h3-title">분과대학 강의관 배분</h3><hr class="hr-title">

<style>
	.card{float: left;}
	.card-header{height: 80px;font-size: 20px; margin-bottom: 10px;}
	
	#mainLeftArea{
		width: 25%; height: 800px;
		margin-right: 15px;
		float: left;
/* 		border: 1px solid red; */
	}
	#mainRightArea{
		width: 60%; height: 800px;
		float: left;
	}
	#collegeContainer{
		width: 100%;
		height: 100%;
	}
	#gwanBodyInnerArea{
/*  		border: 1px solid red;  */
		height: 90%;
	}
	
	#assignContainer1{
		width: 30%;
		height:100%;
/*  		border: 1px solid blue;  */
		float: left;
	}
	#assignContainer2{
		width: 70%;
		height:100%;
/* 		border: 1px solid blue;  */
		float: left;
	}
	#departmentBody{
		width: 95%;
		height:95%;
		margin-top:10px;
		margin-left:10px;
/* 		border: 1px solid red; */
	}
	
	#gwanViewBody{
		width:65%;
		margin-top:10px;
		margin-left:10px;
	 	height:300px;
	}
	
	#buttonBody{
		text-align:center;
		width: 30%;
	 	height:200px;
	 	margin-top: 50px;
	 	margin-left: 15px;
		float: left;
	}
	
	input[type=text]{
		text-align:center;
		font-size:20px;
		width: 90%;
		height: 50px;
		margin-top: 15px;
	}
	
	#assignBtn{margin-top: 15px; margin-right: 20px;}
	#resetBtn{margin-top: 15px;}
	
	#gwanDetailBody{
		width: 95%;
	 	height: 360px;
	 	margin-top: 10px;
	 	margin-left:10px;
	 	clear: both;
	}
	
	.detail {
/* 		border: 1px solid red; */
	}
	
	.collegeClick{
		font-weight:bold;
	}
	.gwanClick:hover{
		cursor: pointer;
		font-weight:bold;
		color: blue;
	}
	
	.collegeClick:hover{
		cursor: pointer;
		color: blue;
	}
	
	
	
	
</style>

<div id="mainLeftArea">
	<div class="card border-info" id="collegeContainer">
		<div class="card-header"><span class="collegeClick">분과대학명</span> [배분강의관]</div>
		<div class="card-body"  id="collegeBody"></div>
	</div>
</div>


<div class="card border-info" id="mainRightArea">
	<div class="card-header"><span id="pickCollege">분과대학을 선택해주세요.</span></div>
	<div id="gwanBodyInnerArea">
	
		<div id="assignContainer1">
			<div class="detail card border-info" id="departmentBody">
				<div class="card-header">소속학과</div>
				<div class="card-body" id="departmentCard">
					<p>분과대학을 선택해주세요.</p>
				</div>
			</div>
		</div>
		
		<div id="assignContainer2">
			<div class="detail card border-info" id="gwanViewBody">
				<div class="card-header">강의관 현황</div>
				<div class="card-body" id="gwanViewCard"></div>
			</div>
			<div class="detail" id="buttonBody">
				<form id="assignForm">
					<input type="text" name="colName" readonly/>
					<input type="hidden" name="colId" readonly />
					<input type="text" name="gwanName" readonly/>
					<input type="button" class="btn btn-primary" value="배분" id="assignBtn"/>
					<input type="reset" class="btn btn-secondary" value="취소" id="resetBtn" />
				</form>
			</div>
			<div class="detail card border-info" id="gwanDetailBody">
				<div class="card-header">강의관 세부정보</div>
				<div class="card-body" id="gwanDetailCard"></div>
			</div>
		</div>
	
	
	</div>
</div>

<script>
let collegeBody = $("#collegeBody");
let gwanViewCard = $("#gwanViewCard");
let departmentCard = $("#departmentCard");
let pickGwan = $("#pickGwan");

let inputColName = $("input[name=colName]");
let inputGwanName = $("input[name=gwanName]");
let inputColId = $("input[name=colId]");

$.ajax({
	url : "${pageContext.request.contextPath}/roomSet/gwanDataList.do",
	method : "get",
	dataType : "json",
	success : function(resp) {
		//departmentCard.empty();
		let gwanList = resp.gwanList;
		
		let spanTags = [];
		if(gwanList && gwanList.length > 0){
			var cnt = 0;
			$.each(gwanList, function(idx, gwan){
				cnt++;
// 				let viewURL = VIEWURLPTRN.replace("%V", prod.prodId);
				let spanTag = $("<span>").html(gwan.gwanName+"&nbsp;&nbsp;")
										 .attr("class", "gwanClick");
				spanTags.push(spanTag);
				if(cnt == 4){
					cnt = 0;
					spanTags.push($("<br>"));
				}
			});
			spanTags.push($("<br>"));
			let spanTag = $("<span>").html("초기화"+"&nbsp;&nbsp;")
			 		.attr("class", "gwanClick")
					.css("color", "red");
			spanTags.push(spanTag);
		}else{
		} // if end
		gwanViewCard.append(spanTags);
	},
	error : function(jqXHR, textStatus, errorThrown) {
		console.log(jqXHR);
		console.log(textStatus);
		console.log(errorThrown);
	}
});


function collegeAjax(){
	$.ajax({
		url : "${pageContext.request.contextPath}/roomSet/collegeDataForm.do",
		method : "get",
		dataType : "json",
		success : function(resp) {
			collegeBody.empty();
			let collegeList = resp.collegeList;
			
			let pTags = [];
			if(collegeList && collegeList.length > 0){
				$.each(collegeList, function(idx, college){
	// 				let viewURL = VIEWURLPTRN.replace("%V", prod.prodId);
					let pTag = $("<p>").append($("<span>").text(college.colName)
														  .addClass("collegeClick")
														  .attr("id", college.colId)
														  .data("col-gwan", college.colGwan));
					pTag.append($("<span>").text("  ["+college.colGwan+"]"));
	// 				pTag.append($("<select>").append("<option>강의관1</option>")
										
	// 				);
					pTags.push(pTag);
				});
			}else{
			} // if end
			collegeBody.append(pTags);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR);
			console.log(textStatus);
			console.log(errorThrown);
		}
	});
};
collegeAjax();

let pickCollege = $("#pickCollege");


$(document).on("click", ".collegeClick", function(){
	let colId = $(this).attr("id");
	let colGwan = $(this).data("colGwan");
	let colName = $(this).text();
	let code = "";
	pickCollege.empty();
	
	code += colName;
	code += "<span style='font-size:15px;'>  현재 배정 강의관 : [ ";
	code += colGwan;
	code += " ]</span>";
	pickCollege.html(code);
// 	pickCollege.text(colName+" 현재 배정 강의관 : "+colGwan);
	inputColName.val(colName);
	inputColId.val(colId);
	
	$.ajax({
		url : "${pageContext.request.contextPath}/roomSet/collegeDepartDataList.do",
		method : "get",
		data : {colName:colName},
		dataType : "json",
		success : function(resp) {
			departmentCard.empty();
			let departList = resp.departList;
			
			let pTags = [];
			if(departList && departList.length > 0){
				$.each(departList, function(idx, depart){
//	 				let viewURL = VIEWURLPTRN.replace("%V", prod.prodId);
					let pTag = $("<p>").text(depart.deptName)
									   .attr("id", depart.deptId)
					pTags.push(pTag);
				});
			}else{
				let pTag = $("<p>").text("소속 학과가 없습니다.");
				pTags.push(pTag);
			} // if end
			departmentCard.append(pTags);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR);
			console.log(textStatus);
			console.log(errorThrown);
		}
	});
	
});


$(document).on("click", ".gwanClick", function(){
	let gwanName = $(this).text();
	pickGwan.text(gwanName);
	inputGwanName.val(gwanName);
});



$("#assignBtn").on("click", function(event){
	let colName = inputColName.val().trim();
	let gwanName = inputGwanName.val().trim();
	let colId = inputColId.val().trim();
	
	if(colName=="") 
		alert("분과대학을 선택하세요.");
	
	if(gwanName=="")
		alert("강의관을 선택하세요");
	
	if(colName!="" && gwanName!=""){
		confirm(colName+"/"+colId);
		
		$.ajax({
			url : "${pageContext.request.contextPath}/roomSet/collegeGwanSet.do",
			method : "get",
			data : {colId:colId,
					ColGwan:gwanName},
			dataType : "json",
			success : function(resp) {
				alert(resp.result);
				collegeAjax();
				$("div").find("[id="+colId+"]").trigger("click");
// 				.trigger("click");
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR);
				console.log(textStatus);
				console.log(errorThrown);
			}
		});
	};
});

</script>













