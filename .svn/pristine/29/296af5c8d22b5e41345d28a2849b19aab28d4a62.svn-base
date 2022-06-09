package kr.or.ddit.lecqna.dao;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.counsel.dao.CounselDAOTest;
import lombok.extern.slf4j.Slf4j;
@RunWith(SpringRunner.class)
@ContextHierarchy({
	@ContextConfiguration("file:webapp/WEB-INF/spring/*-context.xml")//상위 컨테이너
	,@ContextConfiguration("file:webapp/WEB-INF/spring/appServlet/servlet-context.xml")//하위 컨테이너
})
@WebAppConfiguration
@Slf4j
@Transactional
public class LecQnaDAOTest {
	
	@Inject
	private PasswordEncoder passwordEncoder;
	@Test
	public void testSelectTotalLecqnaRecord() {
		String rawPassword="1231241241241241241";
		System.out.println(passwordEncoder.encode(rawPassword));

	}

}
