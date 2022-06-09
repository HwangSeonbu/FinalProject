package kr.or.ddit.counsel.service;

import java.util.List;

import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProCounselVO;

public interface CounselExcelService {
	public List<ProCounselVO> retreiveCounselPro(PagingVO<ProCounselVO>paging);
}
