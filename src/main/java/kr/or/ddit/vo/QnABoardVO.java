package kr.or.ddit.vo;

import java.util.List;

import org.springframework.web.util.HtmlUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 이유정
 * @since 2022. 5. 10.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 4. 28.      이유정       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Data
public class QnABoardVO {
	private String boardNo; //글번호
	private String groupId; //그룹번호 A1
	private Integer userNo; //유저번호
	private String boardTitle; //글제목
	private String boardContent; //자유글제목
	private Integer boardHit; //조회수
	private String boardDate; //작성일자
	private Integer boardLike; //추천수
	private Integer boardDislike; //비추천수 
	private Integer replyCnt;
	private String answerMark;
	private String spanClass;
	
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
	
	private Integer rnum;
	
	public String getBoardContentDisplay() {
		return HtmlUtils.htmlUnescape(this.boardContent);
	}

}
