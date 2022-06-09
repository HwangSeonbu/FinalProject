package kr.or.ddit.graduate.controller;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.graduate.service.GraduateService;
import kr.or.ddit.vo.GraduateVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SimpleSearchVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 졸업학생 리스트 조회 컨트롤러
 * @author 고성식
 * @since 2022. 5. 11.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 5. 11.   고성식		       최초작성 
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Controller
@RequestMapping("/graduate")
@Slf4j
public class GraduateStudentRetrieveController {
	
	@Inject
	private GraduateService service;
	
	@RequestMapping("graduateStudentList.do")
	public String graduateStudentList() {
		
		return "graduate/adminGraduateStudentList";
	}
	
	@RequestMapping(value="graduateStudentList.do" , produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public PagingVO<GraduateVO> listHandler(
			@RequestParam(value="page", required=false, defaultValue="1") int[] currentPage
			, @ModelAttribute("simpleCondition") SimpleSearchVO simpleCondition
			, @ModelAttribute GraduateVO detailCondition
			, Model model
	){
		log.info("listHandler 도착----------------");
		PagingVO<GraduateVO> paging = new PagingVO<>(15, 10);
		paging.setCurrentPage(currentPage[0]);
		paging.setSimpleCondition(simpleCondition);
		paging.setDetailCondition(detailCondition);
		
		service.retrieveStudentList(paging);
		
		model.addAttribute("paging", paging);
		
		return paging;
	}
	
}
