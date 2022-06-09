package kr.or.ddit.lecqna.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.lecqna.service.LecQnaReplyService;

@Controller
public class LecQnaReplyDeleteController {
	@Inject
	private LecQnaReplyService service;
	@RequestMapping("/lecBoard/qna/reply/delete")
	public String deleteReply(
			@RequestParam("replyNo") String replyNo
			,Model model
			) {
		ServiceResult result = service.deleteLecQnaReply(replyNo);
		
		if(result.equals(ServiceResult.OK)){
			model.addAttribute("message", "댓글이 삭제되었습니다.");
		}else {
			model.addAttribute("message", "삭제가 실패하였습니다.");
		}
		
		return "jsonView";
	}
}
