package kr.or.ddit.lecboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.lecboard.service.LecBoardService;
import kr.or.ddit.validate.DeleteLecBoardGroup;
import kr.or.ddit.vo.LecNoticeBoardVO;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@RequestMapping("/lecBoard/notice")
public class LecBoardDeleteController {
	@Autowired
	private LecBoardService service;
	@DeleteMapping("{lecboNo}")
	public String boardDelete(
			@Validated(DeleteLecBoardGroup.class) @ModelAttribute LecNoticeBoardVO board
			, BindingResult errors
			, RedirectAttributes redirectAttributes
			) {
		String viewName = null;
		viewName="redirect:../../lecboard/notice/{lecboNo}";
		if(!errors.hasErrors()) {
			ServiceResult result = service.removeBoard(board);
			switch (result) {
			case FAIL:
				redirectAttributes.addFlashAttribute("message", "서버 오류");
				break;

			default:
				redirectAttributes.addFlashAttribute("message", "삭제성공함");
				viewName="redirect:../notice";
				break;
			}
		}else {
			redirectAttributes.addFlashAttribute("message", "글번호 누락");
		}
	return viewName;
		
	}
	
	
}
