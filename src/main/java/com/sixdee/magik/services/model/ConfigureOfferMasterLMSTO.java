package com.sixdee.magik.services.model;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : March, 2021
 */
 

@SuppressWarnings("serial")
@Entity
@Table(name = "LMS_CNFG_OFFER_MASTER")
public class ConfigureOfferMasterLMSTO {
	
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ConfigureOfferMasterLMSTO")
	@TableGenerator(name = "ConfigureOfferMasterLMSTO", allocationSize = 1)
	@Column(name = "OFFERID")
	private int offerId;
	
	@OneToOne
	@JoinColumn(name="OFFERTYPEID")
	private ConfigureOfferTypeLMSTO  configureOfferTypeLMSTO; 
	
	@OneToOne
	@JoinColumn(name="INTERFACEID")
	private ConfigureInterfaceMasterLMSTO  configureInterfaceMasterLMSTO; 
	
	@OneToOne
	@JoinColumn(name="MERCHANTID")
	private ConfigureOfferMerchantTypeTO  configureOfferMerchantTypeTO; 
	
	
	@Column(name = "PRIORITY")
	private Double priority;
	
	@Column(name = "TOPOFFER")
	private Double topoffer;
	
	@Column(name = "STATUS")
	private Double status;
	
	@Column(name = "POINTS")
	private Double points;
	
	@Column(name = "SHORTCODE")
	private String shortcode;
	
	@DateTimeFormat(pattern="dd-MM-yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name="STARTDATE")
	private Date startDate;
	
	@DateTimeFormat(pattern="dd-MM-yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name="ENDDATE")
	private Date endDate;
	
	@Column(name = "EXPIRYDAYS")
	private Double expiryDay;
	
	@Column(name = "EXPIRYMINUTE")
	private Double expiryMinutes;
	
	@Column(name = "EXPIRYHOUR")
	private Double expiryHour;
	
	@Column(name = "COST")
	private Double cost;
	
	@Column(name = "PROVIDER_NAME")
	private String providerName;
	
	
	@Column(name = "FETCH_TYPE")
	private String fetch_type;
	
	
	@Column(name = "IS_PHYSICAL_GIFT")
	private BigInteger is_physical_gift;

	public String getFetch_type() {
		return fetch_type;
	}


	public void setFetch_type(String fetch_type) {
		this.fetch_type = fetch_type;
	}


	public ConfigureOfferMerchantTypeTO getConfigureOfferMerchantTypeTO() {
		return configureOfferMerchantTypeTO;
	}


	public void setConfigureOfferMerchantTypeTO(ConfigureOfferMerchantTypeTO configureOfferMerchantTypeTO) {
		this.configureOfferMerchantTypeTO = configureOfferMerchantTypeTO;
	}


	public int getOfferId() {
		return offerId;
	}


	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}


	public ConfigureOfferTypeLMSTO getConfigureOfferTypeLMSTO() {
		return configureOfferTypeLMSTO;
	}


	public void setConfigureOfferTypeLMSTO(ConfigureOfferTypeLMSTO configureOfferTypeLMSTO) {
		this.configureOfferTypeLMSTO = configureOfferTypeLMSTO;
	}


	public ConfigureInterfaceMasterLMSTO getConfigureInterfaceMasterLMSTO() {
		return configureInterfaceMasterLMSTO;
	}


	public void setConfigureInterfaceMasterLMSTO(ConfigureInterfaceMasterLMSTO configureInterfaceMasterLMSTO) {
		this.configureInterfaceMasterLMSTO = configureInterfaceMasterLMSTO;
	}


	public Double getPriority() {
		return priority;
	}


	public void setPriority(Double priority) {
		this.priority = priority;
	}


	public Double getTopoffer() {
		return topoffer;
	}


	public void setTopoffer(Double topoffer) {
		this.topoffer = topoffer;
	}


	public Double getStatus() {
		return status;
	}


	public void setStatus(Double status) {
		this.status = status;
	}


	public Double getPoints() {
		return points;
	}


	public void setPoints(Double points) {
		this.points = points;
	}


	public String getShortcode() {
		return shortcode;
	}


	public void setShortcode(String shortcode) {
		this.shortcode = shortcode;
	}


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	public Double getExpiryDay() {
		return expiryDay;
	}


	public void setExpiryDay(Double expiryDay) {
		this.expiryDay = expiryDay;
	}


	public Double getExpiryMinutes() {
		return expiryMinutes;
	}


	public void setExpiryMinutes(Double expiryMinutes) {
		this.expiryMinutes = expiryMinutes;
	}


	public Double getExpiryHour() {
		return expiryHour;
	}


	public void setExpiryHour(Double expiryHour) {
		this.expiryHour = expiryHour;
	}


	public Double getCost() {
		return cost;
	}


	public void setCost(Double cost) {
		this.cost = cost;
	}


	public String getProviderName() {
		return providerName;
	}


	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}


	public BigInteger getIs_physical_gift() {
		return is_physical_gift;
	}


	public void setIs_physical_gift(BigInteger is_physical_gift) {
		this.is_physical_gift = is_physical_gift;
	}
	

}
