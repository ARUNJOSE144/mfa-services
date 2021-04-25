package com.sixdee.magik.services.adaptor;

import java.util.List;

public class Data {

	private DetailResp detailResp;
	private Detail detailReq;
	public List<Detail> detail;

	public List<Detail> getLists() {
		return detail;
	}

	public void setLists(List<Detail> detail) {
		this.detail = detail;
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
		return "Data [detailResp=" + detailResp + ", detailReq=" + detailReq + ", detail=" + detail + "]";
	}

}
