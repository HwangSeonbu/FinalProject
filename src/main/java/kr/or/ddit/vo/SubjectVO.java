package kr.or.ddit.vo;

import java.util.List;

import lombok.Data;

/**
 * 교과목 테이블 + 교수명 VO  (테이블명 : SJT/USERS)
 * @author 주창규
 * @since 2022. 4. 28.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 4. 28.      주창규       최초작성
 * 2022. 5. 09.      주창규	 출석상태 추가
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Data
public class SubjectVO {
	private String lecId;		//과목아아디
	private String lecName;		//강의명
	private Integer sjtCredit;	//학점
	private String sjtMajor;	//전공분류
	private Integer sjtGrade;	//과목에대한 학년(ex 1학년과목, 2학년과목)
	private String proName;		//교수명
	private String stsCode1;	// 출석 카운터를 통한 출석상태 (출석양호, 출석불안, 출석위기, 출석제적)
	private List<LecturePeriodVO> lecturePeriodList;		//1~16주차 출석 현황을 List
	
	private AttendanceCountVO attendanceCountVo;	//출석현황카운터List
	private String sjtName;
	private String sjtId;
}

