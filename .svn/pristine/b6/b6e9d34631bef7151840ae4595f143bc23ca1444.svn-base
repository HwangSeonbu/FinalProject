package kr.or.ddit.grade.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;


import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import kr.or.ddit.grade.service.GradeService;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.security.MemberVOWrapper;
import lombok.extern.slf4j.Slf4j;

/**
 * 교수가 성적을 입력하기 위한 컨트롤러
 * @author 주창규
 * @since 2022. 4. 27.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 4. 27.      주창규       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */

@Controller
@Slf4j
@RequestMapping("/studentGrade")
public class GradeController {
   
   @Inject
   private GradeService gService;
   
   
   //단순히 페이지 이동
   @RequestMapping("selectGradeForm")
   public String semsdataSelect(
   ){
      return "grade/gradeView";
   }
   
   //년도와 학기를 선택 후 해당 정보를 리턴
   @RequestMapping("semsdataForm")
   public String gradeUpdate(
          Model model
   ){
      List<String> semsdata = gService.retrieveSemsdata();
      model.addAttribute("semsdata", semsdata);
      return "jsonView";
   }
   
   @RequestMapping("selectLecture")
   public String lectureSelect(
         Model model
         , Authentication authentication
         , @RequestParam(value ="semsdata") String planSems
         
   ){
      MemberVO authMember = ((MemberVOWrapper)authentication.getPrincipal()).getRealUser();
      int userNo = authMember.getUserNo();
      
      Map<String,Object> map =new HashMap<>();
      
      map.put("planSems", planSems);   //연도 학기
      map.put("userNo", userNo);   //교수번호
      List<Map<String, Object>> professorLecture =gService.retrieveProfessorLecture(map);
      
      
      
      model.addAttribute("professorLecture", professorLecture);
      
      return "jsonView";
   }
   
   @RequestMapping("selectStudentGrade")
   public String gradeSelect(
         Model model
         , @RequestParam(value ="lecSems") int lecSems
         , @RequestParam(value ="lecId") String lecId
   ){
      Map<String,Object> map = new HashMap<>();
      
      map.put("lecSems", lecSems);
      map.put("lecId", lecId);
      
      
      List<Map<String, Object>> studentGrade = gService.retrieveStudentGrade(map);
      
      
      log.info("==================1번studentGrade=>>>>>>>>>={}",studentGrade);
      for(Map<String, Object> eachMap : studentGrade) {
         int   clspct = gService.updateClspct(eachMap);
         int clsrct = gService.updateClsrct(eachMap);
         int lectureclsAttabs = gService.updateclsAttabs(eachMap);
         int clsCrdt = gService.updateclsCrdt(eachMap);
      }
      
      
      studentGrade = gService.retrieveStudentGrade(map);
      log.info("==================2번studentGrade=>>>>>>>>>==========={}",studentGrade);
      
      Map<String, Object> lectureRate = gService.retrieveLectureRate(lecId);
      log.info("==================lectureRate=>>>>>>>>>==========={}",lectureRate);
      
      
      model.addAttribute("studentGrade", studentGrade);
      model.addAttribute("lectureRate", lectureRate);
      
      return "jsonView";
   }
      

   @RequestMapping("dblclickClsMexam")
   public String dblclickClsMexam(
         Model model
         ,@RequestParam(value ="clsMexam") String clsMexam
         ,@RequestParam(value ="lecId") String lecId
         ,@RequestParam(value ="userNo") int userNo
         ,@RequestParam(value ="lecSems") int lecSems
   ){
      Map<String,Object> map =new HashMap<>();
      
      map.put("clsMexam", clsMexam);
      map.put("lecId", lecId);
      map.put("userNo", userNo);
      map.put("lecSems", lecSems);
      
      int clsMexamGrade = gService.updateClsMexam(map);
      
      model.addAttribute("clsMexamGrade", clsMexamGrade);
      
      return "jsonView";
   }
   
   @RequestMapping("dblclickClsFexam")
   public String dblclickClsFexam(
         Model model
         ,@RequestParam(value ="clsFexam") String clsFexam
         ,@RequestParam(value ="lecId") String lecId
         ,@RequestParam(value ="userNo") int userNo
         ,@RequestParam(value ="lecSems") int lecSems
   ){
      Map<String,Object> map =new HashMap<>();
      
      map.put("clsFexam", clsFexam);
      map.put("lecId", lecId);
      map.put("userNo", userNo);
      map.put("lecSems", lecSems);
      
      int lecSts = gService.updatelecSts(map);
      int clsFexamGrade = gService.updateClsFexam(map);
      
      model.addAttribute("clsFexamGrade",clsFexamGrade);
      
      return "jsonView";
   }
   
   @RequestMapping("dblclickClsHwk")
   public String dblclickClsHwk(
         Model model
         ,@RequestParam(value ="clsHwk") String clsHwk
         ,@RequestParam(value ="lecId") String lecId
         ,@RequestParam(value ="userNo") int userNo
         ,@RequestParam(value ="lecSems") int lecSems
   ){
      Map<String,Object> map =new HashMap<>();
      
      map.put("clsHwk", clsHwk);
      map.put("lecId", lecId);
      map.put("userNo", userNo);
      map.put("lecSems", lecSems);
      
      int clsHwkGrade = gService.updateClsHwk(map);
      
      model.addAttribute("clsHwkGrade",clsHwkGrade);
      
      return "jsonView";
   }
   
   
   
   
}