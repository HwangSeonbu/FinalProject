package kr.or.ddit.advice;

import org.aspectj.lang.ProceedingJoinPoint;

import lombok.extern.slf4j.Slf4j;

/**
 * 비즈니스 로직 작동 시간 체크용
 * @author 고성식
 * @since 2022. 4. 20.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 4. 20.  고성식    		   최초작성 
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Slf4j
public class LoggingAdvice {
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		log.info(" 비즈니스 로직{} 시작", joinPoint);
		long start = System.currentTimeMillis();
		// target 호출
		Object[] args = joinPoint.getArgs();
		Object retValue = joinPoint.proceed(args);
		long end = System.currentTimeMillis();
		log.info("  비즈니스 로직{} 종료 : {}", joinPoint, (end - start));
		return retValue;
	}
}
