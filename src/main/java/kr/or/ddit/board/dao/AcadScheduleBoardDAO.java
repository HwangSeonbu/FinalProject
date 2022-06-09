package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.CalendarVO;

/**
 * @author 이유정
 * @since 2022. 5. 6.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 5. 6.      이유정       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */

@Mapper
public interface AcadScheduleBoardDAO {
	/**
	 * 일정구분에 코드'B1' 일정 전체 목록 조회 
	 * @param acadscDiv
	 * @return 존재하지 않을 경우, null 반환 
	 */
	public List<CalendarVO> acadScheduleBoardList();
	/**
	 * 스케줄 상세조회 
	 * @param acadscNo
	 * @return
	 */
	public CalendarVO selectSchedule(Integer acadscNo);
	/**
	 * 일정등록
	 * @param acadscVo
	 * @return
	 */
	public int insertAcadSchedule(CalendarVO acadscVo);
	/**
	 * 일정 수정 
	 * @param acadscNo
	 * @return
	 */
	public void updateSchedule(CalendarVO acadscVo);
	/**
	 * 일정 삭제 
	 * @param acadscNo
	 * @return
	 */
	public int deleteSchedule(Integer acadscNo);
}
