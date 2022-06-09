package kr.or.ddit.lecture.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import kr.or.ddit.lecture.dao.ProfessorLectureDAO;
import kr.or.ddit.vo.ProfessorLectureVO;
import kr.or.ddit.vo.ProfessorVO;
import kr.or.ddit.vo.SemsVO;

@Service
public class ProfessorLectureServiceImpl implements ProfessorLectureService {

	@Inject
	ProfessorLectureDAO dao;
	
	@Override
	public List<ProfessorLectureVO> retrieveProLectureList(ProfessorVO vo) {
		SemsVO semsVo = (SemsVO) RequestContextHolder.getRequestAttributes()
                .getAttribute("semsVo", RequestAttributes.SCOPE_SESSION);
		vo.setThisSems(semsVo.getThisSems());
		
		return dao.selectProLectureList(vo);
	}

	@Override
	public List<ProfessorLectureVO> retrieveProLectureTimeTable(ProfessorVO vo) {
		SemsVO semsVo = (SemsVO) RequestContextHolder.getRequestAttributes()
                .getAttribute("semsVo", RequestAttributes.SCOPE_SESSION);
//		List<ProfessorLectureVO> proLectureList = dao.selectProLectureTimeTableList(vo);
		
		vo.setThisSems(semsVo.getThisSems());
		
		return dao.selectProLectureTimeTableList(vo);
	}

}
