package kr.or.ddit.room.service;

import java.util.List;


import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.room.dao.AdminRoomSetDAO;
import kr.or.ddit.vo.CollegeVO;
import kr.or.ddit.vo.DepartmentVO;
import kr.or.ddit.vo.GwanVO;
import kr.or.ddit.vo.RoomVO;

@Service
public class AdminRoomSetServiceImpl implements AdminRoomSetService {

	@Inject
	AdminRoomSetDAO dao;
	
	@Override
	public List<CollegeVO> retrieveCollegeList() {
		List<CollegeVO> collegeList = dao.selectCollegeList();
		for(CollegeVO vo : collegeList) {
			if(vo.getColGwan() == null) {
				vo.setColGwan("미정");
			}
		}
		return collegeList;
	}

	@Override
	public List<DepartmentVO> retrieveCollegeDepartList(String colName) {
		
		List<DepartmentVO> departList = dao.selectCollegeDepartList(colName);
		for(DepartmentVO vo : departList) {
			if(vo.getColGwan() == null) {
				vo.setColGwan("미정");
			}
		}
		
		return departList;
	}

	@Override
	public List<GwanVO> retrieveGwanList() {
		return dao.selectGwanList();
	}

	@Override
	public int modifyCollegeGwan(CollegeVO vo) {
		if(vo.getColGwan().equals("초기화")) {
			vo.setColGwan(null);
		}
		return dao.updateCollegeGwan(vo);
	}

	@Override
	public GwanVO retrieveFloorList(GwanVO vo) {
		vo.setFloorList(dao.selectFloorList(vo));
		return vo;
	}

	@Override
	public List<RoomVO> retrieveFloorRoomList(GwanVO vo) {
		
		return dao.selectFloorRoomList(vo);
	}

	@Override
	public int modifyDepartFloor(DepartmentVO vo) {
		if(vo.getDeptFl() == 0) {
			vo.setDeptFl(null);
		}
		return dao.updateDepartFloor(vo);
	}

}
