package kr.or.ddit.lecboard.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.springframework.security.core.Authentication;
import org.springframework.web.filter.GenericFilterBean;

/**
 * Servlet Filter implementation class LecBoardStuFilter
 */
@WebFilter("/LecBoardStuFilter")
public class LecBoardStuFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
	
		
		
	}


   
}
