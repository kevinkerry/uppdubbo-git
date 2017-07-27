package com.csii.upp.custom.common.api.data.fundprocess;

import com.csii.upp.custom.common.api.data.base.FundProcessRespHead;

public class SYMIResp extends FundProcessRespHead {
	private static final long serialVersionUID = -6952539662281723912L;
    private String alipayMerchantId; 
    private String weChatSubMerchatId; 
    private String proxyMerNbr; 
    public void setAlipayMerchantId(String alipayMerchantId) {
        this.alipayMerchantId = alipayMerchantId;
    }
    public String getAlipayMerchantId() {
        return alipayMerchantId;
    }

    public void setWeChatSubMerchatId(String weChatSubMerchatId) {
        this.weChatSubMerchatId = weChatSubMerchatId;
    }
    public String getWeChatSubMerchatId() {
        return weChatSubMerchatId;
    }

    public void setProxyMerNbr(String proxyMerNbr) {
        this.proxyMerNbr = proxyMerNbr;
    }
    public String getProxyMerNbr() {
        return proxyMerNbr;
    }


}
