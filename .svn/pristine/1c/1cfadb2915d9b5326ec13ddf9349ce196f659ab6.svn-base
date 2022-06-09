package kr.or.ddit.homework.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.homework.service.HomeworkService;
import kr.or.ddit.validate.InsertHwkInsertGroup;
import kr.or.ddit.vo.ClassVO;
import kr.or.ddit.vo.HwkVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.security.MemberVOWrapper;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RequestMapping("/homework")
@Controller
public class HomeworkSubmitInsertController {
	@Inject
	private HomeworkService service;
	@PostMapping("submit")
	public String submit(
			@Validated(InsertHwkInsertGroup.class) @ModelAttribute("hwk")HwkVO hwk
			,BindingResult errors
			,HttpSession session
			,RedirectAttributes redirectAttributes
			,Model model
			,Authentication authentication
			) {
		String viewName=null;
		MemberVO member = ((MemberVOWrapper)authentication.getPrincipal()).getRealUser();
		int userNo = member.getUserNo();
		hwk.setStuNo(userNo);
		log.info("HwkVO:{}",hwk);
		
		if(!errors.hasErrors()) {
			ClassVO classVO = (ClassVO) session.getAttribute("classVO");
//			homework.setLecId(classVO.getLecId());		
//			log.info("homework========================>{}",homework);
			ServiceResult result=service.createHwk(hwk);
			if(result.equals(ServiceResult.OK)) {
				redirectAttributes.addFlashAttribute("message", "과제를 등록하였습니다.");
				viewName="redirect:../homework/"+hwk.getLechwkNo();
			}else {
				redirectAttributes.addFlashAttribute("message", "서버 오류 입니다. 다시 실행해주세요.");
				viewName="homework/hwView";				
			}
		}else {
			redirectAttributes.addFlashAttribute("hwk", hwk);

			viewName = "homework/hwView";
		}	
		return viewName;
	}
		
		
	}

