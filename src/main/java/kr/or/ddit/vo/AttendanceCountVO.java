package kr.or.ddit.vo;

import lombok.Data;

/** 학생이 수강 중인 강의의 출석현황 카운터 조회
 * 	(출석수/결석수/조퇴수/지각수) 
 * @author 작성자명
 * @since 2022. 5. 3.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 5. 3.      주창규       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Data
public class AttendanceCountVO {
	private int totalCount;			//총 일수
	private int attendanceCount;	//출석수
	private int absentCount;		//결석수
	private int earlyLeaveCount;	//지각수
	private int tardinessCount;		//조퇴수
}
