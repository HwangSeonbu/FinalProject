package kr.or.ddit.stomp;

import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;

import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class EchoMessageHandler {
	
	@Inject
	private SimpMessagingTemplate messagingTemplate;

	@MessageMapping("/handledEcho")
	@SendTo("/topic/echoed")
	public MessageVO handler(@Payload MessageVO messageVO, @Header String id) {
		log.info("id header : {}", id);
		log.info("sender : {}, message : {}", messageVO.getSender(), messageVO.getMessage());
		messageVO.setMessage("서버에서 처리된 메시지 - "+messageVO.getMessage());
		return messageVO;
	}

	
	@MessageMapping({"/DM/**"})
	@SendToUser
	public MessageVO dmHandler(
		@Payload MessageVO messageBody
		, @AuthenticationPrincipal(expression = "realUser") MemberVO authMember
	) {
		String receiver = messageBody.getReceiver();
		messageBody.setSender(authMember.getUserNo()+"");
		messageBody.setMessage("서버에서 가공된 메시지 , "+messageBody.getMessage());
		return messageBody;
		
//		messagingTemplate.convertAndSendToUser(receiver, "/queue/DM1", messageBody);
//		messagingTemplate.convertAndSendToUser(receiver, "/queue/DM2", messageBody);
	}
	
	@MessageMapping("/mine")
	@SendToUser
	public MessageVO dmHandler(
			@Payload MessageVO messageBody
	) throws InterruptedException {
		Thread.sleep(5000);
		messageBody.setMessage("서버에서 지연 처리된 나의 메시지 , "+messageBody.getMessage());
		return messageBody;
	}
	
// destination 이 /app/handledEcho 인 구독 요청에 대해 동작하며, 
// 한번의 요청에 한번의 응답만을 처리하게 됨.
	@SubscribeMapping("/handledEcho")
	public String subscribeHandler(@Headers Map<String, Object> headers) {
		log.info("headers : {}", headers);
		// subscription id 를 생성함.
		String sub_id = UUID.randomUUID().toString();
		return sub_id;
	}
}
