package kr.or.ddit.vo;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 휴학신청에 대한 정보를 담을 VO
 * @author 작성자명
 * @since 2022. 5. 14.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 5. 14.   고성식		       최초작성 
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Data
@EqualsAndHashCode(of="reqId")
public class SRequestVO {
	private int runm;
	private String reqId;
	
	private Integer userNo;
	private Integer reqSms;
	private String reqRsn;
	private String reqStart;
	private String reqEnd;
	private String reqDenl;
	private String reqDate;
	private String reqStat;
	
	
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
	
	private String deptId;
	private String deptName;
	private String colName;
	
	private Integer stuYear;
	private String stuClass;
	private String stuCode;
	
	private String stsCode1;
	

	private List<StudentVO> stuVO;
	private List<UserVO> userVO;
	private SimpleSearchVO simpleCondition;
}
