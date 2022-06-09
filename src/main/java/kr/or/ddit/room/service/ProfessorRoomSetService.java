package kr.or.ddit.room.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.AssignVO;
import kr.or.ddit.vo.DepartLectureAssignVO;
import kr.or.ddit.vo.DepartRoomSetVO;
import kr.or.ddit.vo.DepartmentVO;
import kr.or.ddit.vo.PlanVO;

public interface ProfessorRoomSetService {

	public List<DepartLectureAssignVO> retrieveDepartLecList(PlanVO vo);
	public DepartmentVO retrieveDepartOne(String deptId);
	
	public List<AssignVO> retrieveRoomAssignList(AssignVO vo);
	
	public int modifyDepartRoomAssign(DepartRoomSetVO vo);
	
	
	public List<DepartLectureAssignVO> retrieveSubmitTargetList(PlanVO vo);
	
	public List<DepartLectureAssignVO> retrieveDuplicationResult(List<Integer> planNoList);
	
	public int modifyAssignToSubmit(List<Map<String, Object>> planNoList);
}
