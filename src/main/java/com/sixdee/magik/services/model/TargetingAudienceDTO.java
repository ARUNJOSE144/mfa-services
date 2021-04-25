package com.sixdee.magik.services.model;
/**
 * @author Amal A S
 * @category Target Audience for campaigns
 * @date 03/06/2020
 * 
 */

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class TargetingAudienceDTO {

	private String mediaType;
	private String country;
	private String minAge;
	private String maxAge;
	private String estimatedSize;
	private String statusCode;
	private String statusDesc;
	private String configName;
	private String configDesc;
	private String customFileName;

	private int configId;

	private FacebookDataDTO data;

	private List<TargetingAudienceDTO> listData;

	private List<Integer> genders;

	private List<IdNameDTO> interests;

	private List<TargetingCountries> countries;

	private List<IdNameDTO> industries;

	private List<TargetingWorkPostionsDTO> dataJobList;

	private List<TargetingInterestsResponseDTO> dataInterestList;

	private List<TargetingRegionsDTO> dataRegionList;

	private List<TargetingCountries> dataCountriesList;

	private List<KeyValuePairDTO> regions;
	
	private List<TargetingLanguageMaster> languages;
	
	private List<String> user_os;

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getMinAge() {
		return minAge;
	}

	public void setMinAge(String minAge) {
		this.minAge = minAge;
	}

	public String getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(String maxAge) {
		this.maxAge = maxAge;
	}

	public String getEstimatedSize() {
		return estimatedSize;
	}

	public void setEstimatedSize(String estimatedSize) {
		this.estimatedSize = estimatedSize;
	}

	public FacebookDataDTO getData() {
		return data;
	}

	public void setData(FacebookDataDTO data) {
		this.data = data;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getConfigName() {
		return configName;
	}

	public void setConfigName(String configName) {
		this.configName = configName;
	}

	public String getConfigDesc() {
		return configDesc;
	}

	public void setConfigDesc(String configDesc) {
		this.configDesc = configDesc;
	}

	public int getConfigId() {
		return configId;
	}

	public void setConfigId(int configId) {
		this.configId = configId;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public List<TargetingAudienceDTO> getListData() {
		return listData;
	}

	public void setListData(List<TargetingAudienceDTO> listData) {
		this.listData = listData;
	}

	public List<Integer> getGenders() {
		return genders;
	}

	public void setGenders(List<Integer> genders) {
		this.genders = genders;
	}

	public List<IdNameDTO> getInterests() {
		return interests;
	}

	public void setInterests(List<IdNameDTO> interests) {
		this.interests = interests;
	}

	public List<TargetingCountries> getCountries() {
		return countries;
	}

	public void setCountries(List<TargetingCountries> countries) {
		this.countries = countries;
	}

	public List<IdNameDTO> getIndustries() {
		return industries;
	}

	public void setIndustries(List<IdNameDTO> industries) {
		this.industries = industries;
	}

	public List<TargetingWorkPostionsDTO> getDataJobList() {
		return dataJobList;
	}

	public void setDataJobList(List<TargetingWorkPostionsDTO> dataJobList) {
		this.dataJobList = dataJobList;
	}

	public List<TargetingInterestsResponseDTO> getDataInterestList() {
		return dataInterestList;
	}

	public void setDataInterestList(List<TargetingInterestsResponseDTO> dataInterestList) {
		this.dataInterestList = dataInterestList;
	}

	public List<TargetingRegionsDTO> getDataRegionList() {
		return dataRegionList;
	}

	public void setDataRegionList(List<TargetingRegionsDTO> dataRegionList) {
		this.dataRegionList = dataRegionList;
	}

	public List<TargetingCountries> getDataCountriesList() {
		return dataCountriesList;
	}

	public void setDataCountriesList(List<TargetingCountries> dataCountriesList) {
		this.dataCountriesList = dataCountriesList;
	}

	public List<KeyValuePairDTO> getRegions() {
		return regions;
	}

	public void setRegions(List<KeyValuePairDTO> regions) {
		this.regions = regions;
	}

	public String getCustomFileName() {
		return customFileName;
	}

	public void setCustomFileName(String customFileName) {
		this.customFileName = customFileName;
	}

	public List<TargetingLanguageMaster> getLanguages() {
		return languages;
	}

	public void setLanguages(List<TargetingLanguageMaster> languages) {
		this.languages = languages;
	}

	public List<String> getUser_os() {
		return user_os;
	}

	public void setUser_os(List<String> user_os) {
		this.user_os = user_os;
	}

	@Override
	public String toString() {
		return "TargetingAudienceDTO [mediaType=" + mediaType + ", country=" + country + ", minAge=" + minAge
				+ ", maxAge=" + maxAge + ", estimatedSize=" + estimatedSize + ", statusCode=" + statusCode
				+ ", statusDesc=" + statusDesc + ", configName=" + configName + ", configDesc=" + configDesc
				+ ", customFileName=" + customFileName + ", configId=" + configId + ", data=" + data + ", listData="
				+ listData + ", genders=" + genders + ", interests=" + interests + ", countries=" + countries
				+ ", industries=" + industries + ", dataJobList=" + dataJobList + ", dataInterestList="
				+ dataInterestList + ", dataRegionList=" + dataRegionList + ", dataCountriesList=" + dataCountriesList
				+ ", regions=" + regions + ", languages=" + languages + ", user_os=" + user_os + "]";
	}



}
