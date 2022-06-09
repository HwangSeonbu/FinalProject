package kr.or.ddit.sugang.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.ddit.vo.PlanEditVO;

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
 * 2022. 4. 28.     민진홍      최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Mapper
public interface LectureApplyViewDAO {
	public List<Map<String, Object>> selectPlanList(Map<String,Object> map);
	public int lectureApply(String lecId);
	public int lectureCancel(String lecId);
	public int clsInsert(Map<String, Object> map);
	public int applyCreditSum(Map<String, Object> map);
	public int clsDelete(Map<String, Object> map);
	public List<Map<String, Object>> alreadyAppliedList(Map<String,Object> map);
	public List<Map<String, String>> deptNameList();
	public int cartInsert(Map<String, Object> map);
	public int cartDelete(Map<String, Object> map);
	public List<Map<String, Object>> studentApplyLectureScheduleList(Map<String,Object> info);
	public List<Map<String, Object>> clsInsertValidate(Map<String, Object> map);
	public PlanEditVO lecturePlanView(Integer planNo);
	
	
}
