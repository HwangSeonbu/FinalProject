/**
 * 
 */
package kr.or.ddit.vo;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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

@Data
@EqualsAndHashCode(of="memoNo")
public class MessageVO<T> {
	@NotNull
	private int memoNo; //쪽지번호
	@NotNull
	private int userNo; //보낸사람
	private String memoContent; //쪽지내용
	@NotNull
	private String memoDate; //보낸날짜
	
	private List<T> recieverList;
	
	
}
