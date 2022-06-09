package kr.or.ddit.commons.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TempMainDispatcherController {

	@RequestMapping("/tempMain.do")
	public String mainPageDispatcher(HttpSession session) {
		
		String userType = (String) session.getAttribute("userType");
		String mainDispatcherURL = "";
		
		if(userType.equals("학생")) {
			mainDispatcherURL = "forward:/studentMain.do";
		}else if(userType.equals("교수")) {
			mainDispatcherURL = "forward:/professorMain.do";
		}else {
			mainDispatcherURL = "forward:/adminMain.do";
		}
		
		return mainDispatcherURL;
	}
}
