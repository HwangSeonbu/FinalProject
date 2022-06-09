package kr.or.ddit.board.dao;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.AttchVO;
import kr.or.ddit.vo.NoticeBoardVO;

@Mapper
public interface NBoardAttchDAO {
	public int insertAttches(NoticeBoardVO nBoardVO);
	public AttchVO selectAttch(Integer attchNo);
	public void incrementDowncount(Integer attchNo);
	public int deleteAttches(NoticeBoardVO nBoard);
	public void uploadAttchs(NoticeBoardVO nBoard);
}
