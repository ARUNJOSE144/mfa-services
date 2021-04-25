package com.sixdee.magik.services.model;

import java.util.List;

public class TargetingCountriesResponseDTO {

	private List<TargetingCountries> data;

	private FacebookPaginationDTO paging;

	public List<TargetingCountries> getData() {
		return data;
	}

	public void setData(List<TargetingCountries> data) {
		this.data = data;
	}

	public FacebookPaginationDTO getPaging() {
		return paging;
	}

	public void setPaging(FacebookPaginationDTO paging) {
		this.paging = paging;
	}

	@Override
	public String toString() {
		return "TargetingCountriesResponseDTO [data=" + data + ", paging=" + paging + "]";
	}
	
	

}
