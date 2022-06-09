package kr.or.ddit.calendar.controller;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.calendar.service.CalendarService;
import kr.or.ddit.vo.CalendarVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.security.MemberVOWrapper;

/**
 * @author 이유정
 * @since 2022. 5. 4.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 5. 4.      이유정       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Controller
@RequestMapping("/calendar")
public class CalendarController {
/*
		@RequestMapping("calendarForm.do")
		public String ProcessForm() {
			
			return "calendar/calendarView";
		}*/
		
		@Inject	
		private CalendarService service;
	
		//일정 출력 
		@RequestMapping("calendarForm.do" )
		public String calendarList(Model model, Authentication authentication) {
			MemberVO authMember
			 = ((MemberVOWrapper)authentication.getPrincipal()).getRealUser();
			int acadscUserno = authMember.getUserNo();
			List<CalendarVO> dataList = service.getAllCalendar(acadscUserno);
			model.addAttribute("dataList", dataList);
						
//			return "calendar/calendarView";
			return "jsonView";
		}
		
		
		//일정 insert 
		@RequestMapping("/calendarInsert")
		@ResponseBody
		public int calendarInsert(
				@RequestParam String acadscCont,
				@RequestParam String acadscStart,
				@RequestParam String acadscEnd,
				@RequestParam Integer acadscUserno
		) {
			CalendarVO calendarVo = new CalendarVO();
			calendarVo.setAcadscCont(acadscCont);
			calendarVo.setAcadscStart(acadscStart);
			calendarVo.setAcadscEnd(acadscEnd);
			calendarVo.setAcadscUserno(acadscUserno);
			return service.createCalendar(calendarVo);
		}
		
		
		//글 삭제
		@RequestMapping(value="/delete", method=RequestMethod.POST)
		@ResponseBody
		public int deleteCalendar(@RequestParam Integer acadscNo) {
			System.out.println("#################################딜리트오나요?"+acadscNo);
			
			return service.removeCalendar(acadscNo);
		}
		
		//글 수정 
		@RequestMapping("/update")
		@ResponseBody
		public int updateCalendar(
			@RequestParam Integer acadscNo,
			@RequestParam String acadscCont,
			@RequestParam String acadscStart,
			@RequestParam String acadscEnd
		) {
			CalendarVO calendarVo = new CalendarVO();
			calendarVo.setAcadscNo(acadscNo);
			calendarVo.setAcadscCont(acadscCont);
			calendarVo.setAcadscStart(acadscStart);
			calendarVo.setAcadscEnd(acadscEnd);
			System.out.println("#################################업데이트 오나요?"+calendarVo);
			return service.modifyCalendar(calendarVo);
		}
		
	
	}

