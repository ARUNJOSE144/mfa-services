package com.sixdee.magik.services.model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;
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

/**
 * @author Rajesh
 * @Date : September, 2018
 *
 */

/**
 * @author amal.as
 *
 */
@Entity
@Table(name = "CAMPAIGN_DEFINTION_MASTER")
@SuppressWarnings("serial")
public class CampaignMasterTO implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "CampaignMasterTO")
	@TableGenerator(name = "CampaignMasterTO", allocationSize = 1)
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

	/*
	 * @Column(name = "EXPIRY_DATE") private Date expiryDate;
	 */

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

	@Transient
	private int noOfRules;

	@Transient
	private String statusDesc;

	@Transient
	private String createDateUI;

	@Transient
	private String startDateUI;

	@Transient
	private String endDateUI;

	/*
	 * @Transient private String expiryDateUI;
	 */

	@Transient
	private int noOfActiveRules;

	@Transient
	private int noOfInActiveRules;

	@Transient
	private int totalNoOfSegments;

	@Transient
	private String createDateForOverview;

	@Transient
	private String startDateForOverview;

	@Transient
	private String endDateForOverview;

	/*
	 * @Transient private String expiryDateForOverview;
	 */

	@Column(name = "SEGMENT_TYPE")
	private int campaignSegmentType;

	@Transient
	private String campaignTypeIcon;

	@Transient
	private String campaignSegmentTypeUI;

	@Transient
	private String campaignTypeUI;

	@Column(name = "GEO_FENCING_ID")
	private int geoFencingId;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "facebookCampaignMaster")
	private List<FacebookCampaignMasterDTO> facebookCampaignDetails;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "googleCampaignMaster")
	private List<GoogleCampaignMasterDTO> googleCampaignDetails;

	@Transient
	private String facebookPage;
	@Transient
	private int campaignMediaFormat;
	@Transient
	private String mediaName;
	@Transient
	private String titleName;
	@Transient
	private String titleDescription;
	@Transient
	private String mediaPath;
	@Transient
	private String bid;
	@Transient
	private String dailyBudget;
	@Transient
	private String totalBudget;
	@Transient
	private int fbId;
	@Transient
	private int isCustomAudience;
	@Transient
	private String customAudienceFile;
	@Transient
	private String fbCategroy;
	@Transient
	private int audienceAttachType;
	@Transient
	private String campObjective;
	@Transient
	private String postId;
	@Transient
	private String lifeTimeBudget;
	@Transient
	private int googleId;
	@Transient
	private int adId;
	@Transient
	private String budgetName;
	@Transient
	private String googleChannelType;
	@Transient
	private String url;

	public int getGeoFencingId() {
		return geoFencingId;
	}

	public void setGeoFencingId(int geoFencingId) {
		this.geoFencingId = geoFencingId;
	}

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

	/*
	 * public Date getExpiryDate() { return expiryDate; }
	 * 
	 * public void setExpiryDate(Date expiryDate) { this.expiryDate = expiryDate; }
	 */
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getNoOfRules() {
		return noOfRules;
	}

	public void setNoOfRules(int noOfRules) {
		this.noOfRules = noOfRules;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public String getCreateDateUI() {
		return createDateUI;
	}

	public void setCreateDateUI(String createDateUI) {
		this.createDateUI = createDateUI;
	}

	public String getStartDateUI() {
		return startDateUI;
	}

	public void setStartDateUI(String startDateUI) {
		this.startDateUI = startDateUI;
	}

	public String getEndDateUI() {
		return endDateUI;
	}

	public void setEndDateUI(String endDateUI) {
		this.endDateUI = endDateUI;
	}
	/*
	 * public String getExpiryDateUI() { return expiryDateUI; }
	 * 
	 * public void setExpiryDateUI(String expiryDateUI) { this.expiryDateUI =
	 * expiryDateUI; }
	 */

	public Object cloneObject(Object obj) {
		try {
			Object clone = obj.getClass().newInstance();
			for (Field field : obj.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				field.set(clone, field.get(obj));
			}
			return clone;
		} catch (Exception e) {
			return null;
		}
	}

	public int getNoOfActiveRules() {
		return noOfActiveRules;
	}

	public void setNoOfActiveRules(int noOfActiveRules) {
		this.noOfActiveRules = noOfActiveRules;
	}

	public int getNoOfInActiveRules() {
		return noOfInActiveRules;
	}

	public void setNoOfInActiveRules(int noOfInActiveRules) {
		this.noOfInActiveRules = noOfInActiveRules;
	}

	public String getCreateDateForOverview() {
		return createDateForOverview;
	}

	public void setCreateDateForOverview(String createDateForOverview) {
		this.createDateForOverview = createDateForOverview;
	}

	public String getStartDateForOverview() {
		return startDateForOverview;
	}

	public void setStartDateForOverview(String startDateForOverview) {
		this.startDateForOverview = startDateForOverview;
	}

	public String getEndDateForOverview() {
		return endDateForOverview;
	}

	public void setEndDateForOverview(String endDateForOverview) {
		this.endDateForOverview = endDateForOverview;
	}

	public int getCampaignSegmentType() {
		return campaignSegmentType;
	}

	public void setCampaignSegmentType(int campaignSegmentType) {
		this.campaignSegmentType = campaignSegmentType;
	}

	public String getCampaignSegmentTypeUI() {
		return campaignSegmentTypeUI;
	}

	public void setCampaignSegmentTypeUI(String campaignSegmentTypeUI) {
		this.campaignSegmentTypeUI = campaignSegmentTypeUI;
	}

	public String getCampaignTypeUI() {
		return campaignTypeUI;
	}

	public void setCampaignTypeUI(String campaignTypeUI) {
		this.campaignTypeUI = campaignTypeUI;
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

	public String getCampaignTypeIcon() {
		return campaignTypeIcon;
	}

	public void setCampaignTypeIcon(String campaignTypeIcon) {
		this.campaignTypeIcon = campaignTypeIcon;
	}

	public int getIsTemplate() {
		return isTemplate;
	}

	public void setIsTemplate(int isTemplate) {
		this.isTemplate = isTemplate;
	}

	public int getDefaultTemplate() {
		return defaultTemplate;
	}

	public void setDefaultTemplate(int defaultTemplate) {
		this.defaultTemplate = defaultTemplate;
	}

	public int getTotalNoOfSegments() {
		return totalNoOfSegments;
	}

	public void setTotalNoOfSegments(int totalNoOfSegments) {
		this.totalNoOfSegments = totalNoOfSegments;
	}

	public List<FacebookCampaignMasterDTO> getFacebookCampaignDetails() {
		return facebookCampaignDetails;
	}

	public void setFacebookCampaignDetails(List<FacebookCampaignMasterDTO> facebookCampaignDetails) {
		this.facebookCampaignDetails = facebookCampaignDetails;
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

	public int getFbId() {
		return fbId;
	}

	public void setFbId(int fbId) {
		this.fbId = fbId;
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

	public int getIsCustomAudience() {
		return isCustomAudience;
	}

	public void setIsCustomAudience(int isCustomAudience) {
		this.isCustomAudience = isCustomAudience;
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

	public List<GoogleCampaignMasterDTO> getGoogleCampaignDetails() {
		return googleCampaignDetails;
	}

	public void setGoogleCampaignDetails(List<GoogleCampaignMasterDTO> googleCampaignDetails) {
		this.googleCampaignDetails = googleCampaignDetails;
	}

	public int getGoogleId() {
		return googleId;
	}

	public void setGoogleId(int googleId) {
		this.googleId = googleId;
	}

	public int getAdId() {
		return adId;
	}

	public void setAdId(int adId) {
		this.adId = adId;
	}

	public String getBudgetName() {
		return budgetName;
	}

	public void setBudgetName(String budgetName) {
		this.budgetName = budgetName;
	}

	public String getGoogleChannelType() {
		return googleChannelType;
	}

	public void setGoogleChannelType(String googleChannelType) {
		this.googleChannelType = googleChannelType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "CampaignMasterTO [id=" + id + ", campaignName=" + campaignName + ", campaignDesc=" + campaignDesc
				+ ", campaignType=" + campaignType + ", status=" + status + ", startDate=" + startDate + ", endDate="
				+ endDate + ", isTemplate=" + isTemplate + ", createDate=" + createDate + ", targetCount=" + targetCount
				+ ", playPauseStatus=" + playPauseStatus + ", defaultTemplate=" + defaultTemplate + ", userId=" + userId
				+ ", noOfRules=" + noOfRules + ", statusDesc=" + statusDesc + ", createDateUI=" + createDateUI
				+ ", startDateUI=" + startDateUI + ", endDateUI=" + endDateUI + ", noOfActiveRules=" + noOfActiveRules
				+ ", noOfInActiveRules=" + noOfInActiveRules + ", totalNoOfSegments=" + totalNoOfSegments
				+ ", createDateForOverview=" + createDateForOverview + ", startDateForOverview=" + startDateForOverview
				+ ", endDateForOverview=" + endDateForOverview + ", campaignSegmentType=" + campaignSegmentType
				+ ", campaignTypeIcon=" + campaignTypeIcon + ", campaignSegmentTypeUI=" + campaignSegmentTypeUI
				+ ", campaignTypeUI=" + campaignTypeUI + ", geoFencingId=" + geoFencingId + ", facebookCampaignDetails="
				+ facebookCampaignDetails + ", googleCampaignDetails=" + googleCampaignDetails + ", facebookPage="
				+ facebookPage + ", campaignMediaFormat=" + campaignMediaFormat + ", mediaName=" + mediaName
				+ ", titleName=" + titleName + ", titleDescription=" + titleDescription + ", mediaPath=" + mediaPath
				+ ", bid=" + bid + ", dailyBudget=" + dailyBudget + ", totalBudget=" + totalBudget + ", fbId=" + fbId
				+ ", isCustomAudience=" + isCustomAudience + ", customAudienceFile=" + customAudienceFile
				+ ", fbCategroy=" + fbCategroy + ", audienceAttachType=" + audienceAttachType + ", campObjective="
				+ campObjective + ", postId=" + postId + ", lifeTimeBudget=" + lifeTimeBudget + ", googleId=" + googleId
				+ ", adId=" + adId + ", budgetName=" + budgetName + ", googleChannelType=" + googleChannelType
				+ ", url=" + url + "]";
	}

}
