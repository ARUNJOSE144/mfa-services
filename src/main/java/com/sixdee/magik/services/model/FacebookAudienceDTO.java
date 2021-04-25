package com.sixdee.magik.services.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class FacebookAudienceDTO {

	private GeoLocationsModelDTO geo_locations;

	private int age_min;
	private int age_max;
	private List<IdNameDTO> interests;
	private List<IdNameDTO> industries;
	private List<Integer> genders;
	private List<String> user_os;
	private List<String> publisher_platforms;
	
	public GeoLocationsModelDTO getGeo_locations() {
		return geo_locations;
	}

	public void setGeo_locations(GeoLocationsModelDTO geo_locations) {
		this.geo_locations = geo_locations;
	}

	public int getAge_min() {
		return age_min;
	}

	public void setAge_min(int age_min) {
		this.age_min = age_min;
	}

	public int getAge_max() {
		return age_max;
	}

	public void setAge_max(int age_max) {
		this.age_max = age_max;
	}

	public List<IdNameDTO> getInterests() {
		return interests;
	}

	public void setInterests(List<IdNameDTO> interests) {
		this.interests = interests;
	}

	public List<Integer> getGenders() {
		return genders;
	}

	public void setGenders(List<Integer> genders) {
		this.genders = genders;
	}

	public List<IdNameDTO> getIndustries() {
		return industries;
	}

	public void setIndustries(List<IdNameDTO> industries) {
		this.industries = industries;
	}

	public List<String> getUser_os() {
		return user_os;
	}

	public void setUser_os(List<String> user_os) {
		this.user_os = user_os;
	}

	public List<String> getPublisher_platforms() {
		return publisher_platforms;
	}

	public void setPublisher_platforms(List<String> publisher_platforms) {
		this.publisher_platforms = publisher_platforms;
	}

	@Override
	public String toString() {
		return "FacebookAudienceDTO [geo_locations=" + geo_locations + ", age_min=" + age_min + ", age_max=" + age_max
				+ ", interests=" + interests + ", industries=" + industries + ", genders=" + genders + ", user_os="
				+ user_os + ", publisher_platforms=" + publisher_platforms + "]";
	}

	
	

}
