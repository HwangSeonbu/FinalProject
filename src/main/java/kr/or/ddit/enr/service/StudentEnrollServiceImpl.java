/**
 * 
 */
package kr.or.ddit.enr.service;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import kr.or.ddit.enr.dao.StudentEnrollDAO;
import kr.or.ddit.vo.ScholarshipVO;
import kr.or.ddit.vo.StudentEnrollVO;

/**
 * @author 민진홍
 * @since 2022. 5. 6.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 5. 6.      민진홍       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */

@Service
public class StudentEnrollServiceImpl implements StudentEnrollService {
	
	@Inject
	private StudentEnrollDAO dao;
	/* (non-Javadoc)
	 * @see kr.or.ddit.enr.service.StudentEnrollService#studentEnrollView(int)
	 */
	@Override
	public StudentEnrollVO studentEnrollView(int userNo) {
		
		
		return dao.studentEnrollView(userNo);
	}
	@Override
	public ScholarshipVO studentSCHView(String schNo) {
		
		return dao.studentSCHView(schNo);
	}
	@Override
	public List<StudentEnrollVO> studentEnrollListView(int userNo) {
		return dao.studentEnrollListView(userNo);
	}

}
