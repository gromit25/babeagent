package com.redeye.appagent.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Wrapper 클래스 어노테이션
 * 
 * @author jmsohn
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Wrapper {
	
	/**
	 * Wrapping 대상 클래스 명<br>
	 * ex. com/redeye/Test 형태
	 * 
	 * @return Wrapping 대상 클래스 명
	 */
	String value();
}