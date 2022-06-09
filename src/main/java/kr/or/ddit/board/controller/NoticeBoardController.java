package kr.or.ddit.board.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.board.service.NoticeBoardService;
import kr.or.ddit.vo.AttchVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.NoticeBoardVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SimpleSearchVO;
import kr.or.ddit.vo.security.MemberVOWrapper;

/**
 * @author 이유정
 * @since 2022. 4. 28.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 4. 28.      이유정       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Controller
@RequestMapping("/board")
public class NoticeBoardController {
	
	@Inject	
	private NoticeBoardService service;
		
		//게시판 목록 출력 
		@RequestMapping("/noticeListView.do")
		public String listHandler(
//				1개의 파라마타 받기 위해 사용 (기본적으로 해당 파라미터가 반드시 전송)/꼭 필요한 변수 아니면 false값으로 설정 
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, @ModelAttribute("simpleCondition") SimpleSearchVO simpleCondition
			, Model model, Authentication authentication
		){
			MemberVO authMember = ((MemberVOWrapper)authentication.getPrincipal()).getRealUser();
			int userNo = authMember.getUserNo();
			PagingVO<NoticeBoardVO> paging = new PagingVO<>(10,5);
			paging.setCurrentPage(currentPage);
			paging.setSimpleCondition(simpleCondition);
			paging.setUserNo(userNo);
			
			service.retrieveNBoardList(paging);
			
			model.addAttribute("paging", paging);
			
			return "board/noticeBoardList";
		}	
		
		//게시판 상세보기 
		@RequestMapping("noticeBoardView.do")
		public String detailViewHandler(
			@RequestParam(value="who", required=false, defaultValue="") int noticeNo 
				, Model model, Authentication authentication				
		) {
			MemberVO authMember = ((MemberVOWrapper)authentication.getPrincipal()).getRealUser();
			int userNo = authMember.getUserNo();
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("noticeNo", noticeNo);
			paramMap.put("userNo", userNo);
			
			NoticeBoardVO nBoard = service.retrieveBoard(paramMap);
			model.addAttribute("nBoard", nBoard);
			
			return "board/noticeBoardView";
			
		}
		
		//노티스보드넘버, 게시글 넘버 받아서 다운로드 하기 
		@GetMapping("{noticeNo}/attch/{attchNo}")
		public String download(@PathVariable Integer attchNo, Model model) {
			AttchVO attch = service.downloadAttch(attchNo);
			model.addAttribute("attch", attch);
			return "boardDownloadView";
		}
		
		//삭제
		@RequestMapping(value = "/noticeListView", method=RequestMethod.POST)
		public String nBoardDelete(NoticeBoardVO nBoardVO){
			service.removeNBoard(nBoardVO.getNoticeNo());	

			return "redirect:/board/noticeListView.do";
		}
		
		// 게시판 수정뷰
		@RequestMapping(value = "/noticeEditView", method = RequestMethod.POST)
		public String updateView(NoticeBoardVO nBoardVO, Model model) throws Exception{		
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("noticeNo", nBoardVO.getNoticeNo());
			model.addAttribute("nBoardVO", service.retrieveBoard(paramMap));	
			return "board/noticeBoardEdit";
		}
		
		// 게시판 수정
		@RequestMapping(value = "/noticeBoardView", method = RequestMethod.POST)
		public String update(NoticeBoardVO nBoardVO, Model model) throws Exception{
			service.modifyNBoard(nBoardVO);
			model.addAttribute("who", nBoardVO.getNoticeNo());
			
			return "redirect:/board/noticeBoardView.do";
		}
		
		// 게시판 글 폼으로 가는길 
		@RequestMapping("/BoardWriteForm")
		public String insertForm(
				@ModelAttribute("nBoard") NoticeBoardVO nBoard,
				Authentication authentication,
				Model model
		) {
			MemberVO authMember = ((MemberVOWrapper)authentication.getPrincipal()).getRealUser();
			int userNo = authMember.getUserNo();
			model.addAttribute("userNo", userNo);
			return "board/noticeBoardForm";
		}
		
		//글 저장 
		@RequestMapping(value="/noticeSave", method = RequestMethod.POST)
		public String saveForm(
				@ModelAttribute("nBoard") NoticeBoardVO nBoardVO
				, Model model){			
				service.createNBoard(nBoardVO);
				System.out.println("#####################################################이거?"+nBoardVO);
			    return  "redirect:/board/noticeListView.do";
			}
		
}

