package kr.or.ddit.commons.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.commons.dao.MainInformationDAO;
import kr.or.ddit.vo.NoticeBoardVO;
import kr.or.ddit.vo.SemsVO;

@Service
public class MainInformationServiceImpl implements MainInformationService {

	@Inject
	MainInformationDAO dao;
	
	@Override
	public SemsVO retrieveSemsData() {
		SemsVO semsVo = dao.selectSemsData();
		return semsVo;
	}

	@Override
	public List<NoticeBoardVO> retrieveMyNoticeList(int userNo) {
		return dao.selectMainNoticeList(userNo);
	}
}
