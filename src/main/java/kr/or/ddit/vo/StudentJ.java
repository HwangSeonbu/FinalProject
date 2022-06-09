package kr.or.ddit.vo;

import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(of= {"lecId","userNo"})
public class StudentJ { // 한 학생의 한과목에 대한 수강정보
	private int stuYear;		// 학년
	private String userNo;		// 학번
	private String userName;	// 이름
	private String deptName;	// 학과
	private String lecId;		// 수강과목
	private List<AttandJ> attList; //해당 학생의 해당과목의 출결사항
}
