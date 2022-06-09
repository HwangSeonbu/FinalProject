package kr.or.ddit.student.service;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.exception.PKNotFoundException;
import kr.or.ddit.student.dao.StudentDAO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.StudentVO;

/**
 * @author 고성식
 * @since 2022. 4. 26.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 4. 26.   고성식   		    최초작성 
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Service
public class StudentServiceImpl implements StudentService {

	@Inject
	private StudentDAO studentDAO;
	
	@Inject
	PasswordEncoder passwordEncoder;
		
	@Override
	public List<StudentVO> retrieveStudentList(PagingVO<StudentVO> paging) {
		int totalRecord = studentDAO.selectTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		List<StudentVO> dataList = studentDAO.selectStudentList(paging);
		paging.setDataList(dataList);
		return dataList;
	}



	@Override
	public StudentVO retriveStudent(Integer userNo) throws PKNotFoundException {
		StudentVO student = studentDAO.selectStudent(userNo);
		if(student==null)
			throw new PKNotFoundException(userNo+"에 해당하는 회원이 없음.");
		return student;
	}


	@Override
	public void studentUpload(MultipartFile file) throws IOException {
		
		
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		
		if (!extension.equals("xlsx") && !extension.equals("xls")) {
            throw new IOException("엑셀파일만 업로드 해주세요.");
        }
		
		Workbook workbook = null;

        if (extension.equals("xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if (extension.equals("xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        }

        
        Sheet worksheet = workbook.getSheetAt(0);
        
        
        
        
        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
        	Row row = worksheet.getRow(i);
        	
        	
        	StudentVO data = new StudentVO();

            data.setDeptId(row.getCell(0).getStringCellValue());
            data.setStuYear((int)row.getCell(1).getNumericCellValue());
            data.setStuClass(row.getCell(2).getStringCellValue());
            String pass = row.getCell(3).getStringCellValue();
            String encodedPass = passwordEncoder.encode(pass);
            data.setUserPass(encodedPass);
            data.setUserCode("A102");
            data.setUserName(row.getCell(4).getStringCellValue());
            data.setUserGender(row.getCell(5).getStringCellValue());
            data.setUserPhone(row.getCell(6).getStringCellValue());
            data.setUserAddr(row.getCell(7).getStringCellValue());
            data.setUserReg1(row.getCell(8).getStringCellValue());
            data.setUserReg2((int)row.getCell(9).getNumericCellValue());
            data.setUserMail(row.getCell(10).getStringCellValue());
            data.setUserCode(row.getCell(11).getStringCellValue());
            data.setMemRole(row.getCell(12).getStringCellValue());
            data.setStuCode(row.getCell(13).getStringCellValue());
            data.setStuGduCdt((int)row.getCell(14).getNumericCellValue());
            
            // 1. userNo 를 조회하는 select 추가
            int userNo = studentDAO.selectUserNo();
            // 2. 조회한 userNo를 vo.set
            data.setUserNo(userNo);
            //3. 사용자 insert
            studentDAO.insertUser(data);
            //4. 학생 insert
            studentDAO.insertStudent(data);
            
        }
		
	}

}


