/**
 * 
 */
package kr.or.ddit.vo;

import lombok.Data;

/**
 * @author 민진홍
 * @since 2022. 5. 6.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 5. 6.      민진홍       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Data
public class ScholarshipVO {
	private String schNo;
	private String schName;
	private String schScore;
	private String schQuali;
	private Integer schAmount;
	private String schContent;
}
