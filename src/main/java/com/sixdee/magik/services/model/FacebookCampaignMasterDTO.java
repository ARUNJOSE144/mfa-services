package com.sixdee.magik.services.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "FACEBOOK_CAMPAIGN_MASTER")
@DynamicUpdate(true)
public class FacebookCampaignMasterDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "FacebookCampaignMasterDTO")
	@TableGenerator(name = "FacebookCampaignMasterDTO")
	@Column(name = "ID")
	private int id;

	@Column(name = "TARGETING_ID")
	private int targetingId;

	@Column(name = "FB_PAGE")
	private String facebookPage;

	@Column(name = "CAMPAIGN_MEDIA_FORMAT")
	private int campaignMediaFormat;

	@Column(name = "MEDIA_NAME")
	private String mediaName;

	@Column(name = "TITLE_NAME")
	private String titleName;

	@Column(name = "TITLE_DESCRIPTION")
	private String titleDescription;

	@Column(name = "MEDIA_PATH")
	private String mediaPath;

	@Column(name = "BID")
	private String bid;

	@Column(name = "DAILY_BUDGET")
	private String dailyBudget;

	@Column(name = "TOTAL_BUDGET")
	private String totalBudget;

	@Column(name = "CUSTOM_AUDIENCE")
	private int isCustomAudience;

	@Column(name = "CUSTOM_AUDIENCE_FILE")
	private String customAudienceFile;

	@Column(name = "FB_CATEGORY")
	private String fbCategroy;

	@Column(name = "AUDIENCE_ATTACH_TYPE")
	private int audienceAttachType;

	@Column(name = "CAMPAIGN_OBJECTIVE")
	private String campObjective;

	@Column(name = "SCHEDULED")
	private int scheduled;
	
	@Column(name = "POST_ID")
	private String postId;
	
	@Column(name="LIFETIME_BUDGET")
	private String lifeTimeBudget;
	
	@Column(name="URL")
	private String url;

	@ManyToOne
	@JoinColumn(name = "CAMPAIGN_ID", nullable = false)
	private CampaignMasterTO facebookCampaignMaster;

	public FacebookCampaignMasterDTO() {
	}

	public FacebookCampaignMasterDTO(int id, int targetingId, String facebookPage, int campaignMediaFormat,
			String mediaName, String titleName, String titleDescription, String mediaPath, String bid,
			String dailyBudget, String totalBudget, int isCustomAudience, String customAudienceFile, String fbCategroy,
			int audienceAttachType, String campObjective, CampaignMasterTO facebookCampaignMaster) {
		super();
		this.id = id;
		this.targetingId = targetingId;
		this.facebookPage = facebookPage;
		this.campaignMediaFormat = campaignMediaFormat;
		this.mediaName = mediaName;
		this.titleName = titleName;
		this.titleDescription = titleDescription;
		this.mediaPath = mediaPath;
		this.bid = bid;
		this.dailyBudget = dailyBudget;
		this.totalBudget = totalBudget;
		this.isCustomAudience = isCustomAudience;
		this.customAudienceFile = customAudienceFile;
		this.fbCategroy = fbCategroy;
		this.audienceAttachType = audienceAttachType;
		this.campObjective = campObjective;
		this.facebookCampaignMaster = facebookCampaignMaster;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTargetingId() {
		return targetingId;
	}

	public void setTargetingId(int targetingId) {
		this.targetingId = targetingId;
	}

	public String getFacebookPage() {
		return facebookPage;
	}

	public void setFacebookPage(String facebookPage) {
		this.facebookPage = facebookPage;
	}

	public int getCampaignMediaFormat() {
		return campaignMediaFormat;
	}

	public void setCampaignMediaFormat(int campaignMediaFormat) {
		this.campaignMediaFormat = campaignMediaFormat;
	}

	public String getMediaName() {
		return mediaName;
	}

	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	public String getTitleDescription() {
		return titleDescription;
	}

	public void setTitleDescription(String titleDescription) {
		this.titleDescription = titleDescription;
	}

	public String getMediaPath() {
		return mediaPath;
	}

	public void setMediaPath(String mediaPath) {
		this.mediaPath = mediaPath;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getDailyBudget() {
		return dailyBudget;
	}

	public void setDailyBudget(String dailyBudget) {
		this.dailyBudget = dailyBudget;
	}

	public String getTotalBudget() {
		return totalBudget;
	}

	public void setTotalBudget(String totalBudget) {
		this.totalBudget = totalBudget;
	}

	public CampaignMasterTO getFacebookCampaignMaster() {
		return facebookCampaignMaster;
	}

	public void setFacebookCampaignMaster(CampaignMasterTO facebookCampaignMaster) {
		this.facebookCampaignMaster = facebookCampaignMaster;
	}

	public int getIsCustomAudience() {
		return isCustomAudience;
	}

	public void setIsCustomAudience(int isCustomAudience) {
		this.isCustomAudience = isCustomAudience;
	}

	public String getCustomAudienceFile() {
		return customAudienceFile;
	}

	public void setCustomAudienceFile(String customAudienceFile) {
		this.customAudienceFile = customAudienceFile;
	}

	public String getFbCategroy() {
		return fbCategroy;
	}

	public void setFbCategroy(String fbCategroy) {
		this.fbCategroy = fbCategroy;
	}

	public int getAudienceAttachType() {
		return audienceAttachType;
	}

	public void setAudienceAttachType(int audienceAttachType) {
		this.audienceAttachType = audienceAttachType;
	}

	public String getCampObjective() {
		return campObjective;
	}

	public void setCampObjective(String campObjective) {
		this.campObjective = campObjective;
	}

	public int getScheduled() {
		return scheduled;
	}

	public void setScheduled(int scheduled) {
		this.scheduled = scheduled;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getLifeTimeBudget() {
		return lifeTimeBudget;
	}

	public void setLifeTimeBudget(String lifeTimeBudget) {
		this.lifeTimeBudget = lifeTimeBudget;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "FacebookCampaignMasterDTO [id=" + id + ", targetingId=" + targetingId + ", facebookPage=" + facebookPage
				+ ", campaignMediaFormat=" + campaignMediaFormat + ", mediaName=" + mediaName + ", titleName="
				+ titleName + ", titleDescription=" + titleDescription + ", mediaPath=" + mediaPath + ", bid=" + bid
				+ ", dailyBudget=" + dailyBudget + ", totalBudget=" + totalBudget + ", isCustomAudience="
				+ isCustomAudience + ", customAudienceFile=" + customAudienceFile + ", fbCategroy=" + fbCategroy
				+ ", audienceAttachType=" + audienceAttachType + ", campObjective=" + campObjective + ", scheduled="
				+ scheduled + ", postId=" + postId + ", lifeTimeBudget=" + lifeTimeBudget + ", url=" + url
				+ ", facebookCampaignMaster=" + facebookCampaignMaster + "]";
	}






}
