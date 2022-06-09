package kr.or.ddit.sugang.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.sugang.dao.LectureApplyViewDAO;
import kr.or.ddit.sugang.service.LectureApplyViewService;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PlanEditVO;
import kr.or.ddit.vo.SemsVO;
import kr.or.ddit.vo.security.MemberVOWrapper;
import lombok.extern.slf4j.Slf4j;
/**
 * 
 * @author 민진홍
 * @since 2022. 4. 29.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 4. 29.      작성자명       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Slf4j
@Controller
@RequestMapping("/lecture")
public class LectureApplyViewController {
	
	@Inject
	private LectureApplyViewService lecService;
	
	@Inject
	private LectureApplyViewDAO dao;
	

	/**
	 * 수강신청페이지로 감
	 * @return
	 */
	@GetMapping("view")
	public String lectureView() {
			return "sugang/lectureApplyView";
	}
	
	/**
	 * 수강신청과목 리스트를 조회하는 메서드
	 * @return
	 */
	@GetMapping(value="planList", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<Map<String, Object>> selectPlanList(
			String deptName, String sjtMajor, Integer sjtGrade, String sjtName,Authentication authentication	) {
//		학기
		SemsVO semsVo = (SemsVO) RequestContextHolder.getRequestAttributes()
				.getAttribute("semsVo", RequestAttributes.SCOPE_SESSION);
		MemberVO authMember = ((MemberVOWrapper)authentication.getPrincipal()).getRealUser();
		
		
		List<Map<String, Object>> listVO = lecService.selectPlanList(authMember.getUserNo(),deptName, sjtMajor, sjtGrade, sjtName, semsVo.getNextSems());
		return listVO;
	}
	
	/**
	 * 수강신청 버튼을 눌렀을때, 신청해주는 메서드
	 * @return
	 */
	@GetMapping("apply")
	public String lectureApply(@RequestParam("lecId") String lecId
							    , @RequestParam("lecSems") String lecSems
							    , @RequestParam("lecCredit") int lecCredit
								, Authentication authentication
								, Model model
								, RedirectAttributes redirectAttributes) {
		MemberVO authMember = ((MemberVOWrapper)authentication.getPrincipal()).getRealUser();
		
		ServiceResult rs = lecService.lectureApply(lecId,lecSems,authMember.getUserNo(),lecCredit);
		if(ServiceResult.FAIL.equals(rs)) {
			redirectAttributes.addFlashAttribute("lectureApplyMessage1", "신청실패. 인원이 가득찼어요 ㅠ");
			
			return "redirect:/lecture/view";
		}else if(ServiceResult.VALIDATEFAIL.equals(rs)) {
			
			redirectAttributes.addFlashAttribute("lectureApplyMessage1", "신청실패. 시간표가 중복되었어요ㅠ");
			return "redirect:/lecture/view";
		}else if(ServiceResult.CREDITOVER.equals(rs)) {
			redirectAttributes.addFlashAttribute("lectureApplyMessage1", "신청실패. 신청한 학점이 최대학점을 초과했어요!");
			return "redirect:/lecture/view";
		}
		redirectAttributes.addFlashAttribute("lectureApplyMessage2", "신청성공!");
		return "redirect:/lecture/view";
	}
	
	/**
	 * 신청 완료된 수강목록을 조회하는 메서드
	 * @param 
	 * @return
	 */
	@GetMapping(value="alreadyAppliedList", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<Map<String, Object>> alreadyAppliedList(Authentication authentication) {
//		학기
		SemsVO semsVo = (SemsVO) RequestContextHolder.getRequestAttributes()
				.getAttribute("semsVo", RequestAttributes.SCOPE_SESSION);
		MemberVO authMember = ((MemberVOWrapper)authentication.getPrincipal()).getRealUser();
		List<Map<String, Object>> listVO = lecService.alreadyAppliedList(authMember.getUserNo(),semsVo.getNextSems());
		return listVO;
	}
	
	@GetMapping(value="lectureScheduleList", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<Map<String, Object>> lectureScheduleList(Authentication authentication) {
//		학기
		SemsVO semsVo = (SemsVO) RequestContextHolder.getRequestAttributes()
				.getAttribute("semsVo", RequestAttributes.SCOPE_SESSION);
		MemberVO authMember = ((MemberVOWrapper)authentication.getPrincipal()).getRealUser();
		Map<String, Object> info = new HashMap<>();
		info.put("userNo", authMember.getUserNo());
		info.put("nextSems", semsVo.getNextSems());
		List<Map<String, Object>> scheduleList = lecService.studentApplyLectureScheduleList(info);
		return scheduleList;

		
	}
	
	
	/**
	 * 수강신청 철회를 위한 메서드
	 * @param lecId
	 * @param lecSems
	 * @return
	 */
	@GetMapping("cancel")
	public String lectureCancel(@RequestParam("lecId") String lecId
							    , @RequestParam("lecSems") String lecSems
								, Authentication authentication
								,RedirectAttributes redirectAttributes) {
		
		MemberVO authMember = ((MemberVOWrapper)authentication.getPrincipal()).getRealUser();
		int rs = lecService.lectureCancel(lecId,lecSems,authMember.getUserNo());
		redirectAttributes.addFlashAttribute("lectureApplyMessage3", "철회성공!");
		return "redirect:/lecture/view";
	}
	
	/**
	 * DEPT_NAME 리스트조회 (학과이름)
	 * @return
	 */
	@GetMapping(value="deptNameList", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<Map<String,String>> deptNameList(){
		return lecService.deptNameList();
	}
	
	/**
	 *  찜강 등록
	 * @param lecId
	 * @param lecSems
	 * @return
	 */
	@GetMapping("cartInsert")
	public String lectureCartInsert(@RequestParam("lecId") String lecId
							    , @RequestParam("lecSems") String lecSems
								, Authentication authentication) {
		MemberVO authMember = ((MemberVOWrapper)authentication.getPrincipal()).getRealUser();
		int rs = lecService.cartInsert(lecId,lecSems,authMember.getUserNo());
		return "redirect:/lecture/view";
	}
	
	/**
	 *  찜강 삭제
	 * @param lecId
	 * @param lecSems
	 * @return
	 */
	@GetMapping("cartDelete")
	public String lectureCartDelete(@RequestParam("lecId") String lecId
							    , @RequestParam("lecSems") String lecSems
								, Authentication authentication) {
		MemberVO authMember = ((MemberVOWrapper)authentication.getPrincipal()).getRealUser();
		int rs = lecService.cartDelete(lecId,lecSems,authMember.getUserNo());
		return "redirect:/lecture/view";
	}
	
	/**
	 * 모달창용 강의계획 하나 데이터 뿌리기
	 * @param model
	 * @param planNo
	 * @return
	 */
	@RequestMapping("lecturePlanView")
	public String lecturePlanView(Model model, @RequestParam Integer planNo) {
		PlanEditVO submitPlanVo = dao.lecturePlanView(planNo);
		model.addAttribute("submitPlanVo", submitPlanVo);
		return "jsonView";
	}
	
	
	
}
