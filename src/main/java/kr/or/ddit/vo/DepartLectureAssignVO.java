package kr.or.ddit.vo;

import java.util.List;


import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 학과의 개설될 강의들의 강의실, 강의시간 배정을 위한 VO
 * @author 김재웅
 * @since 2022. 5. 9.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 5. 9.      김재웅       최초작성  
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Data
@EqualsAndHashCode(of="assignNo")
public class DepartLectureAssignVO {

	private String deptId;
	private String sjtId;
	private String planLimit;
	private Integer userNo;
	private Integer planTcnt;
	private String assignNo;
	private String sjtName;
	private String userName;
	private Integer planNo;
	private Integer planYear;
	private String sjtMajor;
	private String roomNoStr;
	private Integer roomNo;
	private String assignDt;
	private String gwanName;
	private String assignDt1;
	private String assignDt2;
	
	private String assignSts;
	private List<AssignVO> assignList;
}
