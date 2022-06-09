package kr.or.ddit.vo;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(of= {"lecId"})
public class LectureJ {
	private int lecSems;	// 학기
	private String lecId;	// 과목코드
	private String lecName;	// 과목명
	private List<StudentJ> students; //해당교과의 수강자 리스트
}
