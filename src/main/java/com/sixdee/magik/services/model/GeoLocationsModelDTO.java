package com.sixdee.magik.services.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class GeoLocationsModelDTO {

	private List<String> countries;
	
	private List<KeyValuePairDTO> regions;

	public List<String> getCountries() {
		return countries;
	}

	public void setCountries(List<String> countries) {
		this.countries = countries;
	}

	public List<KeyValuePairDTO> getRegions() {
		return regions;
	}

	public void setRegions(List<KeyValuePairDTO> regions) {
		this.regions = regions;
	}

	@Override
	public String toString() {
		return "GeoLocationsModelDTO [countries=" + countries + ", regions=" + regions + "]";
	}

	
}
