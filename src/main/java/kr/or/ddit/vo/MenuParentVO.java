package kr.or.ddit.vo;

import lombok.Data;

/**
 * 임시
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
public class MenuParentVO {

	private Integer level;
	private String menuId;
	private String menuText;
	private String menuUrl;
	private String menuClasses;
	private String menuColor;
	private String menuParent;
	private String menuTop;
	private String menuYn;
	
}
