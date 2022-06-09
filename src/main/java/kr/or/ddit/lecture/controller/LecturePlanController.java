package kr.or.ddit.lecture.controller;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.adminLecture.service.AdminLecturePlanService;
import kr.or.ddit.lecture.service.ProfessorLecturePlanService;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.PlanEditVO;
import kr.or.ddit.vo.PlanVO;
import kr.or.ddit.vo.SemsVO;
import kr.or.ddit.vo.SubjectVO;
import kr.or.ddit.vo.security.MemberVOWrapper;

/**
 * 교수의 강의계획 관리
 * <p>강의계획 작성 및 제출
 * <p>강의계획 신청현황 목록조회
 * @author 김재웅
 * @since 2022. 4. 28.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 4. 28.      작성자명       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Controller
@RequestMapping("/lecPlan")
public class LecturePlanController {
	
	@Inject
	ProfessorLecturePlanService service;
	@Inject
	AdminLecturePlanService adminService;
	
	/**
	 * 강의계획신청 리스트 기본 페이지 이동
	 * @return
	 */
	@RequestMapping("LecturePlanForm.do")
	public String LecturePlanForm() {
		
		return "lecture/professorLecPlanForm";
	}
	
	/**
	 * 강의계획신청 상세입력 페이지에서 검색조건에 맞는 과목 리스트 조회
	 * @return
	 */
	@RequestMapping("subjectListData.do")
	public String subjectListData(Model model, @ModelAttribute SubjectVO detailCondition
			,@RequestParam(value="page", required=false, defaultValue="1") int currentPage) {
		
		PagingVO<SubjectVO> pagingVo = new PagingVO<>(5, 5);
		pagingVo.setCurrentPage(currentPage);
		pagingVo.setDetailCondition(detailCondition);
		
		service.retrieveSubjectList(pagingVo);
		model.addAttribute("pagingVo", pagingVo);
		
		return "jsonView";
	}
	
	/**
	 * 작성중인 강의계획 제출
	 * @param planNo
	 * @return
	 */
	@RequestMapping("LecturePlanSubmit.do")
	public String LecturePlanSubmit(@RequestParam int planNo) {
		int result = 0;
		result = service.modifySubmitPlan(planNo);
		return "forward:/lecPlan/LecturePlanList.do";
	}
	
	/**
	 * 강의계획신청 상세 입력 양식 페이지 이동
	 * @return
	 */
	@RequestMapping("LecturePlanDetailForm.do")
	public String LecturePlanDetailForm(Model model, @RequestParam int planNo) {
		
		PlanEditVO planEditVo = service.retrieveLecturePlanEdit(planNo);
		model.addAttribute("planEditVo", planEditVo);
		
		return "lecture/professorLecPlanDetailForm";
	}
	
	// 진행중 - 서브밋 받는 post
	@PostMapping("LecturePlanSubmit.do")
	public String LecturePlanSubmit(@ModelAttribute PlanEditVO vo
				, @RequestParam Map<String, Object> wplanParamList) {
		
		String json = wplanParamList.get("wplanParamList").toString();
		String jsonReplace = json.replaceAll("&quot;", "\"");

		System.out.println("jsonReplace =================> "+ jsonReplace);
		
		ObjectMapper mapper = new ObjectMapper();
		List<Map<String, Object>> wPlanMapList = new ArrayList<>();
		try {
			wPlanMapList =
			mapper.readValue(jsonReplace, new TypeReference<ArrayList<Map<String, Object>>>() {});
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		vo.setWPlanMapList(wPlanMapList);
		
		service.modifyLecturePlan(vo);
		
		return "forward:/lecPlan/LecturePlanDetailForm.do";
	}
	
	/**
	 * 강의계획신청 리스트 기본 페이지의 데이터 뿌리기
	 * @return
	 */
	@RequestMapping("LecturePlanFormData.do")
	public String LecturePlanFormData(Model model, @ModelAttribute PlanVO vo
			, Authentication authentication, HttpSession session) {
		
		MemberVO loginProVo = ((MemberVOWrapper) authentication.getPrincipal()).getRealUser();
		
		SemsVO semsVo = (SemsVO) session.getAttribute("semsVo");
		
		vo.setPlanSems(semsVo.getNextSems());
		vo.setUserNo(loginProVo.getUserNo());
		
		List<PlanVO> planList = service.retrieveLecturePlanList(vo);
		model.addAttribute("planList", planList);
		
		return "jsonView";
	}
	
	/**
	 * 강의계획 추가
	 * @param model
	 * @return
	 */
	@RequestMapping("addPlan.do")
	public String addPlan(Model model, Authentication authentication, HttpSession session) {
		SemsVO semsVo = (SemsVO) session.getAttribute("semsVo");
		PlanVO vo = new PlanVO();
		MemberVO loginProVo = ((MemberVOWrapper) authentication.getPrincipal()).getRealUser();
		
		vo.setUserNo(loginProVo.getUserNo());
		vo.setDeptId(loginProVo.getUserDepartment());
		vo.setPlanSems(semsVo.getNextSems());
		
		int resCnt = service.createLecturePlan(vo);
		String result = "";
		if(resCnt > 0) {
			result = "성공";
		}else {
			result = "실패";
		}
		model.addAttribute("result", result);
		return "jsonView";
	}
	
	/**
	 * 강의계획 삭제
	 * @param model
	 * @param parameters : 삭제할 계획 번호 리스트
	 * @return
	 */
	@RequestMapping("deletePlan.do")
	public String deletePlan(Model model, @RequestParam Map<String, Object> parameters){
		String json = parameters.get("planNoList").toString();
		String jsonReplace = json.replaceAll("&quot;", "\"");
		ObjectMapper mapper = new ObjectMapper();
		List<Map<String, Object>> planNoList = new ArrayList<>();
		try {
			planNoList =
			mapper.readValue(jsonReplace, new TypeReference<ArrayList<Map<String, Object>>>() {});
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		int resCnt = service.removeLecturePlan(planNoList);
		
		if(resCnt == 0) {
			model.addAttribute("result", "실패");
		}else {
			model.addAttribute("result", resCnt);
		}
		return "jsonView";
	}
	
	/**
	 * 제출한 강의계획 현황을 조회하는 페이지 이동 + 동기 데이터 뿌리기
	 * @return
	 */
	@RequestMapping("LecturePlanList.do")
	public String LecturePlanList(Model model, Authentication authentication, HttpSession session) {
		SemsVO semsVo = (SemsVO) session.getAttribute("semsVo");
		MemberVO loginProVo = ((MemberVOWrapper) authentication.getPrincipal()).getRealUser();
		
		PlanVO vo = new PlanVO();
		
		vo.setPlanSems(semsVo.getNextSems());
		vo.setUserNo(loginProVo.getUserNo());
		List<PlanVO> submitPlanList = service.retrieveSubmitPlanList(vo);
		model.addAttribute("submitPlanList", submitPlanList);
		return "lecture/professorLecPlanList";
	}
	
	@RequestMapping("LectureRequestOneData.do")
	public String LectureRequestOneData(Model model, @RequestParam Integer planNo) {
		PlanEditVO submitPlanVo = adminService.retrieveSubmitPlanOne(planNo);
		model.addAttribute("submitPlanVo", submitPlanVo);
		return "jsonView";
	}
}













