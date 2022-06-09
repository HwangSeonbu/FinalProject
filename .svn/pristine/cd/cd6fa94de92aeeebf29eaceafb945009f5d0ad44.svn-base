package kr.or.ddit.sugang.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.sugang.dao.StudentSugangDAO;
import kr.or.ddit.vo.StudentSugangVO;

@Service
public class StudentSugangServiceImpl implements StudentSugangService {

	@Inject
	StudentSugangDAO dao;
	
	@Override
	public List<StudentSugangVO> retrieveStuSugangTimeTable(Map<String, Integer> stuInfo) {
		// TODO Auto-generated method stub
		return dao.selectStuSugangTimeTableList(stuInfo);
	}

}
