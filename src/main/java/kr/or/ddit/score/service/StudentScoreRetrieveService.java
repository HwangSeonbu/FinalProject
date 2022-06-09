/**
 * 
 */
package kr.or.ddit.score.service;

import java.util.List;
import java.util.Map;

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

public interface StudentScoreRetrieveService {
	public List<Map<String, Object>> studentAllScoreList(int userNo);
}
