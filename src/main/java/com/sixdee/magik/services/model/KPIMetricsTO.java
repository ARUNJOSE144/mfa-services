package com.sixdee.magik.services.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Rajesh
 * @Date : November, 2018
 *
 */

@Entity
@Table(name = "RE_METRICS_INFO")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class KPIMetricsTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "KPIMetricsTO")
	@TableGenerator(name = "KPIMetricsTO", allocationSize = 1)
	@Column(name = "ID")
	private int id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "JSON")
	private String metricsJson;

	@Column(name = "CREATE_DATE")
	private Date createDate;

	@Column(name = "USER_ID")
	private int userId;
	
	@Transient
	private String createDateUI;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMetricsJson() {
		return metricsJson;
	}

	public void setMetricsJson(String metricsJson) {
		this.metricsJson = metricsJson;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getCreateDateUI() {
		return createDateUI;
	}

	public void setCreateDateUI(String createDateUI) {
		this.createDateUI = createDateUI;
	}
	
	

}
