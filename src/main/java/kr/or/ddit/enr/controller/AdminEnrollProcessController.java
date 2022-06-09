package kr.or.ddit.enr.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.enr.service.AdminEnrollService;
import kr.or.ddit.grade.service.GradeService;
import kr.or.ddit.vo.AdminEnrollVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SimpleSearchVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 학사관리자의 등록금 납부 확인 
 * @author 고성식
 * @since 2022. 5. 25.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 5. 25.   고성식		      최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Controller
@RequestMapping("/adminEnroll")
@Slf4j
public class AdminEnrollProcessController {
	
	@Inject
	AdminEnrollService service;

	@Inject
	private GradeService Gservice;
	
	@RequestMapping("enrollStandardForm.do")
	public String enrollStandardForm() {
	
		return "enr/adminEnrollStandard";
	}
	
	//학사관리자의 등록금 납부 확인페이지 Mapping
	@RequestMapping("enrollPayList.do")
	public String enrollPayList(Model model) {
		List<String> semsdata = Gservice.retrieveSemsdata();
		model.addAttribute("semsdata", semsdata);
		
		return "enr/adminErollPayList";
	}
	
	@RequestMapping(value="/setFinalCreditEnrollList.do")
	public String setFinalCreditEnrollList(
		@RequestParam(value="page", required=false, defaultValue="1") int currentPage 
		, @RequestParam(value="enrSems", required=false) String enrSems
		, @ModelAttribute("simpleCondition") SimpleSearchVO simpleCondition
		, Model model	
	) {
		log.info("listHandler 도착*/*************************************");
		PagingVO<AdminEnrollVO> paging = new PagingVO<>(15, 10);
		paging.setCurrentPage(currentPage);
		paging.setSimpleCondition(simpleCondition);

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("paging", paging);
		param.put("enrSems", enrSems);
		
		int totalCount = service.retrieveFinalEnrollListTotalCount(param);
		paging.setTotalRecord(totalCount);
		
		List<AdminEnrollVO> list = service.retrieveFinalEnrollList(param);
		
		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		
		return "jsonView";
		
	}
	
	
	@RequestMapping(value="/adminAppointFinalEnroll.do", produces=MediaType.APPLICATION_JSON_VALUE)
	public String adminAppointFinalEnroll(@RequestBody List<AdminEnrollVO> list  ,Model model) throws Exception {
		
		service.adminFinalCreatEnroll(list);
		
		return "jsonView";
	}
}
