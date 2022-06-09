package kr.or.ddit.commons.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.commons.service.MenuService;
import kr.or.ddit.vo.MenuTopVO;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 김재웅
 * @since 2022. 5. 9.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 5. 9.      김재웅       최초작성(유저타입 통합 메인 페이지 이동 컨트롤러)
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Controller
@RequestMapping("/main")
public class MainPageController {

	@Inject
	MenuService service;
	
	@RequestMapping("topMenuList.do")
	public String topMenuList(HttpSession session, @RequestParam String roleId) {
		
//		List<MenuTopVO> topMenuList = service.retrieveTopMenuList(roleId);
		
//		for(MenuTopVO eachVo : topMenuList) {
//			System.out.print(eachVo.getTopmenuId() + "\t");
//			System.out.println(eachVo.getTopmenuText());
//		}
		
		return "";
	}
	
}
