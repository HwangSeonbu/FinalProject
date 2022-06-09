package kr.or.ddit.lecboard.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.AttchVO;
import kr.or.ddit.vo.ClassVO;
import kr.or.ddit.vo.LecNoticeBoardVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.UserVO;

/**
 * 강의 게시판 Service
 * @author 황선부
 * @since 2022. 5. 13.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 5. 13.      황선부       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */

public interface LecBoardService {
	
	public List<Map<String, Object>> retrieveLectureListOfStu(int stuNo);
	public List<Map<String, Object>> retrieveLectureListOfPro(int userNo);
	public List<UserVO> retrieveLecUserList(ClassVO classVO);
	public ServiceResult retrieveLecAuthenUser(ClassVO classVO);
	
	public void retrieveLecBoard(PagingVO<LecNoticeBoardVO> paging);
	
	public LecNoticeBoardVO retrieveOneLecBoard(Integer lecboNo);
	
	public ServiceResult createlecBoard(LecNoticeBoardVO lecNotice);
	
	public AttchVO downloadAttach(Integer attchNo);
	
	public ServiceResult modifyBoard(LecNoticeBoardVO lecNotice);
	
	public ServiceResult removeBoard(LecNoticeBoardVO board);
	
	public Map<String, Object> retrieveOneLec(String lecId);
	
	public List<Map<String, Object>> retrieveOneLecStus(String lecId);
	

	
}
