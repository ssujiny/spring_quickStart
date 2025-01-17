package com.springbook.biz.common;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

// 강한 결합 상태 -> 어노테이션 AOP 추가
@Service
@Aspect // Pointcut + Advice
public class LogAdvice {
	@Pointcut("execution(* com.springbook.biz..*Impl.*(..))")
	public void allPointcut() {}
	
	@Pointcut("execution(* com.springbook.biz..*Impl.get*(..))")
	public void getPointcut() {}
	
	// Advice(횡단관심), 동작 시점을 나타내는 어노테이션
	@Before("allPointcut()")
	public void printLog() {
		System.out.println("[공통 로그] 비즈니스 로직 수행 전 동작");
	}
	
	/* @Aspect: 스프링 컨테이너가 애스팩트 객체로 인식
	 * @Pointcut & @Before -> 위빙 처리된다.
	 * (포인트컷 메소드가 호출될 때, 어드바이스 메소드 실행되도록 설정)
	 * */
}
