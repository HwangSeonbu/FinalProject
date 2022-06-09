package kr.or.ddit.vo;

import lombok.Data;

@Data
public class PageAccessVO {

	private boolean access;
	private String action;
	private String accessPeriod;
}
