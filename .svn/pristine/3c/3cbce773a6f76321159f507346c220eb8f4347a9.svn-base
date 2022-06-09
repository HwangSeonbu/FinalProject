package kr.or.ddit.room.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.room.service.AdminRoomSetService;
import kr.or.ddit.vo.CollegeVO;
import kr.or.ddit.vo.DepartmentVO;
import kr.or.ddit.vo.GwanVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/roomSet")
@Slf4j
public class RoomAssignController {

	@Inject
	AdminRoomSetService service;
	
	@RequestMapping("gwanDataList.do")
	public String gwanDataList(Model model) {
		List<GwanVO> gwanList = service.retrieveGwanList();
		model.addAttribute("gwanList", gwanList);
		return "jsonView";
	}
	
	@RequestMapping("collegeForm.do")
	public String collegeForm() {
		return "room/collegeAssignment";
	}
	
	@RequestMapping("collegeDataForm.do")
	public String collegeDataForm(Model model) {
		List<CollegeVO> collegeList = service.retrieveCollegeList();
		model.addAttribute("collegeList", collegeList);
		
		return "jsonView";
	}
	
	@RequestMapping("collegeDepartDataList.do")
	public String collegeDepartDataList(
			Model model, @RequestParam(value="colName") String colName) {
		List<DepartmentVO> departList = service.retrieveCollegeDepartList(colName);
		model.addAttribute("departList", departList);
		
		return "jsonView";
	}
	
	
	@RequestMapping("collegeGwanSet.do")
	public String collegeGwanSet(@ModelAttribute CollegeVO vo, Model model) {
		int result = service.modifyCollegeGwan(vo);
		if(result == 1) {
			model.addAttribute("result", "성공");
		}else {
			model.addAttribute("result", "실패");
		}
		return "jsonView";
	}
	
	@RequestMapping("departFloorSet.do")
	public String departFloorSet(@ModelAttribute DepartmentVO vo, Model model) {
		int result = service.modifyDepartFloor(vo);
		if(result == 1) {
			model.addAttribute("result", "성공");
		}else {
			model.addAttribute("result", "실패");
		}
		return "jsonView";
	}
	
	@RequestMapping("departForm.do")
	public String departForm() {
		
		return "room/departAssignment";
	}
}
