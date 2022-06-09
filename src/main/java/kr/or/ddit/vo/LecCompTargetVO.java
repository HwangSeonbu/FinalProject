package kr.or.ddit.vo;

import lombok.Data;

/**
 * 강의 승인된 강의들의 상세정보를 위한 VO (== 개설 타겟 강의)
 * @author 김재웅
 * @since 2022. 5. 14.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 5. 14.      김재웅       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Data
public class LecCompTargetVO {
	private String colName;
	private String deptId;
	private String sjtId;
	private String deptName;
	private String gwanName;
	private String headproName;
	private String planSts;
	private String assignDt;
	private String assignDt1;
	private String assignDt2;
	private String lecId;
	private Integer planSems;
	private Integer lecSems;
	private Integer lecPers;
	private String proJob;
	private Integer planTcnt;
	private String sjtName;
	private Integer planNo;
	private Integer roomNo;
	private Integer rnum;
	private Integer userNo;
	private String proName;
	private String planSmry;
	private Integer proNo;
	private String assignSts;
	private String assignSubmit;
	
	private Integer planYear;
	private Integer planLimit;
	private String planEval;
}
