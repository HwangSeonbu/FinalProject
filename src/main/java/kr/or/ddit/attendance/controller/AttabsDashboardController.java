package kr.or.ddit.attendance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashAttabs")
public class AttabsDashboardController {
	
	@RequestMapping("dashboardView.do")
	public String dashboardView() {
		
		return "attendance/studentAttabsDashboard";
	}
}
