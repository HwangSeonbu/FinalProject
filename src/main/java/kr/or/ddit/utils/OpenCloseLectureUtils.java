package kr.or.ddit.utils;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import kr.or.ddit.attendance.dao.ProfessorAttendanceDAO;
import kr.or.ddit.vo.SemsVO;

@Component
public class OpenCloseLectureUtils {

	private String openLec;
	private String closeLec;
	
	@Inject
	ProfessorAttendanceDAO dao;

/*	
	public String openLectureDate() {
		return openLec;
	}
	
	public String closeLectureDate() {
		return closeLec;
	}
*/	
	
	/**
	 * @return 개강일 key : openLec, 종강일 key : closeLec
	 */
	public Map<String, String> openCloseLectureDate(){
		SemsVO semsVo = (SemsVO) RequestContextHolder.getRequestAttributes()
                .getAttribute("semsVo", RequestAttributes.SCOPE_SESSION);
		int thisSems = semsVo.getThisSems();
		Map<String, String> openClose = dao.selectOpenCloseLecDate(thisSems);
		int year = thisSems/100;
		int semester = thisSems%100;
		String defaultStartDate1 = ""+year+"-03-01";
		String defaultEndDate1 = ""+year+"-06-30";
		String defaultStartDate2 = ""+year+"-09-01";
		String defaultEndDate2 = ""+year+"-12-31";
		
		openLec = openClose.get("OPEN_LEC");
		closeLec = openClose.get("CLOSE_LEC");
		
		if(openLec == null || openLec.isEmpty()) {
			if(semester == 1) {
				openLec = defaultStartDate1;
			}else {
				openLec = defaultStartDate2;
			}
		}
		if(closeLec == null || openLec.isEmpty()) {
			if(semester == 1) {
				closeLec = defaultEndDate1;
			}else {
				closeLec = defaultEndDate2;
			}
		}
		
		Map<String, String> returnMap = new HashMap<String, String>();
		
		returnMap.put("openLec", openLec);
		returnMap.put("closeLec", closeLec);
		
		return returnMap;
	}
}
