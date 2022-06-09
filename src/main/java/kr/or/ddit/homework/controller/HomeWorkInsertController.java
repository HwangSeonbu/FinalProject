package kr.or.ddit.homework.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.homework.service.HomeworkService;
import kr.or.ddit.validate.HomeworkInsertGroup;
import kr.or.ddit.vo.ClassVO;
import kr.or.ddit.vo.EvaStandardVO;
import kr.or.ddit.vo.LecHomeworkVO;
import lombok.extern.slf4j.Slf4j;

/**과제 게시판 폼 조회 및 인서트 컨트롤러
 * @author 황선부
 * @since 2022. 5. 24.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 5. 24.      황선부       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Slf4j
@RequestMapping("/homework/new")
@Controller
public class HomeWorkInsertController {
	@Autowired
	private HomeworkService service;
	
	@GetMapping
	public String formView(
			Model model
			) {
		LecHomeworkVO homework = new LecHomeworkVO();
		model.addAttribute("homework", homework);
		return "homework/hwInsert";
	}
	
	@PostMapping("insert")
	public String insertHw(
			@Validated(HomeworkInsertGroup.class) @ModelAttribute("homework") LecHomeworkVO homework
			,BindingResult errors
			,HttpSession session
			,RedirectAttributes redirectAttributes
			,Model model
			) {
		log.info("11111111111111111111111111111111111");
		String viewName=null;
		if(!errors.hasErrors()) {
			ClassVO classVO = (ClassVO) session.getAttribute("classVO");
			homework.setLecId(classVO.getLecId());			
			log.info("homework========================>{}",homework);
			ServiceResult result=service.createHomework(homework);
			if(result.equals(ServiceResult.OK)) {
				viewName="redirect:../../homework";
			}else {
				redirectAttributes.addFlashAttribute("message", "서버 오류 입니다. 다시 실행해주세요.");
				viewName="homework/hwInsert";				
			}
		}else {
		log.info("222222222222222222222222222222222222");
			redirectAttributes.addFlashAttribute("homework", homework);

			viewName = "homework/hwInsert";
		}	
		return viewName;
	}
	
}
