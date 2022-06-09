package kr.or.ddit.sugang.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.sugang.service.LearningLecturesService;
import kr.or.ddit.vo.ClassVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.security.MemberVOWrapper;
import lombok.extern.slf4j.Slf4j;
/**
 * 
 * @author 민진홍
 * @since 2022. 4. 29.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 4. 29.      작성자명       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Slf4j
@Controller
@RequestMapping("/sugang")
public class LearningLectureViewController {
	
	@Inject
	private LearningLecturesService service;
	
	@GetMapping("sugangList.do")
	public String enrollView() {
			return "sugang/learningLectureView";
	}
	
	@GetMapping(value="lecturesList", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<Map<String,Object>> lecturesList(Authentication authentication) {
		MemberVO authMember = ((MemberVOWrapper)authentication.getPrincipal()).getRealUser();
		return service.selectLearningLecturesList(authMember.getUserNo());
		
	}
	
}
