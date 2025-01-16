package com.springbook.biz.common;

// 강한 결합 상태 - 클래스나 메소드명 변경시 BoardServiceImpl 코드 모두 수정 해야함
// 공통 메소드를 호출하는 코드가 비즈니스 로직 안에 존재 -> 핵심관심/횡단관심 완벽하게 분리 X
public class Log4jAdvice {
	public void printLogging() {
		System.out.println("[공통 로그 - Log4j] 비즈니스 로직 수행 전 동작");
	}
}
