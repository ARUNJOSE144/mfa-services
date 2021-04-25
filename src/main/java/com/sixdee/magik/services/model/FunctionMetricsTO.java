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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author arun.jose
 * @Date : September, 2018
 *
 */

@Entity
@Table(name = "RE_FUNCTION_METRICS")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class FunctionMetricsTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "FunctionMetricsTO")
	@TableGenerator(name = "FunctionMetricsTO", allocationSize = 1)
	@Column(name = "ID")
	private int id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "JSON")
	private String json;

	@Column(name = "CREATE_DATE")
	private Date createDate;

	@Column(name = "Updated_DATE")
	private String updatedDate;

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

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
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

	@Override
	public String toString() {
		return "FunctionMetricsTO [id=" + id + ", name=" + name + ", json=" + json + ", createDate=" + createDate
				+ ", updatedDate=" + updatedDate + ", userId=" + userId + ", createDateUI=" + createDateUI + "]";
	}

}
