package kr.or.ddit.score.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 교수의 강의별 성적처리기준을 입력,수정
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
@RequestMapping("/stdScore")
public class StandardScoreInsertController {

	@RequestMapping("StandardScoreForm.do")
	public String StandardScoreForm() {
		
		return "score/professorStandardScoreForm";
	}
}
