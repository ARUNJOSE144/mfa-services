package com.sixdee.magik.services.util;

import java.util.List;

public class Data {

	private DetailResp detailResp;
	private Detail detailReq;
	public List<Detail> lists;

	public List<Detail> getLists() {
		return lists;
	}

	public void setLists(List<Detail> lists) {
		this.lists = lists;
	}

	public Detail getDetailReq() {
		return detailReq;
	}

	public void setDetailReq(Detail detailReq) {
		this.detailReq = detailReq;
	}

	public DetailResp getDetailResp() {
		return detailResp;
	}

	public void setDetailResp(DetailResp detailResp) {
		this.detailResp = detailResp;
	}

	@Override
	public String toString() {
		return "Data [detailResp=" + detailResp + ", detailReq=" + detailReq + ", lists=" + lists + ", getLists()="
				+ getLists() + ", getDetailReq()=" + getDetailReq() + ", getDetailResp()=" + getDetailResp()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

}
