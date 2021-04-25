package com.sixdee.magik.services.model;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.CreationTimestamp;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : March, 2021
 */
 

@SuppressWarnings("serial")
@Entity
@Table(name = "LMS_CNFG_TIER_INFO")
public class TierConfigurationLMSTO {
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TierConfigurationLMSTO")
	@TableGenerator(name = "TierConfigurationLMSTO", allocationSize = 1)
	@Column(name = "ID") 
	private Long autoId; //BigInt
	
	@Column(name = "TIER_NAME")
	private String tierName;

	@Column(name = "TIER_DESC")
	private String tierDesc;

    @Column(name = "MIN_VALUE")
	private Long minValue; //BigInt

    @Column(name = "MAX_VALUE")
	private Long maxValue; //BigInt
  
    @Column(name = "WELCOME_REWARD_POINTS")
	private Integer welcomeRewardPoints; 
 
    @Column(name = "WELCOME_STATUS_POINTS")
	private Integer welcomeStatusPoints; 
	
	@CreationTimestamp
	@Column(name="CREATE_DATE",nullable = false, updatable = false)
	private Date createdDate;

	public Long getAutoId() {
		return autoId;
	}

	public void setAutoId(Long autoId) {
		this.autoId = autoId;
	}

	public String getTierName() {
		return tierName;
	}

	public void setTierName(String tierName) {
		this.tierName = tierName;
	}

	public String getTierDesc() {
		return tierDesc;
	}

	public void setTierDesc(String tierDesc) {
		this.tierDesc = tierDesc;
	}

	public Long getMinValue() {
		return minValue;
	}

	public void setMinValue(Long minValue) {
		this.minValue = minValue;
	}

	public Long getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(Long maxValue) {
		this.maxValue = maxValue;
	}

	public Integer getWelcomeRewardPoints() {
		return welcomeRewardPoints;
	}

	public void setWelcomeRewardPoints(Integer welcomeRewardPoints) {
		this.welcomeRewardPoints = welcomeRewardPoints;
	}

	public Integer getWelcomeStatusPoints() {
		return welcomeStatusPoints;
	}

	public void setWelcomeStatusPoints(Integer welcomeStatusPoints) {
		this.welcomeStatusPoints = welcomeStatusPoints;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	

	
	
	

}
