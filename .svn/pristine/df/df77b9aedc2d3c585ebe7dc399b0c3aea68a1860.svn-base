package kr.or.ddit.lecboard.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.lecboard.service.LecBoardService;
import kr.or.ddit.validate.UpdateLecBoardGroup;
import kr.or.ddit.vo.LecNoticeBoardVO;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@RequestMapping("/lecBoard/notice/{lecboNo}")
@SessionAttributes("board")
public class LecBoardUpdateController {
	@Inject
	private LecBoardService service;
	
	@GetMapping("form")
	public String updateForm(@PathVariable int lecboNo, Model model) {
//		if(!model.containsAttribute("board")) {
			LecNoticeBoardVO board = service.retrieveOneLecBoard(lecboNo);
			model.addAttribute("board", board);
			log.info("board:{}",board);
//		}
		return "lecBoard/lecboardEdit";
	}
	@PostMapping
	public String updateProcess(
			@Validated(UpdateLecBoardGroup.class) @ModelAttribute("board") LecNoticeBoardVO board
			,BindingResult errors
			,Model model
			,RedirectAttributes redirectAttributes
			) {
		log.info("lecNoticeBoardVO==>{}.....................",board);
		String viewName=null;
		if(!errors.hasErrors()) {
			ServiceResult result = service.modifyBoard(board);
			switch (result) {
//			case INVALIDPASSWORD:
//				viewName="redirect:/lecBoard/notice/{lecboNo}/form";
//				model.addAttribute("message", "비밀번호 오류입니다.");
//				break;
			case FAIL:
				viewName="lecBoard/lecboardEdit";
				model.addAttribute("message", "서버 오류입니다.");
				break;
			default:
				redirectAttributes.addFlashAttribute("success", board);
				redirectAttributes.addFlashAttribute("message", "수정성공함");
				viewName = "redirect:../../lecBoard/notice";
				break;
			}
		}else {
			viewName="lecBoard/lecboardEdit";
			redirectAttributes.addFlashAttribute("message", "제목이나 글을 입력하세요");
		}
		return viewName;
	}
}
