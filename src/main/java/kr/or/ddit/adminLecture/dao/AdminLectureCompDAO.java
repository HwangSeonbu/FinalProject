package kr.or.ddit.adminLecture.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.LecCompTargetVO;
import kr.or.ddit.vo.LectureVO;
import kr.or.ddit.vo.PagingVO;

@Mapper
public interface AdminLectureCompDAO {

	public List<LecCompTargetVO> selectLecCompTargetList(PagingVO<LecCompTargetVO> vo);
	public int selectTotalLecCompTargetList(PagingVO<LecCompTargetVO> vo);
	
	public LectureVO selectLectureOne(int planNo);
	public int updatePlanStatus(int planNo);
	public int insertLecture(LectureVO vo);
	
	public List<LecCompTargetVO> selectLecCompList(PagingVO<LecCompTargetVO> vo);
	public int selectTotalLecCompList(PagingVO<LecCompTargetVO> vo);
}
