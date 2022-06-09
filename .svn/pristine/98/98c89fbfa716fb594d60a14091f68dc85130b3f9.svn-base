package kr.or.ddit.board.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.board.dao.FreeBoardDAO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.BoLikeVO;
import kr.or.ddit.vo.FreeBoardVO;
import kr.or.ddit.vo.PagingVO;

@Service
public class FreeBoardServiceImpl implements FreeBoardService {
	
	@Inject
	public FreeBoardDAO fBoardDAO;

	@Override
	public void retrieveBoardList(PagingVO<FreeBoardVO> paging) {
		int totalRecord = fBoardDAO.selectTotalFRecord(paging);
		paging.setTotalRecord(totalRecord);
		List<FreeBoardVO> dataList = fBoardDAO.selectFBoardList(paging);
		paging.setDataList(dataList);		
	}

	@Override
	public void createFBoard(FreeBoardVO fBoardVO) {
		fBoardDAO.insertFBoard(fBoardVO);		
	}

	@Override
	public FreeBoardVO retrieveBoard(Map<String, Object> paramMap) {
		FreeBoardVO fBoard = fBoardDAO.selectFBoard(paramMap);
		int readCnt = fBoardDAO.selectFBoardReadCount(paramMap);
		fBoardDAO.incrementHit(String.valueOf(paramMap.get("boardNo")));
		
		if(readCnt==0) {
			fBoardDAO.insertFBoardReadRecord(paramMap);
		}
		
		String myRecommand = fBoard.getMyRecommand();
		if(myRecommand != null && myRecommand.equals("Y")) {
			fBoard.setMyRecommand("span-like");
		}else if(myRecommand != null && myRecommand.equals("Y")) {
			fBoard.setMyRecommand("span-Dislike");
		}else {
			fBoard.setMyRecommand("span-none");
		}
		return fBoard;
	}

	@Override
	public void removeFBoard(String boardNo) {
		fBoardDAO.deleteFBoard(boardNo);
		
	}

	@Override
	public void modifyFBoard(FreeBoardVO fBoardVO) {
		fBoardDAO.updateFBoard(fBoardVO);
		
	}

	@Override
	public ServiceResult retrieveBoLikeMarkUp(BoLikeVO vo) {
		String bolikeYn = vo.getBolikeYn();
		BoLikeVO resultVo = fBoardDAO.selectBoLikeHistory(vo);
		
		if(resultVo == null) {
			fBoardDAO.insertBoLike(vo);
			if(bolikeYn.equals("Y")) {
				fBoardDAO.updateLikePlus(vo);
				return ServiceResult.NEWLIKE;
			}else {
				fBoardDAO.updateDislikePlus(vo);
				return ServiceResult.NEWDISLIKE;
			}
		}else {
			String resultYn = resultVo.getBolikeYn();
			
			if(resultYn.equals("Y")) {
				if(bolikeYn.equals("Y")) {
					return ServiceResult.ALREADYLIKE;
				}else {
					fBoardDAO.updateBoLike(vo);
					fBoardDAO.updateLikeMinusAndDislikePlus(vo);
					return ServiceResult.CHANGETODISLIKE;
				}
			}else {
				if(bolikeYn.equals("Y")) {
					fBoardDAO.updateBoLike(vo);
					fBoardDAO.updateLikePlusAndDislikeMinus(vo);
					return ServiceResult.CHANGETOLIKE;
				}else {
					return ServiceResult.ALREDYDISLIKE;
				}
			}
		}
	}


}
