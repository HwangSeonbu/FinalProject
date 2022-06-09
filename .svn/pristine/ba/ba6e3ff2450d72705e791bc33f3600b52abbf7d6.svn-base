package kr.or.ddit.graduate.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import kr.or.ddit.graduate.service.GraduateService;
import kr.or.ddit.vo.GraduateVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SimpleSearchVO;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 고성식
 * @since 2022. 5. 27.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 5. 27.   고성식	 	     최초작성 
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Controller
@RequestMapping("/targetGraduate")
@Slf4j
public class GraduateTargetProcessController {

	@Inject
	private GraduateService service;
	
	@RequestMapping("graduateTargetList.do")
	public String graduateTargetList() {
		
		return "graduate/adminGraduateTargetList";
	}
	
	@RequestMapping(value="graduateTargetList.do" , produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
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
		
		service.retreiveTargetStudentList(paging);
		
		model.addAttribute("paging", paging);
		
		return paging;
	}
	
	@RequestMapping(value="/modifyAdminGraduateTarget.do", produces=MediaType.APPLICATION_JSON_VALUE)
	public String modifyAdminGraduateTarget(@RequestBody List<GraduateVO> list) throws Exception{
		
		service.updateGraduateSts(list);
	
		return "jsonView";
	}
}
