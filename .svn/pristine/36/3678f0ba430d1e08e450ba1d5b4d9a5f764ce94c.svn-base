package kr.or.ddit.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="replyNo")
public class LecQnaReplyVO {
	
	private String replyNo;
	private String replyContent;
	private String replyDate;
	private Integer lecboNo;
	private Integer stuNo;
	private Integer proNo;
	private String stuName;
	private String proName;
	
	public void setReplyContent(String replyContent) {
		String modifedrc = replyContent.replace("\n", "<br>");
		this.replyContent = modifedrc;
	}
}
