package kr.or.ddit.board.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.DefaultValue;

import org.apache.struts.tiles.RedeployableActionServlet;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.board.service.ReplyService;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReplyVO;

/**
 * @author 이유정
 * @since 2022. 5. 12.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 5. 12.      이유정       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Controller
@RequestMapping("/board")
public class ReplyController {
	
	@Inject
	private ReplyService service;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<ReplyVO> replyServiceHandler(@RequestParam String boardNo){
		System.out.println("############################################### >> "+boardNo);
			return service.replyListSercive(boardNo);
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public int replyServiceInsert(@RequestParam String boardNo, @RequestParam String replyContent, @RequestParam Integer userNo) {
		ReplyVO reply = new ReplyVO();
		reply.setUserNo(userNo);
		reply.setBoardNo(boardNo);
		reply.setReplyContent(replyContent);
		return service.replyInsertService(reply);
	}
	
	@PostMapping("/delete/{replyNo}")
	@ResponseBody
	public int replyServiceDelete(@PathVariable String replyNo) {
		System.out.println("###################################"+replyNo);
		return service.replyDeleteService(replyNo);
	}	
	
	@RequestMapping("/update")
	@ResponseBody
	public int replyServiceUpdate(@RequestParam String replyNo, @RequestParam String replyContent) {
		ReplyVO reply = new ReplyVO();
		reply.setReplyNo(replyNo);
		reply.setReplyContent(replyContent);
		return service.replyUpdateService(reply);
	}
}




