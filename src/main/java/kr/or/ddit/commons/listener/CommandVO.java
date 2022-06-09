package kr.or.ddit.commons.listener;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CommandVO<MessageVO> {
	private final MessageVO target;
	private final String dest;
}
