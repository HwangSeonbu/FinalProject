package kr.or.ddit.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 메뉴 임시
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
@NoArgsConstructor
public class TempFinalMenuVO {

	private String finalMenuName;
	private String finalMenuURI;
	public TempFinalMenuVO(String finalMenuName, String finalMenuURI) {
		super();
		this.finalMenuName = finalMenuName;
		this.finalMenuURI = finalMenuURI;
	}
	
}
