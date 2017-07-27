/**
 * 
 */
package com.csii.upp.dto.router.ibps;

import java.util.Map;

import com.csii.upp.constant.ResponseCode;
import com.csii.upp.util.StringUtil;

/**
 * 直销银行超级网银对账申请输出参数
 * 
 * @author 姜星
 *
 */
public class RespIbpsCheckApply extends RespIbpsHead {
	private String collatingdate; // 对账日期
	private Long ibpssendtotal; // 往账总笔数
	private Long ibpsrcvtotal; // 来账总笔数
	private Long ibpstotalnum; // 总笔数
	private String msgtype; // 报文类型
	private String filepath; // 文件路径
	private String fileName;//文件名
	public String getCollatingdate() {
		return collatingdate;
	}
	public void setCollatingdate(String collatingdate) {
		this.collatingdate = collatingdate;
	}
	public Long getIbpssendtotal() {
		return ibpssendtotal;
	}
	public void setIbpssendtotal(Long ibpssendtotal) {
		this.ibpssendtotal = ibpssendtotal;
	}
	public Long getIbpsrcvtotal() {
		return ibpsrcvtotal;
	}
	public void setIbpsrcvtotal(Long ibpsrcvtotal) {
		this.ibpsrcvtotal = ibpsrcvtotal;
	}
	public Long getIbpstotalnum() {
		return ibpstotalnum;
	}
	public void setIbpstotalnum(Long ibpstotalnum) {
		this.ibpstotalnum = ibpstotalnum;
	}
	public String getMsgtype() {
		return msgtype;
	}
	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		if(StringUtil.isStringEmpty(fileName))
			return;
		this.fileName = fileName;
	}
	
	@Override
	public void setHead(Map<String, Object> head) {
		this.setReturncode(StringUtil.parseObjectToString(head.get("RspCod")));
		this.setReturnmsg(StringUtil.parseObjectToString(head.get("RspMsg")));
		String returnCode=this.getReturncode().substring(2, this.getReturncode().length());
		if("0I0000".equals(returnCode)){
			this.setResultStatus(ResponseCode.SUCCESS);
			this.setFileName(StringUtil.parseObjectToString(head.get("FileName")));
		}else{
			this.setResultStatus(ResponseCode.FAILURE);
		}
		this.setRtxnstateByAttribute();
	}
}
