package kr.or.ddit.vo.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import kr.or.ddit.vo.MemberVO;

public class MemberVOWrapper extends User{

	private MemberVO realUser;
	public MemberVOWrapper(
		MemberVO realUser
		, boolean enabled, boolean accountNonExpired,
		boolean credentialsNonExpired, boolean accountNonLocked,
		Collection<? extends GrantedAuthority> authorities
	) {
		super(
			  realUser.getUserNo()+"", realUser.getUserPass()
			  , enabled, accountNonExpired
			  , credentialsNonExpired, accountNonLocked
			  , authorities
		);
		this.realUser = realUser;
	}

	public MemberVOWrapper(MemberVO realUser) {
		this(realUser
			, true, true
			, true, true
			, AuthorityUtils.createAuthorityList(realUser.getMemRole()));
	}
	
	public MemberVO getRealUser() {
		return realUser;
	}
}
