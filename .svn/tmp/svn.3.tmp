package kr.or.ddit.counsel.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.counsel.dao.CounselDAO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProCounselVO;
@Service
public class CounselExcelServiceImpl implements CounselExcelService {
	
	@Inject
	private CounselDAO dao;
	
	@Override
	public List<ProCounselVO> retreiveCounselPro(PagingVO<ProCounselVO> paging) {
		List<ProCounselVO> dataList = dao.proSelectCounselList(paging);
		
		paging.setDataList(dataList);
		
		return dataList;
	}

}
