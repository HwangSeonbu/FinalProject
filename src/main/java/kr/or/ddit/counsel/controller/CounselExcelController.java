package kr.or.ddit.counsel.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import kr.or.ddit.counsel.service.CounselService;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProCounselVO;
import kr.or.ddit.vo.security.MemberVOWrapper;
@Controller
public class CounselExcelController {
	@Autowired
	private CounselService service;
	
	@GetMapping("/respCounsel/counselExcel")
	public void ExcelView(
			HttpServletRequest request
			,HttpServletResponse resp
			,Authentication authentication
			) throws FileNotFoundException, IOException {
		String viewName = null;
		
		String userNo
		 = ((MemberVOWrapper)authentication.getPrincipal()).getUsername();
		
		int proNo = Integer.parseInt(userNo);
		
		PagingVO<ProCounselVO> paging = new PagingVO<>();
		paging.setProNo(proNo);
		
		List<ProCounselVO> pList = service.retreiveCounselPro(paging);	
		//excelTest
		String target = "상담관리";
		
		HSSFWorkbook workbook = new HSSFWorkbook();

		HSSFSheet sheet = workbook.createSheet(target);
		
		//title
		HSSFRow titRow = null;
		HSSFRow titRow2 = null;
		//col
		HSSFRow colRow = null;
		//content
		HSSFRow contRow = null;
		
		//style
		HSSFCellStyle titleStyle = columnStyle(workbook);
		HSSFCellStyle contStyle = contStyle(workbook);
		HSSFCellStyle titStyle = titleStyle(workbook);
		
		int rowCount = 0;
        int cellCount = 0;
        
        //create title
        titRow = sheet.createRow(0);
        Cell titCell = titRow.createCell(0);
        titCell.setCellValue("학생 상담 관리");
        
        //date
		titRow2 = sheet.createRow(++rowCount);
        Cell dateCell = titRow2.createCell(8);
        
		Date date = new Date();

        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateCell.setCellValue("현재시간 : "+dateFormatter.format(date));
        
        SimpleDateFormat dateFileName = new SimpleDateFormat("yyyyMMdd");
        String fileName = "counsel."+dateFileName.format(date);
        
        rowCount=2;      
        colRow = sheet.createRow(rowCount++);
        
        //create column
        colRow.createCell(cellCount++).setCellValue("상담번호");
        colRow.createCell(cellCount++).setCellValue("학번");
        colRow.createCell(cellCount++).setCellValue("이름");
        colRow.createCell(cellCount++).setCellValue("학과번호");
        colRow.createCell(cellCount++).setCellValue("학과명");
        colRow.createCell(cellCount++).setCellValue("학적상태");
        colRow.createCell(cellCount++).setCellValue("상담예약 일/시간");
        colRow.createCell(cellCount++).setCellValue("전화번호");
        colRow.createCell(cellCount++).setCellValue("등록상담일");
		
        //범위가 딱 맞아야함.
        for(int i=0; i<cellCount ; i++) {
        	HSSFCell titcell = colRow.getCell(i);
        	titcell.setCellStyle(titleStyle);    	
        }
        
        //cell 병합
        sheet.addMergedRegion(new CellRangeAddress(
        		0
        		,0
        		,0
        		,cellCount-1));
        //title style
        titCell.setCellStyle(titStyle);

        //data cell 불러오기
        for(int i=0; i<pList.size();i++) {
        	cellCount=0;
        	ProCounselVO counsel = pList.get(i);
        	contRow = sheet.createRow(rowCount++);
        	contRow.createCell(cellCount++).setCellValue(counsel.getCnslId());
            contRow.createCell(cellCount++).setCellValue(counsel.getUserNo());
            contRow.createCell(cellCount++).setCellValue(counsel.getUserName());
            contRow.createCell(cellCount++).setCellValue(counsel.getDeptId());
            contRow.createCell(cellCount++).setCellValue(counsel.getDeptName());
            contRow.createCell(cellCount++).setCellValue(counsel.getStuCode());
            contRow.createCell(cellCount++).setCellValue(counsel.getCnslDay());
            contRow.createCell(cellCount++).setCellValue(counsel.getUserPhone());
            contRow.createCell(cellCount++).setCellValue(counsel.getCnslDate());
        	

            for(int j=0; j<cellCount ; j++) {
            	HSSFCell contCell = contRow.getCell(j);
            	contCell.setCellStyle(contStyle);    	
            }
        }
        
        //컬럼 사이즈
        for (int i = 0; i <  pList.size(); i++){
            sheet.autoSizeColumn(i);
            sheet.setColumnWidth(i, (sheet.getColumnWidth(i)) + 1000);
        }
		// Write the output to a file
//		try (OutputStream fileOut = new FileOutputStream("d:/poi/"+fileName+".xls")) {
//		    workbook.write(fileOut);
//		
//		}
		//File saveFile = new File(saveFolder, fileName+"xls");		
		resp.setContentType("Application/Msexcel");
		resp.setHeader("Content-Disposition", "attachment;filename="+fileName+".xls");
		
		OutputStream fileOut = resp.getOutputStream();
		workbook.write(fileOut);
		fileOut.close();
		
		resp.getOutputStream().flush();
		resp.getOutputStream().close();
		
//		return "redirect:respCounsel/requestView";	
	}
	private HSSFCellStyle titleStyle(HSSFWorkbook workbook) {
		HSSFCellStyle HeadStyle = workbook.createCellStyle();
		
		//제목 폰트
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short)15);
//        font.setFontName("굴림");
        font.setBoldweight((short)font.BOLDWEIGHT_BOLD);
		HeadStyle.setFont(font);
		
		HeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		HeadStyle.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
//		HeadStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//		HeadStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//		HeadStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
//		HeadStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		HeadStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

		
		return HeadStyle;
	}
	//column style
	private HSSFCellStyle columnStyle(HSSFWorkbook workbook) {
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
	//title style
	private HSSFCellStyle contStyle(HSSFWorkbook workbook) {
		HSSFCellStyle style = workbook.createCellStyle();
		
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);  //테두리 종류들
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);	
				
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //중앙 정렬(위~아래)
		style.setAlignment(CellStyle.ALIGN_CENTER);  //중앙정렬(좌~우)		

//		style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());  //채우기 선택
//		style.setFillPattern(CellStyle.SOLID_FOREGROUND);  //채우기 적용(이거안하면 안채운다)	
		return style;
	}
	
}
