package kr.or.ddit.vo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.HtmlUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author 이유정
 * @since 2022. 5. 9.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 5. 9.      이유정      최초작성  <- 이부분 작성하면서 코딩 진행해주세요~
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */

@Data
@EqualsAndHashCode(of="noticeNo")
@ToString(exclude= {"noticeContent", "attchList"})
public class NoticeBoardVO {
	private Integer noticeNo;
	private Integer userNo;
	private String noticeTitle;
	private String noticeContent;
	private Integer noticeHit;
	private String noticeDate;
	private Integer attchCnt;
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
	
	private Integer noreadCnt;
	private String noreadClass;
	public void setNoreadCnt(Integer noreadCnt) {
		this.noreadCnt = noreadCnt;
		this.noreadClass = noreadCnt==0?"yet-read":"already-read";
	}
	
	private int startAttNo;
	private List<AttchVO> attchList;
	
	private int[] delAttNos;
	private MultipartFile[] boFiles;
	public void setBoFiles(MultipartFile[] boFiles) {
		if(boFiles==null || boFiles.length==0) return;
		this.boFiles = boFiles;
		this.attchList = new ArrayList<>();
		for(MultipartFile eachfile : boFiles) {
			if(eachfile.isEmpty()) continue;
			AttchVO attch = new AttchVO(eachfile);
			attchList.add(attch);
		}
	}
	
	public String getNoticeContentDisplay() {
		return HtmlUtils.htmlUnescape(this.noticeContent);
	}
	
	
}




















