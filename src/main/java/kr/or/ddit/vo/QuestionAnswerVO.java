package kr.or.ddit.vo;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

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
@EqualsAndHashCode(of="qaNo")
public class QuestionAnswerVO implements Serializable {
	private Integer qaNo;
	private String questNo;
	private String ansNo;
	private String cnslId;
	private String qaLong;
	
	private AnswerVO answerVO;
	private QuestionVO questionVO;
	
	private CounselVO counselVO;
	
	private List<QuestionAnswerVO> qavoList;
	
	private String dyDate;
	private String cnslDate;



	
}
