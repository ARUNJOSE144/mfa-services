package com.sixdee.magik.services.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;


/**
 * @author Nakhil Kurian
 * @Date : November 2020
 *
 */
@Entity
@Table(name = "GROUP_COMPARISION")
@SuppressWarnings("serial")

public class GroupComparisonTo implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "GroupComparisonTo")
	@TableGenerator(name = "GroupComparisonTo", allocationSize = 1)
	@Column(name = "SEQUENCE_ID")
	private int sequenceid;

	@Column(name = "UNIQUE_ID")
	private String uniqueid;

	@Column(name = "MARKETING_ID")
	private int campaignId;

	@Column(name = "MARKETING_PLAN")
	private String campaignName;

	@Column(name = "SCHEDULE_ID")
	private int scheduleid;

	@Column(name = "SCHEDULE_NAME")
	private String scheduleName;

	@Column(name = "SAMPLING_ID")
	private int samplingid;

	@Column(name = "SAMPLING_NAME")
	private String samplingName;

	@Column(name = "GROUP_NAME")
	private String groupName;

	@Column(name = "GROUP_TYPE")
	private String groupType;

	@Column(name = "GROUP_VALUE")
	private String groupValue;

	@Column(name = "START_DATE")
	private String startDate;
	@Column(name = "END_DATE")
	private String endDate;

	@Column(name = "ANALYSIS_TYPE")
	private int analysysType;

	@Column(name = "USER_ID")
	private int userid;

	@Column(name = "CREATE_TIME")
	private Timestamp createTime;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "AB_STATUS")
	private String abstatus;

	@Column(name = "PRE_STATUS")
	private String prestatus;

	@Column(name = "TB_TYPE")
	private int tbType;

	@Column(name = "TB_BEFORE")
	private int tbBefore;

	@Column(name = "TB_AFTER")
	private int tbAfter;

	@Column(name = "IS_WEEKLY")
	private int isweekly;

	@Column(name = "NO_OF_WEEKS")
	private int noofweeks;

	@Column(name = "RULE_LEVEL")
	private int rulelevel;

	@Transient
	private String actionId;

	public int getSequenceid() {
		return sequenceid;
	}

	public void setSequenceid(int sequenceid) {
		this.sequenceid = sequenceid;
	}

	public String getUniqueid() {
		return uniqueid;
	}

	public void setUniqueid(String uniqueid) {
		this.uniqueid = uniqueid;
	}

	public int getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(int campaignId) {
		this.campaignId = campaignId;
	}

	public String getCampaignName() {
		return campaignName;
	}

	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}

	public int getScheduleid() {
		return scheduleid;
	}

	public void setScheduleid(int scheduleid) {
		this.scheduleid = scheduleid;
	}

	public String getScheduleName() {
		return scheduleName;
	}

	public void setScheduleName(String scheduleName) {
		this.scheduleName = scheduleName;
	}

	public int getSamplingid() {
		return samplingid;
	}

	public void setSamplingid(int samplingid) {
		this.samplingid = samplingid;
	}

	public String getSamplingName() {
		return samplingName;
	}

	public void setSamplingName(String samplingName) {
		this.samplingName = samplingName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupType() {
		return groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}

	public String getGroupValue() {
		return groupValue;
	}

	public void setGroupValue(String groupValue) {
		this.groupValue = groupValue;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getAnalysysType() {
		return analysysType;
	}

	public void setAnalysysType(int analysysType) {
		this.analysysType = analysysType;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAbstatus() {
		return abstatus;
	}

	public void setAbstatus(String abstatus) {
		this.abstatus = abstatus;
	}

	public String getPrestatus() {
		return prestatus;
	}

	public void setPrestatus(String prestatus) {
		this.prestatus = prestatus;
	}

	public int getTbType() {
		return tbType;
	}

	public void setTbType(int tbType) {
		this.tbType = tbType;
	}

	public int getTbBefore() {
		return tbBefore;
	}

	public void setTbBefore(int tbBefore) {
		this.tbBefore = tbBefore;
	}

	public int getTbAfter() {
		return tbAfter;
	}

	public void setTbAfter(int tbAfter) {
		this.tbAfter = tbAfter;
	}

	public int getIsweekly() {
		return isweekly;
	}

	public void setIsweekly(int isweekly) {
		this.isweekly = isweekly;
	}

	public int getNoofweeks() {
		return noofweeks;
	}

	public void setNoofweeks(int noofweeks) {
		this.noofweeks = noofweeks;
	}

	public int getRulelevel() {
		return rulelevel;
	}

	public void setRulelevel(int rulelevel) {
		this.rulelevel = rulelevel;
	}

	public String getActionId() {
		return actionId;
	}

	public void setActionId(String actionId) {
		this.actionId = actionId;
	}

	@Override
	public String toString() {
		return "GroupComparisonTo [sequenceid=" + sequenceid + ", uniqueid=" + uniqueid + ", campaignId=" + campaignId
				+ ", campaignName=" + campaignName + ", scheduleid=" + scheduleid + ", scheduleName=" + scheduleName
				+ ", samplingid=" + samplingid + ", samplingName=" + samplingName + ", groupName=" + groupName
				+ ", groupType=" + groupType + ", groupValue=" + groupValue + ", startDate=" + startDate + ", endDate="
				+ endDate + ", analysysType=" + analysysType + ", userid=" + userid + ", createTime=" + createTime
				+ ", status=" + status + ", abstatus=" + abstatus + ", prestatus=" + prestatus + ", tbType=" + tbType
				+ ", tbBefore=" + tbBefore + ", tbAfter=" + tbAfter + ", isweekly=" + isweekly + ", noofweeks="
				+ noofweeks + ", rulelevel=" + rulelevel + ", actionId=" + actionId + "]";
	}

}
