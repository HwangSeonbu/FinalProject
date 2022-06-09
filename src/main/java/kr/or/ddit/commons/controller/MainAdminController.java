package kr.or.ddit.commons.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.vo.TempFinalMenuVO;
import kr.or.ddit.vo.TempMenuVO;
import kr.or.ddit.vo.TempSideMenuVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MainAdminController {
	
	List<TempSideMenuVO> sideMenuList;
	private static TempSideMenuVO sidemenu1_1;
	private static TempSideMenuVO sidemenu1_2;
	private static TempSideMenuVO sidemenu1_3;
	private static TempSideMenuVO sidemenu1_4;
	private static TempSideMenuVO sidemenu1_5;
	private static TempSideMenuVO sidemenu1_6;
	
	private static TempSideMenuVO sidemenu2_1;
	private static TempSideMenuVO sidemenu2_2;
	private static TempSideMenuVO sidemenu2_3;
	
	private static TempSideMenuVO sidemenu3_1;
	private static TempSideMenuVO sidemenu3_2;
	private static TempSideMenuVO sidemenu3_3;
	
	private static TempSideMenuVO sidemenu4_1;
	private static TempSideMenuVO sidemenu4_2;
	private static TempSideMenuVO sidemenu4_3;
	private static TempSideMenuVO sidemenu4_4;
	
	private static TempSideMenuVO sidemenu5_1;
	private static TempSideMenuVO sidemenu5_2;
	private static TempSideMenuVO sidemenu5_3;
	private static TempSideMenuVO sidemenu5_4;
	static {
		//1번메뉴
		TempFinalMenuVO finalmenu1_1_1 = new TempFinalMenuVO("신입생일괄등록", "temp/tempPage.do");
		TempFinalMenuVO finalmenu1_1_2 = new TempFinalMenuVO("신입생개별등록", "temp/tempPage.do");
		List<TempFinalMenuVO> finalmenuList1_1 = Arrays.asList(finalmenu1_1_1, finalmenu1_1_2);
		sidemenu1_1 = new TempSideMenuVO("신입생등록", finalmenuList1_1);
		TempFinalMenuVO finalmenu1_2_1 = new TempFinalMenuVO("재학생조회", "student/studentList.do");
		TempFinalMenuVO finalmenu1_2_2 = new TempFinalMenuVO("졸업생조회", "graduate/graduateStudentList.do");
		List<TempFinalMenuVO> finalmenuList1_2 = 
				Arrays.asList(finalmenu1_2_1, finalmenu1_2_2);
		sidemenu1_2 = new TempSideMenuVO("학생현황조회", finalmenuList1_2);
		TempFinalMenuVO finalmenu1_3_1 = new TempFinalMenuVO("신청기간설정", "req/setPeriod.do");
		TempFinalMenuVO finalmenu1_3_2 = new TempFinalMenuVO("휴학신청현황", "req/leaveSchoolList.do");
		TempFinalMenuVO finalmenu1_3_3 = new TempFinalMenuVO("복학신청현황", "req/returnSchoolList.do");
		List<TempFinalMenuVO> finalmenuList1_3 = 
				Arrays.asList(finalmenu1_3_1, finalmenu1_3_2, finalmenu1_3_3);
		sidemenu1_3 = new TempSideMenuVO("휴복학관리", finalmenuList1_3);
		TempFinalMenuVO finalmenu1_4_1 = new TempFinalMenuVO("장학금선정기준안내", "scholar/scholarBoard.do");
		TempFinalMenuVO finalmenu1_4_2 = new TempFinalMenuVO("장학생현황조회", "scholar/scholarStudentList.do");
		TempFinalMenuVO finalmenu1_4_3 = new TempFinalMenuVO("성적우수장학생선정", "setScholar/setCreditScholarStudent.do");
		TempFinalMenuVO finalmenu1_4_4 = new TempFinalMenuVO("기타장학생선정", "setScholar/setExtraScholarStrudent.do");
		List<TempFinalMenuVO> finalmenuList1_4 = 
				Arrays.asList(finalmenu1_4_1, finalmenu1_4_2, finalmenu1_4_3, finalmenu1_4_4);
		sidemenu1_4 = new TempSideMenuVO("장학관리", finalmenuList1_4);
		TempFinalMenuVO finalmenu1_5_1 = new TempFinalMenuVO("졸업가능대상자관리", "targetGraduate/graduateTargetList.do");
		TempFinalMenuVO finalmenu1_5_2 = new TempFinalMenuVO("졸업유예자관리", "delayGraduate/graduateDelayList.do");
		TempFinalMenuVO finalmenu1_5_3 = new TempFinalMenuVO("졸업유예신청현황", "delayGraduate/graduateDelayRequestList.do");
		List<TempFinalMenuVO> finalmenuList1_5 = 
				Arrays.asList(finalmenu1_5_1, finalmenu1_5_2, finalmenu1_5_3);
		sidemenu1_5 = new TempSideMenuVO("졸업관리", finalmenuList1_5);
		TempFinalMenuVO finalmenu1_6_1 = new TempFinalMenuVO("등록금기준관리", "adminEnroll/enrollStandardForm.do");
		TempFinalMenuVO finalmenu1_6_2 = new TempFinalMenuVO("등록금부과", "setEnroll/enrollSetForm.do");
		TempFinalMenuVO finalmenu1_6_3 = new TempFinalMenuVO("등록금납부현황조회", "adminEnroll/enrollPayList.do");
		List<TempFinalMenuVO> finalmenuList1_6 = 
				Arrays.asList(finalmenu1_6_1, finalmenu1_6_2, finalmenu1_6_3);
		sidemenu1_6 = new TempSideMenuVO("등록금관리", finalmenuList1_6);
		
		//2번메뉴
		sidemenu2_1 = new TempSideMenuVO("신규교수등록", "pro/professorForm.do");
		sidemenu2_2 = new TempSideMenuVO("교수현황조회", "proView/professorList.do");
		sidemenu2_3 = new TempSideMenuVO("교수실적조회", "proView/professorRecordList.do");
		
		//3번메뉴
		TempFinalMenuVO finalmenu3_1_1 = new TempFinalMenuVO("성적입력기간설정", "set/setCrdtPeriodForm.do");
		TempFinalMenuVO finalmenu3_1_2 = new TempFinalMenuVO("평가입력기간설정", "set/setAstPeriodForm.do");
		List<TempFinalMenuVO> finalmenuList3_1 = 
				Arrays.asList(finalmenu3_1_1, finalmenu3_1_2);
		sidemenu3_1 = new TempSideMenuVO("입력기간설정", finalmenuList3_1);;
		
		TempFinalMenuVO finalmenu3_2_1 = new TempFinalMenuVO("시험성적입력현황", "completeScore/testScoreCompleteList.do");
		TempFinalMenuVO finalmenu3_2_2 = new TempFinalMenuVO("최종성적입력현황", "completeScore/totalScoreCompleteList.do");
		TempFinalMenuVO finalmenu3_2_3 = new TempFinalMenuVO("성적이의신청처리현황", "completeScore/objectionScoreCompleteList.do");
		List<TempFinalMenuVO> finalmenuList3_2 = 
				Arrays.asList(finalmenu3_2_1, finalmenu3_2_2, finalmenu3_2_3);
		sidemenu3_2 = new TempSideMenuVO("성적입력현황조회", finalmenuList3_2);
		TempFinalMenuVO finalmenu3_3_1 = new TempFinalMenuVO("평가별진행현황조회", "astResult/AstResultList.do");
		TempFinalMenuVO finalmenu3_3_2 = new TempFinalMenuVO("교수평가결과조회", "astResult/ProfessorAstResultList.do");
		TempFinalMenuVO finalmenu3_3_3 = new TempFinalMenuVO("강의평가결과조회", "astResult/LectureAstResultList.do");
		List<TempFinalMenuVO> finalmenuList3_3 = 
				Arrays.asList(finalmenu3_3_1, finalmenu3_3_2, finalmenu3_3_3);
		sidemenu3_3 = new TempSideMenuVO("강의평가관리", finalmenuList3_3);
		
		//4번메뉴
		sidemenu4_1 = new TempSideMenuVO("개설강의현황조회", "lecComp/LectureCompleteList.do");
		TempFinalMenuVO finalmenu4_2_1 = new TempFinalMenuVO("분과대학강의관배분", "roomSet/collegeForm.do");
		TempFinalMenuVO finalmenu4_2_2 = new TempFinalMenuVO("학과강의층배분", "roomSet/departForm.do");
		List<TempFinalMenuVO> finalmenuList4_2 = 
				Arrays.asList(finalmenu4_2_1, finalmenu4_2_2);
		sidemenu4_2 = new TempSideMenuVO("강의실배분관리", finalmenuList4_2);
		TempFinalMenuVO finalmenu4_3_1 = new TempFinalMenuVO("강의계획신청현황", "lecReq/LectureRequestList.do");
		TempFinalMenuVO finalmenu4_3_2 = new TempFinalMenuVO("반려처리이력조회", "lecReq/LectureRequestDenialList.do");
		List<TempFinalMenuVO> finalmenuList4_3 = 
				Arrays.asList(finalmenu4_3_1, finalmenu4_3_2);
		sidemenu4_3 = new TempSideMenuVO("강의계획신청처리", finalmenuList4_3);
		sidemenu4_4 = new TempSideMenuVO("강의개설처리", "lecComp/LectureCompleteForm.do");
		
		//5번메뉴
		sidemenu5_1 = new TempSideMenuVO("공지사항", "board/noticeListView.do");
		TempFinalMenuVO finalmenu5_2_1 = new TempFinalMenuVO("학사일정게시판", "board/acadScheduleList.do");
		TempFinalMenuVO finalmenu5_2_2 = new TempFinalMenuVO("학사일정등록", "board/acadScInsertForm.do");
		List<TempFinalMenuVO> finalmenuList5_2 = 
				Arrays.asList(finalmenu5_2_1, finalmenu5_2_2);
		sidemenu5_2 = new TempSideMenuVO("학사일정", finalmenuList5_2);
		TempFinalMenuVO finalmenu5_3_1 = new TempFinalMenuVO("질문답변게시판", "board/qnaBoardList.do");
		TempFinalMenuVO finalmenu5_3_2 = new TempFinalMenuVO("챗봇답변관리", "chatBot/ProcessForm.do");
		List<TempFinalMenuVO> finalmenuList5_3 = 
				Arrays.asList(finalmenu5_3_1, finalmenu5_3_2);
		sidemenu5_3 = new TempSideMenuVO("질문답변", finalmenuList5_3);
		TempFinalMenuVO finalmenu5_4_1 = new TempFinalMenuVO("자유게시판", "board/freeBoardList.do");
		TempFinalMenuVO finalmenu5_4_2 = new TempFinalMenuVO("삭제글관리", "board/deleteBoardList.do");
		List<TempFinalMenuVO> finalmenuList5_4 = 
				Arrays.asList(finalmenu5_4_1, finalmenu5_4_2);
		sidemenu5_4 = new TempSideMenuVO("자유게시판", finalmenuList5_4);
	}

	@RequestMapping("/adminMain.do")
	public String topMenuHandler(Model model, HttpSession session){
		log.info("topMenuHandler 도착 ======================================");
		session.removeAttribute("sideMenuListSession");
		
		session.setAttribute("userType", "학사관리자");
		session.setAttribute("userName", "민관리");
		session.setAttribute("userDepartment", "컴퓨터공학과");
		session.setAttribute("userGrade", "1학년");
		
		TempMenuVO topMenu1 = new TempMenuVO("학생관리", 1);
		TempMenuVO topMenu2 = new TempMenuVO("교수관리", 2);
		TempMenuVO topMenu3 = new TempMenuVO("시험/평가", 3);
		TempMenuVO topMenu4 = new TempMenuVO("강의관리", 4);
		TempMenuVO topMenu5 = new TempMenuVO("게시판", 5);
		
		List<TempMenuVO> topMenuList = Arrays.asList(topMenu1, topMenu2, topMenu3,
				topMenu4, topMenu5);
		
		session.setAttribute("topMenuList", topMenuList);
		return "temp/adminMainPage";
	}
	
	@RequestMapping("temp/adminSide.do")
	public String leftMenuHandler(
			@RequestParam String menuNo
			, Model model, HttpSession session) {
		int menuNumber = Integer.parseInt(menuNo);
		
		log.info("leftMenuHandler가 받은 파라미터 ===>{}", menuNumber);
		
		switch (menuNumber) {
		case 1: sideMenuList = Arrays.asList(sidemenu1_1, sidemenu1_2, sidemenu1_3
					, sidemenu1_4, sidemenu1_5, sidemenu1_6);
			break;
		case 2: sideMenuList = Arrays.asList(sidemenu2_1, sidemenu2_2, sidemenu2_3);
			break;
		case 3: sideMenuList = Arrays.asList(sidemenu3_1, sidemenu3_2, sidemenu3_3);
			break;
		case 4: sideMenuList = Arrays.asList(sidemenu4_1, sidemenu4_2
					, sidemenu4_3, sidemenu4_4);
			break;
		case 5: sideMenuList = Arrays.asList(sidemenu5_1, sidemenu5_2, sidemenu5_3, sidemenu5_4);
			break;
		default: sideMenuList = null;
			break;
		}
		session.setAttribute("sideMenuListSession", sideMenuList);
		model.addAttribute("sideMenuList", sideMenuList);
		
		return "jsonView";
	}

//	@RequestMapping("temp/tempPage.do")
//	public String tempView() {
//		return "temp/tempView";
//	}
}


















