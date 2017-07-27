package com.csii.upp.servlet;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ReadListener;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LogFilter implements Filter {

	private final Log log = LogFactory.getLog(getClass());

	@Override
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if (response.getCharacterEncoding() == null) {
			response.setCharacterEncoding("UTF-8"); // Or whatever default. UTF-8 is good for World Domination.
		}

		HttpServletRequestCopier requestCopier = new HttpServletRequestCopier((HttpServletRequest)request);
		HttpServletResponseCopier responseCopier = new HttpServletResponseCopier((HttpServletResponse) response);

		try {
			chain.doFilter(requestCopier, responseCopier);
			responseCopier.flushBuffer();
		} finally {
			String output = new String(responseCopier.getCopy(), response.getCharacterEncoding());
			if(output.contains("<soapenv:Fault>")){
				log.error("Request\t" + requestCopier.getRequestContent());
				log.error("Response\t" + output);
			}
		}
	}

	private class HttpServletRequestCopier extends HttpServletRequestWrapper {
		private ServletInputStream inputStream;
		private ByteArrayInputStream bis;
		private ByteArrayOutputStream bos;
		private byte[] buffer;

		public HttpServletRequestCopier(HttpServletRequest request) throws IOException {
			super(request);
			InputStream is = request.getInputStream();
			bos = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			int chr;
			while ((chr = is.read(buf)) > 0) {
				bos.write(buf, 0, chr);
			}
			buffer = bos.toByteArray();
		}

		public ServletInputStream getInputStream() {
			bis = new ByteArrayInputStream(buffer);
			inputStream = new ServletInputStreamCopier(bis);
			return inputStream;
		}
		
		String getRequestContent() throws IOException {
			BufferedReader reader = new BufferedReader(new InputStreamReader(getInputStream()));
			StringBuilder buffer = new StringBuilder();
			String line;
			while((line = reader.readLine())!= null){
				buffer.append(line);
			}
			reader.close();
			return buffer.toString();
		}

	}

	private class ServletInputStreamCopier extends ServletInputStream {
		private ByteArrayInputStream inputStream;

		public ServletInputStreamCopier(ByteArrayInputStream inputStream) {
			this.inputStream = inputStream;
		}

		@Override
		public int read() throws IOException {
			// TODO Auto-generated method stub
			return inputStream.read();
		}

		@Override
		public boolean isFinished() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isReady() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void setReadListener(ReadListener arg0) {
			// TODO Auto-generated method stub
			
		}

	}

	private class HttpServletResponseCopier extends HttpServletResponseWrapper {
		private ServletOutputStream outputStream;
		private PrintWriter writer;
		private ServletOutputStreamCopier copier;

		public HttpServletResponseCopier(HttpServletResponse response) throws IOException {
			super(response);
		}

		@Override
		public ServletOutputStream getOutputStream() throws IOException {
			if (writer != null) {
				throw new IllegalStateException("getWriter() has already been called on this response.");
			}

			if (outputStream == null) {
				outputStream = getResponse().getOutputStream();
				copier = new ServletOutputStreamCopier(outputStream);
			}

			return copier;
		}

		@Override
		public PrintWriter getWriter() throws IOException {
			if (outputStream != null) {
				throw new IllegalStateException("getOutputStream() has already been called on this response.");
			}

			if (writer == null) {
				copier = new ServletOutputStreamCopier(getResponse().getOutputStream());
				writer = new PrintWriter(new OutputStreamWriter(copier, getResponse().getCharacterEncoding()), true);
			}

			return writer;
		}

		@Override
		public void flushBuffer() throws IOException {
			if (writer != null) {
				writer.flush();
			} else if (outputStream != null) {
				copier.flush();
			}
		}

		public byte[] getCopy() {
			if (copier != null) {
				return copier.getCopy();
			} else {
				return new byte[0];
			}
		}

	}

	private class ServletOutputStreamCopier extends ServletOutputStream {
		private OutputStream outputStream;
		private ByteArrayOutputStream copy;

		public ServletOutputStreamCopier(OutputStream outputStream) {
			this.outputStream = outputStream;
			this.copy = new ByteArrayOutputStream(1024);
		}

		@Override
		public void write(int b) throws IOException {
			outputStream.write(b);
			copy.write(b);
		}

		public byte[] getCopy() {
			return copy.toByteArray();
		}

		@Override
		public boolean isReady() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void setWriteListener(WriteListener arg0) {
			// TODO Auto-generated method stub
			
		}
	}
}