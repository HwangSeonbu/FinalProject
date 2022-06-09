package kr.or.ddit.request.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.RequestVO;

/**
 * 학생의 휴복학 신청 serviceimpl
 * @author 작성자명
 * @since 2022. 5. 4.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 5. 4.    고성식			최초 작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
public interface RequestService {
	/**
	 * 휴학신청리스트
	 * @param paging
	 * @return
	 */
	public List<RequestVO> retrieveStudentLeaveRequestList(PagingVO<RequestVO> paging);
	
	/**
	 * 휴학신청 승인
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public int updateLeaveSts(List<RequestVO> list) throws Exception;
	
	/**
	 * 휴학신청 반려
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public int referLeaveSts(List<RequestVO> list) throws Exception;
	
	/**
	 * 학생의 휴학 신청
	 * @param request
	 * @throws IOException
	 */
	public String studentLeaveRequest(RequestVO requestVO) throws Exception;
	
	/**
	 * 복학신청리스트
	 * @param paging
	 * @return
	 */
	public List<RequestVO> retrieveStudentReturnRequestList(PagingVO<RequestVO> paging);
	
	/**
	 * 복학신청 승인
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public int updateReturnSts(List<RequestVO> list) throws Exception;
	
	/**
	 * 학생의 복학 신청
	 * @param requestVO
	 * @throws Exception
	 */
	public void studentReturnRequest(RequestVO requestVO) throws Exception;
	
	/**
	 * 로그인한 학생의 휴학신청 결과 출력
	 * @param list
	 * @return
	 */
	public List<RequestVO> selectStudentLeaveRequestList(Map<String, Object> param);
	
}
