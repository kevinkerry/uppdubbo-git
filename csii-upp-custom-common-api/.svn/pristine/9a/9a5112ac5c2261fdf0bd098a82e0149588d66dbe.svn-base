package com.csii.upp.custom.common.api.data.fundprocess;

import java.io.Serializable;
import java.util.List;

import com.csii.upp.custom.common.api.data.base.FundProcessRespHead;

public class QUINResp extends FundProcessRespHead {
	private static final long serialVersionUID = -7266400019434303057L;
	private String cmdId;
	private String occuDate;
	private String protocolVersion;
	private String requestID;
	private List<PointRecord> pointRecords;

	public static class PointRecord implements Serializable {
		private static final long serialVersionUID = -7280197971137682981L;
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

	public void setPointRecords(List<PointRecord> pointRecords) {
		this.pointRecords = pointRecords;
	}

	public List<PointRecord> getPointRecords() {
		return pointRecords;
	}

}
