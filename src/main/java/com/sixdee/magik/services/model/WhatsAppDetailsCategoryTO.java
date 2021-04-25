package com.sixdee.magik.services.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : January, 2021
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "CMP_MSG_WHATSAPP_CATEGORY")
public class WhatsAppDetailsCategoryTO {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "WhatsAppDetailsTO")
	@TableGenerator(name = "WhatsAppDetailsTO", allocationSize = 1)
	@Column(name = "ID")
	private int autoId;
	
	@Column(name = "CATEGORY_NAME")
	private String campName;
	
	@Column(name = "CATEGORY_ORDER")
	private int categoryOrder;
	
	@Column(name = "CREATED_BY")
	private int createdUserId;
	
	@CreationTimestamp
	@Column(name="CREATED_DATE",nullable = false, updatable = false)
	private Date createdDate;
	
	@UpdateTimestamp
	@Column(name="MODIFIED_DATE")
	private Date modifiedDate;
	
	@OneToMany(mappedBy = "whatsAppDetailsCategoryTO",fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
	private List<WhatsAppDetailsTO> whatsAppDetailsTO = new ArrayList<>();

	public int getAutoId() {
		return autoId;
	}

	public void setAutoId(int autoId) {
		this.autoId = autoId;
	}

	public String getCampName() {
		return campName;
	}

	public void setCampName(String campName) {
		this.campName = campName;
	}

	public int getCategoryOrder() {
		return categoryOrder;
	}

	public void setCategoryOrder(int categoryOrder) {
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

	public List<WhatsAppDetailsTO> getWhatsAppDetailsTO() {
		return whatsAppDetailsTO;
	}

	public void setWhatsAppDetailsTO(List<WhatsAppDetailsTO> whatsAppDetailsTO) {
		this.whatsAppDetailsTO = whatsAppDetailsTO;
	}

}
