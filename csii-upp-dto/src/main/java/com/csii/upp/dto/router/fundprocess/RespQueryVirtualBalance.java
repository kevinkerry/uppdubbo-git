/**
 * 
 */
package com.csii.upp.dto.router.fundprocess;

import com.csii.upp.util.StringUtil;

/**
 * @author gaoqi
 */
public class RespQueryVirtualBalance extends RespFundProHead {
	
	private String bdoABal;//当前余额
	private String bdoBBal;// 可用余额 
	private String bdoGBal;//保留余额
	private String userNbr;//客户号
	

	public String getUserNbr() {
		return userNbr;
	}
	public void setUserNbr(String userNbr) {
		userNbr = userNbr;
	}
	public String getBdoABal() {
		if(!StringUtil.isStringEmpty(bdoABal)){
			if(bdoABal.startsWith(".")){
				bdoABal="0"+bdoABal;
			}else if(bdoABal.startsWith("-.")){
				bdoABal=bdoABal.replaceAll("-.", "-0.");
			}
		}
		return bdoABal;
	}
	public void setBdoABal(String bdoABal) {
		this.bdoABal = bdoABal;
	}
	public String getBdoBBal() {
		if(!StringUtil.isStringEmpty(bdoBBal)){
			if(bdoBBal.startsWith(".")){
				bdoBBal="0"+bdoBBal;
			}else if(bdoBBal.startsWith("-.")){
				bdoBBal=bdoBBal.replaceAll("-.", "-0.");
			}
		}
		
		return bdoBBal;
	}
	public void setBdoBBal(String bdoBBal) {
		this.bdoBBal = bdoBBal;
	}
	public String getBdoGBal() {
		if(!StringUtil.isStringEmpty(bdoGBal)){
			if(bdoGBal.startsWith(".")){
				bdoGBal="0"+bdoGBal;
			}else if(bdoGBal.startsWith("-.")){
				bdoGBal=bdoGBal.replaceAll("-.", "-0.");
			}
		}
		return bdoGBal;
	}
	public void setBdoGBal(String bdoGBal) {
		this.bdoGBal = bdoGBal;
	}

}
