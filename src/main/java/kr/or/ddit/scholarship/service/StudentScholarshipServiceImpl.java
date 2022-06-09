package kr.or.ddit.scholarship.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.scholarship.dao.StudentScholarshipDAO;
import kr.or.ddit.vo.ScholarshipVO;

/**
 * 
 * @author 민진홍	
 * @since 2022. 5. 10.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 5. 10.      민진홍	       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Service
public class StudentScholarshipServiceImpl implements StudentScholarshipService {

	@Inject
	StudentScholarshipDAO dao;
	
	/**
	 * 장학금리스트조회
	 */
	@Override
	public List<ScholarshipVO> studentScholarshipApplyList() {
		
		return dao.studentScholarshipApplyList();
	}
	
	/**
	 * 장학금신청
	 */
	@Override
	public int studentScholarshipApply(Map<String, Object> info) {
		return dao.studentScholarshipApply(info);
	
		
	}
	
	/**
	 * 이미신청한 장학금코드조회d
	 */
	@Override
	public List<String> studentScholarshipAleardyApply(Map<String,Object> info) {
		return dao.studentScholarshipAleardyApply(info);
	}
	
	/**
	 * 장학금 신청내역 조회
	 */
	@Override
	public List<Map<String, Object>> studentScholarshipReqApplyList(Map<String, Object> info) {
		return dao.studentScholarshipReqApplyList(info);
	}

	
	/**
	 * 장학금 신청취소
	 */
	@Override
	public int cancelStudentScholarshipApply(String reqId) {
		return dao.cancelStudentScholarshipApply(reqId);
	}
	
	
	

}
