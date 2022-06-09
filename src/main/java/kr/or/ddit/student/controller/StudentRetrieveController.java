package kr.or.ddit.student.controller;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.student.service.StudentService;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SimpleSearchVO;
import kr.or.ddit.vo.StudentVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 학생 리스트를 불러오는 controller
 * @author 작성자명
 * @since 2022. 4. 26.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 4. 26.   고성식      		 최초작성  
 * 
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Controller
@RequestMapping("/student")
@Slf4j
public class StudentRetrieveController {
	private final StudentService service;
	public StudentRetrieveController(StudentService service) {
		super();
		this.service = service;
	}
	
	@RequestMapping("studentList.do")
	public String studentListView() {
		return "student/studentList";
	}
	
	@RequestMapping(value="studentList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public PagingVO<StudentVO> listHandler(
			@RequestParam(value="page", required=false, defaultValue="1") int[] currentPage 
			, @ModelAttribute("simpleCondition") SimpleSearchVO simpleCondition
			, @ModelAttribute StudentVO detailCondition
			, Model model	
	) {
		log.info("listHandler 도착*/*************************************");
		PagingVO<StudentVO> paging = new PagingVO<>(15, 10);
		paging.setCurrentPage(currentPage[0]);
		paging.setSimpleCondition(simpleCondition);
		paging.setDetailCondition(detailCondition);
		
		service.retrieveStudentList(paging);
		
		model.addAttribute("paging", paging);
		
		return paging;
	}
	
	@RequestMapping("studentView.do")
	public String view(@RequestParam("what") Integer userNo, Model model) {
		StudentVO student = service.retriveStudent(userNo);
		model.addAttribute("student", student);
		return "student/studentView";
	}
	
	@RequestMapping("studentForm.do")
	public String stuInsert() {
		return "student/studentForm";
	}
	
	
	@RequestMapping(value="studentInsert.do", method=RequestMethod.POST)
	public String readExcel(@RequestParam("file") MultipartFile file, Model model )
            throws IOException {
		
		service.studentUpload(file);
		
        return "jsonView";
    }
	
}
