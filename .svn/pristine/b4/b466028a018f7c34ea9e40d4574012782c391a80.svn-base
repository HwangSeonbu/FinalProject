package kr.or.ddit.room.service;

import java.util.List;

import kr.or.ddit.vo.CollegeVO;
import kr.or.ddit.vo.DepartmentVO;
import kr.or.ddit.vo.GwanVO;
import kr.or.ddit.vo.RoomVO;

public interface AdminRoomSetService {

	public List<CollegeVO> retrieveCollegeList();
	public List<DepartmentVO> retrieveCollegeDepartList(String colName);
	
	public List<GwanVO> retrieveGwanList();
	
	public int modifyCollegeGwan(CollegeVO vo);
	public int modifyDepartFloor(DepartmentVO vo);
	
	public GwanVO retrieveFloorList(GwanVO vo);
	
	public List<RoomVO> retrieveFloorRoomList(GwanVO vo);
}
