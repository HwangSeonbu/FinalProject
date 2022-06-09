package kr.or.ddit.student.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.StudentVO;

/**
 * Student DAO
 * @author 고성식
 * @since 2022. 4. 26.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 4. 26.    고성식		       최초작성
 * 2022. 5. 02.    주창규	        userNo(학생 번호)의 타입을 String >>>> integer로 변경함.
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Mapper
public interface StudentDAO {
	
	/**
	 * 학생 목록 조회
	 * @param paging
	 * @return 
	 */
	public List<StudentVO> selectStudentList(PagingVO<StudentVO> paging);
	
	/**
	 * 검색 조건에 맞는 회원 수 조회
	 * @param paging
	 * @return
	 */
	public int selectTotalRecord(PagingVO<StudentVO> paging);
	
	/**
	 * 학생 정보 상세조회
	 * @param userNo
	 * @return
	 */
	public StudentVO selectStudent(Integer userNo);
	
	/**
	 * 사용자 번호 조회
	 * @param student
	 * @return
	 */
	public int selectUserNo();
	
	/**
	 * 사용자 등록
	 * @param student
	 * @return
	 */
	public void insertUser(StudentVO student);
	
	/**
	 * 학생 등록
	 * @param student
	 * @return
	 */
	public void insertStudent(StudentVO student);
	
	
}
