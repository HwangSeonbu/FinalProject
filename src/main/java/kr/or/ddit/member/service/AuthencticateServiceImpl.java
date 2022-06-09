package kr.or.ddit.member.service;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.utils.PasswordUtils;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.security.MemberVOWrapper;

@Service
public class AuthencticateServiceImpl implements UserDetailsService{
	@Inject
	private MemberDAO memberDAO;
	
	private MessageSourceAccessor accessor;

	@Inject
	public void setMessageSource(MessageSource messageSource){
		this.accessor = new MessageSourceAccessor(messageSource);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberVO input = new MemberVO();
		input.setUserNo(Integer.parseInt(username));
		MemberVO realUser = memberDAO.selectMemberForAuth(input);
		if(realUser==null)
			throw new UsernameNotFoundException(accessor.getMessage("JdbcDaoImpl.notFound", new Object[] {username}));
		return new MemberVOWrapper(realUser);
	}
	
	public Object authenticate(MemberVO input, @Autowired PasswordEncoder passwordEncoder) {
		Object retValue = null;
		MemberVO saved = memberDAO.selectMemberForAuth(input);
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
