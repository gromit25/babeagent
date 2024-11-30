package com.babe.wrapper.net;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import com.babe.wrapper.io.OutputStreamWrapper;
import com.redeye.babe.log.ApiType;
import com.redeye.babe.log.Log;

class SocketOutputStreamWrapper extends OutputStreamWrapper {
	
	private Socket socket;

	SocketOutputStreamWrapper(Socket socket, OutputStream out) {
		super(out);
		this.socket = socket;
	}
	
	@Override
	public void write(int b) throws IOException {
		
		Log.writeLog(ApiType.TCP_SOCKET.getApiTypeName()
				, this.os
				, 0
				, "WTS " + SocketUtil.getSocketStatus(this.socket));
		
		long start = System.currentTimeMillis();
		super.write(b);
		long end = System.currentTimeMillis();
		
		Log.writeLog(ApiType.TCP_SOCKET.getApiTypeName()
				, this.os
				, end - start
				, "WCT 1");
	}
	
	@Override
	public void write(byte b[]) throws IOException {
		
		Log.writeLog(ApiType.TCP_SOCKET.getApiTypeName()
				, this.os
				, 0
				, "WTS " + SocketUtil.getSocketStatus(this.socket));
		
		long start = System.currentTimeMillis();
		super.write(b);
		long end = System.currentTimeMillis();
		
		Log.writeLog(ApiType.TCP_SOCKET.getApiTypeName()
				, this.os
				, end - start
				, "WCT " + b.length);
	}
	
	@Override
	public void write(byte b[], int off, int len) throws IOException {
		Log.writeLog(ApiType.TCP_SOCKET.getApiTypeName()
				, this.os
				, 0
				, "WTS " + SocketUtil.getSocketStatus(this.socket));
		
		long start = System.currentTimeMillis();
		super.write(b, off, len);
		long end = System.currentTimeMillis();
		
		Log.writeLog(ApiType.TCP_SOCKET.getApiTypeName()
				, this.os
				, end - start, "WCT " + len);
	}

}
