package com.csii.upp.dto.router.eaccount;

import java.util.Date;

import com.csii.upp.idto.IDto;

public class PersInfo implements IDto{

	private Long persnbr;
	private String crcd;
	private String salucd;
	private String persname;
	private Date adddate;
	private Date datebirth;
	private Date datedeath;
	private String educlevcd;
	private String incomelevcd;
	private String occptncd;
	private String validyn;
	private Long locorgnbr;
	private String persidtypcd;
	private String idnbr;
	public Long getPersnbr() {
		return persnbr;
	}
	public void setPersnbr(Long persnbr) {
		this.persnbr = persnbr;
	}
	public String getCrcd() {
		return crcd;
	}
	public void setCrcd(String crcd) {
		this.crcd = crcd;
	}
	public String getSalucd() {
		return salucd;
	}
	public void setSalucd(String salucd) {
		this.salucd = salucd;
	}
	public String getPersname() {
		return persname;
	}
	public void setPersname(String persname) {
		this.persname = persname;
	}
	public Date getAdddate() {
		return adddate;
	}
	public void setAdddate(Date adddate) {
		this.adddate = adddate;
	}
	public Date getDatebirth() {
		return datebirth;
	}
	public void setDatebirth(Date datebirth) {
		this.datebirth = datebirth;
	}
	public Date getDatedeath() {
		return datedeath;
	}
	public void setDatedeath(Date datedeath) {
		this.datedeath = datedeath;
	}
	public String getEduclevcd() {
		return educlevcd;
	}
	public void setEduclevcd(String educlevcd) {
		this.educlevcd = educlevcd;
	}
	public String getIncomelevcd() {
		return incomelevcd;
	}
	public void setIncomelevcd(String incomelevcd) {
		this.incomelevcd = incomelevcd;
	}
	public String getOccptncd() {
		return occptncd;
	}
	public void setOccptncd(String occptncd) {
		this.occptncd = occptncd;
	}
	public String getValidyn() {
		return validyn;
	}
	public void setValidyn(String validyn) {
		this.validyn = validyn;
	}
	public Long getLocorgnbr() {
		return locorgnbr;
	}
	public void setLocorgnbr(Long locorgnbr) {
		this.locorgnbr = locorgnbr;
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
