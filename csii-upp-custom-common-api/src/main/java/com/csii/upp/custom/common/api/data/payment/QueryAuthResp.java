package com.csii.upp.custom.common.api.data.payment;

import java.io.Serializable;
import java.util.List;


import com.csii.upp.custom.common.api.data.base.PaymentRespHead;

public class QueryAuthResp extends PaymentRespHead {
	private static final long serialVersionUID = -4890779340011204741L;
    private List<AuthDetail> authlist;
    
    public static class  AuthDetail implements Serializable{

		
		private static final long serialVersionUID = -7301325592428606693L;
    	private String customName;
    	private String certtyp;
    	private String certno;
    	private String cardtyp;
    	private String cardno;
    	private String customtyp;
    	private String phoneno;
    	private String transtatus;
		public String getCustomName() {
			return customName;
		}
		public void setCustomName(String customName) {
			this.customName = customName;
		}
		public String getCerttyp() {
			return certtyp;
		}
		public void setCerttyp(String certtyp) {
			this.certtyp = certtyp;
		}
		public String getCertno() {
			return certno;
		}
		public void setCertno(String certno) {
			this.certno = certno;
		}
		public String getCardtyp() {
			return cardtyp;
		}
		public void setCardtyp(String cardtyp) {
			this.cardtyp = cardtyp;
		}
		public String getCardno() {
			return cardno;
		}
		public void setCardno(String cardno) {
			this.cardno = cardno;
		}
		public String getCustomtyp() {
			return customtyp;
		}
		public void setCustomtyp(String customtyp) {
			this.customtyp = customtyp;
		}
		public String getPhoneno() {
			return phoneno;
		}
		public void setPhoneno(String phoneno) {
			this.phoneno = phoneno;
		}
		public String getTranstatus() {
			return transtatus;
		}
		public void setTranstatus(String transtatus) {
			this.transtatus = transtatus;
		}
    	
    }

	public List<AuthDetail> getAuthlist() {
		return authlist;
	}

	public void setAuthlist(List<AuthDetail> authlist) {
		this.authlist = authlist;
	}

}
