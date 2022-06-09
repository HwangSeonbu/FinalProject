package kr.or.ddit.vo;


import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Data;

@Data
public class AstProVO {

	private Integer userNo;
	private String deptId;
	private String colName;
	private String deptName;
	private String proJob;
	private String userGender;
	private String userName;
	private String lecName;
	private Integer astCnt;
	private String astYn;
	private Date astDate;
	
	private String astDateFormat;
	
	public void setAstDate(Date astDate) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.astDate = astDate;
		astDateFormat = format.format(astDate);
	}
}
