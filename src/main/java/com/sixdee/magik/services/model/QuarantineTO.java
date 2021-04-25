package com.sixdee.magik.services.model;

import java.util.List;

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
@Table(name = "RE_QUARANTINE_POLICY_OLD")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class QuarantineTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "QuarantineTO")
	@TableGenerator(name = "QuarantineTO", allocationSize = 1)
	@Column(name = "ID")
	private int id;

	@Column(name = "POLICY_NAME")
	private String policyName;

	@Column(name = "CREATE_DATE")
	private String createDate;

	@Column(name = "DESCRIPTION")
	private String description;

	@Transient
	private List<QuarantineDetailsTO> detailsAll;
	
	@Transient
	private QuarantineDetailsTO quarantineSpecialDaysDetails;

	@Transient
	private QuarantineDetailsTO quarantineWeekDaysDetails;
	
	@Transient
	private QuarantineDetailsTO quarantineBlackListDetails;
	
	@Transient
	private List<QuarantineBlackListTO> blNumDateList;
	
	@Transient
	private List<QuarantineDetailsTO> SpecialList;
	
	
	@Transient
	private String isFromFile;
	
	
	
	public List<QuarantineDetailsTO> getSpecialList() {
		return SpecialList;
	}

	public void setSpecialList(List<QuarantineDetailsTO> specialList) {
		SpecialList = specialList;
	}

	public String getIsFromFile() {
		return isFromFile;
	}

	public void setIsFromFile(String isFromFile) {
		this.isFromFile = isFromFile;
	}

	public QuarantineDetailsTO getQuarantineBlackListDetails() {
		return quarantineBlackListDetails;
	}

	public void setQuarantineBlackListDetails(QuarantineDetailsTO quarantineBlackListDetails) {
		this.quarantineBlackListDetails = quarantineBlackListDetails;
	}

	public QuarantineDetailsTO getQuarantineWeekDaysDetails() {
		return quarantineWeekDaysDetails;
	}

	public void setQuarantineWeekDaysDetails(QuarantineDetailsTO quarantineWeekDaysDetails) {
		this.quarantineWeekDaysDetails = quarantineWeekDaysDetails;
	}

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

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public QuarantineDetailsTO getQuarantineSpecialDaysDetails() {
		return quarantineSpecialDaysDetails;
	}

	public void setQuarantineSpecialDaysDetails(QuarantineDetailsTO quarantineSpecialDaysDetails) {
		this.quarantineSpecialDaysDetails = quarantineSpecialDaysDetails;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

	public List<QuarantineBlackListTO> getBlNumDateList() {
		return blNumDateList;
	}

	public void setBlNumDateList(List<QuarantineBlackListTO> blNumDateList) {
		this.blNumDateList = blNumDateList;
	}

	public List<QuarantineDetailsTO> getDetailsAll() {
		return detailsAll;
	}

	public void setDetailsAll(List<QuarantineDetailsTO> detailsAll) {
		this.detailsAll = detailsAll;
	}

	@Override
	public String toString() {
		return "QuarantineTO [id=" + id + ", policyName=" + policyName + ", createDate=" + createDate + ", description="
				+ description + ", detailsAll=" + detailsAll + ", quarantineSpecialDaysDetails="
				+ quarantineSpecialDaysDetails + ", quarantineWeekDaysDetails=" + quarantineWeekDaysDetails
				+ ", quarantineBlackListDetails=" + quarantineBlackListDetails + ", blNumDateList=" + blNumDateList
				+ ", isFromFile=" + isFromFile + "]";
	}

	

	

	

}
