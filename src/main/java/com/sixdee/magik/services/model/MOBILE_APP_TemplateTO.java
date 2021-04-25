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
import javax.persistence.Transient;

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
@Table(name = "CMP_MSG_MOBILE_APP_TEMPLATE")
public class MOBILE_APP_TemplateTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "MOBILE_APP_TemplateTO")
	@TableGenerator(name = "MOBILE_APP_TemplateTO", allocationSize = 1)
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
	
	
	@JsonIgnore //Remove according to UI data pull
	@OneToMany(mappedBy = "mobileAppTemplateTO",fetch = FetchType.EAGER,cascade = {CascadeType.REMOVE})
	@Fetch(value = FetchMode.SUBSELECT)
	private List<MOBILE_APP_TemplateDtlsTO> mobileApp_TemplateDtlsTO = new ArrayList<>();
	
	
	@Transient
	private List<MOBILE_APP_TemplateDtlsTO> inboundMessages;
	
	@Transient
	private List<MOBILE_APP_TemplateDtlsTO> outBoundMessages;
	
	
	
	@ManyToOne
	@JoinColumn(name="CATEGORY_ID")
	@JsonIgnore
	private MOBILE_APP_CategoryTO mobileAppCategoryTO = new MOBILE_APP_CategoryTO();

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

	public List<MOBILE_APP_TemplateDtlsTO> getMobileApp_TemplateDtlsTO() {
		return mobileApp_TemplateDtlsTO;
	}

	public void setMobileApp_TemplateDtlsTO(List<MOBILE_APP_TemplateDtlsTO> mobileApp_TemplateDtlsTO) {
		this.mobileApp_TemplateDtlsTO = mobileApp_TemplateDtlsTO;
	}

	public MOBILE_APP_CategoryTO getMobileAppCategoryTO() {
		return mobileAppCategoryTO;
	}

	public void setMobileAppCategoryTO(MOBILE_APP_CategoryTO mobileAppCategoryTO) {
		this.mobileAppCategoryTO = mobileAppCategoryTO;
	}

	public List<MOBILE_APP_TemplateDtlsTO> getInboundMessages() {
		return inboundMessages;
	}

	public void setInboundMessages(List<MOBILE_APP_TemplateDtlsTO> inboundMessages) {
		this.inboundMessages = inboundMessages;
	}

	public List<MOBILE_APP_TemplateDtlsTO> getOutBoundMessages() {
		return outBoundMessages;
	}

	public void setOutBoundMessages(List<MOBILE_APP_TemplateDtlsTO> outBoundMessages) {
		this.outBoundMessages = outBoundMessages;
	}
	
	

	

}
