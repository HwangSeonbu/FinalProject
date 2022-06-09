package kr.or.ddit.lecqna.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.LecqnaVO;
import kr.or.ddit.vo.PagingVO;

/**강의 qna게시판 dao
 * @author 황선부
 * @since 2022. 5. 24.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 5. 24.      황선부       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Mapper
public interface LecQnaDAO {
	
	/**
	 * @param paging
	 * @return
	 */
	public int selectTotalLecqnaRecord(PagingVO<LecqnaVO> paging);
	
	/**
	 * @param paging
	 * @return
	 */
	public List<LecqnaVO> selectLecqnaBoardList(PagingVO<LecqnaVO> paging);
	
	
	public LecqnaVO selectLecqnaBoard(Integer lecboNo);
	
	public void incrementHit(Integer lecboNo);
	
	public int insertLecqnaBoard(LecqnaVO lecqna);
	
	public int updateLecqnaBoard(LecqnaVO lecqna);
	
	public int deleteLecqnaBoard(LecqnaVO lecqna);
	
	public List<LecqnaVO> selectUnanswerd(Map<String, Object> map);
	
	public List<Map<String, Object>> selectMyQuestion(int userNo);
	
}
