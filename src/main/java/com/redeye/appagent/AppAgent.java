package com.redeye.appagent;

import java.lang.instrument.Instrumentation;

import com.redeye.appagent.appwriter.AppTransformer;
import com.redeye.appagent.appwriter.MethodMap;
import com.redeye.appagent.logger.Log;
import com.redeye.appagent.util.AppAgentUtil;
import com.redeye.appagent.util.CronJob;

/**
 * App Agent Main<br>
 * 설정값 로딩 수행 및 스레드 생성 및 동작
 *  
 * @author jmsohn
 */
public final class AppAgent {

	/** 시스템 metrics 정보를 수집하는 크론잡 */
	private static CronJob metricsAcquisitorCronJob;
	
	
	/**
	 * Java VM의 Agent에 Transformer 등록
	 * 
	 * @param args 입력 Argument
	 * @param inst 현재 VM의 Instrument
	 */
	public static void premain(String args, Instrumentation inst) {
		
		try {
			
			//-----------------------
			// 환경 변수에서 설정값을 읽음
			Config.init();
			
			// 메소드 변환맵 초기화
			MethodMap.init(Config.APP_WRITER.getValue());
			
			// App 기동 로깅
			Log.write("APP_START", 0, AppAgentUtil.getSysName());
			
			// 시스템 metrics 정보를 수집하는 크론잡 기동
			try {
				
				if("true".equalsIgnoreCase(Config.METRICS_ACQUISITOR_USE.getValue()) == true) {
					
					metricsAcquisitorCronJob = new CronJob(
						Config.METRICS_ACQUISITOR_SCHEDULE.getValue(),	// 크론잡 스케쥴
						new MetricsAcquisitor()							// 실행할 잡
					);
					
					metricsAcquisitorCronJob.run();
				}
				
			} catch(Exception ex) {
				ex.printStackTrace();
			}
			
			// App 종료 로깅
			Runtime.getRuntime().addShutdownHook(new Thread() {
				
				public void run() {
					
					// App 종료 메시지 출력
					Log.write("APP_END", 0, AppAgentUtil.getSysName());
					
					// 출력큐가 비어 있지 않으면 잠시 대기(0.5초) 후 종료
					if(Log.isEmpty() == false) {
						try {
							Thread.sleep(500);
						} catch(Exception ex) {}
					}
				}
			});
			
			//-----------------------
			// Transformer 등록하여 변환클래스 등록
			AppTransformer transformer = new AppTransformer();
			
			inst.addTransformer(transformer, true);
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
