package kr.or.ddit.attendance.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.AttendanceCountVO;
import kr.or.ddit.vo.LecturePeriodVO;
import kr.or.ddit.vo.StudentVO;
import kr.or.ddit.vo.SubjectVO;

/**
 * 학생의 출석현황 관리를 하기 위한 Persistence Layer
 * 교수가 자신의 강의를 수강하는 학생들의 출석현황 관리를 하기 위한 Persistence Layer
 * @author 주창규
 * @since 2022. 4. 27.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * 
 *      <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 4. 27.      주창규       최초작성
 * 2022. 5. 24.      주창규       교수추가
 * Copyright (c) 2022 by DDIT All right reserved
 *      </pre>
 */
@Mapper
public interface AttendanceDAO {

	/**
	 * 학생 정보 상세 조회 (학번,이름,학부,학과,학년,학적상태)
	 * 
	 * @param userNo 조회할 아이디
	 * @return
	 */
	public StudentVO selectStudentDetail(int userNo);

	/**
	 * 학생이 수강 중인 과목들 조회 (과목학년,과목명,교수명)
	 * 
	 * @param lectureVo
	 * @return
	 */
	public List<SubjectVO> selectStudentLecture(StudentVO studentVo);

	/**
	 * 학생이 수강 중인 강의 주차별 출석현황 조회(1주~16주)
	 * 
	 * @param studentVo
	 * @return
	 */
	public List<LecturePeriodVO> selectStudentLecturePeriod(StudentVO lectureVo);

	/**
	 * 학생이 수강 중인 강의의 출석현황 카운터 조회 (출석수/결석수/조퇴수/지각수)
	 * 
	 * @param attendanceVo
	 * @return
	 */
	public AttendanceCountVO selectStudentAttendanceCount(StudentVO studentVo);

	/** 강의 시작 날짜
	 * @param studentVo
	 * @return
	 */
	public Integer selectStartDate(int lecSems);
	
	/**
	 * 학생이 수강 중인 강의의 출석현황 카운터 조회 (출석수/결석수/조퇴수/지각수) (Map)
	 * 
	 * @param attendanceVo
	 * @return
	 */
	public Map<String, Object> selectStudentAttendanceCountMap (Map<String, Object> map);
	
}