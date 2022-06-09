package kr.or.ddit.homework.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.homework.service.HomeworkService;
import kr.or.ddit.lecboard.service.LecBoardService;
import kr.or.ddit.vo.ClassVO;
import kr.or.ddit.vo.LecHomeworkVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SimpleSearchVO;
import lombok.extern.slf4j.Slf4j;
/**과제 게시판 목록 컨트롤러
 * @author 황선부
 * @since 2022. 5. 24.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 5. 24.      황선부       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Slf4j
@Controller
public class HomeWorkListController {
	@Autowired
	private HomeworkService service;
	@Inject
	private LecBoardService lecService;
	@RequestMapping("/homework")
	public String homeWorkList(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			,@ModelAttribute("simpleCondition") SimpleSearchVO simpleCondition
			,Model model
			,HttpSession session
			){
		ClassVO cls = (ClassVO) session.getAttribute("classVO");
		log.info("cls.getLecId{}",cls.getLecId());
		LecHomeworkVO detailCondition = new LecHomeworkVO();
		detailCondition.setLecId(cls.getLecId());
		
		PagingVO<LecHomeworkVO>paging = new PagingVO<>(10, 5);
		paging.setCurrentPage(currentPage);
		paging.setSimpleCondition(simpleCondition);
		paging.setDetailCondition(detailCondition);
		
		List<LecHomeworkVO>hwList = service.retrieveHomeworkList(paging);
		ClassVO classVO = (ClassVO) session.getAttribute("classVO");
		model.addAttribute("lecId", classVO.getLecId());
		model.addAttribute("lecSems",classVO.getLecSems());
		
		model.addAttribute("board", hwList);
		
		model.addAttribute("paging", paging);
		model.addAttribute("lecMap", lecService.retrieveOneLec(classVO.getLecId()));
		return "homework/hwboard";
	}
	@RequestMapping("boardOne")
	public String homeWorkOne() {
	
		return "homework/hwboard";
	}
}
