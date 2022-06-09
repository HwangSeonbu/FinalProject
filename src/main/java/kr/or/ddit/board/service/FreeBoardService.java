package kr.or.ddit.board.service;

import java.util.Map;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.BoLikeVO;
import kr.or.ddit.vo.FreeBoardVO;
import kr.or.ddit.vo.PagingVO;

public interface FreeBoardService {
	public void retrieveBoardList(PagingVO<FreeBoardVO> paging);
	public FreeBoardVO retrieveBoard(Map<String, Object> paramMap);
	public void createFBoard(FreeBoardVO fBoardVO);
	public void removeFBoard(String boardNo);
	public void modifyFBoard(FreeBoardVO fBoardVO);
	
	public ServiceResult retrieveBoLikeMarkUp(BoLikeVO vo);
}
