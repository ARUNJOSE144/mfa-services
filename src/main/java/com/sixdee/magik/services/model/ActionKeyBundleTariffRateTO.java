package com.sixdee.magik.services.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Nakhil Kurian
 * @category January 2021
 * 
 */

@Entity
@Table(name = "AK_BUNDLE_TARIFF_RATE")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ActionKeyBundleTariffRateTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ActionKeyBundleTariffRateTO")
	@TableGenerator(name = "ActionKeyBundleTariffRateTO", allocationSize = 1)
	@Column(name = "ID")
	private String id;
	@Column(name = "PRODUCT_ID")
	private String productId;

	@Column(name = "PRODUCT_NAME")
	private String productName;

	@Column(name = "PRICE")
	private double price;

	@Column(name = "CAMPAIGN_TYPE")
	private String campaignType;

	@Column(name = "BUNDLE_TYPE")
	private String bundleType;

	@Column(name = "BUNDLE_VOLUME_IN_MB")
	private String bundleVolumeMB;

	@Column(name = "CAMPAIGN_TYPE_ID")
	private String campaignTypeID;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCampaignType() {
		return campaignType;
	}

	public void setCampaignType(String campaignType) {
		this.campaignType = campaignType;
	}

	public String getBundleType() {
		return bundleType;
	}

	public void setBundleType(String bundleType) {
		this.bundleType = bundleType;
	}

	public String getBundleVolumeMB() {
		return bundleVolumeMB;
	}

	public void setBundleVolumeMB(String bundleVolumeMB) {
		this.bundleVolumeMB = bundleVolumeMB;
	}

	public String getCampaignTypeID() {
		return campaignTypeID;
	}

	public void setCampaignTypeID(String campaignTypeID) {
		this.campaignTypeID = campaignTypeID;
	}

	@Override
	public String toString() {
		return "BundleTariffRateTO [id=" + id + ", productId=" + productId + ", productName=" + productName + ", price="
				+ price + ", campaignType=" + campaignType + ", bundleType=" + bundleType + ", bundleVolumeMB="
				+ bundleVolumeMB + ", campaignTypeID=" + campaignTypeID + "]";
	}

}
