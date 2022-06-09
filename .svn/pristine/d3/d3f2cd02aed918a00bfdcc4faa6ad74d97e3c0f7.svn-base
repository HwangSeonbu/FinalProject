package kr.or.ddit.users.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.users.dao.UserDAO;
import kr.or.ddit.utils.PasswordUtils;
import kr.or.ddit.vo.UserVO;
import lombok.AllArgsConstructor;

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
@Service
@AllArgsConstructor
public class AuthenticateServiceImpl implements AuthenticateService {
	@Inject
	private final UserDAO dao;

	@Override
	public Object authenticate(UserVO input) {
		Object retValue = null;
		UserVO saved = dao.selectMemberForAuth(input);
		if(saved!=null) {
			String inputPass = input.getUserPass();
			String savedPass = saved.getUserPass();
			if(PasswordUtils.passwordMatcher(inputPass, savedPass)) {
				retValue = saved;
			}else {
				retValue = ServiceResult.INVALIDPASSWORD;
			}
		}else {
			retValue = ServiceResult.NOTEXIST;
		}
		return retValue;
	}

}
	
	
	

