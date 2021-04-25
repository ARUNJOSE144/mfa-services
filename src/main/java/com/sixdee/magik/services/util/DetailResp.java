package com.sixdee.magik.services.util;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class DetailResp {
	@XStreamImplicit
	public List<Param> lists;

    public Param param;
	
	public Param getParam() {
		return param;
	}

	public void setParam(Param param) {
		this.param = param;
	}
	
	public List<Param> getList() {
		return lists;
	}

	public void setList(List<Param> list) {
		this.lists = list;
	}


}
