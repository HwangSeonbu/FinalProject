package kr.or.ddit.lecqna.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.LecQnaReplyVO;

/**질의응답 게시판 댓글처리 dao
 * @author 황선부
 * @since 2022. 5. 21.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 5. 21.      황선부       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Mapper
public interface LecQnaReplyDAO {

	public List<LecQnaReplyVO> selectLecqnaReplyList(Integer lecboNo);
	public int insertLecqnaReply(LecQnaReplyVO reply);
	public int deleteLecqnaReply(String replyNo);
	
}
