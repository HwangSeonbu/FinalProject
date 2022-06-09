package kr.or.ddit.score.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.score.dao.StudentScoreRetrieveDAO;
import kr.or.ddit.score.service.StudentScoreRetrieveService;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.security.MemberVOWrapper;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@RequestMapping("/stuScore")
public class StudentScoreRetrieveController {

	@Inject
	private StudentScoreRetrieveService service;
	
	@Inject
	private StudentScoreRetrieveDAO dao;
	
	@RequestMapping("myScoreView.do")
	public String myScoreView(@RequestParam("sems") String sems,Authentication authentication, Model model) {
		MemberVO authMember
		 = ((MemberVOWrapper)authentication.getPrincipal()).getRealUser();
		Map<String, Object> info = new HashMap<>();
		info.put("userNo", authMember.getUserNo());
		info.put("sems", sems);
		
		model.addAttribute("scoreList",dao.studentScoreList(info));
		
		return "/score/studentMyScoreView";
	}
	
	/**
	 * 학기별최종성적조회
	 * @return
	 */
	@RequestMapping("totalScoreList.do")
	public String totalScoreList(Authentication authentication, Model model) {
		MemberVO authMember
		 = ((MemberVOWrapper)authentication.getPrincipal()).getRealUser();
		List<Map<String, Object>> scoreList =  service.studentAllScoreList(authMember.getUserNo());
		if(scoreList == null || scoreList.size() == 0) {
			return "score/studentTotalScoreView";
		}
		Map<String, Object> totalMap = scoreList.get(scoreList.size()-1);
		model.addAttribute("totalMap",totalMap);
		scoreList.remove(scoreList.size()-1);
		model.addAttribute("scoreList",scoreList);
		return "score/studentTotalScoreView";
	}
	
}
