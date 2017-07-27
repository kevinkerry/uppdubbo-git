package com.csii.upp.dto.router.paym;

import java.util.List;
import java.util.Map;

import com.csii.upp.constant.PaymTransCode;
import com.csii.upp.dto.extend.InputPaygateData;
/**
 * 新添订单信息
 * @author dell
 *
 */
public class ReqQueryQrCodeStatus  extends ReqPaymHead{
	private String codeTypCd;
	private String scanCodeMode;
	private String payAccessType;
	private String productId;
	private String proBody;
	private String subject;
	private String storeId;
	private String scene;
	private String authCode;
	private String payType;
	
	private List<Map> merTransList;
	public ReqQueryQrCodeStatus(InputPaygateData data) {
		super(data);
		this.setCodeTypCd(data.getCodetypcd());
		this.setTransCode(PaymTransCode.QueryQrCodeStatus);
		this.setMerTransList(data.getMersubtranslist());
		this.setScanCodeMode(data.getScancodemode());
		this.setPayAccessType(data.getPayAccessType());
		this.setProductId(data.getProductId());
		this.setProBody(data.getProBody());
		this.setSubject(data.getSubject());
		this.setStoreId(data.getStoreId());
		this.setScene(data.getScene());
		this.setAuthCode(data.getAuthCode());
		this.setPayType(data.getScancodemode());
	}
	
	public String getCodeTypCd() {
		return codeTypCd;
	}

	public void setCodeTypCd(String codeTypCd) {
		this.codeTypCd = codeTypCd;
	}

	public List<Map> getMerTransList() {
		return merTransList;
	}
	public void setMerTransList(List<Map> merTransList) {
		this.merTransList = merTransList;
	}

	public String getScanCodeMode() {
		return scanCodeMode;
	}

	public void setScanCodeMode(String scanCodeMode) {
		this.scanCodeMode = scanCodeMode;
	}

	public String getPayAccessType() {
		return payAccessType;
	}

	public void setPayAccessType(String payAccessType) {
		this.payAccessType = payAccessType;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProBody() {
		return proBody;
	}

	public void setProBody(String proBody) {
		this.proBody = proBody;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getScene() {
		return scene;
	}

	public void setScene(String scene) {
		this.scene = scene;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}
	

}
