package kr.or.ddit.attendance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/proAttabs")
public class ProfessorAttabsRetrieveController {

	@RequestMapping("attabsList.do")
	public String attabsList() {
		
		return "attendance/professorAttendanceList";
	}
}
