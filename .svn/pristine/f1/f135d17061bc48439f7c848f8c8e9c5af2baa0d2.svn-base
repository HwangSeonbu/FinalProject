package kr.or.ddit.vo;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.util.HtmlUtils;

import lombok.Data;

/**
 * @author 작성자명
 * @since 2022. 5. 13.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 5. 13.      이유정       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */

//QnABoardVO랑 내용 중복 .....
@Data
public class FreeBoardVO {
	private String boardNo; //글번호
	private String groupId; //그룹번호 A1
	private Integer userNo; //유저번호
	private String boardTitle; //글제목
	private String boardContent; //자유글제목
	private Integer boardHit; //조회수
	private String boardDate; //작성일자
	private Integer boardLike; //추천수
	private Integer boardDislike; //비추천수 
	
	private String myRecommand;
	private Integer replyCnt;
	private String boreadClass;
	
	private Integer boreadCnt;
	public void setBoreadCnt(Integer boreadCnt) {
		this.boreadCnt = boreadCnt;
		this.boreadClass = boreadCnt==0?"yet-read":"already-read";
	}

	private Integer rnum;
	
	@NotBlank
	private transient String userPass; //비밀번호
	private String userName; //이름
	private String userType; //학생,학사관리자,교수
	private String userDepartment; //학과
	private String userGrade; //학년
	private String userGender; //성별
	private String userPhone; //핸드폰번호
	private String userAddr; //주소
	private String userReg1; //주민번호1
	private String userReg2; //주민번호2
	private String userMail; //메일
	
	private String userSavename; //사진저장명
	

	
	private String memRole; //권한(ROLE_ADMIN ...)
	
//	public String getfreeContentDisplay() {
//		return HtmlUtils.htmlUnescape(this.boardContent);
//	}
	
	public String getBoardContentDisplay() {
		return HtmlUtils.htmlUnescape(this.boardContent);
	}
	
	
	
}

