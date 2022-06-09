/**
 * 
 */
package kr.or.ddit.score.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author 작성자명
 * @since 2022. 5. 19.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 5. 19.      민진홍	       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Mapper
public interface StudentScoreRetrieveDAO {
	public List<Map<String, Object>> studentAllScoreList(int userNo);
	public List<Map<String, Object>> studentScoreList(Map<String, Object> map);
}
