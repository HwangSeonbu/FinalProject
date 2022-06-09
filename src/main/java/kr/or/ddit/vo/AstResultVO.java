package kr.or.ddit.vo;

import lombok.Data;

@Data
public class AstResultVO {
	private Integer userNo;
	private String lecId;
	private String lecName;
	private String userName;
	private String userGender;
	private String proIndate;
	private String colName;
	private String deptName;
	private String proJob;
	private int lecCnt;
	private int lecTotalpers;
	private int lecRealpers;
	private int astPers;
	private String astComprate;
	private double astScore;
}
