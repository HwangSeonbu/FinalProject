/**
 * 
 */
package kr.or.ddit.commons.service;

import javax.inject.Inject;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.or.ddit.commons.dao.MyPageDAO;
import kr.or.ddit.vo.MemberVO;

/**
 * @author 민진홍
 * @since 2022. 5. 23.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 5. 23.      민진홍       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Service
public class MyPageServiceImpl implements MyPageService {
	
	@Inject
	private MyPageDAO dao;
	
	@Inject
	private PasswordEncoder passwordEncoder;
	
	@Override
	public int editMyInfo(MemberVO vo) {
		
		String encodedPass = passwordEncoder.encode(vo.getUserPass());
		vo.setUserPass(encodedPass);
		return dao.editMyInfo(vo);
	}
}
