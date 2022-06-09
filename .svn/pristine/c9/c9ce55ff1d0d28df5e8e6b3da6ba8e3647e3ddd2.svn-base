package kr.or.ddit.vo;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**과제제출 VO
 * @author 황선부
 * @since 2022. 5. 23.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 5. 23.      황선부       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Data
@EqualsAndHashCode(of="hwkNo")
public class HwkVO implements Serializable {
	private Integer hwkNo;
	private Integer stuNo;
	private Integer lechwkNo;
	private String boardAttch;
	private String hwkDate;
	private Integer hwkScore;
	private Integer proNo;
	private String hwkOpinion;
	private String hwkContent;
	private String hwkRegisdate;
	private String userImage;
	
	private AttchVO attach;
	private String stuName;
	
	//지울려고 하는 파일
	private Integer delAttNo;	
	//신규로 올릴 파일
	private MultipartFile boFile;
		
	public void setBoFile(MultipartFile boFile) {
		if(boFile==null || boFile.isEmpty()) return;
		
		this.boFile = boFile;
		
		this.attach = new AttchVO(boFile);		
	}
	public void setHwkOpinion(String hwkOpinion) {
		
		this.hwkOpinion = hwkOpinion.replace("\n", "<br>");
	}
}
