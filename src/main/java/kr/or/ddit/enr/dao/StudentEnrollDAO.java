/**
 * 
 */
package kr.or.ddit.enr.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.ScholarshipVO;
import kr.or.ddit.vo.StudentEnrollVO;

/**
 * @author 민진홍
 * @since 2022. 5. 6.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 5. 6.      민진홍	       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Mapper
public interface StudentEnrollDAO {
	public StudentEnrollVO studentEnrollView(int userNo);
	public ScholarshipVO studentSCHView(String schNo);
	public List<StudentEnrollVO> studentEnrollListView(int userNo);
}
