package com.csii.upp.dto.router.cnaps2;

import java.math.BigDecimal;

import com.csii.upp.constant.Cnaps2TransCode;
import com.csii.upp.dto.extend.InputFundData;

/**
 * 普通贷记往帐输入参数
 * 
 * @author xujin
 * 
 */
public class ReqUpbsSimpleCreditRtxn extends ReqCnaps2Head {
	public ReqUpbsSimpleCreditRtxn(InputFundData data) {
		super(data);
		setTransCode(Cnaps2TransCode.UpbsSimpleCredit);
		setPaypathcode(upbsAutoOutput.getPaypathcode());
		setFeetype(upbsAutoOutput.getFeetype());
		setFeeamount(upbsAutoOutput.getFeeamount());
		setPayeraccbank(upbsAutoOutput.getAccbankno());
		setPriority(upbsAutoOutput.getPriority());
		setPayeracc(data.getPayeracctnbr());
		setPayername(data.getPayername());
		this.setPayeraccttypecd(data.getPayeraccttypcd());
		setPayeeaccbank(data.getPayeeacctdeptnbr());
		setPayeeacc(data.getPayeeacctnbr());
		setPayeename(data.getPayeename());
		setAmount(data.getTransamt());
		setCurrency(data.getCurrencycd());
		setPostscript(data.getNote());
		setActy(data.getPayeraccttypcd());
	}

	private RespUpbsAutoRout upbsAutoOutput;

	public void setUpbsAutoOutput(RespUpbsAutoRout upbsAutoOutput) {
		this.upbsAutoOutput = upbsAutoOutput;
	}

	public RespUpbsAutoRout getUpbsAutoOutput() {
		return upbsAutoOutput;
	}

	private String paypathcode;// 汇划路径(统一填写：1001-大额，1002-小额，1020-人行通，1005-平台公共)
	private String busitype = "A100";// 平台业务类型(统一填写：A100-普通汇兑)
	private String busikind = "02103";// 平台业务种类(统一填写：02103-网银支付)
	private String payeracc;// 付款人账号
	private String payername;// 付款人名称
	private String payeraccttypecd;// 付款人账号类型
	private String payeraddr;// 付款人地址
	private String payeraccbank;// 付款人开户行行号
	private String payeeaccbank;// 收款人开户行行号
	private String payeeaccttypcd;// 收款人账号类型(0-对公 1-对私 1020人行通必输)
	private String payeeacc;// 收款人账号
	private String payeename;// 收款人名称
	private String payeeaddr;// 收款人地址
	private String currency;// 币种
	private BigDecimal amount;// 交易金额(有小数点，整数部分最多16位数字，小数部分固定2位数字)
	private String priority = "0";// 业务优先级(2：特急；1：紧急；0：普通；各个渠道暂无该需求，各个渠道默认送0普通 )
	private String cuspwkd = "0";// 是否校验密码(0:不校验密码,1，校验查询密码2:校验交易密码)
	private String cuspswd;// 卡折密码(cuspwkd为1,2时必输)
	private String postscript;// 备注
	private String pathpriority = "2";// 汇路优先级(1:速度优先,2:费用优先[汇路代码为1005必输)
	private String feetype;// 手续费类型(1-邮电费2-手续费 ,汇路代码为1005可不输,其他汇路则必输)
	private BigDecimal feeamount;// 手续费金额(如果无手续费则填0.00,汇路代码为1005可不输,其他汇路则必输)
	private String acty;// 付款人账户类型(与payeracctype送同样的值)

	public String getPaypathcode() {
		return paypathcode;
	}

	public void setPaypathcode(String paypathcode) {
		this.paypathcode = paypathcode;
	}

	public String getBusitype() {
		return busitype;
	}

	public void setBusitype(String busitype) {
		this.busitype = busitype;
	}

	public String getBusikind() {
		return busikind;
	}

	public void setBusikind(String busikind) {
		this.busikind = busikind;
	}

	public String getPayeracc() {
		return payeracc;
	}

	public void setPayeracc(String payeracc) {
		this.payeracc = payeracc;
	}

	public String getPayername() {
		return payername;
	}

	public void setPayername(String payername) {
		this.payername = payername;
	}



	public String getPayeraddr() {
		return payeraddr;
	}

	public void setPayeraddr(String payeraddr) {
		this.payeraddr = payeraddr;
	}

	public String getPayeraccbank() {
		return payeraccbank;
	}

	public void setPayeraccbank(String payeraccbank) {
		this.payeraccbank = payeraccbank;
	}

	public String getPayeeaccbank() {
		return payeeaccbank;
	}

	public void setPayeeaccbank(String payeeaccbank) {
		this.payeeaccbank = payeeaccbank;
	}



	public String getPayeraccttypecd() {
		return payeraccttypecd;
	}

	public void setPayeraccttypecd(String payeraccttypecd) {
		this.payeraccttypecd = payeraccttypecd;
	}

	public String getPayeeaccttypcd() {
		return payeeaccttypcd;
	}

	public void setPayeeaccttypcd(String payeeaccttypcd) {
		this.payeeaccttypcd = payeeaccttypcd;
	}

	public String getPayeeacc() {
		return payeeacc;
	}

	public void setPayeeacc(String payeeacc) {
		this.payeeacc = payeeacc;
	}

	public String getPayeename() {
		return payeename;
	}

	public void setPayeename(String payeename) {
		this.payeename = payeename;
	}

	public String getPayeeaddr() {
		return payeeaddr;
	}

	public void setPayeeaddr(String payeeaddr) {
		this.payeeaddr = payeeaddr;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getCuspwkd() {
		return cuspwkd;
	}

	public void setCuspwkd(String cuspwkd) {
		this.cuspwkd = cuspwkd;
	}

	public String getCuspswd() {
		return cuspswd;
	}

	public void setCuspswd(String cuspswd) {
		this.cuspswd = cuspswd;
	}

	public String getPostscript() {
		return postscript;
	}

	public void setPostscript(String postscript) {
		this.postscript = postscript;
	}

	public String getPathpriority() {
		return pathpriority;
	}

	public void setPathpriority(String pathpriority) {
		this.pathpriority = pathpriority;
	}

	public String getFeetype() {
		return feetype;
	}

	public void setFeetype(String feetype) {
		this.feetype = feetype;
	}

	public BigDecimal getFeeamount() {
		return feeamount;
	}

	public void setFeeamount(BigDecimal feeamount) {
		this.feeamount = feeamount;
	}

	public String getActy() {
		return acty;
	}

	public void setActy(String acty) {
		this.acty = acty;
	}
}
