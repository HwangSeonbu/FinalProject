<%--
교수가 학생이 신청한 성적이의신청 조회 하는 jsp
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 4. 27.      주창규      최초작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js">
	
</script>
<pre>
1.  년도와 학기 과목을 조회한다(비동기방식)
2.  제목을 클릭하여 학생이 신청한 이의신청서를 조회한다.
</pre>
<button onclick="location.href='${pageContext.request.contextPath}/ojt/professorObjectionDetail'">조회</button>
