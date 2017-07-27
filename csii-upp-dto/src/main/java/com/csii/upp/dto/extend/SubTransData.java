package com.csii.upp.dto.extend;

import com.csii.upp.dto.generate.Meracctinfo;
import com.csii.upp.dto.generate.Onlinesubtrans;

public class SubTransData extends Onlinesubtrans{
	private Meracctinfo subMerAcct;
	private String merName;
	public Meracctinfo getSubMerAcct() {
		return subMerAcct;
	}

	public void setSubMerAcct(Meracctinfo subMerAcct) {
		this.subMerAcct = subMerAcct;
	}

	public String getMerName() {
		return merName;
	}

	public void setMerName(String merName) {
		this.merName = merName;
	}
	
}
