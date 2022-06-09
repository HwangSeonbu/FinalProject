package kr.or.ddit.score.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.ast.service.AccessPeriodService;
import kr.or.ddit.enumpkg.AccessActionEnum;
import kr.or.ddit.vo.DepartmentVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PageAccessVO;
import kr.or.ddit.vo.security.MemberVOWrapper;

/**
 * 성적이의신청현황을 조회, 처리
 * <p>학생의 성적이의신청 프로세스 겸함
 * @author 김재웅
 * @since 2022. 4. 28.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 4. 28.      김재웅       최초작성
 * 2022. 5. 2.		 김재웅 	 학생 부분 추가
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Controller
@RequestMapping("/ojtScore")
public class ScoreObjectionRetrieveController {

	@Inject
	AccessPeriodService accessService;
	
	/**
	 * 학생이 성적이의 신청을 할수 있는 양식 이동
	 * @return
	 */
	@RequestMapping("objectionScoreForm.do")
	public String objectionScoreForm(Model model) {
		PageAccessVO accessVo = accessService.retrieveAccessPeriod(
				AccessActionEnum.APPLYCREDITOBJECTION);
		if(!accessVo.isAccess()) {
			model.addAttribute("action", accessVo.getAction());
			model.addAttribute("accessPeriod", accessVo.getAccessPeriod());
			return "temp/notPeriodPage";
		}	
			
		
		
		
		
		
		return "score/studentObjectionScoreForm";
	}
	
	/**
	 * 학생이 성적이의신청 결과를 확인할 수 있는 페이지 이동
	 * @return
	 */
	@RequestMapping("objectionScoreResultList.do")
	public String objectionScoreResultList(Model model) {
		PageAccessVO accessVo = accessService.retrieveAccessPeriod(
				AccessActionEnum.APPLYCREDITOBJECTION);
		if(!accessVo.isAccess()) {
			model.addAttribute("action", accessVo.getAction());
			model.addAttribute("accessPeriod", accessVo.getAccessPeriod());
			return "temp/notPeriodPage";
		}
		
		
		return "score/studentObjectionScoreList";
	}
	
	
	
	
}
