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
import org.hibernate.annotations.UpdateTimestamp;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : January, 2020
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "CONFIGURE_CATEGORY_LOY")
public class ConfigureCategoryTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ConfigureCategoryTO")
	@TableGenerator(name = "ConfigureCategoryTO", allocationSize = 1)
	@Column(name = "ID")
	private int autoId;
	
	@Column(name = "CATEGORYNAME")
	private String categoryName;
	
	@Column(name = "CREATED_BY")
	private int createdUserId;
	
	@CreationTimestamp
	@Column(name="CREATED_DATE",nullable = false, updatable = false)
	private Date createdDate;
	
	@UpdateTimestamp
	@Column(name="MODIFIED_DATE")
	private Date modifiedDate;

	public int getAutoId() {
		return autoId;
	}

	public void setAutoId(int autoId) {
		this.autoId = autoId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(int createdUserId) {
		this.createdUserId = createdUserId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	
	

}
