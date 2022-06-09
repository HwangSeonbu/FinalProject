package kr.or.ddit.adminLecture.service;

import java.util.Map;

import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.PlanEditVO;

public interface AdminLecturePlanService {

	public PagingVO<PlanEditVO> retrieveSubmitPlanList(PagingVO<PlanEditVO> vo);
	
	public PlanEditVO retrieveSubmitPlanOne(Integer planNo);
	
	
	//반려처리
	public int modifySubmitPlanSts(Map<String, Object> parameters);
	
	//승인처리
	public int modifySumitApproveProcess(Map<String, Object> parameters);
}
