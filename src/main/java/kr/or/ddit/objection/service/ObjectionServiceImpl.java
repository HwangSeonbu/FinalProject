package kr.or.ddit.objection.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.exception.PKNotFoundException;
import kr.or.ddit.objection.dao.ObjectionDAO;
import kr.or.ddit.vo.ObjectionVO;
import kr.or.ddit.vo.StudentVO;

/**
 * @author 주창규
 * @since 2022. 4. 29.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * 
 *      <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 4. 29.      주창규       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 *      </pre>
 */
@Service
public class ObjectionServiceImpl implements ObjectionService {

	@Inject
	private ObjectionDAO oDao;

//	학생이 자신의 출석인정신청현황목록을 조회
	@Override
	public List<Map<String, Object>> retrieveStudentAttendanceObjection(Map<String, Object> map) {
		
		return oDao.selectStudentAttendanceObjection(map);
	}


}
