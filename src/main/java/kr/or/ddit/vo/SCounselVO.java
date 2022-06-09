package kr.or.ddit.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author 황선부
 * @since 2022. 4. 28.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 4. 28.      황선부       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Data
@EqualsAndHashCode(of="cnslId")
@NoArgsConstructor
public class SCounselVO {

	private int rnum;	
	private String cnslId;
	private int stuNo;
	private Integer proNo;
	private String cnslDate;
	private String dyDate;
	private String cnslDay;
	private String cnslLo;
	private String sems;
	
	private String proOutdate;
	private String proIndate;
	private String proJob;
	
	private Integer userNo;
	private String userName;
	private String userPhone;
	private String userAddr;
	private String userMail;
	private String userGender;
	
	private Integer deptId;
	private String deptName;
	private String colName;
	
	private Integer stuYear;
	private String stuClass;
	private String stuCode;
	
	
	private StudentVO stuVO;	
	private ProfessorVO proVO;
	private SimpleSearchVO simpleCondition;
	
}
