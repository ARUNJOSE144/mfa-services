package com.sixdee.magik.services.util;

public class Request
{
	public String featureId;
	public String app_name;
	public String username;
	public String password;
	public String req_tran_id;
	public String msg_origin;
	public String msg_dest;
	public String timestamp;
	public Data data;
	public String respCode;
	public String resp_tran_id;
	public String resp_desc;
	
	
	public String getFeatureId() {
		return featureId;
	}
	public void setFeatureId(String featureId) {
		this.featureId = featureId;
	}
	public String getApp_name() {
		return app_name;
	}
	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getReq_tran_id() {
		return req_tran_id;
	}
	public void setReq_tran_id(String req_tran_id) {
		this.req_tran_id = req_tran_id;
	}
	public String getMsg_origin() {
		return msg_origin;
	}
	public void setMsg_origin(String msg_origin) {
		this.msg_origin = msg_origin;
	}
	public String getMsg_dest() {
		return msg_dest;
	}
	public void setMsg_dest(String msg_dest) {
		this.msg_dest = msg_dest;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public Data getData() {
		return data;
	}
	public void setData(Data data) {
		this.data = data;
	}
	public String getRespCode() {
		return respCode;
	}
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
	public String getResp_tran_id() {
		return resp_tran_id;
	}
	public void setResp_tran_id(String resp_tran_id) {
		this.resp_tran_id = resp_tran_id;
	}
	public String getResp_desc() {
		return resp_desc;
	}
	public void setResp_desc(String resp_desc) {
		this.resp_desc = resp_desc;
	}
	
	
	@Override
	public String toString() {
		return "Response [featureId=" + featureId + ", app_name=" + app_name
				+ ", username=" + username + ", password=" + password
				+ ", req_tran_id=" + req_tran_id + ", msg_origin=" + msg_origin
				+ ", msg_dest=" + msg_dest + ", timestamp=" + timestamp
				+ ", data=" + data + ", respCode=" + respCode
				+ ", resp_tran_id=" + resp_tran_id + ", resp_desc=" + resp_desc
				+ ", getFeatureId()=" + getFeatureId() + ", getApp_name()="
				+ getApp_name() + ", getUsername()=" + getUsername()
				+ ", getPassword()=" + getPassword() + ", getReq_tran_id()="
				+ getReq_tran_id() + ", getMsg_origin()=" + getMsg_origin()
				+ ", getMsg_dest()=" + getMsg_dest() + ", getTimestamp()="
				+ getTimestamp() + ", getData()=" + getData()
				+ ", getRespCode()=" + getRespCode() + ", getResp_tran_id()="
				+ getResp_tran_id() + ", getResp_desc()=" + getResp_desc()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
	
}
