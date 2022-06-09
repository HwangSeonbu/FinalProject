package kr.or.ddit.vo;

import java.io.Serializable;



import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author 이유정
 * @since 2022. 4. 26.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 4. 27.      이유정       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */

@Data
@EqualsAndHashCode(of="userNo")
@ToString(exclude= {"userReg1", "userReg2"})
public class UserVO implements Serializable{
	
	public  UserVO() {
		super();
	}
	
	
	public UserVO(Integer userNo, String userPass) {
		super();
		this.userNo = userNo;
		this.userPass = userPass;
	}
	
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
	
}

