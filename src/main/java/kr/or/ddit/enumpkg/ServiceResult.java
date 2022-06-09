package kr.or.ddit.enumpkg;

/**
 * @author 이유정
 * @since 2022. 4. 27.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 4. 27.      이유정       최초작성
 * 2022.05.18		민진홍		수강신청중복수정
 * 2022.05.26       김재웅			추천비추천처리결과 추가
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
public enum ServiceResult {
	OK, INVALIDPASSWORD, NOTEXIST, FAIL, PKDUPLICATED, VALIDATEFAIL, CREDITOVER
	
	/*게시물 추천 비추천 처리결과*/
	
	, NEWLIKE, NEWDISLIKE, ALREADYLIKE, ALREDYDISLIKE, CHANGETOLIKE, CHANGETODISLIKE
	
	
	
}
