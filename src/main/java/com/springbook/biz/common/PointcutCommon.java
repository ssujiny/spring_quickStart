package com.springbook.biz.common;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

// 외부에 독립된 클래스에 포인트컷 따로 설정
@Aspect
public class PointcutCommon {

	@Pointcut("execution(* com.springbook.biz..*Impl.*(..))")
	public void allPointcut() {}
	
	@Pointcut("execution(* com.springbook.biz..*Impl.get*(..))")
	public void getPointcut() {}
	
	// ex. 속성값으로 "PointcutCommon.allPointcut()" 요런식으로 지정 => "클래스명.참조메소드명"
}
