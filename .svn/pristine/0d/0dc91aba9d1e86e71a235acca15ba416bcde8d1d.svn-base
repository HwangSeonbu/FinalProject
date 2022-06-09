package kr.or.ddit.vo;

import java.util.List;

import lombok.Data;

@Data
public class AstVO {

	private Integer astNo;
	private double astScore;
	private String astDate;
	private String astDiv;
	private String lecId;
	private Integer lecSems;
	private Integer userNo;
	private String astEtc;
	private Integer proNo;
	private String astAnswer;
	
	private List<String> astEtcList;
	private List<Double> astAnswerList;
	
	private String[] answerStrArr;
	private double[] answerFlArr;
	
	public void setAstAnswer(String astAnswer) {
		this.astAnswer = astAnswer;
		answerStrArr = astAnswer.split(",");
		answerFlArr = new double[answerStrArr.length];
		for(int i=0; i<answerStrArr.length; i++) {
			answerFlArr[i] = Double.parseDouble(answerStrArr[i]);
		}
	}
	
}
