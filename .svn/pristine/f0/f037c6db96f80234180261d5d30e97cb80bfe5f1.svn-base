package kr.or.ddit.adminLecture.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.adminLecture.dao.AdminLectureCompDAO;
import kr.or.ddit.vo.LecCompTargetVO;
import kr.or.ddit.vo.LectureVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SemsVO;

@Service
public class AdminLectureCompServiceImpl implements AdminLectureCompService {

	@Inject
	AdminLectureCompDAO dao;

	@Override
	public PagingVO<LecCompTargetVO> retrieveLecCompTargetList(PagingVO<LecCompTargetVO> vo) {
		int totalRecord = dao.selectTotalLecCompTargetList(vo);
		vo.setTotalRecord(totalRecord);
		
		List<LecCompTargetVO> dataList = dao.selectLecCompTargetList(vo);
		
		for(LecCompTargetVO eachVo : dataList) {
			int tcnt = eachVo.getPlanTcnt();
			String assignSubmit = eachVo.getAssignSubmit();
			assignSubmit = assignSubmit.equals("Y")?"제출":"미제출";
			eachVo.setAssignSubmit(assignSubmit);
			String beforeDt = eachVo.getAssignDt();
			String[] afterDt = beforeDt.split(",");
			if(tcnt == 1) {
				eachVo.setAssignDt1(afterDt[0]);
				eachVo.setAssignDt2("-");
			}else {
				eachVo.setAssignDt1(afterDt[0]);
				eachVo.setAssignDt2(afterDt[1]);
			}
		}
		vo.setDataList(dataList);
		return vo;
	}

	@Override
	public int modifyLecEstablish(Map<String, Object> paramMap) {
		SemsVO semsVo = (SemsVO) RequestContextHolder.getRequestAttributes()
        .getAttribute("semsVo", RequestAttributes.SCOPE_SESSION);
		int nextSems = semsVo.getNextSems();
		
		String json = paramMap.get("planNoList").toString();
		String jsonReplace = json.replaceAll("&quot;", "\"");
		ObjectMapper mapper = new ObjectMapper();
		List<Map<String, Object>> planNoList = new ArrayList<>();
		try {
			planNoList =
			mapper.readValue(jsonReplace, new TypeReference<ArrayList<Map<String, Object>>>() {});
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		int resCnt = 0;
		for(Map<String, Object> eachMap : planNoList) {
			int planNo = Integer.parseInt(String.valueOf(eachMap.get("planNo")));
			LectureVO vo = dao.selectLectureOne(planNo);
			vo.setLecSems(nextSems);
			dao.updatePlanStatus(planNo);
			resCnt += dao.insertLecture(vo);
		}
		return resCnt;
	}

	@Override
	public PagingVO<LecCompTargetVO> retrieveLecCompList(PagingVO<LecCompTargetVO> vo) {
		int totalRecord = dao.selectTotalLecCompList(vo);
		vo.setTotalRecord(totalRecord);
		
		List<LecCompTargetVO> dataList = dao.selectLecCompList(vo);
		
		vo.setDataList(dataList);
		return vo;
	}
}
