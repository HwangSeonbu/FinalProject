package kr.or.ddit.commons.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.commons.dao.FindMyInfoDAO;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author 민진홍
 * @since 2022. 5. 11.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 5. 11.      민진홍       로그인폼으로 이동
 * 2022. 5. 24.      민진홍       아이디찾기
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController{ // POJO
	
	@Inject
	private FindMyInfoDAO findMyInfoDao;
	
	@GetMapping("loginForm.do")
	public String form(){
		
		return "/login/loginForm"; 
	}
	
	@GetMapping("findMyId")
	public String findMyId(){
		
		return "/login/findMyId"; 
	}
	
	@PostMapping("searchId")
	public String searchId(MemberVO vo, RedirectAttributes redirectAttributes){
		MemberVO rsvo = findMyInfoDao.findMyId(vo);
		if(rsvo != null) {
		redirectAttributes.addFlashAttribute("userNo", rsvo.getUserNo());
		return "redirect:/login/findMyId";
		}else {
			redirectAttributes.addFlashAttribute("message","입력한 정보에 맞는 학번(교번)이 존재하지 않습니다.");
			return "redirect:/login/findMyId";
		}
		
	}
	

}














