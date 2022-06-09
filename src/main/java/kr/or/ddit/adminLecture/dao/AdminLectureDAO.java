package kr.or.ddit.adminLecture.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.PlanEditVO;
import kr.or.ddit.vo.PlanVO;

@Mapper
public interface AdminLectureDAO {

	public int selectSubmitPlanTotalRecord(PagingVO<PlanEditVO> vo);
	public List<PlanEditVO> selectSubmitPlanList(PagingVO<PlanEditVO> vo);
	
	public PlanEditVO selectSubmitPlanOne(Integer planNo);
	
	public int updateSubmitPlanSts(PlanVO vo);
	public int updateSubmitApprove(Integer planNo);
	public int insertLectureAssign(Integer planNo);
	
	public int insertPlanok(PlanEditVO vo);
}
