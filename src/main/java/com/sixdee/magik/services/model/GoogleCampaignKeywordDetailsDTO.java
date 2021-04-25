package com.sixdee.magik.services.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "GOOGLE_CAMPAIGN_KEYWORD_DETAILS")
@JsonInclude(Include.NON_NULL)
public class GoogleCampaignKeywordDetailsDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "GoogleCampaignKeywordDetailsDTO")
	@TableGenerator(name = "GoogleCampaignKeywordDetailsDTO")
	@Column(name = "ID")
	private int id;

	@Column(name = "KEYWORD_ID")
	private int keywordId;

	@Column(name = "AD_GROUP_ID")
	private String adGroupId;

	@Column(name = "AD_KEYWORD_ID")
	private String adKeywordId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getKeywordId() {
		return keywordId;
	}

	public void setKeywordId(int keywordId) {
		this.keywordId = keywordId;
	}

	public String getAdGroupId() {
		return adGroupId;
	}

	public void setAdGroupId(String adGroupId) {
		this.adGroupId = adGroupId;
	}

	public String getAdKeywordId() {
		return adKeywordId;
	}

	public void setAdKeywordId(String adKeywordId) {
		this.adKeywordId = adKeywordId;
	}

	@Override
	public String toString() {
		return "GoogleCampaignKeywordDetailsDTO [id=" + id + ", keywordId=" + keywordId + ", adGroupId=" + adGroupId
				+ ", adKeywordId=" + adKeywordId + "]";
	}

}
