package kr.or.ddit.enr.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.AdminEnrollVO;
import kr.or.ddit.vo.PagingVO;

/**
 * @author 고성식
 * @since 2022. 5. 24.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 5. 24.   고성식 		      최초작성 
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
public interface AdminEnrollService {
	/**
	 * 등록금 고지 받을 학생 리스트 출력
	 * @param paging
	 * @return
	 */
	public List<AdminEnrollVO> retrieveStudentEnrollList(PagingVO<AdminEnrollVO> paging);
	
	
	/**
	 * 등록금 부과
	 * @param info
	 * @return
	 */
	public int adminCreatEnroll(List<AdminEnrollVO> list) throws Exception;
	
	/**
	 * 등록금 납부확인 받을 학생 리스트 출력
	 * @param param
	 * @return
	 */
	public List<AdminEnrollVO> retrieveFinalEnrollList(Map<String, Object> param);
	
	/**
	 * 등록금 납부확인 받을 학생 총 카운트
	 * @param param
	 * @return
	 */
	public int retrieveFinalEnrollListTotalCount(Map<String, Object> param);
	
	/**
	 * 납부확인
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public int adminFinalCreatEnroll(List<AdminEnrollVO> list) throws Exception;
	
}
