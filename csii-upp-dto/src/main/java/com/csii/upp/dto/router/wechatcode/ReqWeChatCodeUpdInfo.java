package com.csii.upp.dto.router.wechatcode;

import com.csii.upp.constant.QrCodePreTransCode;
import com.csii.upp.dto.extend.InputFundData;
/**
 * 支付宝信息同步请求类
 * @author shell
 *weChatSubMerchatId
 */
public class ReqWeChatCodeUpdInfo extends ReqWeChatCodeHead {
	
	private String mchtShortName;	//商户简称
	private String servicePhone;	//客服电话
	private String subMchId;		//微信子商户号
	public ReqWeChatCodeUpdInfo(InputFundData data) {
		super(data);
		this.setTxnCode(QrCodePreTransCode.WeChatCodeUpdInfo);
		this.setSubMchId(data.getWeChatSubMerchatId());
		this.setMchtShortName(data.getMerShortName());
		this.setServicePhone(data.getServicePhone());
	}
	public String getMchtShortName() {
		return mchtShortName;
	}
	public void setMchtShortName(String mchtShortName) {
		this.mchtShortName = mchtShortName;
	}
	public String getServicePhone() {
		return servicePhone;
	}
	public void setServicePhone(String servicePhone) {
		this.servicePhone = servicePhone;
	}
	public String getSubMchId() {
		return subMchId;
	}
	public void setSubMchId(String subMchId) {
		this.subMchId = subMchId;
	}

	
}
