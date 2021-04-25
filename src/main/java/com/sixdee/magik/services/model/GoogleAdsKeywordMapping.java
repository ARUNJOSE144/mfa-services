package com.sixdee.magik.services.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "GOOGLE_ADS_KEYWORD_MAPPING")
@JsonInclude(Include.NON_NULL)
public class GoogleAdsKeywordMapping {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "GoogleAdsKeywordMapping")
	@TableGenerator(name = "GoogleAdsKeywordMapping")
	@Column(name = "ID")
	private int id;

	@Column(name = "KEYWORD")
	private String keyword;

	@Column(name = "KEYWORD_TYPE")
	private String keywordType;

	@ManyToOne
	@JoinColumn(name = "AD_ID")
	private GoogleAdsMaster googleMaster;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getKeywordType() {
		return keywordType;
	}

	public void setKeywordType(String keywordType) {
		this.keywordType = keywordType;
	}

	public GoogleAdsMaster getGoogleMaster() {
		return googleMaster;
	}

	public void setGoogleMaster(GoogleAdsMaster googleMaster) {
		this.googleMaster = googleMaster;
	}

	@Override
	public String toString() {
		return "GoogleAdsKeywordMapping [id=" + id + ", keyword=" + keyword + ", keywordType=" + keywordType
				+ ", googleMaster=" + googleMaster + "]";
	}

}
