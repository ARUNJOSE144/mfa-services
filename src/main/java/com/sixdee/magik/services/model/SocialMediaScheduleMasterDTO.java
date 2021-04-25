package com.sixdee.magik.services.model;
/**
 * @author amal.a.s
 * @Date : June, 2020
 *
 */

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name="SOCIAL_MEDIA_SCHEDULING_MASTER")
@DynamicUpdate(true)
@JsonInclude(Include.NON_NULL)
public class SocialMediaScheduleMasterDTO {

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="SocialMediaScheduleMasterDTO")
	@TableGenerator(name="SocialMediaScheduleMasterDTO")
	@Column(name="ID")
	private int id;
	
	@Column(name="CAMPAIGN_ID")
	private int campaignId;
	
	@Column(name="SCHEDULING_TYPE")
	private String schedulingType;
	
	@Column(name="SCHEDULED_STATUS")
	private int scheduledStatus;
	
	@Column(name="SCHEDULE_START_DATE")
	private String scheduleStartDate;
	
	@Column(name="SCHEDULE_END_DATE")
	private String scheduleExpiryDate;
	
	@Column(name="SCHEDULED_DATE")
	private String scheduledDate;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "socialMediaMaster")
	private List<SocialMediaScheduleMappingDTO> mappingDetails;
	
	@Transient
	private List<IdNameDTO> data;
	@Transient
	private String status;
	@Transient
	private String statusDesc;
	@Transient
	private List<SocialMediaScheduleMasterDTO> listData;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(int campaignId) {
		this.campaignId = campaignId;
	}

	public String getSchedulingType() {
		return schedulingType;
	}

	public void setSchedulingType(String schedulingType) {
		this.schedulingType = schedulingType;
	}

	public List<SocialMediaScheduleMappingDTO> getMappingDetails() {
		return mappingDetails;
	}

	public void setMappingDetails(List<SocialMediaScheduleMappingDTO> mappingDetails) {
		this.mappingDetails = mappingDetails;
	}

	public List<IdNameDTO> getData() {
		return data;
	}

	public void setData(List<IdNameDTO> data) {
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public List<SocialMediaScheduleMasterDTO> getListData() {
		return listData;
	}

	public void setListData(List<SocialMediaScheduleMasterDTO> listData) {
		this.listData = listData;
	}

	public int getScheduledStatus() {
		return scheduledStatus;
	}

	public void setScheduledStatus(int scheduledStatus) {
		this.scheduledStatus = scheduledStatus;
	}

	public String getScheduleStartDate() {
		return scheduleStartDate;
	}

	public void setScheduleStartDate(String scheduleStartDate) {
		this.scheduleStartDate = scheduleStartDate;
	}

	public String getScheduleExpiryDate() {
		return scheduleExpiryDate;
	}

	public void setScheduleExpiryDate(String scheduleExpiryDate) {
		this.scheduleExpiryDate = scheduleExpiryDate;
	}

	public String getScheduledDate() {
		return scheduledDate;
	}

	public void setScheduledDate(String scheduledDate) {
		this.scheduledDate = scheduledDate;
	}

	@Override
	public String toString() {
		return "SocialMediaScheduleMasterDTO [id=" + id + ", campaignId=" + campaignId + ", schedulingType="
				+ schedulingType + ", scheduledStatus=" + scheduledStatus + ", scheduleStartDate=" + scheduleStartDate
				+ ", scheduleExpiryDate=" + scheduleExpiryDate + ", scheduledDate=" + scheduledDate
				+ ", mappingDetails=" + mappingDetails + ", data=" + data + ", status=" + status + ", statusDesc="
				+ statusDesc + ", listData=" + listData + "]";
	}


	
}
