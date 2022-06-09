package kr.or.ddit.vo;



import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Data;

@Data
public class AstLecVO {
	private String lecId;
	private Integer userNo;
	private Integer planNo;
	private String proName;
	private String deptId;
	private String colName;
	private String deptName;
	private String lecName;
	private String planSmry;
	private String sjtId;
	private Integer planTcnt;
	private String planEval;
	private Integer sjtCredit;
	private String sjtMajor;
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
