package com.redeye.appagent.builtins.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.redeye.appagent.annotation.TargetClass;
import com.redeye.appagent.annotation.TargetMethod;
import com.redeye.appagent.logger.Log;

/**
 * JDBC 드라이버 관리자(java/sql/DriverManager) Wrapper
 * 
 * @author jmsohn
 */
@TargetClass(type = "DB", cls = "java/sql/DriverManager")
public class DriverManagerWrapper {
	
	/**
	 * DB 접속시 로깅
	 * 
	 * @param conn DB 연결 객체
	 * @param url DB 연결 url
	 */
	private static void logConn(Connection conn, String url) {
		
		Log.write(
			ActionType.DB_CON.name(),
			conn,
			"\"url\":\"%s\"", url
		);
	}
	
	/**
	 *  getConnection Wrapper 메소드
	 * 
	 * @param url 데이터베이스 연결 url
	 * @param user DB 접속 사용자명
	 * @param password DB 접속 패스워드
	 */
	@TargetMethod("getConnection(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/sql/Connection;")
	public static Connection getConnection(String url, String user, String password) throws SQLException {
		
		Connection conn = DriverManager.getConnection(url, user, password);
		logConn(conn, url);
		
		return conn;
	}
	
	/**
	 * getConnection Wrapper 메소드
	 * 
	 * @param url DB 연결 url
	 */
	@TargetMethod("getConnection(Ljava/lang/String;)Ljava/sql/Connection;")
	public static Connection getConnection(String url) throws SQLException {
		
		Connection conn = DriverManager.getConnection(url);
		logConn(conn, url);
		
		return conn;
	}
}
