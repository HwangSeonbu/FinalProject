package kr.or.ddit.lecqna.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.lecqna.dao.LecQnaDAO;
import kr.or.ddit.lecqna.dao.LecQnaReplyDAO;
import kr.or.ddit.vo.LecQnaReplyVO;

/**강의 qna 댓글 관련 service
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
public interface LecQnaReplyService {
	public List<LecQnaReplyVO> retrieveLecQnaReplyList(Integer lecboNo);
	public ServiceResult createLecQnaReply(LecQnaReplyVO reply);
	public ServiceResult deleteLecQnaReply(String replyNo);
}
