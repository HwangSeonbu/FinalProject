package kr.or.ddit.commons.listener;

import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.FrameworkServlet;

import kr.or.ddit.commons.controller.MainController.Testevent;
import kr.or.ddit.stomp.MessageType;
import kr.or.ddit.stomp.MessageVO;
import kr.or.ddit.vo.MemberVO;

/**
 * 
 * @author 민진홍
 * @since 2022. 5. 24.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 5. 24.      민진홍	STOMP 메세지 송신
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Component
public class CustomEventListener {
	@Inject
	private WebApplicationContext root;
	

	
	@EventListener(value=Testevent.class)
	public void test(Testevent event) {
//		WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(sc, DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(root.getServletContext(), FrameworkServlet.SERVLET_CONTEXT_PREFIX + "springDispatcherServlet");
		SimpMessagingTemplate messagingTemplate = wac.getBean(SimpMessagingTemplate.class);
		CommandVO<MessageVO> vo = (CommandVO) event.getSource();
		
		MessageVO messageBody = MessageVO.builder()
				.messageType(vo.getTarget().getMessageType())
				.message(vo.getTarget().getMessage())
				.sender(vo.getTarget().getSender())
				.build();
				messagingTemplate.convertAndSend(vo.getDest(), messageBody);
	}
}
