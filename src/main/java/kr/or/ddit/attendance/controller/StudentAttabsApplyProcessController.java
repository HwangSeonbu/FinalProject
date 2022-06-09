package kr.or.ddit.attendance.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.RequestContextHolder;

import kr.or.ddit.grade.service.GradeService;
import kr.or.ddit.objection.service.ObjectionService;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.SemsVO;
import kr.or.ddit.vo.security.MemberVOWrapper;
import lombok.extern.slf4j.Slf4j;

/**
 * 학생이 출석인정신청내역을 조회하고, 신청하는 컨트롤러
 * 
 * @author 주창규
 * @since 2022. 5. 23.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * 
 *      <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 5. 23.      주창규       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 *      </pre>
 */
@Controller
@Slf4j
@RequestMapping("/applyAttabs")
public class StudentAttabsApplyProcessController {

	@Inject
	private ObjectionService oService;

	@Inject
	private GradeService gService;

	
	//단순히 페이지 이동
	@RequestMapping("attabsApplyForm.do")
	public String semsdataSelect(
	){
		return "attendance/studentAttabsApplyForm";
	}
	
	// 학기를 선택 후 해당 정보를 리턴
	@RequestMapping("semsdataForm")
	public String gradeUpdate(
			Model model
			,Authentication authentication
	){
		MemberVO authMember = ((MemberVOWrapper) authentication.getPrincipal()).getRealUser();
		int userNo = authMember.getUserNo();	//학번
		
		List<String> semsdata = gService.retrieveSemsdata();	//학기 
		
		model.addAttribute("semsdata", semsdata);
		model.addAttribute("userNo", userNo);
		return "jsonView";
	}
	
//	 학생이  출석인정을 신청
	@RequestMapping("attabsApplyForm")
	public String attabsApplyForm(
			Model model
			, Authentication authentication
			, @RequestParam(value ="semsdata") String lecSems
			, @RequestParam(value ="userNo") String userNo
	){
		Map<String, Object> map = new HashMap<>();

		map.put("userNo", userNo);
		map.put("lecSems", lecSems); 
		log.info("=========userNo Test{}", userNo);
		log.info("=========lecSems Test{}", lecSems);
		
		List<Map<String, Object>> studentAttendanceObjectionList = oService.retrieveStudentAttendanceObjection(map);


		log.info("=========studentAttendanceObjectionList Test{}", studentAttendanceObjectionList);

		model.addAttribute("studentAttendanceObjectionList", studentAttendanceObjectionList);

		return "jsonView";
	}

	

//	학생의 출석인정신청 이력을 처리결과와 함께 조회
	@RequestMapping("attabsApplyList.do")
	public String attabsApplyList() {

		return "attendance/studentAttabsApplyList";
	}
}
