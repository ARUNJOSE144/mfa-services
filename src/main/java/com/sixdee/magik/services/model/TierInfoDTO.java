package com.sixdee.magik.services.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="TIER_REWARD_POINTS_DETAILS")
public class TierInfoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	
	@Column(name="TIER_ID",nullable = true)
	private int tierId;
	
	@Column(name="CATEGORY_ID",nullable = true)
	private int categoryId;
	
	@Column(name="MAX_VALUE",nullable = true)
	private String maxValue;
	
	@Column(name="MIN_VALUE",nullable = true)
	private String minValue;
	
	@Column(name="REWARD_POINTS",nullable = true)
	private String rewardPts;
	
	@Column(name="STATUS_POINTS",nullable = true)
	private String statusPts;
	
	@Transient
	private String tierName;
	
	@Transient
	private String tierDesc;
	
	@Transient
	private List categoryList = new ArrayList();
	
	@Transient
	private String categoryName;
	
	@Transient
	private List tierList = new ArrayList();
	
	@Transient
	private String unitCalculation;
	
	@Transient
	private List viewList = new ArrayList();
	
	@Transient
	private HashMap<Integer, String> tierMap = new HashMap<Integer, String>();
	
	@Transient
	private HashMap<Integer, String> categoryMap = new HashMap<Integer, String>();
	
	@Transient
	private HashMap<String, List> tierCategoryMap = new HashMap<String, List>();
	
	@Transient
	private int recordId;
	
	@Transient
	private int status;
	
	@Transient
	private String categoryDesc;
	
	public String getCategoryDesc() {
		return categoryDesc;
	}
	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}
	public String getMinValue() {
		return minValue;
	}
	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}
	public String getRewardPts() {
		return rewardPts;
	}
	public void setRewardPts(String rewardPts) {
		this.rewardPts = rewardPts;
	}
	public String getStatusPts() {
		return statusPts;
	}
	public void setStatusPts(String statusPts) {
		this.statusPts = statusPts;
	}
	public List getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(List categoryList) {
		this.categoryList = categoryList;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public int getTierId() {
		return tierId;
	}
	public void setTierId(int tierId) {
		this.tierId = tierId;
	}
	public List getTierList() {
		return tierList;
	}
	public void setTierList(List tierList) {
		this.tierList = tierList;
	}
	public String getUnitCalculation() {
		return unitCalculation;
	}
	public void setUnitCalculation(String unitCalculation) {
		this.unitCalculation = unitCalculation;
	}
	public List getViewList() {
		return viewList;
	}
	public void setViewList(List viewList) {
		this.viewList = viewList;
	}
	public HashMap<Integer, String> getTierMap() {
		return tierMap;
	}
	public void setTierMap(HashMap<Integer, String> tierMap) {
		this.tierMap = tierMap;
	}
	public HashMap<Integer, String> getCategoryMap() {
		return categoryMap;
	}
	public void setCategoryMap(HashMap<Integer, String> categoryMap) {
		this.categoryMap = categoryMap;
	}
	public HashMap<String, List> getTierCategoryMap() {
		return tierCategoryMap;
	}
	public void setTierCategoryMap(HashMap<String, List> tierCategoryMap) {
		this.tierCategoryMap = tierCategoryMap;
	}
	public int getRecordId() {
		return recordId;
	}
	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
