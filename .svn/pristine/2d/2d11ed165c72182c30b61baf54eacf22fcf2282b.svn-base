package kr.or.ddit.lecqna.service;

import java.util.List;


import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.PKNotFoundException;
import kr.or.ddit.lecqna.dao.LecQnaReplyDAO;
import kr.or.ddit.vo.LecQnaReplyVO;
/**강의 qna 댓글 관련 serviceImpl
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
@Service
public class LecQnaReplyServiceImpl implements LecQnaReplyService {
	@Inject
	private LecQnaReplyDAO dao;
	
	@Override
	public List<LecQnaReplyVO> retrieveLecQnaReplyList(Integer lecboNo) {
		List<LecQnaReplyVO> result = dao.selectLecqnaReplyList(lecboNo);
		
		if(result == null)
			throw new PKNotFoundException(lecboNo+"에 해당하는 게시글 번호가 없음");
		
		return result;
	}

	@Override
	public ServiceResult createLecQnaReply(LecQnaReplyVO reply) {
		ServiceResult result = null;
		int rowcnt = dao.insertLecqnaReply(reply);
		return rowcnt>0?ServiceResult.OK:ServiceResult.FAIL;
	}

	@Override
	public ServiceResult deleteLecQnaReply(String replyNo) {
		ServiceResult result = null;
		int rowcnt = dao.deleteLecqnaReply(replyNo);
		return rowcnt>0?ServiceResult.OK:ServiceResult.FAIL;
	}

}
