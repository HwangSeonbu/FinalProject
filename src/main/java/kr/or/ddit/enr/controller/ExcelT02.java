//package kr.or.ddit.enr.controller;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
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
// * 2022. 5. 4.      민진홍      ExcelT01.java 와 사용하면 jxls를 이용해서 엑셀 입,출력 가능 그러나 pdf 변환에실패해서 그만함
// * 
// * Copyright (c) 2022 by DDIT All right reserved
// * </pre>
// */
//@Controller
//public class ExcelT02 {
//
//	@RequestMapping(value = "/api/excel")
//		public void downloadExcel(HttpServletRequest request,
//                HttpServletResponse response,
//                @RequestParam(value = "year", required=false) String year) throws Exception{
//
//
//Map<String, Object> outputMap = new HashMap<String, Object>();
//
//String title1 =  "년 데이터(A type)";
//String title2 =  "년 데이터(B type)";
//
//
//outputMap.put("title1", title1);
//outputMap.put("title2", title2);
//
//
////파일명 가공
//String newFileName = "test";
//
////위의 엑셀 가공 모듈 불러와서 실행
//ExcelT01 mje = new ExcelT01();
//mje.download(request, response, outputMap, newFileName, "workbook.xlsx");
////mje.download(request, response, 데이터 담긴 map, 다운로드 파일명, 템플릿 파일명);
//}
//}