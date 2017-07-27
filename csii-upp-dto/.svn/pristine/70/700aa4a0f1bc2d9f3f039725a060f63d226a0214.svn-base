package com.csii.upp.dto.router.core;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.csii.upp.constant.ConstCore;
import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.constant.OveralltransTyp;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.StringUtil;

/**
 * 通用记账 (晋商电子账户与卡互转账)
 * 
 * @author 姜星
 * 
 */
public class ReqGeneralCharge extends ReqCoreHead {
	// 晋商接口
	private String baseacctno; // 账号
	private String ccy; // 币种
	private String accttype; // 产品类型
	private String cardno; // 转出卡号
	private String checkoption; // 检查标志 固定值：0001
	private String password; // 密码（取款交易时，密码必输）
	private String cardpbind; // 卡折标志 C:卡 P:折
	private String effectdate; // 生效日期
	private String inputbaltype; // 余额类型
	private String pbflag;// 存折携带标记
	private BigDecimal tranamt; // 交易金额
	private String strtranamt; // 交易金额
	private String transfermode; // 转账模式 W：一借多贷 D：一贷多借
	private String tranmethod; // 转账方式 固定3
	private List<Map<String, Object>> tranarray; // 交易数组
	
	private String sysCd ;//系统代码

	// 九江个性化元素
	private String dqdh; // 地区代号
	private String jgdh; // 机构代号
	private String ywlx; // 业务类型
	private String czbz; // 操作标志
	private String jdbz; // 借贷标志
	private String xzbz; // 现转标志：0-现金，1-转账
	private String pzdh; // 凭证代号
	private String zhdh; // 账号代号
	private String jydm; // 交易代码
	private BigDecimal jyje1; // 交易金额1：手续费
	private BigDecimal jyje2; // 交易金额2：暂时不使用
	private String dfzhdh; // 对方账号代号：xzbz=0时为空
	private String zydh; // 摘要代号：视项目而定
	private String xjxmdh; // 现金项目代号
	private String qkfs; // 取款方式：不检查密码送空,个人客户检查密码取1
	private String mm; // 密码
	private String tcrq; // 提出日期：前置日期
	private String xtgzh; // 系统跟踪号：前置系统跟踪号

	public ReqGeneralCharge(InputFundData data) {
		super(data);
		this.setCcy(data.getCurrencycd());
		
		this.setEffectdate(DateUtil.Date_To_DateTimeFormat(data.getTransdate(), DateFormatCode.DATE_FORMATTER3)); // TODO 确认格式和取值
		this.setInputbaltype("CA");
		this.setPbflag("N");
		this.setTranamt(data.getTransamt());
		this.setTransfermode("W");
		this.setTranmethod("3");

		//九江代码 
		
		//	super(data);
			//this.setTransCode("FPUR");
			this.setJydm("3181"); // 交易代码
			this.setDqdh("08"); // 地区代号
			this.setJgdh("101"); // 机构代号
			this.setYwlx("91"); // 业务类型
			this.setCzbz("0");  //操作标识
			this.setJdbz("0");  //借贷标识
			this.setXzbz("1"); // 现转标志
			this.setTranamt(data.getTransamt());
			//this.setJyje1(data.getFeeamt());
			//周鹏  当手续费为空的时候   默认值为零
			if(data.getFeeamt() ==null)
			{
				this.setJyje1(BigDecimal.ZERO);
			}else
			{
				this.setJyje1(data.getFeeamt());
			}
			
			this.setTcrq(DateUtil.Date_To_DateTimeFormat(data.getTransdate(),
					DateFormatCode.DATE_FORMATTER3)); // 提出日期：前置日期
			
			this.setCardno(data.getPayeracctnbr());
			this.setDfzhdh(data.getPayeeacctnbr());
			this.setCzbz("1");  //操作标识
			
			setTranamt(data.getTransamt().multiply(new BigDecimal("100")));
			//湖北银行老核心要求交易金额以分为单位且不带小数点
			this.setStrtranamt(StringUtil.BigDel2Str(this.getTranamt()));
			// 取出电子账户对应的内部账户
			String innerAcctNbr=ConstCore.INNERACCTNBR;
			if(OveralltransTyp.RTCT.equals(data.getTransid())||OveralltransTyp.SPCT.equals(data.getTransid())){
				this.setCardno(innerAcctNbr);
			}else if(OveralltransTyp.RTDT.equals(data.getTransid())){
				this.setDfzhdh(innerAcctNbr);
			}
	}

	public String getBaseacctno() {
		return baseacctno;
	}

	public void setBaseacctno(String baseacctno) {
		this.baseacctno = baseacctno;
	}

	public String getCcy() {
		return ccy;
	}

	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	public String getAccttype() {
		return accttype;
	}

	public void setAccttype(String accttype) {
		this.accttype = accttype;
	}

	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public String getCheckoption() {
		return checkoption;
	}

	public void setCheckoption(String checkoption) {
		this.checkoption = checkoption;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCardpbind() {
		return cardpbind;
	}

	public void setCardpbind(String cardpbind) {
		this.cardpbind = cardpbind;
	}

	public String getEffectdate() {
		return effectdate;
	}

	public void setEffectdate(String effectdate) {
		this.effectdate = effectdate;
	}

	public String getInputbaltype() {
		return inputbaltype;
	}

	public void setInputbaltype(String inputbaltype) {
		this.inputbaltype = inputbaltype;
	}

	public String getPbflag() {
		return pbflag;
	}

	public void setPbflag(String pbflag) {
		this.pbflag = pbflag;
	}

	public BigDecimal getTranamt() {
		return tranamt;
	}

	public void setTranamt(BigDecimal tranamt) {
		this.tranamt = tranamt;
	}

	public String getTransfermode() {
		return transfermode;
	}

	public void setTransfermode(String transfermode) {
		this.transfermode = transfermode;
	}

	public String getTranmethod() {
		return tranmethod;
	}

	public void setTranmethod(String tranmethod) {
		this.tranmethod = tranmethod;
	}

	public List<Map<String, Object>> getTranarray() {
		return tranarray;
	}

	public void setTranarray(List<Map<String, Object>> tranarray) {
		this.tranarray = tranarray;
	}
	
	public String getSysCd() {
		return sysCd;
	}

	public void setSysCd(String sysCd) {
		this.sysCd = sysCd;
	}

	// 九江元素 start


	public String getDqdh() {
		return dqdh;
	}

	public void setDqdh(String dqdh) {
		this.dqdh = dqdh;
	}

	public String getJgdh() {
		return jgdh;
	}

	public void setJgdh(String jgdh) {
		this.jgdh = jgdh;
	}

	public String getYwlx() {
		return ywlx;
	}

	public void setYwlx(String ywlx) {
		this.ywlx = ywlx;
	}

	public String getCzbz() {
		return czbz;
	}

	public void setCzbz(String czbz) {
		this.czbz = czbz;
	}

	public String getJdbz() {
		return jdbz;
	}

	public void setJdbz(String jdbz) {
		this.jdbz = jdbz;
	}

	public String getXzbz() {
		return xzbz;
	}

	public void setXzbz(String xzbz) {
		this.xzbz = xzbz;
	}

	public String getPzdh() {
		return pzdh;
	}

	public void setPzdh(String pzdh) {
		this.pzdh = pzdh;
	}

	public String getZhdh() {
		return zhdh;
	}

	public void setZhdh(String zhdh) {
		this.zhdh = zhdh;
	}

	public BigDecimal getJyje1() {
		return jyje1;
	}

	public void setJyje1(BigDecimal jyje1) {
		this.jyje1 = jyje1;
	}

	public BigDecimal getJyje2() {
		return jyje2;
	}

	public void setJyje2(BigDecimal jyje2) {
		this.jyje2 = jyje2;
	}

	public String getDfzhdh() {
		return dfzhdh;
	}

	public void setDfzhdh(String dfzhdh) {
		this.dfzhdh = dfzhdh;
	}

	public String getZydh() {
		return zydh;
	}

	public void setZydh(String zydh) {
		this.zydh = zydh;
	}

	public String getXjxmdh() {
		return xjxmdh;
	}

	public void setXjxmdh(String xjxmdh) {
		this.xjxmdh = xjxmdh;
	}

	public String getQkfs() {
		return qkfs;
	}

	public void setQkfs(String qkfs) {
		this.qkfs = qkfs;
	}

	public String getMm() {
		return mm;
	}

	public void setMm(String mm) {
		this.mm = mm;
	}

	public String getTcrq() {
		return tcrq;
	}

	public void setTcrq(String tcrq) {
		this.tcrq = tcrq;
	}

	public String getXtgzh() {
		return xtgzh;
	}

	public void setXtgzh(String xtgzh) {
		this.xtgzh = xtgzh;
	}

	public String getJydm() {
		return jydm;
	}

	public void setJydm(String jydm) {
		this.jydm = jydm;
	}

	public String getStrtranamt() {
		return strtranamt;
	}

	public void setStrtranamt(String strtranamt) {
		this.strtranamt = strtranamt;
	}

	// 九江元素 end
}
