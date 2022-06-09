package kr.or.ddit.board.view;

import java.io.File;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.view.AbstractView;

import kr.or.ddit.vo.AttchVO;

/**
 * @author 작성자명
 * @since 2022. 5. 16.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 5. 16.      이유정       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
public class BoardDownloadView extends AbstractView {
	@Value("#{appInfo['attatchPath']}")
	File projectFolder;

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse resp) throws Exception {
		AttchVO attch = (AttchVO) model.get("attch");
		
		File saveFile = new File(projectFolder, attch.getAttchSname()); 
		String fileName = attch.getAttchFname();
		fileName = URLEncoder.encode(fileName, "UTF-8");
		fileName = fileName.replace("+", " ");
		resp.setHeader("Content-Disposition", "attchment;filename=\""+fileName+"\"");
		resp.setHeader("Content-Length", saveFile.length()+"");
		try(
			OutputStream os = resp.getOutputStream();	
		){
			FileUtils.copyFile(saveFile, os);
			return;
		}
	}
		
}
	

