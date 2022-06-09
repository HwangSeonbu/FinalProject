<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<security:csrfMetaTags/>
	
	<script src="${pageContext.request.contextPath }/resources/js/jquery-3.6.0.min.js"></script>
    <!-- Icon Font Stylesheet -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

   <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
    
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
    <!-- Customized Bootstrap Stylesheet -->
   
	 <link href="${pageContext.request.contextPath }/resources/js/bootstrap-5.1.3-dist/css/bootstrap.css" rel="stylesheet">
    <!-- Template Stylesheet -->
    <link href="${pageContext.request.contextPath }/resources/css/style.css" rel="stylesheet">
	<!-- Hermes Only -->
    <link href="${cPath}/resources/css/hermes.css" rel="stylesheet">
    
    <style>
    	.ms-4{margin-left: 0.5rem !important;}
    
    	.table thead tr{
    		background: orange !important; color:black !important; font-weight: bold;
    	}
    
    
    	#sideMenuArea .nav-item .nav-link{
    		color: orange !important; font-size: 15px !important;
    	}
    /*
    	#sideMenuArea .nav-item .dropdown-menu{
    		background-color: #6c6e78 !important;
    	}
    */
    	#sideMenuArea .nav-item .dropdown-menu .dropdown-item{
    		color: white !important; font-size: 15px !important;
    	}
	   	#sideMenuArea .nav-item .dropdown-menu .dropdown-item:hover{
    		color: black !important; 
    	}
	   	
    	/*사이드 대덕인재개발원~ 사이드메뉴 바*/
    	
    	#hermes_leftTop_logo{background-color: #303138 !important; 
    		width: 100% !important; margin-right: 0px !important; 
    	}
    	
    	/*탑메뉴바 쭉*/
    	
    	#hermes_top_bar{background-color: #303138 !important;
    		margin-left:20px !important; min-width: 1200px !important; 
    		padding-top: 0px !important; padding-bottom: 0px !important; height: 60px !important;
    	}
    	
    	/*탑메뉴 바 중에 탑메뉴 텍스트 들어가는 네모*/
    
    	#hermes-top-menu{background-color: #303138 !important; 
    		margin-left:100px !important; 
    		padding: 0px !important; height: 100% !important;}
    		
    	.sidebar .navbar .dropdown-toggle::after{right: 18px !important;}
    	#hermes-top-menu a{font-size: 17px !important;}	
    	/*탑메뉴 바 중에 메세지함 들어가는 네모*/
    
    	#heremes-top-submenu{background-color: #303138 !important; 
    		margin-right: 150px !important; margin-top: 0.25rem !important;}
    	#heremes-top-submenu a{font-size: 17px !important; }
    	#heremes-top-submenu a span{color:white; }
    	
    	#subTopMenu{padding: 0px !important; height: 100% !important; margin-top: 0px !important;}
    	
    	#hermes_leftTop_logo{padding-left: 0px !important;}
    	/* 사이드 메뉴텍스트 들어가는 네모 제외하고 사이드 바 쭈욱*/
    
    	#hermes-left-bar{padding-right: 0px !important;
    		transition:0s; background-color: #303138 !important; width: 210px;}
    
    	.bi{margin-right: 10px !important;}
    	.dropdown-toggle{padding-left: 0px !important;}
    	
    	
    	.nav-topMenuText{height: 100% !important; margin-left: 0px !important; padding-right: 10px !important;
    		
    	}
    	.nav-topMenuText a{height: 100% !important; padding-top: 20px !important; width: 120px !important;
    		text-align: center !important; font-size: 20px;  color:orange !important;
    	}
    	
    	.nowTopMenu{background-color: white !important; color:#303138 !important; font-weight: bold !important;}
    
    </style>
	

	<style type="text/css">
	
	.nav-link{
		font-family: 'Noto Sans KR', sans-serif;
		font-size: 15px;
	}
	/* 이미지 흐림현상제거 */
	img{
	image-rendering: -webkit-optimize-contrast;
	transform: translateZ(0);
	backface-visibility: hidden;
	}
	
	
	</style>
	
<script type="text/javascript">
	const CONTEXTPATH = "${cPath}";
	<c:if test="${not empty message }">
		alert("${message}");
	</c:if>
	const overNoDataCode = "<div class='div-imageWrap' id='imageWrap'><div class='div-imageInnerWrap'><img alt='' src='${cPath }/resources/img/noData.jpg' style='width:100%;'/></div><div class='div-textInnerImage'> 조건에 맞는 결과가<br>없습니다.</div></div>";
	const overNeedDataCode = "<div class='div-imageWrap' id='imageWrap'><div class='div-imageInnerWrap'><img alt='' src='${cPath }/resources/img/noData.jpg' style='width:100%;'/></div><div class='div-textInnerImage'> 검색 조건을<br>선택하세요.</div></div>";
	
// 	ajax CSRF 설정
	let csrfMeta = $("meta[name='_csrf']");
	let commonHeaders = {}
	if(csrfMeta){
		commonHeaders['X-CSRF-TOKEN'] = csrfMeta.attr('content');
	}
	$.ajaxSetup({
		headers:commonHeaders
	});

	$( document ).ajaxError(function( event, request, settings ) {
	  	console.log(event);
	  	console.log(request);
	  	console.log(settings);
	});
	
</script>
