package kr.or.ddit.counsel.controller;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.counsel.dao.CounselDAO;
import kr.or.ddit.counsel.service.CounselService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.CounselVO;
import kr.or.ddit.vo.DepartmentVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProfessorVO;
import kr.or.ddit.vo.QuestionAnswerVO;
import kr.or.ddit.vo.QuestionVO;
import kr.or.ddit.vo.SCounselVO;
import kr.or.ddit.vo.SimpleSearchVO;
import kr.or.ddit.vo.security.MemberVOWrapper;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 황선부
 * @since 2022. 4. 28.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                     수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 4. 28.   황선부       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Slf4j
@Controller
@RequestMapping("/reqCounsel")
public class ReqCounselController {
	@Inject
	private CounselDAO dao;
	@Inject
	private CounselService service;
	
	@RequestMapping("view")
	public String listHandler(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage 
			, @ModelAttribute("simpleCondition") SimpleSearchVO simpleCondition
			, Model model , Authentication authentication
	){
		log.info("********************{}",currentPage);
		SCounselVO scounselVO = new SCounselVO();
		

		
		String userNo1
		 = ((MemberVOWrapper)authentication.getPrincipal()).getUsername();
		
		int stuNo = Integer.parseInt(userNo1);
		
		
		PagingVO<SCounselVO> paging = new PagingVO<>(5, 8);
		paging.setStuNo(stuNo);
		paging.setCurrentPage(currentPage);
		paging.setSimpleCondition(simpleCondition);
		QuestionVO questionVO = new QuestionVO();
		
		List<QuestionVO> qlist= service.listQuestion();
		service.retreiveCounselStu(paging);
		
		model.addAttribute("paging", paging);
		log.info("~~~~~~~~~~~~~~~~~{}",paging);
		
		model.addAttribute("qlist", qlist);
		
		//departList 불러오기
		List<DepartmentVO> departList = dao.selectdepartment();
		model.addAttribute("departList", departList);
		return "counsel/reqCounsel";
	}
	
	@RequestMapping(value="getProList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<ProfessorVO> proList(
			@RequestParam String deptId
			){
		List<ProfessorVO> proList = dao.selectProfessor(deptId);
		
		return proList;
		
	}

	@PostMapping("register")
	public String register(
			CounselVO counsel
			,Model model
			,RedirectAttributes redirectAttributes
			,Authentication authentication
			) throws ServletException, IOException {
		
		String userNo1
		 = ((MemberVOWrapper)authentication.getPrincipal()).getUsername();
		
		int stuNo = Integer.parseInt(userNo1);
		
		counsel.setStuNo(stuNo);
		
		String viewName= null;
		List<QuestionAnswerVO> qaList = null;		
		ServiceResult result = service.createCounsel(counsel);		
		String message=null;
		if(result.equals(ServiceResult.FAIL)) {
			message="서버 오류, 잠시 후 다시 시도하세요.";
			redirectAttributes.addFlashAttribute("message", "서버 오류, 잠시 후 다시 시도하세요.");
		}else {
			message="상담접수가 완료되었습니다.";
			redirectAttributes.addFlashAttribute("message", "상담접수가 완료되었습니다.");
		}
		return "redirect:view";		
	}
	
	

}

