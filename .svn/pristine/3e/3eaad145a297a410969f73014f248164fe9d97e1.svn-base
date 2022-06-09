package kr.or.ddit.sugang.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.sugang.service.StudentSugangService;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.SemsVO;
import kr.or.ddit.vo.StudentSugangVO;
import kr.or.ddit.vo.security.MemberVOWrapper;

@Controller
@RequestMapping("/sugang")
public class StudentTimeTableController {

	@Inject
	StudentSugangService service;
	
	@RequestMapping("timeTableView.do")
	public String timeTableView() {
		
		return "sugang/studentLecTimeTable";
	}
	
	
	@RequestMapping("timeTableDataView.do")
	public String timeTableDataView(Model model, Authentication authentication, HttpSession session) {
		SemsVO semsVo = (SemsVO) session.getAttribute("semsVo");
		MemberVO member = ((MemberVOWrapper)authentication.getPrincipal()).getRealUser();
		int userNo = member.getUserNo();
		
		Map<String, Integer> stuInfo = new HashMap<>();
		stuInfo.put("stuNo", userNo);
		stuInfo.put("thisSems", semsVo.getThisSems());
		
		List<StudentSugangVO> stuSugangList = service.retrieveStuSugangTimeTable(stuInfo);
		
		model.addAttribute("stuSugangList", stuSugangList);
		return "jsonView";
	}
	
	
	@RequestMapping("preTimeTableView.do")
	public String preTimeTableView() {
		
		return "sugang/studentLecPreTimeTable";
	}
}
