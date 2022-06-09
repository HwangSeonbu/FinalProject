//package kr.or.ddit.enr.controller;
//
//import java.io.*;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import java.util.Iterator;
//import com.itextpdf.text.*;
//import com.itextpdf.text.pdf.*;
//
///**
// * 
// * @author 민진홍
// * @since 2022. 5. 4.
// * @version 1.0
// * @see javax.servlet.http.HttpServlet
// * <pre>
// * [[개정이력(Modification Information)]]
// * 수정일                          수정자               수정내용
// * --------     --------    ----------------------
// * 2022. 5. 4.      작성자명       최초작성
// * 실패
// * Copyright (c) 2022 by DDIT All right reserved
// * </pre>
// */
//public class ExcelT03 {
//		public static void main(String[] args) throws Exception{
//			FileInputStream input_document = new FileInputStream(new File("C:\\Users\\PC-01\\Desktop\\sample.xlsx"));
//			
//			// Read workbook into HSSFWorkbook
//			XSSFWorkbook my_xls_workbook = new XSSFWorkbook(input_document); 
//			
//			// Read worksheet into HSSFSheet
//			XSSFSheet my_worksheet = my_xls_workbook.getSheetAt(0); 
//			
//			// To iterate over the rows
//			Iterator<Row> rowIterator = my_worksheet.iterator();
//			
//			// We will create output PDF document objects at this point
//			Document iText_xls_2_pdf = new Document();
//			PdfWriter.getInstance(iText_xls_2_pdf, new FileOutputStream("Excel2PDF_Output.pdf"));
//			iText_xls_2_pdf.open();
//			
//			// We have two columns in the Excel sheet, so we create a PDF table with two columns
//			// Note: There are ways to make this dynamic in nature, if you want to.
//			PdfPTable my_table = new PdfPTable(2);
//			
//			// We will use the object below to dynamically add new data to the table
//			PdfPCell table_cell;
//			
//			// Loop through rows.
//			while (rowIterator.hasNext()) {
//				Row row = rowIterator.next(); 
//				Iterator<Cell> cellIterator = row.cellIterator();
//				while (cellIterator.hasNext()) {
//					Cell cell = cellIterator.next(); //Fetch CELL
//					switch (cell.getCellType()) { //Identify CELL type
//						//you need to add more code here based on your requirement / transformations
//						case Cell.CELL_TYPE_STRING:
//							//Push the data from Excel to PDF Cell
//	                        			//feel free to move the code below to suit to your needs
//							table_cell=new PdfPCell(new Phrase(cell.getStringCellValue()));
//							my_table.addCell(table_cell);
//							break;
//					}
//				}
//
//			}
//			
//			// Finally add the table to PDF document
//			iText_xls_2_pdf.add(my_table);                       
//			iText_xls_2_pdf.close();                
//			
//			// we created our pdf file..
//			input_document.close(); //close xls
//		}
//	}
//
//
