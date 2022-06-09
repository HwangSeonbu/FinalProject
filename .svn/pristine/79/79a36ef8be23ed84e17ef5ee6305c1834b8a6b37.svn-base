package kr.or.ddit.set.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.AcadscVO;
import kr.or.ddit.vo.SemsDataVO;

@Mapper
public interface PeriodDAO {
	public List<SemsDataVO> selectSemsDataList();
	
	public List<AcadscVO> selectPeriodDataList(int pickSemsNo);
	
	public int updateOrInsertPeriod(AcadscVO vo);
}
