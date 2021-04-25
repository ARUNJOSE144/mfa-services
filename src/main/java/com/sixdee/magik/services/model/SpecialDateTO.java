package com.sixdee.magik.services.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

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
@Table(name = "RE_QUARANTINE_SPECIAL_DAYS")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class SpecialDateTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SpecialDateTO")
	@TableGenerator(name = "SpecialDateTO", allocationSize = 1)

	@Column(name = "ID")
	private int id;

	@Column(name = "POLICY_NAME")
	private String policyName;

	@Column(name = "CREATE_USER_ID")
	private int createUserId;

	@Column(name = "CREATE_DATE")
	private String createDate;

	@Column(name = "MODIFIED_DATE")
	private String ModifiedDate;

	@Column(name = "FILE_UPLOAD_NAME")
	private String fileName;

	@Transient
	private String specialDate;

	@Transient
	private String description;

	@Transient
	private String specialDateStartTime;

	@Transient
	private String specialDateEndTime;

	@Transient
	private int specialDayId;

	@Transient
	private String marketingPlanId;

	@Transient
	private String marketingPlanName;

	@Transient
	private String isFromFile;

	@Transient
	private int detailsId;

	@Transient
	private Set<SpecialDateDetailsTO> specialDaysDetails;

	@Transient
	private List<SpecialDateTO> Desclist;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	public int getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(int createUserId) {
		this.createUserId = createUserId;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getModifiedDate() {
		return ModifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
		ModifiedDate = modifiedDate;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getSpecialDate() {
		return specialDate;
	}

	public void setSpecialDate(String specialDate) {
		this.specialDate = specialDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getSpecialDayId() {
		return specialDayId;
	}

	public void setSpecialDayId(int specialDayId) {
		this.specialDayId = specialDayId;
	}

	public String getIsFromFile() {
		return isFromFile;
	}

	public void setIsFromFile(String isFromFile) {
		this.isFromFile = isFromFile;
	}

	public int getDetailsId() {
		return detailsId;
	}

	public void setDetailsId(int detailsId) {
		this.detailsId = detailsId;
	}

	public Set<SpecialDateDetailsTO> getSpecialDaysDetails() {
		return specialDaysDetails;
	}

	public void setSpecialDaysDetails(Set<SpecialDateDetailsTO> specialDaysDetails) {
		this.specialDaysDetails = specialDaysDetails;
	}

	public List<SpecialDateTO> getDesclist() {
		return Desclist;
	}

	public void setDesclist(List<SpecialDateTO> desclist) {
		Desclist = desclist;
	}

	public String getSpecialDateStartTime() {
		return specialDateStartTime;
	}

	public void setSpecialDateStartTime(String specialDateStartTime) {
		this.specialDateStartTime = specialDateStartTime;
	}

	public String getSpecialDateEndTime() {
		return specialDateEndTime;
	}

	public void setSpecialDateEndTime(String specialDateEndTime) {
		this.specialDateEndTime = specialDateEndTime;
	}

	public String getMarketingPlanId() {
		return marketingPlanId;
	}

	public void setMarketingPlanId(String marketingPlanId) {
		this.marketingPlanId = marketingPlanId;
	}

	public String getMarketingPlanName() {
		return marketingPlanName;
	}

	public void setMarketingPlanName(String marketingPlanName) {
		this.marketingPlanName = marketingPlanName;
	}

	@Override
	public String toString() {
		return "SpecialDateTO [id=" + id + ", policyName=" + policyName + ", createUserId=" + createUserId
				+ ", createDate=" + createDate + ", ModifiedDate=" + ModifiedDate + ", fileName=" + fileName
				+ ", specialDate=" + specialDate + ", description=" + description + ", specialDateStartTime="
				+ specialDateStartTime + ", specialDateEndTime=" + specialDateEndTime + ", specialDayId=" + specialDayId
				+ ", marketingPlanId=" + marketingPlanId + ", marketingPlanName=" + marketingPlanName + ", isFromFile="
				+ isFromFile + ", detailsId=" + detailsId + ", specialDaysDetails=" + specialDaysDetails + ", Desclist="
				+ Desclist + "]";
	}

}
