/**
 * <pre>
 * 
 * </pre>
 * @author 작성자명
 * @since 2022. 5. 21.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2022. 5. 21.      작성자명       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */ 

/* 테이블 행 클릭시 엑셀 ROW 셀렉트와 비슷한 효과 주기 */
$(document).on("click", ".tr-targetLine", function(){
	$(".tr-targetLine").addClass("tr-targetHover");
	if($(this).hasClass("tr-pickTarget")){
		$(this).addClass("tr-targetHover");
		$(this).removeClass("tr-pickTarget");
	}else{
		$(".tr-targetLine").removeClass("tr-pickTarget");
		$(this).addClass("tr-pickTarget");
		$(this).removeClass("tr-targetHover");
	}
});

/* 테이블 행 td 맨 앞의 체크박스 전체선택 효과 주기 */
$(document).on("click", "#checkAll", function(){
	if($("#checkAll").is(":checked")){
		$(".checkEach").prop("checked", true);
	}else{
		$(".checkEach").prop("checked", false);
	}
});
$(document).on("click", ".checkEach", function(){
	if($("input[class=checkEach]:checked").length == $(".checkEach").length){
		$("#checkAll").prop("checked", true);
	}else{
		$("#checkAll").prop("checked", false);
	}
});


/* a태그 클릭시 스크롤 방지 */
function autoScrollBlock(event) {
	event.preventDefault();
};


/* 검색결과 없음 커스텀 태그조각
var customDataCode = "<div class='div-imageWrap' id='imageWrap'><div class='div-imageInnerWrap'>";
customDataCode += "<img alt='' src='${cPath }/resources/img/noData.jpg' style='width:100%;'/></div>";
customDataCode += "<div class='div-textInnerImage'> 조건에 맞는 데이터가<br>없습니다.</div></div>";
*/


