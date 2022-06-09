package kr.or.ddit.calendar.dao;

import java.util.List;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import kr.or.ddit.vo.CalendarVO;

/**
 * @author 이유정
 * @since 2022. 5. 4.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 5. 4.      이유정      최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Mapper
public interface CalendarDAO {
	public List<CalendarVO> selectCalendarList(int acadscUserno);
	public int insertCalendar(CalendarVO calendarVo);
	public CalendarVO selectCalendar(Integer acadscNo);
	public int updateCalendar(CalendarVO calendarVo);
	public int deleteCalendar(Integer acadscNo);
	
	
}	
