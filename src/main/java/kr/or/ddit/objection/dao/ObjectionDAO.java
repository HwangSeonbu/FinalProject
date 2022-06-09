package kr.or.ddit.objection.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;


/**
 * 학생이 자신의 출석인정현황을 관리하기 위한 Persistence Layer
 * @author 주창규
 * @since 2022. 4. 29.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 4. 29.      주창규       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Mapper
public interface ObjectionDAO {

	/**
	 * 학생이 출석인정 신청을 조회
	 * 
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> selectStudentAttendanceObjection(Map<String, Object> map);

}
