package com.sixdee.magik.services.model;

import java.util.List;

public class TargetingRegionResponseDTO {
	
	private List<TargetingRegionsDTO> data;
	
	private FacebookPaginationDTO paging;

	public List<TargetingRegionsDTO> getData() {
		return data;
	}

	public void setData(List<TargetingRegionsDTO> data) {
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
		return "TargetingRegionResponseDTO [data=" + data + ", paging=" + paging + "]";
	}
	
	

}
