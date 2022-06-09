package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.vo.CalendarVO;

public interface AcadScheduleBoardService {
	/**
	 * 코드에 맞는 회원 목록 전체 조회 
	 * @param acadscDiv
	 * @return 
	 */
	public List<CalendarVO> getAllSchedule();
	/**
	 * 일정 상세조회 
	 * @param acadscNo
	 * @return
	 */
	public CalendarVO retrieveSchedule(Integer acadscNo);
	/**
	 * 일정 등록 
	 * @param acadscVo
	 * @return
	 */
	public void createSchedule(CalendarVO acadscVo);
	/**
	 * 일정 수정 
	 * @param acadscVo
	 */
	public void modifySchedule(CalendarVO acadscVo);
	/**
	 * 일정 삭제 
	 * @param acadscNo
	 */
	public void removeSchedule(Integer acadscNo);
	
}
