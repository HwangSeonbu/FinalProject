package kr.or.ddit.pro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pro")
public class ProfessorInsertController {

	@RequestMapping("professorForm.do")
	public String professorForm() {
		
		return "pro/adminProfessorInsert";
	}
}
