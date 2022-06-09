package kr.or.ddit.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.BoLikeVO;
import kr.or.ddit.vo.FreeBoardVO;
import kr.or.ddit.vo.PagingVO;

/**
 * @author 작성자명
 * @since 2022. 5. 13.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 5. 13.      이유정       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
		 
@Mapper
public interface FreeBoardDAO {
	/**
	 * 전체 레코드 
	 * @param paging
	 * @return
	 */
	public int selectTotalFRecord(PagingVO<FreeBoardVO> paging);
	/**
	 * 회원 목록 조히 
	 * @param paging
	 * @return 조건에 맞는 레코드가 없는 경우, size()==0
	 */
	public List<FreeBoardVO> selectFBoardList(PagingVO<FreeBoardVO> paging);
	/**
	 * 상세조회 뷰 
	 * @param boardNo
	 * @return
	 */
	public FreeBoardVO selectFBoard(Map<String, Object> paramMap);
	/**
	 * 게시물 등록 
	 * @param fBoard
	 * @return
	 */
	public int insertFBoard(FreeBoardVO fBoard);
	/**
	 * 게시물 수정 
	 * @param fBoard
	 * @return
	 */
	public void updateFBoard(FreeBoardVO fBoard);
	/**
	 * 게시물 삭제 
	 * @param boardNo
	 * @return
	 */
	public void deleteFBoard(String boardNo);
	/**
	 * 조회수 증가 
	 * @param boardNo
	 * @return
	 */
	public void incrementHit(String boardNo);

	
	/**
	 * 읽은 글 검사
	 * @param paramMap
	 * @return
	 */
	public int selectFBoardReadCount(Map<String, Object> paramMap);
	public void insertFBoardReadRecord(Map<String, Object> paramMap);
	
	/* 추천비추천 프로세스 */
	/*셀렉트, 인서트, 업데이트, + 라이크증가, 디스라이크증가, 라증디감, 라감디증*/
	
	public BoLikeVO selectBoLikeHistory(BoLikeVO vo);
	public int insertBoLike(BoLikeVO vo);
	public int updateBoLike(BoLikeVO vo);
	
	public int updateLikePlus(BoLikeVO vo);
	public int updateDislikePlus(BoLikeVO vo);
	
	public int updateLikePlusAndDislikeMinus(BoLikeVO vo);
	public int updateLikeMinusAndDislikePlus(BoLikeVO vo);
	
	
	
	
	
	
	
	
	
	
	
}