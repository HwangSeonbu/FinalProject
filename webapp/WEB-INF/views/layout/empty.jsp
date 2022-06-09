<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<style>
</style>
<title>HERMES</title>
</head>
<body>
<div class="bg">
<!--   <video muted autoplay loop> -->
<%--     <source src="${pageContext.request.contextPath }/resources/video/mainBackground.mp4" type="video/mp4"> --%>
<!--   </video> -->
<!--   <div class="text"> -->
<!--   </div> -->
    <tiles:insertAttribute name="content"/>
</div>
	
</body>
</html>