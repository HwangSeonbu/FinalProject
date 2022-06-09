/**
 * 
 */
package kr.or.ddit.scholarship.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.ScholarshipVO;

/**
 * @author 민진홍
 * @since 2022. 5. 10.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 5. 10.      민진홍       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */

@Mapper
public interface StudentScholarshipDAO {
	/**
	 * (학생)장학금 리스트 조회
	 * @return
	 */
	public List<ScholarshipVO> studentScholarshipApplyList(); 
	
	/**
	 * 이미신청한 장학금코드조회
	 * @return
	 */
	public List<String> studentScholarshipAleardyApply(Map<String,Object> info);
	
	/**
	 * (학생)장학금 신청
	 * @param info
	 * @return
	 */
	public int studentScholarshipApply(Map<String, Object> info);
	
	/**
	 * (학생)장학금 신청내역 조회
	 * @param info
	 * @return
	 */
	public List<Map<String,Object>> studentScholarshipReqApplyList(Map<String, Object> info);
	
	/**
	 * (학생)장학금 신청취소
	 * @param reqId
	 * @return
	 */
	public int cancelStudentScholarshipApply(String reqId);
	
	
}
