package kr.or.ddit.commons.service;

import java.util.List;

import kr.or.ddit.vo.NoticeBoardVO;
import kr.or.ddit.vo.SemsVO;

public interface MainInformationService {
	
	public SemsVO retrieveSemsData();
	
	public List<NoticeBoardVO> retrieveMyNoticeList(int userNo);
}
