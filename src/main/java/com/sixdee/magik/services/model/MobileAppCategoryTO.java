package com.sixdee.magik.services.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "MOBILE_APP_CATEGORY_MASTER")

public class MobileAppCategoryTO {
	
	/*@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "MobileAppCategoryTO")
	@TableGenerator(name = "MobileAppCategoryTO", allocationSize = 1)
	@Column(name = "CATEGORY_ID")
	private int campId;*/
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CATEGORY_ID")
	private int campId;

	@Column(name = "CATEGORY_NAME")
	private String campName;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "CATEGORY_ORDER")
	private String categoryOrder;

	@Transient
	private String statusCode;
	
	@Column(name = "CREATED_BY")
	private int createdUserId;
	
	@CreationTimestamp
	@Column(name="CREATED_DATE",nullable = false, updatable = false)
	private Date createdDate;
	
	@UpdateTimestamp
	@Column(name="MODIFIED_DATE")
	private Date modifiedDate;
	

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public int getCampId() {
		return campId;
	}

	public void setCampId(int campId) {
		this.campId = campId;
	}

	public String getCampName() {
		return campName;
	}

	public void setCampName(String campName) {
		this.campName = campName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCategoryOrder() {
		return categoryOrder;
	}

	public void setCategoryOrder(String categoryOrder) {
		this.categoryOrder = categoryOrder;
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
