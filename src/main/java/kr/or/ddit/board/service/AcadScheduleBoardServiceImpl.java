package kr.or.ddit.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.board.dao.AcadScheduleBoardDAO;
import kr.or.ddit.exception.PKNotFoundException;
import kr.or.ddit.vo.CalendarVO;

/**
 * @author 이유정
 * @since 2022. 5. 6.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 5. 6.      이유정       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Service
public class AcadScheduleBoardServiceImpl implements AcadScheduleBoardService{
	
	@Inject
	private AcadScheduleBoardDAO dao;


	@Override
	public List<CalendarVO> getAllSchedule() {	
		
		List<CalendarVO> dataList = dao.acadScheduleBoardList();
		
		for(CalendarVO eachVo : dataList) {
			String beforeDate = eachVo.getAcadscStart();
			System.out.println(beforeDate);
			String temp = beforeDate.substring(5, 7);
			int month = Integer.parseInt(temp);
			System.out.println(">>>>>>>>>>>>>>>>>>>> " + month );
			
			eachVo.setMonth(month);
		}	
		return dataList;		
	}


	@Override
	public CalendarVO retrieveSchedule(Integer acadscNo) {
		CalendarVO acadSc = dao.selectSchedule(acadscNo);
		if(acadSc==null)
			throw new PKNotFoundException(String.format("%d번 글이 없음", acadSc));
		return acadSc;
	}


	@Override
	public void createSchedule(CalendarVO acadscVo) {
		dao.insertAcadSchedule(acadscVo);
	}


	@Override
	public void modifySchedule(CalendarVO acadscVo) {
		dao.updateSchedule(acadscVo);
		
	}


	@Override
	public void removeSchedule(Integer acadscNo) {
		dao.deleteSchedule(acadscNo);
		
	}	
	
	

}
