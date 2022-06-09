package kr.or.ddit.homework.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.homework.service.HomeworkService;
import kr.or.ddit.vo.LecHomeworkVO;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@RequestMapping("/homework")
public class HomeworkDeleteController {
	@Inject
	private HomeworkService service;
	@DeleteMapping("{lechwkNo}")
	public String delete(
			@ModelAttribute("homework") LecHomeworkVO homework
			,RedirectAttributes redirectAttributes
			) {
		log.info("homwork.dddddd{}",homework.getLechwkNo());
		
		String viewName = null;
		viewName="redirect:../homework/{lechwkNo}";
			
			ServiceResult result = service.deleteHomwork(homework);
			
			switch (result) {
			case FAIL:
				redirectAttributes.addFlashAttribute("message", "서버 오류");
				break;

			default:
				redirectAttributes.addFlashAttribute("message", "삭제성공함");
				viewName="redirect:../homework";
				break;
			}
	
			return viewName;
		
	}
}

