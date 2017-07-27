package com.csii.upp.fundprocess.xml;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.csii.pe.channel.stream.tcp.SimpleHandler;
import com.csii.pe.service.comm.Channel;
import com.csii.upp.util.StringUtil;

/**
 * 固定格式报文长度头的XML接收处理
 * 
 * 00123<?xml version="1.0" encoding="UTF-8"?><service>…………</service>
 * 
 * @author 徐锦
 *
 */
public class FixLengthXMLHandler extends SimpleHandler {
	private int headLength;
	private Pattern beginPattern;
	private Pattern endPattern;

	protected byte[] readStream(Channel channel) throws Exception {
		InputStream input = channel.getInputStream();

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

			matcher = this.endPattern.matcher(line);
		} while (!(matcher.find()));

		return bout.toByteArray();
	}

	protected void writeStream(Channel channel, byte[] outBuffer)
			throws IOException {
		String temp=new String(outBuffer,"utf-8");
		int lastIndexOf = temp.lastIndexOf("\r\n");
		if(lastIndexOf>0 && lastIndexOf+2==temp.length()){
			temp=temp.substring(0, lastIndexOf);
			outBuffer=temp.getBytes("utf-8");
		}
		DataOutputStream out = new DataOutputStream(channel.getOutputStream());
		out.write(String.format(StringUtil.buildString("%0", headLength, "d"),
				outBuffer.length).getBytes());
		out.write(outBuffer);
		out.flush();
	}

	public void setHeadLength(int headLength) {
		this.headLength = headLength;
	}

	public void setEndTagName(String endTagName) {
		beginPattern = Pattern.compile(StringUtil.buildString("< *",
				endTagName, " *>"));
		endPattern = Pattern.compile(StringUtil.buildString("</ *",
				endTagName, " *>"));
	}
}
