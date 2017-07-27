package com.csii.upp.custom.common.api.data.payment;

import java.io.Serializable;
import java.util.List;


import com.csii.upp.custom.common.api.data.base.PaymentRespHead;

public class QueryIntegralResp extends PaymentRespHead {
	private static final long serialVersionUID = -5609377607351873761L;
    private String cmdId; 
    private String occuDate; 
    private String protocolVersion; 
    private String requestID; 
    private List<PointRecordMap> pointRecords; 
    
    public static class PointRecordMap implements Serializable{

		
		private static final long serialVersionUID = -4212854695763184443L;
		private String branchNo;
		private String branchName;
		private String integralTotal;
		private String clientNo;
		public String getBranchNo() {
			return branchNo;
		}
		public void setBranchNo(String branchNo) {
			this.branchNo = branchNo;
		}
		public String getBranchName() {
			return branchName;
		}
		public void setBranchName(String branchName) {
			this.branchName = branchName;
		}
		public String getIntegralTotal() {
			return integralTotal;
		}
		public void setIntegralTotal(String integralTotal) {
			this.integralTotal = integralTotal;
		}
		public String getClientNo() {
			return clientNo;
		}
		public void setClientNo(String clientNo) {
			this.clientNo = clientNo;
		}
		
    	
    }
    public void setCmdId(String cmdId) {
        this.cmdId = cmdId;
    }
    public String getCmdId() {
        return cmdId;
    }

    public void setOccuDate(String occuDate) {
        this.occuDate = occuDate;
    }
    public String getOccuDate() {
        return occuDate;
    }

    public void setProtocolVersion(String protocolVersion) {
        this.protocolVersion = protocolVersion;
    }
    public String getProtocolVersion() {
        return protocolVersion;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }
    public String getRequestID() {
        return requestID;
    }

    public void setPointRecords(List<PointRecordMap> pointRecords) {
        this.pointRecords = pointRecords;
    }
    public List<PointRecordMap> getPointRecords() {
        return pointRecords;
    }


}
