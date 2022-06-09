package kr.or.ddit.scholarship.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.enr.service.StudentEnrollService;
import kr.or.ddit.vo.StudentEnrollVO;
import kr.or.ddit.vo.security.MemberVOWrapper;
/**
 * 
 * @author 민진홍
 * @since 2022. 5. 10.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 5. 10.      민진홍 		최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Controller
@RequestMapping("/scholarship")
public class StudentScholarshipViewController {
	
	@Inject
	private StudentEnrollService service; //enr 패키지의 service임 사용법이 같아서 씀
	
	@RequestMapping("scholarInfo.do")
	public String scholarInfo() {
	
		return "scholarship/studentScholarInfoView";
	}
	
	/**
	 * 장학생이력조회페이지
	 * @return
	 */
	@RequestMapping("scholarshipList.do")
	public String scholarshipList(Authentication authentication, Model model) {
		String userNo1 = ((MemberVOWrapper)authentication.getPrincipal()).getUsername();
		int userNo = Integer.parseInt(userNo1);
		List<StudentEnrollVO> list = service.studentEnrollListView(userNo);
		model.addAttribute("enrollList",list);
		
		return "scholarship/studentScholarList";
	}
	
	
}
