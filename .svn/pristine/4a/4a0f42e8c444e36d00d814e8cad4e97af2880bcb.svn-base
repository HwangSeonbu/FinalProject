package kr.or.ddit.graduate.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.GraduateVO;
import kr.or.ddit.vo.PagingVO;

/**
 * 졸업생VO
 * @author 고성식
 * @since 2022. 5. 11.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 5. 11.     고성식	      최초작성  
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Mapper
public interface GraduateDAO {
	
	/**
	 * 졸업생조회 
	 * @param paging
	 * @return
	 */
	public List<GraduateVO> selectGraduateList(PagingVO<GraduateVO> paging);
	
	/**
	 * 졸업생리스트조건에 맞는 회원 수 조회
	 * @param paging
	 * @return
	 */
	public int selectTotalRecord(PagingVO<GraduateVO> paging);
	
	/**
	 * 학생 정보 상세조회
	 * @param userNo
	 * @return
	 */
	public GraduateVO selectStudent(Integer userNo);
	
	/**
	 * 졸업가능 학생 리스트
	 * @param paging
	 * @return
	 */
	public List<GraduateVO> selectGraduateTargetList(PagingVO<GraduateVO> paging);
	
	/**
	 * 졸업가능리스트 조건에 맞는 회원 수 조회
	 * @param paging
	 * @return
	 */
	public int selelctTargetTotalRecord(PagingVO<GraduateVO> paging);
	
	/**
	 * 졸업승인한 학생 상태 변경
	 * @param vo
	 * @return
	 */
	public int updateGraduateSts(GraduateVO vo);
	
}
