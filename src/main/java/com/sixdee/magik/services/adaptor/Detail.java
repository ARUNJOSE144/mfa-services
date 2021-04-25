package com.sixdee.magik.services.adaptor;

import java.util.List;

public class Detail {

	public List<Param> param;

	public List<Param> getParam() {
		return param;
	}

	public void setParam(List<Param> param) {
		this.param = param;
	}

	@Override
	public String toString() {
		return "Detail [param=" + param + "]";
	}

	

}
