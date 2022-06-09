package kr.or.ddit.lecqna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.lecqna.service.LecQnaReplyService;
import kr.or.ddit.vo.LecQnaReplyVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.security.MemberVOWrapper;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RequestMapping("/lecBoard/qna/reply")
@Controller
public class LecQnaReplyInsertController {
	@Autowired
	private LecQnaReplyService service;
	
	@PostMapping("new")
	public String insertLecReply(
			@ModelAttribute LecQnaReplyVO reply
			,Model model
			,RedirectAttributes redirectAttributes
			,Authentication authen
			) {

		ServiceResult result = service.createLecQnaReply(reply);
		int boNo = reply.getLecboNo();
		if(!result.equals(ServiceResult.OK))
			redirectAttributes.addFlashAttribute("message", "서버 오류입니다.");
			
			model.addAttribute("message", "성공");
		
		
		return "jsonView";
	}
}
