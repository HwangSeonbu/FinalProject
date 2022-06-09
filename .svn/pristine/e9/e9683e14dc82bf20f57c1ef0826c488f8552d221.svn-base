package kr.or.ddit.users.dao;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.UserVO;

/**
 * @author 이유정
 * @since 2022. 4. 27.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 4. 27.      이유정       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Mapper
public interface UserDAO {
	/**
	 * 아이디와 비밀번호의 기반의 데이터 조회
	 * @param input
	 * @return 조건에 맞는 사용자가 없으면, null 반환.
	 */
	public UserVO selectMemberForAuth(UserVO input);

}
