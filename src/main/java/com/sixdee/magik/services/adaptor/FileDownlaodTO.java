package com.sixdee.magik.services.adaptor;

/**
 * @author Nakhil Kurian
 * @Date : January, 2021
 *
 */
public class FileDownlaodTO {

	public String featureId;
	public String reqTxnId;
	public String respTxnId;
	public String msgOrigin;
	public String msgDest;
	public String timestamp;
	public String respCode;
	public String respDesc;
	public Data data;
	public Param param;

	public String getFeatureId() {
		return featureId;
	}

	public void setFeatureId(String featureId) {
		this.featureId = featureId;
	}

	public String getReqTxnId() {
		return reqTxnId;
	}

	public void setReqTxnId(String reqTxnId) {
		this.reqTxnId = reqTxnId;
	}

	public String getRespTxnId() {
		return respTxnId;
	}

	public void setRespTxnId(String respTxnId) {
		this.respTxnId = respTxnId;
	}

	public String getMsgOrigin() {
		return msgOrigin;
	}

	public void setMsgOrigin(String msgOrigin) {
		this.msgOrigin = msgOrigin;
	}

	public String getMsgDest() {
		return msgDest;
	}

	public void setMsgDest(String msgDest) {
		this.msgDest = msgDest;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getRespDesc() {
		return respDesc;
	}

	public void setRespDesc(String respDesc) {
		this.respDesc = respDesc;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public Param getParam() {
		return param;
	}

	public void setParam(Param param) {
		this.param = param;
	}

	@Override
	public String toString() {
		return "FileDownlaodTO [featureId=" + featureId + ", reqTxnId=" + reqTxnId + ", respTxnId=" + respTxnId
				+ ", msgOrigin=" + msgOrigin + ", msgDest=" + msgDest + ", timestamp=" + timestamp + ", respCode="
				+ respCode + ", respDesc=" + respDesc + ", data=" + data + ", param=" + param + "]";
	}

}
