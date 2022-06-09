package kr.or.ddit.scholarship.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.grade.service.GradeService;
import kr.or.ddit.scholarship.service.AdminScholarshipService;
import kr.or.ddit.vo.AdminScholarshipVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SimpleSearchVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 장학금종류 조회, 장학생현황 조회
 * @author 김재웅
 * @since 2022. 4. 28.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 4. 28.   김재웅 		      최초작성
 * 2022. 5. 09.	    고성식 		      성적우수장학생 선발을 위한 성적우수자 조회		
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Controller
@RequestMapping("/scholar")
@Slf4j
public class AdminScholarshipRetrieveController {
	
	@Inject
	private AdminScholarshipService service;
	
	@Inject
	private GradeService Gservice;
	
	
	/**
	 * 장학선정 페이지로 mapping
	 * @return
	 */
	@RequestMapping("scholarBoard.do")
	public String scholarKindList() {
		
		return "scholarship/adminScholarBoard";
	}
	
	/**
	 * 학사관리자의 장학생 현황 조회
	 * @return
	 */
	@RequestMapping("scholarStudentList.do")
	public String scholarStudentList(Model model) {
		// 수강학기 리스트 조회( 성적입력 코드 활용 > 해당 로직 변경 시 확인 필요 )
		List<String> semsdata = Gservice.retrieveSemsdata();
		model.addAttribute("semsdata", semsdata);
		
		return "scholarship/adminScholarStudentList";
	}
	
	@RequestMapping(value="/totalScholarStudentList.do")
	public String totalScholarStudentList(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage 
			, @RequestParam(value="semsNo", required=false) String semsNo
			, @ModelAttribute("simpleCondition") SimpleSearchVO simpleCondition
			, Model model	
	) {
		log.info("totalScholarStudentList 도착**************************************");
		PagingVO<AdminScholarshipVO> paging = new PagingVO<>(10, 10);
		paging.setCurrentPage(currentPage);
		paging.setSimpleCondition(simpleCondition);
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("paging", paging);
		param.put("semsNo", semsNo);
		
		int totalCount = service.retrieveTotalScholarshipListCount(param);
		paging.setTotalRecord(totalCount);
		
		List<AdminScholarshipVO> list = service.retrieveTotalScholarshipList(param);
		
		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		
		return "jsonView";
	}
	
}
