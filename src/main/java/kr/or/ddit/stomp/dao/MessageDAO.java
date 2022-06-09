/**
 * 
 */
package kr.or.ddit.stomp.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.ddit.vo.MemberVO;

/**
 * @author 민진홍
 * @since 2022. 5. 25.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 5. 25.      민진홍       메세지
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */

@Mapper
public interface MessageDAO {
	
	public int messageSendInsert(Map<String,Object> info); //MEMO
	public int messageReceiverInsert(int receiver); //RECIEVER
	public List<Map<String,Object>> messageList(int userNo); //받은메시지리스트
	public List<Map<String,Object>> sendMessageList(int userNo); //보낸메시지리스트
	public int messageCount(int userNo); //받은메시지Count
	public List<MemberVO> messageUserList(int userNo); //유저리스트
	public List<MemberVO> messageUserListSearch(Map<String,Object> info); //유저리스트
	public int messageReadFlag(@Param("userNo") int userNo,@Param("memoNo") int memoNo); // 메시지 읽기처리
	public int messageDelete(@Param("userNo") int userNo,@Param("memoNo") int memoNo); // 메시지 삭제처리
	public List<Map<String, String>> messageDeptNameList(); //학과조회
	
	
}
