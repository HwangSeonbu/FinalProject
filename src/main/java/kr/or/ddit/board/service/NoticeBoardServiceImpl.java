package kr.or.ddit.board.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.faces.view.AttachedObjectHandler;
import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import kr.or.ddit.board.dao.NBoardAttchDAO;
import kr.or.ddit.board.dao.NoticeBoardDAO;
import kr.or.ddit.exception.PKNotFoundException;
import kr.or.ddit.vo.AttchVO;
import kr.or.ddit.vo.NoticeBoardVO;
import kr.or.ddit.vo.PagingVO;

@Service
public class NoticeBoardServiceImpl implements NoticeBoardService {
	
	@Inject
	private NoticeBoardDAO nBoardDAO;
	@Inject
	private NBoardAttchDAO nBoardAttchDAO;
	@Value("#{appInfo.attatchPath}")
	private File projectFolder;

	@Override
	public void retrieveNBoardList(PagingVO<NoticeBoardVO> paging) {
		
		int totalRecord = nBoardDAO.selectTotalNRecord(paging);
		paging.setTotalRecord(totalRecord);
		List<NoticeBoardVO> dataList = nBoardDAO.selectNBoardList(paging);
		paging.setDataList(dataList);	
	}

	@Override
	public NoticeBoardVO retrieveBoard(Map<String, Object> paramMap) {
		int noticeNo = (int) paramMap.get("noticeNo");
		Object userNo = paramMap.get("userNo");
		int readCnt = 0;
		NoticeBoardVO nBoard = nBoardDAO.selectNBoard(paramMap);
		if(userNo != null) {
			readCnt = nBoardDAO.selectNBoardReadCount(paramMap);
		}
		
		if(userNo != null && readCnt==0) {
			nBoardDAO.insertNBoardReadRecord(paramMap);
		}
		
		if(nBoard==null)
			throw new PKNotFoundException(String.format("%d번 글이 없음", noticeNo));		
		nBoardDAO.incrementHit(noticeNo);		
		return nBoard;
	}


	@Override
	public void removeNBoard(Integer noticeNo) {
		nBoardDAO.deleteNBoard(noticeNo);	
	}

	@Override
	public int modifyNBoard(NoticeBoardVO nBoardVO) {
		int rowcnt = nBoardDAO.updateNBoard(nBoardVO);
		if(rowcnt > 0) {
			//기존 파일 삭제
			deleteAttches(nBoardVO);
			//신규 업로드
			uploadAttch(nBoardVO);
		}
		return rowcnt;
	}
	
	@Override
	public int createNBoard(NoticeBoardVO nBoardVO) {
		int rowcnt = nBoardDAO.insertNBoard(nBoardVO);
		if(rowcnt > 0) {
			uploadAttch(nBoardVO);
		}	
		return rowcnt;
	}	

	@Override
	public void uploadAttch(NoticeBoardVO nBoardVO) {
		List<AttchVO> attchList = nBoardVO.getAttchList();
				if(attchList==null || attchList.isEmpty()) return;
//				업로드 : 2진 데이터(projectFolder) + 메타데이터(attchList)				
				nBoardAttchDAO.insertAttches(nBoardVO);
				attchList.forEach((attch)->{
					try {
						attch.saveTo(projectFolder);
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
				});
			}

	@Override
	public AttchVO downloadAttch(Integer attchNo) {
		AttchVO attch = nBoardAttchDAO.selectAttch(attchNo);
		if(attch==null)
			throw new PKNotFoundException(String.format("%d번 파일이 없음.", attchNo));
			nBoardAttchDAO.incrementDowncount(attchNo);
			return attch;
		
	}
	
	private void deleteAttches(NoticeBoardVO nBoard) {
		int[] delAttNos = nBoard.getDelAttNos();
		if(delAttNos==null || delAttNos.length==0) return;
//		삭제 : 2진 데이터(saveFolder) + 메타데이터(Attatch)
		List<String> saveNames = Arrays.stream(delAttNos).mapToObj((delAttNo)->{
									return nBoardAttchDAO.selectAttch(delAttNo).getAttchSname();
								}).collect(Collectors.toList());
		nBoardAttchDAO.deleteAttches(nBoard);
		saveNames.forEach((saveName)->{
			FileUtils.deleteQuietly(new File(projectFolder, saveName));
		});
	}
	
}
		

