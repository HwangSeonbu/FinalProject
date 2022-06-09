package kr.or.ddit.counsel.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.CounselVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProCounselVO;
import kr.or.ddit.vo.QuestionAnswerVO;
import kr.or.ddit.vo.QuestionVO;
import kr.or.ddit.vo.SCounselVO;
import kr.or.ddit.vo.StudentVO;

/**상담 Service interface
 * @author 황선부
 * @since 2022. 4. 28.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 4. 28.      황선부       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */

public interface CounselService {
	
	public List<SCounselVO> retreiveCounselStu (PagingVO<SCounselVO>paging);
	public List<QuestionVO> listQuestion();
	public ServiceResult createCounsel(CounselVO counsel);
	public List<ProCounselVO> retreiveCounselPro(PagingVO<ProCounselVO>paging);
	
	public ProCounselVO retreiveOneCounselStudent(Integer userNo);
	public Map<String, Object> retreiveOneReqCounsel(String cnslId);
	public List<String> retreiveOneLogCounsel(String cnslId, ProCounselVO counsel);
	public ServiceResult createCounselLog(CounselVO counselVO);
	
	public ServiceResult modifyMessageInfo(CounselVO counsel);
	
	public ServiceResult modifyCounselLog(CounselVO counsel);
	
	public ServiceResult deleteCounselLog(String cnslId);
	
}
