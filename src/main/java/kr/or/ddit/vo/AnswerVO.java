package kr.or.ddit.vo;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**상담설문과 상담일지의 답목록 테이블
 * @author 황선부
 * @since 2022. 5. 9.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 5. 9.      황선부       최초작성  <- 이부분 작성하면서 코딩 진행해주세요~
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Data
@EqualsAndHashCode(of="ansNo")
public class AnswerVO {
	private String ansNo;
	private String ansConent;
	private Integer ansScore;
	
	private List<QuestionAnswerVO> qaVO;
}
