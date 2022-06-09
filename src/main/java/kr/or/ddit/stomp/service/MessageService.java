/**
 * 
 */
package kr.or.ddit.stomp.service;

import java.util.Map;

/**
* @author 민진홍
 * @since 2022. 5. 25.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 5. 25.      민진홍       메시지서비스
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */

public interface MessageService {
	public int MessageSend(Map<String, Object> map);
}
