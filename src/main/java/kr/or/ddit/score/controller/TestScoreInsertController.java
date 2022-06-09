package kr.or.ddit.score.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 교수의 중간고사/기말고사 성적입력을 위한 양식 조회, 입력정보 인서트
 * @author 김재웅
 * @since 2022. 4. 28.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 4. 28.      김재웅       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Controller
@RequestMapping("/testScore")
public class TestScoreInsertController {

	@RequestMapping("middleScoreForm.do")
	public String middleScoreForm() {
		
		return "score/professorMiddleScoreForm";
	}
	
	@RequestMapping("finalScoreForm.do")
	public String finalScoreForm() {
		
		return "score/professorFinalScoreForm";
	}
	
	
}
















