package kr.or.ddit.lecqna.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.AttchVO;
import kr.or.ddit.vo.LecqnaVO;
import kr.or.ddit.vo.PagingVO;

/**강의 qna  service
 * @author 황선부
 * @since 2022. 5. 24.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 5. 24.      황선부       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
public interface LecQnaService {
	public void retrieveBoardList(PagingVO<LecqnaVO>paging);
	
	public LecqnaVO retrieveBoard(int lecboNo);
	public ServiceResult createBoard(LecqnaVO lecqna);
	public AttchVO downloadAttach(int attchNo);
	public ServiceResult modifyBoard(LecqnaVO lecqna);
	
	public ServiceResult deleteBoard(LecqnaVO lecqna);
	
	public List<LecqnaVO> retrieveUnanswerd(Map<String, Object> map);
	public List<Map<String, Object>> retrieveMyQuestion(int stuNo);
}
