package com.csii.upp.dto.router.paym;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.csii.upp.constant.PaymTransCode;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.util.StringUtil;

/**
 * 查询二维码交易请求
 * @author wy
 *
 */
public class ReqQueryCodeUrl extends ReqPaymHead {

	private List<Map> merTransList;
	private String scanCodeMode;
	private String codeTypCd;
	private String qrcodeordernbr;
	private String discountableamt;
	private String isCredit;
	private String timeStart;
	private String timeExpire;
	private String customerIp;
	private String productInfoDetail;
	private String productId;
	private String proBody;
	private String subject;
	private String operatorId;
	private String storeId;
	private String termId;
	private String timeoutExpress;
	private String alipayStoreId;
	public ReqQueryCodeUrl(InputPaygateData data) {
		super(data);
		this.setTransCode(PaymTransCode.QueryCodeUrl);
		this.setScanCodeMode(data.getScancodemode());
		this.setQrcodeordernbr(data.getQrcodeordernbr());
		this.setCodeTypCd(data.getCodetypcd());
		this.setDiscountableamt(StringUtil.parseObjectToString(data.getDiscountableamt()));
		this.setIsCredit(data.getIsCredit());
		this.setTimeStart(data.getTimeStart());
		this.setTimeExpire(data.getTimeExpire());
		this.setCustomerIp(data.getCustomerIp());
		this.setProductInfoDetail(data.getProductInfoDetail());
		this.setProductId(data.getProductId());
		this.setProBody(data.getProBody());
		this.setSubject(data.getSubject());
		this.setOperatorId(data.getOperatorId());
		this.setTermId(data.getTermId());
		this.setTimeoutExpress(data.getTimeoutExpress());
		this.setStoreId(data.getStoreId());
		this.setAlipayStoreId(data.getAlipayStoreId());
		this.setMerTransList(this.getmerListMap(data));
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

	public String getQrcodeordernbr() {
		return qrcodeordernbr;
	}

	public void setQrcodeordernbr(String qrcodeordernbr) {
		this.qrcodeordernbr = qrcodeordernbr;
	}

	public String getCodeTypCd() {
		return codeTypCd;
	}

	public void setCodeTypCd(String codeTypCd) {
		this.codeTypCd = codeTypCd;
	}

	public String getDiscountableamt() {
		return discountableamt;
	}

	public void setDiscountableamt(String discountableamt) {
		this.discountableamt = discountableamt;
	}

	public String getIsCredit() {
		return isCredit;
	}

	public void setIsCredit(String isCredit) {
		this.isCredit = isCredit;
	}

	public String getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}

	public String getTimeExpire() {
		return timeExpire;
	}

	public void setTimeExpire(String timeExpire) {
		this.timeExpire = timeExpire;
	}

	public String getCustomerIp() {
		return customerIp;
	}

	public void setCustomerIp(String customerIp) {
		this.customerIp = customerIp;
	}

	public String getProductInfoDetail() {
		return productInfoDetail;
	}

	public void setProductInfoDetail(String productInfoDetail) {
		this.productInfoDetail = productInfoDetail;
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

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getTermId() {
		return termId;
	}

	public void setTermId(String termId) {
		this.termId = termId;
	}

	public String getTimeoutExpress() {
		return timeoutExpress;
	}

	public void setTimeoutExpress(String timeoutExpress) {
		this.timeoutExpress = timeoutExpress;
	}
	public String getAlipayStoreId() {
		return alipayStoreId;
	}

	public void setAlipayStoreId(String alipayStoreId) {
		this.alipayStoreId = alipayStoreId;
	}
	
	@SuppressWarnings("unchecked")
	protected List<Map>getmerListMap(InputPaygateData data){
		List<Map> payeeAcctList = new ArrayList<Map>();
		Map combMap = new HashMap();
		combMap.put(Dict.SUB_MERCHANT_ID, data.getSubMerchantId());
		combMap.put(Dict.MER_NAME,data.getMerName());
		combMap.put(Dict.THIRD_MER_NBR, data.getThirdMerNbr());
		payeeAcctList.add(combMap);
		return payeeAcctList;
	}
}
