package kr.or.ddit.sugang.dao;
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

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LearningLecturesDAO {
	public List<Map<String,Object>> selectLearningLecturesList(int userNo);
}
