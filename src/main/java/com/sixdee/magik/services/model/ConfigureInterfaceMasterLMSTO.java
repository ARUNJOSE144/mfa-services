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
 * @Date : March, 2021
 */
 

@SuppressWarnings("serial")
@Entity
@Table(name = "LMS_CNFG_INTERFACE_MASTER")
public class ConfigureInterfaceMasterLMSTO {
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ConfigureInterfaceMasterLMSTO")
	@TableGenerator(name = "ConfigureInterfaceMasterLMSTO", allocationSize = 1)
	@Column(name = "INTERFACEID")
	private int interfaceID;
	
	@Column(name = "INTERFACENAME")
	private String interfaceName;
	
	@CreationTimestamp
	@Column(name="CREATEDATE",nullable = false, updatable = false)
	private Date createdDate;
	
	@UpdateTimestamp
	@Column(name="UPDATEDATE")
	private Date updateDate;

	public int getInterfaceID() {
		return interfaceID;
	}

	public void setInterfaceID(int interfaceID) {
		this.interfaceID = interfaceID;
	}

	public String getInterfaceName() {
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	

}
