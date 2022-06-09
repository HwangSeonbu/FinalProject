package kr.or.ddit.lecture.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.lecture.dao.ProfessorLectureDAO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.PlanEditVO;
import kr.or.ddit.vo.PlanVO;
import kr.or.ddit.vo.SubjectVO;
import kr.or.ddit.vo.WplanVO;

/**
 * @author 김재웅
 * @since 2022. 5. 10.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 5. 10.      김재웅       최초작성(교수-강의계획신청, 제출 프로세스 구현체)
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Service
public class ProfessorLecturePlanServiceImpl implements ProfessorLecturePlanService {

	@Inject
	ProfessorLectureDAO dao;
	
	@Override
	public List<PlanVO> retrieveLecturePlanList(PlanVO vo) {
		
		List<PlanVO> planList = dao.selectLecturePlanList(vo);
		
		for(PlanVO eachVo : planList) {
			if(eachVo.getPlanSmry() == null) {
				eachVo.setPlanSmry("(강의계획 요약을 작성해주세요.)");
			}
			if(eachVo.getSjtName() == null) {
				eachVo.setSjtName("(과목 미선택)");
			}
		}
		return planList;
	}

	@Override
	public int createLecturePlan(PlanVO vo) {
		return dao.insertLecturePlan(vo);
	}

	@Override
	public int removeLecturePlan(List<Map<String, Object>> paramList) {
		int resCnt = 0;
		
		for(Map<String, Object> eachMap : paramList) {
			Set<String> keys = eachMap.keySet();
			for(String eachKey : keys) {
				int planNo = Integer.parseInt(String.valueOf(eachMap.get(eachKey)));
				resCnt += dao.deleteLecturePlan(planNo);
			}
		}
		return resCnt;
	}

	@Override
	public PlanEditVO retrieveLecturePlanEdit(int planNo) {
		PlanEditVO planEditVo = dao.selectLecturePlanEditData(planNo);
		if(planEditVo.getPlanSmry() == null) {
			planEditVo.setPlanSmry("강의계획 요약을 작성해주세요.");
		}
		return planEditVo;
	}

	@Override
	public int modifyLecturePlan(PlanEditVO vo) {
		WplanVO wPlanVo = new WplanVO();
		
		Integer planNo = vo.getPlanNo();
		List<Map<String, Object>> wPlanMapList = vo.getWPlanMapList();
		wPlanVo.setPlanNo(planNo);
		for(Map<String, Object> eachMap : wPlanMapList) {
			Object obj = eachMap.get("wplanNo");
			if(obj == null) {
				wPlanVo.setWplanNo(0);
			}else {
				wPlanVo.setWplanNo(Integer.parseInt(String.valueOf(obj)));
			}
			wPlanVo.setWplanW(Integer.parseInt(String.valueOf(eachMap.get("wplanW"))));
			wPlanVo.setWplanCont(String.valueOf(eachMap.get("wplanCont")));
			
			System.out.println("----------------------------------");
			System.out.print(wPlanVo.getPlanNo()+"\t");
			System.out.print(wPlanVo.getWplanNo()+"\t");
			System.out.print(wPlanVo.getWplanW()+"\t");
			System.out.println(wPlanVo.getWplanCont());
			
			dao.updateOrInsertWplan(wPlanVo);
		}
		
		return dao.updateLecturePlan(vo);
	}

	
	@Override
	public PagingVO<SubjectVO> retrieveSubjectList(PagingVO<SubjectVO> vo) {
		int totalRecord = dao.selectSubjectTotalRecord(vo);
		vo.setTotalRecord(totalRecord);
		
		List<SubjectVO> dataList = dao.selectSubjectList(vo);
		vo.setDataList(dataList);
		return vo;
	}

	@Override
	public List<PlanVO> retrieveSubmitPlanList(PlanVO vo) {
		return dao.selectSubmitPlanList(vo);
	}

	@Override
	public int modifySubmitPlan(int planNo) {
		return dao.updatePlanSts(planNo);
	}

}







