package kr.or.ddit.room.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.core.config.plugins.visitors.PluginNodeVisitor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.ast.service.AccessPeriodService;
import kr.or.ddit.enumpkg.AccessActionEnum;
import kr.or.ddit.room.service.ProfessorRoomSetService;
import kr.or.ddit.vo.DepartLectureAssignVO;
import kr.or.ddit.vo.DepartRoomSetVO;
import kr.or.ddit.vo.DepartmentVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PageAccessVO;
import kr.or.ddit.vo.PlanVO;
import kr.or.ddit.vo.SemsVO;
import kr.or.ddit.vo.security.MemberVOWrapper;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/roomDetailSet")
@Slf4j
public class ProfessorRoomAssignController {

	@Inject
	ProfessorRoomSetService service;
	
	@Inject
	AccessPeriodService accessService;
	
	@RequestMapping("departLectureDataList.do")
	public String departLectureDataList(Model model, Authentication authentication
			, HttpSession session) {
		
		MemberVO loginProVo = ((MemberVOWrapper) authentication.getPrincipal()).getRealUser();
		SemsVO semsVo = (SemsVO) session.getAttribute("semsVo");
		PlanVO vo = new PlanVO();
		
		vo.setPlanSems(semsVo.getNextSems());
		vo.setDeptId(loginProVo.getUserDepartment());
		
		List<DepartLectureAssignVO> departLecList = service.retrieveDepartLecList(vo);
		
		model.addAttribute("departLecList", departLecList);
		
		return "jsonView";
	}
	
	//기본 페이지 단순이동
	@RequestMapping("detailRoomForm.do")
	public String detailRoomForm(Model model) {
		PageAccessVO accessVo = accessService.retrieveAccessPeriod(
				AccessActionEnum.ASSIGNLECTUREROOM);
		
		if(accessVo.isAccess()) {
			return "room/professorSetDetailRoom";
		}else {
			model.addAttribute("action", accessVo.getAction());
			model.addAttribute("accessPeriod", accessVo.getAccessPeriod());
			
			return "temp/notPeriodPage";
		}
	}
	
	//최초 페이지에 대한 학과정보 데이터 세팅
	@RequestMapping("departData.do")
	public String departData(Model model, Authentication authentication) {
		MemberVO loginProVo = ((MemberVOWrapper) authentication.getPrincipal()).getRealUser();
		DepartmentVO department = service.retrieveDepartOne(loginProVo.getUserDepartment());
		
		model.addAttribute("department", department);
		
		return "jsonView";
	}
	
	@RequestMapping("departRoomSet.do")
	public String departRoomSet(Model model, @ModelAttribute DepartRoomSetVO vo) {
		
		service.modifyDepartRoomAssign(vo);
		
		return "jsonView";
	}
	
	
	
	@RequestMapping("roomResultList.do")
	public String roomResultList(Model model, Authentication authentication) {
		PageAccessVO accessVo = accessService.retrieveAccessPeriod(
				AccessActionEnum.ASSIGNLECTUREROOM);
		
		if(accessVo.isAccess()) {
			MemberVO loginProVo = ((MemberVOWrapper) authentication.getPrincipal()).getRealUser();
			DepartmentVO department = service.retrieveDepartOne(loginProVo.getUserDepartment());
			model.addAttribute("department", department);
			return "room/professorEnrollRoom";
		}else {
			model.addAttribute("action", accessVo.getAction());
			model.addAttribute("accessPeriod", accessVo.getAccessPeriod());
			
			return "temp/notPeriodPage";
		}
		
	}
	
	@RequestMapping("roomResultDataList.do")
	public String roomResultDataList(Model model, Authentication authentication
			, HttpSession session) {
		MemberVO loginProVo = ((MemberVOWrapper) authentication.getPrincipal()).getRealUser();
		SemsVO semsVo = (SemsVO) session.getAttribute("semsVo");
		PlanVO vo = new PlanVO();
		vo.setDeptId(loginProVo.getUserDepartment());
		vo.setPlanSems(semsVo.getNextSems());
		
		List<DepartLectureAssignVO> targetList = service.retrieveSubmitTargetList(vo);
		
		model.addAttribute("targetList", targetList);
		
		return "jsonView";
	}
	
	@RequestMapping("assignDuplicationCheck.do")
	public String assignDuplicationCheck(Model model
			, @RequestParam Map<String, Object> parameters) {
		
		String json = parameters.get("planNoList").toString();
		String jsonReplace = json.replaceAll("&quot;", "\"");
		ObjectMapper mapper = new ObjectMapper();
		List<Map<String, Object>> planNoList = new ArrayList<>();
		try {
			planNoList =
			mapper.readValue(jsonReplace, new TypeReference<ArrayList<Map<String, Object>>>() {});
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		List<Integer> planNos = new ArrayList<>();
		for(Map<String, Object> eachMap : planNoList) {
			int planNo = Integer.parseInt(String.valueOf(eachMap.get("planNo")));
			planNos.add(planNo);
		}
		
		List<DepartLectureAssignVO> dupleList = service.retrieveDuplicationResult(planNos);
//		if(dupleList == null || dupleList.size() == 0) {
//		}else {
//			//중복있음
//			for(DepartLectureAssignVO eachVo : dupleList) {
//				System.out.println("#############################");
//				System.out.println(eachVo.toString());
//			}
//		}
		model.addAttribute("dupleList", dupleList);
		
		return "jsonView";
	}
	
	@RequestMapping("assignSubmitProcess.do")
	public String assignSubmitProcess(Model model
			, @RequestParam Map<String, Object> parameters) {
		
		String json = parameters.get("planNoList").toString();
		String jsonReplace = json.replaceAll("&quot;", "\"");
		ObjectMapper mapper = new ObjectMapper();
		List<Map<String, Object>> planNoList = new ArrayList<>();
		try {
			planNoList =
			mapper.readValue(jsonReplace, new TypeReference<ArrayList<Map<String, Object>>>() {});
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		List<Integer> planNos = new ArrayList<>();
		for(Map<String, Object> eachMap : planNoList) {
			int planNo = Integer.parseInt(String.valueOf(eachMap.get("planNo")));
			planNos.add(planNo);
		}
		
		int resCnt = service.modifyAssignToSubmit(planNoList);
		
		return "jsonView";
	}
}




