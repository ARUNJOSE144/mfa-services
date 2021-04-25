package com.sixdee.magik.services.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : March, 2021
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "LMS_CNFG_USER_CATEGORY_MASTER")
public class UserCategoryLMSTO {
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "UserCategoryLMSTO")
	@TableGenerator(name = "UserCategoryLMSTO", allocationSize = 1)
	@Column(name = "CATEGORYID")
	private double categoryId;
	
	@Column(name = "CATEGORYNAME")
	private String categoryName;

	public double getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(double categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
}
