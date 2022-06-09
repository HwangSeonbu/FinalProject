package kr.or.ddit.adminLecture.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import aj.org.objectweb.asm.Attribute;
import kr.or.ddit.adminLecture.dao.AdminLectureDAO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.PlanEditVO;
import kr.or.ddit.vo.PlanVO;
import kr.or.ddit.vo.SemsVO;

@Service
public class AdminLecturePlanServiceImpl implements AdminLecturePlanService {
	@Inject
	AdminLectureDAO dao;
	
	@Override
	public PagingVO<PlanEditVO> retrieveSubmitPlanList(PagingVO<PlanEditVO> vo) {
		SemsVO semsVo = (SemsVO) RequestContextHolder.getRequestAttributes()
				 			.getAttribute("semsVo", RequestAttributes.SCOPE_SESSION);
		int nextSems = semsVo.getNextSems();
		
		int totalRecord = dao.selectSubmitPlanTotalRecord(vo);
		vo.setTotalRecord(totalRecord);
//		int nextSems = vo.getDetailCondition().getPlanSems();
		
		List<PlanEditVO> dataList = dao.selectSubmitPlanList(vo);
		for(Iterator<PlanEditVO> value = dataList.iterator(); value.hasNext();) {
			PlanEditVO eachVo = value.next();
			if(eachVo.getPlanSems() != nextSems) {
				value.remove();
			}
		}
		vo.setDataList(dataList);
		return vo;
	}

	@Override
	public PlanEditVO retrieveSubmitPlanOne(Integer planNo) {
		return dao.selectSubmitPlanOne(planNo);
	}

	@Override
	public int modifySubmitPlanSts(Map<String, Object> parameters) {
		int result = 0;
		String targetPlanNo = parameters.get("targetPlanNos").toString();
		String targetPlanNoReplace = targetPlanNo.replaceAll("&quot;", "\"");
		
		String reason = parameters.get("reason").toString();
		String reasonReplace = reason.replaceAll("&quot;", "\"");
		
		ObjectMapper mapper = new ObjectMapper();
		PlanVO vo = new PlanVO();
		vo.setPlanDenlrsn(reasonReplace);
		
		if(targetPlanNoReplace.charAt(0) == '[') {
			List<String> planNoList = new ArrayList<>();
			try {
				planNoList =
						mapper.readValue(targetPlanNoReplace, new TypeReference<ArrayList<String>>() {});
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			for(String planNoStr : planNoList) {
				Integer planNo = Integer.parseInt(String.valueOf(planNoStr));
				vo.setPlanNo(planNo);
				result += dao.updateSubmitPlanSts(vo);
			}
		}else {
			Integer planNo = null;
			try {
				planNo =
						mapper.readValue(targetPlanNoReplace, new TypeReference<Integer>() {});
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			vo.setPlanNo(planNo);
			result += dao.updateSubmitPlanSts(vo);
		}
		return result;
	}

	@Override
	public int modifySumitApproveProcess(Map<String, Object> parameters) {
		SemsVO semsVo = (SemsVO) RequestContextHolder.getRequestAttributes()
	 			.getAttribute("semsVo", RequestAttributes.SCOPE_SESSION);
		int nextSems = semsVo.getNextSems();
		
		int resCnt = 0;
		String targetPlanNo = parameters.get("targetPlanNos").toString();
		String targetPlanNoReplace = targetPlanNo.replaceAll("&quot;", "\"");
		
		int targetSems = nextSems;
		int lecIdPrefix = Integer.parseInt(String.valueOf(targetSems).substring(2));
		
		ObjectMapper mapper = new ObjectMapper();
		List<PlanEditVO> planNoList = new ArrayList<>();
		try {
			planNoList =
					mapper.readValue(targetPlanNoReplace, new TypeReference<ArrayList<PlanEditVO>>() {});
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		for(PlanEditVO targetVo : planNoList) {
			targetVo.setPlanSems(lecIdPrefix);
			Integer planNo = targetVo.getPlanNo();
			dao.insertPlanok(targetVo);
			resCnt += dao.updateSubmitApprove(planNo);
			for(int i = 1; i <= 2; i++) {
				dao.insertLectureAssign(planNo);
			}
		}
		return resCnt;
	}
}
