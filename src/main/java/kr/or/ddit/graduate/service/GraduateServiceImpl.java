package kr.or.ddit.graduate.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;


import kr.or.ddit.exception.PKNotFoundException;
import kr.or.ddit.graduate.dao.GraduateDAO;
import kr.or.ddit.vo.GraduateVO;
import kr.or.ddit.vo.PagingVO;

/**
 * @author 고성식
 * @since 2022. 5. 11.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 5. 11.   고성식		       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Service
public class GraduateServiceImpl implements GraduateService{
	
	@Inject
	private GraduateDAO dao;

	@Override
	public List<GraduateVO> retrieveStudentList(PagingVO<GraduateVO> paging) {
		int totalRecord = dao.selectTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		List<GraduateVO> dataList = dao.selectGraduateList(paging);
		paging.setDataList(dataList);
		return dataList;
	}

	@Override
	public GraduateVO retriveStudent(Integer userNo) throws PKNotFoundException {
		GraduateVO student = dao.selectStudent(userNo);
		if(student==null)
			throw new PKNotFoundException(userNo+"에 해당하는 회원이 없음.");
		return student;
	}

	@Override
	public List<GraduateVO> retreiveTargetStudentList(PagingVO<GraduateVO> paging) {
		int totalRecord = dao.selelctTargetTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		List<GraduateVO> dataList = dao.selectGraduateTargetList(paging);
		paging.setDataList(dataList);
		return dataList;
	}


	@Override
	public int updateGraduateSts(List<GraduateVO> list) throws Exception {
		int result = 0;
		
		for(GraduateVO vo : list) {
			vo.setStuCode("C104");
			result += dao.updateGraduateSts(vo);
		}
		
		return result;
	}
}
