package kr.or.ddit.board.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.board.service.AcadScheduleBoardService;
import kr.or.ddit.vo.AcadscVO;
import kr.or.ddit.vo.CalendarVO;

@Controller
@RequestMapping("/board")
public class CoBoardRetrieveController {

	@Inject
	private AcadScheduleBoardService service;
	
	//목록 출력
	@RequestMapping("acadScheduleList.do")
	//클릭으로 여기로 바로옴. 클릭으로 받아올 부분이 없고 모델로 그냥 jsp에 뿌릴거 저장할것만 넣어놈. 
	public String acadScheduleList(Model model) {		
		System.out.println("###################################################");
		//캘린더vo를 list에 담아서 acadsclist라는 변수이름에 서비스갯얼스케줄을 넣음 
		List<CalendarVO> acadScList = service.getAllSchedule();
		//서비스통해서 xml로 받아온 acadScList 이름에 vo를 싹 다 모델에 담아라  - 모델에 담은건 무조건 jsp로 보내려고 담는거임
		model.addAttribute("acadScList", acadScList);		
		
		return "board/acadScheduleBoardList";
	}
	
	//일정등록 폼 
	@RequestMapping("acadScheduleForm.do")
	public String acadScheduleInsertForm(
			@ModelAttribute("acadscVo") CalendarVO acadscVo 
			, Model model
			){
		return "board/acadScheduleBoardInsert";
	}
	
	//일정등록 저장 
	@RequestMapping(value="/acadScSave.do", method = RequestMethod.POST)
	public String acadSave(
			@ModelAttribute("acadscVo") CalendarVO acadscVo 
			,Model model){
		service.createSchedule(acadscVo);
		return "redirect:/board/acadScheduleList.do";
	}
	
	//상세화면 뷰  
	@RequestMapping("acadScheduleView.do")
	public String acadView(
			@RequestParam(value="who", required=false, defaultValue="") Integer acadscNo
			, Model model
	) {
		System.out.println("###################################헬로?");
		CalendarVO acadscVo = service.retrieveSchedule(acadscNo);
		model.addAttribute("acadscVo", acadscVo);
		return "board/acadScheduleBoardView";
	}
	
	//수정화면 
	@RequestMapping(value="/acadScheduleEdit.do", method = RequestMethod.POST)
	public String acadEdit(CalendarVO acadscVo, Model model) {
		model.addAttribute("acadscVo", service.retrieveSchedule(acadscVo.getAcadscNo()));
		return "board/acadScheduleBoardEdit";
	}
	
	//수정하고 리스트로 가기
	@RequestMapping(value="/saveSchedule", method = RequestMethod.POST)
	public String updateSave(CalendarVO acadscVo, Model model) {
		service.modifySchedule(acadscVo);
		model.addAttribute("acadscVo", acadscVo);
		
		
		return "forward:/board/acadScheduleList.do";
	}
	
	//일정 삭제
	@RequestMapping(value="/deleteSchedule", method = RequestMethod.POST)
	public String deleteSchedule(CalendarVO acadscVo) {
		service.removeSchedule(acadscVo.getAcadscNo());
		
		return "forward:/board/acadScheduleList.do";		
	}
}
