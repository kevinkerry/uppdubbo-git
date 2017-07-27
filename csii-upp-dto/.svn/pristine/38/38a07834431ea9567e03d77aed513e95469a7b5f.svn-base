package com.csii.upp.dto.router.eaccount;

import com.csii.upp.constant.EAccountTransCode;
import com.csii.upp.dto.extend.InputFundData;

public class ReqQueryPerson extends ReqEAccountHead{

	public ReqQueryPerson(InputFundData data) {
		super(data);
		this.setPersnbr(data.getPersnbr());
		this.setTransCode(EAccountTransCode.QueryPerson);
	}
	
	private String persnbr;
	private String persname;
	private String persidtypcd;
	private String idnbr;
	public String getPersnbr() {
		return persnbr;
	}
	public void setPersnbr(String persnbr) {
		this.persnbr = persnbr;
	}
	public String getPersname() {
		return persname;
	}
	public void setPersname(String persname) {
		this.persname = persname;
	}
	public String getPersidtypcd() {
		return persidtypcd;
	}
	public void setPersidtypcd(String persidtypcd) {
		this.persidtypcd = persidtypcd;
	}
	public String getIdnbr() {
		return idnbr;
	}
	public void setIdnbr(String idnbr) {
		this.idnbr = idnbr;
	}

}
