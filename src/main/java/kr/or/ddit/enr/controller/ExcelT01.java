//package kr.or.ddit.enr.controller;
//
//import java.io.BufferedInputStream;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import com.fasterxml.jackson.databind.exc.InvalidFormatException;
//import net.sf.jxls.exception.ParsePropertyException;
//import net.sf.jxls.transformer.XLSTransformer;
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
//public class ExcelT01 {
//	Logger logger = LoggerFactory.getLogger(this.getClass()); //로거
//	
//	public void download(HttpServletRequest request, HttpServletResponse response, Map<String, Object> model, String fileName, String templateFile)
//            throws ParsePropertyException, InvalidFormatException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
//
//		String tempPath = request.getSession().getServletContext().getRealPath("/resources"); //temp파일이 있는 경로
//		
//		try {
//		    InputStream is = new BufferedInputStream(new FileInputStream(tempPath + "\\" + templateFile));
//		    XLSTransformer xls = new XLSTransformer();
//		    Workbook workbook = xls.transformXLS(is, model);
//		    
//		    String outputFileName = new String(fileName.getBytes("KSC5601"), "8859_1"); //한글명 에러 방지
//		    response.setHeader("Content-Disposition", "attachment; filename=\"" + outputFileName + ".xlsx\"");
//		    
//		    OutputStream os = response.getOutputStream();
//		    
//		    workbook.write(os);
//		    
//		    logger.info(">>>>>>>>> Make and Download Excel File Success"); //로그 찍기
//			logger.info("          fileName : " + fileName + ".xlsx");
//		} catch (IOException e) {
//		    e.printStackTrace();
//		}
//	}
//	
//	
//}