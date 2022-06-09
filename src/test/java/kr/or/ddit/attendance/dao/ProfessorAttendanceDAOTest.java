package kr.or.ddit.attendance.dao;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import kr.or.ddit.vo.LectureJ;
import lombok.extern.slf4j.Slf4j;
@RunWith(SpringRunner.class)
@ContextConfiguration("file:webapp/WEB-INF/spring/*-context.xml")
@WebAppConfiguration
@Slf4j
public class ProfessorAttendanceDAOTest {
	
	@Inject
	ProfessorAttendanceDAO pDao;
	
	@Test
	public void testSelectAttendanceLecture() {
		
		Map<String, Object> map = new HashMap<>();
		map.put("lecId", "12");
		map.put("lecSems", 202201);
		map.put("startDate", 20220307);
		
		List<LectureJ> lecture= pDao.selectAttendanceLectureExcel(map);
		log.info("=========================lecture=={}",lecture);
		
		for(int i = 0; i < lecture.size(); i++) {
			lecture.get(i).getStudents().forEach((std)->{
				log.info("======{}",std);
				std.getAttList().forEach((att)->{
					log.info("========================{}",att);
				});
			}) ;
		}
	}

}
