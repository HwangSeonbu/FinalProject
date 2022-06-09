package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReplyVO;

@Mapper
public interface ReplyDAO {
	/**
	 * 댓글 개수
	 * @return
	 */
	public int replyCount();
	/**
	 * 댓글 목록
	 * @return
	 */
	public List<ReplyVO> replyList(String boardNo);
	/**
	 * 신규 댓글 등록 
	 * @param replyvo
	 */	
	public int insertReply(ReplyVO reply);
	/**
	 * 댓글 수정
	 * @param reply
	 * @return
	 */
	public int replyUpdate(ReplyVO reply); 
	/**
	 * 댓글 삭제 
	 * @param replyNo
	 * @return
	 */
	public int replyDelete(String replyNo);
		
}
