package kr.or.ddit.enr.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import kr.or.ddit.enr.dao.AdminEnrollDAO;
import kr.or.ddit.vo.AdminEnrollVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SemsVO;

@Service
public class AdminEnrollSerivceImpl implements AdminEnrollService{

	@Inject
	private AdminEnrollDAO dao;
	
	@Override
	public List<AdminEnrollVO> retrieveStudentEnrollList(PagingVO<AdminEnrollVO> paging) {
		int totalRecord = dao.selectEnrollTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		List<AdminEnrollVO> dataList = dao.selectEnrollList(paging);
		paging.setDataList(dataList);
		return dataList;
	}

	
	
	@Override
	public int adminCreatEnroll(List<AdminEnrollVO> list) throws Exception {
		SemsVO semsVo = (SemsVO) RequestContextHolder.getRequestAttributes()
                .getAttribute("semsVo", RequestAttributes.SCOPE_SESSION);
		int nextSems = semsVo.getNextSems();
		int result = 0;
		
		for(AdminEnrollVO vo : list) {
			String enrAmt = vo.getEnrAmt().replaceAll(",", "");
			vo.setEnrAmt(enrAmt);
			vo.setEnrSems(nextSems);
			result += dao.insertEnroll(vo);
		}
		return result;
	}

	@Override
	public List<AdminEnrollVO> retrieveFinalEnrollList(Map<String, Object> param) {
		return dao.selectFinalEnrollList(param);
	}

	@Override
	public int retrieveFinalEnrollListTotalCount(Map<String, Object> param) {
		return dao.selectFinalEnrollTotalRecord(param);
	}

	@Override
	public int adminFinalCreatEnroll(List<AdminEnrollVO> list) throws Exception {
		int result = 0;
		
		for(AdminEnrollVO vo : list) {
			String enrPay = vo.getEnrPay().replaceAll(",", "");
			vo.setEnrPay(enrPay);
			result += dao.updateFinalEnroll(vo);
		}
		return result;
	}





}
