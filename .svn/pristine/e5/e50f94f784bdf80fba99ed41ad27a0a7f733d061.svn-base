package kr.or.ddit.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.board.dao.ReplyDAO;
import kr.or.ddit.vo.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Inject 
	private ReplyDAO dao;
		
	@Override
	public List<ReplyVO> replyListSercive(String boardNo) {
		return dao.replyList(boardNo);
	}

	@Override
	public int replyInsertService(ReplyVO reply) {
		
		return dao.insertReply(reply);
	}

	@Override
	public int replyDeleteService(String replyNo) {
		
		return dao.replyDelete(replyNo);
	}

	@Override
	public int replyUpdateService(ReplyVO reply) {
		
		return dao.replyUpdate(reply);
	}

	
	
}
