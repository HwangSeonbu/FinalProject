package kr.or.ddit.enr.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.enr.service.AdminEnrollService;
import kr.or.ddit.grade.service.GradeService;
import kr.or.ddit.vo.AdminEnrollVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SimpleSearchVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 학사관리자의 등록금부여 
 * @author 고성식
 * @since 2022. 5. 24.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 5. 24.    고성식   			   최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Slf4j
@Controller
@RequestMapping("/setEnroll")
public class EnrollSetController {

	@Inject
	AdminEnrollService service;
	
	@Inject
	private GradeService Gservice;
	
	@RequestMapping("enrollSetForm.do")
	public String enrollSetForm() {
		return "enr/adminSetEnroll";
	}
	
	@RequestMapping(value="enrollSetForm.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public PagingVO<AdminEnrollVO> setCreditEnrollStudentList(
		@RequestParam(value="page", required=false, defaultValue="1") int[] currentPage 
		, @ModelAttribute("simpleCondition") SimpleSearchVO simpleCondition
		, @ModelAttribute AdminEnrollVO detailCondition
		, Model model, Authentication authentication
	){
		log.info("listHandler 도착*/*************************************");
		PagingVO<AdminEnrollVO> paging = new PagingVO<>(15, 10);
		paging.setCurrentPage(currentPage[0]);
		paging.setSimpleCondition(simpleCondition);
		paging.setDetailCondition(detailCondition);

		
		service.retrieveStudentEnrollList(paging);
		
		model.addAttribute("paging", paging);
		
		return paging;
	}
	
	@RequestMapping(value="adminAppointEnroll.do", produces=MediaType.APPLICATION_JSON_VALUE)
	public String adminAppointEnroll(@RequestBody List<AdminEnrollVO> list  ,Model model) throws Exception {
		
		service.adminCreatEnroll(list);
		
		return "jsonView";
	}
	
	
}
