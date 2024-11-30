package com.babe.wrapper.db;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Hashtable;

import com.redeye.babe.log.Log;

/**
 * 데이터베이스 PreparedStatement의  Wrapper클래스
 * 
 * @author jmsohn
 */
public class PreparedStatementWrapper implements PreparedStatement{
	
	private PreparedStatement pstmt;
	private String sql;
	private String sqlhash;
	private Hashtable<Integer, String> params;
	
	public PreparedStatementWrapper(PreparedStatement pstmt, String sql) {
		this.pstmt = pstmt;
		this.setSql(sql);
	}
	
	/**
	 * 데이터베이스 쿼리 수행 정보(ContentsDB)에 파라미터 정보 추가
	 * @param paramIndex 추가 파라미터의 순번 
	 * @param value 추가할 파라미터값
	 */
	private void addParamValue(int paramIndex, String value) {
		
		if(value == null) {
			this.getParams().put(paramIndex, "null");
		} else {
			this.getParams().put(paramIndex, value);
		}
	}
	
	@Override
	public ResultSet executeQuery(String sql) throws SQLException {
		return this.pstmt.executeQuery(sql);
	}

	@Override
	public int executeUpdate(String sql) throws SQLException {
		return this.pstmt.executeUpdate(sql);
	}

	@Override
	public void close() throws SQLException {
		this.pstmt.close();
	}

	@Override
	public int getMaxFieldSize() throws SQLException {
		return this.pstmt.getMaxFieldSize();
	}

	@Override
	public void setMaxFieldSize(int max) throws SQLException {
		this.pstmt.setMaxFieldSize(max);
	}

	@Override
	public int getMaxRows() throws SQLException {
		return this.pstmt.getMaxRows();
	}

	@Override
	public void setMaxRows(int max) throws SQLException {
		this.pstmt.setMaxRows(max);
	}

	@Override
	public void setEscapeProcessing(boolean enable) throws SQLException {
		this.pstmt.setEscapeProcessing(enable);
	}

	@Override
	public int getQueryTimeout() throws SQLException {
		return this.pstmt.getQueryTimeout();
	}

	@Override
	public void setQueryTimeout(int seconds) throws SQLException {
		this.pstmt.setQueryTimeout(seconds);
	}

	@Override
	public void cancel() throws SQLException {
		this.pstmt.cancel();
	}

	@Override
	public SQLWarning getWarnings() throws SQLException {
		return this.pstmt.getWarnings();
	}

	@Override
	public void clearWarnings() throws SQLException {
		this.pstmt.clearWarnings();
	}

	@Override
	public void setCursorName(String name) throws SQLException {
		this.pstmt.setCursorName(name);
	}

	@Override
	public boolean execute(String sql) throws SQLException {
		return this.pstmt.execute(sql);
	}

	@Override
	public ResultSet getResultSet() throws SQLException {
		return this.pstmt.getResultSet();
	}

	@Override
	public int getUpdateCount() throws SQLException {
		return this.pstmt.getUpdateCount();
	}

	@Override
	public boolean getMoreResults() throws SQLException {
		return this.pstmt.getMoreResults();
	}

	@Override
	public void setFetchDirection(int direction) throws SQLException {
		this.pstmt.setFetchDirection(direction);
	}

	@Override
	public int getFetchDirection() throws SQLException {
		return this.pstmt.getFetchDirection();
	}

	@Override
	public void setFetchSize(int rows) throws SQLException {
		this.pstmt.setFetchSize(rows);
	}

	@Override
	public int getFetchSize() throws SQLException {
		return this.pstmt.getFetchSize();
	}

	@Override
	public int getResultSetConcurrency() throws SQLException {
		return this.pstmt.getResultSetConcurrency();
	}

	@Override
	public int getResultSetType() throws SQLException {
		return this.pstmt.getResultSetType();
	}

	@Override
	public void addBatch(String sql) throws SQLException {
		this.pstmt.addBatch(sql);
	}

	@Override
	public void clearBatch() throws SQLException {
		this.pstmt.clearBatch();
	}

	@Override
	public int[] executeBatch() throws SQLException {
		return this.pstmt.executeBatch();
	}

	@Override
	public Connection getConnection() throws SQLException {
		return this.pstmt.getConnection();
	}

	@Override
	public boolean getMoreResults(int current) throws SQLException {
		return this.pstmt.getMoreResults(current);
	}

	@Override
	public ResultSet getGeneratedKeys() throws SQLException {
		return this.pstmt.getGeneratedKeys();
	}

	@Override
	public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
		return this.pstmt.executeUpdate(sql, autoGeneratedKeys);
	}

	@Override
	public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
		return this.pstmt.executeUpdate(sql, columnIndexes);
	}

	@Override
	public int executeUpdate(String sql, String[] columnNames) throws SQLException {
		return this.pstmt.executeUpdate(sql, columnNames);
	}

	@Override
	public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
		return this.pstmt.execute(sql, autoGeneratedKeys);
	}

	@Override
	public boolean execute(String sql, int[] columnIndexes) throws SQLException {
		return this.pstmt.execute(sql, columnIndexes);
	}

	@Override
	public boolean execute(String sql, String[] columnNames) throws SQLException {
		return this.pstmt.execute(sql, columnNames);
	}

	@Override
	public int getResultSetHoldability() throws SQLException {
		return this.pstmt.getResultSetHoldability();
	}

	@Override
	public boolean isClosed() throws SQLException {
		return this.pstmt.isClosed();
	}

	@Override
	public void setPoolable(boolean poolable) throws SQLException {
		this.pstmt.setPoolable(poolable);
	}

	@Override
	public boolean isPoolable() throws SQLException {
		return this.pstmt.isPoolable();
	}

	@Override
	public void closeOnCompletion() throws SQLException {
		this.pstmt.closeOnCompletion();
	}

	@Override
	public boolean isCloseOnCompletion() throws SQLException {
		return this.pstmt.isCloseOnCompletion();
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return this.pstmt.unwrap(iface);
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return this.pstmt.isWrapperFor(iface);
	}

	@Override
	public ResultSet executeQuery() throws SQLException {
		
		// 1. 메소드(executeQuery) 수행전 전처리 부
		Log.writePreSqlLog(this.pstmt, this.getSql(), this.getSqlhash(), this.getParams());
		
		// 2. 메소드(executeQuery) 수행
		long start = System.currentTimeMillis();
		ResultSet rs = this.pstmt.executeQuery();
		long end = System.currentTimeMillis();

		// 3. 메소드(executeQuery) 수행 후 처리부
		Log.writePostSqlLog(this.pstmt, this.getSql(), this.getSqlhash(), start, end);
		
		return rs;
	}

	@Override
	public int executeUpdate() throws SQLException {
		
		// 1. 메소드(executeUpdate) 수행전 전처리 부
		Log.writePreSqlLog(this.pstmt, this.getSql(), this.getSqlhash(), this.getParams());
		
		// 2. 메소드(executeUpdate) 수행
		long start = System.currentTimeMillis();
		int result = this.pstmt.executeUpdate();
		long end = System.currentTimeMillis();

		// 3. 메소드(executeQuery) 수행 후 처리부
		Log.writePostSqlLog(this.pstmt, this.getSql(), this.getSqlhash(), start, end);
		
		return result;
		
	}

	@Override
	public void setNull(int parameterIndex, int sqlType) throws SQLException {
		addParamValue(parameterIndex, "null");
		this.pstmt.setNull(parameterIndex, sqlType);
	}

	@Override
	public void setBoolean(int parameterIndex, boolean x) throws SQLException {
		addParamValue(parameterIndex, Boolean.toString(x));
		this.pstmt.setBoolean(parameterIndex, x);
	}

	@Override
	public void setByte(int parameterIndex, byte x) throws SQLException {
		addParamValue(parameterIndex, Byte.toString(x));
		this.pstmt.setShort(parameterIndex, x);
	}

	@Override
	public void setShort(int parameterIndex, short x) throws SQLException {
		addParamValue(parameterIndex, Short.toString(x));
		this.pstmt.setShort(parameterIndex, x);
	}

	@Override
	public void setInt(int parameterIndex, int x) throws SQLException {
		addParamValue(parameterIndex, Integer.toString(x));
		this.pstmt.setInt(parameterIndex, x);
	}

	@Override
	public void setLong(int parameterIndex, long x) throws SQLException {
		addParamValue(parameterIndex, Long.toString(x));
		this.pstmt.setLong(parameterIndex, x);
	}

	@Override
	public void setFloat(int parameterIndex, float x) throws SQLException {
		addParamValue(parameterIndex, Float.toString(x));
		this.pstmt.setFloat(parameterIndex, x);
	}

	@Override
	public void setDouble(int parameterIndex, double x) throws SQLException {
		addParamValue(parameterIndex, Double.toString(x));
		this.pstmt.setDouble(parameterIndex, x);
	}

	@Override
	public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException {
		if(x != null) {
			addParamValue(parameterIndex, x.toString());
		} else {
			addParamValue(parameterIndex, "null");
		}
		this.pstmt.setBigDecimal(parameterIndex, x);
	}

	@Override
	public void setString(int parameterIndex, String x) throws SQLException {
		if(x != null) {
			addParamValue(parameterIndex, x);
		} else {
			addParamValue(parameterIndex, "null");
		}
		this.pstmt.setString(parameterIndex, x);
	}

	@Override
	public void setBytes(int parameterIndex, byte[] x) throws SQLException {
		if(x != null) {
			addParamValue(parameterIndex, "byte array : " + x.length);
		} else {
			addParamValue(parameterIndex, "null");
		}
		this.pstmt.setBytes(parameterIndex, x);
	}

	@Override
	public void setDate(int parameterIndex, Date x) throws SQLException {
		if(x != null) {
			addParamValue(parameterIndex, x.toString());
		} else {
			addParamValue(parameterIndex, "null");
		}
		this.pstmt.setDate(parameterIndex, x);
	}

	@Override
	public void setTime(int parameterIndex, Time x) throws SQLException {
		if(x != null) {
			addParamValue(parameterIndex, x.toString());
		} else {
			addParamValue(parameterIndex, "null");
		}
		this.pstmt.setTime(parameterIndex, x);
	}

	@Override
	public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException {
		if(x != null) {
			addParamValue(parameterIndex, x.toString());
		} else {
			addParamValue(parameterIndex, "null");
		}
		this.pstmt.setTimestamp(parameterIndex, x);
	}

	@Override
	public void setAsciiStream(int parameterIndex, InputStream x, int length) throws SQLException {
		if(x != null) {
			addParamValue(parameterIndex, x.toString());
		} else {
			addParamValue(parameterIndex, "null");
		}
		this.pstmt.setAsciiStream(parameterIndex, x);
	}

	@Override
	@Deprecated
	public void setUnicodeStream(int parameterIndex, InputStream x, int length) throws SQLException {
		if(x != null) {
			addParamValue(parameterIndex, x.toString());
		} else {
			addParamValue(parameterIndex, "null");
		}
		this.pstmt.setUnicodeStream(parameterIndex, x, length);
	}

	@Override
	public void setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException {
		if(x != null) {
			addParamValue(parameterIndex, x.toString());
		} else {
			addParamValue(parameterIndex, "null");
		}
		this.pstmt.setBinaryStream(parameterIndex, x, length);
	}

	@Override
	public void clearParameters() throws SQLException {
		this.pstmt.clearParameters();
	}

	@Override
	public void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException {
		if(x != null) {
			addParamValue(parameterIndex, x.toString());
		} else {
			addParamValue(parameterIndex, "null");
		}
		this.pstmt.setObject(parameterIndex, x, targetSqlType);
	}

	@Override
	public void setObject(int parameterIndex, Object x) throws SQLException {
		if(x != null) {
			addParamValue(parameterIndex, x.toString());
		} else {
			addParamValue(parameterIndex, "null");
		}
		this.pstmt.setObject(parameterIndex, x);
	}

	@Override
	public boolean execute() throws SQLException {
		return this.pstmt.execute();
	}

	@Override
	public void addBatch() throws SQLException {
		this.pstmt.addBatch();
	}

	@Override
	public void setCharacterStream(int parameterIndex, Reader reader, int length) throws SQLException {
		this.pstmt.setCharacterStream(parameterIndex, reader, length);
	}

	@Override
	public void setRef(int parameterIndex, Ref x) throws SQLException {
		this.pstmt.setRef(parameterIndex, x);
	}

	@Override
	public void setBlob(int parameterIndex, Blob x) throws SQLException {
		this.pstmt.setBlob(parameterIndex, x);
	}

	@Override
	public void setClob(int parameterIndex, Clob x) throws SQLException {
		this.pstmt.setClob(parameterIndex, x);
	}

	@Override
	public void setArray(int parameterIndex, Array x) throws SQLException {
		this.pstmt.setArray(parameterIndex, x);
	}

	@Override
	public ResultSetMetaData getMetaData() throws SQLException {
		return this.pstmt.getMetaData();
	}

	@Override
	public void setDate(int parameterIndex, Date x, Calendar cal) throws SQLException {
		this.pstmt.setDate(parameterIndex, x, cal);
	}

	@Override
	public void setTime(int parameterIndex, Time x, Calendar cal) throws SQLException {
		this.pstmt.setTime(parameterIndex, x, cal);
	}

	@Override
	public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) throws SQLException {
		this.setTimestamp(parameterIndex, x, cal);
	}

	@Override
	public void setNull(int parameterIndex, int sqlType, String typeName) throws SQLException {
		this.pstmt.setNull(parameterIndex, sqlType, typeName);
	}

	@Override
	public void setURL(int parameterIndex, URL x) throws SQLException {
		this.pstmt.setURL(parameterIndex, x);
	}

	@Override
	public ParameterMetaData getParameterMetaData() throws SQLException {
		return this.pstmt.getParameterMetaData();
	}

	@Override
	public void setRowId(int parameterIndex, RowId x) throws SQLException {
		this.pstmt.setRowId(parameterIndex, x);
	}

	@Override
	public void setNString(int parameterIndex, String value) throws SQLException {
		this.pstmt.setNString(parameterIndex, value);
	}

	@Override
	public void setNCharacterStream(int parameterIndex, Reader value, long length) throws SQLException {
		this.pstmt.setNCharacterStream(parameterIndex, value, length);
	}

	@Override
	public void setNClob(int parameterIndex, NClob value) throws SQLException {
		this.pstmt.setNClob(parameterIndex, value);
	}

	@Override
	public void setClob(int parameterIndex, Reader reader, long length) throws SQLException {
		this.pstmt.setClob(parameterIndex, reader, length);
	}

	@Override
	public void setBlob(int parameterIndex, InputStream inputStream, long length) throws SQLException {
		this.pstmt.setBlob(parameterIndex, inputStream, length);
	}

	@Override
	public void setNClob(int parameterIndex, Reader reader, long length) throws SQLException {
		this.pstmt.setNClob(parameterIndex, reader, length);
	}

	@Override
	public void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException {
		this.pstmt.setSQLXML(parameterIndex, xmlObject);
	}

	@Override
	public void setObject(int parameterIndex, Object x, int targetSqlType, int scaleOrLength) throws SQLException {
		this.pstmt.setObject(parameterIndex, x, targetSqlType, scaleOrLength);
	}

	@Override
	public void setAsciiStream(int parameterIndex, InputStream x, long length) throws SQLException {
		this.pstmt.setAsciiStream(parameterIndex, x, length);
	}

	@Override
	public void setBinaryStream(int parameterIndex, InputStream x, long length) throws SQLException {
		this.pstmt.setBinaryStream(parameterIndex, x, length);
	}

	@Override
	public void setCharacterStream(int parameterIndex, Reader reader, long length) throws SQLException {
		this.pstmt.setCharacterStream(parameterIndex, reader, length);
	}

	@Override
	public void setAsciiStream(int parameterIndex, InputStream x) throws SQLException {
		this.pstmt.setAsciiStream(parameterIndex, x);
	}

	@Override
	public void setBinaryStream(int parameterIndex, InputStream x) throws SQLException {
		this.pstmt.setBinaryStream(parameterIndex, x);
	}

	@Override
	public void setCharacterStream(int parameterIndex, Reader reader) throws SQLException {
		this.pstmt.setCharacterStream(parameterIndex, reader);
	}

	@Override
	public void setNCharacterStream(int parameterIndex, Reader value) throws SQLException {
		this.pstmt.setNCharacterStream(parameterIndex, value);
	}

	@Override
	public void setClob(int parameterIndex, Reader reader) throws SQLException {
		this.pstmt.setClob(parameterIndex, reader);
	}

	@Override
	public void setBlob(int parameterIndex, InputStream inputStream) throws SQLException {
		this.pstmt.setBlob(parameterIndex, inputStream);
	}

	@Override
	public void setNClob(int parameterIndex, Reader reader) throws SQLException {
		this.pstmt.setNClob(parameterIndex, reader);
	}
	
	//-----------------------------------------

	private String getSql() {
		if(this.sql == null) {
			return "";
		}
		
		return this.sql;
	}

	private void setSql(String sql) {
		this.sql = sql;
	}

	private String getSqlhash() {
		if(this.sqlhash == null) {
			return "";
		}
		
		return this.sqlhash;
	}

	private Hashtable<Integer, String> getParams() {
		if(this.params == null) {
			this.params = new Hashtable<Integer, String>();
		}
		
		return this.params;
	}
}
