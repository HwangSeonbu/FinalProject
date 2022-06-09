package kr.or.ddit.sugang.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.sugang.dao.LearningLecturesDAO;
import kr.or.ddit.vo.ClassVO;

/**
 * 
 * @author 민진홍	
 * @since 2022. 5. 2.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 5. 2.      작성자명       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Service
public class LearningLecturesServiceImpl implements LearningLecturesService {

	@Inject
	private LearningLecturesDAO dao;
	
	@Override
	public List<Map<String,Object>> selectLearningLecturesList(int userNo) {
		
		return dao.selectLearningLecturesList(userNo);
	}

}
