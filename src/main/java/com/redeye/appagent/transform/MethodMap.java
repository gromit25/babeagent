package com.redeye.appagent.transform;

import java.util.HashMap;
import java.util.Map;

import com.redeye.appagent.exception.AgentException;
import com.redeye.appagent.util.StringUtil;

/**
 * 메소드 변환 Map
 * 
 * @author jmsohn
 */
public class MethodMap {
	
	/** 메소드 변환 Map 객체 */
	private static Map<String, MethodPair> map;
	
	/**
	 * Method Map 초기화
	 * 
	 * @param targetClasses
	 */
	public static void init(Class<?>... targetClasses) throws Exception {
		
		// 입력값 검증
		if(targetClasses == null) {
			throw new AgentException("target classes is null.");
		}
		
		// Method Map 객체 생성
		map = new HashMap<>();
		
		// 각 클래스 로드
		for(Class<?> targetClass: targetClasses) {
			
			for(MethodPair methodPair: MethodPair.load(targetClass)) {
				map.put(methodPair.getKey(), methodPair);
			}
		}
	}
	
	/**
	 * 주어진 메소드가 map 대상 여부 반환
	 * 
	 * @param className 클래스 명
	 * @param methodName 메소드 명
	 * @param signature 시그니처
	 * @return 주어진 메소드가 map 대상 여부
	 */
	public static boolean isTarget(
		String className,
		String methodName,
		String signature
	) {
		
		// class 명, method 명, signature 가 blank 인 경우 false 반환
		if(StringUtil.isBlank(className) == true) {
			return false;
		}
		
		if(StringUtil.isBlank(methodName) == true) {
			return false;
		}
		
		if(StringUtil.isBlank(signature) == true) {
			return false;
		}
		
		// map 에 있는지 여부 반환
		return map.containsKey(className + "." + methodName + signature);
	}
	
	/**
	 * 대상 메소드 스펙으로 변환할 메소드 스펙을 반환<br>
	 * 없을 경우 null 을 반환
	 * 
	 * @param targetSpec 대상 메소드 스펙
	 * @return 변환할 메소드 스펙
	 */
	public static MethodSpec getAltMethod(MethodSpec targetSpec) {
		
		// 입력값 검증
		if(targetSpec == null) {
			return null;
		}
		
		// 맵이 초기화 되지 않았을 경우 null 반환
		if(map == null) {
			return null;
		}
		
		// 맵에서 변환할 메소드 스팩을 반환
		String key = targetSpec.toString();
		if(map.containsKey(key) == true) {
			return map.get(key).getAlterMethod();
		} else {
			return null;
		}
	}
}
