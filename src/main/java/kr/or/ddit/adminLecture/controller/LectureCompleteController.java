package kr.or.ddit.adminLecture.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.adminLecture.service.AdminLectureCompService;
import kr.or.ddit.room.service.AdminRoomSetService;
import kr.or.ddit.set.service.PeriodService;
import kr.or.ddit.vo.GwanVO;
import kr.or.ddit.vo.LecCompTargetVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.PlanEditVO;
import kr.or.ddit.vo.SemsDataVO;
import kr.or.ddit.vo.SemsVO;
import kr.or.ddit.vo.SimpleSearchVO;

@Controller
@RequestMapping("/lecComp")
public class LectureCompleteController {

	@Inject
	AdminLectureCompService service;
	
	@Inject
	PeriodService periodService;
	
	@Inject
	AdminRoomSetService roomService;
	
	/**
	 * 개설완료된 강의만 보는 기본 페이지로 이동
	 * @return
	 */
	@RequestMapping("LectureCompleteList.do")
	public String LectureCompleteList(Model model, HttpSession session) {
		SemsVO semsVo = (SemsVO) session.getAttribute("semsVo");
		int prevSems = semsVo.getPrevSems();
		int thisSems = semsVo.getThisSems();
		int nextSems = semsVo.getNextSems();
		
		List<SemsDataVO> semsList = periodService.retrieveBaseSemsDataList();
		List<GwanVO> gwanList = roomService.retrieveGwanList();
		model.addAttribute("gwanList", gwanList);
		model.addAttribute("semsList", semsList);
		model.addAttribute("thisSems", thisSems);
		
		return "lecture/adminLecList";
	}
	
	/**
	 * 개설완료된 강의페이지에 데이터 뿌리기
	 * @return
	 */
	@RequestMapping("LectureCompleteListData.do")
	public String LectureCompleteListData(Model model, HttpSession session
			, @ModelAttribute LecCompTargetVO detailCondition
			, @ModelAttribute SimpleSearchVO simpleCondition
			, @RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, @RequestParam int targetSems) {
		PagingVO<LecCompTargetVO> pagingVo = new PagingVO<>(10, 5);
		pagingVo.setTargetSems(targetSems);
		pagingVo.setCurrentPage(currentPage);
		pagingVo.setDetailCondition(detailCondition);
		pagingVo.setSimpleCondition(simpleCondition);
		System.out.println("######################################");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>"+detailCondition.getPlanYear());
		service.retrieveLecCompList(pagingVo);
		
		model.addAttribute("pagingVo", pagingVo);
		
		return "jsonView";
	}
	
	
	
	/**
	 * 승인강의 대상으로 배정을 기다렸다가 강의를 개설하는 관리자 페이지로 이동
	 * @return
	 */
	@RequestMapping("LectureCompleteForm.do")
	public String LectureCompleteForm() {
		return "lecture/adminLecRequestComplete";
	}
	
	@RequestMapping("LectureCompleteFormData.do")
	public String LectureCompleteFormData(Model model, HttpSession session
			, @ModelAttribute LecCompTargetVO detailCondition
			, @ModelAttribute SimpleSearchVO simpleCondition
			, @RequestParam(value="page", required=false, defaultValue="1") int currentPage) {
		PagingVO<LecCompTargetVO> pagingVo = new PagingVO<>(10, 5);
		SemsVO semsVo = (SemsVO) session.getAttribute("semsVo");
		Integer nextSems = semsVo.getNextSems();
		
		pagingVo.setTargetSems(nextSems);
		pagingVo.setCurrentPage(currentPage);
		pagingVo.setDetailCondition(detailCondition);
		pagingVo.setSimpleCondition(simpleCondition);
		
		service.retrieveLecCompTargetList(pagingVo);
		
		model.addAttribute("pagingVo", pagingVo);
		return "jsonView";
	}
	
	@RequestMapping("LectureEstablish.do")
	public String LectureEstablish(Model model, @RequestParam Map<String, Object> parameters) {
		int resCnt = service.modifyLecEstablish(parameters);
		
		return "jsonView";
	}
	
}













