/**
 * 
 */
package kr.or.ddit.plan.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.plan.service.PlanService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 작성자명
 * @since 2022. 4. 27.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 4. 27.      작성자명       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Slf4j
@Controller
@RequestMapping("/plan")
public class PlanController {
	
	@Inject
	private PlanService planService;
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<Map<String, Object>> selectPlanList(Model model) {
		List<Map<String, Object>> listVO = planService.selectPlanList();
		log.info(listVO.toString());
		for(Map<String,Object> mapVO:listVO) {
			for(String key : mapVO.keySet()) {
			Object value = mapVO.get(key);
			
			log.info("꺼내온 값 : {}", value);
			}
		}
//		model.addAttribute("lectures", listVO);
//		return "sugang/lectureApplyView";
		return listVO;
	}
}
