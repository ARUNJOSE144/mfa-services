package com.sixdee.magik.services.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name="APPLICATION_STATUS_MESSAGES")
public class StatusCodeDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "StatusCodeDTO")
	@TableGenerator(name = "StatusCodeDTO", allocationSize = 1)
	@Column(name = "STATUS_ID")
	private int statusId;
	
	@Column(name = "STATUS_CODE")
	private String statusCode;
	
	@Column(name = "STATUS_DESCRIPTION")
	private String statusDescription;
	
	@Column(name = "SERVICE_NAME")
	private String serviceName;
	
	@Column(name = "TYPE")
	private String type;

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusDescription() {
		return statusDescription;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
