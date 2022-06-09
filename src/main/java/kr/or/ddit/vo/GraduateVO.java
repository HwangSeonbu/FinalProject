package kr.or.ddit.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 졸업학생 VO
 * @author 작성자명
 * @since 2022. 5. 11.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 5. 11.    고성식		       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Data
@EqualsAndHashCode(of="userNo")
public class GraduateVO {
	
	private Integer rnum;
	private Integer userNo;
	private String userPass;
	private String userName;
	private String userGender;
	private String userPhone;
	private String userAddr;
	private String userReg1;
	private String userReg2;
	private String userMail;
	private String userSavename;
	private String userDate;
	private String userCode;
	private String memRole;
	
	private String deptId;
	private Integer stuYear;
	private String stuClass;
	private String stuCode;
	private String stuCdtEarn;
	private String stuGduCdt;
	private String stuGduDate;
	
	private String deptName;
	private String colName;
	private Integer deptFl;
	
	private String stsCode1;
	
}
