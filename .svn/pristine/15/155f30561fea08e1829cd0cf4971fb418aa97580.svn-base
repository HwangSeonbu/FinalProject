package kr.or.ddit.sugang.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.enumpkg.ServiceResult;

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

public interface LectureApplyViewService {
	public List<Map<String, Object>> selectPlanList(int userNo,String deptName, String sjtMajor, Integer sjtGrade, String sjtName,int nextSems);
	public ServiceResult lectureApply(String lecId,String lecSems, int userNo,int lecCredit);
	public int lectureCancel(String lecId,String lecSems, int userNo);
	public List<Map<String, Object>> alreadyAppliedList(int userNo,int nextSems);
	public List<Map<String, String>> deptNameList();
	public int cartInsert(String lecId,String lecSems, int userNo);
	public int cartDelete(String lecId,String lecSems, int userNo);
	public List<Map<String, Object>> studentApplyLectureScheduleList(Map<String,Object> info);
}
