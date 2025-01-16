package com.springbook.biz.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

// 강한 결합 상태 -> 어노테이션 AOP 추가
@Service
public class LogAdvice {
	@Pointcut("execution(* com.springbook.biz..*Impl.*(..))")
	public void allPointcut() {}
	
	@Pointcut("execution(* com.springbook.biz..*Impl.get*(..))")
	public void getPointcut() {}
	
	//public void printLog(JoinPoint jp) {
	//	System.out.println("[공통 로그] 비즈니스 로직 수행 전 동작");
	//}
}
