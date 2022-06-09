package kr.or.ddit.ast.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.ast.dao.AssessmentDAO;
import kr.or.ddit.ast.service.AccessPeriodService;
import kr.or.ddit.ast.service.AssessmentService;
import kr.or.ddit.enumpkg.AccessActionEnum;
import kr.or.ddit.vo.AstLecVO;
import kr.or.ddit.vo.AstProVO;
import kr.or.ddit.vo.LectureVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PageAccessVO;
import kr.or.ddit.vo.ProfessorLectureVO;
import kr.or.ddit.vo.SemsVO;
import kr.or.ddit.vo.security.MemberVOWrapper;

/**
 * 학생의 교수, 강의평가를 처리하기 위한 컨트롤러
 * @author 김재웅
 * @since 2022. 5. 2.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 5. 2.      김재웅       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Controller
@RequestMapping("/astEnroll")
public class AssesmentEnrollProcessController {

	@Inject
	AccessPeriodService accessService;
	
	@Inject
	AssessmentService service;
	
	@Inject
	AssessmentDAO dao;
	
	
	/**
	 * 강의평가 기본페이지
	 * @param model
	 * @return
	 */
	@RequestMapping("LectureEnrollForm.do")
	public String LectureEnrollForm(Model model, HttpSession session
				, Authentication authentication) {
		PageAccessVO accessVo = accessService.retrieveAccessPeriod(
				AccessActionEnum.ENROLLASSESSMENT);
		if(accessVo.isAccess()) {
			MemberVO loginStuVo = ((MemberVOWrapper) authentication.getPrincipal()).getRealUser();
			int userNo = loginStuVo.getUserNo();
			SemsVO semsVo = (SemsVO) session.getAttribute("semsVo");
			int thisSems = semsVo.getThisSems();
			
			Map<String, Integer> paramMap = new HashMap<>();
			paramMap.put("userNo", userNo);
			paramMap.put("lecSems", thisSems);
			
			List<AstLecVO> lecList = service.retrieveAstTargetLecList(paramMap);
			model.addAttribute("lecList", lecList);
			
			return "ast/studentLectureAstForm";
		}else {
			model.addAttribute("action", accessVo.getAction());
			model.addAttribute("accessPeriod", accessVo.getAccessPeriod());
			return "temp/notPeriodPage";
		}
	}
	
	/**
	 * 강의평가 상세입력페이지
	 * @param model
	 * @param lecId
	 * @return
	 */
	@RequestMapping("lecAstDetailForm.do")
	public String lecAstDetailForm(Model model, @RequestParam String lecId) {
		LectureVO lectureVo = dao.selectAstTargetLecOne(lecId);
		model.addAttribute("lectureVo", lectureVo);
		
		return "ast/lecAstDetailForm";
	}
	
	@RequestMapping("lecAstSunmit.do")
	public String lecAstSunmit(@RequestParam String qList, @RequestParam String opnion
			, @RequestParam String lecId, HttpSession session
			, Authentication authentication) {
		MemberVO loginStuVo = ((MemberVOWrapper) authentication.getPrincipal()).getRealUser();
		int userNo = loginStuVo.getUserNo();
		SemsVO semsVo = (SemsVO) session.getAttribute("semsVo");
		int thisSems = semsVo.getThisSems();
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userNo", userNo);
		paramMap.put("thisSems", thisSems);
		paramMap.put("qList", qList);
		paramMap.put("opnion", opnion);
		paramMap.put("lecId", lecId);
		paramMap.put("flag", "강의");
		int resCnt = service.createAssessment(paramMap);
		
		return "forward:/astEnroll/LectureEnrollForm.do";
	}
	
	
	
	
	
	/**
	 * 교수평가 상세입력페이지
	 * @param model
	 * @param userNo
	 * @return
	 */
	@RequestMapping("proAstDetailForm.do")
	public String proAstDetailForm(Model model, @RequestParam int userNo
			, Authentication authentication, HttpSession session) {
		MemberVO loginStuVo = ((MemberVOWrapper) authentication.getPrincipal()).getRealUser();
		int stuNo = loginStuVo.getUserNo();
		SemsVO semsVo = (SemsVO) session.getAttribute("semsVo");
		int thisSems = semsVo.getThisSems();
		
		System.out.println("################################");
		System.out.println(userNo);
		
		Map<String, Integer> paramMap = new HashMap<>();
		paramMap.put("stuNo", stuNo);
		paramMap.put("proNo", userNo);
		paramMap.put("lecSems", thisSems);
		
		ProfessorLectureVO professorVo = dao.selectAstTargetProOne(paramMap);
		System.out.println(professorVo.toString());
		model.addAttribute("professorVo", professorVo);
		
		/*for(LectureVO vo : professorVo.getMylecList()) {
			System.out.println("################################");
			System.out.println(vo.toString());
		};*/
		//model.addAttribute("mylecList", professorVo.getMylecList());
		
		return "ast/proAstDetailForm";
	}
	
	@RequestMapping("proAstSunmit.do")
	public String proAstSunmit(@RequestParam String qList, @RequestParam String opnion
			, @RequestParam Integer proNo, HttpSession session
			, Authentication authentication) {
		MemberVO loginStuVo = ((MemberVOWrapper) authentication.getPrincipal()).getRealUser();
		int userNo = loginStuVo.getUserNo();
		SemsVO semsVo = (SemsVO) session.getAttribute("semsVo");
		int thisSems = semsVo.getThisSems();
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userNo", userNo);
		paramMap.put("thisSems", thisSems);
		paramMap.put("qList", qList);
		paramMap.put("opnion", opnion);
		paramMap.put("proNo", proNo);
		paramMap.put("flag", "교수");
		int resCnt = service.createAssessment(paramMap);
		
		return "forward:/astEnroll/ProfessorEnrollForm.do";
	}
	
	
	/**
	 * 교수평가 기본페이지
	 * @param model
	 * @return
	 */
	@RequestMapping("ProfessorEnrollForm.do")
	public String ProfessorEnrollForm(Model model, HttpSession session
				, Authentication authentication) {
		PageAccessVO accessVo = accessService.retrieveAccessPeriod(
				AccessActionEnum.ENROLLASSESSMENT);
		if(accessVo.isAccess()) {
			MemberVO loginStuVo = ((MemberVOWrapper) authentication.getPrincipal()).getRealUser();
			int userNo = loginStuVo.getUserNo();
			SemsVO semsVo = (SemsVO) session.getAttribute("semsVo");
			int thisSems = semsVo.getThisSems();
			
			Map<String, Integer> paramMap = new HashMap<>();
			paramMap.put("userNo", userNo);
			paramMap.put("lecSems", thisSems);
			
			List<AstProVO> proList = service.retrieveAstTargetProList(paramMap);
			model.addAttribute("proList", proList);
			
			return "ast/studentProfessorAstForm";
		}else {
			model.addAttribute("action", accessVo.getAction());
			model.addAttribute("accessPeriod", accessVo.getAccessPeriod());
			
			return "temp/notPeriodPage";
		}
	}
	
	
	
	
	
	
}
