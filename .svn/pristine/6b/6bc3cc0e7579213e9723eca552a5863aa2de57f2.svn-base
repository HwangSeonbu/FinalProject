package kr.or.ddit.sugang.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.sugang.dao.LectureApplyViewDAO;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author 민진홍
 * @since 2022. 4. 28.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 4. 28.      작성자명       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Slf4j
@Service
public class LectureApplyViewServiceImpl implements LectureApplyViewService {
	
	@Inject
	private LectureApplyViewDAO lecDAO;
	
	@Override
	public List<Map<String, Object>> selectPlanList(int userNo, String deptName, String sjtMajor, Integer sjtGrade, String sjtName, int nextSems) {
		Map<String, Object> map = new HashMap<>();
		map.put("userNo", userNo);
		map.put("deptName", deptName);
		map.put("sjtMajor", sjtMajor);
		map.put("sjtGrade", sjtGrade);
		map.put("sjtName", sjtName);
		map.put("nextSems", nextSems);
		
		return lecDAO.selectPlanList(map);
	}
	
	/**
	 * 수강신청
	 */
	@Override
	public ServiceResult lectureApply(String lecId,String lecSems, int userNo,int lecCredit) {
		Map<String, Object> map = new HashMap<>();
		map.put("lecId", lecId);
		map.put("lecSems", lecSems);
		map.put("userNo", userNo);
		int applyCreditSum = lecDAO.applyCreditSum(map);
		if((applyCreditSum+lecCredit) > 21) {
		return ServiceResult.CREDITOVER;
		}
		
		List<Map<String, Object>> validateList = lecDAO.clsInsertValidate(map);
		if(!validateList.isEmpty()) {
			return ServiceResult.VALIDATEFAIL;
		}
		int rownum = lecDAO.lectureApply(lecId);
		if(rownum >= 1) {
			rownum = lecDAO.clsInsert(map);
		}else {
			return ServiceResult.FAIL;
		}
		
		return ServiceResult.OK;	
	}
	
	/**
	 * 이미신청한수강내역
	 */
	@Override
	public List<Map<String, Object>> alreadyAppliedList(int userNo,int nextSems) {
		Map<String, Object> map = new HashMap<>();
		map.put("userNo", userNo);
		map.put("nextSems", nextSems);
		return lecDAO.alreadyAppliedList(map);
	}
	
	/**
	 * 수강신청철회
	 */
	@Override
	public int lectureCancel(String lecId, String lecSems, int userNo) {
		int rownum = lecDAO.lectureCancel(lecId);
		if(rownum >= 1) {
			Map<String, Object> map = new HashMap<>();
			map.put("lecId", lecId);
			map.put("lecSems", lecSems);
			map.put("userNo", userNo);
			rownum = lecDAO.clsDelete(map);
		}
		
		return rownum;	
	}

	
	@Override
	public List<Map<String, String>> deptNameList() {
		return lecDAO.deptNameList();
	}

	/**
	 * 찜강의 등록
	 */
	@Override
	public int cartInsert(String lecId, String lecSems, int userNo) {
		Map<String, Object> map = new HashMap<>();
		map.put("lecId", lecId);
		map.put("lecSems", lecSems);
		map.put("userNo", userNo);
		int rownum = lecDAO.cartInsert(map);

		return rownum;
	}
	/**
	 * 찜강의 삭제
	 */
	@Override
	public int cartDelete(String lecId, String lecSems, int userNo) {
		Map<String, Object> map = new HashMap<>();
		map.put("lecId", lecId);
		map.put("lecSems", lecSems);
		map.put("userNo", userNo);
		int rownum = lecDAO.cartDelete(map);

		return rownum;
	}
	
	/**
	 * 시간표 조회
	 */
	@Override
	public List<Map<String, Object>> studentApplyLectureScheduleList(Map<String, Object> info) {
		
		List<Map<String,Object>> list = lecDAO.studentApplyLectureScheduleList(info);
		List<Map<String,Object>> rsList = new ArrayList<>();
		for(Map<String,Object> map : list) {
			Map<String, Object> rsMap = new HashMap<>();
			String day =  (String) map.get("ASSIGN_DAY");
			String time = (String) map.get("ASSIGN_TIME");
			String name = (String) map.get("LEC_NAME");
			if(day.equals("월")) {
				rsMap.put("mon"+time, name);
			}
			else if(day.equals("화")) {
				rsMap.put("tue"+time, name);
			}
			else if(day.equals("수")) {
				rsMap.put("wed"+time, name);
			}
			else if(day.equals("목")) {
				rsMap.put("thu"+time, name);
			}
			else if(day.equals("금")) {
				rsMap.put("fri"+time, name);
			}
			rsList.add(rsMap);
			
			
		}
		
		
		
		return rsList;
	}
}
