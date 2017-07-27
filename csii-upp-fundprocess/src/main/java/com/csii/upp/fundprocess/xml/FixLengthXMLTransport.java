package com.csii.upp.fundprocess.xml;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.csii.pe.service.comm.CommunicationException;
import com.csii.pe.service.comm.tcp.AbstractTcpTransport;
import com.csii.upp.util.StringUtil;

/**
 * 固定格式报文长度头的XML发送处理（晋商银行使用）
 * 
 * 00123<?xml version="1.0" encoding="UTF-8"?><service>…………</service>
 * 
 */
public class FixLengthXMLTransport extends AbstractTcpTransport {
	private int headLength;
	private Pattern beginPattern;
	private Pattern endPattern;

	protected Object readStream(InputStream input)
			throws CommunicationException, IOException {
		BufferedReader lineReader = new BufferedReader(new InputStreamReader(
				input, "utf-8"));
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		Matcher matcher;
		boolean getContent = false;
		do {
			String line = lineReader.readLine();
			if (line == null) {
				break;
			}
			if (!getContent) {
				matcher = beginPattern.matcher(line);
				if (matcher.find()) {
					getContent = true;
					line = line.substring(matcher.start());
				}
			}
			if (getContent) {
				bout.write(line.getBytes("utf-8"));
				bout.write("\r\n".getBytes());
			}

			matcher = endPattern.matcher(line);
		} while (!(matcher.find()));

		return bout.toByteArray();
	}

	protected void writeStream(OutputStream out, byte[] sndBuffer)
			throws IOException {
		DataOutputStream dos = new DataOutputStream(out);
		dos.write(String.format("%0" + headLength + "d", sndBuffer.length)
				.getBytes());
		dos.write(sndBuffer);
		dos.flush();
	}

	public void setHeadLength(int headLength) {
		this.headLength = headLength;
	}

	public void setEndTagName(String endTagName) {
		beginPattern = Pattern.compile(StringUtil.buildString("< *",
				endTagName, " *>"));
		endPattern = Pattern.compile(StringUtil.buildString("</ *", endTagName,
				" *>"));
	}
}
