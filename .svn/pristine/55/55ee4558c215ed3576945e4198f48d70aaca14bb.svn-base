package kr.or.ddit.room.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.CollegeVO;
import kr.or.ddit.vo.DepartmentVO;
import kr.or.ddit.vo.GwanVO;
import kr.or.ddit.vo.RoomVO;

@Mapper
public interface AdminRoomSetDAO {

	public List<CollegeVO> selectCollegeList();
	public List<DepartmentVO> selectCollegeDepartList(String colName);
	
	public List<GwanVO> selectGwanList();
	
	public int updateCollegeGwan(CollegeVO vo);
	public int updateDepartFloor(DepartmentVO vo);
	
	public List<Integer> selectFloorList(GwanVO vo);

	public List<RoomVO> selectFloorRoomList(GwanVO vo);
}
