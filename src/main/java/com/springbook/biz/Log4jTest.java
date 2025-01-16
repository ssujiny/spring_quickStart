package com.springbook.biz;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 톰캣 구동 없이 테스트용
public class Log4jTest {

		// 자바 애플리케이션으로 로그 찍을때는 루트 디렉터리 기준!
		private static final Logger logger = LoggerFactory.getLogger(Log4jTest.class);
		
		public static void main(String[] args) { 
			logger.info("Log4j initialization test"); 
		}
	

}