package com.sixdee.magik.services.model;

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
@Table(name = "REWARD_POINTS_CATEGORY")
public class CategoryLoyaltyLMSTO {
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "CategoryLoyaltyLMSTO")
	@TableGenerator(name = "CategoryLoyaltyLMSTO", allocationSize = 1)
	@Column(name = "CATEGORY_ID")
	private int catId;
	
	@Column(name = "CATEGORY_NAME")
	private String categoryName;
	
	@Column(name = "CATEGORY_DESC")
	private String categoryDesc;
	
	@Column(name = "UNITS_CALCULATION")
	private String unitCalc;
	
	@CreationTimestamp
	@Column(name="CREATE_TIME",nullable = false, updatable = false)
	private Date createdDate;

	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryDesc() {
		return categoryDesc;
	}

	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}

	public String getUnitCalc() {
		return unitCalc;
	}

	public void setUnitCalc(String unitCalc) {
		this.unitCalc = unitCalc;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	
}
