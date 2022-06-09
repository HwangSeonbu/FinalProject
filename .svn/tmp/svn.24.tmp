package kr.or.ddit.security;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class customfilter2 extends AbstractAuthenticationProcessingFilter {

	    public customfilter2(String defaultFilterProcessesUrl) {
	        super(defaultFilterProcessesUrl);
	        log.info("커스텀필터 테스트입니다!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	        
	    }

	    @Override
	    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
	        String username = request.getParameter("username");
	        String password = request.getParameter("password");

	        return getAuthenticationManager()
	                .authenticate(new UsernamePasswordAuthenticationToken(username, password));
	    }
	}