package kr.or.ddit.personalArchive.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("archive")
public class PersonalArchiveController {
	@RequestMapping("go")
	public String cloud() {
		return "archive/storage";
	}
}
