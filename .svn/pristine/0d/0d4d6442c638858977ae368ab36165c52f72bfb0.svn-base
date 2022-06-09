package kr.or.ddit.set.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.set.service.PeriodService;
import kr.or.ddit.vo.AcadscVO;
import kr.or.ddit.vo.SemsDataVO;
import kr.or.ddit.vo.SemsVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/set")
@Slf4j
public class SetPeriodController {

	@Inject
	PeriodService service;
	
	@RequestMapping("setAstPeriodForm.do")
	public String setAstPeriodForm() {
		
		return "set/adminSetAstPeriod";
	}
	
	/**
	 * 시험성적입력기간 기본 페이지  이동(현재 개강일 종강일 포함)
	 * @return
	 */
	@RequestMapping("setCrdtPeriodForm.do")
	public String setCrdtPeriodForm(Model model, HttpSession session) {
		SemsVO semsVo = (SemsVO) session.getAttribute("semsVo");
		int prevSems = semsVo.getPrevSems();
		int thisSems = semsVo.getThisSems();
		int nextSems = semsVo.getNextSems();
		
		List<SemsDataVO> semsList = service.retrieveBaseSemsDataList();
		model.addAttribute("semsList", semsList);
		model.addAttribute("thisSems", thisSems);
		
		return "set/adminSetCrdtPeriod";
	}
	
	/**
	 * 기본페이지에 디폴트 기간 데이터 뿌리기
	 * @param model
	 * @param pickSemsNo
	 * @return
	 */
	@RequestMapping("periodData.do")
	public String periodData(Model model, @RequestParam int pickSemsNo) {
		List<AcadscVO> acadscList = service.retrievePeriodDataList(pickSemsNo);
		
		model.addAttribute("acadscList", acadscList);
		
		return "jsonView";
	}
	
	@RequestMapping("periodSet.do")
	public String periodSet(Model model, @ModelAttribute AcadscVO paramVo) {
		
		service.modifyOrCreatePeriod(paramVo);
		
		return "jsonView";
	}
}





