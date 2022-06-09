	package kr.or.ddit.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.board.dao.QnABoardDAO;
import kr.or.ddit.exception.PKNotFoundException;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.QnABoardVO;

@Service
public class QnABoardServiceImpl implements QnABoardService {

	@Inject
	private QnABoardDAO qBoardDAO;
	
	@Override
	public void retrieveBoardList(PagingVO<QnABoardVO> paging) {
		int totalRecord = qBoardDAO.selectTotalQRecord(paging);
		paging.setTotalRecord(totalRecord);
		List<QnABoardVO> dataList = qBoardDAO.selectQBoardList(paging);
		for(QnABoardVO eachVo : dataList) {
			int replyCnt = eachVo.getReplyCnt();
			if(replyCnt == 0) {
				eachVo.setSpanClass("span-answerWait");
				eachVo.setAnswerMark("[답변대기중]");
			}else {
				eachVo.setSpanClass("span-answerComplete");
				eachVo.setAnswerMark("[답변완료]");
			}
		}
		paging.setDataList(dataList);
	}

	@Override
	public QnABoardVO retrieveBoard(String boardNo) {
		QnABoardVO qBoard = qBoardDAO.selectQBoard(boardNo);
		qBoardDAO.incrementHit(boardNo);
		return qBoard;
	}

	@Override
	public void createQBoard(QnABoardVO qBoardVO) {
		qBoardDAO.insertQBoard(qBoardVO);	
	}

	@Override
	public void removeQBoard(String boardNo) {
		qBoardDAO.deleteQBoard(boardNo);
		
	}

	@Override
	public void modifyQBoard(QnABoardVO qBoardVO) {
		qBoardDAO.updateQBoard(qBoardVO);
		
	}
	
 
}
