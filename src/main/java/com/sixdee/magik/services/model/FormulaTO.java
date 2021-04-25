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
 * @Date : December, 2018
 *
 */

@Entity
@Table(name = "RE_FORMULA_INFO")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class FormulaTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "FormulaTO")
	@TableGenerator(name = "FormulaTO", allocationSize = 1)
	@Column(name = "ID")
	private int id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "DATA")
	private String data;

	@Column(name = "CREATE_DATE")
	private Date createDate;

	@Column(name = "USER_ID")
	private int userId;

	@Column(name = "FORMULA_PREVIEW")
	private String formulaPreview;

	@Transient
	private String profileName;

	@Transient
	private String createDateUI;

	public String getCreateDateUI() {
		return createDateUI;
	}

	public void setCreateDateUI(String createDateUI) {
		this.createDateUI = createDateUI;
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
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

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public String getFormulaPreview() {
		return formulaPreview;
	}

	public void setFormulaPreview(String formulaPreview) {
		this.formulaPreview = formulaPreview;
	}

	@Override
	public String toString() {
		return "FormulaTO [id=" + id + ", name=" + name + ", description=" + description + ", data=" + data
				+ ", createDate=" + createDate + ", userId=" + userId + ", formulaPreview=" + formulaPreview
				+ ", profileName=" + profileName + ", createDateUI=" + createDateUI + "]";
	}

}
