package kr.or.ddit.lecqna.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.lecqna.service.LecQnaService;
import kr.or.ddit.validate.InsertLecQnaGroup;
import kr.or.ddit.validate.UpdateLecBoardGroup;
import kr.or.ddit.vo.ClassVO;
import kr.or.ddit.vo.LecNoticeBoardVO;
import kr.or.ddit.vo.LecqnaVO;
import lombok.extern.slf4j.Slf4j;
/**강의 문의게시판 업데이트 컨트롤러
 * @author 황선부
 * @since 2022. 5. 21.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 5. 21.      황선부       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Slf4j
@RequestMapping("/lecBoard/qna/{bono}")
@Controller
public class LecQnaUpdateController {
	@Autowired
	private LecQnaService service;
	@GetMapping("form")
	public String updateForm(@PathVariable int bono
			,Model model) {
		LecqnaVO board = service.retrieveBoard(bono);
		model.addAttribute("board", board);
		log.info("board:{}",board);
		return "lecQna/lecQnaEdit";
	}
	
	
	@PostMapping
	public String updateProcess(
			@Validated(InsertLecQnaGroup.class) @ModelAttribute("board") LecqnaVO board
			,BindingResult errors
			,Model model
			,RedirectAttributes redirectAttributes
			,HttpSession session
			) {
		ClassVO classVO =  (ClassVO) session.getAttribute("classVO");
		
		log.info("classVO:{}",classVO);
		
		board.setLecId(classVO.getLecId());
		
		String viewName=null;
		if(!errors.hasErrors()) {
			ServiceResult result = service.modifyBoard(board);
			switch (result) {
//			case INVALIDPASSWORD:
//				viewName="redirect:lecQna/lecQnaEdit";
//				model.addAttribute("message", "비밀번호 오류입니다.");
//				break;
			case FAIL:
				viewName="redirect:lecQna/lecQnaEdit";
				model.addAttribute("message", "서버 오류입니다.");
				break;
			default:
				redirectAttributes.addFlashAttribute("success", board);
				redirectAttributes.addFlashAttribute("message", "수정성공함");
				viewName = "redirect:../qna";
				break;
			}
		}else {
			viewName="lecQna/lecQnaEdit";
			redirectAttributes.addFlashAttribute("message", "제목이나 글을 입력하세요");
		}
		return viewName;
	}
}
