package com.csii.upp.qrcodeplatform.action.handler;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;

import com.csii.pe.channel.stream.AbstractHandler;
import com.csii.pe.channel.stream.StreamContext;
import com.csii.pe.channel.stream.tcp.TcpClientInfo;
import com.csii.pe.core.ClientInfo;
import com.csii.pe.core.Context;
import com.csii.pe.service.comm.Channel;
import com.csii.pe.service.comm.CommunicationException;
import com.csii.pe.service.session.SessionManager;
import com.csii.pe.transform.Parser;
import com.csii.pe.transform.TransformException;
import com.csii.pe.transform.stream.VariableCounter;
import com.csii.upp.qrcodeplatform.action.constant.Dict;

/**
 * 报文处理
 * 
 * @author xujin
 * 
 */
public class TcpDefaultHandler extends AbstractHandler implements
		InitializingBean {
	private static final Log logger = LogFactory
			.getLog(TcpDefaultHandler.class);

	private int headLength;
	private int lengthAdjust = 0;
	private int offsetOfLengthField;
	private String type;
	private String encoding;
	private boolean headIncluded = true;

	private boolean abandonHead = false;

	private boolean digitalSignature = false;
	private int offsetOfSignatureSwitch;
	private VariableCounter variableCounter;
	private String xmlBodyName;
	private Parser xmlParser;
	private int minLength = 10;
	private ThreadLocal<byte[]> rcvBufTL = new ThreadLocal();
	private static ThreadLocal<String> packetsBuf = new ThreadLocal<String>();
	
	public void setXmlBodyName(String xmlBodyName) {
		this.xmlBodyName = xmlBodyName;
	}

	public void setXmlParser(Parser xmlParser) {
		this.xmlParser = xmlParser;
	}

	public int getMinLength() {
		return minLength;
	}

	public void setMinLength(int minLength) {
		this.minLength = minLength;
	}

	protected Object readChannel(Channel channel) throws Exception {
		byte[] inBuffer = readStream(channel);
		if ((inBuffer == null) || (inBuffer.length <= this.minLength)) {
			return null;
		}
		
		inBuffer = modifyReqPackets(inBuffer);

		if ((this.debug) && (inBuffer != null)) {
			logger.info("recv request msg:"
					+ new String(inBuffer, this.getEncoding()));
			this.rcvBufTL.set(inBuffer);
		}

		Object object = parseStream(channel, inBuffer);

		if (object instanceof Map) {
			Map result = (Map) object;
			byte[] xmlBytes = result.get(xmlBodyName).toString()
					.getBytes(getEncoding());
			result.putAll((Map) xmlParser.parse(new ByteArrayInputStream(
					xmlBytes, 0, xmlBytes.length), result));
			// result.remove(xmlBodyName);
			result.put(Dict.RECV_DATE_TIME, new Date());
		}
		return object;
	}

	// 判断是否为网银交易, 修改请求报文
	private byte[] modifyReqPackets(byte[] reqBinData){
		String packets = null;
		try{
			String reqStr = new String(reqBinData, this.getEncoding());
			String transId = reqStr.substring(8, 12); 
			String transCode = null;
			if(transId.equals("PP01")){
				transCode = "UPP10003";
			}else if(transId.equals("WHDW")){
				transCode = "UPP10015";
			}
			if(transCode == null){
				return reqBinData;
			}
			packetsBuf.set(reqStr);
			log.debug("接收网银请求交易报文：" + reqStr);
			String strLen = reqStr.substring(0, 8);
			Integer intLen = new Integer(strLen); 
			int len = intLen.intValue();
			String newStrLen = new String(new Integer(len - 72).toString());
			StringBuilder len0 = new StringBuilder("00000000".substring(newStrLen.length())).append(newStrLen);
			String systemCode = "01";
			StringBuilder head = len0.append(transCode).append(systemCode);
			packets = head.append(reqStr.substring(90)).toString();
		}catch(Exception e){
			log.error(e.getMessage());
		}
		return packets.getBytes();
	}

	//判断是否为网银交易，修改返回报文
	private byte[] modifyRetPackets(byte[] respBinData){
		try{
			String reqStr = packetsBuf.get();
			if(reqStr == null ){
				return respBinData;
			}
			log.debug("本地存储变量存储的网银请求报文：" + reqStr);
			String originalReqHead = reqStr.substring(8, 90);
			String respPackets = new String(respBinData, this.getEncoding());
			String respPaksLen = respPackets.substring(0, 8);
			Integer intLen = new Integer(respPaksLen); 
			int len = intLen.intValue();
			String retPakShortLen = new String(new Integer(len + 72).toString());
			StringBuilder respPacketsLen = new StringBuilder("00000000".substring(retPakShortLen.length())).append(retPakShortLen);
			String retPak = respPacketsLen.append(originalReqHead).append(respPackets.substring(18)).toString();
			log.debug("返回网银交易报文：" + retPak);
			respBinData = retPak.getBytes();
			packetsBuf.remove();
		}catch(Exception e){
			log.error(e.getMessage());
		}
		return respBinData;
	}
	
	protected void writeChannel(Channel channel, Object writeObject,
			Context context) throws Exception {
		byte[] outBuffer = null;
		outBuffer = prepareResultBuffer(channel, writeObject, context);
		if (outBuffer == null)
			return;
		if (this.debug) {
			logger.info("send responce msg:"
					+ new String(outBuffer, this.getEncoding()));
			this.rcvBufTL.remove();
		}
		outBuffer = modifyRetPackets(outBuffer);
		writeStream(channel, outBuffer);
	}

	protected Context createContext(String arg0, Map arg1, String arg2,
			SessionManager arg3, Channel arg4) {
		ClientInfo clientInfo = new TcpClientInfo(arg4.getAttributes());
		return new StreamContext(arg0, arg1, arg2, arg3, clientInfo,
				arg4.getAttributes());
	}

	protected byte[] readStream(Channel channel) throws Exception {
		InputStream input = channel.getInputStream();
		byte[] headBuffer = new byte[this.headLength];
		for (int offset = 0; offset < this.headLength;) {
			int length = input.read(headBuffer, offset, this.headLength
					- offset);
			if (length < 0) {
				return null;
			}
			offset += length;
		}
		ByteArrayInputStream bi = new ByteArrayInputStream(headBuffer,
				getOffsetOfLengthField(), this.headLength
						- getOffsetOfLengthField());
		try {
			Integer packetLength = (Integer) this.variableCounter.parse(bi,
					null);
			int totalLength;
			if (this.headIncluded) {
				if (!(this.abandonHead)) {
					totalLength = packetLength.intValue();
				} else {
					totalLength = packetLength.intValue() - this.headLength;

					this.headLength = 0;
				}
			} else {
				if (!(this.abandonHead)) {
					totalLength = packetLength.intValue() + this.headLength;
				} else {
					totalLength = packetLength.intValue();

					this.headLength = 0;
				}
			}
			totalLength += this.lengthAdjust;

			byte[] resultBuffer = new byte[totalLength];

			System.arraycopy(headBuffer, 0, resultBuffer, 0, this.headLength);

			int offset = this.headLength;
			while (offset < totalLength) {
				int realLength = input.read(resultBuffer, offset, totalLength
						- offset);
				if (realLength >= 0) {
					offset += realLength;
				} else {
					this.log.error("the length of packet should be :"
							+ totalLength + " but encounter eof at offset:"
							+ offset);
					throw new CommunicationException("invalid_packet_data");
				}
			}

			if (isDigitalSignature()) {
				byte signatureSwitch = headBuffer[getOffsetOfSignatureSwitch()];
				if (signatureSwitch == 89) {
					byte[] signatureLengthBytes = new byte[8];
					int tmpLength = input.read(signatureLengthBytes);
					if (tmpLength != 8) {
						throw new CommunicationException(
								"invalid_signature_head");
					}

					int signatureLength = Integer.parseInt(new String(
							signatureLengthBytes).trim());

					byte[] signatureBuffer = new byte[signatureLength];

					int signatureOffset = 0;
					while (signatureOffset < signatureLength) {
						int realLength = input.read(signatureBuffer,
								signatureOffset, signatureLength
										- signatureOffset);
						if (realLength >= 0) {
							signatureOffset += realLength;
						} else {
							this.log.error("the length of signature should be :"
									+ signatureLength
									+ " but encounter eof at signatureOffset:"
									+ signatureOffset);
							throw new CommunicationException(
									"invalid_signature_data");
						}

					}

					channel.setAttribute("dataPart", resultBuffer);

					channel.setAttribute("signaturePart", signatureBuffer);
				}

			}

			return resultBuffer;
		} catch (TransformException te) {
			throw new CommunicationException(te.getMessageKey());
		}
	}

	protected byte[] prepareResultBuffer(Channel channel, Object writeObject,
			Context context) {
		if (!(this.abandonHead)) {
			return super.prepareResultBuffer(channel, writeObject, context);
		}

		byte[] outBuffer = super.prepareResultBuffer(channel, writeObject,
				context);

		byte[] returnBuffer = new byte[this.headLength + outBuffer.length];

		System.arraycopy(outBuffer, 0, returnBuffer, this.headLength,
				outBuffer.length);
		int packetLength;
		if (this.headIncluded) {
			packetLength = outBuffer.length + this.headLength;
		} else {
			packetLength = outBuffer.length;
		}

		try {
			byte[] lengthBuffer = (byte[]) this.variableCounter.format(
					new Integer(packetLength), null);

			System.arraycopy(lengthBuffer, 0, returnBuffer,
					getOffsetOfLengthField(), lengthBuffer.length);
			return returnBuffer;
		} catch (TransformException te) {
			this.log.error(te);
		}
		return null;
	}

	public String getEncoding() {
		return this.encoding;
	}

	public boolean isHeadIncluded() {
		return this.headIncluded;
	}

	public int getHeadLength() {
		return this.headLength;
	}

	public int getOffsetOfLengthField() {
		return this.offsetOfLengthField;
	}

	public String getType() {
		return this.type;
	}

	public void setEncoding(String string) {
		this.encoding = string;
	}

	public void setHeadIncluded(boolean b) {
		this.headIncluded = b;
	}

	public void setHeadLength(int i) {
		this.headLength = i;
	}

	public void setOffsetOfLengthField(int i) {
		this.offsetOfLengthField = i;
	}

	public void setType(String string) {
		this.type = string;
	}

	public void afterPropertiesSet() throws Exception {
		this.variableCounter = new VariableCounter();
		this.variableCounter.setName("TcpTranport");
		this.variableCounter.setType(getType());
		this.variableCounter.setEncoding(getEncoding());
	}

	public boolean isDigitalSignature() {
		return this.digitalSignature;
	}

	public int getOffsetOfSignatureSwitch() {
		return this.offsetOfSignatureSwitch;
	}

	public void setDigitalSignature(boolean b) {
		this.digitalSignature = b;
	}

	public void setOffsetOfSignatureSwitch(int i) {
		this.offsetOfSignatureSwitch = i;
	}

	public void setAbandonHead(boolean b) {
		this.abandonHead = b;
	}

	public void setLengthAdjust(int lengthAdjust) {
		this.lengthAdjust = lengthAdjust;
	}
}
