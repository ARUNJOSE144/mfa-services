package com.inno.mfa.services.model;

import java.util.List;

import com.inno.mfa.services.model.trade.TradeMasterTo;

import lombok.Data;

/**
 * @author Arun Jose
 * @Date : March, 2021
 */

public class CommonRespTo<T> {

	private int resultCode;
	private String responseMsg;
	private T data;
	private List<T> list;
	
	public int getResultCode() {
		return resultCode;
	}
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	public String getResponseMsg() {
		return responseMsg;
	}
	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}

}