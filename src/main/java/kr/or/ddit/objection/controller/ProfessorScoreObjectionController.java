package kr.or.ddit.objection.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 김재웅
 * @since 2022. 5. 11.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 5. 11.      김재웅       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Controller
@RequestMapping("/ojtView")
public class ProfessorScoreObjectionController {
	
	/**
	 * 교수가 확인하는 성적이의신청 현황
	 * @return
	 */
	@RequestMapping("objectionScoreList.do")
	public String objectionScoreList() {
		
		return "score/professorObjectionScoreList";
	}
}
