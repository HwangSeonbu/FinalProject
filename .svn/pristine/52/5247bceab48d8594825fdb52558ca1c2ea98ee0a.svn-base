package kr.or.ddit.homework.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.homework.service.HomeworkService;
import kr.or.ddit.validate.HwkUpdateGroup;
import kr.or.ddit.vo.HwkVO;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@RequestMapping("/homework")
public class HomeworkSubmitUpdateController {
	@Inject
	private HomeworkService service;
	@PostMapping("submitUpdate")
	public String update(
			@Validated(HwkUpdateGroup.class) @ModelAttribute("hwk") HwkVO hwk
			,BindingResult errors
			,RedirectAttributes redirectAttributes
			) {
		String viewName= null;
		log.info("hwk====={}",hwk);
		if(!errors.hasErrors()) {
			ServiceResult result = service.modifyHwk(hwk);
			if(result.equals(ServiceResult.OK)) {
				viewName = "redirect:../homework";
				redirectAttributes.addFlashAttribute("message", "제출한 과제가 수정되었습니다.");
			}else {
				viewName = "redirect:../homework/"+hwk.getLechwkNo();
				redirectAttributes.addFlashAttribute("message", "서버 오류입니다.다시 시도해주세요.");
				
			}
			
		}else {
			viewName="redirect:../homework/"+hwk.getLechwkNo();
		}
		
		
		return viewName;
	}
}
