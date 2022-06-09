package kr.or.ddit.board.service;

import java.util.Map;

import kr.or.ddit.vo.AttchVO;
import kr.or.ddit.vo.NoticeBoardVO;
import kr.or.ddit.vo.PagingVO;

public interface NoticeBoardService {
	public void retrieveNBoardList(PagingVO<NoticeBoardVO> paging);
	public NoticeBoardVO retrieveBoard(Map<String, Object> paramMap);
	public void removeNBoard(Integer noticeNo);
	public int modifyNBoard(NoticeBoardVO nBoardVO);
	public int createNBoard(NoticeBoardVO nBoardVO);
	public void uploadAttch(NoticeBoardVO nBoardVO);
	public AttchVO downloadAttch(Integer attchNo);
}
