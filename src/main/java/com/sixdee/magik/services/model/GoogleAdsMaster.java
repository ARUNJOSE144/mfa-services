package com.sixdee.magik.services.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "GOOGLE_ADS_MASTER")
@JsonInclude(Include.NON_NULL)
public class GoogleAdsMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "GoogleAdsMaster")
	@TableGenerator(name = "GoogleAdsMaster")
	@Column(name = "ID")
	private int id;

	@Column(name = "AD_NAME")
	private String adName;

	@Column(name = "AD_HEADLINE_1")
	private String headLine_1;

	@Column(name = "AD_HEADLINE_2")
	private String headLine_2;

	@Column(name = "AD_HEADLINE_3")
	private String headLine_3;

	@Column(name = "AD_DESCRIPTION_1")
	private String description_1;

	@Column(name = "AD_DESCRIPTION_2")
	private String description_2;

	@Column(name = "AD_UPLOAD_TYPE")
	private String uploadType;

	@Column(name = "AD_UPLOAD_FILE_NAME")
	private String uploadFileName;

	@Column(name = "AD_URL")
	private String url;

	@Column(name = "AD_BID_AMOUNT")
	private String bidAmount;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "googleMaster")
	private List<GoogleAdsKeywordMapping> mappingDetails;

	@Transient
	private String statusCode;

	@Transient
	private String statusDescription;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdName() {
		return adName;
	}

	public void setAdName(String adName) {
		this.adName = adName;
	}

	public String getHeadLine_1() {
		return headLine_1;
	}

	public void setHeadLine_1(String headLine_1) {
		this.headLine_1 = headLine_1;
	}

	public String getHeadLine_2() {
		return headLine_2;
	}

	public void setHeadLine_2(String headLine_2) {
		this.headLine_2 = headLine_2;
	}

	public String getHeadLine_3() {
		return headLine_3;
	}

	public void setHeadLine_3(String headLine_3) {
		this.headLine_3 = headLine_3;
	}

	public String getDescription_1() {
		return description_1;
	}

	public void setDescription_1(String description_1) {
		this.description_1 = description_1;
	}

	public String getDescription_2() {
		return description_2;
	}

	public void setDescription_2(String description_2) {
		this.description_2 = description_2;
	}

	public String getUploadType() {
		return uploadType;
	}

	public void setUploadType(String uploadType) {
		this.uploadType = uploadType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<GoogleAdsKeywordMapping> getMappingDetails() {
		return mappingDetails;
	}

	public void setMappingDetails(List<GoogleAdsKeywordMapping> mappingDetails) {
		this.mappingDetails = mappingDetails;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusDescription() {
		return statusDescription;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

	public String getBidAmount() {
		return bidAmount;
	}

	public void setBidAmount(String bidAmount) {
		this.bidAmount = bidAmount;
	}

	@Override
	public String toString() {
		return "GoogleAdsMaster [id=" + id + ", adName=" + adName + ", headLine_1=" + headLine_1 + ", headLine_2="
				+ headLine_2 + ", headLine_3=" + headLine_3 + ", description_1=" + description_1 + ", description_2="
				+ description_2 + ", uploadType=" + uploadType + ", uploadFileName=" + uploadFileName + ", url=" + url
				+ ", bidAmount=" + bidAmount + ", mappingDetails=" + mappingDetails + ", statusCode=" + statusCode
				+ ", statusDescription=" + statusDescription + "]";
	}

}
