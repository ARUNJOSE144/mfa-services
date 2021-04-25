package com.sixdee.magik.services.util;

import java.util.ArrayList;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class MultiParam {
	public Param param1;
	@XStreamImplicit(itemFieldName="PARAM")
	public ArrayList<Param> params;

	public ArrayList<Param> getParams() {
		return params;
	}

	public void setParams(ArrayList<Param> params) {
		this.params = params;
	}

	public Param getParam() {
		return param1;
	}

	public void setParam(Param param) {
		this.param1 = param;
	}

	public Param getParam1() {
		return param1;
	}

	public void setParam1(Param param1) {
		this.param1 = param1;
	}
	
	
	
	
}
