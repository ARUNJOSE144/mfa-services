package com.sixdee.magik.services.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Basil Jose
 * @version 1.0.0.0
 * @since Dec 6, 2017 : 5:58:48 PM
 */

@Entity
@Table(name = "MFS_CONFIG_PARAMETERS")
public class ConfigParam implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private String paramId;

	@Column(name = "PARAMETER_NAME", nullable = false)
	private String paramName;

	@Column(name = "PARAMETER_VALUE", nullable = false)
	private String paramValue;

	@Column(name = "PARAMETER_DESC")
	private String paramDesc;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE", updatable = false)
	private Date createdDate;

	@Column(name = "CREATED_USER")
	private String createdUser;

	/** -----------------------------------------------------. **/

	public String getParamId() {
		return paramId;
	}

	public void setParamId(String paramId) {
		this.paramId = paramId;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public String getParamDesc() {
		return paramDesc;
	}

	public void setParamDesc(String paramDesc) {
		this.paramDesc = paramDesc;
	}

	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "ConfigParam [paramId=" + paramId + ", paramName=" + paramName + ", paramValue=" + paramValue
				+ ", paramDesc=" + paramDesc + ", createdDate=" + createdDate + ", createdUser=" + createdUser + "]";
	}

}