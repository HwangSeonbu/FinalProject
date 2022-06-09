package kr.or.ddit.lecture.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

/**
 * 교수가 자신이 강의하는 강의 목록에 대한 점수 비중을 설정하는 컨트롤러
 * ex)중간고사,기말고사,출석,과제 점수 비중 설정
      30    30    20  20
 * @author 주창규
 * @since 2022. 4. 26.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 4. 26.      주창규       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Controller
@RequestMapping("/lecture")
@Slf4j
public class LectureProportion {
	
	@GetMapping("/LectureProportionView")
	public String lectureProportionView() {
		log.info("====================44444");
		return "lecture/lectureProportionView";
	}
	
	@GetMapping("/LectureProportionDetail")
	public String lectureProportionDetail() {
		log.info("====================55555");
		return "lecture/lectureProportionDetail";
	}
}
