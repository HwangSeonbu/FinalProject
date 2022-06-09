package kr.or.ddit.lecboard.dao;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.counsel.dao.CounselDAOTest;
import kr.or.ddit.lecqna.dao.LecQnaDAO;
import kr.or.ddit.lecqna.service.LecQnaService;
import kr.or.ddit.vo.ClassVO;
import kr.or.ddit.vo.LecNoticeBoardVO;
import kr.or.ddit.vo.LecqnaVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
@RunWith(SpringRunner.class)
@ContextHierarchy({
	@ContextConfiguration("file:webapp/WEB-INF/spring/*-context.xml")//상위 컨테이너
	,@ContextConfiguration("file:webapp/WEB-INF/spring/appServlet/servlet-context.xml")//하위 컨테이너
})
@WebAppConfiguration
@Slf4j
@Transactional//테스트과정의 트랜잭션은 자동 롤백!!중요!!//단위테스트간 서로 영향을 주지 않도록 트랜잭션 관리함.테스트하고 svn에 커밋해야함!!
public class LecBoardDAOTest {
	@Autowired
	private LecBoardDAO dao;
	@Autowired
	private LecQnaDAO qnaDAO;
	
	@Test
	public void testSelectLectureListOfStu() {
		int stuNo=1;
		
		List<Map<String, Object>> lecList = dao.selectLectureListOfStu(stuNo);
		assertNotNull(lecList);
	}
	@Test
	public void testSelectLecUserList() {
		int stuNo=1;
		ClassVO classVO = new ClassVO();
		classVO.setUserNo(stuNo);
		
		List<UserVO> lecList = dao.selectLecUserList(classVO);
		assertNotNull(lecList);
	}
	@Test
	public void testSelectAuthenLecUserNo() {
		int stuNo=1;
		ClassVO classVO = new ClassVO();
		classVO.setUserNo(stuNo);
		
		int result = dao.selectAuthenLecUserNo(classVO);
		assertNotNull(result);
	}
	@Test
	public void testSelectTotalLecBoardRecord() {
		LecNoticeBoardVO lecBoard = new LecNoticeBoardVO();
		lecBoard.setLecSems(202201);
		lecBoard.setLecId("1");
		lecBoard.setUserNo(11);
		
		PagingVO<LecNoticeBoardVO> paging = new PagingVO<>();
		paging.setDetailCondition(lecBoard);
		
		 
		int result = dao.selectTotalLecBoardRecord(paging);
		log.info("result 값: {}",result);
		assertNotNull(result);
	}
	
	@Test
	public void testSelectLecBoard() {
		LecNoticeBoardVO lecBoard = new LecNoticeBoardVO();
		lecBoard.setLecSems(202201);
		lecBoard.setLecId("1");
		lecBoard.setUserNo(11);
		
		PagingVO<LecNoticeBoardVO> paging = new PagingVO<>(5, 3);
		paging.setDetailCondition(lecBoard);
		paging.setCurrentPage(2);

		List<LecNoticeBoardVO> bList = dao.selectLecBoard(paging);
		log.info("bList 값: {}",bList);
		assertNotNull(bList);
	}
	
	@Test
	public void testLecqnaList() {
		
		PagingVO<LecqnaVO> paging = new PagingVO<>(5, 3);
		paging.setLecId("28");
		int result = qnaDAO.selectTotalLecqnaRecord(paging);
		log.info("result ==========>{}",result);
	}
	

}
