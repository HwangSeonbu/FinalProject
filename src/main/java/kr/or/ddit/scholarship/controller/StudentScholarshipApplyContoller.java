package kr.or.ddit.scholarship.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import kr.or.ddit.scholarship.service.StudentScholarshipService;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.ScholarshipVO;
import kr.or.ddit.vo.SemsVO;
import kr.or.ddit.vo.security.MemberVOWrapper;
import lombok.extern.slf4j.Slf4j;
/**
 * 
 * @author 민진홍
 * @since 2022. 5. 16.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 5. 16.      민진홍	장학금신청
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Slf4j
@Controller
@RequestMapping("/applyScholar")
public class StudentScholarshipApplyContoller {

	@Inject
	private StudentScholarshipService service;
	
	/**
	 * 장학금 신청페이지 조회
	 * @return
	 */
	@RequestMapping("scholarApplyForm.do")
	public String scholarApplyForm(Model model,Authentication authentication) {
			MemberVO authMember
			 = ((MemberVOWrapper)authentication.getPrincipal()).getRealUser();
			int userNo = authMember.getUserNo();
			SemsVO semsVo = (SemsVO) RequestContextHolder.getRequestAttributes()
                    .getAttribute("semsVo", RequestAttributes.SCOPE_SESSION);
			
			List<ScholarshipVO> schList = service.studentScholarshipApplyList();
			Map<String,Object> info = new HashMap<>();
			info.put("userNo", userNo);
			info.put("nextSemes",semsVo.getNextSems());
			List<String> aleardList = service.studentScholarshipAleardyApply(info);
			List<Map<String,Object>> reqList = service.studentScholarshipReqApplyList(info);
			model.addAttribute("schList", schList);
			model.addAttribute("aleardList",aleardList);
			model.addAttribute("reqList",reqList);
			
		return "scholarship/studentScholarRequestForm";
	}
	
	/**
	 * 장학금 신청버튼
	 * @param schNo
	 * @param session
	 * @param authentication
	 * @return
	 */
	@PostMapping("studentScholarApply")
	@ResponseBody
	public int studentScholarApply(@RequestParam("schNo") String schNo, Authentication authentication) {
		MemberVO authMember
		 = ((MemberVOWrapper)authentication.getPrincipal()).getRealUser();
		SemsVO semsVo = (SemsVO) RequestContextHolder.getRequestAttributes()
                .getAttribute("semsVo", RequestAttributes.SCOPE_SESSION);
		int userNo = authMember.getUserNo();
		Map<String,Object> info = new HashMap<>();
		info.put("schNo", schNo);
		info.put("userNo", userNo);
		info.put("nextSemes",semsVo.getNextSems());
		return service.studentScholarshipApply(info);
	}
	
	@PostMapping("cancelStudentScholarshipApply")
	@ResponseBody
	public int cancelStudentScholarshipApply(@RequestParam("reqId") String reqId) {
		return service.cancelStudentScholarshipApply(reqId);
		
	}
	
	
	
	
}
