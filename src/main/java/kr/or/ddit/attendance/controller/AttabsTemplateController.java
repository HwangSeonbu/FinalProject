package kr.or.ddit.attendance.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.attendance.dao.AttendanceDAO;
import kr.or.ddit.attendance.service.ProfessorAttendanceService;
import kr.or.ddit.grade.service.GradeService;
import kr.or.ddit.vo.LectureJ;
import lombok.extern.slf4j.Slf4j;

/**
 * 교수가 자신의 강의를 수강 중인 학생들의 출석을 조회하하는 컨트롤러
 * 
 * @author 주창규
 * @since 2022. 5. 24.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * 
 *      <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 5. 24.      주창규       최초작성
 * 
 * Copyright (c) 2022 by DDIT All right reserved
 *      </pre>
 */
@Controller
@Slf4j
@RequestMapping("/templateAttabs")
public class AttabsTemplateController {

	@Inject
	private GradeService gService;

	@Inject
	private AttendanceDAO aDao;

	@Inject
	private ProfessorAttendanceService pService;

	@RequestMapping("templateView.do")
	public String templateView(
			Model model
	){
		List<String> semsdata = gService.retrieveSemsdata();
		model.addAttribute("semsdata", semsdata);

		return "attendance/professorAttabsTemplate";
	}

	
	@RequestMapping("attendanceList")
	public String attendanceList(
			Model model
			, @RequestParam(value = "lecSems") int lecSems
			, @RequestParam(value = "lecId") String lecId

	){
		int startDate = aDao.selectStartDate(lecSems);

		Map<String, Object> map = new HashMap<>();

		map.put("lecSems", lecSems);
		map.put("lecId", lecId);
		map.put("startDate", startDate);

		List<Map<String, Object>> attendanceList = pService.retrieveAttendanceLecture(map);

		model.addAttribute("attendanceList", attendanceList);

		return "jsonView";
	}

	//-----------------------------------------------------
	private int rowCount;
	private int cellCount;
	
	// title
	private HSSFRow titRow = null;
	private HSSFRow titRow2 = null;
	// col
	private HSSFRow colRow = null;
	// content
	private HSSFRow contRow = null;
	//-----------------------------------------------------
	
	@RequestMapping("attExcel")
	public void attExcel(
			Model model
			, @RequestParam(value = "lecSems") int lecSems
			, @RequestParam(value = "lecId") String lecId
			, HttpServletResponse resp
	) throws FileNotFoundException, IOException {
		int startDate = aDao.selectStartDate(lecSems);

		Map<String, Object> map = new HashMap<>();

		map.put("lecSems", lecSems);
		map.put("lecId", lecId);
		map.put("startDate", startDate);

		List<LectureJ> excelList = pService.retrieveAttendanceLectureExcel(map);

		String target = "출석부";

		HSSFWorkbook workbook = new HSSFWorkbook();

		HSSFSheet sheet = workbook.createSheet(target);

		// title
		titRow = null;
		titRow2 = null;
		// col
		colRow = null;
		// content
		contRow = null;
		
		
		// style
		HSSFCellStyle titleStyle = columnStyle(workbook);
		HSSFCellStyle contStyle = contStyle(workbook);
		HSSFCellStyle titStyle = titleStyle(workbook);

		rowCount = 0;
		cellCount = 0;

		// create title
		titRow = sheet.createRow(0);
		Cell titCell = titRow.createCell(0);
		titCell.setCellValue("출석부");

		// date
		titRow2 = sheet.createRow(++rowCount);
		Cell dateCell = titRow2.createCell(21);

		Date date = new Date();

		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd ");
		dateCell.setCellValue("현재날짜 : " + dateFormatter.format(date));

		SimpleDateFormat dateFileName = new SimpleDateFormat("yyyyMMdd");
		String fileName = "counsel." + dateFileName.format(date);

		rowCount = 2;

		colRow = sheet.createRow(rowCount++);

		// create column
		colRow.createCell(cellCount++).setCellValue("학기");
		colRow.createCell(cellCount++).setCellValue("강의명");
		colRow.createCell(cellCount++).setCellValue("학년");
		colRow.createCell(cellCount++).setCellValue("학번");
		colRow.createCell(cellCount++).setCellValue("이름");
		colRow.createCell(cellCount++).setCellValue("1주1시수");
		colRow.createCell(cellCount++).setCellValue("1주2시수");
		colRow.createCell(cellCount++).setCellValue("2주1시수");
		colRow.createCell(cellCount++).setCellValue("2주2시수");
		colRow.createCell(cellCount++).setCellValue("3주1시수");
		colRow.createCell(cellCount++).setCellValue("3주2시수");
		colRow.createCell(cellCount++).setCellValue("4주1시수");
		colRow.createCell(cellCount++).setCellValue("4주2시수");
		colRow.createCell(cellCount++).setCellValue("5주1시수");
		colRow.createCell(cellCount++).setCellValue("5주2시수");
		colRow.createCell(cellCount++).setCellValue("6주1시수");
		colRow.createCell(cellCount++).setCellValue("6주2시수");
		colRow.createCell(cellCount++).setCellValue("7주1시수");
		colRow.createCell(cellCount++).setCellValue("7주2시수");
		colRow.createCell(cellCount++).setCellValue("8주1시수");
		colRow.createCell(cellCount++).setCellValue("8주2시수");
		colRow.createCell(cellCount++).setCellValue("9주1시수");
		colRow.createCell(cellCount++).setCellValue("9주2시수");
		colRow.createCell(cellCount++).setCellValue("10주1시수");
		colRow.createCell(cellCount++).setCellValue("10주2시수");
		colRow.createCell(cellCount++).setCellValue("11주1시수");
		colRow.createCell(cellCount++).setCellValue("11주2시수");
		colRow.createCell(cellCount++).setCellValue("12주1시수");
		colRow.createCell(cellCount++).setCellValue("12주2시수");
		colRow.createCell(cellCount++).setCellValue("13주1시수");
		colRow.createCell(cellCount++).setCellValue("13주2시수");
		colRow.createCell(cellCount++).setCellValue("14주1시수");
		colRow.createCell(cellCount++).setCellValue("14주2시수");
		colRow.createCell(cellCount++).setCellValue("15주1시수");
		colRow.createCell(cellCount++).setCellValue("15주2시수");
		colRow.createCell(cellCount++).setCellValue("16주1시수");
		colRow.createCell(cellCount++).setCellValue("16주2시수");

		for (int i = 0; i < cellCount; i++) {
			HSSFCell titcell = colRow.getCell(i);
			titcell.setCellStyle(titleStyle);
		}
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, cellCount - 1));
		titCell.setCellStyle(titStyle);
		
		log.info("excelList.size()---->{}", excelList.size());
		
		for (int i = 0; i < excelList.size(); i++) {
			
			
			LectureJ attsel = excelList.get(i);
			
			excelList.get(i).getStudents().forEach((std)->{
				
				cellCount = 0;
				contRow = sheet.createRow(rowCount++);
				contRow.createCell(cellCount++).setCellValue(String.valueOf (attsel.getLecSems()));
				contRow.createCell(cellCount++).setCellValue(String.valueOf (attsel.getLecName()));
				
				contRow.createCell(cellCount++).setCellValue(String.valueOf (std.getStuYear()));
				contRow.createCell(cellCount++).setCellValue(String.valueOf (std.getUserNo()));
				contRow.createCell(cellCount++).setCellValue(String.valueOf (std.getUserName()));
				
				std.getAttList().forEach((att)->{
					contRow.createCell(cellCount++).setCellValue(String.valueOf (att.getAttabsCode()+","+att.getAttabsDate()));
				});
			}) ;
			for(int j=0; j<cellCount ; j++) {
				HSSFCell contCell = contRow.getCell(j);
				contCell.setCellStyle(contStyle);    	
			}
		}


		// 컬럼 사이즈
		for (int i = 0; i < excelList.size(); i++) {
			sheet.autoSizeColumn(i);
			sheet.setColumnWidth(i, (sheet.getColumnWidth(i)) + 1000);
		}

		resp.setContentType("Application/Msexcel");
		resp.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xls");

		OutputStream fileOut = resp.getOutputStream();
		workbook.write(fileOut);
		fileOut.close();

		resp.getOutputStream().flush();
		resp.getOutputStream().close();

	}

	private HSSFCellStyle titleStyle(HSSFWorkbook workbook) {
		HSSFCellStyle HeadStyle = workbook.createCellStyle();
		
		HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short)15);
        font.setBoldweight((short)font.BOLDWEIGHT_BOLD);
        
		HeadStyle.setFont(font);
		HeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		HeadStyle.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
		HeadStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		return HeadStyle;
	}

	private HSSFCellStyle contStyle(HSSFWorkbook workbook) {
HSSFCellStyle style = workbook.createCellStyle();
		
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);  //테두리 종류들
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);	
				
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //중앙 정렬(위~아래)
		style.setAlignment(CellStyle.ALIGN_CENTER);  //중앙정렬(좌~우)		

		style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());  //채우기 선택
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);  //채우기 적용(이거안하면 안채운다)
		
		return style;
	}

	private HSSFCellStyle columnStyle(HSSFWorkbook workbook) {
HSSFCellStyle style = workbook.createCellStyle();
		
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);  //테두리 종류들
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);	
				
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //중앙 정렬(위~아래)
		style.setAlignment(CellStyle.ALIGN_CENTER);  //중앙정렬(좌~우)		

		return style;
	}
}
