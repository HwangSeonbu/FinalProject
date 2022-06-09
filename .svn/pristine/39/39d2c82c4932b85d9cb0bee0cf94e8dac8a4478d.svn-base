package kr.or.ddit.counsel.TEST;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.inject.Inject;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.counsel.service.CounselService;
import lombok.extern.slf4j.Slf4j;
@RunWith(SpringRunner.class)
@ContextHierarchy({
	@ContextConfiguration("file:webapp/WEB-INF/spring/*-context.xml")//상위 컨테이너
	,@ContextConfiguration("file:webapp/WEB-INF/spring/appServlet/servlet-context.xml")//하위 컨테이너
})
@WebAppConfiguration
@Slf4j
@Transactional//테스트과정의 트랜잭션은 자동 롤백!!중요!!//단위테스트간 서로 영향을 주지 않도록 트랜잭션 관리함.테스트하고 svn에 커밋해야함!!
public class PoiTest {
	@Inject
	private CounselService service;
	
	
	
	
	
	
	
	public static void main(String[] args) throws FileNotFoundException, IOException{
		
//		Workbook wb = new HSSFWorkbook();

//		try  (OutputStream fileOut = new FileOutputStream("d:/workspace.xls")) {
//		    wb.write(fileOut);
//		}
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		CreationHelper createHelper = workbook.getCreationHelper();

		HSSFSheet sheet = workbook.createSheet();
		HSSFCellStyle style = workbook.createCellStyle();
		
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);  //테두리 종류들
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);	
				
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //중앙 정렬(위~아래)
		style.setAlignment(CellStyle.ALIGN_CENTER);  //중앙정렬(좌~우)		

		style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());  //채우기 선택
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);  //채우기 적용(이거안하면 안채운다)
		for(int i=0; i<=10;i++) {
			
		HSSFRow row = sheet.createRow(i); //열 생성
		Cell cell = row.createCell(0);  //셀 생성
		cell.setCellStyle(style);
		row.createCell(1).setCellValue(
			     createHelper.createRichTextString("상담 학생 목록"));
		
		}
		sheet.autoSizeColumn(2);
		// Write the output to a file
		try (OutputStream fileOut = new FileOutputStream("d:/poi/workbook6.xls")) {
		    workbook.write(fileOut);
		}
	
	
	}
}
