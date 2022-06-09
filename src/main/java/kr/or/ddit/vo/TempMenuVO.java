package kr.or.ddit.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 김재웅
 * @since 2022. 4. 27.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 4. 27.      김재웅       임시 VO입니다.
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Data
@NoArgsConstructor
public class TempMenuVO {

	private String topMenuName;
	private int topMenuNo;
	public TempMenuVO(String topMenuName, int topMenuNo) {
		super();
		this.topMenuName = topMenuName;
		this.topMenuNo = topMenuNo;
	}
	
	
}
