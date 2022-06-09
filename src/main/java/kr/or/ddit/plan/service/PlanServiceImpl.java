/**
 * 
 */
package kr.or.ddit.plan.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.plan.dao.PlanDAO;
import kr.or.ddit.vo.PlanVO;

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

@Service
public class PlanServiceImpl implements PlanService{
	
	@Inject
	private PlanDAO planDAO;

	@Override
	public List<Map<String, Object>> selectPlanList() {
		return planDAO.selectPlanList();
		
	}

}
