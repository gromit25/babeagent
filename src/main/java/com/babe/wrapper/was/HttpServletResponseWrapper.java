package com.babe.wrapper.was;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

import javax.servlet.http.HttpServletResponse;

public class HttpServletResponseWrapper {
	
	public static OutputStream getOutputSream(HttpServletResponse response) throws IOException {
		return new ResponseOutputStreamWrapper(response.getOutputStream());
	}
	
	public static Writer getWriter(HttpServletResponse response) throws IOException {
		return new ResponseWriterWrapper(response.getWriter());
	}

}
