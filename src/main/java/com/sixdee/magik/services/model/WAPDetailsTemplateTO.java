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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : January, 2021
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "CMP_MSG_WAP_TEMPLATE")
public class WAPDetailsTemplateTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "WAPDetailsTemplateTO")
	@TableGenerator(name = "WAPDetailsTemplateTO", allocationSize = 1)
	@Column(name = "ID")
	private int autoId;
	
	@Column(name = "MESSAGE_NAME")
	private String messageName;
	
	
	@Column(name = "CREATED_BY")
	private int createdUserId;
	
	@CreationTimestamp
	@Column(name="CREATED_DATE",nullable = false, updatable = false)
	private Date createdDate;
	
	@UpdateTimestamp
	@Column(name="MODIFIED_DATE")
	private Date modifiedDate;
	
	@OneToMany(mappedBy = "wapDetailsTemplateTO",fetch = FetchType.EAGER,cascade = {CascadeType.REMOVE})
	@Fetch(value = FetchMode.SUBSELECT)
	private List<WAPDetailsTemplateDtlsTO> wapDetailsTemplateDtlsTO = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name="CATEGORY_ID")
	@JsonIgnore
	private WAPDetailsCategoryTO wapDetailsCategoryTO = new WAPDetailsCategoryTO();

	public int getAutoId() {
		return autoId;
	}

	public void setAutoId(int autoId) {
		this.autoId = autoId;
	}

	public String getMessageName() {
		return messageName;
	}

	public void setMessageName(String messageName) {
		this.messageName = messageName;
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

	public List<WAPDetailsTemplateDtlsTO> getWapDetailsTemplateDtlsTO() {
		return wapDetailsTemplateDtlsTO;
	}

	public void setWapDetailsTemplateDtlsTO(List<WAPDetailsTemplateDtlsTO> wapDetailsTemplateDtlsTO) {
		this.wapDetailsTemplateDtlsTO = wapDetailsTemplateDtlsTO;
	}

	public WAPDetailsCategoryTO getWapDetailsCategoryTO() {
		return wapDetailsCategoryTO;
	}

	public void setWapDetailsCategoryTO(WAPDetailsCategoryTO wapDetailsCategoryTO) {
		this.wapDetailsCategoryTO = wapDetailsCategoryTO;
	}
	
	
	

	
	
	
	
	
	
	
	
	

}
