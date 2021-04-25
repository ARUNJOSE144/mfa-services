package com.sixdee.magik.services.model;

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

import org.hibernate.annotations.CreationTimestamp;


/**
 * @author ABHIRAM MACHIRAJU
 * @Date : March, 2021
 */
 

@SuppressWarnings("serial")
@Entity
@Table(name = "TIER_REWARD_POINTS_DETAILS")
public class TierDetailsLMSTO {
	
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TierDetailsLMSTO")
	@TableGenerator(name = "TierDetailsLMSTO", allocationSize = 1)
	@Column(name = "ID")
	private int autoId;
	
	@OneToOne
	@JoinColumn(name="TIER_ID")
	private TierConfigurationLMSTO  tierConfigurationTO; 
	
	@OneToOne
	@JoinColumn(name="CATEGORY_ID")
	private CategoryLoyaltyLMSTO  categoryTO; 
	
	@Column(name = "MIN_VALUE")
	private Double minValue;
	
	@Column(name = "MAX_VALUE")
	private Double maxValue;
	
	@Column(name = "REWARD_POINTS")
	private Double rewardPoints;
	
	@Column(name = "STATUS_POINTS")
	private Double statusPoints;
	
	@CreationTimestamp
	@Column(name="CREATE_TIME",nullable = false, updatable = false)
	private Date createdDate;

	public int getAutoId() {
		return autoId;
	}

	public void setAutoId(int autoId) {
		this.autoId = autoId;
	}

	public TierConfigurationLMSTO getTierConfigurationTO() {
		return tierConfigurationTO;
	}

	public void setTierConfigurationTO(TierConfigurationLMSTO tierConfigurationTO) {
		this.tierConfigurationTO = tierConfigurationTO;
	}

	public CategoryLoyaltyLMSTO getCategoryTO() {
		return categoryTO;
	}

	public void setCategoryTO(CategoryLoyaltyLMSTO categoryTO) {
		this.categoryTO = categoryTO;
	}

	public Double getMinValue() {
		return minValue;
	}

	public void setMinValue(Double minValue) {
		this.minValue = minValue;
	}

	public Double getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(Double maxValue) {
		this.maxValue = maxValue;
	}

	public Double getRewardPoints() {
		return rewardPoints;
	}

	public void setRewardPoints(Double rewardPoints) {
		this.rewardPoints = rewardPoints;
	}

	public Double getStatusPoints() {
		return statusPoints;
	}

	public void setStatusPoints(Double statusPoints) {
		this.statusPoints = statusPoints;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	
	
	
}
