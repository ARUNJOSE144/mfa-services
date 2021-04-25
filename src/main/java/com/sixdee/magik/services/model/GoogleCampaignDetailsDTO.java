package com.sixdee.magik.services.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "GOOGLE_CAMPAIGN_DETAILS")
@JsonInclude(Include.NON_NULL)
public class GoogleCampaignDetailsDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "GoogleCampaignDetailsDTO")
	@TableGenerator(name = "GoogleCampaignDetailsDTO")
	@Column(name = "ID")
	private int id;

	@Column(name = "MAGIK_CAMPAIGN_ID")
	private int magikCampaignId;

	@Column(name = "AD_CAMPAIGN_ID")
	private String adCampaignId;

	@Column(name = "AD_GROUP_ID")
	private String adGroupId;

	@Column(name = "AD_AD_ID")
	private String adId;

	@Transient
	List<GoogleCampaignKeywordDetailsDTO> keywordDetails;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMagikCampaignId() {
		return magikCampaignId;
	}

	public void setMagikCampaignId(int magikCampaignId) {
		this.magikCampaignId = magikCampaignId;
	}

	public String getAdCampaignId() {
		return adCampaignId;
	}

	public void setAdCampaignId(String adCampaignId) {
		this.adCampaignId = adCampaignId;
	}

	public String getAdGroupId() {
		return adGroupId;
	}

	public void setAdGroupId(String adGroupId) {
		this.adGroupId = adGroupId;
	}

	public String getAdId() {
		return adId;
	}

	public void setAdId(String adId) {
		this.adId = adId;
	}

	public List<GoogleCampaignKeywordDetailsDTO> getKeywordDetails() {
		return keywordDetails;
	}

	public void setKeywordDetails(List<GoogleCampaignKeywordDetailsDTO> keywordDetails) {
		this.keywordDetails = keywordDetails;
	}

	@Override
	public String toString() {
		return "GoogleCampaignDetailsDTO [id=" + id + ", magikCampaignId=" + magikCampaignId + ", adCampaignId="
				+ adCampaignId + ", adGroupId=" + adGroupId + ", adId=" + adId + ", keywordDetails=" + keywordDetails
				+ "]";
	}

}
