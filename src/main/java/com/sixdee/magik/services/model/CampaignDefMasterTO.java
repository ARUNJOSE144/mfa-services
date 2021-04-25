package com.sixdee.magik.services.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : February, 2021
 */

@Entity
@Table(name = "CAMPAIGN_DEFINTION_MASTER")
@SuppressWarnings("serial")
public class CampaignDefMasterTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "CampaignDefMasterTO")
	@TableGenerator(name = "CampaignDefMasterTO", allocationSize = 1)
	@Column(name = "ID")
	private int id;

	@Column(name = "CAMPAIGN_NAME")
	private String campaignName;

	@Column(name = "CAMPAIGN_DESCRIPTION")
	private String campaignDesc;

	@Column(name = "CAMPAIGN_TYPE")
	private int campaignType;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "START_DATE", updatable = false)
	private Date startDate;

	@Column(name = "END_DATE")
	private Date endDate;

	@Column(name = "IS_TEMPLATE")
	private int isTemplate;

	@Column(name = "CREATE_DATE", updatable = false)
	private Date createDate;

	@Column(name = "TARGET_COUNT")
	private String targetCount;

	@Column(name = "PLAY_PAUSE")
	private int playPauseStatus;

	@Column(name = "IS_DEFAULT_TEMPLATE")
	private int defaultTemplate;

	@Column(name = "CREATED_BY")
	private int userId;
	
	@Column(name = "SEGMENT_TYPE")
	private int campaignSegmentType;


	@Column(name = "GEO_FENCING_ID")
	private int geoFencingId;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getCampaignName() {
		return campaignName;
	}


	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}


	public String getCampaignDesc() {
		return campaignDesc;
	}


	public void setCampaignDesc(String campaignDesc) {
		this.campaignDesc = campaignDesc;
	}


	public int getCampaignType() {
		return campaignType;
	}


	public void setCampaignType(int campaignType) {
		this.campaignType = campaignType;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	public int getIsTemplate() {
		return isTemplate;
	}


	public void setIsTemplate(int isTemplate) {
		this.isTemplate = isTemplate;
	}


	public Date getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	public String getTargetCount() {
		return targetCount;
	}


	public void setTargetCount(String targetCount) {
		this.targetCount = targetCount;
	}


	public int getPlayPauseStatus() {
		return playPauseStatus;
	}


	public void setPlayPauseStatus(int playPauseStatus) {
		this.playPauseStatus = playPauseStatus;
	}


	public int getDefaultTemplate() {
		return defaultTemplate;
	}


	public void setDefaultTemplate(int defaultTemplate) {
		this.defaultTemplate = defaultTemplate;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public int getCampaignSegmentType() {
		return campaignSegmentType;
	}


	public void setCampaignSegmentType(int campaignSegmentType) {
		this.campaignSegmentType = campaignSegmentType;
	}


	public int getGeoFencingId() {
		return geoFencingId;
	}


	public void setGeoFencingId(int geoFencingId) {
		this.geoFencingId = geoFencingId;
	}
	
	


}
