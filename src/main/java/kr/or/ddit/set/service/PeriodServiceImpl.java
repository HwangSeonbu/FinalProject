package kr.or.ddit.set.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.set.dao.PeriodDAO;
import kr.or.ddit.vo.AcadscVO;
import kr.or.ddit.vo.SemsDataVO;

@Service
public class PeriodServiceImpl implements PeriodService {

	@Inject
	PeriodDAO dao;
	
	@Override
	public List<SemsDataVO> retrieveBaseSemsDataList() {
		List<SemsDataVO> semsList = dao.selectSemsDataList();
		for(SemsDataVO eachVo : semsList) {
			int semsNo = eachVo.getSemsNo();
			int year = semsNo/100;
			int semester = semsNo%100;
			eachVo.setSemester(""+year+"년 "+semester+"학기");
		}
		return semsList;
	}

	@Override
	public List<AcadscVO> retrievePeriodDataList(int pickSemsNo) {
		int acadscSems = pickSemsNo;
		List<AcadscVO> acadscList = dao.selectPeriodDataList(acadscSems);
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
		int year = acadscSems/100;
		int semester = acadscSems%100;
		String defaultStartDate1 = ""+year+"-03-01";
		String defaultEndDate1 = ""+year+"-06-30";
		String defaultStartDate2 = ""+year+"-09-01";
		String defaultEndDate2 = ""+year+"-12-31";
		
		for(AcadscVO eachVo : acadscList) {
			String stsId = eachVo.getStsId();
			if(eachVo.getAcadscNo() == null) {
				eachVo.setAcadscNo(0);
				eachVo.setSetSts("미입력");
				eachVo.setStsClass("span-setNo");
				if(semester == 1) {
					if(stsId.equals("S102")) {
						eachVo.setStartDate(defaultEndDate1);
						eachVo.setEndDate(defaultEndDate1);
					}else {
						eachVo.setStartDate(defaultStartDate1);
						eachVo.setEndDate(defaultEndDate1);
					}
				}else {
					if(stsId.equals("S102")) {
						eachVo.setStartDate(defaultEndDate2);
						eachVo.setEndDate(defaultEndDate2);
					}else {
						eachVo.setStartDate(defaultStartDate2);
						eachVo.setEndDate(defaultEndDate2);
					}
				}
			}else {
				eachVo.setSetSts("시스템 반영중");
				eachVo.setStsClass("span-setYes");
				String startDate = formatDate.format(eachVo.getAcadscStart());
				eachVo.setStartDate(startDate);
				if(eachVo.getAcadscEnd() == null) {
					eachVo.setEndDate(startDate);
				}else {
					String endDate = formatDate.format(eachVo.getAcadscEnd());
					eachVo.setEndDate(endDate);
				}
			}
		}
		return acadscList;
	}

	@Override
	public int modifyOrCreatePeriod(AcadscVO paramVo) {
		
		paramVo.setAcadscDiv("B3");
		
		return dao.updateOrInsertPeriod(paramVo);
	}

}
