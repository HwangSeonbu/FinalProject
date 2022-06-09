package kr.or.ddit.homework.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.vo.EvaStandardVO;
import kr.or.ddit.vo.LecHomeworkVO;
import lombok.extern.slf4j.Slf4j;
/**homework DAO TEST
 * @author 황선부
 * @since 2022. 5. 23.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 5. 23.      작성자명       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@RunWith(SpringRunner.class)
@ContextHierarchy({
	@ContextConfiguration("file:webapp/WEB-INF/spring/*-context.xml")//상위 컨테이너
//	,@ContextConfiguration("file:webapp/WEB-INF/spring/appServlet/*-context.xml")//하위 컨테이너
})
@WebAppConfiguration
@Slf4j
@Transactional
public class HomeworkDAOTest {
	@Autowired
	private HomeworkDAO dao;
//	@Test
//	public void testHomework() {
//		LecHomeworkVO hw =dao.homework(1);
//		assertNotNull(hw);
//		log.info("hw===>{}",hw);
//	}
	
//	@Test
//	public void testStringDate() {
//		LecHomeworkVO hw =dao.homework(56);
//		assertNotNull(hw);
//		log.info("hw===>{}",hw);
//	}
	
	@Test
	public void testStringDate() {
		LecHomeworkVO hw = new LecHomeworkVO();
		EvaStandardVO eva = new EvaStandardVO();
		EvaStandardVO eva2 = new EvaStandardVO();
		
		eva.setEvaId("70");
		eva.setEvaStd("1");
		eva.setEvaScore(1);
		eva2.setEvaId("71");
		eva2.setEvaStd("2");
		eva2.setEvaScore(2);
		
		List<EvaStandardVO> evaList = new ArrayList<>();
		evaList.add(eva2);
		evaList.add(eva);
		
		hw.setEvaList(evaList);
		
		//mybatis select(procedure-out)임
		dao.updateEva(hw);
		log.info("result ==>{}",hw.getRowcnt());
		log.info("hw===>{}",hw);
	}


}























