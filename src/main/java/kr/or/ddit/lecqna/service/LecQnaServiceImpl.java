package kr.or.ddit.lecqna.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.PKNotFoundException;
import kr.or.ddit.lecboard.dao.AttachDAO;
import kr.or.ddit.lecqna.dao.LecQnaDAO;
import kr.or.ddit.utils.PasswordUtils;
import kr.or.ddit.vo.AttchVO;
import kr.or.ddit.vo.LecNoticeBoardVO;
import kr.or.ddit.vo.LecqnaVO;
import kr.or.ddit.vo.PagingVO;
import lombok.extern.slf4j.Slf4j;
/**강의 qna  service Impl
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
@Slf4j
public class LecQnaServiceImpl implements LecQnaService {
	@Autowired
	private LecQnaDAO qnaDAO;
	@Autowired
	private AttachDAO attachDAO;
	
//	@Inject
//	private PasswordEncoder passwordEncoder;
	
	@Value("#{appInfo.attatchPath}")
	private File saveFolder;
	
	@Override
	public void retrieveBoardList(PagingVO<LecqnaVO> paging) {
		// TODO Auto-generated method stub
		int totalRecord = qnaDAO.selectTotalLecqnaRecord(paging);
		log.info("totalRecord==========>{}",totalRecord);
		paging.setTotalRecord(totalRecord);
		List<LecqnaVO> dataList = qnaDAO.selectLecqnaBoardList(paging);
		paging.setDataList(dataList);
	}
	@Override
	public LecqnaVO retrieveBoard(int lecboNo) {
		LecqnaVO lecQna = qnaDAO.selectLecqnaBoard(lecboNo);
		if(lecQna==null)
			throw new PKNotFoundException(lecboNo+"에 해당하는 게시물 번호가 없습니다.");	
		qnaDAO.incrementHit(lecboNo);
		
		return lecQna;
	}
	@Override
	public ServiceResult createBoard(LecqnaVO lecqna) {
		ServiceResult result = null;
		int rowcnt = qnaDAO.insertLecqnaBoard(lecqna);
	
		if(rowcnt > 0) {
			uploadAttatches(lecqna);
		}
		return rowcnt>0?ServiceResult.OK:ServiceResult.FAIL;
	}
	private void uploadAttatches(LecqnaVO board) {
		AttchVO attach= board.getAttach();
		log.info("attach=======>>>>>{}",attach);
		if(attach==null)
			return;
		attachDAO.insertQnaAttach(board);
		try {
			attach.saveTo(saveFolder);
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public AttchVO downloadAttach(int attchNo) {
		AttchVO attch = attachDAO.selectAttach(attchNo);
		if(attch==null) 
			throw new PKNotFoundException(String.format("%d 번 파일이 없음", attchNo));
		return attch;
	}
	
	@Override
	public ServiceResult modifyBoard(LecqnaVO board) {
		LecqnaVO saved = qnaDAO.selectLecqnaBoard(board.getLecboNo());
		ServiceResult result = null;
		
//		if(authenticate(board,saved)) {
//			String rawPass = board.getLecboPass();
//			board.setLecboPass(passwordEncoder.encode(rawPass));
			int rowcnt = qnaDAO.updateLecqnaBoard(board);
			
			if(rowcnt>0) {
				
				deleteAttach(board);
				uploadAttatches(board);
				
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAIL;
			}
//		}else {
//			result = ServiceResult.INVALIDPASSWORD;
//		}
		return result;

	}
	private void deleteAttach(LecqnaVO board) {
		log.info("board.getDelAttNo() : {}",board.getDelAttNo());
		if(board.getDelAttNo()==null)
			return;
		attachDAO.deleteQnaAttach(board);
	}
//	private boolean authenticate(LecqnaVO input, LecqnaVO saved) {
//		
//		return passwordEncoder.matches(input.getLecboPass(), saved.getLecboPass());
//	}
	
	@Override
	public ServiceResult deleteBoard(LecqnaVO board) {
		LecqnaVO lecboVO = qnaDAO.selectLecqnaBoard(board.getLecboNo());
		ServiceResult result = null;
		
		log.info("lecboVO=>>>{}",lecboVO);
//		List<AttchVO> attachList = new ArrayList<>();
		
		lecboVO.setDelAttNo(board.getDelAttNo());
		
		deleteAttach(lecboVO);
		
		int rowcnt = qnaDAO.deleteLecqnaBoard(board);
		
		return rowcnt>0?ServiceResult.OK:ServiceResult.FAIL;
	}
	@Override
	public List<LecqnaVO> retrieveUnanswerd(Map<String, Object> map) {
		return qnaDAO.selectUnanswerd(map);
		
	}
	@Override
	public List<Map<String, Object>> retrieveMyQuestion(int stuNo) {
	
		return qnaDAO.selectMyQuestion(stuNo);
	}
	

}
