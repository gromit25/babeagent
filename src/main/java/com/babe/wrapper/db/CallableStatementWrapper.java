package com.babe.wrapper.db;

import java.sql.CallableStatement;
import java.sql.SQLException;

/**
 * 데이터베이스 CallableStatement 의 Wrapper 클래스
 * 
 * @author jmsohn
 */
public class CallableStatementWrapper {
	
	public static boolean execute(CallableStatement cstmt) throws SQLException {
		
		// 1. 메소드(executeUpdate) 수행전 전처리 부
		String sql = ContentsDB.getSql();
		String hash = ContentsDB.getHash();
		String params = ContentsDB.getParams();
		
		// 2. 메소드(execute) 수행
		long start = System.currentTimeMillis();
		boolean result = cstmt.execute();
		long end = System.currentTimeMillis();
		
		// 3. 메소드(executeQuery) 수행 후 처리부
		
		return result;
	}
	
	public static boolean execute(CallableStatement cstmt, String sql) throws SQLException {
		boolean result = cstmt.execute(sql);
		return result;
	}
	
	public static boolean execute(CallableStatement cstmt, String sql, int autoGeneratedKeys) throws SQLException {
		boolean result = cstmt.execute(sql, autoGeneratedKeys);
		return result;
	}
	
	public static boolean execute(CallableStatement cstmt, String sql, int[] columnIndexes) throws SQLException {
		boolean result = cstmt.execute(sql, columnIndexes);
		return result;
	}
	
	public static boolean execute(CallableStatement cstmt, String sql, String[] columnNames) throws SQLException {
		boolean result = cstmt.execute(sql, columnNames);
		return result;
	}
}
